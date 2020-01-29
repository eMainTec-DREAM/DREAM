package common.struts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.GaiaConfig;
import common.bean.MwareConfig;
import common.bean.User;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.util.CommonUtil;

import com.fins.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.module.sitemesh.RequestConstants;

/**
 * �� strutsAction �� ȣ���Ѵ�.
 * strutsAction �� ���� ��, �� �� ���� ���� ó���� ���ش�.
 * ������ ���� ���� ó�� ���� ó���Ѵ�.
 * @author  javaworker
 * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
 * @since   1.0
 *
 */
public abstract class GaiaAuthAction
        extends BaseAction
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(GaiaAuthAction.class);

    public GaiaAuthAction()
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
        beforeExecute(form, request);

        //===========Set Page Type =======================
        request.getSession().setAttribute("pageType", "gaia");
        //================================================
        
        // =======================================================
        // Session Check !!!
        if (getUser(request) == null)
        {   
            //========================================================================
            if ("true".equals(request.getParameter("isGridSearch")))
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
             * common.js �� ���� ��ũ��Ʈ �޼����� �����Ѵ�.
             * session ����Ǿ logout page �� ���°�� common.js �� �ٱ��� �޼��� �� �����Ͽ����Ѵ�.
             */
            getCommonMessages(request);
            request.getSession().setAttribute("gridException", "-9999");
            
            return mapping.findForward("gaiaLogoutPage");
        }
        
        // ���� �α� ������ �α� �������� session id�� üũ
        // ���� �α����� ���� session id�� ���� ��� �α��� ȭ���� �����ش�.
        if (!checkLoginUser(request))
        {
            //========================================================================
            if ("true".equals(request.getParameter("isGridSearch")))
            {
                // ���� �α�ƿ�
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
            
            // ���� �α�ƿ�
            request.getSession().setAttribute("gridException", "-8888");
            
            /*
             * common.js �� ���� ��ũ��Ʈ �޼����� �����Ѵ�.
             * session ����Ǿ logout page �� ���°�� common.js �� �ٱ��� �޼��� �� �����Ͽ����Ѵ�.
             */
            getCommonMessages(request);
            
            return mapping.findForward("gaiaLogoutPage");
        }
        // =======================================================
        
        //Action Script
        Map map = request.getParameterMap();
        if(map.containsKey("ACTION_FUNCTION")) request.setAttribute("ACTION_FUNCTION", map.get("ACTION_FUNCTION"));
        if(map.containsKey("IS_FROM_NOTICE")) request.setAttribute("IS_FROM_NOTICE", map.get("IS_FROM_NOTICE"));
        
        
        try 
        {
            // execute struts action
            returnMapping = run(mapping, form, request, response);
            request.setAttribute("FORM_NAME", mapping.getName());
            afterExecute();
 
            // �����Ǵ� �������� ������ �ִ��� üũ
           // if (checkAuthoPage(request, returnMapping.getPath())) return mapping.findForward("loginPage");
            
            //if ("Y".equals(MwareConfig.getIsUsePageAccessLogging())) saveAccessLog(request, returnMapping.getPath());
            
            // ======================================================
            // before fowading page, set page configuration
            setPageConfig(request, returnMapping.getPath());
            //=======================================================
            
            if (logger.isDebugEnabled()) logger.debug(returnMapping.toString());
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw e;
        }        
        return returnMapping;
    }

    /**
     * �޴� ���� Log�� ����Ѵ�.
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
     * �ش� �������� ������ �ִ��� üũ�Ѵ�.
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
        // ���� Page Id ��ȸ
        String currentPageId = CommonUtil.getPageIdFromPath(path);  //maEqMstrList

        // �ش� row map
        Map tempMap = null;

        Map superPageName = new HashMap();
        superPageName.put("JSON","jsonPage");
        superPageName.put("AJAX", "ajaxXmlVal");
        superPageName.put("REPORT", "pdfViewer");
        superPageName.put("RDREPORT", "report");
        superPageName.put("EXCEL", "gridExport");
        superPageName.put("INIT","index");
       // superPageName.put("INITPAGE", getUser(request).getFileName());

        if(currentPageId.indexOf("?") >= 0)
        {
            
        }
        //Menu Page Id�� ������ �ʰų� jsonPage Forwarding�� �ƴ� Tab Page ���� �̵��� ����
        else if(!superPageName.containsValue(currentPageId)) 
        {
            Hashtable pageHash = GaiaConfig.getPagesTable();   //��� ������ ��ȯ ('pmMstrList':�ܸ���Ʈ(ArrayList))
            List pageList= null;

            boolean isSec = false;

            return isSec;
        }
        
        return false;
    }

    /**
     * page�� ���ϵɶ� �ʿ��� value ���� ��ȸ, �����Ѵ�.
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
         * page config ó�� ���� �ʴ� ���  
         * grid�� ���� ��ȸ�ϴ� ��� : isGridSearch �Ķ���Ͱ� true
         * Tree ���� ó���ϴ� ���     : isTreeAction   �Ķ���Ͱ� true
         * Ajax Request �ϴ� ���   : isAjaxRequest  �Ķ���Ͱ� true
         */ /*
        if ("true".equals(request.getParameter("isGridSearch")) ||
            "true".equals(request.getParameter("isTreeAction"))   ||
            "true".equals(request.getParameter("isAjaxRequest")))
        
         * if ("/common/jsp/sheetCommonFind.jsp".equals(path) ||
         * "/common/jsp/sheetCommonSave.jsp".equals(path))
         */

        // Filter ���� ����
        if (request.getAttribute(RequestConstants.FILTER_APPLIED) != null)
        {
            Boolean filterApplid = (Boolean)request.getAttribute(RequestConstants.FILTER_APPLIED);
            
            // filter �� ����ȵǾ��ٸ� �ʿ� ����
            if (!filterApplid.booleanValue()) return;
        }
        else return;

        //=============================================================
        // ������� ���� Locale ���� : bean tab ����Ͽ� jsp���� ����ȭ�� �����
        //setLocale(request, getUser(request).getLocale());
        // session ������ ó�� locale �����Ѵ�.
        //=============================================================
        
      //=========================================================
        // ���� Page Id ��ȸ
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
        Hashtable tapPageHash = GaiaConfig.getTabPagesTable();
        if(tapPageHash.containsKey(currentPageId)){
        	request.setAttribute("tabPage", getTabPages(request,currentPageId,"TABPAGE"));
        }

        request.setAttribute("pageButton", getPageButtons(request, currentPageId));

        //========================================
        // ���� script ���� ��ȸ
        // common.js �� ���� ��ũ��Ʈ �޼����� �����Ѵ�.
        getCommonMessages(request);
        //========================================
    }

    private Object getPageButtons(HttpServletRequest request,  String currentPageId) throws Exception
    {
    	ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
    	//
        Hashtable buttonHash = GaiaConfig.getButtonTable();
        ArrayList buttonList = null;
        ArrayList rtnBtnList = null;
        
        if(buttonHash.containsKey(currentPageId))
        {
            buttonList = (ArrayList) buttonHash.get(currentPageId);
            rtnBtnList = new ArrayList();
            
            for(Object buttons :  buttonList)
            {
                Map button = (Map)buttons;
                
                //������ ���� äũ ���ϸ� �׳� �н�
                String chkAuth = String.valueOf(button.get("ISCHKAUTH"));
                if("N".equals(chkAuth) || "null".equals(chkAuth))
                {
                    rtnBtnList.add(buttons);
                    continue;
                }
                
                //��ư ���� äũ
                String chkAuthBtn = String.valueOf(button.get("ISCHKAUTHBTN"));
                if("N".equals(chkAuthBtn) || "null".equals(chkAuthBtn))
                {
                    rtnBtnList.add(buttons);
                    continue;
                }
                
                if(true)
                {
                    rtnBtnList.add(buttons);
                }
            }
        }

        return rtnBtnList;
    }

    /**
     * �ش� �������� tab ������ �����Ѵ�.
     * @author  javaworker
     * @version $Id: AuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param currentPageId
     * @param type (TABTITLE : �� Ÿ��Ʋ�����ö� , TABPAGE : �� �������� �����ö�)
     * @return
     * @throws Exception 
     */
    private Object getTabPages(HttpServletRequest request, String currentPageId, String type) throws Exception
    {
        String [] tabInfos = null;

        Hashtable tapPageHash = GaiaConfig.getTabPagesTable();   //��� �� ������ ��ȯ ('pmMstrList':�ܸ���Ʈ)
        List tabPageList= null;
        List rtnPageList= null; 
        
        if(tapPageHash.containsKey(currentPageId))
        {
            tabPageList = (ArrayList)tapPageHash.get(currentPageId); //���ݱ��� �� �޴��� TabPage�� ���������� ���� ������������ ����

            rtnPageList = new ArrayList();
            
            for(Object tabs :  tabPageList)
            {
                Map tab = (Map)tabs;
                
                //���� äũ ���ϸ� �׳� �н�
                String chkAuth = String.valueOf(tab.get("ISCHKAUTH"));
                if("N".equals(chkAuth) || "null".equals(chkAuth))
                {
                    rtnPageList.add(tabs);
                    continue;
                }

                if(true)
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
//                    // security�� ��ϵǾ� �ִٸ� flag�� true�� �ٲ㼭 ������� �ʰ� �Ѵ�.
//                    if (tempTabPages[i].equals("TAB." + tempSecurityObjectId))
//                    {
//                        securityFlag = true;
//                        break; // exit for(j)
//                    }
//                }
//                
//
//                // security ������ ���� ��츸 �����Ѵ�.
//                if (!securityFlag)
//                {
//                	if("TABTITLE".equals(type)) tabArrayList.add(tempTabTitles[i]);
//                	else if("TABPAGE".equals(type)) tabArrayList.add(tempTabPages[i]);
//                }
//            }
//            
//            //==========================================================
//            /*
//             * �ش� ���������� ���õǾ� �ִ� �ش� ������ �� tab ���� security�� ������
//             * tab ������ ������ arrayList�� �ٽ� String [] �� ĳ���� �Ͽ� 
//             * �����Ѵ�.
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
     * ���� �α� ������ �α� �������� session id�� üũ
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
         * UserService ��ü����
         * loginUsers ��� hashtable�� ������  session id��  �˻��� �Ͽ�
         * �ش� session id Ű ���� �ִ����� üũ �Ѵ�. 
         * �ش� Ű ����  ������ true�� �����ϰ�  
         * �ش� Ű ���� ������  false�� ������ �Ѵ�. 
         */
        String sessionId = request.getSession().getId(); //���� session id ���� ���´�.
        Hashtable loginUsers = BaseAction.getLoginUsers(); 

        // ���� session ID�� ����� User ��ü�� �ִ��� üũ�Ѵ�.
        Object loginUser = loginUsers.get(sessionId); 

        if (loginUser != null)
        {
            result = true;
            
            if(!"gaia".equals(((User)loginUser).getPageType())) result = false;
        }
        
        return result;
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
        
        java.util.Locale locale = getUser(request).getLocale();
        org.apache.struts.util.MessageResources messages = getResources(request);
        return messages.getMessage(locale, key);
    }
    
    /**
     * ������ : ������ ��� Ŭ������ �� �޼ҵ带 �������̵� �Ͽ� �װ��� ������ �����Ѵ�. <br>
     * ���� : <br>
     * ���� ���� : <br>
     * �ۼ� ��¥ : (05-03-31 ���� 11:13:24)
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
    
    /**
     * buttonHash ���� PageKey ������ ����Ǿ��ִ� 
     * String [] �� button id ������ Hashtable�� Key�� buttion name�� value
     * �� �ٽ� buttonHash���� pageKey ������ �����Ѵ�.
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
        // �ٱ��� �� ó�� �ϱ� ���� message ��ü
        java.util.Locale locale = getUser(request).getLocale();
        org.apache.struts.util.MessageResources messages = getResources(request);
        //=========================================================================

        String userGroup = getUser(request).getUserGroup();
        Hashtable securityTable = GaiaConfig.getSecurityTable();        
        
        Hashtable tmepHash = new Hashtable();
        
        for (int i=0, length=pageButtons.length; i<length; i++)
        {
            String tempButtonId = pageButtons[i];

            // ===================================================================
            // T4SECURITY ���� ���� ���� �� üũ�Ѵ�.
            String securityKey = userGroup + ".BUTTON." + currentPageId;
            String [] securityObjectId = (String []) securityTable.get(securityKey);
            
            boolean securityFlag = false;
            for (int j=0; securityObjectId != null && j<securityObjectId.length; j++)
            {
                String tempSecurityObjectId = securityObjectId[j];
                
                // security�� ��ϵǾ� �ִٸ�  ������� �ʰ� �Ѵ�.
                if (tempButtonId.equals(tempSecurityObjectId))
                {
                    securityFlag = true;
                }
            }
            
            if (securityFlag)
            {
                // security �� ��ϵ� ���̶�� ó�� ���� �ʰ� �Ѵ�.
                continue;
            }
            
            //====================================================================
            
            try
            {
                // button id Ű ������ button name�� �����Ѵ�.
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
    
    public int setHeader(HttpServletRequest request,  HttpServletResponse response, String listId, String currentPageId) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        List resultList = null;
        Map resultMap = null;
        
        resultList = configService.getGaiaUserHeader(getUser(request), listId, currentPageId);

        String height = "";
        if(resultList != null && resultList.size() > 1)
        {
            height = String.valueOf(((Map)(resultList.get(resultList.size()-1))).get("HEIGHT"));
        }

        int heightInt = 0;
        if(height != "" && height != "null") heightInt = Math.round(Integer.parseInt(height)) * 30 + 30; //���ٿ� 30px. + footer 20px
        
        resultMap = CommonUtil.makeHeaderJson(resultList);

       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //�����ڵ� ó��
        
        JSONObject jsonObj = new JSONObject();
        
        String jsonString = gson.toJson(resultMap).toString(); 
        jsonObj.put("header", jsonString);
        jsonObj.put("height", heightInt+"");
        
        response.getWriter().print(jsonObj.toString());
        
        return resultList.size();
    }

    public void makeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount) throws IOException
    {
        Map result = null;
        String isHeaderLoaded = (String)request.getParameter("isHeaderLoaded");
        String firstRow = (String)request.getParameter("firstRow");

        if("N".equals(isHeaderLoaded))
            result = CommonUtil.makeHeaderJsonByList(resultList, request); //�ش��� ������ �ش��� List���� ����� �Ѹ���.
        else
            result = CommonUtil.makeResultJson(resultList); //�ƴϸ� �׳� 
        
        Gson gson = new Gson();
        
        result.put("total_count", totalCount);
        result.put("pos", firstRow);

        String jsonString = gson.toJson(result);
        jsonString = jsonString.replaceAll("\"ID\"", "id");

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

        response.getWriter().print(jsonString);

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
        // TODO Auto-generated method stub
        request.setAttribute("resultList", resultList);
        request.setAttribute("headList", headList);
        request.setAttribute("fileName", fileName);
    }
}
