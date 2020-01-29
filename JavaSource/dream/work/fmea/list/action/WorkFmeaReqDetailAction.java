package dream.work.fmea.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaReqDetailDTO;
import dream.work.fmea.list.form.WorkFmeaReqDetailForm;
import dream.work.fmea.list.service.WorkFmeaReqDetailService;

/**
 * 고장영향성평가 - Detail Action
 * 
 * @author kim21017
 * @version $Id: WorkFmeaReqDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workFmeaReqDetail" name="workFmeaReqDetailForm"
 *                input="/dream/work/fmea/list/workFmeaReqDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workFmeaReqDetail" path="/dream/work/fmea/list/workFmeaReqDetail.jsp"
 *                        redirect="false"
 */
public class WorkFmeaReqDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 요청 */
    public static final int REQ_STATUS_UPDATE   = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkFmeaReqDetailForm workFmeaReqDetailForm = (WorkFmeaReqDetailForm) form;

        super.updateAudit(workFmeaReqDetailForm.getWorkFmeaReqDetailDTO().getAuditKey()==""?workFmeaReqDetailForm.getWorkFmeaReqCommonDTO().getAuditKey():workFmeaReqDetailForm.getWorkFmeaReqDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));

        switch (workFmeaReqDetailForm.getStrutsAction())
        {
            case WorkFmeaReqDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workFmeaReqDetailForm);
                returnActionForward = mapping.findForward("workFmeaReqDetail");
                break;
            case WorkFmeaReqDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workFmeaReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkFmeaReqDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workFmeaReqDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkFmeaReqDetailAction.REQ_STATUS_UPDATE:
                updateStatus(workFmeaReqDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workFmeaReqDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workFmeaReqDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaReqDetailForm workFmeaReqDetailForm) throws Exception 
    {   
    	WorkFmeaReqDetailService workFmeaReqDetailService = (WorkFmeaReqDetailService)getBean("workFmeaReqDetailService");
    	
    	WorkFmeaReqCommonDTO workFmeaReqCommonDTO = workFmeaReqDetailForm.getWorkFmeaReqCommonDTO(); 
    	WorkFmeaReqDetailDTO workFmeaReqDetailDTO = workFmeaReqDetailService.findDetail(workFmeaReqCommonDTO, getUser(request));
    	workFmeaReqDetailForm.setWorkFmeaReqDetailDTO(workFmeaReqDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workFmeaReqDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaReqDetailForm workFmeaReqDetailForm) throws Exception
    {
    	WorkFmeaReqDetailService workFmeaReqDetailService = (WorkFmeaReqDetailService)getBean("workFmeaReqDetailService");
    	WorkFmeaReqDetailDTO  workFmeaReqDetailDTO = workFmeaReqDetailForm.getWorkFmeaReqDetailDTO(); 
    	workFmeaReqDetailService.insertDetail(workFmeaReqDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workFmeaReqDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaReqDetailForm workFmeaReqDetailForm) throws Exception
    {
    	WorkFmeaReqDetailService workFmeaReqDetailService = (WorkFmeaReqDetailService)getBean("workFmeaReqDetailService");
    	WorkFmeaReqDetailDTO  workFmeaReqDetailDTO = workFmeaReqDetailForm.getWorkFmeaReqDetailDTO();
    	workFmeaReqDetailService.updateDetail(workFmeaReqDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * UPDATE STATUS
     * @param workFmeaReqDetailForm
     * @param request
     * @throws Exception
     */
    private void updateStatus(WorkFmeaReqDetailForm workFmeaReqDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkFmeaReqDetailService workFmeaReqDetailService = (WorkFmeaReqDetailService)getBean("workFmeaReqDetailService");
    	WorkFmeaReqDetailDTO  workFmeaReqDetailDTO = workFmeaReqDetailForm.getWorkFmeaReqDetailDTO();
    	workFmeaReqDetailService.updateStatus(workFmeaReqDetailDTO, getUser(request));
        
    	setAjaxStatus(request);
    }

}