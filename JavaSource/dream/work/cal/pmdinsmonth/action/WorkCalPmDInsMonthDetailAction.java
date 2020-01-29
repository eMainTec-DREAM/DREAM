package dream.work.cal.pmdinsmonth.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pmdinsmonth.dto.WorkCalPmDInsMonthDetailDTO;
import dream.work.cal.pmdinsmonth.form.WorkCalPmDInsMonthDetailForm;
import dream.work.cal.pmdinsmonth.service.WorkCalPmDInsMonthDetailService;

/**
 * ������������ - �� action
 *
 * @author kim2107
 * @version $Id: WorkCalPmDInsMonthDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workCalPmDInsMonthDetail" name="workCalPmDInsMonthDetailForm"
 *                input="/dream/work/cal/pmdinsmonth/workCalPmDInsMonthDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmDInsMonthDetail" path="/dream/work/cal/pmdinsmonth/workCalPmDInsMonthDetail.jsp"
 *                        redirect="false"
 */
public class WorkCalPmDInsMonthDetailAction extends AuthAction
{
    /** ��Ͽ��� ������ TAB�̵� ��ȸ�� �ϴ°�� */
    public static final int MONTH_SCHED_DETAIL_INIT			= 8001;
    /** ���� */
    public static final int MONTH_SCHED_DETAIL_UPDATE 		= 6002;

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmDInsMonthDetailForm workCalPmDInsMonthDetailForm = (WorkCalPmDInsMonthDetailForm) form;

        super.updateAudit(workCalPmDInsMonthDetailForm.getWorkCalPmDInsMonthDetailDTO().getAuditKey()==""?workCalPmDInsMonthDetailForm.getWorkCalPmDInsMonthCommonDTO().getAuditKey():workCalPmDInsMonthDetailForm.getWorkCalPmDInsMonthDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (workCalPmDInsMonthDetailForm.getStrutsAction())
        {
            case WorkCalPmDInsMonthDetailAction.MONTH_SCHED_DETAIL_INIT:
                // ������ ��ȸ
                this.findDetail(request, workCalPmDInsMonthDetailForm);
                returnActionForward = mapping.findForward("workCalPmDInsMonthDetail");
                break;
            case WorkCalPmDInsMonthDetailAction.MONTH_SCHED_DETAIL_UPDATE:
            	updateDetail(workCalPmDInsMonthDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workCalPmDInsMonthDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * ������������ �� ��ȸ
     * @author kim2107
     * @version $Id: WorkCalPmDInsMonthDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmDInsMonthDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkCalPmDInsMonthDetailForm workCalPmDInsMonthDetailForm)throws Exception
    {
        // Service ��ü ����
    	WorkCalPmDInsMonthDetailService workCalPmDInsMonthDetailService = (WorkCalPmDInsMonthDetailService)getBean("workCalPmDInsMonthDetailService");

        // �Ѱ��� PM������Id ����
        String pmInsDSchedId = workCalPmDInsMonthDetailForm.getWorkCalPmDInsMonthCommonDTO().getPmInsDSchedId();

        // ��ȸ�� �� �κ�
        WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = workCalPmDInsMonthDetailService.findDetail(pmInsDSchedId, getUser(request));

        // ��ȸ�� ��  �����Ѵ�.
        workCalPmDInsMonthDetailForm.setWorkCalPmDInsMonthDetailDTO(workCalPmDInsMonthDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkCalPmDInsMonthDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmDInsMonthDetailForm
     * @param request
     */
    private void updateDetail(WorkCalPmDInsMonthDetailForm workCalPmDInsMonthDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkCalPmDInsMonthDetailService workCalPmDInsMonthDetailService = (WorkCalPmDInsMonthDetailService) getBean("workCalPmDInsMonthDetailService");

        WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = workCalPmDInsMonthDetailForm.getWorkCalPmDInsMonthDetailDTO();
        
        workCalPmDInsMonthDetailService.updateDetail(workCalPmDInsMonthDetailDTO, getUser(request));

        setAjaxStatus(request);
    }
}