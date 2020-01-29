package intf.dream.android.online.login.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.bean.MwareConfig;
import common.bean.User;
import common.struts.BaseAction;
import common.struts.NoneAuthAction;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.mgr.usrgrp.service.MaUsrGrpAuthListService;
import intf.dream.android.online.login.form.AnOnLoginForm;
import intf.dream.android.online.login.service.AnOnLoginService;

/**
 * Android Login 처리
 * @author  kim21017
 * @version $Id: AnOnLoginAction.java,v 1.0 2017/07/31 00:51:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/anOnLogin" name="anOnLoginForm"
 *                input="/android/online/login/anOnLogin.jsp" scope="request"
 *                validate="false"
 */
public class AnOnLoginAction
        extends NoneAuthAction
{
    /** 로그인 요청 */
    public static final String LOGIN				= "LOGIN";
    /** 로그아웃 요청 */
    public static final String LOGOUT 				= "LOGOUT";
    /** 재로그인 요청 */
    public static final String USER_OK_LOGOUT 		= "USER_OK_LOGOUT";

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        // 새로운 session 생성
        HttpSession session = request.getSession();
        AnOnLoginForm anOnLoginForm = (AnOnLoginForm) form;
        Map map = getMapData(request);
        
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        
    	// 현재 세션으로 이미 로그인 되어 있는지 여부
    	boolean existsLoginUser = false;

        List userInfoList = null;
        List deviceInfoList = null;
        String userNo = String.valueOf(map.get("userNo"));

        
        switch(anOnLoginForm.getServiceName())
        {
            case LOGIN:
                // 로그인 인증과 유저정보를 조회한다.
                userInfoList = getUserInfo(request, map, true);
                deviceInfoList = getDeviceInfo(request, map);

                if(deviceInfoList == null || deviceInfoList.size()<1){
                	//DEVICE ID CHECK
//            		setMessage(response, "","DEVICE_ID_ERROR");
            		makeJsonResult(userInfoList, request, response,"DEVICE_ID_ERROR");
                	break;
                }
                
                if (userInfoList == null|| userInfoList.size()<1){
                	//ID, PASSWORD 체크
//            		setMessage(response, "","CHECK_ID");
            		makeJsonResult(userInfoList, request, response,"CHECK_ID");
                	break;
                }
                
                // 로그인 시도 유저ID가 로그인 되어 있는지 체크
                if(!anOnLoginService.isUsing(userNo))
                {
                	if(!chkCcUserNum(request, userInfoList))
                	{
                		anOnLoginService.unBound(session);
                    	//접속자 수 초과
//                		setMessage(response, "","CONN_OVER");
                		makeJsonResult(userInfoList, request, response,"CONN_OVER");
                        break;
                	} 
                	 Map hash = (Map)userInfoList.get(0);
                     String changeNeed = (String)hash.get("CHANGENEED");
                	
                     //비밀번호 변경이 필요한 상태
                     if("Y".equals(changeNeed))
                     {
                         // 비밀번호 변경 필요
                    	 anOnLoginService.unBound(session);
//                 		setMessage(response, "","CHANGE_PW");
                		makeJsonResult(userInfoList, request, response,"CHANGE_PW");
                    	 break;
                     }
                    // 사용중이 아니라면 login 처리한다.
                    setSession(session, String.valueOf(map.get("lang")), userInfoList);
                    loginSuccess(request);
                    //login 정보 전달
                    makeJsonResult(userInfoList, request, response,"OK");
                    
                    break;
                }
                // 로그인 시도 유저ID가 이미 로그인되어 있다면
                else
                {
                    /*
                     * 이미 다른 세션에서  로그인 되어 있는 상태에서 다시 로그인 시도 할때
                     * 사용자에게 로그인 할 것인지 확인 하게 한다.
                     */
//             		setMessage(response, "","CONN_ID");
            		makeJsonResult(userInfoList, request, response,"CONN_ID");
                	break;
                }
            case LOGOUT:
            	anOnLoginService.unBound(session);
                setLocale(request, new Locale(String.valueOf(map.get("lang"))));
                setMessage(response, "","LOGOUT");
                break;
            case USER_OK_LOGOUT:
                userInfoList = getUserInfo(request, map, false);

                if (userInfoList == null) break;
                //confirm에서 사용자가 선택에 따라서 실행이 된다.
                if (userInfoList.size() > 0)
                {
                	anOnLoginService.isForceLogOut(userNo, session.getId());
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                    	anOnLoginService.unBound(session);
                    	//접속자 수 초과
//                		setMessage(response, "","CONN_OVER");
                        makeJsonResult(userInfoList, request, response,"CONN_OVER");
                        break;
                	}
                    
                    setSession(session, String.valueOf(map.get("lang")), userInfoList);
                    loginSuccess(request);
                    //login 정보 전달
                    makeJsonResult(userInfoList, request, response,"OK");
                    
                    break;
                }
                //Login ID가 없을 때 실행된다. 
                else
                {
                	//ID, PASSWORD 체크
//            		setMessage(response, "","CHECK_ID");
                    makeJsonResult(userInfoList, request, response,"CHECK_ID");
                }
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
                    break;
                }
            
                // 다시 로긴해야하는경우 그전의 session 을 invalidate 한다.
                anOnLoginService.unBound(session);
                
                setLocale(request, new Locale(String.valueOf(map.get("lang"))));
                
                break;
        }

        return  mapping.findForward("jsonPage");
    }
    
    /**
     * 로그인 성공
     * @author  javaworker
     * @version $Id: LoginAction.java,v 1.2 2013/10/08 00:51:30 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     */
    private void loginSuccess(HttpServletRequest request)
    {
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(request.getSession().getId());
        
        // 로그인 이력을 남긴다.
        anOnLoginService.insertLogHist(loginUser, request);
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
    private List getUserInfo(HttpServletRequest request, Map map, boolean valid) throws Exception
    { 
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        List userInfoList = anOnLoginService.findUserInfo(map);     // user 정보 조회
        List emptyList = new ArrayList<>();
        // true 인경우 password 를 비교하여 로그인 인증한다.
        if (valid)
        {
            // Encrypt Password
            String passwordEncrypt = CommonUtil.aesEncodeString(String.valueOf(map.get("password")));
            //===============================================================
            
            // User가 없는 경우
            if(userInfoList == null || userInfoList.size() <= 0)
            {
                // ID 혹은 Password가 잘못되었습니다.
            	
                return emptyList;
            }
            
            Map hash = (Map)userInfoList.get(0);
            String userPassword = (String)hash.get("PASSWORD");
            
            if(!passwordEncrypt.equals(userPassword))
            {
                // ID 혹은 Password가 잘못되었습니다.
                return emptyList;
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
            anOnLoginService.setLoginDate(map);
           
            return userInfoList;
        }else{
        	return userInfoList;
        }
    }
    private List getDeviceInfo(HttpServletRequest request, Map map) throws Exception
    { 
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        List deviceInfoList = anOnLoginService.findDeviceInfo(map);     // user 정보 조회
        return deviceInfoList;
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

        String id           = String.valueOf(hash.get("USERID")); //사용자 ID (Login)
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
        user.setPageType("dream");
        
        user.setLoginTimeMillis(System.currentTimeMillis());

        if(language == null) language = lang;
        //================================================
        // 언어 설정
        Locale locale = new Locale(language);
        user.setLocale(locale);
        session.setAttribute(Globals.LOCALE_KEY, locale);
        //=================================================
        
        // 현재 로그인한 User 객체를 저장한다.
        Hashtable loginUsers = BaseAction.getLoginUsers();
        loginUsers.put(session.getId(), user);
        BaseAction.setLoginUsers(loginUsers);

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
    
    public Map getMapData(HttpServletRequest request) throws ParseException
    {
        Map map = null;
        Map<String, String[]> paramMap = request.getParameterMap();
        JSONObject jsonObj = null;
        
        if(paramMap.containsKey("data"))
        {
            String data = paramMap.get("data")[0];
          //  Gson gson = new Gson();
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
//            Gson gson = gsonBuilder.create();
//            
//            map = gson.fromJson(data, Map.class);
            
            JSONParser jsonParser = new JSONParser();
            jsonObj = (JSONObject) jsonParser.parse(data);
        }
        
        return jsonObj;
    }
    public void setMessage(HttpServletResponse response, String message, String status) throws IOException
    {
        Map resultMap = new HashMap();
        
        resultMap.put("ResultMsg", message);
        resultMap.put("ResultStatus", status);
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(resultMap);
        response.getWriter().print(jsonString);
    }
    public void makeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String status) throws IOException
    {   
        Map result = CommonUtil.makeResultJson(resultList);
        result.put("ResultStatus", status);        
        result.put("ResultMsg", "");        
        
       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
        
        String jsonString = gson.toJson(result); 
        response.getWriter().print(jsonString);

    }
}