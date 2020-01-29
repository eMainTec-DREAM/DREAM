package common.struts;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.module.sitemesh.RequestConstants;

import common.bean.MwareConfig;
import common.bean.ResponseDTO;
import common.bean.User;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.util.CommonUtil;
import common.util.MessageUtil;

/**
 * 각 strutsAction 을 호출한다.
 * strutsAction 의 수행 전, 후 에 따른 공통 처리를 해준다.
 * 포딩에 따른 공통 처리 등을 처리한다.
 * @author  javaworker
 * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
 * @since   1.0
 *
 */
public abstract class SuperAuthAction
        extends BaseAction
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(SuperAuthAction.class);

    public SuperAuthAction()
    {
        super();
    }

    /**
     * @see org.apache.struts.action.Action#execute(
     *      org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {   
        ActionForward returnMapping = null;
        Map auditMap = beforeExecute(form, request);
        
        try 
        {
            //Audit Data Setting
            request.setAttribute("auditMap", auditMap);
            // execute struts action
            returnMapping = run(mapping, form, request, response);
            request.setAttribute("FORM_NAME", mapping.getName());
            
            //Super Auth Action을 사용하는 모든 페이지는 권한 부여한다. 
            request.setAttribute("AUTH", "OK");
            request.setAttribute("AUTHMSG","");
            
            //set Page Title with fileName
            super.setPageTitle(request, CommonUtil.getPageIdFromPath(returnMapping.getPath()));
            
            afterExecute();

            // ======================================================
            // before fowading page, set page configuration
            setPageConfig(request, returnMapping.getPath());
            //=======================================================
            
            if (logger.isDebugEnabled()) logger.debug(returnMapping.toString());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            
            //Save Error Log
            String errorLogId = saveErrorLog(e, getUser(request), request.getRequestURI());
            
            if ("true".equals(request.getParameter("isAjax")))
            {
                ResponseDTO responseDTO = new ResponseDTO.Builder(MessageUtil.getMessage(getUser(request).getLocale(), "MESSAGE", "CMSG104"))
                    .status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .addExt("errorLogId", errorLogId)
                    .build();
                
                CommonUtil.makeJsonResult(responseDTO, response);
                return mapping.findForward("jsonPage");
            }
            else if ("true".equals(request.getParameter("isGridSearch")))
            {
                response.getWriter().print("{\"errorLogId\":\""+errorLogId+"\"}");
                return mapping.findForward("jsonPage");
            }
            else if ("true".equals(request.getParameter("isTreeAction")))
            {
//                request.getSession().setAttribute("isTreeAction", "true");
            }
            else if ("true".equals(request.getParameter("isAjaxRequest")))
            {
                setAjaxError(request, errorLogId);
                return mapping.findForward("ajaxXmlVal");
            }
            else 
            {
                returnMapping = mapping.findForward("errorException");
                request.setAttribute("errorLogId", errorLogId);
                setPageConfig(request, returnMapping.getPath());
                return returnMapping;
            }
        }        
        return returnMapping;
    }

    /**
     * 메뉴 접속 Log를 기록한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param path
     * @return
     */
    private void saveAccessLog(HttpServletRequest request, String path)
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");

        configService.saveAccessLog(getUser(request), path);
    }
    
    /**
     * 해당 페이지에 권한이 있는지 체크한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param path
     * @return
     * @throws Exception 
     */
    private boolean checkAuthoPage(HttpServletRequest request, String path) throws Exception
    {
        // 현재 Page Id 조회
        String currentPageId = CommonUtil.getPageIdFromPath(path);  //maEqMstrList

        // 해당 row map
        Map tempMap = null;
        
        Map superPageName = new HashMap();
        superPageName.put("JSON","jsonPage");
        superPageName.put("AJAX", "ajaxXmlVal");
        superPageName.put("REPORT", "pdfViewer");
        superPageName.put("RDREPORT", "report");
        superPageName.put("INIT","index");
       // superPageName.put("INITPAGE", getUser(request).getFileName());

        //Menu Page Id에 속하지 않거나 jsonPage Forwarding이 아닌 Tab Page 강제 이동을 막음
        if(!superPageName.containsValue(currentPageId)) 
        {
            String[] secPages = getSecObject(request, "PAGE");  //papage_id
            Hashtable pageHash = MwareConfig.getPagesTable();   //모든 페이지 소환 ('pmMstrList':텝리스트(ArrayList))
            List pageList= null;

            boolean isSec = true;
            
            if(pageHash.containsKey(currentPageId))
            {
                pageList = (ArrayList) pageHash.get(currentPageId); //페이지 정보 (pageId, filename...)
                for(Object pages:  pageList)
                {
                    Map page = (Map)pages;
                    
                    String chkAuth = String.valueOf(page.get("ISCHKAUTH"));
                    if("N".equals(chkAuth) || "null".equals(chkAuth))
                    {
                        isSec = false;  
                    }
                    
                    String pageId = String.valueOf(page.get("PAGEID"));
                    if(secPages != null)
                        for(String secPage : secPages)
                        {
                            //현재 페이지가 Security Tab에 권한을 받은 페이지면 ok
                            if(secPage.equals(pageId)) isSec = false;  
                        }
                }
                
                if(currentPageId.equals("maMyInfo")) isSec = false;
            }

            return isSec;
        }
        

        return false;
    }

    /**
     * page로 리턴될때 필요한 value 값을 조회, 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param path
     */
    private void setPageConfig(HttpServletRequest request, String path)
            throws Exception
    {
        /*
         * page config 처리 하지 않는 경우  
         * grid에 값을 조회하는 경우 : isGridSearch 파라메터가 true
         * Tree 에서 처리하는 경우     : isTreeAction   파라메터가 true
         * Ajax Request 하는 경우   : isAjaxRequest  파라메터가 true
         */ /*
        if ("true".equals(request.getParameter("isGridSearch")) ||
            "true".equals(request.getParameter("isTreeAction"))   ||
            "true".equals(request.getParameter("isAjaxRequest")))
        
         * if ("/common/jsp/sheetCommonFind.jsp".equals(path) ||
         * "/common/jsp/sheetCommonSave.jsp".equals(path))
         */

        // Filter 적용 여부
        if (request.getAttribute(RequestConstants.FILTER_APPLIED) != null)
        {
            Boolean filterApplid = (Boolean)request.getAttribute(RequestConstants.FILTER_APPLIED);
            
            // filter 가 적용안되었다면 필요 없음
            if (!filterApplid.booleanValue()) return;
        }
        else return;

        //=============================================================
        // 사용자의 현재 Locale 셋팅 : bean tab 사용하여 jsp에서 국제화시 적용됨
        if(getUser(request) == null)
        {
        	ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        	//setLocale(request, new Locale(configService.findLanguage()));
        }
        // session 설정시 처음 locale 셋팅한다.
        //=============================================================
        
      //=========================================================
        // 현재 Page Id 조회
        String currentPageId = CommonUtil.getPageIdFromPath(path);
        request.setAttribute("currentPageId", currentPageId);
        //=========================================================
        
        String menuId = CommonUtil.getMenuId(request, currentPageId);
        if(menuId != null && menuId  != "")// menuId = getUser(request).getInitMenuId();
        {
            Hashtable menuPathTable = MwareConfig.getMenuPathTable();
            String [] tempMenuPath = (String [])menuPathTable.get(menuId);
            request.setAttribute("menuPath", tempMenuPath);
            request.setAttribute("menuId", menuId);
        }
      
        //Tab Page Setting
        Hashtable tapPageHash = MwareConfig.getTabPagesTable();
        if(tapPageHash.containsKey(currentPageId)){
        	request.setAttribute("tabPage", tapPageHash.get(currentPageId));
        	//tabPageInclude.jsp 에서 탭페이지의 타이틀명을 설정할 때 사용
//        	request.setAttribute("tabTitle", getTabPages(request,currentPageId,"TABTITLE"));
        }
        
        //Page Fields 세팅
//        Hashtable pageFieldsHash = MwareConfig.getPageFieldTable();
//        if(pageFieldsHash.containsKey(currentPageId)){
//            request.setAttribute("pageFields", pageFieldsHash.get(currentPageId));
//        }
        
        Hashtable buttonHash = MwareConfig.getButtonTable();
        request.setAttribute("pageButton",buttonHash.get(currentPageId));
        //=====================================================================
        // 현재 페이지의 security에 등록된 button 셋팅
//        request.setAttribute("SEC_BUTTON", getSecButton(request));
        //=====================================================================
        
        //========================================
        // 공통 script 정보 조회
        // common.js 및 공통 스크립트 메세지를 추출한다.
        getCommonMessages(request);
        //========================================
        
        HttpSession session = request.getSession();
        if(session.getAttribute("ctPath") == null)
            session.setAttribute("ctPath", MwareConfig.getInitCtPath());
    }

    private Object getPageButtons(HttpServletRequest request,  String currentPageId) throws Exception
    {
    	ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
    	//
        Hashtable buttonHash = MwareConfig.getButtonTable();
        String[] secButtons = getSecObject(request, "BUTTON");  //pgBtnId
        ArrayList buttonList = null;
        ArrayList rtnBtnList = null;
        
        if(buttonHash.containsKey(currentPageId))
        {
            buttonList = (ArrayList) buttonHash.get(currentPageId);
            rtnBtnList = new ArrayList();
            
            for(Object buttons :  buttonList)
            {
                Map button = (Map)buttons;
                
                //패이지 권한 채크 안하면 그냥 패스
                String chkAuth = String.valueOf(button.get("ISCHKAUTH"));
                if("N".equals(chkAuth) || "null".equals(chkAuth))
                {
                    rtnBtnList.add(buttons);
                    continue;
                }
                
                //버튼 권한 채크
                String chkAuthBtn = String.valueOf(button.get("ISCHKAUTHBTN"));
                if("N".equals(chkAuthBtn) || "null".equals(chkAuthBtn))
                {
                    rtnBtnList.add(buttons);
                    continue;
                }
                
                boolean isSecBtn = false;
                if(secButtons != null)
                    for(String secButton : secButtons)
                    {
                        if(secButton.equals(String.valueOf(button.get("PGBTNID")))) isSecBtn = true;
                    }
                
                if(isSecBtn)
                {
                    rtnBtnList.add(buttons);
                }
            }
        }

        return rtnBtnList;
    }

    /**
     * 해당 페이지의 tab 내역을 셋팅한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param currentPageId
     * @param type (TABTITLE : 탭 타이틀명가져올때 , TABPAGE : 탭 페이지명 가져올때)
     * @return
     * @throws Exception 
     */
    private Object getTabPages(HttpServletRequest request, String currentPageId, String type) throws Exception
    {
        String [] tabInfos = null;
        
        String[] secTabs = getSecObject(request, "TAB");  //pgBtnId
        Hashtable tapPageHash = MwareConfig.getTabPagesTable();   //모든 텝 페이지 소환 ('pmMstrList':텝리스트)
        List tabPageList= null;
        List rtnPageList= null; 
        
        if(tapPageHash.containsKey(currentPageId))
        {
            tabPageList = (ArrayList)tapPageHash.get(currentPageId); //지금까지 각 메뉴의 TabPage를 관리했지만 이젠 각페이지에서 관리

            rtnPageList = new ArrayList();
            
            for(Object tabs :  tabPageList)
            {
                Map tab = (Map)tabs;
                
                //권한 채크 안하면 그냥 패스
                String chkAuth = String.valueOf(tab.get("ISCHKAUTH"));
                if("N".equals(chkAuth) || "null".equals(chkAuth))
                {
                    rtnPageList.add(tabs);
                    continue;
                }
                
                boolean isSec = false;
                if(secTabs != null)
                    for(String secTab : secTabs)
                    {
                        if(secTab.equals(String.valueOf(tab.get("PGPAGEID")))) isSec = true;
                    }
                
                if(isSec)
                {
                    rtnPageList.add(tabs);
                }
            }

        }
        return rtnPageList;
        
//        
//        
//        
//        
//        
//        if (tempTabPages != null)
//        {
//            
//            tapPageHash.
//            
//            int tabLength = tempTabPages.length;
//            ArrayList tabArrayList = new ArrayList();
//            for (int i=0; i<tabLength; i++)
//            {
//                boolean securityFlag = false;
//                for (int j=0; secTabs != null && j<secTabs.length; j++)
//                {
//                    String tempSecurityObjectId = secTabs[j];
//                    
//                    System.out.println("!!!!"+tempTabPages[i]);
//                    // security에 등록되어 있다면 flag를 true로 바꿔서 저장되지 않게 한다.
//                    if (tempTabPages[i].equals("TAB." + tempSecurityObjectId))
//                    {
//                        securityFlag = true;
//                        break; // exit for(j)
//                    }
//                }
//                
//
//                // security 내역에 없는 경우만 저장한다.
//                if (!securityFlag)
//                {
//                	if("TABTITLE".equals(type)) tabArrayList.add(tempTabTitles[i]);
//                	else if("TABPAGE".equals(type)) tabArrayList.add(tempTabPages[i]);
//                }
//            }
//            
//            //==========================================================
//            /*
//             * 해당 페이지에서 셋팅되어 있던 해당 페이지 의 tab 들을 security를 제외한
//             * tab 내역을 셋팅한 arrayList를 다시 String [] 로 캐스팅 하여 
//             * 리턴한다.
//             */ 
//            Object [] obj = tabArrayList.toArray();
//            int objTabLength = obj.length;
//            tabInfos = new String[objTabLength];
//            
//            for (int j=0; j<objTabLength; j++)
//            {
//            	tabInfos[j] = obj[j]==null?"":obj[j].toString();
//            }
//        }
//
//        return tabInfos;
    }

    /**
     * 공통 메세지를 추출하여 request에 셋팅한다.
     * commonInclude.jsp에서 이값을 
     * 스크립트 로 만들어서 공통 스크립트에서 키값으로 사용가능하게 정의 한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     */
    protected void getCommonMessages(HttpServletRequest request)
    {
        getCommonMessages(request, false);
    }
    
    /**
     * 공통 메세지를 추출하여 request에 셋팅한다.
     * commonInclude.jsp에서 이값을 
     * 스크립트 로 만들어서 공통 스크립트에서 키값으로 사용가능하게 정의 한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param reload : reload flag
     */
    protected void getCommonMessages(HttpServletRequest request, boolean reload)
    {
        if (!reload)
        {
            // reload 아니더라도 message가 셋팅이 안되어 있다면 조회 되게 한다.
            List messageObj = (ArrayList)request.getSession().getAttribute("COMMON_MESSAGES");
//            if (messageObj != null) return;
            if (messageObj != null && messageObj.size() > 0) return;
        }
        
        DataBaseMessageResources dataBaseMessageResources = 
            (DataBaseMessageResources)getResources(request);
       
        // 현재 셋팅된 언어 
        String localeKey = "";
        User user = (User) request.getSession().getAttribute(request.getSession().getId());
        if(user == null)
        {
            //localeKey = getLocale(request).getLanguage();
            ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
            // session 이 종료 되었을때 아무거나 message를 조회하여 struts 가 메세지리소스를 로딩하게 한다.
            //dataBaseMessageResources.getMessage(locale, "index.mainTitle");
            localeKey = configService.findLanguage();
            dataBaseMessageResources.loadLocale(localeKey);
        }
        else
        {
            localeKey = getUser(request).getLocale().getLanguage();
        }
        
        // 로딩된 언어 추출
        HashMap hashMap = dataBaseMessageResources.getMessages();
        
        // 설정된 Key
        Object [] keyArray = hashMap.keySet().toArray();
        
        // 셋팅할 공통 메세지
        ArrayList commMessages = new ArrayList();
        
        for (int i=0; i<keyArray.length; i++)
        {
            String tempKey = (String)keyArray[i];
            
            // key 중 [COMMON.CMSG] 로 시작하는 KEY 값들을 추출한다.
            if (tempKey.indexOf(localeKey + ".COMMON.") != -1 || tempKey.indexOf(localeKey + ".GRID.") != -1)
            {
                String [] tempCommonArray = new String[2];
                
                tempCommonArray[0] = tempKey.replaceFirst(localeKey+".", ""); // 앞의 언어 설정을 키값에서 뺀다.
                tempCommonArray[1] = (String)hashMap.get(tempKey);
                
                // 메세지 키와 내용을 셋팅한다.
                commMessages.add(tempCommonArray);
            }
        }
        
        request.getSession().setAttribute("COMMON_MESSAGES", commMessages);
    }
    
    /**
     * 현재 로긴 유저가 로긴 상태인지 session id로 체크
     * @author  mentor
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    private boolean checkLoginUser(HttpServletRequest request)
    {
        boolean result = false;
        
        /*
         * UserService 객체에서
         * loginUsers 라는 hashtable에 현재의  session id를  검색을 하여
         * 해당 session id 키 값이 있는지를 체크 한다. 
         * 해당 키 값이  있으면 true를 리턴하고  
         * 해당 키 값이 없으면  false를 리턴을 한다. 
         */
        String sessionId = request.getSession().getId(); //현재 session id 값을 얻어온다.
        Hashtable loginUsers = BaseAction.getLoginUsers(); 
        
        // 현재 session ID로 저장된 User 객체가 있는지 체크한다.
        Object loginUser = loginUsers.get(sessionId); 
        if (loginUser != null)
        {
            result = true;
        }
        
        return result;
    }
    
    protected String getMessage(HttpServletRequest request, String key)
    {
        return super.getMessage(request, key);
        /*if (key == null || "".equals(key))
        {
            return "";
        }
        
        
         * key 값이 유효한지 체크한다. ex)MENU_ID.keyId
         * . 이 없거나 . 의 앞뒤에 key Id 가 없다면 "" 를 리턴한다.
         
        int decimalIndex = key.indexOf(".");
        if (decimalIndex == -1 || 
                key.substring(0, decimalIndex).equals("") || 
                key.substring(decimalIndex+1).equals(""))
        {
            return "";
        }
        
        java.util.Locale locale = getUser(request).getLocale();
        org.apache.struts.util.MessageResources messages = getResources(request);
        return messages.getMessage(locale, key);*/
    }
    
    /**
     * 사용목적 : 하위의 모든 클래스는 이 메소드를 오버라이드 하여 그곳에 로직을 구현한다. <br>
     * 사용법 : <br>
     * 구현 설명 : <br>
     * 작성 날짜 : (05-03-31 오전 11:13:24)
     * 
     * @see kbl.ea.monitor.common.struts.BaseAction#run(
     *      org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected abstract ActionForward run(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    
    /**
     * 이전,다음 페이지를 위한 Key를 저장한다.
     * List Key를 저장한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param listKey
     * @param pageNum
     * @return
     */
    protected boolean setListKey(HttpServletRequest request, String [] listKey, int pageNum)
    {
        /*
         * Search : 새로 조회 
         * Next   : 다음 페이지 조회
         * Pre    : 이전 페이지 조회
         */ 
        String sheetAction = request.getParameter("sheetActionName");

        /*
         * LIST_KEY를 popup[퀵메뉴] 으로 띄워서 이전,다음 KEY값을 저장하는 경우 충돌이 일어난다.
         * 그래서 LIST_KEY를 POPUP 인경우와 아닌 경우로 구분한다. 
         */
        String listKeyName = "LIST_KEY";
        if ("popupPage".equals(request.getParameter("isDecoratorName")))
        {
            listKeyName = "POPUP_LIST_KEY";
        }
        
        String [] prevKey = (String [])request.getSession().getAttribute(listKeyName);
        
        // 첫번째 page 이거나 조회된 그전 Key 가 없다면 현재 key만 저장한다.
        if (("Search".equals(sheetAction) && pageNum == 1) || prevKey == null)
        {
            request.getSession().setAttribute(listKeyName, listKey);
        }
        else 
        {
            String [] tempKey = null;
            
            // 다음 페이지 조회 이므로 prevKey + listKey 를 한다.
            if ("Next".equals(sheetAction))
            {
                tempKey = CommonUtil.addTwoStringArray(prevKey, listKey);  
                request.getSession().setAttribute(listKeyName, tempKey);
            }
        }
                
        return false;
    }
    
    /**
     * request 에 저장된 list key 에서 현재 키 위치를 계산하여 page num을 계산 리턴한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    protected int getListPageNum(HttpServletRequest request, String currKey)
    {
        int index = -1;
        int page = -1;
        
        /*
         * LIST_KEY를 popup[퀵메뉴] 으로 띄워서 이전,다음 KEY값을 저장하는 경우 충돌이 일어난다.
         * 그래서 LIST_KEY를 POPUP 인경우와 아닌 경우로 구분한다. 
         */
        String listKeyName = "LIST_KEY";
        if ("popupPage".equals(request.getParameter("isDecoratorName")))
        {
            listKeyName = "POPUP_LIST_KEY";
        }
        
        // 세션에 저장된 키값들을 가져 온다.
        String [] listKey = (String []) request.getSession().getAttribute(listKeyName);
        
        if(listKey != null && !"".equals(listKey))
        {
            for(int i=0; i<listKey.length; i++)
            {
                if(listKey[i].equals(currKey)) 
                {
                    index = i;
                }
            }
        }
        
        if(index != -1)
        {
            page = (index /100) + 1;
            
            /*
             * 계산 된 페이지 number 이하의 페이지 Key는 모두 삭제한다.
             * 1, 2, 3 조회후 2페이지 값을 선택후 상세에 갔다가 다시 목록으로 오면 2페이지만 조회되는다.
             * 이때 다음 페이지 조회가 되면서 다시 3페이지가 list_key 에 셋팅되기 때문이다. 
             */
            request.getSession().setAttribute(listKeyName, resetListArray(listKey, page*100));
        }
        
        return page;
    }
    
    /**
     * listKey[] 의 배열을 offset 만큼의 배열을 리턴한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param listKey
     * @param offSet
     * @return
     */
    private String[] resetListArray(String [] listKey, int offSet)
    {
        if (listKey.length < offSet)
        {
            return listKey;
        }
        
        String [] resultList = new String[offSet];
        for (int i=0; i<offSet; i++)
        {
            resultList[i] = listKey[i];
        }
        
        return resultList;
    }
    
    /**
     *  이전, 다음 Key를 셋팅하여 리턴한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param keyNo
     * @return
     */
    protected String[] getPrevNextButton(HttpServletRequest request, String keyNo)
    {
        /*
         * LIST_KEY를 popup[퀵메뉴] 으로 띄워서 이전,다음 KEY값을 저장하는 경우 충돌이 일어난다.
         * 그래서 LIST_KEY를 POPUP 인경우와 아닌 경우로 구분한다. 
         */
        String listKeyName = "LIST_KEY";
        if ("popupPage".equals(request.getParameter("isDecoratorName")))
        {
            listKeyName = "POPUP_LIST_KEY";
        }
        
        String [] aKeyNo = (String [])request.getSession().getAttribute(listKeyName);
        String [] prevNextButton = CommonUtil.getPrevNextButton(aKeyNo, keyNo);
        
        return prevNextButton;
    }
    
    /**
     * totalCount 가 0 이라면 조회하지 않고,
     * sheet 구성을 끝낸다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param totalCount
     * @param request
     * @return
     */
    protected boolean checkTotalCount(int totalCount, HttpServletRequest request) 
    {
        if (totalCount > 0)
        {
            return false;            
        }
        
        // 조회한 List 를 form에 셋팅한다.
        request.setAttribute(FIND_XML_ATTRIBUTE, new LinkedList());
        
        // 처음 조회할때 가지고 있던 total count를 계속 가지고 있는다.
        request.setAttribute(TOTAL_COUNT_ATTRIBUTE, totalCount + "");
        
        return true;
    }
    
    /**
     * page 에 등록된 security button을 배열로 리턴한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param buttonHash
     * @throws Exception
     */
    private String[] getSecObject(HttpServletRequest request, String type) throws Exception
    {
        //String currentPageId = (String)request.getAttribute("currentPageId");

        String userGroup = getUser(request).getUsrgrpId();
        Hashtable securityTable = MwareConfig.getSecurityTable();        
        
        return (String[])securityTable.get(userGroup + "."+type);
    }
    
    /**
     * buttonHash 안의 PageKey 값으로 저장되어있는 
     * String [] 의 button id 값들을 Hashtable의 Key로 buttion name을 value
     * 로 다시 buttonHash안의 pageKey 값으로 저장한다.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param buttonHash
     * @throws Exception

    private Hashtable getButtonHash(HttpServletRequest request, 
            String [] pageButtons, String currentPageId) throws Exception
    {
        if (pageButtons == null)
        {
            return null;            
        }

        //=========================================================================
        // 다국어 를 처리 하기 위한 message 객체
        java.util.Locale locale = getUser(request).getLocale();
        org.apache.struts.util.MessageResources messages = getResources(request);
        //=========================================================================

        String userGroup = getUser(request).getUserGroup();
        Hashtable securityTable = MwareConfig.getSecurityTable();        
        
        Hashtable tmepHash = new Hashtable();
        
        for (int i=0, length=pageButtons.length; i<length; i++)
        {
            String tempButtonId = pageButtons[i];

            // ===================================================================
            // T4SECURITY 에서 값이 없는 지 체크한다.
            String securityKey = userGroup + ".BUTTON." + currentPageId;
            String [] securityObjectId = (String []) securityTable.get(securityKey);
            
            boolean securityFlag = false;
            for (int j=0; securityObjectId != null && j<securityObjectId.length; j++)
            {
                String tempSecurityObjectId = securityObjectId[j];
                
                // security에 등록되어 있다면  저장되지 않게 한다.
                if (tempButtonId.equals(tempSecurityObjectId))
                {
                    securityFlag = true;
                }
            }
            
            if (securityFlag)
            {
                // security 에 등록된 값이라면 처리 되지 않게 한다.
                continue;
            }
            
            //====================================================================
            
            try
            {
                // button id 키 값으로 button name을 저장한다.
                tmepHash.put("BUTTON." + tempButtonId, 
                        messages.getMessage(locale, "BUTTON." + tempButtonId));
            }
            catch(Exception ex)
            {
                System.out.println("=========================================================================");
                System.out.println("=                          EXCEPTION !!!!!!!!!                          =");
                System.out.println("=========================================================================");
                System.out.println("=========================================================================");
                System.out.println("================= T4RESOUCE 에 해당 BUTTON ID 값이 없습니다. =================");
                System.out.println("======================BUTTON ID [" + tempButtonId + "]======================");
                System.out.println("=========================================================================");
                System.out.println("=========================================================================");
                System.out.println("=========================================================================");
            }
        }

        return tmepHash;
    }
         */
    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param values
     */
    protected void setAjaxDesc(HttpServletRequest request, String value)
    {
        String[] values = new String[1];
        values[0] = value;
        setAjaxDesc(request, values);
    }
    
    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param values
     */
    protected void setAjaxDesc(HttpServletRequest request, int value)
    {
        String[] values = new String[1];
        values[0] = value+"";
        setAjaxDesc(request, values);
    }

    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param value
     */
    protected void setAjaxDesc(HttpServletRequest request, String[] values)
    {
        if (values != null)
        {
            request.setAttribute("AJAX_DESC", values);
        }
    }

    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param value
     */
    protected void setAjaxId(HttpServletRequest request, String id)
    {
        String[] values = new String[1];
        values[0] = id;
        setAjaxId(request, values);
    }

    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param value
     */
    protected void setAjaxId(HttpServletRequest request, String[] ids)
    {
        if (ids != null)
        {
            request.setAttribute("AJAX_ID", ids);
        }
    }

    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param value
     */
    protected void setAjaxEtc(HttpServletRequest request, String etc)
    {
        String[] values = new String[1];
        values[0] = etc;
        setAjaxEtc(request, values);
    }

    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param value
     */
    protected void setAjaxEtc(HttpServletRequest request, String[] etcs)
    {
        if (etcs != null)
        {
            request.setAttribute("AJAX_ETC", etcs);
        }
    }

    /**
     * values의 값을 id, desc 에 셋팅한다. values[0]:ID, values[1]:DESC
     * 
     * @author javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since 1.0
     * @param request
     * @param values
     */
    protected void setAjaxValues(HttpServletRequest request, String[][] values)
    {
        if (values == null)
        {
            return;
        }
        int valueLength = values.length;
        String[] ids = new String[valueLength];
        String[] descs = new String[valueLength];
        for (int i = 0; i < valueLength; i++)
        {
            ids[i] = values[i][0];      // CODE 셋팅
            descs[i] = values[i][1];    // DESC 셋팅
        }
        setAjaxId(request, ids);
        setAjaxDesc(request, descs);
    }
    
    
    /**
     * 다중 data(이중배열)를 재배열 처리 후 리턴한다.
     * @author  wondo
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param values
     */
    protected void setAjaxData(HttpServletRequest request, String[][] values)
    {
        if (values == null || values.length == 0)
        {
            return;
        }
        
        int colLength = values[0].length;
        int rowLength = values.length;
        
        //기존 배열의 행,열을 열,행으로 재배열
        String[][] data = new String[colLength][rowLength];
        
        for (int i=0; i<rowLength; i++)
        {
            for(int j=0; j<colLength; j++)
            {
                String vale = values[i][j];
                data[j][i] = vale;
                
            }//end for j
            
        }//end for i
        
        request.setAttribute("AJAX_DATA" , data);
    }
    
    /**
     * XML 형태로 만들기위해 Ajax에서 파싱 가능한 변수로 셋팅한다.
     * @author  wondo
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param data
     * @param cnt
     */
    protected void setAjaxData(HttpServletRequest request, String[] data)
    {
        request.setAttribute("AJAX_DATA" , data);
    }
    
    /**
     * AJAX 처리 상태를 셋팅한다. 
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param value
     */
    protected void setAjaxStatus(HttpServletRequest request)
    {
        setAjaxStatus(request, "VALID");
    }
    
    /**
     * AJAX 처리 상태를 셋팅한다. 
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param value
     */
    protected void setAjaxStatus(HttpServletRequest request, String status)
    {
        request.setAttribute("AJAX_STATUS", status);
    }
    
    public int setHeader(HttpServletRequest request,  HttpServletResponse response, String listId, String currentPageId) throws IOException
    {
        return super.setHeader(request, response, listId, currentPageId);
    }

    public void makeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount) throws IOException
    {
        /*Map result = null;
        String isHeaderLoaded = (String)request.getParameter("isHeaderLoaded");

        if("N".equals(isHeaderLoaded))
            result = CommonUtil.makeHeaderJsonByList(resultList, request); //해더가 없으면 해더를 List에서 만들어 뿌린다.
        else
            result = CommonUtil.makeResultJson(resultList); //아니면 그냥 
        
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(result);
//        jsonString = jsonString.replaceAll("\"id\"", "id");
//        System.out.println("!!!!!"+jsonString);

        response.getWriter().print(jsonString);*/
    	Map result = null;
        String isHeaderLoaded = (String)request.getParameter("isHeaderLoaded");
        String firstRow = (String)request.getParameter("firstRow");
        String totCount = (String)request.getParameter("isTotalCount");

        if("".equals(totalCount))totalCount = "0";
        
        if("null".equals(firstRow) || firstRow == null ) firstRow = "0";
        if("null".equals(totCount) || totCount == null || "0".equals(totCount)) totCount = totalCount;
        
        result = CommonUtil.makeResultJson(resultList); //아니면 그냥 

        String totcnt;
        if((Integer.parseInt(firstRow) + Integer.parseInt(MwareConfig.getGridMaxLoadCount())) < Integer.parseInt(totCount)) totcnt = (Integer.parseInt(firstRow) + Integer.parseInt(MwareConfig.getGridMaxLoadCount()))+"";
        else totcnt = totCount;
        
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setFieldNamingStrategy(new UnderScoreToUpper()).create();

        result.put("view_count", totCount);
        result.put("total_count",totcnt);
        result.put("pos", firstRow);
        
        String jsonString = CommonUtil.makeJsonString(result);

        logger.debug(jsonString);

        response.getWriter().print(jsonString);
        
    }
    
    private class UnderScoreToUpper implements FieldNamingStrategy{

        @Override
        public String translateName(Field f) {

            String name = f.getName();

            Pattern p = Pattern.compile("[_][a-z]{1}");

            Matcher m = p.matcher(name);

            while(m.find()){
                String c = m.group(0).replace("_", "").toUpperCase();
                name = name.replace(m.group(0), c);
            }
            return name;
        }

    }
    
    public void makeGridExport(List resultList, HttpServletRequest request,HttpServletResponse response,String listId, String currentPageId, String fileName) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        List headList = null;
        Map resultMap = null;
        
        headList = configService.getUserHeader(getUser(request), listId, currentPageId);
        // TODO Auto-generated method stub
        request.setAttribute("resultList", resultList);
        request.setAttribute("headList", headList);
        request.setAttribute("fileName", fileName);
    }
    
    /**
     * TEST LIST MAKE HEADER
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param resultList
     * @param request
     * @param response
     * @param listId
     * @param labelMap 
     * @throws IOException
     */
    public void makeJsonResultWithHead(List resultList, HttpServletRequest request,  HttpServletResponse response, String listId, Map<String, String> labelMap) throws IOException
    {
        Map result = CommonUtil.makeHeaderJsonByList(resultList, request);
        
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(result);
        System.out.println("!!!!"+jsonString);
        response.getWriter().print(jsonString);

    }
    
    /**
     * For Validation
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param resultMap
     * @param request
     * @param response
     * @throws IOException
     */
    public void makeJson(Map resultMap, HttpServletRequest request,  HttpServletResponse response) throws IOException
    {
        String status = String.valueOf(resultMap.get("status"));
        List resultList = (ArrayList)resultMap.get("list");
        
        List result = CommonUtil.makeJson(resultList);
        
        Gson gson = new Gson();
        JSONObject jsonObj = new JSONObject();
        
        String jsonString = gson.toJson(result);
        
        jsonObj.put("status", status);
        jsonObj.put("list", jsonString);

        response.getWriter().print(jsonObj.toString());

    }
    
    public void makeTreeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String listId) throws IOException
    {
        CommonUtil.makeTreeJsonResult(resultList, request, response, listId);
    }
    
    public List setGridMap(HttpServletRequest request)
    {
        String gridIds = request.getParameterValues("ids")[0];
        String[] gridIdArr = gridIds.split(",");
        
        Map paramMap = request.getParameterMap();
        Iterator<String> keys = null;
        
        List returnList = new ArrayList();
        Map parameterMap = null;
        
        for(String gridId : gridIdArr)
        {
            parameterMap = new HashMap();
            keys = paramMap.keySet().iterator();
            while( keys.hasNext() ){
                String key = keys.next();
                
                if(key.indexOf("_") > 0)
                {
                    String[] idNColumnId = key.split("_");
                    if(gridId.equals(idNColumnId[0]))
                    {
                        parameterMap.put(idNColumnId[1], ((String[])paramMap.get(key))[0]);
                    }
                }

            }
            
            returnList.add(parameterMap);
        }
        
        return returnList;
    }
    
    public void makeJsonResultPop(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount) throws IOException
    {
        Map result = null;
        String firstRow = (String)request.getParameter("firstRow");

        result = CommonUtil.makeHeaderJsonByListAtoCmp(resultList); //해더가 없으면 해더를 List에서 만들어 뿌린다.
        
        Gson gson = new Gson();
        
        result.put("total_count", totalCount);
        result.put("pos", firstRow);

        String jsonString = gson.toJson(result);
        jsonString = jsonString.replaceAll("\"ID\"", "id");
//        System.out.println("!!!!!"+jsonString);

        response.getWriter().print(jsonString);

    }
    
    public void updateAudit(String auditKey, Map auditMap, User user)
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        
        configService.saveAudit(auditKey, String.valueOf(auditMap.get("currentPageId")), auditMap, user);
    }
}
