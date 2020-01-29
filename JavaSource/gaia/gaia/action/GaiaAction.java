package gaia.gaia.action;
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
import dream.mgr.usrgrp.service.MaUsrGrpAuthListService;
import gaia.gaia.dto.GaiaDTO;
import gaia.gaia.form.GaiaForm;
import gaia.gaia.service.GaiaService;

/**
 * Login 처리 Action
 * @author  javaworker
 * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
 * @since   1.0
 * @struts.action path="/gaia" name="gaiaForm"
 *                input="/gaia/gaia.jsp" scope="request"
 *                validate="false" 
 * @struts.action-forward name="findUser"
 *                        path="/gaPgMngList.do" redirect="true"
 * @struts.action-forward name="index"
 *                        path="/gaia/gaia.jsp" redirect="false"
 * @struts.action-forward name="logout"
 *                        path="/gaia/logout.jsp" redirect="false"   
 */
public class GaiaAction extends NoneAuthAction
{
    /** 로그인 요청 */
    public static final int USER_FIND = 2001;
    /** 로그아웃 요청 */
    public static final int LOGOUT = 2004;
    /** 윈도우창이 닫히면서 로그아웃 */
    public static final int UNLOAD = 2005;
    /**
     * 로그인이 되어있을 때 다시 같은 아이디로 로그인을 시도할 경우
     * 메시지창에서 확인(OK)버튼을 눌렀을 경우 실행 된다. 
     */
    public static final int USER_OK_LOGOUT = 2006;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        GaiaForm gaiaForm = (GaiaForm) form;
        String forwadName = "index";   // default forward index.jsp
        
        // 현재 세션으로 이미 로그인 되어 있는지 여부
        boolean existsLoginUser = false;
        GaiaService gaiaService = (GaiaService) getBean("gaiaService");
        HttpSession session = request.getSession();
        session.setAttribute("pageType", "gaia");
        
