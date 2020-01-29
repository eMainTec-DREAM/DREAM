package dream.work.pm.std.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValDetailDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;
import dream.work.pm.std.form.WorkPmStdCalibValDetailForm;
import dream.work.pm.std.service.WorkPmStdCalibValDetailService;

/**
 * 표준교정값 - Detail Action
 * 
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmStdCalibValDetail" name="workPmStdCalibValDetailForm"
 *                input="/dream/work/pm/std/workPmStdCalibValDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmStdCalibValDetail" path="/dream/work/pm/std/workPmStdCalibValDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmStdCalibValDetailAction extends AuthAction
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
        WorkPmStdCalibValDetailForm workPmStdCalibValDetailForm = (WorkPmStdCalibValDetailForm) form;
        
        super.updateAudit(workPmStdCalibValDetailForm.getWorkPmStdCalibValDetailDTO().getAuditKey()==""?workPmStdCalibValDetailForm.getWorkPmStdCalibValListDTO().getAuditKey():workPmStdCalibValDetailForm.getWorkPmStdCalibValDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmStdCalibValDetailForm.getStrutsAction())
        {
            case WorkPmStdCalibValDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmStdCalibValDetailForm);
                returnActionForward = mapping.findForward("workPmStdCalibValDetail");
                break;
            case WorkPmStdCalibValDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workPmStdCalibValDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmStdCalibValDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workPmStdCalibValDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workPmStdCalibValDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmStdCalibValDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValDetailForm workPmStdCalibValDetailForm) throws Exception 
    {   
    	WorkPmStdCalibValDetailService workPmStdCalibValDetailService = (WorkPmStdCalibValDetailService)getBean("workPmStdCalibValDetailService");
    	
    	WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = workPmStdCalibValDetailForm.getWorkPmStdCalibCommonDTO(); 
    	WorkPmStdCalibValListDTO workPmStdCalibValListDTO = workPmStdCalibValDetailForm.getWorkPmStdCalibValListDTO();
    	WorkPmStdCalibValDetailDTO workPmStdCalibValDetailDTO = workPmStdCalibValDetailService.findDetail(workPmStdCalibCommonDTO,workPmStdCalibValListDTO, getUser(request));
    	workPmStdCalibValDetailForm.setWorkPmStdCalibValDetailDTO(workPmStdCalibValDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmStdCalibValDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValDetailForm workPmStdCalibValDetailForm) throws Exception
    {
    	WorkPmStdCalibValDetailService workPmStdCalibValDetailService = (WorkPmStdCalibValDetailService)getBean("workPmStdCalibValDetailService");
    	WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = workPmStdCalibValDetailForm.getWorkPmStdCalibCommonDTO(); 
    	WorkPmStdCalibValDetailDTO  workPmStdCalibValDetailDTO = workPmStdCalibValDetailForm.getWorkPmStdCalibValDetailDTO(); 
    	workPmStdCalibValDetailService.insertDetail(workPmStdCalibCommonDTO,workPmStdCalibValDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmStdCalibValDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValDetailForm workPmStdCalibValDetailForm) throws Exception
    {
    	WorkPmStdCalibValDetailService workPmStdCalibValDetailService = (WorkPmStdCalibValDetailService)getBean("workPmStdCalibValDetailService");
    	WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = workPmStdCalibValDetailForm.getWorkPmStdCalibCommonDTO(); 
    	WorkPmStdCalibValDetailDTO  workPmStdCalibValDetailDTO = workPmStdCalibValDetailForm.getWorkPmStdCalibValDetailDTO();
    	workPmStdCalibValDetailService.updateDetail(workPmStdCalibCommonDTO, workPmStdCalibValDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }

}