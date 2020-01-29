package dream.rcm.crity.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.rcm.crity.dto.RcmCrityCommonDTO;
import dream.rcm.crity.dto.RcmCrityMatrixDTO;
import dream.rcm.crity.form.RcmCrityMatrixForm;
import dream.rcm.crity.service.RcmCrityMatrixService;

/**
 * Criticality Matrix Page - Matrix Action
 * 
 * @author kim21017
 * @version $Id: RcmCrityMatrixAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/rcmCrityMatrix" name="rcmCrityMatrixForm"
 *                input="/dream/rcm/crity/rcmCrityMatrix.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="rcmCrityMatrix" path="/dream/rcm/crity/rcmCrityMatrix.jsp"
 *                        redirect="false"
 */

public class RcmCrityMatrixAction extends SuperAuthAction
{
    /** Col 조회 */
    public static final int COL_FIND 		= 1001;
    /** Val 조회 */
    public static final int VAL_FIND 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        RcmCrityMatrixForm rcmCrityMatrixForm = (RcmCrityMatrixForm) form;
        
        switch (rcmCrityMatrixForm.getStrutsAction())
        {
            case RcmCrityMatrixAction.COL_FIND:
            	findCol(request, response, rcmCrityMatrixForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case RcmCrityMatrixAction.VAL_FIND:
            	findVal(request, response, rcmCrityMatrixForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("rcmCrityMatrix");
                break;
        }
        return returnActionForward;
    }
    /**
     * FIND COL
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityMatrixForm
     */
    private void findCol(HttpServletRequest request, HttpServletResponse response, RcmCrityMatrixForm rcmCrityMatrixForm) throws Exception
    {
    	RcmCrityMatrixService rcmCrityMatrixService = (RcmCrityMatrixService) getBean("rcmCrityMatrixService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityMatrixForm.getRcmCrityCommonDTO();
    	RcmCrityMatrixDTO rcmCrityMatrixDTO = rcmCrityMatrixForm.getRcmCrityMatrixDTO();
    	String[][] resultList = rcmCrityMatrixService.findCol(rcmCrityCommonDTO,rcmCrityMatrixDTO, getUser(request));
    	setAjaxData(request, resultList);
        
    }
    /**
     * FIND VAL
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param rcmCrityMatrixForm
     */
    private void findVal(HttpServletRequest request, HttpServletResponse response, RcmCrityMatrixForm rcmCrityMatrixForm) throws Exception
    {
    	RcmCrityMatrixService rcmCrityMatrixService = (RcmCrityMatrixService) getBean("rcmCrityMatrixService");
    	RcmCrityCommonDTO rcmCrityCommonDTO = rcmCrityMatrixForm.getRcmCrityCommonDTO();
    	RcmCrityMatrixDTO rcmCrityMatrixDTO = rcmCrityMatrixForm.getRcmCrityMatrixDTO();
    	String[][] resultList = rcmCrityMatrixService.findVal(rcmCrityCommonDTO,rcmCrityMatrixDTO, getUser(request));
    	setAjaxData(request, resultList);
        
    }
}
