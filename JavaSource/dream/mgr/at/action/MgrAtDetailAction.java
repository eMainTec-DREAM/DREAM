package dream.mgr.at.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.mgr.at.dto.MgrAtCommonDTO;
import dream.mgr.at.dto.MgrAtDetailDTO;
import dream.mgr.at.form.MgrAtDetailForm;
import dream.mgr.at.service.MgrAtDetailService;

/**
 * Audit Trail - 상세 action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/mgrAtDetail" name="mgrAtDetailForm"
 *                input="/dream/mgr/at/mgrAtDetail.jsp" scope="request"
 *                validate="false"
 */
public class MgrAtDetailAction extends SuperAuthAction
{
	/** Audit Trail 조회 */
    public static final int DETAIL_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrAtDetailForm mgrAtDetailForm = (MgrAtDetailForm) form;
        
        switch (mgrAtDetailForm.getStrutsAction())
        {
            case MgrAtDetailAction.DETAIL_FIND:
            	findDetail(mgrAtDetailForm, request);
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
     * @param mgrAtDetailForm
     */
    private void findDetail(MgrAtDetailForm mgrAtDetailForm, HttpServletRequest request)throws Exception 
    {   
    	MgrAtDetailService mgrAtDetailService = (MgrAtDetailService)getBean("mgrAtDetailService");
    	
    	MgrAtCommonDTO mgrAtCommonDTO = mgrAtDetailForm.getMgrAtCommonDTO();
    	
    	MgrAtDetailDTO mgrAtDetailDTO = mgrAtDetailService.findDetail(mgrAtCommonDTO, getUser(request));
    	
        mgrAtDetailForm.setMgrAtDetailDTO(mgrAtDetailDTO);
    }
}