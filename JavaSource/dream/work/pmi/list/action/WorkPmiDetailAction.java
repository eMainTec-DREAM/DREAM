package dream.work.pmi.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiDetailDTO;
import dream.work.pmi.list.form.WorkPmiDetailForm;
import dream.work.pmi.list.service.WorkPmiDetailService;

/**
 * �����۾� - �� action
 * 
 * @author kim2107
 * @version $Id: WorkPmiDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmiDetail" name="workPmiDetailForm"
 *                input="/dream/work/pmi/list/workPmiDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiDetail" path=/dream/work/pmi/list/workPmiDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmiDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int WORK_PMI_DETAIL_INIT 			= 8001;
    /** ���� */
    public static final int WORK_PMI_DETAIL_UPDATE 			= 6003;
    /** �Ϸ� */
    public static final int WORK_PMI_DETAIL_COMPLETE 		= 6004;
    /** �Ϸ� */
    public static final int WORK_PMI_DETAIL_CHECKPOINT 		= 6005;
    /** �Ϸ���� */
    public static final int WORK_PMI_DETAIL_COMPLETE_CANCEL	= 6006;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiDetailForm workPmiDetailForm = (WorkPmiDetailForm) form;

        super.updateAudit(workPmiDetailForm.getWorkPmiDetailDTO().getAuditKey()==""?workPmiDetailForm.getWorkPmiCommonDTO().getAuditKey():workPmiDetailForm.getWorkPmiDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workPmiDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (workPmiDetailForm.getStrutsAction())
        {
            case WorkPmiDetailAction.WORK_PMI_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workPmiDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmiDetailAction.WORK_PMI_DETAIL_UPDATE:
            	updateDetail(workPmiDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmiDetailAction.WORK_PMI_DETAIL_COMPLETE:
            	completeDetail(workPmiDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmiDetailAction.WORK_PMI_DETAIL_CHECKPOINT:
            	checkPoint(workPmiDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmiDetailAction.WORK_PMI_DETAIL_COMPLETE_CANCEL:
            	completeCancelDetail(workPmiDetailForm, request);
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
     * @author kim2107
     * @version $Id: WorkPmiDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmiDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmiDetailForm workPmiDetailForm)throws Exception 
    {   
        // Service ��ü ����
    	WorkPmiDetailService workPmiDetailService = (WorkPmiDetailService)getBean("workPmiDetailService", request);

    	WorkPmiCommonDTO workPmiCommonDTO = workPmiDetailForm.getWorkPmiCommonDTO();
    	
    	User user = getUser(request);
    	
        // ��ȸ�� �� �κ�
        WorkPmiDetailDTO workPmiDetailDTO = workPmiDetailService.findDetail(workPmiCommonDTO, user);
        
        // ��ȸ�� ��  �����Ѵ�.
        workPmiDetailForm.setWorkPmiDetailDTO(workPmiDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkPmiDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDetailForm
     * @param request
     */
    private void updateDetail(WorkPmiDetailForm workPmiDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmiDetailService workPmiDetailService = (WorkPmiDetailService) getBean("workPmiDetailService", request);
        
        WorkPmiDetailDTO workPmiDetailDTO = workPmiDetailForm.getWorkPmiDetailDTO();
        WorkPmiCommonDTO workPmiCommonDTO = workPmiDetailForm.getWorkPmiCommonDTO();
        
        User user = getUser(request);
        
        workPmiDetailService.updateDetail(workPmiDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    /**
     * detail complete
     * @author  kim21017
     * @version $Id: WorkPmiDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDetailForm
     * @param request
     */
    private void completeDetail(WorkPmiDetailForm workPmiDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmiDetailService workPmiDetailService = (WorkPmiDetailService) getBean("workPmiDetailService", request);
        
        WorkPmiDetailDTO workPmiDetailDTO = workPmiDetailForm.getWorkPmiDetailDTO();
        
        User user = getUser(request);
        
        workPmiDetailService.completeDetail(workPmiDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    private void checkPoint(WorkPmiDetailForm workPmiDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmiDetailService workPmiDetailService = (WorkPmiDetailService) getBean("workPmiDetailService", request);
            	
    	WorkPmiDetailDTO workPmiDetailDTO = workPmiDetailForm.getWorkPmiDetailDTO();
    	
    	String isValid = workPmiDetailService.checkPoint(workPmiDetailDTO,getUser(request));
    	
    	setAjaxDesc(request, isValid);
    }
    
    /**
     * detail complete Cancel
     * @author  sy.yang
     * @version $Id: WorkPmiDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiDetailForm
     * @param request
     */
    private void completeCancelDetail(WorkPmiDetailForm workPmiDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmiDetailService workPmiDetailService = (WorkPmiDetailService) getBean("workPmiDetailService", request);
    	
    	WorkPmiDetailDTO workPmiDetailDTO = workPmiDetailForm.getWorkPmiDetailDTO();
    	
    	User user = getUser(request);
    	
    	workPmiDetailService.completeCancelDetail(workPmiDetailDTO, user);
    	
    	setAjaxStatus(request);
    }
    
}