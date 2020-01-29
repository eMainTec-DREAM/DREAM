package dream.budget.plan.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.budget.plan.list.dto.MaPtDeptBgDetailDTO;
import dream.budget.plan.list.form.MaPtDeptBgDetailForm;
import dream.budget.plan.list.service.MaPtDeptBgDetailService;

/**
 * ��ǰ�ŷ�ó
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/maPtDeptBgDetail" name="maPtDeptBgDetailForm"
 *                input="/dream/budget/plan/list/maPtDeptBgDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtDeptBgDetail" path="/dream/budget/plan/list/maPtDeptBgDetail.jsp"
 *                        redirect="false"
 */
public class MaPtDeptBgDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int PTDEPT_BUDGET_DETAIL_INIT 	= 8001;
    /** ���� */
    public static final int PTDEPT_BUDGET_DETAIL_UPDATE = 6002;
    /** �Է� */
    public static final int PTDEPT_BUDGET_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtDeptBgDetailForm maPtDeptBgDetailForm = (MaPtDeptBgDetailForm) form;
        
        super.updateAudit(maPtDeptBgDetailForm.getMaPtDeptBgDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtDeptBgDetailForm.getStrutsAction())
        {
            case MaPtDeptBgDetailAction.PTDEPT_BUDGET_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, maPtDeptBgDetailForm);
                returnActionForward = mapping.findForward("maPtDeptBgDetail");
                break;
            case MaPtDeptBgDetailAction.PTDEPT_BUDGET_DETAIL_UPDATE:
            	updateDetail(maPtDeptBgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtDeptBgDetailAction.PTDEPT_BUDGET_DETAIL_INPUT:
            	insertDetail(maPtDeptBgDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtDeptBgDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     *  �� ��ȸ
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtDeptBgDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtDeptBgDetailForm maPtDeptBgDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	MaPtDeptBgDetailService maPtDeptBgDetailService = (MaPtDeptBgDetailService)getBean("maPtDeptBgDetailService");

        // ��ȸ�� �� �κ�
        MaPtDeptBgDetailDTO maPtDeptBgDetailDTO 
            = maPtDeptBgDetailService.findDetail(maPtDeptBgDetailForm.getMaPtBudgetCommonDTO(), getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        maPtDeptBgDetailForm.setMaPtDeptBgDetailDTO(maPtDeptBgDetailDTO);
    }
    
    /**
     * detail update
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailForm
     * @param request
     */
    private void updateDetail(MaPtDeptBgDetailForm maPtDeptBgDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtDeptBgDetailService maPtDeptBgDetailService = (MaPtDeptBgDetailService) getBean("maPtDeptBgDetailService");
        
        MaPtDeptBgDetailDTO maPtDeptBgDetailDTO = maPtDeptBgDetailForm.getMaPtDeptBgDetailDTO();
        
        maPtDeptBgDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtDeptBgDetailService.updateDetail(maPtDeptBgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtDeptBgDetailForm
     * @param request
     */
    private void insertDetail(MaPtDeptBgDetailForm maPtDeptBgDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtDeptBgDetailService maPtDeptBgDetailService = (MaPtDeptBgDetailService) getBean("maPtDeptBgDetailService");
        
        MaPtDeptBgDetailDTO maPtDeptBgDetailDTO = maPtDeptBgDetailForm.getMaPtDeptBgDetailDTO();
        
        maPtDeptBgDetailDTO.setCompNo((getUser(request).getCompNo()));
        // ����Id ����
//        String partId = maPtDeptBgDetailForm.getMaPtBudgetCommonDTO().getPartId();
//        maPtDeptBgDetailDTO.setPartId(partId);
        
        maPtDeptBgDetailService.insertDetail(maPtDeptBgDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}