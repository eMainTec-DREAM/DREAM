package intf.dream.android.macheck.action;


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
import intf.dream.android.macheck.form.AnIfCheckForm;
import intf.dream.android.macheck.service.AnIfCheckService;

/**
 * check  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfCheck" name="anIfCheckForm"
 *                input="/android/macheck/anIfCheck.jsp" scope="request"
 *                validate="false"
 */
public class AnIfCheckAction extends IfAuthAction
{
    //DEVIDE CHECK
    public static final String DEVICE_CHECK 	= "CHECKDEVICE";
    //NETWORK CHECK
    public static final String NETWORK_CHECK 	= "NETWORKCHECK";
    //SKIN CHECK
    public static final String SKIN_CHECK 		= "SKINCHECK";
    //VERSION CHECK
    public static final String VERSION_CHECK 	= "VERSIONCHECK";
    //ANT VERSION CHECK
    public static final String ANT_VERSION_CHECK 	= "ANT_VERSIONCHECK";
    //BEE VERSION CHECK
    public static final String BEE_VERSION_CHECK 	= "BEE_VERSIONCHECK";
    //CRICKET VERSION CHECK
    public static final String CRICKET_VERSION_CHECK 	= "CRICKET_VERSIONCHECK";
    //Logo CHECK
    public static final String LOGO_CHECK 		= "LOGOCHECK";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfCheckForm anIfCheckForm = (AnIfCheckForm) form;
        
        switch (anIfCheckForm.getServiceName())
        {
            case AnIfCheckAction.DEVICE_CHECK:
            	deviceCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.NETWORK_CHECK:
            	netCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.SKIN_CHECK:
            	skinCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.VERSION_CHECK:
            	versionCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.ANT_VERSION_CHECK:
            	antVersionCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.BEE_VERSION_CHECK:
            	beeVersionCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.CRICKET_VERSION_CHECK:
            	cricketVersionCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case AnIfCheckAction.LOGO_CHECK:
            	logoCheck(request, response, anIfCheckForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void deviceCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");

    	Map map = getMapData(request);

        //리스트를 조회한다.
        List resultList = anIfCheckService.deviceCheck(map);

        // 조회한 List 를 form에 셋팅한다.
        super.makeJsonResult(resultList, request, response);
    }
    private void skinCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anIfCheckService.skinCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void versionCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anIfCheckService.versionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void antVersionCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anIfCheckService.antVersionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void beeVersionCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anIfCheckService.beeVersionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void cricketVersionCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anIfCheckService.cricketVersionCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }
    private void logoCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException, InvalidKeyException, URISyntaxException, StorageException
    {
    	AnIfCheckService anIfCheckService = (AnIfCheckService) getBean("anIfCheckService");
    	
    	Map map = getMapData(request);
    	
    	//리스트를 조회한다.
    	List resultList = anIfCheckService.logoCheck(map);
    	
    	// 조회한 List 를 form에 셋팅한다.
    	super.makeJsonResult(resultList, request, response);
    }

    private void netCheck(HttpServletRequest request, HttpServletResponse response, AnIfCheckForm anIfCheckForm)  throws IOException, ParseException
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
