package dream.work.let.permit.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointDetailDTO;
import dream.work.let.permit.dto.WorkLetPermitPointListDTO;
import dream.work.let.permit.form.WorkLetPermitPointDetailForm;
import dream.work.let.permit.service.WorkLetPermitPointDetailService;

/**
 * 안전작업허가서 작업유형 - 점검항목 상세 Action
 * 
 * @author syyang
 * @version $Id: WorkLetPermitPointDetailAction.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 * @struts:action path="/workLetPermitPointDetail" name="workLetPermitPointDetailForm"
 *                input="/dream/work/let/permit/workLetPermitPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetPermitPointDetail" path="/dream/work/let/permit/workLetPermitPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkLetPermitPointDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitPointDetailForm workLetPermitPointDetailForm = (WorkLetPermitPointDetailForm) form;
        
        super.updateAudit(workLetPermitPointDetailForm.getWorkLetPermitPointDetailDTO().getAuditKey()==""?workLetPermitPointDetailForm.getWorkLetPermitDetailDTO().getAuditKey():workLetPermitPointDetailForm.getWorkLetPermitPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetPermitPointDetailForm.getStrutsAction())
        {
            case WorkLetPermitPointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workLetPermitPointDetailForm);
                returnActionForward = mapping.findForward("workLetPermitPointDetail");
                break;
            case WorkLetPermitPointDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workLetPermitPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitPointDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workLetPermitPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workLetPermitPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workLetPermitPointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkLetPermitPointDetailForm workLetPermitPointDetailForm) throws Exception 
    {   
    	WorkLetPermitPointDetailService workLetPermitPointDetailService = (WorkLetPermitPointDetailService)getBean("workLetPermitPointDetailService");
    	
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitPointDetailForm.getWorkLetPermitDetailDTO();
    	WorkLetPermitPointListDTO workLetPermitPointListDTO = workLetPermitPointDetailForm.getWorkLetPermitPointListDTO();
		
    	User user = getUser(request);
    	
    	WorkLetPermitPointDetailDTO workLetPermitPointDetailDTO = workLetPermitPointDetailService.findDetail(workLetPermitDetailDTO, workLetPermitPointListDTO, user);
    	
    	workLetPermitPointDetailForm.setWorkLetPermitPointDetailDTO(workLetPermitPointDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workLetPermitPointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkLetPermitPointDetailForm workLetPermitPointDetailForm) throws Exception
    {
    	WorkLetPermitPointDetailService workLetPermitPointDetailService = (WorkLetPermitPointDetailService)getBean("workLetPermitPointDetailService");
    	
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitPointDetailForm.getWorkLetPermitDetailDTO();
    	WorkLetPermitPointDetailDTO  workLetPermitPointDetailDTO = workLetPermitPointDetailForm.getWorkLetPermitPointDetailDTO(); 
    			
    	User user = getUser(request);
    	
    	workLetPermitPointDetailService.insertDetail(workLetPermitDetailDTO, workLetPermitPointDetailDTO, user);
    	
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workLetPermitPointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkLetPermitPointDetailForm workLetPermitPointDetailForm) throws Exception
    {
    	WorkLetPermitPointDetailService workLetPermitPointDetailService = (WorkLetPermitPointDetailService)getBean("workLetPermitPointDetailService");
    	
    	WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitPointDetailForm.getWorkLetPermitDetailDTO();
    	WorkLetPermitPointDetailDTO  workLetPermitPointDetailDTO = workLetPermitPointDetailForm.getWorkLetPermitPointDetailDTO();
    	
    	User user = getUser(request);
    	
    	workLetPermitPointDetailService.updateDetail(workLetPermitDetailDTO, workLetPermitPointDetailDTO, user);
        
        setAjaxStatus(request);
    }

}