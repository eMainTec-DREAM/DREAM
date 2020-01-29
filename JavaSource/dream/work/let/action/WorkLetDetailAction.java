package dream.work.let.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.dto.WorkLetDetailDTO;
import dream.work.let.form.WorkLetDetailForm;
import dream.work.let.service.WorkLetDetailService;

/**
 * 안전작업 - 상세 action
 * 
 * @author syyang
 * @version $Id: WorkLetDetailAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
 * @since 1.0
 * @struts:action path="/workLetDetail" name="workLetDetailForm"
 *                input="/dream/work/let/workLetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetDetail" path=/dream/work/let/workLetDetail.jsp"
 *                        redirect="false"
 */
public class WorkLetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_LET_DETAIL_INIT 			= 8001;
    /** 저장 */
    public static final int WO_LET_DETAIL_INPUT 		= 5001;
    /** 수정 */
    public static final int WO_LET_DETAIL_UPDATE 		= 6002;
    /** 완료 */
    public static final int WO_LET_DETAIL_COMPLETE 		= 6003;
    /** 완료취소 */
    public static final int WO_LET_DETAIL_COMPLETE_CANCEL 		= 6004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetDetailForm workLetDetailForm = (WorkLetDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workLetDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workLetDetailForm.getWorkLetDetailDTO().getAuditKey()==""?workLetDetailForm.getWorkLetCommonDTO().getAuditKey():workLetDetailForm.getWorkLetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workLetDetailForm.getStrutsAction())
        {
            case WorkLetDetailAction.WO_LET_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workLetDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkLetDetailAction.WO_LET_DETAIL_INPUT:
            	insertDetail(workLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetDetailAction.WO_LET_DETAIL_UPDATE:
            	updateDetail(workLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetDetailAction.WO_LET_DETAIL_COMPLETE:
                completeDetail(workLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkLetDetailAction.WO_LET_DETAIL_COMPLETE_CANCEL:
            	completeCancelDetail(workLetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

	/**
     * 안전작업 상세 조회
     * @author syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workLetDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkLetDetailForm workLetDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkLetDetailService workLetDetailService = (WorkLetDetailService)getBean("workLetDetailService");

    	WorkLetCommonDTO workLetCommonDTO = workLetDetailForm.getWorkLetCommonDTO();
    	
        // 조회된 상세 부분
        WorkLetDetailDTO workLetDetailDTO = workLetDetailService.findDetail(workLetCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        workLetDetailForm.setWorkLetDetailDTO(workLetDetailDTO);
    }
    /**
     * detail insert
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailForm
     * @param request
     */
    private void insertDetail(WorkLetDetailForm workLetDetailForm, HttpServletRequest request) throws Exception
    {
        WorkLetDetailService workLetDetailService = (WorkLetDetailService) getBean("workLetDetailService");
        
        WorkLetDetailDTO workLetDetailDTO = workLetDetailForm.getWorkLetDetailDTO();
        WorkLetCommonDTO workLetCommonDTO = workLetDetailForm.getWorkLetCommonDTO();

//        workLetCommonDTO.setCompNo(getUser(request).getCompNo());
//        workLetDetailDTO.setEnterBy(getUser(request).getUserId());
//        workLetDetailDTO.setCompNo(getUser(request).getCompNo());
        
        User loginUser = getUser(request);
        
        workLetDetailService.insertDetail(workLetDetailDTO, workLetCommonDTO, loginUser);
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  syyang
     * @version $Id$
     * @since   1.0
     * 
     * @param workLetDetailForm
     * @param request
     */
    private void updateDetail(WorkLetDetailForm workLetDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetDetailService workLetDetailService = (WorkLetDetailService) getBean("workLetDetailService");
        
        WorkLetDetailDTO workLetDetailDTO = workLetDetailForm.getWorkLetDetailDTO();
        WorkLetCommonDTO workLetCommonDTO = workLetDetailForm.getWorkLetCommonDTO();
        
    	workLetCommonDTO.setCompNo(getUser(request).getCompNo());
        workLetDetailDTO.setEnterBy(getUser(request).getUserId());
        workLetDetailDTO.setCompNo(getUser(request).getCompNo());
        
        workLetDetailService.updateDetail(workLetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail complete
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetDetailForm
     * @param request
     */
    private void completeDetail(WorkLetDetailForm workLetDetailForm, HttpServletRequest request) throws Exception
    {
        WorkLetDetailService workLetDetailService = (WorkLetDetailService) getBean("workLetDetailService",request);
        
        WorkLetDetailDTO workLetDetailDTO = workLetDetailForm.getWorkLetDetailDTO();
        
        workLetDetailService.completeDetail(workLetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * detail complete
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param workLetDetailForm
     * @param request
     */
    private void completeCancelDetail(WorkLetDetailForm workLetDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkLetDetailService workLetDetailService = (WorkLetDetailService) getBean("workLetDetailService");
    	
    	WorkLetDetailDTO workLetDetailDTO = workLetDetailForm.getWorkLetDetailDTO();
        
    	String status = workLetDetailService.completeCancelDetail(workLetDetailDTO, getUser(request));

    	setAjaxDesc(request, status);
    }
    
}