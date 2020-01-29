package intf.dream.ant.check.action;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.storage.StorageException;

import common.struts.IfAuthAction;
import intf.dream.ant.check.form.AntCheckForm;
import intf.dream.ant.check.service.AntCheckService;

/**
 * check  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antCheck" name="antCheckForm"
 *                input="/ant/check/antCheck.jsp" scope="request"
 *                validate="false"
 */
public class AntCheckAction extends IfAuthAction
{
    //DEVIDE CHECK
    public static final String DEVICE_CHECK 	= "CHECKDEVICE";
    //NETWORK CHECK
    public static final String NETWORK_CHECK 	= "NETWORKCHECK";
    //SKIN CHECK
    public static final String SKIN_CHECK 		= "SKINCHECK";
    //ANT VERSION CHECK
    public static final String ANT_VERSION_CHECK 	= "ANT_VERSIONCHECK";
    //Logo CHECK
    public static final String LOGO_CHECK 		= "LOGOCHECK";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntCheckForm antCheckForm = (AntCheckForm) form;
        
        switch (antCheckForm.getServiceName())
        {
            case AntCheckAction.DEVICE_CHECK:
            	deviceCheck(request, response, antCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AntCheckAction.NETWORK_CHECK:
            	netCheck(request, response, antCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AntCheckAction.SKIN_CHECK:
            	skinCheck(request, response, antCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AntCheckAction.ANT_VERSION_CHECK:
            	antVersionCheck(request, response, antCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AntCheckAction.LOGO_CHECK:
            	logoCheck(request, response, antCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void deviceCheck(HttpServletRequest request, HttpServletResponse response, AntCheckForm antCheckForm)  throws IOException, ParseException
    {
    	AntCheckService antCheckService = (AntCheckService) getBean("antCheckService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = antCheckService.deviceCheck(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void skinCheck(HttpServletRequest request, HttpServletResponse response, AntCheckForm antCheckForm)  throws IOException, ParseException
    {
    	AntCheckService antCheckService = (AntCheckService) getBean("antCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = antCheckService.skinCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void antVersionCheck(HttpServletRequest request, HttpServletResponse response, AntCheckForm antCheckForm)  throws IOException, ParseException
    {
    	AntCheckService antCheckService = (AntCheckService) getBean("antCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = antCheckService.antVersionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void logoCheck(HttpServletRequest request, HttpServletResponse response, AntCheckForm antCheckForm)  throws IOException, ParseException, InvalidKeyException, URISyntaxException, StorageException
    {
    	AntCheckService antCheckService = (AntCheckService) getBean("antCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = antCheckService.logoCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }

    private void netCheck(HttpServletRequest request, HttpServletResponse response, AntCheckForm antCheckForm)  throws IOException, ParseException
    {
    	Map result = new HashMap();
        result.put("ResultStatus", "OK");        
        result.put("ResultMsg", "");        
        
       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
        
        String jsonString = gson.toJson(result); 
        response.getWriter().print(jsonString);
    }
}
