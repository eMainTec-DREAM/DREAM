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
 * �� strutsAction �� ȣ���Ѵ�.
 * strutsAction �� ���� ��, �� �� ���� ���� ó���� ���ش�.
 * ������ ���� ���� ó�� ���� ó���Ѵ�.
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
             * common.js �� ���� ��ũ��Ʈ �޼����� �����Ѵ�.
             * session ����Ǿ logout page �� ���°�� common.js �� �ٱ��� �޼��� �� �����Ͽ����Ѵ�.
             */
            getCommonMessages(request);
            request.getSession().setAttribute("gridException", "-9999");
            
            return mapping.findForward("logoutPage");
        }
        
        // ���� �α� ������ �α� �������� session id�� üũ
        // ���� �α����� ���� session id�� ���� ��� �α��� ȭ���� �����ش�.
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
            
            //ȭ�� ���� ����
            String rtnAuthCode = "OK";
            String rtnAuthMsg  = "";
            if(checkAuthoPage(request, returnMapping.getPath())) //���� ������ �Ǵ� �������� ������ �����ϴ�.
            {   
                String currentPageId = CommonUtil.getPageIdFromPath(returnMapping.getPath());
                Hashtable pageHash = MwareConfig.getPagesTable();   //��� ������ ��ȯ ('pmMstrList':�ܸ���Ʈ(ArrayList))
                //���� ������ �Ǵ� �������� ����ϴ�(�����ϴ�) �������Դϱ�?
                if(pageHash.containsKey(currentPageId)) rtnAuthMsg = getMessage(request,"MESSAGE.MGS0241"); //���������� ���� ������ �����ϴ�.
                else rtnAuthMsg = getMessage(request,"MESSAGE.MSG020");  //�������� �����ϴ�. �����ڿ��� �����ϼ���.   
                //������ ����!
                rtnAuthCode = "NO"; 
            }
            request.setAttribute("AUTH", rtnAuthCode); //������ �ִ�:OK, ������ ����:NO
            request.setAttribute("AUTHMSG",rtnAuthMsg); //���������� ���� ������ �����ϴ�. , �������� �����ϴ�. �����ڿ��� �����ϼ���. 
            
            //set Page Title with fileName
            super.setPageTitle(request, CommonUtil.getPageIdFromPath(returnMapping.getPath()));
            
            afterExecute();

            // �����Ǵ� �������� ������ �ִ��� üũ
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
        superPageName.put("UBIREPORT", "ubiReport");
        superPageName.put("EXCEL", "gridExport");
        superPageName.put("INIT","index");
       // superPageName.put("INITPAGE", getUser(request).getFileName());

        if(currentPageId.indexOf("?") >= 0)
        {
            
        }
        //Menu Page Id�� ������ �ʰų� jsonPage Forwarding�� �ƴ� Tab Page ���� �̵��� ����
        else if(!superPageName.containsValue(currentPageId)) 
        {
            String[] secPages = getSecObject(request, "PAGE");  //papage_id
            Hashtable pageHash = MwareConfig.getPagesTable();   //��� ������ ��ȯ ('pmMstrList':�ܸ���Ʈ(ArrayList))
            List pageList= null;

            boolean isSec = true;
            if(pageHash.containsKey(currentPageId))
            {
                pageList = (ArrayList) pageHash.get(currentPageId); //������ ���� (pageId, filename...)
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
                            //���� �������� Security Tab�� ������ ���� �������� ok
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
        // Filter ���� ����
        if (request.getAttribute(RequestConstants.FILTER_APPLIED) != null)
        {
            Boolean filterApplid = (Boolean)request.getAttribute(RequestConstants.FILTER_APPLIED);
            
            // filter �� ����ȵǾ��ٸ� �ʿ� ����
            if (!filterApplid.booleanValue()) return;
        }
        else return;

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
        Hashtable tapPageHash = MwareConfig.getTabPagesTable();
        if(tapPageHash.containsKey(currentPageId)) request.setAttribute("tabPage", getTabPages(request,currentPageId,"TABPAGE"));

        request.setAttribute("pageButton", getPageButtons(request, currentPageId));
        
        request.setAttribute("pageLinks", getPageLinks(request, currentPageId));

        //========================================
        // ���� script ���� ��ȸ
        // common.js �� ���� ��ũ��Ʈ �޼����� �����Ѵ�.
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
        
        String[] secTabs = getSecObject(request, "TAB");  //pgBtnId
        Hashtable tapPageHash = MwareConfig.getTabPagesTable();   //��� �� ������ ��ȯ ('pmMstrList':�ܸ���Ʈ)
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
     * page �� ��ϵ� security button�� �迭�� �����Ѵ�.
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
//            if(!"dream".equals(((User)loginUser).getPageType())) result = false;
        }
        
        return result;
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
        Hashtable securityTable = MwareConfig.getSecurityTable();        
        
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
}
