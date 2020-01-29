package dream.login.login.action;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base32;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import common.bean.MwareConfig;
import common.bean.User;
import common.config.service.ConfigService;
import common.message.DataBaseMessageResources;
import common.sso.dto.SsoDTO;
import common.sso.service.SsoService;
import common.struts.BaseAction;
import common.struts.NoneAuthAction;
import common.util.AES256Cipher;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.comm.service.DateService;
import dream.login.login.dto.LoginDTO;
import dream.login.login.form.LoginForm;
import dream.login.login.service.LoginService;
import dream.mgr.usrgrp.service.MaUsrGrpAuthListService;
import dream.pers.priv.info.dto.MaChangePwDTO;
import dream.pers.priv.info.service.MaChangePwService;

/**
 * Login ó�� Action
 * @author  javaworker
 * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
 * @since   1.0
 * @struts.action path="/index" name="loginForm"
 *                input="/dream/index.jsp" scope="request"
 *                validate="false" 
 * @struts.action-forward name="findUser"
 *                        path="/main.do" redirect="true"
 * @struts.action-forward name="index"
 *                        path="/index.jsp" redirect="false"
 * @struts.action-forward name="logout"
 *                        path="/logout.jsp" redirect="false"   
 * @struts.action-forward name="mainPwChange"
 *                        path="/dream/login/login/mainPwChange.jsp" redirect="false"
 * @struts.action-forward name="resultService"
 *                        path="/common/jsp/resultView.jsp" redirect="false"   
 */
