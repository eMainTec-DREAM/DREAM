package dream.work.list.energy.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.energy.dto.WorkListEnergyMstrDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.form.WorkListEnergyMstrDetailForm;
import dream.work.list.energy.service.WorkListEnergyMstrDetailService;

/**
 * ���������� - �������� ��� �� action
 * @author sy.yang
 * @version $Id: WorkListEnergyMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
 * @since 1.0
 * @struts:action path="/workListEnergyMstrDetail" name="workListEnergyMstrDetailForm"
 *                input="/dream/work/list/energy/workListEnergyMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListEnergyMstrDetail" path=/dream/work/list/energy/workListEnergyMstrDetail.jsp"
 *                        redirect="false"
 */
public class WorkListEnergyMstrDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_PMU_DETAIL_INIT 			= 8001;
    /** ���� */
    public static final int WORK_PMU_DETAIL_INPUT 			= 5001;
    /** ���� */
    public static final int WORK_PMU_DETAIL_UPDATE 			= 6001;
    /** �����׸� ���忩�� �˻� */
    public static final int WORK_PMU_DETAIL_CHECKPOINT 		= 8002;
    /** �����׸� ���� ��ϰ�(����,�ð�����) ���� Ȯ�� */
    public static final int WORK_PMU_DETAIL_IS_LAST_POINT 	= 8003;
    /** Ȯ���� üũ */
    public static final int WORK_PMU_DETAIL_CHECK_CONFIRM	= 8004;
    /** �Ϸ� */
    public static final int WORK_PMU_DETAIL_COMPLETE 		= 6002;
    /** �Ϸ���� */
    public static final int WORK_PMU_DETAIL_COMPLETE_CANCEL	= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm = (WorkListEnergyMstrDetailForm) form;

        super.updateAudit(workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO().getAuditKey()==""?workListEnergyMstrDetailForm.getWorkListEnergyMstrListCommonDTO().getAuditKey():workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListEnergyMstrDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (workListEnergyMstrDetailForm.getStrutsAction())
        {
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workListEnergyMstrDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_INPUT:
            	insertDetail(workListEnergyMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_UPDATE:
            	updateDetail(workListEnergyMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_COMPLETE:
            	completeDetail(workListEnergyMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_COMPLETE_CANCEL:
            	completeCancelDetail(workListEnergyMstrDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_CHECKPOINT:
            	checkPoint(workListEnergyMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_CHECK_CONFIRM:
            	checkConfirm(workListEnergyMstrDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListEnergyMstrDetailAction.WORK_PMU_DETAIL_IS_LAST_POINT:
            	isLastPoint(workListEnergyMstrDetailForm, request);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * �۾���� �� ��ȸ
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListEnergyMstrDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService)getBean("workListEnergyMstrDetailService", request);

    	WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrListCommonDTO();
    	
    	User user = getUser(request);
    	
        // ��ȸ�� �� �κ�
        WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailService.findDetail(workListEnergyMstrListCommonDTO, user);
        
        // ��ȸ�� ��  �����Ѵ�.
        workListEnergyMstrDetailForm.setWorkListEnergyMstrDetailDTO(workListEnergyMstrDetailDTO);
    }
    
    /**
     * detail insert
     * @author  sy.yang
     * @version $Id: MaWoResultMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailForm
     * @param request
     */
    private void insertDetail(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
        
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrListCommonDTO();
        WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
        
        workListEnergyMstrDetailService.insertDetail(workListEnergyMstrListCommonDTO, workListEnergyMstrDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    
    /**
     * detail update
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailForm
     * @param request
     */
    private void updateDetail(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
        
        WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrListCommonDTO();
        
        User user = getUser(request);
        
        workListEnergyMstrDetailService.updateDetail(workListEnergyMstrDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail complete
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailForm
     * @param request
     */
    private void completeDetail(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
        
        WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
        
        User user = getUser(request);
        
        workListEnergyMstrDetailService.completeDetail(workListEnergyMstrDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail complete Cancel
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrDetailForm
     * @param request
     */
    private void completeCancelDetail(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
    	
    	WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
    	
    	User user = getUser(request);
    	
    	workListEnergyMstrDetailService.completeCancelDetail(workListEnergyMstrDetailDTO, user);
    	
    	setAjaxStatus(request);
    }
    
    private void checkPoint(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
            	
    	WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
    	
    	String isValid = workListEnergyMstrDetailService.checkPoint(workListEnergyMstrDetailDTO,getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
    
    private void isLastPoint(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
    	
    	WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
    	
    	String isValid = workListEnergyMstrDetailService.isLastPoint(workListEnergyMstrDetailDTO,getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
    
    private void checkConfirm(WorkListEnergyMstrDetailForm workListEnergyMstrDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyMstrDetailService workListEnergyMstrDetailService = (WorkListEnergyMstrDetailService) getBean("workListEnergyMstrDetailService", request);
            	
    	WorkListEnergyMstrDetailDTO workListEnergyMstrDetailDTO = workListEnergyMstrDetailForm.getWorkListEnergyMstrDetailDTO();
    	
    	String isValid = workListEnergyMstrDetailService.checkConfirm(workListEnergyMstrDetailDTO,getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
}