package intf.dream.cricket.check.action;


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
import intf.dream.cricket.check.form.CricketCheckForm;
import intf.dream.cricket.check.service.CricketCheckService;

/**
 * check  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketCheck" name="cricketCheckForm"
 *                input="/cricket/check/cricketCheck.jsp" scope="request"
 *                validate="false"
 */
public class CricketCheckAction extends IfAuthAction
{
    //DEVIDE CHECK
    public static final String DEVICE_CHECK 	= "CHECKDEVICE";
    //NETWORK CHECK
    public static final String NETWORK_CHECK 	= "NETWORKCHECK";
    //SKIN CHECK
    public static final String SKIN_CHECK 		= "SKINCHECK";
    //CRICKET VERSION CHECK
    public static final String CRICKET_VERSION_CHECK 	= "CRICKET_VERSIONCHECK";
    //Logo CHECK
    public static final String LOGO_CHECK 		= "LOGOCHECK";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketCheckForm cricketCheckForm = (CricketCheckForm) form;
        
        switch (cricketCheckForm.getServiceName())
        {
            case CricketCheckAction.DEVICE_CHECK:
            	deviceCheck(request, response, cricketCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketCheckAction.NETWORK_CHECK:
            	netCheck(request, response, cricketCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketCheckAction.SKIN_CHECK:
            	skinCheck(request, response, cricketCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketCheckAction.CRICKET_VERSION_CHECK:
            	cricketVersionCheck(request, response, cricketCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case CricketCheckAction.LOGO_CHECK:
            	logoCheck(request, response, cricketCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void deviceCheck(HttpServletRequest request, HttpServletResponse response, CricketCheckForm cricketCheckForm)  throws IOException, ParseException
    {
    	CricketCheckService cricketCheckService = (CricketCheckService) getBean("cricketCheckService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = cricketCheckService.deviceCheck(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void skinCheck(HttpServletRequest request, HttpServletResponse response, CricketCheckForm cricketCheckForm)  throws IOException, ParseException
    {
    	CricketCheckService cricketCheckService = (CricketCheckService) getBean("cricketCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = cricketCheckService.skinCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void cricketVersionCheck(HttpServletRequest request, HttpServletResponse response, CricketCheckForm cricketCheckForm)  throws IOException, ParseException
    {
    	CricketCheckService cricketCheckService = (CricketCheckService) getBean("cricketCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = cricketCheckService.cricketVersionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void logoCheck(HttpServletRequest request, HttpServletResponse response, CricketCheckForm cricketCheckForm)  throws IOException, ParseException, InvalidKeyException, URISyntaxException, StorageException
    {
    	CricketCheckService cricketCheckService = (CricketCheckService) getBean("cricketCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = cricketCheckService.logoCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }

    private void netCheck(HttpServletRequest request, HttpServletResponse response, CricketCheckForm cricketCheckForm)  throws IOException, ParseException
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
