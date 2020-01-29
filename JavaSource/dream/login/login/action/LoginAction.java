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
 * Login 처리 Action
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
    /** 로그인 요청 */
    public static final int USER_FIND = 2001;
    /** 비밀번호 변경 요청 */
    public static final int PW_CHANGE = 2002;
    /** 로그아웃 요청 */
    public static final int LOGOUT = 2004;
    /** 윈도우창이 닫히면서 로그아웃 */
    public static final int UNLOAD = 2005;
    /** 인증을 거치지 않고 로긴 할때 사용된다.(id, password 입력하지 않고 링크로 로긴) */
    public static final int NOT_LOGIN_USER = 9554;
    /** 로긴 하지 않고 모듈을 호출한다. */
    public static final int CALL_SERVICE = 3002;
    /** SSO Login */
    public static final int SSO_USER_FIND = 9001;
    /**
     * 로그인이 되어있을 때 다시 같은 아이디로 로그인을 시도할 경우
     * 메시지창에서 확인(OK)버튼을 눌렀을 경우 실행 된다. 
     */
    public static final int USER_OK_LOGOUT = 2006;
    /** 로그인 하지 않고 해당 페이지로 foward */
    private static final int CALL_PAGE = 3003;
    /** Main Page cancel */
    public static final int CANCEL = 3004;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        // 새로운 session 생성
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

        //Locale 설정
        DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
        dataBaseMessageResources.loadLocale(localeLang);
        
    	// 현재 세션으로 이미 로그인 되어 있는지 여부
    	boolean existsLoginUser = false;

        List userInfoList = null;
        User user = null;
        String userNo = loginForm.getLoginDTO().getUserNo(); //Login Id
        String compNo = loginForm.getLoginDTO().getCompNo(); //Comp No

        
        switch(loginForm.getStrutsAction())
        {
            case LoginAction.CALL_SERVICE :
                // Login 하지 않고 특정 서비스를 이용한다.
                callService(loginForm, request);
                forwadName = "resultService";
                break;
            case LoginAction.NOT_LOGIN_USER :

                // 로그인 유저 정보를 조회한다.
                userInfoList = getUserInfo(request, loginForm, false);
                
                //Config 값과 오늘 날짜 (10자리) 조합하여 검색. 안 맞으면 forwadName = "logout" 
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

                // 로그인 인증과 유저정보를 조회한다.
                userInfoList = getUserInfo(request, loginForm, true);

                if (userInfoList == null) break;
                
            	 Map hash = (Map)userInfoList.get(0);
                 String changeNeed = (String)hash.get("CHANGENEED");
            	
                 //비밀번호 변경이 필요한 상태
                 if("Y".equals(changeNeed))
                 {
                	 forwadName = "mainPwChange";
                	 break;
                 }
                // 사용중이 아니라면 login 처리한다.
                setTimeGap(session, loginForm.getLoginDTO().getLocalTime());
                setSession(session, loginForm.getLoginDTO().getLocale(), userInfoList);
                forwadName = "findUser";
                break;

            case USER_OK_LOGOUT:
                userInfoList = getUserInfo(request, loginForm, false);

                if (userInfoList == null) break;
                //confirm에서 사용자가 선택에 따라서 실행이 된다.
                if (userInfoList.size() > 0)
                {
                    setTimeGap(session, loginForm.getLoginDTO().getLocalTime());
                    setSession(session, loginForm.getLoginDTO().getLocale(), userInfoList);
                    forwadName = "findUser";
   
                    break;
                }
                //Login ID가 없을 때 실행된다. 
                else
                {
                    // 등록된 User가 아닙니다.
                    request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0003"));
                }
                break;
            case PW_CHANGE:
            	// 로그인 인증과 유저정보를 조회한다.
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

                // 사용중이 아니라면 login 처리한다.
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
                    // 현재 login user 로그인 처리한다.
                    setSession(session, configService.findLanguage(), userInfoList);

                    return callPage(request);
                }
                
                //========================================================================================
                // 인사DB에 등록 사용자가 아닙니다. \\n 관리자에게 문의 바랍니다.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0008"));
                //========================================================================================
                
                break;
            case CANCEL:
            	forwadName = "index";
            	break;
            case LoginAction.SSO_USER_FIND :
				user = new User();
				
            	// SSO Login을 사용하는 회사인지 체크
            	if (!"Y".equals(MwareConfig.getIsUseSsoLogin())) {
            		// SSO Login을 사용하지 않는 회사입니다.
//            		request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0264"));
            		forwadName = "index";
                	break;
				}
            	
            	String ssoStr = "";
            	String ssoKey = MwareConfig.getSsoAesKey();
            	
            	// 사이트주소/index.do?strutsAction=9001&ssoStr=암호화된문자
            	if ("".equals(request.getParameter("ssoStr")) || request.getParameter("ssoStr") == null) {
            		forwadName = "index";
                	break;
				} else{
					ssoStr = request.getParameter("ssoStr");
				}
            	
            	try {
            		// 복호화 (ex userNo=12312&today=20190116)
            		String decodeStr = AES256Cipher.getInstance(ssoKey).AES_Decode(ssoStr).trim();
            		String[] a = decodeStr.split("&");
            		String decodeUserNo = a[0].replaceFirst("userNo=", "");
            		String decodeToday = a[1].replaceFirst("today=", "");
            		
            		// 오늘날짜로 생성된 암호인지 체크
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
            	
            	
            	// 1. 각 사이트별 서비스를 타기위해 필요한 값 셋팅 (userNo로 comp_no 구해오기)
				//userNo로 compNo 검색
				List compList = loginService.findCompInfo(loginDTO);
				if (compList.size() > 1) {
                	// 해당 계정의 회사가 2곳 이상입니다.
                	request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0262"));
                	forwadName = "index";
                	break;
				} else if (compList.size() == 0){
                	// 해당 시스템에 로그인계정이 없습니다.
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
                
            	// 2. 각 사이트별 확장서비스로 sso로 로그인을 시켜줘야하는지 판별한다.
                SsoService ssoService = (SsoService) getBean("ssoService", request);
            	boolean ssoChk = ssoService.checkSso(request, ssoDTO, user);
            	if (ssoChk) {
            		// 로그인 유저 정보를 조회한다.
            		userInfoList = getUserInfo(request, loginForm, false);
				
	                if (userInfoList.size() > 0)
	                {
	                    setSession(session,  configService.findLanguage(), userInfoList);
	                    forwadName = "findUser";
	                    break;
	                }
            	}
            	
            	// SSO 로그인을 사용하는데, 우리 시스템을 사용하지 않는 유저면 index화면을 보여준다.
        		forwadName = "index";
                break;
                
            default:
                /*
                 * 현재 session ID로 login Users 해쉬테이블에 값이 존재하는지를 체크한다. 
                 * 해쉬테이블에 존재한다면 Main 화면을 보여주고
                 * 존재하지 않는 다면 index(로그인)화면을 보여준다.
                 * 로그인을 한 후 같은 브라우저로 다른 웹사이트로 이동을 하였다가 주소창에 주소를 입력하고  들어가면 
                 * 로그인 없이 바로 이동할 수 있도록 한다.
                 */
                existsLoginUser = existsLoginUser(request);
                if (existsLoginUser)
                {
                    forwadName = "findUser";
                    break;
                }
            
                // 다시 로긴해야하는경우 그전의 session 을 invalidate 한다.
                loginService.unBound(session);
                
                
                setLocale(request, new Locale(configService.findLanguage()));
                
                break;
        }
       
        // index.jsp 화면으로 보여질때 
        if ("index".equals(forwadName))
        {
            //=================================
            // index page Select box(language)
//            setPageSelect(request, loginForm);
            //=================================
        }
        
        // LOG IN 성공
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
            // 키, 코드, 시간으로 일회용 비밀번호가 맞는지 일치 여부 확인.
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
     * 로그인 성공
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
        
        // 로그인 이력을 남긴다.
        loginService.insertLogHist(loginUser, request);
        // 로그인 시도 이력을 남긴다.
        loginService.insertLoginTryLog(loginDTO, request, "Y");
    }

    /**
     * User 정보를 조회하고 인증한다.
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
        
        List userInfoList = loginService.findUserInfo(loginDTO);     // user 정보 조회

        // User가 없는 경우
        if(userInfoList == null || userInfoList.size() <= 0)
        {
            // 로그인 시도 이력을 남긴다.
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
        
        // true 인경우 password 를 비교하여 로그인 인증한다.
        if (valid)
        {
        	//암호 복호화
            String password = loginForm.getLoginDTO().getPassWord();
            //===============================================================
            // Encrypt Password
            String passwordEncrypt = CommonUtil.aesEncodeString(password);
            //===============================================================

            if("Y".equals(isLocked) && !"0".equals(MwareConfig.getPwFailLimitCnt()))
            {
            	// 로그인 시도 이력을 남긴다.
            	loginService.insertLoginTryLog(loginDTO, request, "N");
            	
            	Map alertMap = new HashMap();
                alertMap.put("code", "LOCKUSER");
                String jsonString = new Gson().toJson(alertMap);
                request.setAttribute("ALERT", jsonString);
                
            	return null;
            }
            
            //OTP 입력이 필요한 상태
            if("Y".equals(MwareConfig.getIsUseOtpLogin()))
            {
                // OTP가 맞는지 체크한다.
                boolean _valid = checkOtpCode(userInfoList, loginDTO.getOtp());
                if(!_valid)
                {
                    // 로그인 시도 이력을 남긴다.
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
                // 로그인 시도 이력을 남긴다.
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
         
            //changePwDate 가 널인경우 Login 처리한다
            if(changePwdDate!=""&&changePwdDate!=null)
            {
            	interval =DateUtil.getDayInterval(changePwdDate,DateUtil.getDate());
            }
            else
            {
            	interval=0;
            }
            	
            
            //Change Pw 가 Y 이며 변경한 날짜가 PW 변경주기보다 오래 되었다면 PW 변경 페이지를 로딩 한다.  데이터 없을떄 에러날수 있음.. 7.10
            if(("Y").equals(MwareConfig.getIsChangePwNeeded()) && interval>Integer.parseInt(MwareConfig.getPwChangeCycleDay()))
            {
            	hash.put("CHANGENEED", "Y");            	
/*            	System.out.println((String)hash.get("CHANGENEED")+"체인지");
            	System.out.println("메인페이지 소환");*/
            	
            }
            else
            {
            	hash.put("CHANGENEED", "N");  
            }


            if(loginService.isUsing(loginDTO.getUserNo(), loginDTO.getCompNo() )) loginService.isForceLogOut(loginDTO.getUserNo(), loginDTO.getCompNo(), request.getSession(true).getId());
            if(!chkCcUserNum(request, userInfoList))
            {
                loginService.unBound(request.getSession(true));
                
                // 로그인 시도 이력을 남긴다.
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
                
                // 로그인 시도 이력을 남긴다.
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
     * 해당 Page로 forward
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
        
        // 작업요청 상세
        if ("eqMstrDetail".equals(pageId))
        {
            int eqMstrDetailAction = 0;
            String equipNo = request.getParameter("EQUIP_NO");
            
            param = param + "&strutsAction=" + eqMstrDetailAction +
                            "&eqMstrCommonDTO.equipNo=" + equipNo;
        }
        // 작업요청 상세
        else if ("woReqMstrDetail".equals(pageId))
        {
            int worMesDetailAction = 0;
            
            param = param + "&strutsAction=" + worMesDetailAction;
        }
        // 작업결과 상세
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
     * Login 하지 않고, 특정 Service 를 호출한다.
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
             * 호출할 서비스를 기술한다.
             * wfType   : 문서 종류 (WO:작업지시서, PR:구매요청서, PO:구매요구서 등)
             * stepNum  : 결재 순번
             * authoNo  : 결재 문서 번호
             * authoBy  : 결재자
             * wfStatus : 결재(C:승인, B:전결, D:반려, E:취소)
            String wfType   = request.getParameter("WF_TYPE");
            String stepNum  = request.getParameter("STEP_NUM");
            String authoNo  = request.getParameter("AUTHO_NO");
            String authoBy  = loginForm.getLoginDTO().getUserID();
            String wfStatus = request.getParameter("WF_STATUS");
            
            int result = commonAppService.callAppService(wfType, stepNum, authoNo, authoBy, wfStatus);
            
            // 정상 처리
            if (result == 1)
            {
                request.setAttribute("RESULT_MSG", "처리 완료 되었습니다.");                
            }
            else
            {
                request.setAttribute("RESULT_MSG", "결재 처리가 정상 완료 되지 않았습니다.");    
            }
        }
        else
        {
            request.setAttribute("RESULT_MSG", "호출된 서비스를 찾을수 없습니다.");            
        }
        */        
    }

    /**
     * 현재 session ID로 login Users 해쉬테이블에 키 값이 존재하는지를  체크한다. 
     * @author  mentor
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return true  : 있는 경우
     *         false : 없는 경우
     * @throws Exception
     */
    private boolean existsLoginUser(HttpServletRequest request) throws Exception
    {
        boolean result = false;
        
        String sessionId = request.getSession().getId(); //현재 session id 값을 얻어온다. 

        Enumeration e = BaseAction.getLoginUsers().keys();
        String key = "";
        while(e.hasMoreElements())
        {
            key = (String)e.nextElement();
            
            // Session Id가 있는지 비교한다.
            if (sessionId.equals(key)) //해쉬 테리블에 키 값이 존재하는지를 비교한다. 
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
     * Session 을 User 객체에 저장
     * @param session 세션 객체
     * @param l 
     * @param userID 사용자 아이디
     */
    public void setSession(HttpSession session, String lang, List userList)
    {
        /*
         * Session정보를 담는 객체
         */
        User user = new User();
        
        Map hash = (Map)userList.get(0);
        
        //여러 페이지JSP에서 에러 발생해서 주석처리 
//        for(Object obj:hash.keySet()){
//            String key = (String) obj;
//            Object value = hash.get(key);
//            if(value == null){
//                hash.put(key, "");
//            }
//        }

//        String id           = hash.get("USERID")==null?"":String.valueOf(hash.get("USERID")); //사용자 ID (Login)
//        String userNo       = hash.get("USERNO")==null?"":String.valueOf(hash.get("USERNO")); //사용자 번호 
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
//        String shiftType         = hash.get("SHIFTTYPE")==null?"":String.valueOf(hash.get("SHIFTTYPE")); //교대조
//        String shiftTypeDesc     = hash.get("SHIFTTYPEDESC")==null?"":String.valueOf(hash.get("SHIFTTYPEDESC")); //교대조명
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

        String id           = String.valueOf(hash.get("USERID")); //사용자 ID (Login)
        String userNo       = String.valueOf(hash.get("USERNO")); //사용자 번호 
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
        String shiftType         = String.valueOf(hash.get("SHIFTTYPE")); //교대조
        String shiftTypeDesc     = String.valueOf(hash.get("SHIFTTYPEDESC")); //교대조명
        
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
        // 언어 설정
        Locale locale = new Locale(language);
        user.setLocale(locale);
        session.setAttribute(Globals.LOCALE_KEY, locale);
        //=================================================
        
        // 현재 로그인한 User 객체를 저장한다.
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
    	int userLicenseCount =  5;//설정이 잘못되었으면 5명으로 셋팅	
    	try{
    		userLicenseCount = Integer.parseInt(MwareConfig.getUserLicenseCnt());
    	}catch(Exception e){
    	    e.printStackTrace();
    	}

    	if("NAMED".equals(MwareConfig.getUserLicenseType())){
        	//Named이면 접속자를 체크하지 않음.
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
	        // Key 추출
	        Enumeration loginUsersEm = loginUsersTable.keys();
	        
	        User user = null;
	        Map<String,String> userMap = new HashMap<String,String>();
	        int i = 1;
	        while (loginUsersEm.hasMoreElements())
	        {
	            // User session Id 
	            String loginUserKey = (String)loginUsersEm.nextElement();
	            
	            user = (User)loginUsersTable.get(loginUserKey);
	            
	            //User의 권한 그룹이 ADMIN Group인 경우 CC User 카운트에서 제외
	            for(String admGrpStr : sysAdmGrpArr) if(admGrpStr.equals(user.getUsrgrpName())) userMap.put(user.getUserNo()+user.getCompNo(), user.getUserName());

	            if(!userMap.containsKey(user.getUserNo()+user.getCompNo())) lgnCnt++;
	            
	            userMap.put(user.getUserNo()+user.getCompNo(), user.getUserName());

	        }

	        if(lgnCnt < userLicenseCount) result = true;
        }
    	
        //현재 유저가 SYSTEM 권한 유저면 그냥 통과
        String groupName    = String.valueOf(hash.get("USRGRPNAME"));
        for(String admGrpStr : sysAdmGrpArr)
        {
        	if(admGrpStr.equals(groupName)) result= true;
        }
        
        return result;
    }
}