/**  */
package common.struts;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Hashtable;
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

import common.bean.MwareConfig;
import common.util.AES256Cipher2;
import common.util.CommonUtil;
import dream.login.login.dto.LoginDTO;
import dream.login.login.service.LoginService;
import intf.dream.bee.common.service.BeeCommonListService;


/**
 * @author  kim21017
 * @version $Id: IfOnlineAuthAction.java,v 1.1 2013/08/30 09:11:57 kim21017 Exp $
 * @since   1.0
 * 
 */
public abstract class IfOnlineAuthAction
        extends BaseAction
{
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(AuthAction.class);

    public IfOnlineAuthAction()
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
        if(!checkLoginInfo(request, response)){
            return mapping.findForward("jsonPage");
        }

        //SAVE ACCESS LOG (Who, What, When)
        saveAccessLog(request);
        
        try
        {
            returnMapping = run(mapping, form, request, response);
        }
        catch (SQLSyntaxErrorException e){
        	Map result = new HashMap();
            result.put("ResultStatus", "SQLERROR");        
            result.put("ResultMsg", "CMSG035");        
            
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
            logger.error(this, e);
            throw e;
        }
        catch (IOException e){
        	Map result = new HashMap();
            result.put("ResultStatus", "IOERROR");        
            result.put("ResultMsg", "CMSG035");        
            
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
            logger.error(this, e);
            throw e;
        }
        catch (Exception e)
        {
        	Map result = new HashMap();
            result.put("ResultStatus", "ERROR");        
            result.put("ResultMsg", "CMSG035");        
            
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
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
        //System.out.println(request.getRequestURI());
        
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
        String deviceId = paramMap.containsKey("deviceId")?paramMap.get("deviceId")[0]:"";
        String serviceType = paramMap.containsKey("serviceType")?paramMap.get("serviceType")[0]:"";
        
        loginDTO.setUserNo(decUser);
        loginDTO.setPassWord(passwordEncrypt);
        loginDTO.setCompNo(compNo);
        loginDTO.setDeviceId(deviceId);
        loginDTO.setServiceType(serviceType);

        LoginService loginService = (LoginService) getBean("loginService");
        
        List userInfoList = loginService.findIfUserInfo(loginDTO);
        List deviceList = loginService.findIfDeviceInfo(loginDTO);
        		
        //===============================================================

        // 등록된 device가 아닙니다.
        if(deviceList == null || deviceList.size() <= 0)
        {
        	Map result = new HashMap();
            result.put("ResultStatus", "ERROR");        
            result.put("ResultMsg", "MSG0143");        
            
           // Gson gson = new Gson();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
            return false;
        }
        // User가 없는 경우
        if(userInfoList == null || userInfoList.size() <= 0)
        {
        	Map result = new HashMap();
            result.put("ResultStatus", "ERROR");        
            result.put("ResultMsg", "MSG0117");        
            
           // Gson gson = new Gson();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
            return false;
        }
        
        Map hash = (Map)userInfoList.get(0);
        String userPassword = (String)hash.get("IFPWD");
//         Password가 틀린경우 
        if(!passwordEncrypt.equals(userPassword))
        {
        	Map result = new HashMap();
            result.put("ResultStatus", "ERROR");        
            result.put("ResultMsg", "MSG0117");        
            
           // Gson gson = new Gson();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
            return false;
        }
        
        // Session Check !!!
        if (getUser(request) == null)
        {
        	Map result = new HashMap();
            result.put("ResultStatus", "SESSION");        
            result.put("ResultMsg", "CMSG020");        
            
           // Gson gson = new Gson();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
        	return false;
        }
        
        if(!checkLoginUser(request))
        {
        	Map result = new HashMap();
            result.put("ResultStatus", "SESSION");        
            result.put("ResultMsg", "CMSG020");        
            
           // Gson gson = new Gson();
            Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
            
            String jsonString = gson.toJson(result); 
            response.getWriter().print(jsonString);
        	return false;
        }
        
        return true;
    }
    
    private boolean checkLoginUser(HttpServletRequest request)
    {
        boolean result = false;
        
        /*
         * UserService 객체에서
         * loginUsers 라는 hashtable에 현재의  session id를  검색을 하여
         * 해당 session id 키 값이 있는지를 체크 한다. 
         * 해당 키 값이  있으면 true를 리턴하고  
         * 해당 키 값이 없으면  false를 리턴을 한다. 
         */
        String sessionId = request.getSession().getId(); //현재 session id 값을 얻어온다.
        Hashtable loginUsers = BaseAction.getLoginUsers(); 
        // 현재 session ID로 저장된 User 객체가 있는지 체크한다.
        Object loginUser = loginUsers.get(sessionId); 
        if (loginUser != null)
        {
            result = true;
        }
        
        return result;
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
            JSONParser jsonParser = new JSONParser();
            jsonObj = (JSONObject) jsonParser.parse(data);
        }
        
        return jsonObj;
    }
    
    public void updateAudit(HttpServletRequest request) throws Exception{
    	
    	if(!"Y".equals(MwareConfig.getIsUseAuditTrail())) return;
    	
    	Map<String, String[]> paramMap = request.getParameterMap();
        String serviceName = paramMap.containsKey("serviceName")?paramMap.get("serviceName")[0]:"";
        
        String dataCudType = "";
        if(serviceName.contains("UPDATE"))dataCudType = "U";
        else if(serviceName.contains("INSERT")) dataCudType = "I";
        else if(serviceName.contains("DELETE")) dataCudType = "D";
        
        if("Y".equals(MwareConfig.getIsUseAuditTrailRead())){
        	if(serviceName.contains("LIST")||serviceName.contains("DETAIL")) dataCudType = "R";
        	else if(serviceName.contains("PRINT")) dataCudType = "P";
        }
        
        if("".equals(dataCudType)) return;
        
        BeeCommonListService beeCommonListService = (BeeCommonListService) BaseAction.ctx.getBean("beeCommonListService");
    	
    	if("U".equals(dataCudType) || "I".equals(dataCudType)|| "D".equals(dataCudType)){
    		List list = getListData(request);
    		for(Object obj:list){
    			Map<String, Object> data = (Map)obj;
    			data.put("dataCudType", dataCudType);
    			beeCommonListService.insertTraceLog(data);
    		}
    	}else if("R".equals(dataCudType) || "P".equals(dataCudType)){
    		Map<String, Object> data = getMapData(request);
    		data.put("dataCudType", dataCudType);
            beeCommonListService.insertTraceLog(data);
    	}
    	
    }
   
}
