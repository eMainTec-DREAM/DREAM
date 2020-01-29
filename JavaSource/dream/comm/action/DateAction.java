package dream.comm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import common.util.DateUtil;
import dream.comm.form.DateForm;
import dream.comm.service.DateService;

/**
 * Date Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/getDate" name="dateForm"
 *                scope="request"
 *                validate="false"
 */
public class DateAction extends SuperAuthAction
{
	/** Get Date Time */
    public static final int GET_TIME_STAMP   = 1001;
    /** Get Time Gap */
    public static final int GET_TIME_GAP     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        DateForm dateForm = (DateForm) form;
        
        switch (dateForm.getStrutsAction())
        {
	        case DateAction.GET_TIME_STAMP:
	            getTimeStamp(dateForm, request);
	        	returnActionForward = mapping.findForward("ajaxXmlVal");
	            break;
	        case DateAction.GET_TIME_GAP:
	            getTimeGap(dateForm, request);
	            returnActionForward = mapping.findForward("ajaxXmlVal");
	            break;
            default:
                getTimeStamp(dateForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
        }

        return returnActionForward;
    }
    
    private void getTimeStamp(DateForm dateForm, HttpServletRequest request) throws Exception
    {
    	String date = DateUtil.getTimeStamp(getUser(request).getOffset(), dateForm.getFormat(), dateForm.getIntervalType(), dateForm.getInterval());
        
    	setAjaxDesc(request, date);
    }
    
    private void getTimeGap(DateForm dateForm, HttpServletRequest request) throws Exception
    {
        DateService dateService = (DateService) getBean("dateService");
        
        String timeGap = dateService.getTimeGap(request.getSession(), dateForm.getLocalTime());
        
        setAjaxDesc(request, timeGap);
    }
    
}