package common.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.opensymphony.module.sitemesh.RequestConstants;

import common.bean.MwareConfig;
import common.bean.ResponseDTO;
import common.config.service.ConfigService;
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
public abstract class AuthAction
        extends BaseAction
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AuthAction.class);

    public AuthAction()
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

        // =======================================================
        // Session Check !!!
        if (getUser(request) == null)
        {   
            //========================================================================
            if ("true".equals(request.getParameter("isAjax")))
            {
                ResponseDTO responseDTO = new ResponseDTO.Builder(MessageUtil.getMessage(getUser(request).getLocale(), "MESSAGE", "CMSG020"))
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .build();
                
                CommonUtil.makeJsonResult(responseDTO, response);
                return mapping.findForward("jsonPage");
            }
            else if ("true".equals(request.getParameter("isGridSearch")))
            {
                // session time out
                response.getWriter().print("{\"gridException\":\"true\"}");
                return mapping.findForward("jsonPage");
            }
            else if ("true".equals(request.getParameter("isTreeAction")))
            {
                request.getSession().setAttribute("isTreeAction", "true");
            }
            else if ("true".equals(request.getParameter("isAjaxRequest")))
            {
                setAjaxStatus(request, "SESSION_OUT");
                return mapping.findForward("ajaxXmlVal");
            }
            //========================================================================
            /*
             * common.js 및 공통 스크립트 메세지를 추출한다.
             * session 종료되어서 logout page 로 가는경우 common.js 에 다국어 메세지 를 셋팅하여야한다.
             */
            getCommonMessages(request);
            request.getSession().setAttribute("gridException", "-9999");
            
            return mapping.findForward("logoutPage");
        }
        
        // 현재 로긴 유저가 로긴 상태인지 session id로 체크
        // 만약 로그인한 유저 session id가 없을 경우 로그인 화면을 보여준다.
        if (!checkLoginUser(request))
        {
            //========================================================================
            if ("true".equals(request.getParameter("isAjax")))
            {
                ResponseDTO responseDTO = new ResponseDTO.Builder(MessageUtil.getMessage(getUser(request).getLocale(), "MESSAGE", "CMSG020"))
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .build();
                
                CommonUtil.makeJsonResult(responseDTO, response);
                return mapping.findForward("jsonPage");
            }
            else if ("true".equals(request.getParameter("isGridSearch")))
            {
                // 강제 로긴아웃
                response.getWriter().print("{\"gridException\":\"true\"}");
                return mapping.findForward("jsonPage");
            }
            else if ("true".equals(request.getParameter("isTreeAction")))
            {
                request.getSession().setAttribute("isTreeAction", "true");
            }
            else if ("true".equals(request.getParameter("isAjaxRequest")))
            {
                setAjaxStatus(request, "SESSION_OUT");
                return mapping.findForward("ajaxXmlVal");
            }
            
            // 강제 로긴아웃
            request.getSession().setAttribute("gridException", "-8888");
            
            /*
             * common.js 및 공통 스크립트 메세지를 추출한다.
             * session 종료되어서 logout page 로 가는경우 common.js 에 다국어 메세지 를 셋팅하여야한다.
             */
            getCommonMessages(request);
            
            return mapping.findForward("logoutPage");
        }
        // =======================================================
        
        //Action Script
        Map map = request.getParameterMap();
        if(map.containsKey("ACTION_FUNCTION")) request.setAttribute("ACTION_FUNCTION", map.get("ACTION_FUNCTION"));
        if(map.containsKey("IS_FROM_NOTICE")) request.setAttribute("IS_FROM_NOTICE", map.get("IS_FROM_NOTICE"));
        
        
        try 
        {
            //Audit Data Setting
            request.setAttribute("auditMap", auditMap);
            // execute struts action
            returnMapping = run(mapping, form, request, response);
            request.setAttribute("FORM_NAME", mapping.getName());
            
            //화면 권한 관련
            String rtnAuthCode = "OK";
            String rtnAuthMsg  = "";
            if(checkAuthoPage(request, returnMapping.getPath())) //현재 포워딩 되는 페이지에 권한이 없습니다.
            {   
                String currentPageId = CommonUtil.getPageIdFromPath(returnMapping.getPath());
                Hashtable pageHash = MwareConfig.getPagesTable();   //모든 페이지 소환 ('pmMstrList':텝리스트(ArrayList))
                //현재 포워딩 되는 페이지가 사용하는(존재하는) 페이지입니까?
                if(pageHash.containsKey(currentPageId)) rtnAuthMsg = getMessage(request,"MESSAGE.MGS0241"); //이페이지에 대한 권한이 없습니다.
                else rtnAuthMsg = getMessage(request,"MESSAGE.MSG020");  //페이지가 없습니다. 관리자에게 문의하세요.   
                //권한이 없다!
                rtnAuthCode = "NO"; 
            }
            request.setAttribute("AUTH", rtnAuthCode); //권한이 있다:OK, 권한이 없다:NO
            request.setAttribute("AUTHMSG",rtnAuthMsg); //이페이지에 대한 권한이 없습니다. , 페이지가 없습니다. 관리자에게 문의하세요. 
            
            //set Page Title with fileName
            super.setPageTitle(request, CommonUtil.getPageIdFromPath(returnMapping.getPath()));
            
            afterExecute();

            // 포딩되는 페이지에 권한이 있는지 체크
//            if (checkAuthoPage(request, returnMapping.getPath())) return mapping.findForward("loginPage");
            
            if ("Y".equals(MwareConfig.getIsUsePageAccessLogging())) saveAccessLog(request, returnMapping.getPath());
            
            // ======================================================
            // before fowading page, set page configuration
            setPageConfig(request, returnMapping.getPath());
            //=======================================================
            
            //if (logger.isDebugEnabled()) logger.debug(returnMapping.toString());
            
            String currentPageId = (String)request.getAttribute("currentPageId");
            javax.servlet.http.Cookie c = new javax.servlet.http.Cookie("fileDownloadToken_"+currentPageId, "FALSE");
            response.addCookie(c);
            
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
        superPageName.put("UBIREPORT", "ubiReport");
        superPageName.put("EXCEL", "gridExport");
        superPageName.put("INIT","index");
       // superPageName.put("INITPAGE", getUser(request).getFileName());

        if(currentPageId.indexOf("?") >= 0)
        {
            
        }
        //Menu Page Id에 속하지 않거나 jsonPage Forwarding이 아닌 Tab Page 강제 이동을 막음
        else if(!superPageName.containsValue(currentPageId)) 
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

                    //Set Page Title 
                    request.setAttribute("PAGE_TITLE", getMessage(request, String.valueOf(page.get("PAGENAME"))));

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
        // Filter 적용 여부
        if (request.getAttribute(RequestConstants.FILTER_APPLIED) != null)
        {
            Boolean filterApplid = (Boolean)request.getAttribute(RequestConstants.FILTER_APPLIED);
            
            // filter 가 적용안되었다면 필요 없음
            if (!filterApplid.booleanValue()) return;
        }
        else return;

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
        if(tapPageHash.containsKey(currentPageId)) request.setAttribute("tabPage", getTabPages(request,currentPageId,"TABPAGE"));

        request.setAttribute("pageButton", getPageButtons(request, currentPageId));
        
        request.setAttribute("pageLinks", getPageLinks(request, currentPageId));

        //========================================
        // 공통 script 정보 조회
        // common.js 및 공통 스크립트 메세지를 추출한다.
        getCommonMessages(request);
        //========================================
    }

    private Object getPageLinks(HttpServletRequest request,  String currentPageId) throws Exception
    {
    	Hashtable linkHash = MwareConfig.getLinkedTable();
    	ArrayList linkList = null;
    	if(linkHash.containsKey(currentPageId))
        {
    		linkList = (ArrayList) linkHash.get(currentPageId);
        }
    	
    	return linkList;
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
//            if(!"dream".equals(((User)loginUser).getPageType())) result = false;
        }
        
        return result;
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
            }
        }

        return tmepHash;
    }
         */
}
