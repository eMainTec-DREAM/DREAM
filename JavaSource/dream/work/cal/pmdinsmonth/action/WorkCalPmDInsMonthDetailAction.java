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
 * 월간예방일정 - 상세 action
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MONTH_SCHED_DETAIL_INIT			= 8001;
    /** 수정 */
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
                // 페이지 조회
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
     * 월간예방일정 상세 조회
     * @author kim2107
     * @version $Id: WorkCalPmDInsMonthDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmDInsMonthDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkCalPmDInsMonthDetailForm workCalPmDInsMonthDetailForm)throws Exception
    {
        // Service 객체 생성
    	WorkCalPmDInsMonthDetailService workCalPmDInsMonthDetailService = (WorkCalPmDInsMonthDetailService)getBean("workCalPmDInsMonthDetailService");

        // 넘겨진 PM스케쥴Id 구함
        String pmInsDSchedId = workCalPmDInsMonthDetailForm.getWorkCalPmDInsMonthCommonDTO().getPmInsDSchedId();

        // 조회된 상세 부분
        WorkCalPmDInsMonthDetailDTO workCalPmDInsMonthDetailDTO = workCalPmDInsMonthDetailService.findDetail(pmInsDSchedId, getUser(request));

        // 조회된 상세  셋팅한다.
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