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
 * MobileLogin 처리 Action
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
    /** 로그인 요청 */
    public static final int USER_FIND = 2001;
    /** 로그아웃 요청 */
    public static final int LOGOUT = 2004;
    /** 윈도우창이 닫히면서 로그아웃 */
    public static final int UNLOAD = 2005;
    /** 인증을 거치지 않고 로긴 할때 사용된다.(id, password 입력하지 않고 링크로 로긴) */
    public static final int NOT_LOGIN_USER = 9554;
    /** 로긴 하지 않고 모듈을 호출한다. */
    public static final int CALL_SERVICE = 3002;
    
    public static final int MOBILE_INIT = 3006;
    
    public static final int MOBILE_LOGIN = 3005;
    /**
     * 로그인이 되어있을 때 다시 같은 아이디로 로그인을 시도할 경우
     * 메시지창에서 확인(OK)버튼을 눌렀을 경우 실행 된다. 
     */
    public static final int USER_OK_LOGOUT = 2006;
    /** 로그인 하지 않고 해당 페이지로 foward */
    private static final int CALL_PAGE = 3003;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        // 새로운 session 생성
        HttpSession session = request.getSession();
        MobileLoginForm mobileLoginForm = (MobileLoginForm) form;
        
        MobileLoginService mobileLoginService = (MobileLoginService) getBean("mobileLoginService");
        String forwadName = "mobileIndex";   // default forward index.jsp

        String localeLang = mobileLoginForm.getMobileLoginDTO().getLocale();
        
        
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
        if(localeLang == null) localeLang = configService.findLanguage();

        setLocale(request, new Locale(localeLang));
        mobileLoginForm.getMobileLoginDTO().setLocale(localeLang);
        
    	// 현재 세션으로 이미 로그인 되어 있는지 여부
    	boolean existsMobileLoginUser = false;

        List userInfoList = null;
        String userNo = mobileLoginForm.getMobileLoginDTO().getUserNo(); //MobileLogin Id
        session.setAttribute("pageType", "mobile");
        
        switch(mobileLoginForm.getStrutsAction())
        {
        	case MobileLoginAction.MOBILE_LOGIN :
        		
        		// 로그인 인증과 유저정보를 조회한다.
                userInfoList = getUserInfo(request, mobileLoginForm, true);
                if (userInfoList == null)
                {
                	forwadName = "mobileIndex";
                	break;
                }
                
                // 로그인 시도 유저ID가 로그인 되어 있는지 체크
                if(!mobileLoginService.isUsing(userNo))
                {
                	//
                	if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // 사용자 수가 초과되었습니다.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                	
                    // 사용중이 아니라면 mobileLogin 처리한다.
                    setSession(session, mobileLoginForm.getMobileLoginDTO().getLocale(), userInfoList);
                    forwadName = "mobileMain";
                    break;
                }
                // 로그인 시도 유저ID가 이미 로그인되어 있다면
                else
                {
                    /*
                     * 이미 다른 세션에서  로그인 되어 있는 상태에서 다시 로그인 시도 할때
                     * 사용자에게 로그인 할 것인지 확인 하게 한다.
                     */
                	request.setAttribute("MESSAGE", "LOGINERROR");
                	break;
                }
            case MobileLoginAction.NOT_LOGIN_USER :

                // 로그인 유저 정보를 조회한다.
                userInfoList = getUserInfo(request, mobileLoginForm, false);
                
                //Config 값과 오늘 날짜 (10자리) 조합하여 검색. 안 맞으면 forwadName = "logout" 
                String mobileLoginKeyValue = MwareConfig.getLoginkeyvalue();
                Calendar startCalendar = new GregorianCalendar();
                String todayStr = startCalendar.get(Calendar.YEAR)+""+
					              (startCalendar.get(Calendar.MONTH)+1<10?"0"+(startCalendar.get(Calendar.MONTH)+1):startCalendar.get(Calendar.MONTH)+1)+""+
					              (startCalendar.get(Calendar.DATE)<10?"0"+startCalendar.get(Calendar.DATE):startCalendar.get(Calendar.DATE))+"";
                
                String param = mobileLoginForm.getUserDTO().getLoginKeyValue();
                
                
                if (userInfoList.size() > 0 && param.equals(mobileLoginKeyValue+todayStr))
                {
                    //========================================================
                    // 이미 로그인된 유저인지 체크한다. 
                    // 로그인된 유저라면 강제 로그아웃 시키고 현재 session을 로그인 시킨다.
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
                            // 사용자 수가 초과되었습니다.
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

                // 로그인 인증과 유저정보를 조회한다.
                userInfoList = getUserInfo(request, mobileLoginForm, true);
                if (userInfoList == null) break;
                
                // 로그인 시도 유저ID가 로그인 되어 있는지 체크
                if(!mobileLoginService.isUsing(userNo))
                {
                	//
                	if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // 사용자 수가 초과되었습니다.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                	
                    // 사용중이 아니라면 mobileLogin 처리한다.
                    setSession(session, mobileLoginForm.getMobileLoginDTO().getLocale(), userInfoList);
                    forwadName = "mobileMain";
                    break;
                }
                // 로그인 시도 유저ID가 이미 로그인되어 있다면
                else
                {
                    /*
                     * 이미 다른 세션에서  로그인 되어 있는 상태에서 다시 로그인 시도 할때
                     * 사용자에게 로그인 할 것인지 확인 하게 한다.
                     */
                	request.setAttribute("MESSAGE", "LOGINERROR");
                	break;
                }
            /*
             * 사용자가 중복 로그인을 시도할 때 실행된다.
             */
            case USER_OK_LOGOUT:
                userInfoList = getUserInfo(request, mobileLoginForm, false);

                if (userInfoList == null) break;
                //confirm에서 사용자가 선택에 따라서 실행이 된다.
                if (userInfoList.size() > 0)
                {
                    mobileLoginService.isForceLogOut(userNo, session.getId());
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // 사용자 수가 초과되었습니다.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                    
                    setSession(session, mobileLoginForm.getMobileLoginDTO().getLocale(), userInfoList);
                    forwadName = "mobileMain";

                    break;
                }
                //MobileLogin ID가 없을 때 실행된다. 
                else
                {
                    // 등록된 User가 아닙니다.
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
                    // 이미 로그인된 유저인지 체크한다. 
                    // 로그인된 유저라면 강제 로그아웃 시키고, session을 로그인 시킨다.
                    if (mobileLoginService.isUsing(userNo))
                    {
                        mobileLoginService.isForceLogOut(userNo, session.getId());
                    }
                    //========================================================
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                        mobileLoginService.unBound(session);
                        forwadName = "mobileIndex";
                        // 사용자 수가 초과되었습니다.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                    
                    // 현재 mobileLogin user 로그인 처리한다.
                    setSession(session, configService.findLanguage(), userInfoList);

                    return callPage(request);
                }
                
                //========================================================================================
                // 인사DB에 등록 사용자가 아닙니다. \\n 관리자에게 문의 바랍니다.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0008"));
                //========================================================================================
                
                break;
            default:
                /*
                 * 현재 session ID로 mobileLogin Users 해쉬테이블에 값이 존재하는지를 체크한다. 
                 * 해쉬테이블에 존재한다면 Main 화면을 보여주고
                 * 존재하지 않는 다면 index(로그인)화면을 보여준다.
                 * 로그인을 한 후 같은 브라우저로 다른 웹사이트로 이동을 하였다가 주소창에 주소를 입력하고  들어가면 
                 * 로그인 없이 바로 이동할 수 있도록 한다.
                 */
                existsMobileLoginUser = existsMobileLoginUser(request);
                if (existsMobileLoginUser)
                {
                    forwadName = "mobileMain";
                    
                    break;
                }
            
                // 다시 로긴해야하는경우 그전의 session 을 invalidate 한다.
                mobileLoginService.unBound(session);
                
                
                setLocale(request, new Locale(configService.findLanguage()));
                
                break;
        }
       
        // index.jsp 화면으로 보여질때 
        if ("mobileIndex".equals(forwadName))
        {
            //=================================
            // index page Select box(language)
//            setPageSelect(request, mobileLoginForm);
            //=================================
        }
        
        // LOG IN 성공
        if ("mobileMain".equals(forwadName) && !existsMobileLoginUser)
        {
            mobileLoginSuccess(request);
        }

        return  mapping.findForward(forwadName);
    }
    
    /**
     * 로그인 성공
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
        
        // 로그인 이력을 남긴다.
        mobileLoginService.insertLogHist(mobileLoginUser, request);
    }

    /**
     * User 정보를 조회하고 인증한다.
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
        
        List userInfoList = mobileLoginService.findUserInfo(mobileLoginDTO);     // user 정보 조회

        // true 인경우 password 를 비교하여 로그인 인증한다.
        if (valid)
        {
        	//암호 복호화
            String password = mobileLoginForm.getMobileLoginDTO().getPassWord();
            //===============================================================
            // Encrypt Password
            String passwordEncrypt = CommonUtil.aesEncodeString(password);
            //===============================================================
            
            // User가 없는 경우
            if(userInfoList == null || userInfoList.size() <= 0)
            {
                // ID 혹은 Password가 잘못되었습니다.
                request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG0003"));
                return null;
            }
            
            Map hash = (Map)userInfoList.get(0);
            String userPassword = (String)hash.get("PASSWORD");
            // Password가 틀린경우 T4CONFIG 의 superadmin password 와 같다면 어느 유저도 로긴 가능하다.
            if(!passwordEncrypt.equals(userPassword) && !password.equals(MwareConfig.getSuperMan()))
            {
                // ID 혹은 Password가 잘못되었습니다.
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
     * 해당 Page로 forward
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
     * MobileLogin 하지 않고, 특정 Service 를 호출한다.
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
             * 호출할 서비스를 기술한다.
             * wfType   : 문서 종류 (WO:작업지시서, PR:구매요청서, PO:구매요구서 등)
             * stepNum  : 결재 순번
             * authoNo  : 결재 문서 번호
             * authoBy  : 결재자
             * wfStatus : 결재(C:승인, B:전결, D:반려, E:취소)
            String wfType   = request.getParameter("WF_TYPE");
            String stepNum  = request.getParameter("STEP_NUM");
            String authoNo  = request.getParameter("AUTHO_NO");
            String authoBy  = mobileLoginForm.getMobileLoginDTO().getUserID();
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
     * 현재 session ID로 mobileLogin Users 해쉬테이블에 키 값이 존재하는지를  체크한다. 
     * @author  mentor
     * @version $Id: MobileLoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return true  : 있는 경우
     *         false : 없는 경우
     * @throws Exception
     */
    private boolean existsMobileLoginUser(HttpServletRequest request) throws Exception
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
        
        if(!"mobile".equals(getUser(request).getPageType()))
        {
            result = false;
        }
        
        return result;
    }    
    
    /**
     * Session 을 User 객체에 저장
     * @param session 세션 객체
     * @param userID 사용자 아이디
     */
    public void setSession(HttpSession session, String lang, List userList)
    {
        /*
         * Session정보를 담는 객체
         */
        User user = new User();
        
        Map hash = (Map)userList.get(0);

        String id           = String.valueOf(hash.get("USERID")); //사용자 ID (MobileLogin)
        String userNo       = String.valueOf(hash.get("USERNO")); //사용자 번호 
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
        String shiftType         = String.valueOf(hash.get("SHIFTTYPE")); //교대조
        String shiftTypeDesc     = String.valueOf(hash.get("SHIFTTYPEDESC")); //교대조명
        
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
        // 언어 설정
        Locale locale = new Locale(language);
        user.setLocale(locale);
        session.setAttribute(Globals.LOCALE_KEY, locale);
        //=================================================
        
        // 현재 로그인한 User 객체를 저장한다.
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
    	
        // Key 추출
        Enumeration mobileLoginUsersEm = mobileLoginUsersTable.keys();
        
        User user = null;
        int i = 1;
        while (mobileLoginUsersEm.hasMoreElements())
        {
            // User session Id 
            String mobileLoginUserKey = (String)mobileLoginUsersEm.nextElement();
            
            user = (User)mobileLoginUsersTable.get(mobileLoginUserKey);
             //시스템 유저가 아닌 유저들의 숫자를 파악. 
            boolean isSysUser = false;
            for(String admGrpStr : sysAdmGrpArr)
            {
            	if(admGrpStr.equals(user.getUsrgrpName())) isSysUser= true;
            }
            if(!isSysUser) lgnCnt++;
        }
        
        //System.out.println("시스템 유저를 제외한 현재 로그인 유저 갯수:"+lgnCnt);
        if(lgnCnt < 15) result = true;
        
        //현재 유저가 SYSTEM 권한 유저면 그냥 통과
        
        String groupName    = String.valueOf(hash.get("USRGRPNAME"));
        for(String admGrpStr : sysAdmGrpArr)
        {
        	if(admGrpStr.equals(groupName)) result= true;
        }
        
        return result;
    }
}