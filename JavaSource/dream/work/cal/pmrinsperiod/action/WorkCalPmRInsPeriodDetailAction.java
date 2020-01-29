package dream.work.cal.pmrinsperiod.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pmrinsperiod.dto.WorkCalPmRInsPeriodDetailDTO;
import dream.work.cal.pmrinsperiod.form.WorkCalPmRInsPeriodDetailForm;
import dream.work.cal.pmrinsperiod.service.WorkCalPmRInsPeriodDetailService;

/**
 * �����۾�����(�Ⱓ) - �� action
 *
 * @author kim2107
 * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workCalPmRInsPeriodDetail" name="workCalPmRInsPeriodDetailForm"
 *                input="/dream/work/cal/pmrinsperiod/workCalPmRInsPeriodDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmRInsPeriodDetail" path="/dream/work/cal/pmrinsperiod/workCalPmRInsPeriodDetail.jsp"
 *                        redirect="false"
 */
public class WorkCalPmRInsPeriodDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int MONTH_SCHED_DETAIL_INIT			= 8001;
    /** ���� */
    public static final int MONTH_SCHED_DETAIL_UPDATE 		= 6002;
    /** �Է� */
    public static final int MONTH_SCHED_DETAIL_INPUT 		= 5003;
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmRInsPeriodDetailForm workCalPmRInsPeriodDetailForm = (WorkCalPmRInsPeriodDetailForm) form;

        super.updateAudit(workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodDetailDTO().getAuditKey()==""?workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodCommonDTO().getAuditKey():workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (workCalPmRInsPeriodDetailForm.getStrutsAction())
        {
            case WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workCalPmRInsPeriodDetailForm);
                returnActionForward = mapping.findForward("workCalPmRInsPeriodDetail");
                break;
            case WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_UPDATE:
            	updateDetail(workCalPmRInsPeriodDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkCalPmRInsPeriodDetailAction.MONTH_SCHED_DETAIL_INPUT:
            	inputDetail(workCalPmRInsPeriodDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workCalPmRInsPeriodDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * �����۾�����(�Ⱓ) �� ��ȸ
     * @author kim2107
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsPeriodDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkCalPmRInsPeriodDetailForm workCalPmRInsPeriodDetailForm)throws Exception
    {
        // Service ��ü ����
    	WorkCalPmRInsPeriodDetailService workCalPmRInsPeriodDetailService = (WorkCalPmRInsPeriodDetailService)getBean("workCalPmRInsPeriodDetailService");

        // �Ѱ��� PM������Id ����
        String pmInsSchedId = workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodCommonDTO().getPmInsSchedId();

        // ��ȸ�� �� �κ�
        WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = workCalPmRInsPeriodDetailService.findDetail(pmInsSchedId, getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        workCalPmRInsPeriodDetailForm.setWorkCalPmRInsPeriodDetailDTO(workCalPmRInsPeriodDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodDetailForm
     * @param request
     */
    private void updateDetail(WorkCalPmRInsPeriodDetailForm workCalPmRInsPeriodDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkCalPmRInsPeriodDetailService workCalPmRInsPeriodDetailService = (WorkCalPmRInsPeriodDetailService) getBean("workCalPmRInsPeriodDetailService");

        WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodDetailDTO();
        
        workCalPmRInsPeriodDetailService.updateDetail(workCalPmRInsPeriodDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmRInsPeriodDetailForm
     * @param request
     */
    private void inputDetail(WorkCalPmRInsPeriodDetailForm workCalPmRInsPeriodDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkCalPmRInsPeriodDetailService workCalPmRInsPeriodDetailService = (WorkCalPmRInsPeriodDetailService) getBean("workCalPmRInsPeriodDetailService");

        WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodDetailDTO();
        
        workCalPmRInsPeriodDetailService.inputDetail(workCalPmRInsPeriodDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
}