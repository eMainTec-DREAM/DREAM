package dream.work.pm.check.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckDetailDTO;
import dream.work.pm.check.form.WorkPmCheckDetailForm;
import dream.work.pm.check.service.WorkPmCheckDetailService;

/**
 * WorkPmCheck Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmCheckDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmCheckDetail" name="workPmCheckDetailForm"
 *                input="/dream/work/pm/check/workPmCheckDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmCheckDetail" path="/dream/work/pm/check/workPmCheckDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmCheckDetailAction extends AuthAction
{
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmCheckDetailForm workPmCheckDetailForm = (WorkPmCheckDetailForm) form;
        
        super.updateAudit(workPmCheckDetailForm.getWorkPmCheckDetailDTO().getAuditKey()==""?workPmCheckDetailForm.getWorkPmCheckCommonDTO().getAuditKey():workPmCheckDetailForm.getWorkPmCheckDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmCheckDetailForm.getStrutsAction())
        {
            case WorkPmCheckDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmCheckDetailForm);
                returnActionForward = mapping.findForward("workPmCheckDetail");
                break;
            case WorkPmCheckDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workPmCheckDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmCheckDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workPmCheckDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("workPmCheckDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmCheckDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmCheckDetailForm workPmCheckDetailForm) throws Exception 
    {   
        WorkPmCheckDetailService workPmCheckDetailService = (WorkPmCheckDetailService)getBean("workPmCheckDetailService");
        
        WorkPmCheckCommonDTO workPmCheckCommonDTO = workPmCheckDetailForm.getWorkPmCheckCommonDTO(); 

        User user = getUser(request);
        WorkPmCheckDetailDTO workPmCheckDetailDTO = workPmCheckDetailService.findDetail(workPmCheckCommonDTO, user);
        workPmCheckDetailForm.setWorkPmCheckDetailDTO(workPmCheckDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmCheckDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmCheckDetailForm workPmCheckDetailForm) throws Exception
    {
        WorkPmCheckDetailService workPmCheckDetailService = (WorkPmCheckDetailService)getBean("workPmCheckDetailService");
        WorkPmCheckDetailDTO  workPmCheckDetailDTO = workPmCheckDetailForm.getWorkPmCheckDetailDTO(); 
        
        User user = getUser(request);
        workPmCheckDetailService.insertDetail(workPmCheckDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmCheckDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmCheckDetailForm workPmCheckDetailForm) throws Exception
    {
        WorkPmCheckDetailService workPmCheckDetailService = (WorkPmCheckDetailService)getBean("workPmCheckDetailService");
        WorkPmCheckDetailDTO  workPmCheckDetailDTO = workPmCheckDetailForm.getWorkPmCheckDetailDTO();
        
        User user = getUser(request);
        workPmCheckDetailService.updateDetail(workPmCheckDetailDTO, user);
        setAjaxStatus(request);
    }
}
