package dream.mgr.workflow.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.workflow.dto.MgrWorkflowPhaseDetailDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.form.MgrWorkflowPhaseDetailForm;
import dream.mgr.workflow.service.MgrWorkflowPhaseDetailService;

/**
 * Wokrflow Phase Page - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrWorkflowPhaseDetail" name="mgrWorkflowPhaseDetailForm"
 *                input="/dream/mgr/workflow/mgrWorkflowPhaseDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkflowPhaseDetail" path="/dream/mgr/workflow/mgrWorkflowPhaseDetail.jsp"
 *                        redirect="false"
 */
public class MgrWorkflowPhaseDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MgrWorkflowPhaseDetailForm mgrWorkflowPhaseDetailForm = (MgrWorkflowPhaseDetailForm) form;
        
        super.updateAudit(mgrWorkflowPhaseDetailForm.getMgrWorkflowPhaseDetailDTO().getAuditKey()==""?mgrWorkflowPhaseDetailForm.getMgrWorkflowPhaseListDTO().getAuditKey():mgrWorkflowPhaseDetailForm.getMgrWorkflowPhaseDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrWorkflowPhaseDetailForm.getStrutsAction())
        {
            case MgrWorkflowPhaseDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrWorkflowPhaseDetailForm);
                returnActionForward = mapping.findForward("mgrWorkflowPhaseDetail");
                break;
            case MgrWorkflowPhaseDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrWorkflowPhaseDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrWorkflowPhaseDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrWorkflowPhaseDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrWorkflowPhaseDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrWorkflowPhaseDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkflowPhaseDetailForm mgrWorkflowPhaseDetailForm) throws Exception 
    {   
    	MgrWorkflowPhaseDetailService mgrWorkflowPhaseDetailService = (MgrWorkflowPhaseDetailService)getBean("mgrWorkflowPhaseDetailService");
    	
    	MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO = mgrWorkflowPhaseDetailForm.getMgrWorkflowPhaseListDTO(); 

    	User user = getUser(request);
    	
    	MgrWorkflowPhaseDetailDTO mgrWorkflowPhaseDetailDTO = mgrWorkflowPhaseDetailService.findWorkflowPhaseDetail(mgrWorkflowPhaseListDTO, user);
    	mgrWorkflowPhaseDetailForm.setMgrWorkflowPhaseDetailDTO(mgrWorkflowPhaseDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param mgrWorkflowPhaseDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkflowPhaseDetailForm mgrWorkflowPhaseDetailForm) throws Exception
    {
    	MgrWorkflowPhaseDetailService mgrWorkflowPhaseDetailService = (MgrWorkflowPhaseDetailService)getBean("mgrWorkflowPhaseDetailService");
    	MgrWorkflowPhaseDetailDTO  mgrWorkflowPhaseDetailDTO = mgrWorkflowPhaseDetailForm.getMgrWorkflowPhaseDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	mgrWorkflowPhaseDetailService.insertWorkflowPhaseDetail(mgrWorkflowPhaseDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrWorkflowPhaseDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkflowPhaseDetailForm mgrWorkflowPhaseDetailForm) throws Exception
    {
    	MgrWorkflowPhaseDetailService mgrWorkflowPhaseDetailService = (MgrWorkflowPhaseDetailService)getBean("mgrWorkflowPhaseDetailService");
    	MgrWorkflowPhaseDetailDTO  mgrWorkflowPhaseDetailDTO = mgrWorkflowPhaseDetailForm.getMgrWorkflowPhaseDetailDTO();
    	
    	User user = getUser(request);
    	
    	mgrWorkflowPhaseDetailService.updateWorkflowPhaseDetail(mgrWorkflowPhaseDetailDTO, user);
        
        setAjaxStatus(request);
    }

}