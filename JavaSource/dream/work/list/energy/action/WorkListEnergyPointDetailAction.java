package dream.work.list.energy.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;
import dream.work.list.energy.form.WorkListEnergyPointDetailForm;
import dream.work.list.energy.service.WorkListEnergyPointDetailService;

/**
 * ������ �� �����׸� �� action
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailAction.java,v 1.0 2015/12/04 09:09:30 sy.yang Exp $
 * @since   1.0
 * @struts:action path="/workListEnergyPointDetail" name="workListEnergyPointDetailForm"
 *                input="/dream/work/list/energy/workListEnergyPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListEnergyPointDetail" path="/dream/work/list/energy/workListEnergyPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkListEnergyPointDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_PMU_POINT_DETAIL_INIT 		= 8001;
    /** ���� */
    public static final int WORK_PMU_POINT_DETAIL_UPDATE 	= 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListEnergyPointDetailForm workListEnergyPointDetailForm = (WorkListEnergyPointDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListEnergyPointDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workListEnergyPointDetailForm.getWorkListEnergyPointDetailDTO().getAuditKey()==""?workListEnergyPointDetailForm.getWorkListEnergyPointListDTO().getAuditKey():workListEnergyPointDetailForm.getWorkListEnergyPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListEnergyPointDetailForm.getStrutsAction())
        {
            case WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workListEnergyPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_UPDATE:
            	updateDetail(workListEnergyPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �������� - �����׸� �� ��ȸ
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListEnergyPointDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListEnergyPointDetailForm workListEnergyPointDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkListEnergyPointDetailService workListEnergyPointDetailService = (WorkListEnergyPointDetailService)getBean("workListEnergyPointDetailService");

    	WorkListEnergyPointListDTO workListEnergyPointListDTO = workListEnergyPointDetailForm.getWorkListEnergyPointListDTO();
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyPointDetailForm.getWorkListEnergyMstrListCommonDTO();
        
//    	// �۾����Id ����
//        String pminslistId = workListEnergyPointDetailForm.getWorkListEnergyMstrListCommonDTO().getPminslistId();
//        // �Ѱ��� �����׸�id ����
//        String pmInsPointId = workListEnergyPointDetailForm.getWorkListEnergyPointListDTO().getPmInsPointId();
//        // �Ѱ��� �����׸�id ����
//        String pmPointId = workListEnergyPointDetailForm.getWorkListEnergyPointListDTO().getPmPointId();
        
        // ��ȸ�� �� �κ�
        WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = workListEnergyPointDetailService.findDetail(workListEnergyMstrListCommonDTO, workListEnergyPointListDTO, getUser(request));
        
        // ��ȸ�� ��  �����Ѵ�.
        workListEnergyPointDetailForm.setWorkListEnergyPointDetailDTO(workListEnergyPointDetailDTO);
    }
    /**
     * detail update
     * @author  sy.yang
     * @version $Id: WorkListEnergyPointDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyPointDetailForm
     * @param request
     */
    private void updateDetail(WorkListEnergyPointDetailForm workListEnergyPointDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyPointDetailService workListEnergyPointDetailService = (WorkListEnergyPointDetailService) getBean("workListEnergyPointDetailService");
        
        WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = workListEnergyPointDetailForm.getWorkListEnergyPointDetailDTO();
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyPointDetailForm.getWorkListEnergyMstrListCommonDTO();
        
        User user = getUser(request);
        
        workListEnergyPointDetailService.updateDetail(workListEnergyPointDetailDTO,workListEnergyMstrListCommonDTO,user);
        
        setAjaxStatus(request);
    }
    
}