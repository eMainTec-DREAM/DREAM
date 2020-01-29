package intf.dream.bee.check.action;


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
import intf.dream.bee.check.form.BeeCheckForm;
import intf.dream.bee.check.service.BeeCheckService;

/**
 * check  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeCheck" name="beeCheckForm"
 *                input="/bee/check/beeCheck.jsp" scope="request"
 *                validate="false"
 */
public class BeeCheckAction extends IfAuthAction
{
    //DEVIDE CHECK
    public static final String DEVICE_CHECK 	= "CHECKDEVICE";
    //NETWORK CHECK
    public static final String NETWORK_CHECK 	= "NETWORKCHECK";
    //SKIN CHECK
    public static final String SKIN_CHECK 		= "SKINCHECK";
    //BEE VERSION CHECK
    public static final String BEE_VERSION_CHECK 	= "BEE_VERSIONCHECK";
    //Logo CHECK
    public static final String LOGO_CHECK 		= "LOGOCHECK";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeCheckForm beeCheckForm = (BeeCheckForm) form;
        
        switch (beeCheckForm.getServiceName())
        {
            case BeeCheckAction.DEVICE_CHECK:
            	deviceCheck(request, response, beeCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCheckAction.NETWORK_CHECK:
            	netCheck(request, response, beeCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCheckAction.SKIN_CHECK:
            	skinCheck(request, response, beeCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCheckAction.BEE_VERSION_CHECK:
            	beeVersionCheck(request, response, beeCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case BeeCheckAction.LOGO_CHECK:
            	logoCheck(request, response, beeCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void deviceCheck(HttpServletRequest request, HttpServletResponse response, BeeCheckForm beeCheckForm)  throws IOException, ParseException
    {
    	BeeCheckService beeCheckService = (BeeCheckService) getBean("beeCheckService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = beeCheckService.deviceCheck(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void skinCheck(HttpServletRequest request, HttpServletResponse response, BeeCheckForm beeCheckForm)  throws IOException, ParseException
    {
    	BeeCheckService beeCheckService = (BeeCheckService) getBean("beeCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = beeCheckService.skinCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void beeVersionCheck(HttpServletRequest request, HttpServletResponse response, BeeCheckForm beeCheckForm)  throws IOException, ParseException
    {
    	BeeCheckService beeCheckService = (BeeCheckService) getBean("beeCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = beeCheckService.beeVersionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void logoCheck(HttpServletRequest request, HttpServletResponse response, BeeCheckForm beeCheckForm)  throws IOException, ParseException, InvalidKeyException, URISyntaxException, StorageException
    {
    	BeeCheckService beeCheckService = (BeeCheckService) getBean("beeCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = beeCheckService.logoCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }

    private void netCheck(HttpServletRequest request, HttpServletResponse response, BeeCheckForm beeCheckForm)  throws IOException, ParseException
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
