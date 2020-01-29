package dream.work.let.permit.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.form.WorkLetPermitDetailForm;
import dream.work.let.permit.service.WorkLetPermitDetailService;

/**
 * 안전작업 - 안전작업허가서 상세
 * @author  syyang
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workLetPermitDetail" name="workLetPermitDetailForm"
 *                input="/dream/work/let/permit/workLetPermitDetail.jsp" scope="request"
 *                validate="false"
 */
public class WorkLetPermitDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_LET_PERMIT_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int WO_LET_PERMIT_DETAIL_UPDATE 	= 6001;
    /** 입력 */
    public static final int WO_LET_PERMIT_DETAIL_INPUT 		= 5001;
    /** 완료 */
    public static final int WO_LET_PERMIT_DETAIL_COMPLETE 			= 6002;
    /** 완료취소 */
    public static final int WO_LET_PERMIT_DETAIL_COMPLETE_CANCEL 	= 6003;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitDetailForm workLetPermitDetailForm = (WorkLetPermitDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workLetPermitDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        switch (workLetPermitDetailForm.getStrutsAction())
        {
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workLetPermitDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_UPDATE:
            	updateDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_INPUT:
            	insertDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_COMPLETE:
                completeDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkLetPermitDetailAction.WO_LET_PERMIT_DETAIL_COMPLETE_CANCEL:
            	completeCancelDetail(workLetPermitDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업계획목록 - 안전작업 상세 조회
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkLetPermitDetailForm workLetPermitDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService)getBean("workLetPermitDetailService");

    	// 안전작업허가서 ID 구함
        String woLetId = workLetPermitDetailForm.getWorkLetCommonDTO().getWoLetId();
        // 안전작업유형id 구함
        String woLetListId = workLetPermitDetailForm.getWorkLetPermitListDTO().getWoLetListId();
        
        // 조회된 상세 부분
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailService.findDetail(woLetId, woLetListId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        workLetPermitDetailForm.setWorkLetPermitDetailDTO(workLetPermitDetailDTO);
    }
    
    /**
     * detail update
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void updateDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetCommonDTO workLetCommonDTO = workLetPermitDetailForm.getWorkLetCommonDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        workLetPermitDetailService.updateDetail(workLetCommonDTO, workLetPermitDetailDTO,getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail insert
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void insertDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetCommonDTO workLetCommonDTO = workLetPermitDetailForm.getWorkLetCommonDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        workLetPermitDetailService.insertDetail(workLetCommonDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

    
    /**
     * detail complete
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void completeDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetDetailDTO workLetDetailDTO = workLetPermitDetailForm.getWorkLetDetailDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        String status = workLetPermitDetailService.completeDetail(workLetDetailDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }
    
    /**
     * detail complete cancel
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetPermitDetailForm
     * @param request
     */
    private void completeCancelDetail(WorkLetPermitDetailForm workLetPermitDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitDetailService workLetPermitDetailService = (WorkLetPermitDetailService) getBean("workLetPermitDetailService");
        
    	WorkLetDetailDTO workLetDetailDTO = workLetPermitDetailForm.getWorkLetDetailDTO();
        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitDetailForm.getWorkLetPermitDetailDTO();
        
        String status = workLetPermitDetailService.completeCancelDetail(workLetDetailDTO, workLetPermitDetailDTO, getUser(request));
        
        setAjaxDesc(request, status);
    }
    
}