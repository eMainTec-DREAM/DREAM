package dream.work.let.permit.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.let.permit.dto.WorkLetPermitCraftDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.form.WorkLetPermitCraftDetailForm;
import dream.work.let.permit.service.WorkLetPermitCraftDetailService;

/**
 * 안전작업허가서유형 - 작업자 상세 action
 * @author  syyang
 * @version $Id: WorkLetPermitCraftDetailAction.java,v 1.0 2015/12/04 09:09:30 syyang Exp $
 * @since   1.0
 * @struts:action path="/workLetPermitCraftDetail" name="workLetPermitCraftDetailForm"
 *                input="/dream/work/let/permit/workLetPermitCraftDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetPermitCraftDetail" path="/dream/work/let/permit/workLetPermitCraftDetail.jsp"
 *                        redirect="false"
 */
public class WorkLetPermitCraftDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_LET_PERMIT_CRAFT_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int WO_LET_PERMIT_CRAFT_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WO_LET_PERMIT_CRAFT_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitCraftDetailForm workLetPermitCraftDetailForm = (WorkLetPermitCraftDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workLetPermitCraftDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workLetPermitCraftDetailForm.getWorkLetPermitCraftDetailDTO().getAuditKey()==""?workLetPermitCraftDetailForm.getWorkLetPermitCraftListDTO().getAuditKey():workLetPermitCraftDetailForm.getWorkLetPermitCraftDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetPermitCraftDetailForm.getStrutsAction())
        {
            case WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workLetPermitCraftDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_UPDATE:
            	updateDetail(workLetPermitCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitCraftDetailAction.WO_LET_PERMIT_CRAFT_DETAIL_INPUT:
            	insertDetail(workLetPermitCraftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업결과-작업자 상세 조회
     * @author kim2107
     * @version $Id: WorkLetPermitCraftDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitCraftDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkLetPermitCraftDetailForm workLetPermitCraftDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkLetPermitCraftDetailService workLetPermitCraftDetailService = (WorkLetPermitCraftDetailService)getBean("workLetPermitCraftDetailService");

    	// 안전작업허가서 Id 구함
        String woLetListId = workLetPermitCraftDetailForm.getWorkLetPermitDetailDTO().getWoLetListId();
        // 안전작업허가서 작업자 id 구함
        String woLetListCraftId = workLetPermitCraftDetailForm.getWorkLetPermitCraftListDTO().getWoLetListCraftId();
        
        // 조회된 상세 부분
        WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO = workLetPermitCraftDetailService.findDetail(woLetListId, woLetListCraftId, getUser(request));
        
        // 조회된 상세  셋팅한다.
        workLetPermitCraftDetailForm.setWorkLetPermitCraftDetailDTO(workLetPermitCraftDetailDTO);
    }
    /**
     * detail update
     * @author  syyang
     * @version $Id: WorkLetPermitCraftDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitCraftDetailForm
     * @param request
     */
    private void updateDetail(WorkLetPermitCraftDetailForm workLetPermitCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitCraftDetailService workLetPermitCraftDetailService = (WorkLetPermitCraftDetailService) getBean("workLetPermitCraftDetailService");
        
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitCraftDetailForm.getWorkLetPermitDetailDTO();
    	WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO = workLetPermitCraftDetailForm.getWorkLetPermitCraftDetailDTO();
        
        workLetPermitCraftDetailService.updateDetail(workLetPermitDetailDTO, workLetPermitCraftDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  syyang
     * @version $Id: WorkLetPermitCraftDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param workLetPermitCraftDetailForm
     * @param request
     */
    private void insertDetail(WorkLetPermitCraftDetailForm workLetPermitCraftDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetPermitCraftDetailService workLetPermitCraftDetailService = (WorkLetPermitCraftDetailService) getBean("workLetPermitCraftDetailService");
    	
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitCraftDetailForm.getWorkLetPermitDetailDTO();
    	WorkLetPermitCraftDetailDTO workLetPermitCraftDetailDTO = workLetPermitCraftDetailForm.getWorkLetPermitCraftDetailDTO();
        
        workLetPermitCraftDetailService.insertDetail(workLetPermitDetailDTO, workLetPermitCraftDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
}