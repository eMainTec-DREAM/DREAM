package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.WorkListPointDetailDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.form.WorkListPointDetailForm;
import dream.work.list.service.WorkListPointDetailService;

/**
 * 점검내용 - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkListPointDetailAction.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workListPointDetail" name="workListPointDetailForm"
 *                input="/dream/work/list/workListPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListPointDetail" path="/dream/work/list/workListPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkListPointDetailAction extends AuthAction
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
        WorkListPointDetailForm workListPointDetailForm = (WorkListPointDetailForm) form;
        
        super.updateAudit(workListPointDetailForm.getWorkListPointDetailDTO().getAuditKey()==""?workListPointDetailForm.getWorkListPointListDTO().getAuditKey():workListPointDetailForm.getWorkListPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListPointDetailForm.getStrutsAction())
        {
            case WorkListPointDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workListPointDetailForm);
                returnActionForward = mapping.findForward("workListPointDetail");
                break;
            case WorkListPointDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workListPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListPointDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workListPointDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workListPointDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workListPointDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkListPointDetailForm workListPointDetailForm) throws Exception 
    {   
    	WorkListPointDetailService workListPointDetailService = (WorkListPointDetailService)getBean("workListPointDetailService");
    	
    	WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPointDetailForm.getWorkListPtrlResultCommonDTO(); 
    	WorkListPointListDTO workListPointListDTO = workListPointDetailForm.getWorkListPointListDTO();
    	WorkListPointDetailDTO workListPointDetailDTO = workListPointDetailService.findDetail(workListPtrlResultCommonDTO,workListPointListDTO, getUser(request));
    	workListPointDetailForm.setWorkListPointDetailDTO(workListPointDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workListPointDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkListPointDetailForm workListPointDetailForm) throws Exception
    {
    	WorkListPointDetailService workListPointDetailService = (WorkListPointDetailService)getBean("workListPointDetailService");
    	WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPointDetailForm.getWorkListPtrlResultCommonDTO(); 
    	WorkListPointDetailDTO  workListPointDetailDTO = workListPointDetailForm.getWorkListPointDetailDTO(); 
    	workListPointDetailService.insertDetail(workListPtrlResultCommonDTO,workListPointDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workListPointDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkListPointDetailForm workListPointDetailForm) throws Exception
    {
    	WorkListPointDetailService workListPointDetailService = (WorkListPointDetailService)getBean("workListPointDetailService");
    	WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPointDetailForm.getWorkListPtrlResultCommonDTO(); 
    	WorkListPointDetailDTO  workListPointDetailDTO = workListPointDetailForm.getWorkListPointDetailDTO();
    	workListPointDetailService.updateDetail(workListPtrlResultCommonDTO, workListPointDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}