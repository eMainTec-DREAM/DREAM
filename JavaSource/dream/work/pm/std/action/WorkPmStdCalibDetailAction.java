package dream.work.pm.std.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibDetailDTO;
import dream.work.pm.std.form.WorkPmStdCalibDetailForm;
import dream.work.pm.std.service.WorkPmStdCalibDetailService;

/**
 * 교정표준값 타입 - Detail Action
 * 
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmStdCalibDetail" name="workPmStdCalibDetailForm"
 *                input="/dream/work/pm/std/workPmStdCalibDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmStdCalibDetail" path="/dream/work/pm/std/workPmStdCalibDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmStdCalibDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 중복 체크 */
    public static final int DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmStdCalibDetailForm workPmStdCalibDetailForm = (WorkPmStdCalibDetailForm) form;
        
        super.updateAudit(workPmStdCalibDetailForm.getWorkPmStdCalibDetailDTO().getAuditKey()==""?workPmStdCalibDetailForm.getWorkPmStdCalibCommonDTO().getAuditKey():workPmStdCalibDetailForm.getWorkPmStdCalibDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmStdCalibDetailForm.getStrutsAction())
        {
            case WorkPmStdCalibDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmStdCalibDetailForm);
                returnActionForward = mapping.findForward("workPmStdCalibDetail");
                break;
            case WorkPmStdCalibDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workPmStdCalibDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmStdCalibDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workPmStdCalibDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmStdCalibDetailAction.DETAIL_CHECK:
            	validDetail(request, response, workPmStdCalibDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workPmStdCalibDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workPmStdCalibDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibDetailForm workPmStdCalibDetailForm) throws Exception 
    {   
    	WorkPmStdCalibDetailService workPmStdCalibDetailService = (WorkPmStdCalibDetailService)getBean("workPmStdCalibDetailService");
    	
    	WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = workPmStdCalibDetailForm.getWorkPmStdCalibCommonDTO(); 
    	WorkPmStdCalibDetailDTO workPmStdCalibDetailDTO = workPmStdCalibDetailService.findDetail(workPmStdCalibCommonDTO, getUser(request));
    	workPmStdCalibDetailForm.setWorkPmStdCalibDetailDTO(workPmStdCalibDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workPmStdCalibDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibDetailForm workPmStdCalibDetailForm) throws Exception
    {
    	WorkPmStdCalibDetailService workPmStdCalibDetailService = (WorkPmStdCalibDetailService)getBean("workPmStdCalibDetailService");
    	WorkPmStdCalibDetailDTO  workPmStdCalibDetailDTO = workPmStdCalibDetailForm.getWorkPmStdCalibDetailDTO(); 
    	workPmStdCalibDetailService.insertDetail(workPmStdCalibDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workPmStdCalibDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibDetailForm workPmStdCalibDetailForm) throws Exception
    {
    	WorkPmStdCalibDetailService workPmStdCalibDetailService = (WorkPmStdCalibDetailService)getBean("workPmStdCalibDetailService");
    	WorkPmStdCalibDetailDTO  workPmStdCalibDetailDTO = workPmStdCalibDetailForm.getWorkPmStdCalibDetailDTO();
    	workPmStdCalibDetailService.updateDetail(workPmStdCalibDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void validDetail(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibDetailForm workPmStdCalibDetailForm) throws Exception
    {
    	WorkPmStdCalibDetailService workPmStdCalibDetailService = (WorkPmStdCalibDetailService)getBean("workPmStdCalibDetailService");
    	WorkPmStdCalibDetailDTO  workPmStdCalibDetailDTO = workPmStdCalibDetailForm.getWorkPmStdCalibDetailDTO();
    	
    	int pmcTypeCnt = workPmStdCalibDetailService.validDetail(workPmStdCalibDetailDTO, getUser(request));
    	
    	setAjaxDesc(request, pmcTypeCnt);
    }

}