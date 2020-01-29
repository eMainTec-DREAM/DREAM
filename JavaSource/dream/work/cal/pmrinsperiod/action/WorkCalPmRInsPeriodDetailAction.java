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
 * 예방작업일정(기간) - 상세 action
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
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int MONTH_SCHED_DETAIL_INIT			= 8001;
    /** 수정 */
    public static final int MONTH_SCHED_DETAIL_UPDATE 		= 6002;
    /** 입력 */
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
                // 페이지 조회
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
     * 예방작업일정(기간) 상세 조회
     * @author kim2107
     * @version $Id: MaWoSchedDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     *
     * @param request
     * @param workCalPmRInsPeriodDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkCalPmRInsPeriodDetailForm workCalPmRInsPeriodDetailForm)throws Exception
    {
        // Service 객체 생성
    	WorkCalPmRInsPeriodDetailService workCalPmRInsPeriodDetailService = (WorkCalPmRInsPeriodDetailService)getBean("workCalPmRInsPeriodDetailService");

        // 넘겨진 PM스케쥴Id 구함
        String pmInsSchedId = workCalPmRInsPeriodDetailForm.getWorkCalPmRInsPeriodCommonDTO().getPmInsSchedId();

        // 조회된 상세 부분
        WorkCalPmRInsPeriodDetailDTO workCalPmRInsPeriodDetailDTO = workCalPmRInsPeriodDetailService.findDetail(pmInsSchedId, getUser(request));

        // 조회된 상세  셋팅한다.
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