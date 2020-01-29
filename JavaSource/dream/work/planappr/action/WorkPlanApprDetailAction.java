package dream.work.planappr.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.dto.WorkPlanApprDetailDTO;
import dream.work.planappr.form.WorkPlanApprDetailForm;
import dream.work.planappr.service.WorkPlanApprDetailService;

/**
 * �۾���ȹ���� - �� action
 * 
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @struts:action path="/workPlanApprDetail" name="workPlanApprDetailForm"
 *                input="/dream/work/planappr/workPlanApprDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPlanApprDetail" path=/dream/work/planappr/workPlanApprDetail.jsp"
 *                        redirect="false"
 */
public class WorkPlanApprDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int DETAIL_INIT 			= 8001;
    /** ���� */
    public static final int DETAIL_INPUT 			= 5002;
    /** ���� */
    public static final int DETAIL_UPDATE 			= 6003;
    /** �ߺ��˻� */
    public static final int DETAIL_DUPLICATE_CHECK	= 8004;
    /** Ȯ�� */
    public static final int DETAIL_STATUS_UPDATE	= 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPlanApprDetailForm workPlanApprDetailForm = (WorkPlanApprDetailForm) form;
        
        super.updateAudit(workPlanApprDetailForm.getWorkPlanApprDetailDTO().getAuditKey()==""?workPlanApprDetailForm.getWorkPlanApprCommonDTO().getAuditKey():workPlanApprDetailForm.getWorkPlanApprDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workPlanApprDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (workPlanApprDetailForm.getStrutsAction())
        {
            case WorkPlanApprDetailAction.DETAIL_INIT:
                this.findDetail(request, workPlanApprDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPlanApprDetailAction.DETAIL_INPUT:
            	this.insertDetail(workPlanApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPlanApprDetailAction.DETAIL_UPDATE:
            	this.updateDetail(workPlanApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPlanApprDetailAction.DETAIL_STATUS_UPDATE:
            	this.updateStatus(workPlanApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPlanApprDetailAction.DETAIL_DUPLICATE_CHECK:
            	this.checkAppr(workPlanApprDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void findDetail(HttpServletRequest request, WorkPlanApprDetailForm workPlanApprDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkPlanApprDetailService workPlanApprDetailService = (WorkPlanApprDetailService)getBean("workPlanApprDetailService");

    	WorkPlanApprCommonDTO workPlanApprCommonDTO = workPlanApprDetailForm.getWorkPlanApprCommonDTO();
        // ��ȸ�� �� �κ�
        WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprDetailService.findDetail(workPlanApprCommonDTO, getUser(request));
        // ��ȸ�� ��  �����Ѵ�.
        workPlanApprDetailForm.setWorkPlanApprDetailDTO(workPlanApprDetailDTO);
    }
    private void insertDetail(WorkPlanApprDetailForm workPlanApprDetailForm, HttpServletRequest request) throws Exception
    {
        WorkPlanApprDetailService workPlanApprDetailService = (WorkPlanApprDetailService) getBean("workPlanApprDetailService");
        
        WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprDetailForm.getWorkPlanApprDetailDTO();
        WorkPlanApprCommonDTO workPlanApprCommonDTO = workPlanApprDetailForm.getWorkPlanApprCommonDTO();
        
        workPlanApprDetailService.insertDetail(workPlanApprDetailDTO, workPlanApprCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    private void updateDetail(WorkPlanApprDetailForm workPlanApprDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPlanApprDetailService workPlanApprDetailService = (WorkPlanApprDetailService) getBean("workPlanApprDetailService");
        
        WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprDetailForm.getWorkPlanApprDetailDTO();
        WorkPlanApprCommonDTO workPlanApprCommonDTO = workPlanApprDetailForm.getWorkPlanApprCommonDTO();
        
        workPlanApprDetailService.updateDetail(workPlanApprDetailDTO, workPlanApprCommonDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    private void updateStatus(WorkPlanApprDetailForm workPlanApprDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPlanApprDetailService workPlanApprDetailService = (WorkPlanApprDetailService) getBean("workPlanApprDetailService");
        
        WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprDetailForm.getWorkPlanApprDetailDTO();
        
        workPlanApprDetailService.updateStatus(workPlanApprDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void checkAppr(WorkPlanApprDetailForm workPlanApprDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPlanApprDetailService workPlanApprDetailService = (WorkPlanApprDetailService) getBean("workPlanApprDetailService");
    	
    	WorkPlanApprDetailDTO workPlanApprDetailDTO = workPlanApprDetailForm.getWorkPlanApprDetailDTO();
    	
    	String isValid = workPlanApprDetailService.checkAppr(workPlanApprDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
}