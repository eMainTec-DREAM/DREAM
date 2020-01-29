package dream.mgr.audtrail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.mgr.audtrail.dto.MgrAudTrailCommonDTO;
import dream.mgr.audtrail.dto.MgrAudTrailDetailDTO;
import dream.mgr.audtrail.form.MgrAudTrailDetailForm;
import dream.mgr.audtrail.service.MgrAudTrailDetailService;

/**
 * Audit Trail - 상세 action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrAudTrailDetail" name="mgrAudTrailDetailForm"
 *                input="/dream/mgr/audtrail/mgrAudTrailDetail.jsp" scope="request"
 *                validate="false"
 */
public class MgrAudTrailDetailAction extends SuperAuthAction
{
	/** Audit Trail 조회 */
    public static final int DETAIL_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrAudTrailDetailForm mgrAudTrailDetailForm = (MgrAudTrailDetailForm) form;
        
        switch (mgrAudTrailDetailForm.getStrutsAction())
        {
            case MgrAudTrailDetailAction.DETAIL_FIND:
            	findDetail(mgrAudTrailDetailForm, request);
                returnActionForward = mapping.getInputForward();
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * Param 조회
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param mgrAudTrailDetailForm
     */
    private void findDetail(MgrAudTrailDetailForm mgrAudTrailDetailForm, HttpServletRequest request)throws Exception 
    {   
    	MgrAudTrailDetailService mgrAudTrailDetailService = (MgrAudTrailDetailService)getBean("mgrAudTrailDetailService");
    	
    	MgrAudTrailCommonDTO mgrAudTrailCommonDTO = mgrAudTrailDetailForm.getMgrAudTrailCommonDTO();
    	
    	MgrAudTrailDetailDTO mgrAudTrailDetailDTO = mgrAudTrailDetailService.findDetail(mgrAudTrailCommonDTO, getUser(request));
    	
        mgrAudTrailDetailForm.setMgrAudTrailDetailDTO(mgrAudTrailDetailDTO);
    }
}