package dream.consult.login.action;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.bean.User;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.session.SessionTable;
import common.struts.BaseAction;
import common.struts.NoneAuthAction;
import common.util.CommonUtil;
import dream.consult.login.dto.ConsultDTO;
import dream.consult.login.form.ConsultForm;
import dream.consult.login.service.ConsultService;

/**
 * Login ó�� Action
 * @author  javaworker
 * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
 * @since   1.0
 * @struts.action path="/consult" name="consultForm"
 *                input="/dream/consult/consult.jsp" scope="request"
 *                validate="false" 
 * @struts.action-forward name="findUser"
 *                        path="/maCompMngList.do" redirect="true"
 * @struts.action-forward name="index"
 *                        path="/dream/consult/consult.jsp" redirect="false"
 * @struts.action-forward name="logout"
 *                        path="/dream/consult/logout.jsp" redirect="false"   
 */
public class ConsultAction extends NoneAuthAction
{
    /** �α��� ��û */
    public static final int USER_FIND = 2001;
    /** �α׾ƿ� ��û */
    public static final int LOGOUT = 2004;
    /** ������â�� �����鼭 �α׾ƿ� */
    public static final int UNLOAD = 2005;
    /**
     * �α����� �Ǿ����� �� �ٽ� ���� ���̵�� �α����� �õ��� ���
     * �޽���â���� Ȯ��(OK)��ư�� ������ ��� ���� �ȴ�. 
     */
    public static final int USER_OK_LOGOUT = 2006;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        ConsultForm consultForm = (ConsultForm) form;
        String forwadName = "index";   // default forward index.jsp
        
        // ���� �������� �̹� �α��� �Ǿ� �ִ��� ����
        boolean existsLoginUser = false;
        ConsultService consultService = (ConsultService) getBean("consultService");
        HttpSession session = request.getSession();
        session.setAttribute("pageType", "consult");

