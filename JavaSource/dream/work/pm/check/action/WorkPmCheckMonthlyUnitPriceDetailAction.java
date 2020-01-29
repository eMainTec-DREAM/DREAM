package dream.work.pm.check.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceDetailDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;
import dream.work.pm.check.form.WorkPmCheckMonthlyUnitPriceDetailForm;
import dream.work.pm.check.service.WorkPmCheckMonthlyUnitPriceDetailService;

/**
 * 월별단가 - Detail Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceDetailAction.java,v 1.0 2017/08/28 12:37:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmCheckMonthlyUnitPriceDetail" name="workPmCheckMonthlyUnitPriceDetailForm"
 *                input="/dream/work/pm/check/workPmCheckMonthlyUnitPriceDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmCheckMonthlyUnitPriceDetail" path="/dream/work/pm/check/workPmCheckMonthlyUnitPriceDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmCheckMonthlyUnitPriceDetailAction extends AuthAction
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
        WorkPmCheckMonthlyUnitPriceDetailForm workPmCheckMonthlyUnitPriceDetailForm = (WorkPmCheckMonthlyUnitPriceDetailForm) form;
        
        super.updateAudit(workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckMonthlyUnitPriceDetailDTO().getAuditKey()==""?workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckMonthlyUnitPriceListDTO().getAuditKey():workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckMonthlyUnitPriceDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmCheckMonthlyUnitPriceDetailForm.getStrutsAction())
        {
            case WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmCheckMonthlyUnitPriceDetailForm);
                returnActionForward = mapping.findForward("workPmCheckMonthlyUnitPriceDetail");
                break;
            case WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workPmCheckMonthlyUnitPriceDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmCheckMonthlyUnitPriceDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workPmCheckMonthlyUnitPriceDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workPmCheckMonthlyUnitPriceDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmCheckMonthlyUnitPriceDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmCheckMonthlyUnitPriceDetailForm workPmCheckMonthlyUnitPriceDetailForm) throws Exception 
    {   
    	WorkPmCheckMonthlyUnitPriceDetailService workPmCheckMonthlyUnitPriceDetailService = (WorkPmCheckMonthlyUnitPriceDetailService)getBean("workPmCheckMonthlyUnitPriceDetailService");
    	
    	WorkPmCheckCommonDTO workPmCheckCommonDTO = workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckCommonDTO(); 
    	WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO = workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckMonthlyUnitPriceListDTO();
    	WorkPmCheckMonthlyUnitPriceDetailDTO workPmCheckMonthlyUnitPriceDetailDTO = workPmCheckMonthlyUnitPriceDetailService.findDetail(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceListDTO, getUser(request));
    	workPmCheckMonthlyUnitPriceDetailForm.setWorkPmCheckMonthlyUnitPriceDetailDTO(workPmCheckMonthlyUnitPriceDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmCheckMonthlyUnitPriceDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmCheckMonthlyUnitPriceDetailForm workPmCheckMonthlyUnitPriceDetailForm) throws Exception
    {
    	WorkPmCheckMonthlyUnitPriceDetailService workPmCheckMonthlyUnitPriceDetailService = (WorkPmCheckMonthlyUnitPriceDetailService)getBean("workPmCheckMonthlyUnitPriceDetailService");
    	WorkPmCheckCommonDTO workPmCheckCommonDTO = workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckCommonDTO(); 
    	WorkPmCheckMonthlyUnitPriceDetailDTO  workPmCheckMonthlyUnitPriceDetailDTO = workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckMonthlyUnitPriceDetailDTO(); 
    	workPmCheckMonthlyUnitPriceDetailService.insertDetail(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmCheckMonthlyUnitPriceDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmCheckMonthlyUnitPriceDetailForm workPmCheckMonthlyUnitPriceDetailForm) throws Exception
    {
    	WorkPmCheckMonthlyUnitPriceDetailService workPmCheckMonthlyUnitPriceDetailService = (WorkPmCheckMonthlyUnitPriceDetailService)getBean("workPmCheckMonthlyUnitPriceDetailService");
    	WorkPmCheckCommonDTO workPmCheckCommonDTO = workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckCommonDTO(); 
    	WorkPmCheckMonthlyUnitPriceDetailDTO  workPmCheckMonthlyUnitPriceDetailDTO = workPmCheckMonthlyUnitPriceDetailForm.getWorkPmCheckMonthlyUnitPriceDetailDTO();
    	workPmCheckMonthlyUnitPriceDetailService.updateDetail(workPmCheckCommonDTO, workPmCheckMonthlyUnitPriceDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}