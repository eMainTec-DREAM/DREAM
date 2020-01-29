package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.dto.WorkListCinsPlanMstrDetailDTO;
import dream.work.list.form.WorkListCinsPlanMstrDetailForm;
import dream.work.list.service.WorkListCinsPlanMstrDetailService;

/**
 * WorkListCinsPlanMstr Page - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrDetailAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workListCinsPlanMstrDetail" name="workListCinsPlanMstrDetailForm"
 *                input="/dream/work/list/workListCinsPlanMstrDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListCinsPlanMstrDetail" path="/dream/work/list/workListCinsPlanMstrDetail.jsp"
 *                        redirect="false"
 */
public class WorkListCinsPlanMstrDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT               	    = 8001;
    /** 저장 */
    public static final int DETAIL_INPUT                	= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE             		= 6003;
    /** 체크 */
    public static final int WORK_PMI_DETAIL_CHECKPOINT      = 8004;
    /** 완료 */
    public static final int WORK_PMI_DETAIL_COMPLETE        = 5005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListCinsPlanMstrDetailForm workListCinsPlanMstrDetailForm = (WorkListCinsPlanMstrDetailForm) form;
        
        super.updateAudit(workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrDetailDTO().getAuditKey()==""?workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrCommonDTO().getAuditKey():workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (workListCinsPlanMstrDetailForm.getStrutsAction())
        {
            case WorkListCinsPlanMstrDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workListCinsPlanMstrDetailForm);
                returnActionForward = mapping.findForward("workListCinsPlanMstrDetail");
                break;
            case WorkListCinsPlanMstrDetailAction.DETAIL_INPUT:
                insertDetail(request, response, workListCinsPlanMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListCinsPlanMstrDetailAction.DETAIL_UPDATE:
                updateDetail(request, response, workListCinsPlanMstrDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListCinsPlanMstrDetailAction.WORK_PMI_DETAIL_COMPLETE:
                completeDetail(workListCinsPlanMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkListCinsPlanMstrDetailAction.WORK_PMI_DETAIL_CHECKPOINT:
                checkPoint(workListCinsPlanMstrDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("workListCinsPlanMstrDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workListCinsPlanMstrDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkListCinsPlanMstrDetailForm workListCinsPlanMstrDetailForm) throws Exception 
    {   
        WorkListCinsPlanMstrDetailService workListCinsPlanMstrDetailService = (WorkListCinsPlanMstrDetailService)getBean("workListCinsPlanMstrDetailService");
        
        WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO = workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrCommonDTO(); 

        User user = getUser(request);
        WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO = workListCinsPlanMstrDetailService.findDetail(workListCinsPlanMstrCommonDTO, user);
        workListCinsPlanMstrDetailForm.setWorkListCinsPlanMstrDetailDTO(workListCinsPlanMstrDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workListCinsPlanMstrDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkListCinsPlanMstrDetailForm workListCinsPlanMstrDetailForm) throws Exception
    {
        WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO = workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrCommonDTO();
        WorkListCinsPlanMstrDetailService workListCinsPlanMstrDetailService = (WorkListCinsPlanMstrDetailService)getBean("workListCinsPlanMstrDetailService");
        WorkListCinsPlanMstrDetailDTO  workListCinsPlanMstrDetailDTO = workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrDetailDTO(); 
        
        User user = getUser(request);
        workListCinsPlanMstrDetailService.insertDetail(workListCinsPlanMstrCommonDTO, workListCinsPlanMstrDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workListCinsPlanMstrDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkListCinsPlanMstrDetailForm workListCinsPlanMstrDetailForm) throws Exception
    {
        WorkListCinsPlanMstrDetailService workListCinsPlanMstrDetailService = (WorkListCinsPlanMstrDetailService)getBean("workListCinsPlanMstrDetailService");
        WorkListCinsPlanMstrDetailDTO  workListCinsPlanMstrDetailDTO = workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrDetailDTO();
        
        User user = getUser(request);
        workListCinsPlanMstrDetailService.updateDetail(workListCinsPlanMstrDetailDTO, user);
        setAjaxStatus(request);
    }

    private void checkPoint(WorkListCinsPlanMstrDetailForm workListCinsPlanMstrDetailForm, HttpServletRequest request) throws Exception
    {
        WorkListCinsPlanMstrDetailService workListCinsPlanMstrDetailService = (WorkListCinsPlanMstrDetailService) getBean("workListCinsPlanMstrDetailService");
                
        WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO = workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrDetailDTO();
        
        String isValid = workListCinsPlanMstrDetailService.checkPoint(workListCinsPlanMstrDetailDTO,getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
    private void completeDetail(WorkListCinsPlanMstrDetailForm workListCinsPlanMstrDetailForm, HttpServletRequest request) throws Exception
    {
        WorkListCinsPlanMstrDetailService workListCinsPlanMstrDetailService = (WorkListCinsPlanMstrDetailService) getBean("workListCinsPlanMstrDetailService");
        
        WorkListCinsPlanMstrDetailDTO workListCinsPlanMstrDetailDTO = workListCinsPlanMstrDetailForm.getWorkListCinsPlanMstrDetailDTO();
        
        workListCinsPlanMstrDetailDTO.setEnterBy(getUser(request).getUserId());
        workListCinsPlanMstrDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User user = getUser(request);
        workListCinsPlanMstrDetailService.completeDetail(workListCinsPlanMstrDetailDTO,user);
        
        setAjaxStatus(request);
    }
    
}