        switch(gaiaForm.getStrutsAction())
        {
            case USER_FIND:
                // 새로운 session 생성
                
                ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");
                
                String localeLang = gaiaForm.getGaiaDTO().getLocale();
                //if(localeLang == null) localeLang = configService.findLanguage();
                if(localeLang == null) localeLang = "ko";
                
                Locale local = new Locale(localeLang);
                
                setLocale(request, local);
                gaiaForm.getGaiaDTO().setLocale(localeLang);
                
                
                String userNo = gaiaForm.getGaiaDTO().getUserNo(); //Login Id
                List userInfoList = getUserInfo(request, gaiaForm, true);

                if (userInfoList == null) break; //유저정보가 없으면 다시 index 페이지.
                
                // 로그인 시도 유저ID가 로그인 되어 있는지 체크
                if(!gaiaService.isUsing(userNo))
                {
                	//Concurrent User Check
                	if(!chkCcUserNum(request, userInfoList))
                	{
                	    gaiaService.unBound(session);
                        forwadName = "index";
                        // 사용자 수가 초과되었습니다.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                	
                    // 사용중이 아니라면 login 처리한다.
                    setSession(session, request, localeLang, userInfoList);
                    
                    forwadName = "findUser";
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
                userInfoList = getUserInfo(request, gaiaForm, false);

                if (userInfoList == null) break;
                //confirm에서 사용자가 선택에 따라서 실행이 된다.
                if (userInfoList.size() > 0)
                {
                    userNo = gaiaForm.getGaiaDTO().getUserNo(); //Login Id
                    gaiaService.isForceLogOut(userNo, session.getId());
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                        gaiaService.unBound(session);
                        forwadName = "index";
                        // 사용자 수가 초과되었습니다.
                        request.setAttribute("MESSAGE", getMessage(request, "MESSAGE.MSG1000"));
                        break;
                	}
                    
                    setSession(session, request, gaiaForm.getGaiaDTO().getLocale(), userInfoList);
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
            case LOGOUT:
                gaiaService.unBound(session);
                configService = (ConfigService) BaseAction.ctx.getBean("configService");
                setLocale(request, new Locale("ko"));
                break;
            case UNLOAD:
                gaiaService.unBound(session);
                forwadName = "logout";
                break;
            default:
                
                existsLoginUser = existsLoginUser(request);
                if (existsLoginUser)
                {
                    forwadName = "findUser";
                    break;
                }
                // 다시 로긴해야하는경우 그전의 session 을 invalidate 한다.
                gaiaService.unBound(session);
                forwadName ="index";
                
                break;
        }
        
        // LOG IN 성공
        if ("findUser".equals(forwadName) && !existsLoginUser)
        {
            loginSuccess(request);
        }

//        ActionForward forward = new ActionForward("/gaPgMngList.do");
//
//        return forward;
//        
        return  mapping.findForward(forwadName);
    }

    
    private void loginSuccess(HttpServletRequest request)
    {
        GaiaService gaiaService = (GaiaService) getBean("gaiaService");
        
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(request.getSession().getId());
        
        // 로그인 이력을 남긴다.
        gaiaService.insertLogHist(loginUser, request);
    }
    /**
     * User 정보를 조회하고 인증한다.
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @param gaiaForm
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
    private List getUserInfo(HttpServletRequest request, GaiaForm gaiaForm, boolean valid) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    { 
        GaiaService gaiaService = (GaiaService) getBean("gaiaService");
        GaiaDTO gaiaDTO = gaiaForm.getGaiaDTO();

        if("".equals(gaiaDTO.getUserNo()))
        {
        	User userDTO = gaiaForm.getUserDTO();
        	gaiaDTO.setUserNo(userDTO.getUserNo());
        	gaiaDTO.setLocale(userDTO.getLang());
        	gaiaDTO.setCompNo(userDTO.getCompNo());
        }
        
        List userInfoList = gaiaService.findUserInfo(gaiaDTO);     // user 정보 조회

        // true 인경우 password 를 비교하여 로그인 인증한다.
        if (valid)
        {
        	//암호 복호화
            String password = gaiaForm.getGaiaDTO().getPassWord();
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

            return userInfoList;
        }
        else
        {       	
            String validation = gaiaDTO.getValid();
        	if(!validation.equals("valid")) return null;
            return userInfoList;
        }
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
        
        if(!"gaia".equals(getUser(request).getPageType()))
        {
            result = false;
        }
        
        return result;
    }   
    
    /**
     * Session 을 User 객체에 저장
     * @param session 세션 객체
     * @param request 
     * @param userID 사용자 아이디
     */
    private ActionForward setSession(HttpSession session, HttpServletRequest request, String lang, List userList)
    {
        /*
         * Session정보를 담는 객체
         */
        User user = new User();
        
        Map hash = (Map)userList.get(0);

        String id           = String.valueOf(hash.get("USERID")); //사용자 ID (Login)
        String userNo       = String.valueOf(hash.get("USERNO")); //사용자 번호 
        String name         = (String)hash.get("USERNAME");
        String language     = "ko";

        user.setUserId(id);
        user.setUserNo(userNo);
        user.setUserName(name);
        user.setLangId(language);
        user.setPageType("gaia");
        
        //================================================
        // 언어 설정
        Locale locale = new Locale(language);
        user.setLocale(locale);
        //=================================================
        
        // 현재 로그인한 User 객체를 저장한다.
        Hashtable loginUsers = BaseAction.getLoginUsers();
        loginUsers.put(session.getId(), user);
        BaseAction.setLoginUsers(loginUsers);

        session.setAttribute(session.getId(), user);
        session.setAttribute("ctPath", MwareConfig.getInitCtPath());
        
        ActionForward forwardName = setMainProperty(request);
        
        return forwardName;
    }
    
    
    private ActionForward setMainProperty(HttpServletRequest request)
    {
        GaiaService gaiaService = (GaiaService) getBean("gaiaService");
       
       HttpSession session = request.getSession();
       String jsonString = null;
       User loginUser = getUser(request);   

       String fName = "";
       
       //================================================================
       // 현재 사용자 그룹의 권한에 해당하는 Menu를 session에 셋팅한다.
       if (session.getAttribute("MENU") == null)
       {
           ArrayList menuList = gaiaService.findGaiaMenuList(loginUser);

           session.setAttribute("MENU", menuList);
       }
       
       //================================================================
       // 현재 사용자 Field 정보를 session에 셋팅한다.
       if (session.getAttribute("PAGEFIELD") == null)
       {
           Hashtable pageFields = gaiaService.findPageFields(loginUser);
           session.setAttribute("GAIAPAGEFIELD", pageFields);
       }
       
       //================================================================
       //Locale 설정
       DataBaseMessageResources dataBaseMessageResources = (DataBaseMessageResources)getResources(request);
       dataBaseMessageResources.loadLocale(getUser(request).getLangId()); 
       //================================================================
       
       //================================
       // 불필요한 session을 invalid 시킨다.
       SessionTable.arrangeSesstion();
       //================================ 
       
       //Forwarding Page 권한 확인
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
    	MaUsrGrpAuthListService maUsrGrpAuthListService = (MaUsrGrpAuthListService) getBean("maUsrGrpAuthListService");
    	
    	Map hash = (Map)userList.get(0);
    	String[] sysAdmGrpArr = maUsrGrpAuthListService.getAdminUsrGrp(String.valueOf(hash.get("COMPNO")));
    	
    	boolean result = false;
    	int lgnCnt = 0;
        Hashtable loginUsersTable = BaseAction.getLoginUsers();
        // Key 추출
        Enumeration loginUsersEm = loginUsersTable.keys();
        
        User user = null;
        int i = 1;
        while (loginUsersEm.hasMoreElements())
        {
            // User session Id 
            String loginUserKey = (String)loginUsersEm.nextElement();
            
            user = (User)loginUsersTable.get(loginUserKey);
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