package common.struts;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fins.org.json.JSONObject;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;

import common.bean.MwareConfig;
import common.bean.User;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.util.CommonUtil;

/**
 * 최상위 Action
 * @author  javaworker
 * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
 * @since   1.0
 *
 */
public abstract class BaseAction
        extends Action
{
    /**
     * Logger for this class
     */
    private static final Logger         logger                  = Logger.getLogger(BaseAction.class);
    public static WebApplicationContext ctx                     = null;
    
    public static final int             DEFAULT_ACTION          = 0;
    // 공통 grid xml 페이지로 객체를 전달 하기 위한 Attribute
    public static final String          SAVE_XML_ATTRIBUTE      = "resultString";
    public static final String          FIND_XML_ATTRIBUTE      = "resultList";
    public static final String          TOTAL_COUNT_ATTRIBUTE   = "totalCount";

    public static final String          REPORT_MAP_ATTRIBUTE    = "REPORT_MAP";
    public static final String          REPORT_NAME_ATTRIBUTE   = "REPORT_NAME";
    // default strutsAction
    public static final int BASE_DEFAULT_INIT = 9999;
    
    // default strutsAction
    public static final int BASE_QUICK_DETAIL = 9191;
    
    // default strutsAction
    public static final int BASE_QUICK_LIST = 9090;
    
    // Excel Export
    public static final int BASE_GRID_EXPORT = 9909;
    
    // Header Set
    public static final int BASE_SET_HEADER = 9010;
    
    // default search 
    public static final int BASE_QUICK_SEARCH = 9020;
    
    // default strutsAction
    public static final int BASE_ACTION_REPORT = 9500;
    
    /** 
     * 사용자 User 객체를 저장해둘 Hashtable 선언 
     */
    private static Hashtable loginUsers = new Hashtable();
    
    /** config loading */
    private boolean configIsLoading = false;

    /**
     * Spring Service Bean 리턴
     * @author  javaworker
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since   1.0
     * 
     * @param name
     * @return
     */
    public Object getBean(String name)
    {
        if (ctx == null)
        {
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(super.servlet.getServletContext());
        }
        logger.debug("get spring service[" + name + "]");
        return ctx.getBean(name);
    }
    
    /**
     * Decorator Pattern 적용
     * @param name
     * @param request
     * @return
     */
    public Object getBean(String name, HttpServletRequest request)
    {
    	return getBean(setDecoBeanId(name, request));
    }

    /**
     * 현재 로그인 유저 리턴
     * @author  javaworker
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    protected User getUser(HttpServletRequest request)
    {
    	return CommonUtil.getUser(request);
       /* HttpSession session = request.getSession();
        
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        
        User user = (User) session.getAttribute(request.getSession().getId());
        if(user == null)
        {
        	//String language = configService.findLanguage();
            String language = "en"; //기본 랭귀지 설정 ?!!!?!!?!?!?
        	Locale locale = new Locale(language);
        	user = new User();
        	user.setLangId(language);
            user.setLocale(locale);
//            user.setCompNo("100");
        }
        return user;*/
    }

    /**
     * struts validation시 사용
     * @author  javaworker
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    protected ActionErrors validateToken(HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();
        if (logger.isDebugEnabled())
        {
            logger.debug("token is : " + isTokenValid(request));
        }

        resetToken(request);
        if (!errors.isEmpty())
        {
            //saveErrors(request, errors);
            saveToken(request);
        }
        return errors;
    }

    /**
     * staruts action 수행 전 호출
     * @author pksup
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since 1.0
     * @param form
     * @param request
     * @return 
     */
    protected Map beforeExecute(ActionForm form, HttpServletRequest request)
            throws Exception
    {
        //System.out.println("<==================================>");
        //System.out.println("[BaseAction System Log : parameter!]");
        Enumeration params = request.getParameterNames();
        
        Map auditMap = new HashMap();
        StringBuffer  encValues = null;
        String stActStr = request.getParameter("strutsAction");
        if(stActStr == null) stActStr = "0";
        int stAct = Integer.parseInt(stActStr==""?"0":stActStr);
        
        while (params.hasMoreElements()) 
        {
            
            String name = (String)params.nextElement();
            String []values = request.getParameterValues(name);
            if (values==null)
            {
                String value = request.getParameter(name);
                //System.out.println(name + "=" + value);
                logger.debug(name + "=" + value);
                encValues = new StringBuffer();
                encValues.append(value);
            }
            else
            {
                encValues = new StringBuffer();
                for (int i=0; i<values.length; i++)
                {
//                    System.out.println(name + "[" + i + "]=" + values[i]);
                	logger.debug(name + "[" + i + "]=" + values[i]);

                    encValues.append(i==0? values[i]:"^|^"+ values[i]);

                }
                
                //3900바이트로 문자열 자르기
                byte[] bytes = encValues.toString().getBytes("utf-8");
                if(bytes.length > 3900){
                    byte[] temp = new byte[3900];
                    for(int i = 0; i < 3900; i++){
                        temp[i] = bytes[i];
                    }
                    bytes = temp;
                }
                
                auditMap.put(name, new String(bytes, "utf-8"));
            }
        }
        
        //System.out.println("<==================================>");
        
        //============================================================
        // parameter로 pageMessage가 전송되었다면 Attribute로 셋팅한다.
        String pageMessage = request.getParameter("pageMessage");
        setPageMessage(request, pageMessage);
        //============================================================
        
        // Config Setting
        if (!configIsLoading)
        {
         // Web Context Path
            MwareConfig.setServerName(request.getServerName());
            // Web Context Path
            MwareConfig.setServerPort(request.getServerPort());
       
            // Web Context Path
            MwareConfig.setContextPath(request.getContextPath());
        }
        
        //===================================
        // 한번 설정 하였다면 다시 호출되자 않게 한다.
        configIsLoading = true;
        //===================================
        
        return auditMap;
    }
    
    /**
     * staruts action 수행 후 호출
     * @author  javaworker
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since   1.0
     *
     */
    protected void afterExecute()
    {}

    /**
     * @param mapping
     * @param form -
     *            ActionForm
     * @param request -
     *            HttpServletRequest
     * @param response -
     *            HttpServletResponse
     * @return ActionForward - forward
     * @throws Exception
     */
    protected abstract ActionForward run(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception;

    public static Hashtable getLoginUsers()
    {
        return loginUsers;
    }

    public static void setLoginUsers(Hashtable loginUsers)
    {
        BaseAction.loginUsers = loginUsers;
    }
    
    /**
     * 수행후 PAGE 에 보여줄 Message 셋팅
     * @author  javaworker
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param value
     */
    protected void setPageMessage(HttpServletRequest request, String pageMessage)
    {
        if (pageMessage == null || "".equals(pageMessage)) return;
        request.setAttribute("PAGE_MESSAGE", pageMessage);
    }
    
    protected String getMessage(HttpServletRequest request, String key)
    {
        if (key == null || "".equals(key))
        {
            return "";
        }
        
        /*
         * key 값이 유효한지 체크한다. ex)MENU_ID.keyId
         * . 이 없거나 . 의 앞뒤에 key Id 가 없다면 "" 를 리턴한다.
         */
        int decimalIndex = key.indexOf(".");
        if (decimalIndex == -1 || 
                key.substring(0, decimalIndex).equals("") || 
                key.substring(decimalIndex+1).equals(""))
        {
            return "";
        }
        java.util.Locale locale = null;
        User user = (User) request.getSession().getAttribute(request.getSession().getId());
        if(user == null)
        {
            ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
            // session 이 종료 되었을때 아무거나 message를 조회하여 struts 가 메세지리소스를 로딩하게 한다.
            locale = new Locale(configService.findLanguage());
        }
        else locale = getUser(request).getLocale();
        
        org.apache.struts.util.MessageResources messages = getResources(request);
        return messages.getMessage(locale, key);
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
            Object messageObj = request.getSession().getAttribute("COMMON_MESSAGES");
            if (messageObj != null) return;
        }
        
        DataBaseMessageResources dataBaseMessageResources = 
            (DataBaseMessageResources)getResources(request);
       
        // 현재 셋팅된 언어 
        String localeKey = "";
        if (getUser(request) == null)
        {
            //localeKey = getLocale(request).getLanguage();
            ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
            // session 이 종료 되었을때 아무거나 message를 조회하여 struts 가 메세지리소스를 로딩하게 한다.
            //dataBaseMessageResources.getMessage(locale, "index.mainTitle");
            dataBaseMessageResources.loadLocale(configService.findLanguage());
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
     * Set Page Title
     * @author  HN4741
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param currentPageId
     */
    public void setPageTitle(HttpServletRequest request, String currentPageId)
    {
        Hashtable pageHash = MwareConfig.getPagesTable();
        if(pageHash.containsKey(currentPageId))
        {
            List pageList = (ArrayList) pageHash.get(currentPageId); //페이지 정보 (pageId, filename...)
            for(Object pages:  pageList)
            {
                Map page = (Map)pages;

                //Set Page Title 
                request.setAttribute("PAGE_TITLE", getMessage(request, String.valueOf(page.get("PAGENAME"))));
            }
        }
    }

    public String setDecoBeanId(String initBeanId,HttpServletRequest request)
    {
    	List<Map> decoList = MwareConfig.getDecoMapList();
    	String compNo = getUser(request).getCompNo();
    	
    	for(Map decoMap : decoList)
    	{
    		logger.debug(decoMap.get("BEAN_NO")+"   "+decoMap.get("COMP_NO")+"  "+compNo);
    		if(initBeanId.equals(decoMap.get("BEAN_NO")) && compNo.equals(decoMap.get("COMP_NO")))
    		{
    			initBeanId = "null".equals(String.valueOf(decoMap.get("PACKAGE_NO")))?initBeanId:String.valueOf(decoMap.get("PACKAGE_NO"))+initBeanId;
    			break;
    		}
    	}
    	
    	logger.debug("Decorator Pattern Applied:"+initBeanId);
    	return initBeanId;
    }

    public int setHeader(HttpServletRequest request,  HttpServletResponse response, String listId, String currentPageId) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        List resultList = null;
        Map resultMap = null;
        
        resultList = configService.getUserHeader(CommonUtil.getUser(request), listId, currentPageId);

        String height = "";
        if(resultList != null && resultList.size() > 1)
        {
            height = String.valueOf(((Map)(resultList.get(resultList.size()-1))).get("HEIGHT"));
            
            if(CommonUtil.isNullCheck(height)) height = "0";
        }

        int heightInt = 0;
        if(height != "" && height != "null") heightInt = Math.round(Integer.parseInt(height)) * 30 + 30; //한줄에 30px. + footer 20px
        
        resultMap = CommonUtil.makeHeaderJson(resultList);
        String jsonString = CommonUtil.makeJsonString(resultMap);

        //logger.debug(jsonString);
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("header", jsonString);
        jsonObj.put("height", heightInt+"");
        
        response.getWriter().print(jsonObj.toString());
        
        return resultList.size();
    }
    
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
    
    public String saveErrorLog(Exception e, User loginUser, String url)
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        
        StringBuffer errBuf = new StringBuffer();
        for(StackTraceElement el : e.getStackTrace())
        {
            errBuf.append(el);
        }

        return configService.saveErrorLog(e, errBuf.toString(), loginUser, url);
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
            request.setAttribute("AJAX_STATUS", "VALID");
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
        request.setAttribute("AJAX_STATUS", "VALID");
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
    
    /**
     * AJAX 에러 상태를 셋팅한다. 
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param value
     */
    protected void setAjaxError(HttpServletRequest request, String errorLogId)
    {
        setAjaxStatus(request, "ERROR");
        request.setAttribute("AJAX_DESC", new String[]{errorLogId});
    }

    public void makeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount) throws IOException
    {
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
  
        result.put("view_count", totCount);
        result.put("total_count",totcnt);
        result.put("pos", firstRow);
        
        String jsonString = CommonUtil.makeJsonString(result);

       // logger.debug(jsonString);

        response.getWriter().print(jsonString);
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

        //logger.debug(jsonString);

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
     * @throws IOException
     */
    public void makeJsonResultWithHead(List resultList, HttpServletRequest request,  HttpServletResponse response, String listId) throws IOException
    {
        Map result = CommonUtil.makeHeaderJsonByList(resultList, request);
        
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(result);
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
        
        List result = CommonUtil.makeJsonNoId(resultList);
        
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

    /**
     * Grid Export
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param resultList
     * @param request
     * @param response
     * @param currentPageId
     * @throws IOException 
     */
    public void makeGridExport(List resultList, HttpServletRequest request,HttpServletResponse response,String listId, String currentPageId, String fileName) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        List headList = null;
        Map resultMap = null;
        
        headList = configService.getUserHeader(getUser(request), listId, currentPageId);

        if(headList.size() == 0){
            headList = CommonUtil.makeHeader(resultList);
        }
            
        // TODO Auto-generated method stub
        request.setAttribute("resultList", resultList);
        request.setAttribute("headList", headList);
        request.setAttribute("fileName", fileName);
        request.setAttribute("currentPageId", currentPageId);
        request.setAttribute("isTree", "N");
        request.setAttribute("user", getUser(request));
        
    }
    
    public void makeTreeGridExport(List resultList, HttpServletRequest request,HttpServletResponse response,String listId, String currentPageId, String fileName) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        List headList = null;
        Map resultMap = null;
        
        headList = configService.getUserHeader(getUser(request), listId, currentPageId);

        if(headList.size() == 0){
            headList = CommonUtil.makeHeader(resultList);
        }
            
        // TODO Auto-generated method stub
        request.setAttribute("resultList", resultList);
        request.setAttribute("headList", headList);
        request.setAttribute("fileName", fileName);
        request.setAttribute("currentPageId", currentPageId);
        request.setAttribute("isTree", "Y");
        request.setAttribute("user", getUser(request));
        
    }
    
    public void makeJsonListForReport(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount, String message) throws IOException
    {

        Map result = null;
        String isHeaderLoaded = (String)request.getParameter("isHeaderLoaded");
        String firstRow = (String)request.getParameter("firstRow");
        String totCount = (String)request.getParameter("isTotalCount");

        if("".equals(totalCount))totalCount = "0";
        
        if("null".equals(firstRow) || firstRow == null ) firstRow = "0";
        if("null".equals(totCount) || totCount == null || "0".equals(totCount)) totCount = totalCount;

        if(!"0".equals(firstRow))
            result = CommonUtil.makeResultJson(resultList); //아니면 그냥 
        else
            result = CommonUtil.makeHeaderJsonByListForRpt(resultList, request);
        
        result.put("message", message);

        String totcnt;
        if((Integer.parseInt(firstRow) + Integer.parseInt(MwareConfig.getGridMaxLoadCount())) < Integer.parseInt(totCount)) totcnt = (Integer.parseInt(firstRow) + Integer.parseInt(MwareConfig.getGridMaxLoadCount()))+"";
        else totcnt = totCount;
  
        result.put("view_count", totCount);
        result.put("total_count",totcnt);
        result.put("pos", firstRow);
        
        String jsonString = CommonUtil.makeJsonString(result);

        //logger.debug(jsonString);

        response.getWriter().print(jsonString);

    }
    
    public void updateAudit(String auditKey, Map auditMap, User user)
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        
        configService.saveAudit(auditKey, String.valueOf(auditMap.get("currentPageId")), auditMap, user);
    }
}
