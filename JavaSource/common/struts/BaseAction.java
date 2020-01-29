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
 * �ֻ��� Action
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
    // ���� grid xml �������� ��ü�� ���� �ϱ� ���� Attribute
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
     * ����� User ��ü�� �����ص� Hashtable ���� 
     */
    private static Hashtable loginUsers = new Hashtable();
    
    /** config loading */
    private boolean configIsLoading = false;

    /**
     * Spring Service Bean ����
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
     * Decorator Pattern ����
     * @param name
     * @param request
     * @return
     */
    public Object getBean(String name, HttpServletRequest request)
    {
    	return getBean(setDecoBeanId(name, request));
    }

    /**
     * ���� �α��� ���� ����
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
            String language = "en"; //�⺻ ������ ���� ?!!!?!!?!?!?
        	Locale locale = new Locale(language);
        	user = new User();
        	user.setLangId(language);
            user.setLocale(locale);
//            user.setCompNo("100");
        }
        return user;*/
    }

    /**
     * struts validation�� ���
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
     * staruts action ���� �� ȣ��
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
                
                //3900����Ʈ�� ���ڿ� �ڸ���
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
        // parameter�� pageMessage�� ���۵Ǿ��ٸ� Attribute�� �����Ѵ�.
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
        // �ѹ� ���� �Ͽ��ٸ� �ٽ� ȣ����� �ʰ� �Ѵ�.
        configIsLoading = true;
        //===================================
        
        return auditMap;
    }
    
    /**
     * staruts action ���� �� ȣ��
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
     * ������ PAGE �� ������ Message ����
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
         * key ���� ��ȿ���� üũ�Ѵ�. ex)MENU_ID.keyId
         * . �� ���ų� . �� �յڿ� key Id �� ���ٸ� "" �� �����Ѵ�.
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
            // session �� ���� �Ǿ����� �ƹ��ų� message�� ��ȸ�Ͽ� struts �� �޼������ҽ��� �ε��ϰ� �Ѵ�.
            locale = new Locale(configService.findLanguage());
        }
        else locale = getUser(request).getLocale();
        
        org.apache.struts.util.MessageResources messages = getResources(request);
        return messages.getMessage(locale, key);
    }
    
    /**
     * ���� �޼����� �����Ͽ� request�� �����Ѵ�.
     * commonInclude.jsp���� �̰��� 
     * ��ũ��Ʈ �� ���� ���� ��ũ��Ʈ���� Ű������ ��밡���ϰ� ���� �Ѵ�.
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
     * ���� �޼����� �����Ͽ� request�� �����Ѵ�.
     * commonInclude.jsp���� �̰��� 
     * ��ũ��Ʈ �� ���� ���� ��ũ��Ʈ���� Ű������ ��밡���ϰ� ���� �Ѵ�.
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
            // reload �ƴϴ��� message�� ������ �ȵǾ� �ִٸ� ��ȸ �ǰ� �Ѵ�.
            Object messageObj = request.getSession().getAttribute("COMMON_MESSAGES");
            if (messageObj != null) return;
        }
        
        DataBaseMessageResources dataBaseMessageResources = 
            (DataBaseMessageResources)getResources(request);
       
        // ���� ���õ� ��� 
        String localeKey = "";
        if (getUser(request) == null)
        {
            //localeKey = getLocale(request).getLanguage();
            ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
            // session �� ���� �Ǿ����� �ƹ��ų� message�� ��ȸ�Ͽ� struts �� �޼������ҽ��� �ε��ϰ� �Ѵ�.
            //dataBaseMessageResources.getMessage(locale, "index.mainTitle");
            dataBaseMessageResources.loadLocale(configService.findLanguage());
        }
        else
        {
            localeKey = getUser(request).getLocale().getLanguage();
        }
        
        // �ε��� ��� ����
        HashMap hashMap = dataBaseMessageResources.getMessages();
        
        // ������ Key
        Object [] keyArray = hashMap.keySet().toArray();
        
        // ������ ���� �޼���
        ArrayList commMessages = new ArrayList();
        
        for (int i=0; i<keyArray.length; i++)
        {
            String tempKey = (String)keyArray[i];
            
            // key �� [COMMON.CMSG] �� �����ϴ� KEY ������ �����Ѵ�.
            if (tempKey.indexOf(localeKey + ".COMMON.") != -1 || tempKey.indexOf(localeKey + ".GRID.") != -1)
            {
                String [] tempCommonArray = new String[2];
                
                tempCommonArray[0] = tempKey.replaceFirst(localeKey+".", ""); // ���� ��� ������ Ű������ ����.
                tempCommonArray[1] = (String)hashMap.get(tempKey);
                
                // �޼��� Ű�� ������ �����Ѵ�.
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
            List pageList = (ArrayList) pageHash.get(currentPageId); //������ ���� (pageId, filename...)
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
        if(height != "" && height != "null") heightInt = Math.round(Integer.parseInt(height)) * 30 + 30; //���ٿ� 30px. + footer 20px
        
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
     * ����,���� �������� ���� Key�� �����Ѵ�.
     * List Key�� �����Ѵ�.
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
         * Search : ���� ��ȸ 
         * Next   : ���� ������ ��ȸ
         * Pre    : ���� ������ ��ȸ
         */ 
        String sheetAction = request.getParameter("sheetActionName");

        /*
         * LIST_KEY�� popup[���޴�] ���� ����� ����,���� KEY���� �����ϴ� ��� �浹�� �Ͼ��.
         * �׷��� LIST_KEY�� POPUP �ΰ��� �ƴ� ���� �����Ѵ�. 
         */
        String listKeyName = "LIST_KEY";
        if ("popupPage".equals(request.getParameter("isDecoratorName")))
        {
            listKeyName = "POPUP_LIST_KEY";
        }
        
        String [] prevKey = (String [])request.getSession().getAttribute(listKeyName);
        
        // ù��° page �̰ų� ��ȸ�� ���� Key �� ���ٸ� ���� key�� �����Ѵ�.
        if (("Search".equals(sheetAction) && pageNum == 1) || prevKey == null)
        {
            request.getSession().setAttribute(listKeyName, listKey);
        }
        else 
        {
            String [] tempKey = null;
            
            // ���� ������ ��ȸ �̹Ƿ� prevKey + listKey �� �Ѵ�.
            if ("Next".equals(sheetAction))
            {
                tempKey = CommonUtil.addTwoStringArray(prevKey, listKey);  
                request.getSession().setAttribute(listKeyName, tempKey);
            }
        }
                
        return false;
    }
    
    /**
     * request �� ����� list key ���� ���� Ű ��ġ�� ����Ͽ� page num�� ��� �����Ѵ�.
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
         * LIST_KEY�� popup[���޴�] ���� ����� ����,���� KEY���� �����ϴ� ��� �浹�� �Ͼ��.
         * �׷��� LIST_KEY�� POPUP �ΰ��� �ƴ� ���� �����Ѵ�. 
         */
        String listKeyName = "LIST_KEY";
        if ("popupPage".equals(request.getParameter("isDecoratorName")))
        {
            listKeyName = "POPUP_LIST_KEY";
        }
        
        // ���ǿ� ����� Ű������ ���� �´�.
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
             * ��� �� ������ number ������ ������ Key�� ��� �����Ѵ�.
             * 1, 2, 3 ��ȸ�� 2������ ���� ������ �󼼿� ���ٰ� �ٽ� ������� ���� 2�������� ��ȸ�Ǵ´�.
             * �̶� ���� ������ ��ȸ�� �Ǹ鼭 �ٽ� 3�������� list_key �� ���õǱ� �����̴�. 
             */
            request.getSession().setAttribute(listKeyName, resetListArray(listKey, page*100));
        }
        
        return page;
    }
    
    /**
     * listKey[] �� �迭�� offset ��ŭ�� �迭�� �����Ѵ�.
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
     *  ����, ���� Key�� �����Ͽ� �����Ѵ�.
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
         * LIST_KEY�� popup[���޴�] ���� ����� ����,���� KEY���� �����ϴ� ��� �浹�� �Ͼ��.
         * �׷��� LIST_KEY�� POPUP �ΰ��� �ƴ� ���� �����Ѵ�. 
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
     * totalCount �� 0 �̶�� ��ȸ���� �ʰ�,
     * sheet ������ ������.
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
        
        // ��ȸ�� List �� form�� �����Ѵ�.
        request.setAttribute(FIND_XML_ATTRIBUTE, new LinkedList());
        
        // ó�� ��ȸ�Ҷ� ������ �ִ� total count�� ��� ������ �ִ´�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * values�� ���� id, desc �� �����Ѵ�. values[0]:ID, values[1]:DESC
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
            ids[i] = values[i][0];      // CODE ����
            descs[i] = values[i][1];    // DESC ����
        }
        setAjaxId(request, ids);
        setAjaxDesc(request, descs);
    }
    
    
    /**
     * ���� data(���߹迭)�� ��迭 ó�� �� �����Ѵ�.
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
        
        //���� �迭�� ��,���� ��,������ ��迭
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
     * XML ���·� ��������� Ajax���� �Ľ� ������ ������ �����Ѵ�.
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
     * AJAX ó�� ���¸� �����Ѵ�. 
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
     * AJAX ó�� ���¸� �����Ѵ�. 
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
     * AJAX ���� ���¸� �����Ѵ�. 
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
        
        result = CommonUtil.makeResultJson(resultList); //�ƴϸ� �׳� 

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

        result = CommonUtil.makeHeaderJsonByListAtoCmp(resultList); //�ش��� ������ �ش��� List���� ����� �Ѹ���.
        
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
            result = CommonUtil.makeResultJson(resultList); //�ƴϸ� �׳� 
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
