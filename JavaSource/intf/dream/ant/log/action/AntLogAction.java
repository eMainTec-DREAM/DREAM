package intf.dream.ant.log.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.ant.log.form.AntLogForm;
import intf.dream.ant.log.service.AntLogService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antLog" name="antLogForm"
 *                input="/ant/log/antLog.jsp" scope="request"
 *                validate="false"
 */
public class AntLogAction extends IfAuthAction
{
    //Login Log
    public static final String LOGINLOG 				= "LOGINLOG";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntLogForm antLogForm = (AntLogForm) form;
        
        switch (antLogForm.getServiceName())
        {
            case AntLogAction.LOGINLOG:
            	saveLoginLog(request, response, antLogForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void saveLoginLog(HttpServletRequest request, HttpServletResponse response, AntLogForm antLogForm)  throws Exception
    {
    	AntLogService antLogService = (AntLogService) getBean("antLogService");

    	Map map = getMapData(request);
    	int qty = antLogService.saveLoginLog(map);
    	setMessage(response, "","OK");
    }
}