public class LoginAction
        extends NoneAuthAction
{
    /** �α��� ��û */
    public static final int USER_FIND = 2001;
    /** ��й�ȣ ���� ��û */
    public static final int PW_CHANGE = 2002;
    /** �α׾ƿ� ��û */
    public static final int LOGOUT = 2004;
    /** ������â�� �����鼭 �α׾ƿ� */
    public static final int UNLOAD = 2005;
    /** ������ ��ġ�� �ʰ� �α� �Ҷ� ���ȴ�.(id, password �Է����� �ʰ� ��ũ�� �α�) */
    public static final int NOT_LOGIN_USER = 9554;
    /** �α� ���� �ʰ� ����� ȣ���Ѵ�. */
    public static final int CALL_SERVICE = 3002;
    /** SSO Login */
    public static final int SSO_USER_FIND = 9001;
    /**
     * �α����� �Ǿ����� �� �ٽ� ���� ���̵�� �α����� �õ��� ���
     * �޽���â���� Ȯ��(OK)��ư�� ������ ��� ���� �ȴ�. 
     */
    public static final int USER_OK_LOGOUT = 2006;
    /** �α��� ���� �ʰ� �ش� �������� foward */
    private static final int CALL_PAGE = 3003;
    /** Main Page cancel */
    public static final int CANCEL = 3004;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        // ���ο� session ����
        HttpSession session = request.getSession();
        LoginForm loginForm = (LoginForm) form;
        
        LoginService loginService = (LoginService) getBean("loginService");
        String forwadName = "index";   // default forward index.jsp
        
        String localeLang = loginForm.getLoginDTO().getLocale();
        
        LoginDTO loginDTO = loginForm.getLoginDTO();
        if("".equals(loginDTO.getPassWord()))
        {
        	loginDTO.setPassWord(loginDTO.getpWord());
        	loginForm.setLoginDTO(loginDTO);
        }
        
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        if(localeLang == null) localeLang = configService.findLanguage();

        setLocale(request, new Locale(localeLang));
        loginForm.getLoginDTO().setLocale(localeLang);

        //Locale ����
        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
        dataBaseMessageResources.loadLocale(localeLang);
        
    	// ���� �������� �̹� �α��� �Ǿ� �ִ��� ����
    	boolean existsLoginUser = false;

        List userInfoList = null;
        User user = null;
        String userNo = loginForm.getLoginDTO().getUserNo(); //Login Id
        String compNo = loginForm.getLoginDTO().getCompNo(); //Comp No

        
        switch(loginForm.getStrutsAction())
        {
            case LoginAction.CALL_SERVICE :
                // Login ���� �ʰ� Ư�� ���񽺸� �̿��Ѵ�.
                callService(loginForm, request);
                forwadName = "resultService";
                break;
            case LoginAction.NOT_LOGIN_USER :

                // �α��� ���� ������ ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, loginForm, false);
                
                //Config ���� ���� ��¥ (10�ڸ�) �����Ͽ� �˻�. �� ������ forwadName = "logout" 
                String loginKeyValue = MwareConfig.getLoginkeyvalue();
                Calendar startCalendar = new GregorianCalendar();
                String todayStr = "";
//                todayStr = startCalendar.get(Calendar.YEAR)+""+
//					              (startCalendar.get(Calendar.MONTH)+1<10?"0"+(startCalendar.get(Calendar.MONTH)+1):startCalendar.get(Calendar.MONTH)+1)+""+
//					              (startCalendar.get(Calendar.DATE)<10?"0"+startCalendar.get(Calendar.DATE):startCalendar.get(Calendar.DATE))+"";
                
                String param = loginForm.getUserDTO().getLoginKeyValue();
                
                if (userInfoList.size() > 0 && param.equals(loginKeyValue+todayStr))
                {
                    setSession(session,  configService.findLanguage(), userInfoList);
                    forwadName = "findUser";
                }
                break;
            case USER_FIND:

                // �α��� ������ ���������� ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, loginForm, true);

                if (userInfoList == null) break;
                
            	 Map hash = (Map)userInfoList.get(0);
                 String changeNeed = (String)hash.get("CHANGENEED");
            	
                 //��й�ȣ ������ �ʿ��� ����
                 if("Y".equals(changeNeed))
                 {
                	 forwadName = "mainPwChange";
                	 break;
                 }
                // ������� �ƴ϶�� login ó���Ѵ�.
                setTimeGap(session, loginForm.getLoginDTO().getLocalTime());
                setSession(session, loginForm.getLoginDTO().getLocale(), userInfoList);
                forwadName = "findUser";
                break;

            case USER_OK_LOGOUT:
                userInfoList = getUserInfo(request, loginForm, false);

                if (userInfoList == null) break;
                //confirm���� ����ڰ� ���ÿ� ���� ������ �ȴ�.
                if (userInfoList.size() > 0)
                {
                    setTimeGap(session, loginForm.getLoginDTO().getLocalTime());
                    setSession(session, loginForm.getLoginDTO().getLocale(), userInfoList);
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
            case PW_CHANGE:
            	// �α��� ������ ���������� ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, loginForm, true);
                forwadName = "mainPwChange";
                
                if (userInfoList == null)  break;
    

            	MaChangePwService maChangePwService = (MaChangePwService) getBean("maChangePwService");
                
            	MaChangePwDTO maChangePwDTO = new MaChangePwDTO();

                hash = (Map)userInfoList.get(0);
                String userId = String.valueOf(hash.get("USERID"));
   
                maChangePwDTO.setCompNo(String.valueOf(hash.get("COMPNO")));
                maChangePwDTO.setUserId(userId);
                maChangePwDTO.setNewPw(loginDTO.getNewPassWord());
                maChangePwDTO.setConfirmPw(loginDTO.getNewPassWord());
                maChangePwDTO.setOldPw(loginDTO.getPassWord());
                maChangePwDTO.setConfirmPw(loginDTO.getValidPassWord());
                
                user = getUser(request);
                user.setLangId(loginDTO.getLocale());
                user.setLocale(new Locale(loginDTO.getLocale()));

                Map<String,String> rtnMap = maChangePwService.checkPassword(maChangePwDTO, user);
                
                if(!"SUCCESS".equals(rtnMap.get("RESULT")))
                {
                    loginService.unBound(session);
                    request.setAttribute("MESSAGE", rtnMap.get("MESSAGE"));
                    break;
                }

                // ������� �ƴ϶�� login ó���Ѵ�.
                setTimeGap(session, loginForm.getLoginDTO().getLocalTime());
                setSession(session,  configService.findLanguage(), userInfoList);
                forwadName = "findUser";
                break;
          
            case LOGOUT:
                loginService.unBound(session);
                setLocale(request, new Locale(configService.findLanguage()));
                break;
            case UNLOAD:
                loginService.unBound(session);
                forwadName = "logout";
                break;
            case CALL_PAGE :
                userInfoList = getUserInfo(request, loginForm, false);
                
                if (userInfoList.size() > 0)
                {
                    // ���� login user �α��� ó���Ѵ�.
                    setSession(session, configService.findLanguage(), userInfoList);

                    return callPage(request);
                }
                
                //========================================================================================
                // �λ�DB�� ��� ����ڰ� �ƴմϴ�. \\n �����ڿ��� ���� �ٶ��ϴ�.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0008"));
                //========================================================================================
                
                break;
            case CANCEL:
            	forwadName = "index";
            	break;
            case LoginAction.SSO_USER_FIND :
				user = new User();
				
            	// SSO Login�� ����ϴ� ȸ������ üũ
            	if (!"Y".equals(MwareConfig.getIsUseSsoLogin())) {
            		// SSO Login�� ������� �ʴ� ȸ���Դϴ�.
//            		request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0264"));
            		forwadName = "index";
                	break;
				}
            	
            	String ssoStr = "";
            	String ssoKey = MwareConfig.getSsoAesKey();
            	
            	// ����Ʈ�ּ�/index.do?strutsAction=9001&ssoStr=��ȣȭ�ȹ���
            	if ("".equals(request.getParameter("ssoStr")) || request.getParameter("ssoStr") == null) {
            		forwadName = "index";
                	break;
				} else{
					ssoStr = request.getParameter("ssoStr");
				}
            	
            	try {
            		// ��ȣȭ (ex userNo=12312&today=20190116)
            		String decodeStr = AES256Cipher.getInstance(ssoKey).AES_Decode(ssoStr).trim();
            		String[] a = decodeStr.split("&");
            		String decodeUserNo = a[0].replaceFirst("userNo=", "");
            		String decodeToday = a[1].replaceFirst("today=", "");
            		
            		// ���ó�¥�� ������ ��ȣ���� üũ
            		if (!DateUtil.getDate().equals(decodeToday)) {
                		forwadName = "index";
                    	break;
					}else{
						userNo = decodeUserNo;
						loginDTO.setUserNo(userNo);
						user.setUserNo(userNo);
					}
					
				} catch (Exception e) {
            		forwadName = "index";
                	break;
				}
            	
            	
            	// 1. �� ����Ʈ�� ���񽺸� Ÿ������ �ʿ��� �� ���� (userNo�� comp_no ���ؿ���)
				//userNo�� compNo �˻�
				List compList = loginService.findCompInfo(loginDTO);
				if (compList.size() > 1) {
                	// �ش� ������ ȸ�簡 2�� �̻��Դϴ�.
                	request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0262"));
                	forwadName = "index";
                	break;
				} else if (compList.size() == 0){
                	// �ش� �ý��ۿ� �α��ΰ����� �����ϴ�.
                	request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0263"));
                	forwadName = "index";
                	break;
				}
						
				SsoDTO ssoDTO = new SsoDTO();
				Map comp = (Map) compList.get(0);
				loginDTO.setCompNo((String)comp.get("compNo"));
				loginForm.setLoginDTO(loginDTO);
				user.setCompNo((String)comp.get("compNo"));
            	
                BaseAction.getLoginUsers().put(session.getId(), user);

                session.setAttribute(session.getId(), user);
                
                ssoDTO.setUserNo(loginDTO.getUserNo());
                
            	// 2. �� ����Ʈ�� Ȯ�弭�񽺷� sso�� �α����� ��������ϴ��� �Ǻ��Ѵ�.
                SsoService ssoService = (SsoService) getBean("ssoService", request);
            	boolean ssoChk = ssoService.checkSso(request, ssoDTO, user);
            	if (ssoChk) {
            		// �α��� ���� ������ ��ȸ�Ѵ�.
            		userInfoList = getUserInfo(request, loginForm, false);
				
	                if (userInfoList.size() > 0)
	                {
	                    setSession(session,  configService.findLanguage(), userInfoList);
	                    forwadName = "findUser";
	                    break;
	                }
            	}
            	
            	// SSO �α����� ����ϴµ�, �츮 �ý����� ������� �ʴ� ������ indexȭ���� �����ش�.
        		forwadName = "index";
                break;
                
            default:
                /*
                 * ���� session ID�� login Users �ؽ����̺� ���� �����ϴ����� üũ�Ѵ�. 
                 * �ؽ����̺� �����Ѵٸ� Main ȭ���� �����ְ�
                 * �������� �ʴ� �ٸ� index(�α���)ȭ���� �����ش�.
                 * �α����� �� �� ���� �������� �ٸ� ������Ʈ�� �̵��� �Ͽ��ٰ� �ּ�â�� �ּҸ� �Է��ϰ�  ���� 
                 * �α��� ���� �ٷ� �̵��� �� �ֵ��� �Ѵ�.
                 */
                existsLoginUser = existsLoginUser(request);
                if (existsLoginUser)
                {
                    forwadName = "findUser";
                    break;
                }
            
                // �ٽ� �α��ؾ��ϴ°�� ������ session �� invalidate �Ѵ�.
                loginService.unBound(session);
                
                
                setLocale(request, new Locale(configService.findLanguage()));
                
                break;
        }
       
        // index.jsp ȭ������ �������� 
        if ("index".equals(forwadName))
        {
            //=================================
            // index page Select box(language)
//            setPageSelect(request, loginForm);
            //=================================
        }
        
        // LOG IN ����
        if ("findUser".equals(forwadName) && !existsLoginUser)
        {
            loginSuccess(request, loginForm);
            session.setAttribute("redirectPage", loginForm.getRedirectPage());
            session.setAttribute("redirectParam", loginForm.getRedirectParam());
        }

        return  mapping.findForward(forwadName);
    }
    
    protected boolean checkOtpCode(List userInfoList, String inputCode) throws ServletException, IOException {
        boolean isValidCode = false;
        try {
            // Ű, �ڵ�, �ð����� ��ȸ�� ��й�ȣ�� �´��� ��ġ ���� Ȯ��.
            Map hash = (Map)userInfoList.get(0);
            String encodedKey = hash.get("OTPKEY")==null?"":String.valueOf(hash.get("OTPKEY"));
            Base32 codec = new Base32();
            byte[] decodedKey = codec.decode(encodedKey);
            long t = new Date().getTime() / 30000;
            
            // Window is used to check codes generated in the near past.
            // You can use this value to tune how far you're willing to go.
            int window = 0;
            for (int i = -window; i <= window; ++i) {
                String code = verifyCode(decodedKey, t + i);
                
                if (code.equals(inputCode)) {
                    isValidCode = true;
                }
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return isValidCode;
    }
    
    private static String verifyCode(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
 
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
 
        int offset = hash[20 - 1] & 0xF;
 
        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
 
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
 
        return String.valueOf(truncatedHash);
    }
    
    /**
     * �α��� ����
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     */
    private void loginSuccess(HttpServletRequest request, LoginForm loginForm)
    {
        LoginService loginService = (LoginService) getBean("loginService");
        LoginDTO loginDTO = loginForm.getLoginDTO();        
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(request.getSession().getId());
        
        // �α��� �̷��� �����.
        loginService.insertLogHist(loginUser, request);
        // �α��� �õ� �̷��� �����.
        loginService.insertLoginTryLog(loginDTO, request, "Y");
    }

    /**
     * User ������ ��ȸ�ϰ� �����Ѵ�.
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param loginForm
     * @param valid
     * @return
     * @throws Exception 
     */
    private List getUserInfo(HttpServletRequest request, LoginForm loginForm, boolean valid) throws Exception
    { 
        LoginService loginService = (LoginService) getBean("loginService");
        LoginDTO loginDTO = loginForm.getLoginDTO();

        if("".equals(loginDTO.getUserNo()))
        {
        	User userDTO = loginForm.getUserDTO();
        	loginDTO.setUserNo(userDTO.getUserNo());
        	loginDTO.setLocale(userDTO.getLang());
        	loginDTO.setCompNo(userDTO.getCompNo());
        }
        
        List userInfoList = loginService.findUserInfo(loginDTO);     // user ���� ��ȸ

        // User�� ���� ���
        if(userInfoList == null || userInfoList.size() <= 0)
        {
            // �α��� �õ� �̷��� �����.
            loginService.insertLoginTryLog(loginDTO, request, "N");
            
            Map alertMap = new HashMap();
            alertMap.put("code", "WRONGIDPW");
            alertMap.put("pwFailCnt", 0);
            alertMap.put("pwFailLimitCnt", 0);
            String jsonString = new Gson().toJson(alertMap);
            request.setAttribute("ALERT", jsonString);
            
            return null;
        }
        
        Map hash = (Map)userInfoList.get(0);
        String isLocked = (String)hash.get("ISLOCKED");
        String loginFailCnt = (String)hash.get("LOGINFAILCNT").toString();
        
        // true �ΰ�� password �� ���Ͽ� �α��� �����Ѵ�.
        if (valid)
        {
        	//��ȣ ��ȣȭ
            String password = loginForm.getLoginDTO().getPassWord();
            //===============================================================
            // Encrypt Password
            String passwordEncrypt = CommonUtil.aesEncodeString(password);
            //===============================================================

            if("Y".equals(isLocked) && !"0".equals(MwareConfig.getPwFailLimitCnt()))
            {
            	// �α��� �õ� �̷��� �����.
            	loginService.insertLoginTryLog(loginDTO, request, "N");
            	
            	Map alertMap = new HashMap();
                alertMap.put("code", "LOCKUSER");
                String jsonString = new Gson().toJson(alertMap);
                request.setAttribute("ALERT", jsonString);
                
            	return null;
            }
            
            //OTP �Է��� �ʿ��� ����
            if("Y".equals(MwareConfig.getIsUseOtpLogin()))
            {
                // OTP�� �´��� üũ�Ѵ�.
                boolean _valid = checkOtpCode(userInfoList, loginDTO.getOtp());
                if(!_valid)
                {
                    // �α��� �õ� �̷��� �����.
                    loginService.insertLoginTryLog(loginDTO, request, "N");
                    
                    int pwFailCnt = Integer.valueOf(loginFailCnt).intValue()+1;
                    Map alertMap = new HashMap();
                    alertMap.put("code", "WRONGIDPW");
                    alertMap.put("pwFailCnt", pwFailCnt);
                    alertMap.put("pwFailLimitCnt", MwareConfig.getPwFailLimitCnt());
                    String jsonString = new Gson().toJson(alertMap);
                    request.setAttribute("ALERT", jsonString);
                    
                    return null;
                }
            }
            
            String userPassword = (String)hash.get("PASSWORD");
            
            if(!passwordEncrypt.equals(userPassword))
            {
                // �α��� �õ� �̷��� �����.
                loginService.insertLoginTryLog(loginDTO, request, "N");

                int pwFailCnt = Integer.valueOf(loginFailCnt).intValue()+1;
                Map alertMap = new HashMap();
                alertMap.put("code", "WRONGIDPW");
                alertMap.put("pwFailCnt", pwFailCnt);
                alertMap.put("pwFailLimitCnt", MwareConfig.getPwFailLimitCnt());
                String jsonString = new Gson().toJson(alertMap);
                request.setAttribute("ALERT", jsonString);
                
                return null;
            }

            int interval;
            
            String changePwdDate = (String)hash.get("CHANGEPWDDATE");
         
            //changePwDate �� ���ΰ�� Login ó���Ѵ�
            if(changePwdDate!=""&&changePwdDate!=null)
            {
            	interval =DateUtil.getDayInterval(changePwdDate,DateUtil.getDate());
            }
            else
            {
            	interval=0;
            }
            	
            
            //Change Pw �� Y �̸� ������ ��¥�� PW �����ֱ⺸�� ���� �Ǿ��ٸ� PW ���� �������� �ε� �Ѵ�.  ������ ������ �������� ����.. 7.10
            if(("Y").equals(MwareConfig.getIsChangePwNeeded()) && interval>Integer.parseInt(MwareConfig.getPwChangeCycleDay()))
            {
            	hash.put("CHANGENEED", "Y");            	
/*            	System.out.println((String)hash.get("CHANGENEED")+"ü����");
            	System.out.println("���������� ��ȯ");*/
            	
            }
            else
            {
            	hash.put("CHANGENEED", "N");  
            }


            if(loginService.isUsing(loginDTO.getUserNo(), loginDTO.getCompNo() )) loginService.isForceLogOut(loginDTO.getUserNo(), loginDTO.getCompNo(), request.getSession(true).getId());
            if(!chkCcUserNum(request, userInfoList))
            {
                loginService.unBound(request.getSession(true));
                
                // �α��� �õ� �̷��� �����.
                loginService.insertLoginTryLog(loginDTO, request, "N");
                
                int pwFailCnt = Integer.valueOf(loginFailCnt).intValue()+1;
                Map alertMap = new HashMap();
                alertMap.put("code", "OVERUSER");
                alertMap.put("pwFailCnt", pwFailCnt);
                alertMap.put("pwFailLimitCnt", MwareConfig.getPwFailLimitCnt());
                String jsonString = new Gson().toJson(alertMap);
                request.setAttribute("ALERT", jsonString);

                return null;
            }
            
            loginService.setLoginDate(loginDTO);
            return userInfoList;
        }
        else
        {       	
            if(loginService.isUsing(loginDTO.getUserNo(), loginDTO.getCompNo() )) loginService.isForceLogOut(loginDTO.getUserNo(), loginDTO.getCompNo(), request.getSession(true).getId());
            if(!chkCcUserNum(request, userInfoList))
            {
                loginService.unBound(request.getSession(true));
                
                // �α��� �õ� �̷��� �����.
                loginService.insertLoginTryLog(loginDTO, request, "N");
                
                int pwFailCnt = Integer.valueOf(loginFailCnt).intValue()+1;
                Map alertMap = new HashMap();
                alertMap.put("code", "OVERUSER");
                alertMap.put("pwFailCnt", pwFailCnt);
                alertMap.put("pwFailLimitCnt", MwareConfig.getPwFailLimitCnt());
                String jsonString = new Gson().toJson(alertMap);
                request.setAttribute("ALERT", jsonString);

                return null;
            } 
            return userInfoList;
        }
    }
    
    /**
     * �ش� Page�� forward
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
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
     * Login ���� �ʰ�, Ư�� Service �� ȣ���Ѵ�.
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param userInfoList
     * @param request2
     */
    private void callService(LoginForm loginForm, HttpServletRequest request) throws Exception
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
            String authoBy  = loginForm.getLoginDTO().getUserID();
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
        
        if(!"dream".equals(getUser(request).getPageType()))
        {
            result = false;
        }
        
        return result;
    }    
    
    public void setTimeGap(HttpSession session, long localTime) throws Exception
    {
        DateService dateService = (DateService) getBean("dateService");
        
        dateService.getTimeGap(session, localTime);
    }
    
    /**
     * Session �� User ��ü�� ����
     * @param session ���� ��ü
     * @param l 
     * @param userID ����� ���̵�
     */
    public void setSession(HttpSession session, String lang, List userList)
    {
        /*
         * Session������ ��� ��ü
         */
        User user = new User();
        
        Map hash = (Map)userList.get(0);
        
        //���� ������JSP���� ���� �߻��ؼ� �ּ�ó�� 
//        for(Object obj:hash.keySet()){
//            String key = (String) obj;
//            Object value = hash.get(key);
//            if(value == null){
//                hash.put(key, "");
//            }
//        }

//        String id           = hash.get("USERID")==null?"":String.valueOf(hash.get("USERID")); //����� ID (Login)
//        String userNo       = hash.get("USERNO")==null?"":String.valueOf(hash.get("USERNO")); //����� ��ȣ 
//        String name         = hash.get("USERNAME")==null?"":String.valueOf(hash.get("USERNAME"));
//
//        String group        = hash.get("USRGRPID")==null?"":String.valueOf(hash.get("USRGRPID"));
//        String groupName    = hash.get("USRGRPNAME")==null?"":String.valueOf(hash.get("USRGRPNAME"));
//        String compNo       = hash.get("COMPNO")==null?"":String.valueOf(hash.get("COMPNO"));
//        String initMenuId   = hash.get("INITMENUID")==null?"":String.valueOf(hash.get("INITMENUID"));
//        String initFileName = hash.get("FILENAME")==null?"":String.valueOf(hash.get("FILENAME"));
//        
////        String language     = "en";
////        String languageId   = "en";
//        String language     = hash.get("LANG")==null?"":String.valueOf(hash.get("LANG"));
//        String languageId   = hash.get("LANGID")==null?"":String.valueOf(hash.get("LANGID"));
//        String langDesc     =  hash.get("LANGDESC")==null?"":String.valueOf(hash.get("LANGDESC"));
//       
//       
//        String deptId       = hash.get("DEPTID")==null?"":String.valueOf(hash.get("DEPTID"));
//        String deptDesc     = hash.get("DEPTDESC")==null?"":String.valueOf(hash.get("DEPTDESC"));
//        
//        String filterDeptId       = hash.get("FILTERDEPTID")==null?"":String.valueOf(hash.get("FILTERDEPTID"));
//        String filterDeptDesc     = hash.get("FILTERDEPTDESC")==null?"":String.valueOf(hash.get("FILTERDEPTDESC"));
//        
//        String filterEmpId       = hash.get("FILTEREMPID")==null?"":String.valueOf(hash.get("FILTEREMPID"));
//        String filterEmpDesc     = hash.get("FILTEREMPDESC")==null?"":String.valueOf(hash.get("FILTEREMPDESC"));
//
//        String empId       = hash.get("EMPID")==null?"":String.valueOf(hash.get("EMPID"));
//        String empName     = hash.get("EMPNAME")==null?"":String.valueOf(hash.get("EMPNAME"));
//        
//        String wcodeId       = hash.get("WCODEID")==null?"":String.valueOf(hash.get("WCODEID"));
//        String wname         = hash.get("WNAME")==null?"":String.valueOf(hash.get("WNAME"));
//        
//        String twcodeId       = hash.get("TWCODEID")==null?"":String.valueOf(hash.get("TWCODEID"));
//        String twname         = hash.get("TWNAME")==null?"":String.valueOf(hash.get("TWNAME"));
//        
//        String securGrade       = hash.get("SECURGRADE")==null?"":String.valueOf(hash.get("SECURGRADE"));
//        String scrnFontSize     = hash.get("SCRNFONTSIZE")==null?"":String.valueOf(hash.get("SCRNFONTSIZE"));
//        
//        String ctPath            = hash.get("CTPATH")==null?"":String.valueOf(hash.get("CTPATH")); //CSS Path
//        String shiftType         = hash.get("SHIFTTYPE")==null?"":String.valueOf(hash.get("SHIFTTYPE")); //������
//        String shiftTypeDesc     = hash.get("SHIFTTYPEDESC")==null?"":String.valueOf(hash.get("SHIFTTYPEDESC")); //��������
//        
//        String eqLocId   = hash.get("EQLOCID")==null?"":String.valueOf(hash.get("EQLOCID"));
//        String eqLocDesc = hash.get("EQLOCDESC")==null?"":String.valueOf(hash.get("EQLOCDESC"));
//        String plant     = hash.get("PLANT")==null?"":String.valueOf(hash.get("PLANT"));
//        String plantDesc = hash.get("PLANTDESC")==null?"":String.valueOf(hash.get("PLANTDESC"));
//        
//        String alarmViewYn = hash.get("ALARMVIEWYN")==null?"":String.valueOf(hash.get("ALARMVIEWYN"));
//        
//        String wkctrId   = hash.get("WKCTRID")==null?"":String.valueOf(hash.get("WKCTRID"));
//        String wkctrDesc = hash.get("WKCTRDESC")==null?"":String.valueOf(hash.get("WKCTRDESC"));
//        String eqCtgTypeId   = hash.get("EQCTGTYPEID")==null?"":String.valueOf(hash.get("EQCTGTYPEID"));
//        String eqCtgTypeDesc = hash.get("EQCTGTYPEDESC")==null?"":String.valueOf(hash.get("EQCTGTYPEDESC"));
//        String menuDisplay = hash.get("MENUDISPLAY")==null?"":String.valueOf(hash.get("MENUDISPLAY"));
//        String mPhone = hash.get("MPHONE")==null?"":String.valueOf(hash.get("MPHONE"));
//        String eMail = hash.get("EMAIL")==null?"":String.valueOf(hash.get("EMAIL"));

        String id           = String.valueOf(hash.get("USERID")); //����� ID (Login)
        String userNo       = String.valueOf(hash.get("USERNO")); //����� ��ȣ 
        String name         = (String)hash.get("USERNAME");

        String group        = String.valueOf(hash.get("USRGRPID"));
        String groupName    = String.valueOf(hash.get("USRGRPNAME"));
        String compNo       = (String)hash.get("COMPNO");
        String compName       = (String)hash.get("COMPNAME");
        String initMenuId   = String.valueOf(hash.get("INITMENUID"));
        String initFileName = String.valueOf(hash.get("FILENAME"));
        
//        String language     = "en";
//        String languageId   = "en";
        String language     = (String)hash.get("LANG");
        String languageId   = String.valueOf(hash.get("LANGID"));
        String langDesc   = String.valueOf(hash.get("LANGDESC"));
       
       
        String deptId       = String.valueOf(hash.get("DEPTID"));
        String deptNo       = String.valueOf(hash.get("DEPTNO"));
        String deptDesc     = (String)hash.get("DEPTDESC");
        
        String filterDeptId       = String.valueOf(hash.get("FILTERDEPTID"));
        String filterDeptDesc     = (String)hash.get("FILTERDEPTDESC");
        
        String filterEmpId       = String.valueOf(hash.get("FILTEREMPID"));
        String filterEmpDesc     = (String)hash.get("FILTEREMPDESC");

        String empId       = String.valueOf(hash.get("EMPID"));
        String empNo       = String.valueOf(hash.get("EMPNO"));
        String empName     = (String)hash.get("EMPNAME");
        
        String wcodeId       = String.valueOf(hash.get("WCODEID"));
        String wname         = (String)hash.get("WNAME");
        
        String twcodeId       = String.valueOf(hash.get("TWCODEID"));
        String twname         = (String)hash.get("TWNAME");
        
        String fromWcodeId       = String.valueOf(hash.get("FROMWCODEID"));
        String fromWname         = (String)hash.get("FROMWNAME");
        
        String toWcodeId       = String.valueOf(hash.get("TOWCODEID"));
        String toWname         = (String)hash.get("TOWNAME");
        
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
        String filterWkCtrId   = String.valueOf(hash.get("FILTERWKCTRID"));
        String filterWkCtrDesc = String.valueOf(hash.get("FILTERWKCTRDESC"));
        String eqCtgTypeId   = String.valueOf(hash.get("EQCTGTYPEID"));
        String eqCtgTypeDesc = String.valueOf(hash.get("EQCTGTYPEDESC"));
        String menuDisplay = String.valueOf(hash.get("MENUDISPLAY"));
        String mPhone = String.valueOf(hash.get("MPHONE"));
        String eMail = String.valueOf(hash.get("EMAIL"));
        String filterWcodeId = String.valueOf(hash.get("FILTERWCODEID"));
        String filterWcodeDesc = String.valueOf(hash.get("FILTERWCODEDESC"));
        String filterPlant = String.valueOf(hash.get("FILTERPLANT"));
        String filterPlantDesc = String.valueOf(hash.get("FILTERPLANTDESC"));
//        
//        String grade = hash.get("GRADE")==null?"":(String)hash.get("GRADE");
        
        
//        String pageType = (String)hash.get("PAGETYPE");
//        String skinType = (String)hash.get("SKINTYPE");
        user.setUserId(id);
        user.setUserNo(userNo);
        user.setUserName(name);
        
        user.setEmpNo(empNo);
        user.setUsrgrpId(group);
        user.setUsrgrpName(groupName);
        user.setCompNo(compNo);
        user.setInitMenuId(initMenuId);
        user.setFileName(initFileName);
        user.setLangId(languageId);
        user.setDeptId(deptId);
        user.setDeptNo(deptNo);
        user.setDeptDesc(deptDesc);
        user.setFilterDeptId(filterDeptId);
        user.setFilterEmpId(filterEmpId);
        user.setFilterEmpDesc(filterEmpDesc);
        user.setFilterDeptDesc(filterDeptDesc);
        user.setEmpId(empId);
        user.setEmpName(empName);
        user.setWcodeId(wcodeId);
        user.setWname(wname);
        user.setTwcodeId(twcodeId);
        user.setTwname(twname);
        user.setFromWcodeId(fromWcodeId);
        user.setFromWname(fromWname);
        user.setToWcodeId(toWcodeId);
        user.setToWname(toWname);
        user.setSecurGrade(securGrade);
        user.setScrnFontSize(scrnFontSize);
        user.setCtPath(ctPath);
        user.setShiftType(shiftType);
        user.setShiftTypeDesc(shiftTypeDesc);
        user.setEqLocId(eqLocId);
        user.setEqLocDesc(eqLocDesc);
        user.setPlant(plant);
        user.setPlantDesc(plantDesc);
        user.setFilterPlant(filterPlant);
        user.setFilterPlantDesc(filterPlantDesc);
        user.setAlarmViewYn(alarmViewYn);
        user.setWkctrId(wkctrId);
        user.setWkctrDesc(wkctrDesc);
        user.setFilterWkCtrId(filterWkCtrId);
        user.setFilterWkCtrDesc(filterWkCtrDesc);
        user.setEqCtgTypeId(eqCtgTypeId);
        user.setEqCtgTypeDesc(eqCtgTypeDesc);
        user.setLangDesc(langDesc);
        user.setMenuDisplay(menuDisplay);
        user.setPageType("dream");
        user.setmPhone(mPhone);
        user.seteMail(eMail);
        user.setFilterWcodeId(filterWcodeId);
        user.setFilterWcodeDesc(filterWcodeDesc);
        
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
        
        session.setAttribute("jsVer", System.currentTimeMillis());
        session.setAttribute("compNo", compNo);
        session.setAttribute("compName", compName);
    }
    
    
    /**
     * Check Concurrent User
     * @param request
     * @return
     */
    private boolean chkCcUserNum(HttpServletRequest request, List userList)
    {
    	LoginService loginService = (LoginService) getBean("loginService");
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");
    	
    	Map hash = (Map)userList.get(0);
    	String[] sysAdmGrpArr = maUsrGrpAuthListService.getAdminUsrGrp(String.valueOf(hash.get("COMPNO")));
    	
    	boolean result = false;
    	int lgnCnt = 0;
    	int userLicenseCount =  5;//������ �߸��Ǿ����� 5������ ����	
    	try{
    		userLicenseCount = Integer.parseInt(MwareConfig.getUserLicenseCnt());
    	}catch(Exception e){
    	    e.printStackTrace();
    	}

    	if("NAMED".equals(MwareConfig.getUserLicenseType())){
        	//Named�̸� �����ڸ� üũ���� ����.
        	try{ 
        		lgnCnt = Integer.parseInt(loginService.countNamedUserCnt());
        		
        		if(lgnCnt > userLicenseCount)
        		{    
            		Map alertMap = new HashMap();
                    alertMap.put("code", "NAMEDOVER");
                    String jsonString = new Gson().toJson(alertMap);
                    
            		request.getSession(true).setAttribute("ALERT", jsonString);
        		}
        		result = true;
        	}catch(Exception e){}
        }else{
    	
	        Hashtable loginUsersTable = BaseAction.getLoginUsers();
	        // Key ����
	        Enumeration loginUsersEm = loginUsersTable.keys();
	        
	        User user = null;
	        Map<String,String> userMap = new HashMap<String,String>();
	        int i = 1;
	        while (loginUsersEm.hasMoreElements())
	        {
	            // User session Id 
	            String loginUserKey = (String)loginUsersEm.nextElement();
	            
	            user = (User)loginUsersTable.get(loginUserKey);
	            
	            //User�� ���� �׷��� ADMIN Group�� ��� CC User ī��Ʈ���� ����
	            for(String admGrpStr : sysAdmGrpArr) if(admGrpStr.equals(user.getUsrgrpName())) userMap.put(user.getUserNo()+user.getCompNo(), user.getUserName());

	            if(!userMap.containsKey(user.getUserNo()+user.getCompNo())) lgnCnt++;
	            
	            userMap.put(user.getUserNo()+user.getCompNo(), user.getUserName());

	        }

	        if(lgnCnt < userLicenseCount) result = true;
        }
    	
        //���� ������ SYSTEM ���� ������ �׳� ���
        String groupName    = String.valueOf(hash.get("USRGRPNAME"));
        for(String admGrpStr : sysAdmGrpArr)
        {
        	if(admGrpStr.equals(groupName)) result= true;
        }
        
        return result;
    }
}