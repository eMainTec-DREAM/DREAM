package intf.dream.bee.log.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.bee.log.form.BeeLogForm;
import intf.dream.bee.log.service.BeeLogService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/beeLog" name="beeLogForm"
 *                input="/ant/log/beeLog.jsp" scope="request"
 *                validate="false"
 */
public class BeeLogAction extends IfAuthAction
{
    //Login Log
    public static final String LOGINLOG 				= "LOGINLOG";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BeeLogForm beeLogForm = (BeeLogForm) form;
        
        switch (beeLogForm.getServiceName())
        {
            case BeeLogAction.LOGINLOG:
            	saveLoginLog(request, response, beeLogForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void saveLoginLog(HttpServletRequest request, HttpServletResponse response, BeeLogForm beeLogForm)  throws Exception
    {
    	BeeLogService beeLogService = (BeeLogService) getBean("beeLogService");

    	Map map = getMapData(request);
    	int qty = beeLogService.saveLoginLog(map);
    	setMessage(response, "","OK");
    }
}
