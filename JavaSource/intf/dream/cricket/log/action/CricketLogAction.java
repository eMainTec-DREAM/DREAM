package intf.dream.cricket.log.action;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.cricket.log.form.CricketLogForm;
import intf.dream.cricket.log.service.CricketLogService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/cricketLog" name="cricketLogForm"
 *                input="/ant/log/cricketLog.jsp" scope="request"
 *                validate="false"
 */
public class CricketLogAction extends IfAuthAction
{
    //Login Log
    public static final String LOGINLOG 				= "LOGINLOG";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        CricketLogForm cricketLogForm = (CricketLogForm) form;
        
        switch (cricketLogForm.getServiceName())
        {
            case CricketLogAction.LOGINLOG:
            	saveLoginLog(request, response, cricketLogForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void saveLoginLog(HttpServletRequest request, HttpServletResponse response, CricketLogForm cricketLogForm)  throws Exception
    {
    	CricketLogService cricketLogService = (CricketLogService) getBean("cricketLogService");

    	Map map = getMapData(request);
    	int qty = cricketLogService.saveLoginLog(map);
    	setMessage(response, "","OK");
    }
}
