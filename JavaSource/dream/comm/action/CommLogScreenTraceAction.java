package dream.comm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;

import dream.comm.dto.CommLogScrnTraceDTO;
import dream.comm.form.CommLogScreenTraceForm;
import dream.comm.service.CommLogScreenTraceService;

/**
 * 설비종류 - 상세 action
 * 
 * @author jung7126
 * @version $Id: CommLogScreenTraceAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/logScreenTrace" name="commLogScreenTraceForm"
 *                input="/dream/app/tracelog/appTracelogList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="logScreenTrace" path="/dream/app/tracelog/appTracelogList.jsp"
 *                        redirect="false"
 */
public class CommLogScreenTraceAction extends SuperAuthAction
{
	/** 로깅 페이지 */
    public static final int COMM_LOG_SCREEN_TRACE 		    = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        CommLogScreenTraceForm commLogScreenTraceForm = (CommLogScreenTraceForm) form;
        
        switch (commLogScreenTraceForm.getStrutsAction())
        {
	        case CommLogScreenTraceAction.COMM_LOG_SCREEN_TRACE:
	        	logScreenTrace(commLogScreenTraceForm, request);
	        	returnActionForward = mapping.findForward("ajaxXmlVal");
	            break;
	        
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    
    private void logScreenTrace(CommLogScreenTraceForm commLogScreenTraceForm, HttpServletRequest request) throws Exception
    {
    	CommLogScreenTraceService commLogScreenTraceService = (CommLogScreenTraceService) getBean("commLogScreenTraceService");
        
    	CommLogScrnTraceDTO commLogScrnTraceDTO = commLogScreenTraceForm.getCommLogScrnTraceDTO();
    	
        try{
        	commLogScreenTraceService.insertLogScreenTrace(commLogScrnTraceDTO, getUser(request));
        }catch(Exception e){
        	
        }
        
        setAjaxStatus(request);
    }
    
    
}