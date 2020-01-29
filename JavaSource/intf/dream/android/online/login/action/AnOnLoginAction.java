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
 * Android Login ó��
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
    /** �α��� ��û */
    public static final String LOGIN				= "LOGIN";
    /** �α׾ƿ� ��û */
    public static final String LOGOUT 				= "LOGOUT";
    /** ��α��� ��û */
    public static final String USER_OK_LOGOUT 		= "USER_OK_LOGOUT";

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    { 
        // ���ο� session ����
        HttpSession session = request.getSession();
        AnOnLoginForm anOnLoginForm = (AnOnLoginForm) form;
        Map map = getMapData(request);
        
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        
    	// ���� �������� �̹� �α��� �Ǿ� �ִ��� ����
    	boolean existsLoginUser = false;

        List userInfoList = null;
        List deviceInfoList = null;
        String userNo = String.valueOf(map.get("userNo"));

        
        switch(anOnLoginForm.getServiceName())
        {
            case LOGIN:
                // �α��� ������ ���������� ��ȸ�Ѵ�.
                userInfoList = getUserInfo(request, map, true);
                deviceInfoList = getDeviceInfo(request, map);

                if(deviceInfoList == null || deviceInfoList.size()<1){
                	//DEVICE ID CHECK
//            		setMessage(response, "","DEVICE_ID_ERROR");
            		makeJsonResult(userInfoList, request, response,"DEVICE_ID_ERROR");
                	break;
                }
                
                if (userInfoList == null|| userInfoList.size()<1){
                	//ID, PASSWORD üũ
//            		setMessage(response, "","CHECK_ID");
            		makeJsonResult(userInfoList, request, response,"CHECK_ID");
                	break;
                }
                
                // �α��� �õ� ����ID�� �α��� �Ǿ� �ִ��� üũ
                if(!anOnLoginService.isUsing(userNo))
                {
                	if(!chkCcUserNum(request, userInfoList))
                	{
                		anOnLoginService.unBound(session);
                    	//������ �� �ʰ�
//                		setMessage(response, "","CONN_OVER");
                		makeJsonResult(userInfoList, request, response,"CONN_OVER");
                        break;
                	} 
                	 Map hash = (Map)userInfoList.get(0);
                     String changeNeed = (String)hash.get("CHANGENEED");
                	
                     //��й�ȣ ������ �ʿ��� ����
                     if("Y".equals(changeNeed))
                     {
                         // ��й�ȣ ���� �ʿ�
                    	 anOnLoginService.unBound(session);
//                 		setMessage(response, "","CHANGE_PW");
                		makeJsonResult(userInfoList, request, response,"CHANGE_PW");
                    	 break;
                     }
                    // ������� �ƴ϶�� login ó���Ѵ�.
                    setSession(session, String.valueOf(map.get("lang")), userInfoList);
                    loginSuccess(request);
                    //login ���� ����
                    makeJsonResult(userInfoList, request, response,"OK");
                    
                    break;
                }
                // �α��� �õ� ����ID�� �̹� �α��εǾ� �ִٸ�
                else
                {
                    /*
                     * �̹� �ٸ� ���ǿ���  �α��� �Ǿ� �ִ� ���¿��� �ٽ� �α��� �õ� �Ҷ�
                     * ����ڿ��� �α��� �� ������ Ȯ�� �ϰ� �Ѵ�.
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
                //confirm���� ����ڰ� ���ÿ� ���� ������ �ȴ�.
                if (userInfoList.size() > 0)
                {
                	anOnLoginService.isForceLogOut(userNo, session.getId());
                    
                    if(!chkCcUserNum(request, userInfoList))
                	{
                    	anOnLoginService.unBound(session);
                    	//������ �� �ʰ�
//                		setMessage(response, "","CONN_OVER");
                        makeJsonResult(userInfoList, request, response,"CONN_OVER");
                        break;
                	}
                    
                    setSession(session, String.valueOf(map.get("lang")), userInfoList);
                    loginSuccess(request);
                    //login ���� ����
                    makeJsonResult(userInfoList, request, response,"OK");
                    
                    break;
                }
                //Login ID�� ���� �� ����ȴ�. 
                else
                {
                	//ID, PASSWORD üũ
//            		setMessage(response, "","CHECK_ID");
                    makeJsonResult(userInfoList, request, response,"CHECK_ID");
                }
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
                    break;
                }
            
                // �ٽ� �α��ؾ��ϴ°�� ������ session �� invalidate �Ѵ�.
                anOnLoginService.unBound(session);
                
                setLocale(request, new Locale(String.valueOf(map.get("lang"))));
                
                break;
        }

        return  mapping.findForward("jsonPage");
    }
    
    /**
     * �α��� ����
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
        
        // �α��� �̷��� �����.
        anOnLoginService.insertLogHist(loginUser, request);
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
    private List getUserInfo(HttpServletRequest request, Map map, boolean valid) throws Exception
    { 
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        List userInfoList = anOnLoginService.findUserInfo(map);     // user ���� ��ȸ
        List emptyList = new ArrayList<>();
        // true �ΰ�� password �� ���Ͽ� �α��� �����Ѵ�.
        if (valid)
        {
            // Encrypt Password
            String passwordEncrypt = CommonUtil.aesEncodeString(String.valueOf(map.get("password")));
            //===============================================================
            
            // User�� ���� ���
            if(userInfoList == null || userInfoList.size() <= 0)
            {
                // ID Ȥ�� Password�� �߸��Ǿ����ϴ�.
            	
                return emptyList;
            }
            
            Map hash = (Map)userInfoList.get(0);
            String userPassword = (String)hash.get("PASSWORD");
            
            if(!passwordEncrypt.equals(userPassword))
            {
                // ID Ȥ�� Password�� �߸��Ǿ����ϴ�.
                return emptyList;
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
            anOnLoginService.setLoginDate(map);
           
            return userInfoList;
        }else{
        	return userInfoList;
        }
    }
    private List getDeviceInfo(HttpServletRequest request, Map map) throws Exception
    { 
        AnOnLoginService anOnLoginService = (AnOnLoginService) getBean("anOnLoginService");
        List deviceInfoList = anOnLoginService.findDeviceInfo(map);     // user ���� ��ȸ
        return deviceInfoList;
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

        String id           = String.valueOf(hash.get("USERID")); //����� ID (Login)
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
        user.setPageType("dream");
        
        user.setLoginTimeMillis(System.currentTimeMillis());

        if(language == null) language = lang;
        //================================================
        // ��� ����
        Locale locale = new Locale(language);
        user.setLocale(locale);
        session.setAttribute(Globals.LOCALE_KEY, locale);
        //=================================================
        
        // ���� �α����� User ��ü�� �����Ѵ�.
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
        // Key ����
        Enumeration loginUsersEm = loginUsersTable.keys();
        
        User user = null;
        int i = 1;
        while (loginUsersEm.hasMoreElements())
        {
            // User session Id 
            String loginUserKey = (String)loginUsersEm.nextElement();
            
            user = (User)loginUsersTable.get(loginUserKey);
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
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //�����ڵ� ó��
        
        String jsonString = gson.toJson(result); 
        response.getWriter().print(jsonString);

    }
}