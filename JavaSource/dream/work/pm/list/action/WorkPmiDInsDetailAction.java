package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsDetailDTO;
import dream.work.pm.list.form.WorkPmiDInsDetailForm;
import dream.work.pm.list.service.WorkPmiDInsDetailService;

/**
 * WorkPmiDIns Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiDInsDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiDInsDetail" name="workPmiDInsDetailForm"
 *                input="/dream/work/pmi/list/workPmiDInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiDInsDetail" path="/dream/work/pmi/list/workPmiDInsDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmiDInsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT                		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT              	  	= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE               	= 6003;
    /** 체크 */
    public static final int WORK_PMI_DETAIL_CHECKPOINT      = 8004;
    /** 완료 */
    public static final int WORK_PMI_DETAIL_COMPLETE        = 6005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiDInsDetailForm workPmiDInsDetailForm = (WorkPmiDInsDetailForm) form;
        
        super.updateAudit(workPmiDInsDetailForm.getWorkPmiDInsDetailDTO().getAuditKey()==""?workPmiDInsDetailForm.getWorkPmiDInsCommonDTO().getAuditKey():workPmiDInsDetailForm.getWorkPmiDInsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmiDInsDetailForm.getStrutsAction())
        {
            case WorkPmiDInsDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmiDInsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmiDInsDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workPmiDInsDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workPmiDInsDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsDetailAction.WORK_PMI_DETAIL_COMPLETE:
                completeDetail(workPmiDInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiDInsDetailAction.WORK_PMI_DETAIL_CHECKPOINT:
                checkPoint(workPmiDInsDetailForm, request);
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
     * @param workPmiDInsDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsDetailForm workPmiDInsDetailForm) throws Exception 
    {   
        WorkPmiDInsDetailService workPmiDInsDetailService = (WorkPmiDInsDetailService)getBean("workPmiDInsDetailService");
        
        WorkPmiDInsCommonDTO workPmiDInsCommonDTO = workPmiDInsDetailForm.getWorkPmiDInsCommonDTO(); 

        User user = getUser(request);
        WorkPmiDInsDetailDTO workPmiDInsDetailDTO = workPmiDInsDetailService.findDetail(workPmiDInsCommonDTO, user);
        workPmiDInsDetailForm.setWorkPmiDInsDetailDTO(workPmiDInsDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmiDInsDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsDetailForm workPmiDInsDetailForm) throws Exception
    {
        WorkPmiDInsCommonDTO workPmiDInsCommonDTO = workPmiDInsDetailForm.getWorkPmiDInsCommonDTO();
        WorkPmiDInsDetailService workPmiDInsDetailService = (WorkPmiDInsDetailService)getBean("workPmiDInsDetailService");
        WorkPmiDInsDetailDTO  workPmiDInsDetailDTO = workPmiDInsDetailForm.getWorkPmiDInsDetailDTO(); 
        
        User user = getUser(request);
        workPmiDInsDetailService.insertDetail(workPmiDInsCommonDTO, workPmiDInsDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmiDInsDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsDetailForm workPmiDInsDetailForm) throws Exception
    {
        WorkPmiDInsDetailService workPmiDInsDetailService = (WorkPmiDInsDetailService)getBean("workPmiDInsDetailService");
        WorkPmiDInsDetailDTO  workPmiDInsDetailDTO = workPmiDInsDetailForm.getWorkPmiDInsDetailDTO();
        
        User user = getUser(request);
        workPmiDInsDetailService.updateDetail(workPmiDInsDetailDTO, user);
        setAjaxStatus(request);
    }

    private void checkPoint(WorkPmiDInsDetailForm workPmiDInsDetailForm, HttpServletRequest request) throws Exception
    {
        WorkPmiDInsDetailService workPmiDInsDetailService = (WorkPmiDInsDetailService) getBean("workPmiDInsDetailService");
                
        WorkPmiDInsDetailDTO workPmiDInsDetailDTO = workPmiDInsDetailForm.getWorkPmiDInsDetailDTO();
        
        String isValid = workPmiDInsDetailService.checkPoint(workPmiDInsDetailDTO,getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
    private void completeDetail(WorkPmiDInsDetailForm workPmiDInsDetailForm, HttpServletRequest request) throws Exception
    {
        WorkPmiDInsDetailService workPmiDInsDetailService = (WorkPmiDInsDetailService) getBean("workPmiDInsDetailService");
        
        WorkPmiDInsDetailDTO workPmiDInsDetailDTO = workPmiDInsDetailForm.getWorkPmiDInsDetailDTO();
        
        workPmiDInsDetailDTO.setEnterBy(getUser(request).getUserId());
        workPmiDInsDetailDTO.setCompNo(getUser(request).getCompNo());
        User user = getUser(request);
        
        workPmiDInsDetailService.completeDetail(workPmiDInsDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
}
