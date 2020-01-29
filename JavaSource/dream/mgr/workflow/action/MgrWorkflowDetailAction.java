package dream.mgr.workflow.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowDetailDTO;
import dream.mgr.workflow.form.MgrWorkflowDetailForm;
import dream.mgr.workflow.service.MgrWorkflowDetailService;

/**
 * Wokrflow Page - Detail Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrWorkflowDetail" name="mgrWorkflowDetailForm"
 *                input="/dream/mgr/workflow/mgrWorkflowDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkflowDetail" path="/dream/mgr/workflow/mgrWorkflowDetail.jsp"
 *                        redirect="false"
 */
public class MgrWorkflowDetailAction extends AuthAction
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
        MgrWorkflowDetailForm mgrWorkflowDetailForm = (MgrWorkflowDetailForm) form;
        
        super.updateAudit(mgrWorkflowDetailForm.getMgrWorkflowDetailDTO().getAuditKey()==""?mgrWorkflowDetailForm.getMgrWorkflowCommonDTO().getAuditKey():mgrWorkflowDetailForm.getMgrWorkflowDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (mgrWorkflowDetailForm.getStrutsAction())
        {
            case MgrWorkflowDetailAction.DETAIL_INIT:
                this.findDetail(request, response, mgrWorkflowDetailForm);
                returnActionForward = mapping.findForward("mgrWorkflowDetail");
                break;
            case MgrWorkflowDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, mgrWorkflowDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MgrWorkflowDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, mgrWorkflowDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("mgrWorkflowDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param mgrWorkflowDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkflowDetailForm mgrWorkflowDetailForm) throws Exception 
    {   
    	MgrWorkflowDetailService mgrWorkflowDetailService = (MgrWorkflowDetailService)getBean("mgrWorkflowDetailService");
    	
    	MgrWorkflowCommonDTO mgrWorkflowCommonDTO = mgrWorkflowDetailForm.getMgrWorkflowCommonDTO(); 

    	User user = getUser(request);
    	mgrWorkflowCommonDTO.setCompNo(user.getCompNo());
    	
    	MgrWorkflowDetailDTO mgrWorkflowDetailDTO = mgrWorkflowDetailService.findWorkflowDetail(mgrWorkflowCommonDTO, user);
    	mgrWorkflowDetailForm.setMgrWorkflowDetailDTO(mgrWorkflowDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param mgrWorkflowDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkflowDetailForm mgrWorkflowDetailForm) throws Exception
    {
    	MgrWorkflowDetailService mgrWorkflowDetailService = (MgrWorkflowDetailService)getBean("mgrWorkflowDetailService");
    	MgrWorkflowDetailDTO  mgrWorkflowDetailDTO = mgrWorkflowDetailForm.getMgrWorkflowDetailDTO(); 
    	
    	User user = getUser(request);
    	mgrWorkflowDetailDTO.setCompNo(user.getCompNo());
    	
    	mgrWorkflowDetailService.insertWorkflowDetail(mgrWorkflowDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param mgrWorkflowDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, MgrWorkflowDetailForm mgrWorkflowDetailForm) throws Exception
    {
    	MgrWorkflowDetailService mgrWorkflowDetailService = (MgrWorkflowDetailService)getBean("mgrWorkflowDetailService");
    	MgrWorkflowDetailDTO  mgrWorkflowDetailDTO = mgrWorkflowDetailForm.getMgrWorkflowDetailDTO();
    	
    	User user = getUser(request);
    	mgrWorkflowDetailDTO.setCompNo(user.getCompNo());
    	
    	mgrWorkflowDetailService.updateWorkflowDetail(mgrWorkflowDetailDTO, user);
        
        setAjaxStatus(request);
    }

}