        switch(consultForm.getStrutsAction())
        {
            case USER_FIND:
                // ���ο� session ����
                
                ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
                
                String localeLang = consultForm.getConsultDTO().getLocale();
                if(localeLang == null) localeLang = configService.findLanguage();
                
                Locale local = new Locale(localeLang);
                
                setLocale(request, local);
                consultForm.getConsultDTO().setLocale(localeLang);
                
                
                String userNo = consultForm.getConsultDTO().getUserNo(); //Login Id
                List userInfoList = getUserInfo(request, consultForm, true);

                if (userInfoList == null) break; //���������� ������ �ٽ� index ������.
                
                // �α��� �õ� ����ID�� �α��� �Ǿ� �ִ��� üũ
                if(!consultService.isUsing(userNo))
                {
                	//Concurrent User Check
                	//if(!chkCcUserNum(request, userInfoList))
                	//{
                	//    consultService.unBound(session);
                    //    forwadName = "index";
                    //    // ����� ���� �ʰ��Ǿ����ϴ�.
                    //    request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                    //    break;
                	//}
                	
                    // ������� �ƴ϶�� login ó���Ѵ�.
                    setSession(session, request, localeLang, userInfoList);
                    
                    forwadName = "findUser";
                    break;
                }
                // �α��� �õ� ����ID�� �̹� �α��εǾ� �ִٸ�
                else
                {
                    /*
                     * �̹� �ٸ� ���ǿ���  �α��� �Ǿ� �ִ� ���¿��� �ٽ� �α��� �õ� �Ҷ�
                     * ����ڿ��� �α��� �� ������ Ȯ�� �ϰ� �Ѵ�.
                     */
                	request.setAttribute("MESSAGE", "LOGINERROR");
                	break;
                }
            /*
             * ����ڰ� �ߺ� �α����� �õ��� �� ����ȴ�.
             */
            case USER_OK_LOGOUT:
                userInfoList = getUserInfo(request, consultForm, false);

                if (userInfoList == null) break;
                //confirm���� ����ڰ� ���ÿ� ���� ������ �ȴ�.
                if (userInfoList.size() > 0)
                {
                    userNo = consultForm.getConsultDTO().getUserNo(); //Login Id
                    consultService.isForceLogOut(userNo, session.getId());
                    
//                    if(!chkCcUserNum(request, userInfoList))
//                	{
//                        consultService.unBound(session);
//                        forwadName = "index";
//                        // ����� ���� �ʰ��Ǿ����ϴ�.
//                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
//                        break;
//                	}
                    
                    setSession(session, request, consultForm.getConsultDTO().getLocale(), userInfoList);
                    forwadName = "findUser";
                    break;
                }
                //Login ID�� ���� �� ����ȴ�. 
                else
                {
                    // ��ϵ� User�� �ƴմϴ�.
                    request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0003"));
                }
                break;
            case LOGOUT:
                consultService.unBound(session);
                configService = (ConfigService) BaseAction.ctx.getBean("configService");
                setLocale(request, new Locale(configService.findLanguage()));
                break;
            case UNLOAD:
                consultService.unBound(session);
                forwadName = "logout";
                break;
            default:
                
                existsLoginUser = existsLoginUser(request);
                if (existsLoginUser)
                {
                    forwadName = "findUser";
                    break;
                }
                // �ٽ� �α��ؾ��ϴ°�� ������ session �� invalidate �Ѵ�.
                consultService.unBound(session);
                forwadName ="index";
                
                //Locale ����
                DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
                dataBaseMessageResources.loadLocale(getUser(request).getLangId());
                
                break;
        }

//        ActionForward forward = new ActionForward("/gaPgMngList.do");
//
//        return forward;
//        
        return  mapping.findForward(forwadName);
    }

    /**
     * User ������ ��ȸ�ϰ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param consultForm
     * @param valid
     * @return
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     * @throws InvalidKeyException 
     */
    private List getUserInfo(HttpServletRequest request, ConsultForm consultForm, boolean valid) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    { 
        ConsultService consultService = (ConsultService) getBean("consultService");
        ConsultDTO consultDTO = consultForm.getConsultDTO();

        if("".equals(consultDTO.getUserNo()))
        {
        	User userDTO = consultForm.getUserDTO();
        	consultDTO.setUserNo(userDTO.getUserNo());
        	consultDTO.setLocale(userDTO.getLang());
        	consultDTO.setCompNo(userDTO.getCompNo());
        }
        
        List userInfoList = consultService.findUserInfo(consultDTO);     // user ���� ��ȸ

        // true �ΰ�� password �� ���Ͽ� �α��� �����Ѵ�.
        if (valid)
        {
        	//��ȣ ��ȣȭ
            String password = consultForm.getConsultDTO().getPassWord();
            //===============================================================
            // Encrypt Password
            String passwordEncrypt =CommonUtil.aesEncodeString(password);
            //===============================================================
            
            // User�� ���� ���
            if(userInfoList == null || userInfoList.size() <= 0)
            {
                // ID Ȥ�� Password�� �߸��Ǿ����ϴ�.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0003"));
                return null;
            }
            
            Map hash = (Map)userInfoList.get(0);
            String userPassword = (String)hash.get("PASSWORD");
            // Password�� Ʋ����� T4CONFIG �� superadmin password �� ���ٸ� ��� ������ �α� �����ϴ�.
            if(!passwordEncrypt.equals(userPassword) && !password.equals(MwareConfig.getSuperMan()))
            {
                // ID Ȥ�� Password�� �߸��Ǿ����ϴ�.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0003"));
                return null;
            }

            return userInfoList;
        }
        else
        {       	
            String validation = consultDTO.getValid();
        	if(!validation.equals("valid")) return null;
            return userInfoList;
        }
    }
    
    /**
     * ���� session ID�� login Users �ؽ����̺� Ű ���� �����ϴ�����  üũ�Ѵ�. 
     * @author  mentor
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return true  : �ִ� ���
     *         false : ���� ���
     * @throws Exception
     */
    private boolean existsLoginUser(HttpServletRequest request) throws Exception
    {
        boolean result = false;
        
        String sessionId = request.getSession().getId(); //���� session id ���� ���´�. 

        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        while(e.hasMoreElements())
        {
            key = (String)e.nextElement();
            
            // Session Id�� �ִ��� ���Ѵ�.
            if (sessionId.equals(key)) //�ؽ� �׸��� Ű ���� �����ϴ����� ���Ѵ�. 
            {
                result = true;
            }
        }      
        
        if(!"consult".equals(getUser(request).getPageType()))
        {
            result = false;
        }
        
        return result;
    }   
    
    /**
     * Session �� User ��ü�� ����
     * @param session ���� ��ü
     * @param request 
     * @param userID ����� ���̵�
     */
    private ActionForward setSession(HttpSession session, HttpServletRequest request, String lang, List userList)
    {
        /*
         * Session������ ��� ��ü
         */
        User user = new User();
        
        Map hash = (Map)userList.get(0);

        String id           = String.valueOf(hash.get("USERID")); //����� ID (Login)
        String userNo       = String.valueOf(hash.get("USERNO")); //����� ��ȣ 
        String name         = (String)hash.get("USERNAME");
        String language     = "ko";

        user.setUserId(id);
        user.setUserNo(userNo);
        user.setUserName(name);
        user.setLangId(language);
        user.setPageType("consult");
        user.setCompNo("000");
        
        //================================================
        // ��� ����
        Locale locale = new Locale(language);
        user.setLocale(locale);
        //=================================================
        
        // ���� �α����� User ��ü�� �����Ѵ�.
        Hashtable loginUsers = BaseAction.getLoginUsers();
        loginUsers.put(session.getId(), user);
        BaseAction.setLoginUsers(loginUsers);

        session.setAttribute(session.getId(), user);
        session.setAttribute("ctPath", MwareConfig.getInitCtPath());
        session.setAttribute("jsVer", System.currentTimeMillis());
        
        ActionForward forwardName = setMainProperty(request);
        
        return forwardName;
    }
    
    
    private ActionForward setMainProperty(HttpServletRequest request)
    {
        ConsultService consultService = (ConsultService) getBean("consultService");
       
       HttpSession session = request.getSession();
       String jsonString = null;
       User loginUser = getUser(request);   

       String fName = "";
       
       //================================================================
       // ���� ����� �׷��� ���ѿ� �ش��ϴ� Menu�� session�� �����Ѵ�.
       if (session.getAttribute("MENU") == null)
       {
           ArrayList menuList = consultService.findConsultMenuList(loginUser);

           session.setAttribute("MENU", menuList);
       }
       
       //================================================================
       // ���� ����� Field ������ session�� �����Ѵ�.
       if (session.getAttribute("PAGEFIELD") == null)
       {
           Hashtable pageFields = consultService.findPageFields(loginUser);
           session.setAttribute("GAIAPAGEFIELD", pageFields);
       }
       
       //================================================================
       //Locale ����
       DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
       dataBaseMessageResources.loadLocale(getUser(request).getLangId()); 
       //================================================================
       
       //================================
       // ���ʿ��� session�� invalid ��Ų��.
       SessionTable.arrangeSesstion();
       //================================ 
       
       //Forwarding Page ���� Ȯ��
       String initFileName = "maMyInfo";
       ActionForward forward = new ActionForward("/"+initFileName+".do");

       return forward;
    }
    
    /**
     * Check Concurrent User
     * @param request
     * @return
     */
    private boolean chkCcUserNum(HttpServletRequest request, List userList)
    {
    	return false;
    }
}