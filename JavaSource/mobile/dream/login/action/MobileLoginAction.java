package mobile.dream.login.action;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
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

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.MwareConfig;
import common.bean.User;
import common.config.service.ConfigService;
import common.struts.BaseAction;
import common.struts.NoneAuthAction;
import common.util.CommonUtil;
import dream.mgr.usrgrp.service.MaUsrGrpAuthListService;
import mobile.dream.login.dto.MobileLoginDTO;
import mobile.dream.login.form.MobileLoginForm;
import mobile.dream.login.service.MobileLoginService;

/**
 * MobileLogin ó�� Action
 * @author  javaworker
 * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
 * @since   1.0
 * @struts.action path="/mobile" name="mobileLoginForm"
 *                input="/mobile/dream/mobile.jsp" scope="request"
 *                validate="false" 
 * @struts.action-forward name="mobileMain"
 *                        path="/mobileMain.do" redirect="true"
 * @struts.action-forward name="mobileIndex"
 *                        path="/mobile/dream/mobile.jsp" redirect="false"   
 */
public class MobileLoginAction
        extends NoneAuthAction
{
    /** �α��� ��û */
    public static final int USER_FIND = 2001;
    /** �α׾ƿ� ��û */
    public static final int LOGOUT = 2004;
    /** ������â�� �����鼭 �α׾ƿ� */
    public static final int UNLOAD = 2005;
    /** ������ ��ġ�� �ʰ� �α� �Ҷ� ���ȴ�.(id, password �Է����� �ʰ� ��ũ�� �α�) */
    public static final int NOT_LOGIN_USER = 9554;
    /** �α� ���� �ʰ� ����� ȣ���Ѵ�. */
    public static final int CALL_SERVICE = 3002;
    
    public static final int MOBILE_INIT = 3006;
    
    public static final int MOBILE_LOGIN = 3005;
    /**
     * �α����� �Ǿ����� �� �ٽ� ���� ���̵�� �α����� �õ��� ���
     * �޽���â���� Ȯ��(OK)��ư�� ������ ��� ���� �ȴ�. 
     */
    public static final int USER_OK_LOGOUT = 2006;
    /** �α��� ���� �ʰ� �ش� �������� foward */
    private static final int CALL_PAGE = 3003;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        // ���ο� session ����
        HttpSession session = request.getSession();
        MobileLoginForm mobileLoginForm = (MobileLoginForm) form;
        
        MobileLoginService mobileLoginService = (MobileLoginService) getBean("mobileLoginService");
        String forwadName = "mobileIndex";   // default forward index.jsp

        String localeLang = mobileLoginForm.getMobileLoginDTO().getLocale();
        
        
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        if(localeLang == null) localeLang = configService.findLanguage();

        setLocale(request, new Locale(localeLang));
        mobileLoginForm.getMobileLoginDTO().setLocale(localeLang);
        
    	// ���� �������� �̹� �α��� �Ǿ� �ִ��� ����
    	boolean existsMobileLoginUser = false;

        List userInfoList = null;
        String userNo = mobileLoginForm.getMobileLoginDTO().getUserNo(); //MobileLogin Id
        session.setAttribute("pageType", "mobile");
        
        switch(mobileLoginForm.getStrutsAction())
        {
        	case MobileLoginAction.MOBILE_LOGIN :
        		
        		// �α��� ������ ���������� ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, mobileLoginForm, true);
                if (userInfoList == null)
                {
                	forwadName = "mobileIndex";
                	break;
                }
                
                // �α��� �õ� ����ID�� �α��� �Ǿ� �ִ��� üũ
                if(!mobileLoginService.isUsing(userNo))
                {
                	//
                	if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // ����� ���� �ʰ��Ǿ����ϴ�.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                	
                    // ������� �ƴ϶�� mobileLogin ó���Ѵ�.
                    setSession(session, mobileLoginForm.getMobileLoginDTO().getLocale(), userInfoList);
                    forwadName = "mobileMain";
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
            case MobileLoginAction.NOT_LOGIN_USER :

                // �α��� ���� ������ ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, mobileLoginForm, false);
                
                //Config ���� ���� ��¥ (10�ڸ�) �����Ͽ� �˻�. �� ������ forwadName = "logout" 
                String mobileLoginKeyValue = MwareConfig.getLoginkeyvalue();
                Calendar startCalendar = new GregorianCalendar();
                String todayStr = startCalendar.get(Calendar.YEAR)+""+
					              (startCalendar.get(Calendar.MONTH)+1<10?"0"+(startCalendar.get(Calendar.MONTH)+1):startCalendar.get(Calendar.MONTH)+1)+""+
					              (startCalendar.get(Calendar.DATE)<10?"0"+startCalendar.get(Calendar.DATE):startCalendar.get(Calendar.DATE))+"";
                
                String param = mobileLoginForm.getUserDTO().getLoginKeyValue();
                
                
                if (userInfoList.size() > 0 && param.equals(mobileLoginKeyValue+todayStr))
                {
                    //========================================================
                    // �̹� �α��ε� �������� üũ�Ѵ�. 
                    // �α��ε� ������� ���� �α׾ƿ� ��Ű�� ���� session�� �α��� ��Ų��.
                    if (mobileLoginService.isUsing(userNo))
                    {
                        mobileLoginService.isForceLogOut(userNo, session.getId());
                    }
                    else //
                    {
                    	if(!chkCcUserNum(request, userInfoList))
                    	{
                            mobileLoginService.unBound(session);
                            forwadName = "index";
                            // ����� ���� �ʰ��Ǿ����ϴ�.
                            if(request.getRequestURI().indexOf("mobile") > 0) forwadName = "mobileIndex";
                            request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                            break;
                    	}
                    }
                    //========================================================
                    
                    setSession(session,  configService.findLanguage(), userInfoList);
                    forwadName = "mobileMain";
                }
                break;
            case USER_FIND:

                // �α��� ������ ���������� ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, mobileLoginForm, true);
                if (userInfoList == null) break;
                
                // �α��� �õ� ����ID�� �α��� �Ǿ� �ִ��� üũ
                if(!mobileLoginService.isUsing(userNo))
                {
                	//
                	if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // ����� ���� �ʰ��Ǿ����ϴ�.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                	
                    // ������� �ƴ϶�� mobileLogin ó���Ѵ�.
                    setSession(session, mobileLoginForm.getMobileLoginDTO().getLocale(), userInfoList);
                    forwadName = "mobileMain";
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
                userInfoList = getUserInfo(request, mobileLoginForm, false);

                if (userInfoList == null) break;
                //confirm���� ����ڰ� ���ÿ� ���� ������ �ȴ�.
                if (userInfoList.size() > 0)
                {
                    mobileLoginService.isForceLogOut(userNo, session.getId());
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // ����� ���� �ʰ��Ǿ����ϴ�.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                    
                    setSession(session, mobileLoginForm.getMobileLoginDTO().getLocale(), userInfoList);
                    forwadName = "mobileMain";

                    break;
                }
                //MobileLogin ID�� ���� �� ����ȴ�. 
                else
                {
                    // ��ϵ� User�� �ƴմϴ�.
                    request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0003"));
                }
                break;
            case LOGOUT:
                mobileLoginService.unBound(session);
                setLocale(request, new Locale(configService.findLanguage()));
                break;
            case UNLOAD:
                mobileLoginService.unBound(session);
                forwadName = "mobileLogout";
                break;
            case CALL_PAGE :
                userInfoList = getUserInfo(request, mobileLoginForm, false);
                if (userInfoList.size() > 0)
                {
                    //========================================================
                    // �̹� �α��ε� �������� üũ�Ѵ�. 
                    // �α��ε� ������� ���� �α׾ƿ� ��Ű��, session�� �α��� ��Ų��.
                    if (mobileLoginService.isUsing(userNo))
                    {
                        mobileLoginService.isForceLogOut(userNo, session.getId());
                    }
                    //========================================================
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // ����� ���� �ʰ��Ǿ����ϴ�.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                    
                    // ���� mobileLogin user �α��� ó���Ѵ�.
                    setSession(session, configService.findLanguage(), userInfoList);

                    return callPage(request);
                }
                
                //========================================================================================
                // �λ�DB�� ��� ����ڰ� �ƴմϴ�. \\n �����ڿ��� ���� �ٶ��ϴ�.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0008"));
                //========================================================================================
                
                break;
            default:
                /*
                 * ���� session ID�� mobileLogin Users �ؽ����̺� ���� �����ϴ����� üũ�Ѵ�. 
                 * �ؽ����̺� �����Ѵٸ� Main ȭ���� �����ְ�
                 * �������� �ʴ� �ٸ� index(�α���)ȭ���� �����ش�.
                 * �α����� �� �� ���� �������� �ٸ� ������Ʈ�� �̵��� �Ͽ��ٰ� �ּ�â�� �ּҸ� �Է��ϰ�  ���� 
                 * �α��� ���� �ٷ� �̵��� �� �ֵ��� �Ѵ�.
                 */
                existsMobileLoginUser = existsMobileLoginUser(request);
                if (existsMobileLoginUser)
                {
                    forwadName = "mobileMain";
                    
                    break;
                }
            
                // �ٽ� �α��ؾ��ϴ°�� ������ session �� invalidate �Ѵ�.
                mobileLoginService.unBound(session);
                
                
                setLocale(request, new Locale(configService.findLanguage()));
                
                break;
        }
       
        // index.jsp ȭ������ �������� 
        if ("mobileIndex".equals(forwadName))
        {
            //=================================
            // index page Select box(language)
//            setPageSelect(request, mobileLoginForm);
            //=================================
        }
        
        // LOG IN ����
        if ("mobileMain".equals(forwadName) && !existsMobileLoginUser)
        {
            mobileLoginSuccess(request);
        }

        return  mapping.findForward(forwadName);
    }
    
    /**
     * �α��� ����
     * @author  javaworker
     * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     */
    private void mobileLoginSuccess(HttpServletRequest request)
    {
        MobileLoginService mobileLoginService = (MobileLoginService) getBean("mobileLoginService");
        
        HttpSession session = request.getSession();
        User mobileLoginUser = (User) session.getAttribute(request.getSession().getId());
        
        // �α��� �̷��� �����.
        mobileLoginService.insertLogHist(mobileLoginUser, request);
    }

    /**
     * User ������ ��ȸ�ϰ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param mobileLoginForm
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
    private List getUserInfo(HttpServletRequest request, MobileLoginForm mobileLoginForm, boolean valid) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    { 
        MobileLoginService mobileLoginService = (MobileLoginService) getBean("mobileLoginService");
        MobileLoginDTO mobileLoginDTO = mobileLoginForm.getMobileLoginDTO();

        if("".equals(mobileLoginDTO.getUserNo()))
        {
        	User userDTO = mobileLoginForm.getUserDTO();
        	mobileLoginDTO.setUserNo(userDTO.getUserNo());
        	mobileLoginDTO.setLocale(userDTO.getLang());
        	mobileLoginDTO.setCompNo(userDTO.getCompNo());
        }
        
        List userInfoList = mobileLoginService.findUserInfo(mobileLoginDTO);     // user ���� ��ȸ

        // true �ΰ�� password �� ���Ͽ� �α��� �����Ѵ�.
        if (valid)
        {
        	//��ȣ ��ȣȭ
            String password = mobileLoginForm.getMobileLoginDTO().getPassWord();
            //===============================================================
            // Encrypt Password
            String passwordEncrypt = CommonUtil.aesEncodeString(password);
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
            mobileLoginService.setLoginDate(mobileLoginDTO);
           
            return userInfoList;
        }
        else
        {       	
            String validation = mobileLoginDTO.getValid();
        	if(!validation.equals("valid")) return null;
            return userInfoList;
        }
    }
    
    /**
     * �ش� Page�� forward
     * @author  javaworker
     * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    private ActionForward callPage(HttpServletRequest request)
    {
        String pageId = request.getParameter("PAGE_ID");
        
        String param = "?isDecoratorName=popupPage";
        
        // �۾���û ��
        if ("eqMstrDetail".equals(pageId))
        {
            int eqMstrDetailAction = 0;
            String equipNo = request.getParameter("EQUIP_NO");
            
            param = param + "&strutsAction=" + eqMstrDetailAction +
                            "&eqMstrCommonDTO.equipNo=" + equipNo;
        }
        // �۾���û ��
        else if ("woReqMstrDetail".equals(pageId))
        {
            int worMesDetailAction = 0;
            
            param = param + "&strutsAction=" + worMesDetailAction;
        }
        // �۾���� ��
        else if ("woResultMstrDetail".equals(pageId))
        {
            int worMesDetailAction = 0;
            String woNo    = request.getParameter("WONO");
            
            param = param + "&strutsAction=" + worMesDetailAction +
                            "&woResultMstrCommonDTO.woNo=" + woNo;
        }
        
        return new ActionForward("/" + pageId + ".do" + param, true);
    }

    /**
     * MobileLogin ���� �ʰ�, Ư�� Service �� ȣ���Ѵ�.
     * @author  javaworker
     * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userInfoList
     * @param request2
     */
    private void callService(MobileLoginForm mobileLoginForm, HttpServletRequest request) throws Exception
    {
        /*
        String serviceType = request.getParameter("SERVICE_TYPE");
        
        if ("WONO_APP".equals(serviceType))
        {            
            CommonAppService commonAppService = (CommonAppService)getBean("commonAppService");
            
            /*
             * ȣ���� ���񽺸� ����Ѵ�.
             * wfType   : ���� ���� (WO:�۾����ü�, PR:���ſ�û��, PO:���ſ䱸�� ��)
             * stepNum  : ���� ����
             * authoNo  : ���� ���� ��ȣ
             * authoBy  : ������
             * wfStatus : ����(C:����, B:����, D:�ݷ�, E:���)
            String wfType   = request.getParameter("WF_TYPE");
            String stepNum  = request.getParameter("STEP_NUM");
            String authoNo  = request.getParameter("AUTHO_NO");
            String authoBy  = mobileLoginForm.getMobileLoginDTO().getUserID();
            String wfStatus = request.getParameter("WF_STATUS");
            
            int result = commonAppService.callAppService(wfType, stepNum, authoNo, authoBy, wfStatus);
            
            // ���� ó��
            if (result == 1)
            {
                request.setAttribute("RESULT_MSG", "ó�� �Ϸ� �Ǿ����ϴ�.");                
            }
            else
            {
                request.setAttribute("RESULT_MSG", "���� ó���� ���� �Ϸ� ���� �ʾҽ��ϴ�.");    
            }
        }
        else
        {
            request.setAttribute("RESULT_MSG", "ȣ��� ���񽺸� ã���� �����ϴ�.");            
        }
        */        
    }

    /**
     * ���� session ID�� mobileLogin Users �ؽ����̺� Ű ���� �����ϴ�����  üũ�Ѵ�. 
     * @author  mentor
     * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return true  : �ִ� ���
     *         false : ���� ���
     * @throws Exception
     */
    private boolean existsMobileLoginUser(HttpServletRequest request) throws Exception
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
        
        if(!"mobile".equals(getUser(request).getPageType()))
        {
            result = false;
        }
        
        return result;
    }    
    
    /**
     * Session �� User ��ü�� ����
     * @param session ���� ��ü
     * @param userID ����� ���̵�
     */
    public void setSession(HttpSession session, String lang, List userList)
    {
        /*
         * Session������ ��� ��ü
         */
        User user = new User();
        
        Map hash = (Map)userList.get(0);

        String id           = String.valueOf(hash.get("USERID")); //����� ID (MobileLogin)
        String userNo       = String.valueOf(hash.get("USERNO")); //����� ��ȣ 
        String name         = (String)hash.get("USERNAME");

        String group        = String.valueOf(hash.get("USRGRPID"));
        String groupName    = String.valueOf(hash.get("USRGRPNAME"));
        String compNo       = (String)hash.get("COMPNO");
        String initMenuId   = String.valueOf(hash.get("INITMENUID"));
        String initFileName = String.valueOf(hash.get("FILENAME"));
        
//        String language     = "en";
//        String languageId   = "en";
        String language     = (String)hash.get("LANG");
        String languageId   = String.valueOf(hash.get("LANGID"));
        String langDesc   = String.valueOf(hash.get("LANGDESC"));
       
       
        String deptId       = String.valueOf(hash.get("DEPTID"));
        String deptDesc     = (String)hash.get("DEPTDESC");
        
        String filterDeptId       = String.valueOf(hash.get("FILTERDEPTID"));
        String filterDeptDesc     = (String)hash.get("FILTERDEPTDESC");

        String empId       = String.valueOf(hash.get("EMPID"));
        String empName     = (String)hash.get("EMPNAME");
        
        String wcodeId       = String.valueOf(hash.get("WCODEID"));
        String wname         = (String)hash.get("WNAME");
        
        String twcodeId       = String.valueOf(hash.get("TWCODEID"));
        String twname         = (String)hash.get("TWNAME");
        
        String securGrade       = String.valueOf(hash.get("SECURGRADE"));
        String scrnFontSize     = String.valueOf(hash.get("SCRNFONTSIZE"));
        
        String ctPath     = String.valueOf(hash.get("CTPATH")); //CSS Path
        String shiftType         = String.valueOf(hash.get("SHIFTTYPE")); //������
        String shiftTypeDesc     = String.valueOf(hash.get("SHIFTTYPEDESC")); //��������
        
        String eqLocId   = String.valueOf(hash.get("EQLOCID"));
        String eqLocDesc = String.valueOf(hash.get("EQLOCDESC"));
        String plant     = String.valueOf(hash.get("PLANT"));
        String plantDesc = String.valueOf(hash.get("PLANTDESC"));
        
        String alarmViewYn = String.valueOf(hash.get("ALARMVIEWYN"));
        
        String wkctrId   = String.valueOf(hash.get("WKCTRID"));
        String wkctrDesc = String.valueOf(hash.get("WKCTRDESC"));
        String eqCtgTypeId   = String.valueOf(hash.get("EQCTGTYPEID"));
        String eqCtgTypeDesc = String.valueOf(hash.get("EQCTGTYPEDESC"));
        String menuDisplay = String.valueOf(hash.get("MENUDISPLAY"));
//        
//        String grade = hash.get("GRADE")==null?"":(String)hash.get("GRADE");
        
        
//        String pageType = (String)hash.get("PAGETYPE");
//        String skinType = (String)hash.get("SKINTYPE");
        user.setUserId(id);
        user.setUserNo(userNo);
        user.setUserName(name);
        
        user.setUsrgrpId(group);
        user.setUsrgrpName(groupName);
        user.setCompNo(compNo);
        user.setInitMenuId(initMenuId);
        user.setFileName(initFileName);
        user.setLangId(languageId);
        user.setDeptId(deptId);
        user.setDeptDesc(deptDesc);
        user.setFilterDeptId(filterDeptId);
        user.setFilterDeptDesc(filterDeptDesc);
        user.setEmpId(empId);
        user.setEmpName(empName);
        user.setWcodeId(wcodeId);
        user.setWname(wname);
        user.setTwcodeId(twcodeId);
        user.setTwname(twname);
        user.setSecurGrade(securGrade);
        user.setScrnFontSize(scrnFontSize);
        user.setCtPath(ctPath);
        user.setShiftType(shiftType);
        user.setShiftTypeDesc(shiftTypeDesc);
        user.setEqLocId(eqLocId);
        user.setEqLocDesc(eqLocDesc);
        user.setPlant(plant);
        user.setPlantDesc(plantDesc);
        user.setAlarmViewYn(alarmViewYn);
        user.setWkctrId(wkctrId);
        user.setWkctrDesc(wkctrDesc);
        user.setEqCtgTypeId(eqCtgTypeId);
        user.setEqCtgTypeDesc(eqCtgTypeDesc);
        user.setLangDesc(langDesc);
        user.setMenuDisplay(menuDisplay);
        user.setPageType("mobile");
        
        user.setLoginTimeMillis(System.currentTimeMillis());

        if(language == null) language = lang;
        //================================================
        // ��� ����
        Locale locale = new Locale(language);
        user.setLocale(locale);
        session.setAttribute(Globals.LOCALE_KEY, locale);
        //=================================================
        
        // ���� �α����� User ��ü�� �����Ѵ�.
        BaseAction.getLoginUsers().put(session.getId(), user);

        session.setAttribute(session.getId(), user);
        session.setAttribute("ctPath", ctPath=="null"?MwareConfig.getInitCtPath():ctPath);
    }
    
    
    /**
     * Check Concurrent User
     * @param request
     * @return
     */
    private boolean chkCcUserNum(HttpServletRequest request, List userList)
    {
    	boolean result = false;
    	int lgnCnt = 0;
        Hashtable mobileLoginUsersTable = BaseAction.getLoginUsers();
        MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");
    	
        Map hash = (Map)userList.get(0);
        String[] sysAdmGrpArr = maUsrGrpAuthListService.getAdminUsrGrp(String.valueOf(hash.get("COMPNO")));
    	
        // Key ����
        Enumeration mobileLoginUsersEm = mobileLoginUsersTable.keys();
        
        User user = null;
        int i = 1;
        while (mobileLoginUsersEm.hasMoreElements())
        {
            // User session Id 
            String mobileLoginUserKey = (String)mobileLoginUsersEm.nextElement();
            
            user = (User)mobileLoginUsersTable.get(mobileLoginUserKey);
             //�ý��� ������ �ƴ� �������� ���ڸ� �ľ�. 
            boolean isSysUser = false;
            for(String admGrpStr : sysAdmGrpArr)
            {
            	if(admGrpStr.equals(user.getUsrgrpName())) isSysUser= true;
            }
            if(!isSysUser) lgnCnt++;
        }
        
        //System.out.println("�ý��� ������ ������ ���� �α��� ���� ����:"+lgnCnt);
        if(lgnCnt < 15) result = true;
        
        //���� ������ SYSTEM ���� ������ �׳� ���
        
        String groupName    = String.valueOf(hash.get("USRGRPNAME"));
        for(String admGrpStr : sysAdmGrpArr)
        {
        	if(admGrpStr.equals(groupName)) result= true;
        }
        
        return result;
    }
}