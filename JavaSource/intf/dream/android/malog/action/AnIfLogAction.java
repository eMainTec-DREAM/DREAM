package intf.dream.android.malog.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.android.malog.form.AnIfLogForm;
import intf.dream.android.malog.service.AnIfLogService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfLog" name="anIfLogForm"
 *                input="/android/malog/anIfLog.jsp" scope="request"
 *                validate="false"
 */
public class AnIfLogAction extends IfAuthAction
{
    //Login Log
    public static final String LOGINLOG 				= "LOGINLOG";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfLogForm anIfLogForm = (AnIfLogForm) form;
        
        switch (anIfLogForm.getServiceName())
        {
            case AnIfLogAction.LOGINLOG:
            	saveLoginLog(request, response, anIfLogForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void saveLoginLog(HttpServletRequest request, HttpServletResponse response, AnIfLogForm anIfLogForm)  throws Exception
    {
    	AnIfLogService anIfLogService = (AnIfLogService) getBean("anIfLogService");

    	Map map = getMapData(request);
    	int qty = anIfLogService.saveLoginLog(map);
    	setMessage(response, "","OK");
    }
}
