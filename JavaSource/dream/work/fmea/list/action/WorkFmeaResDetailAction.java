package dream.work.fmea.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaResDetailDTO;
import dream.work.fmea.list.form.WorkFmeaResDetailForm;
import dream.work.fmea.list.service.WorkFmeaResDetailService;

/**
 * 고장영향성평가 - Detail Action
 * 
 * @author kim21017
 * @version $Id: WorkFmeaResDetailAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workFmeaResDetail" name="workFmeaResDetailForm"
 *                input="/dream/work/fmea/list/workFmeaResDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workFmeaResDetail" path="/dream/work/fmea/list/workFmeaResDetail.jsp"
 *                        redirect="false"
 */
public class WorkFmeaResDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 평가완료 */
    public static final int DETAIL_COMPLETED    = 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkFmeaResDetailForm workFmeaResDetailForm = (WorkFmeaResDetailForm) form;
        
        super.updateAudit(workFmeaResDetailForm.getWorkFmeaResDetailDTO().getAuditKey()==""?workFmeaResDetailForm.getWorkFmeaResCommonDTO().getAuditKey():workFmeaResDetailForm.getWorkFmeaResDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workFmeaResDetailForm.getStrutsAction())
        {
            case WorkFmeaResDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workFmeaResDetailForm);
                returnActionForward = mapping.findForward("workFmeaResDetail");
                break;
            case WorkFmeaResDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workFmeaResDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkFmeaResDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workFmeaResDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkFmeaResDetailAction.DETAIL_COMPLETED:
                completedDetail(request, response, workFmeaResDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            default:
                returnActionForward = mapping.findForward("workFmeaResDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * FIND DETAIL
     * @param request
     * @param response
     * @param workFmeaResDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaResDetailForm workFmeaResDetailForm) throws Exception 
    {   
        WorkFmeaResDetailService workFmeaResDetailService = (WorkFmeaResDetailService)getBean("workFmeaResDetailService");
    	
    	WorkFmeaResCommonDTO workFmeaResCommonDTO = workFmeaResDetailForm.getWorkFmeaResCommonDTO(); 
    	WorkFmeaResDetailDTO workFmeaResDetailDTO = workFmeaResDetailService.findDetail(workFmeaResCommonDTO, getUser(request));
    	workFmeaResDetailForm.setWorkFmeaResDetailDTO(workFmeaResDetailDTO);
    }

    /**
     * INSERT DETAIL
     * @param request
     * @param response
     * @param workFmeaResDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaResDetailForm workFmeaResDetailForm) throws Exception
    {
    	WorkFmeaResDetailService workFmeaResDetailService = (WorkFmeaResDetailService)getBean("workFmeaResDetailService");
    	WorkFmeaResDetailDTO  workFmeaResDetailDTO = workFmeaResDetailForm.getWorkFmeaResDetailDTO(); 
    	workFmeaResDetailService.insertDetail(workFmeaResDetailDTO, getUser(request));
        setAjaxStatus(request);
    }

    /**
     * UPDATE DETAIL
     * @param request
     * @param response
     * @param workFmeaResDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaResDetailForm workFmeaResDetailForm) throws Exception
    {
    	WorkFmeaResDetailService workFmeaResDetailService = (WorkFmeaResDetailService)getBean("workFmeaResDetailService");
    	WorkFmeaResDetailDTO  workFmeaResDetailDTO = workFmeaResDetailForm.getWorkFmeaResDetailDTO();
    	workFmeaResDetailService.updateDetail(workFmeaResDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * COMPLETED DETAIL
     * @param request
     * @param response
     * @param workFmeaResDetailForm
     * @throws Exception
     */
    private void completedDetail(HttpServletRequest request, HttpServletResponse response, WorkFmeaResDetailForm workFmeaResDetailForm) throws Exception
    {
        WorkFmeaResDetailService workFmeaResDetailService = (WorkFmeaResDetailService)getBean("workFmeaResDetailService");
        WorkFmeaResDetailDTO  workFmeaResDetailDTO = workFmeaResDetailForm.getWorkFmeaResDetailDTO();
        WorkFmeaResCommonDTO workFmeaResCommonDTO = workFmeaResDetailForm.getWorkFmeaResCommonDTO();
        
        User user = getUser(request);
        workFmeaResDetailService.completedDetail(workFmeaResCommonDTO, workFmeaResDetailDTO, user);
        setAjaxStatus(request);
    }
    
}