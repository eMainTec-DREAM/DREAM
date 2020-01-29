/**  */
package common.struts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.util.AES256Cipher2;
import common.util.CommonUtil;
import dream.login.login.dto.LoginDTO;
import dream.login.login.service.LoginService;


/**
 * @author  pksup
 * @version $Id: NoneAuthAction.java,v 1.1 2013/08/30 09:11:57 javaworker Exp $
 * @since   1.0
 * 
 */
public abstract class IfAuthAction
        extends BaseAction
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AuthAction.class);

    public IfAuthAction()
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
        
        //CHECK LOGIN INFO (ID, PW, COMP) encrypted necessary
        if(!checkLoginInfo(request, response)) return mapping.findForward("jsonPage");

        //SAVE ACCESS LOG (Who, What, When)
        saveAccessLog(request);
        
        try
        {
            returnMapping = run(mapping, form, request, response);
        }
        catch (Exception e)
        {
            logger.error(this, e);
            throw e;
        }
        afterExecute();
        
        return returnMapping;
    }

    /**
     * PDA 사용자 및 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     */
    private void saveAccessLog(HttpServletRequest request)
    {
        // TODO Auto-generated method stub
        System.out.println(request.getRequestURI());
        
    }

    /**
     * Check userId, passWord, compNo
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws InvalidAlgorithmParameterException 
     * @throws NoSuchPaddingException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    private boolean checkLoginInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Map<String, String[]> paramMap = request.getParameterMap();

        LoginDTO loginDTO = new LoginDTO();

        String decUser = paramMap.containsKey("user")?paramMap.get("user")[0]:"";
        String passwordEncrypt = AES256Cipher2.getInstance().AES_Encode(paramMap.containsKey("pwd")?paramMap.get("pwd")[0]:"");
        String compNo = paramMap.containsKey("compNo")?paramMap.get("compNo")[0]:"";
        
        loginDTO.setUserNo(decUser);
        loginDTO.setPassWord(passwordEncrypt);
        loginDTO.setCompNo(compNo);

        LoginService loginService = (LoginService) getBean("loginService");
        
        List userInfoList = loginService.findIfUserInfo(loginDTO);

        //===============================================================
        
        // User가 없는 경우
        if(userInfoList == null || userInfoList.size() <= 0)
        {
            // 등록된 User가 아닙니다.
            setMessage(response, getMessage(request, "MESSAGE.MSG0003"), "ERROR");
            return false;
        }
        
        Map hash = (Map)userInfoList.get(0);
        String userPassword = (String)hash.get("IFPWD");
//         Password가 틀린경우 
        if(!passwordEncrypt.equals(userPassword))
        {
            // PASSWORD를 잘못 입력 하셨습니다.
            setMessage(response,  getMessage(request, "MESSAGE.MSG0004"), "ERROR");
            return false;
        }

        return true;
    }

    /**
     * 사용목적 : 하위의 모든 클래스는 이 메소드를 오버라이드 하여 그곳에 로직을 구현한다. <br>
     * 사용법 : <br>
     * 구현 설명 : <br>
     * 작성 날짜 : (05-03-31 오전 11:13:24)
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
    
    
    protected String getMessage(HttpServletRequest request, String key)
    {
        if (key == null || "".equals(key))
        {
            return "";
        }
        
        /*
         * key 값이 유효한지 체크한다. ex)MENU_ID.keyId
         * . 이 없거나 . 의 앞뒤에 key Id 가 없다면 "" 를 리턴한다.
         */
        int decimalIndex = key.indexOf(".");
        if (decimalIndex == -1 || 
                key.substring(0, decimalIndex).equals("") || 
                key.substring(decimalIndex+1).equals(""))
        {
            return "";
        }
        
        java.util.Locale locale = getLocale(request);
        if (getUser(request) != null)
        {
            locale = getUser(request).getLocale();
        }
        org.apache.struts.util.MessageResources messages = getResources(request);
        return messages.getMessage(locale, key);
    }
    
    public void makeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response) throws IOException
    {   
        Map result = CommonUtil.makeResultJson(resultList);
        result.put("ResultStatus", "OK");        
        result.put("ResultMsg", "");        
        
       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
        
        String jsonString = gson.toJson(result); 
        response.getWriter().print(jsonString);

    }
    
    /**
     * Set Message (Status)
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param response
     * @param message
     * @throws IOException 
     */
    public void setMessage(HttpServletResponse response, String message, String status) throws IOException
    {
        Map resultMap = new HashMap();
        
        resultMap.put("ResultMsg", message);
        resultMap.put("ResultStatus", status);
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(resultMap);
        response.getWriter().print(jsonString);
    }
    
    public Object makeDTO(HttpServletRequest request, Class<?> clss) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        Map<String, String[]> paramMap = request.getParameterMap();
        Class<?> cls = Class.forName(clss.getName());
        String methodName = "";
        Object obj = cls.newInstance();
        boolean isMethod = false;
        
        for( String key : paramMap.keySet() ){
            isMethod = false;
            methodName = "set"+ key.substring(0, 1).toUpperCase() + key.substring(1);

            Method[] methods = cls.getMethods();
            for( Method method : methods ){
                if(methodName.equals(method.getName())) isMethod = true;
            }
            if(!isMethod) continue;
            
           // sum 메소드를 가져와서 합 구하기 (인자 파라미터 나열)
            Method method = cls.getMethod(methodName, String.class);
            method.invoke(obj, String.valueOf(paramMap.get(key)[0]));
        }
        
        return obj;
    }
    
    /**
     * Json List<Map> (data) 추출
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @return
     * @throws ParseException 
     */
    public List getListData(HttpServletRequest request) throws ParseException
    {
        List list = null;
        JSONArray jsonArray = null;
        
        Map<String, String[]> paramMap = request.getParameterMap();
        
        if(paramMap.containsKey("data"))
        {
            String data = paramMap.get("data")[0];
           // Gson gson = new Gson();
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
//            Gson gson = gsonBuilder.create();

            JSONParser jsonParser = new JSONParser();
            jsonArray = (JSONArray) jsonParser.parse(data);
            
//            list = gson.fromJson(data, List.class);
        }
        
        return jsonArray;
    }
    
    /**
     * Json Map(data) 추출
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @return
     * @throws ParseException 
     */
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
   
}
