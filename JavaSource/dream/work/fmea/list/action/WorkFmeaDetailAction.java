package dream.work.fmea.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaDetailDTO;
import dream.work.fmea.list.form.WorkFmeaDetailForm;
import dream.work.fmea.list.service.WorkFmeaDetailService;

/**
 * 고장영향성평가 - Detail Action
 * 
 * @author kim21017
 * @version $Id: WorkFmeaDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workFmeaDetail" name="workFmeaDetailForm"
 *                input="/dream/work/fmea/list/workFmeaDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workFmeaDetail" path="/dream/work/fmea/list/workFmeaDetail.jsp"
 *                        redirect="false"
 */
public class WorkFmeaDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 확정 */
    public static final int DETAIL_CONFIRM 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkFmeaDetailForm workFmeaDetailForm = (WorkFmeaDetailForm) form;
        
        super.updateAudit(workFmeaDetailForm.getWorkFmeaDetailDTO().getAuditKey()==""?workFmeaDetailForm.getWorkFmeaCommonDTO().getAuditKey():workFmeaDetailForm.getWorkFmeaDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workFmeaDetailForm.getStrutsAction())
        {
            case WorkFmeaDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workFmeaDetailForm);
                returnActionForward = mapping.findForward("workFmeaDetail");
                break;
            case WorkFmeaDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workFmeaDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkFmeaDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workFmeaDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkFmeaDetailAction.DETAIL_CONFIRM:
            	confirmDetail(workFmeaDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workFmeaDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workFmeaDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaDetailForm workFmeaDetailForm) throws Exception 
    {   
    	WorkFmeaDetailService workFmeaDetailService = (WorkFmeaDetailService)getBean("workFmeaDetailService", request);
    	
    	WorkFmeaCommonDTO workFmeaCommonDTO = workFmeaDetailForm.getWorkFmeaCommonDTO(); 
    	WorkFmeaDetailDTO workFmeaDetailDTO = workFmeaDetailService.findDetail(workFmeaCommonDTO, getUser(request));
    	workFmeaDetailForm.setWorkFmeaDetailDTO(workFmeaDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workFmeaDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaDetailForm workFmeaDetailForm) throws Exception
    {
    	WorkFmeaDetailService workFmeaDetailService = (WorkFmeaDetailService)getBean("workFmeaDetailService", request);
    	WorkFmeaDetailDTO  workFmeaDetailDTO = workFmeaDetailForm.getWorkFmeaDetailDTO(); 
    	workFmeaDetailService.insertDetail(workFmeaDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workFmeaDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaDetailForm workFmeaDetailForm) throws Exception
    {
    	WorkFmeaDetailService workFmeaDetailService = (WorkFmeaDetailService)getBean("workFmeaDetailService", request);
    	WorkFmeaDetailDTO  workFmeaDetailDTO = workFmeaDetailForm.getWorkFmeaDetailDTO();
    	workFmeaDetailService.updateDetail(workFmeaDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * CONFIRM DETAIL
     * @param workFmeaDetailForm
     * @param request
     * @throws Exception
     */
    private void confirmDetail(WorkFmeaDetailForm workFmeaDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkFmeaDetailService workFmeaDetailService = (WorkFmeaDetailService)getBean("workFmeaDetailService", request);
    	WorkFmeaDetailDTO  workFmeaDetailDTO = workFmeaDetailForm.getWorkFmeaDetailDTO();
    	workFmeaDetailService.confirmDetail(workFmeaDetailDTO, getUser(request));
        
    	setAjaxStatus(request);
    }

}