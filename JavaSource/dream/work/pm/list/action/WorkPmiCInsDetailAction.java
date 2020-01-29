package dream.work.pm.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiCInsDetailDTO;
import dream.work.pm.list.form.WorkPmiCInsDetailForm;
import dream.work.pm.list.service.WorkPmiCInsDetailService;

/**
 * WorkPmiCIns Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiCInsDetail" name="workPmiCInsDetailForm"
 *                input="/dream/work/pmi/list/workPmiCInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiCInsDetail" path="/dream/work/pmi/list/workPmiCInsDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmiCInsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                 = 1001;
    /** 저장 */
    public static final int DETAIL_INPUT                = 1002;
    /** 수정 */
    public static final int DETAIL_UPDATE               = 1003;
    /** 체크 */
    public static final int WORK_PMI_DETAIL_CHECKPOINT      = 1004;
    /** 완료 */
    public static final int WORK_PMI_DETAIL_COMPLETE        = 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiCInsDetailForm workPmiCInsDetailForm = (WorkPmiCInsDetailForm) form;
        
        switch (workPmiCInsDetailForm.getStrutsAction())
        {
            case WorkPmiCInsDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmiCInsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmiCInsDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workPmiCInsDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiCInsDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workPmiCInsDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiCInsDetailAction.WORK_PMI_DETAIL_COMPLETE:
                completeDetail(workPmiCInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiCInsDetailAction.WORK_PMI_DETAIL_CHECKPOINT:
                checkPoint(workPmiCInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmiCInsDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsDetailForm workPmiCInsDetailForm) throws Exception 
    {   
        WorkPmiCInsDetailService workPmiCInsDetailService = (WorkPmiCInsDetailService)getBean("workPmiCInsDetailService");
        
        WorkPmiCInsCommonDTO workPmiCInsCommonDTO = workPmiCInsDetailForm.getWorkPmiCInsCommonDTO(); 

        User user = getUser(request);
        WorkPmiCInsDetailDTO workPmiCInsDetailDTO = workPmiCInsDetailService.findDetail(workPmiCInsCommonDTO, user);
        workPmiCInsDetailForm.setWorkPmiCInsDetailDTO(workPmiCInsDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmiCInsDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsDetailForm workPmiCInsDetailForm) throws Exception
    {
        WorkPmiCInsCommonDTO workPmiCInsCommonDTO = workPmiCInsDetailForm.getWorkPmiCInsCommonDTO();
        WorkPmiCInsDetailService workPmiCInsDetailService = (WorkPmiCInsDetailService)getBean("workPmiCInsDetailService");
        WorkPmiCInsDetailDTO  workPmiCInsDetailDTO = workPmiCInsDetailForm.getWorkPmiCInsDetailDTO(); 
        
        User user = getUser(request);
        workPmiCInsDetailService.insertDetail(workPmiCInsCommonDTO, workPmiCInsDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmiCInsDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsDetailForm workPmiCInsDetailForm) throws Exception
    {
        WorkPmiCInsDetailService workPmiCInsDetailService = (WorkPmiCInsDetailService)getBean("workPmiCInsDetailService");
        WorkPmiCInsDetailDTO  workPmiCInsDetailDTO = workPmiCInsDetailForm.getWorkPmiCInsDetailDTO();
        
        User user = getUser(request);
        workPmiCInsDetailService.updateDetail(workPmiCInsDetailDTO, user);
        setAjaxStatus(request);
    }

    private void checkPoint(WorkPmiCInsDetailForm workPmiCInsDetailForm, HttpServletRequest request) throws Exception
    {
        WorkPmiCInsDetailService workPmiCInsDetailService = (WorkPmiCInsDetailService) getBean("workPmiCInsDetailService");
                
        WorkPmiCInsDetailDTO workPmiCInsDetailDTO = workPmiCInsDetailForm.getWorkPmiCInsDetailDTO();
        
        String isValid = workPmiCInsDetailService.checkPoint(workPmiCInsDetailDTO,getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
    private void completeDetail(WorkPmiCInsDetailForm workPmiCInsDetailForm, HttpServletRequest request) throws Exception
    {
        WorkPmiCInsDetailService workPmiCInsDetailService = (WorkPmiCInsDetailService) getBean("workPmiCInsDetailService");
        
        WorkPmiCInsDetailDTO workPmiCInsDetailDTO = workPmiCInsDetailForm.getWorkPmiCInsDetailDTO();
        
        workPmiCInsDetailDTO.setEnterBy(getUser(request).getUserId());
        workPmiCInsDetailDTO.setCompNo(getUser(request).getCompNo());
        User user = getUser(request);
        workPmiCInsDetailService.completeDetail(workPmiCInsDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
}
