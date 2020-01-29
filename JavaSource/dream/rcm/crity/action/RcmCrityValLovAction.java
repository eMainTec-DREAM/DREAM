package dream.rcm.crity.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.form.RcmCrityValLovForm;
import dream.rcm.crity.service.RcmCrityValLovService;

/**
 * Criticality Matrix Page - List Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityValLov" name="rcmCrityValLovForm"
 *                input="/dream/rcm/crity/rcmCrityValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityValLov" path="/dream/rcm/crity/rcmCrityValLov.jsp"
 *                        redirect="false"
 */

public class RcmCrityValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityValLovForm rcmCrityValLovForm = (RcmCrityValLovForm) form;
        
        switch (rcmCrityValLovForm.getStrutsAction())
        {
            case RcmCrityValLovAction.BASE_SET_HEADER:
                setHeader(request, response, rcmCrityValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case RcmCrityValLovAction.LIST_FIND:
                findList(request, response, rcmCrityValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;       
            case RcmCrityValLovAction.BASE_GRID_EXPORT:
            	findList(request, response, rcmCrityValLovForm);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("rcmCrityValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, RcmCrityValLovForm rcmCrityValLovForm) throws IOException
    {
        super.setHeader(request, response, rcmCrityValLovForm.getListId(), rcmCrityValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, RcmCrityValLovForm rcmCrityValLovForm) throws Exception
    {
    	RcmCrityValLovService rcmCrityValLovService = (RcmCrityValLovService) getBean("rcmCrityValLovService");

        List resultList = rcmCrityValLovService.findList(rcmCrityValLovForm, getUser(request));
        super.makeJsonResult(resultList, request, response, rcmCrityValLovForm.getListId());
        
    }
    
}
