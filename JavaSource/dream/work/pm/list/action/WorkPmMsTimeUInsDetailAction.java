package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsDetailDTO;
import dream.work.pm.list.dto.WorkPmMsTimeUInsListDTO;
import dream.work.pm.list.form.WorkPmMsTimeUInsDetailForm;
import dream.work.pm.list.service.WorkPmMsTimeUInsDetailService;

/**
 * 작업시간 상세 Action
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @struts:action path="/workPmMsTimeUInsDetail" name="workPmMsTimeUInsDetailForm"
 *                input="/dream/work/pm/list/ins/workPmMsTimeUInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmMsTimeUInsDetail" path="/dream/work/pm/list/ins/workPmMsTimeUInsDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmMsTimeUInsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_MS_TIME_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int PM_MS_TIME_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int PM_MS_TIME_DETAIL_INPUT 	= 5003;
    /** 중복체크 */
    public static final int PM_MS_TIME_DETAIL_CHECK		= 8004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmMsTimeUInsDetailForm workPmMsTimeUInsDetailForm = (WorkPmMsTimeUInsDetailForm) form;
        
        super.updateAudit(workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsDetailDTO().getAuditKey()==""?workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsDetailDTO().getAuditKey():workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmMsTimeUInsDetailForm.getStrutsAction())
        {
            case WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workPmMsTimeUInsDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_UPDATE:
            	updateDetail(workPmMsTimeUInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_INPUT:
            	insertDetail(workPmMsTimeUInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmMsTimeUInsDetailAction.PM_MS_TIME_DETAIL_CHECK:
            	validTime(workPmMsTimeUInsDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 상세 조회
     * @author js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmMsTimeUInsDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmMsTimeUInsDetailForm workPmMsTimeUInsDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkPmMsTimeUInsDetailService workPmMsTimeUInsDetailService = (WorkPmMsTimeUInsDetailService)getBean("workPmMsTimeUInsDetailService");
    	
    	WorkPmMsTimeUInsListDTO workPmMsTimeUInsListDTO = workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsListDTO();
        // 조회된 상세 부분
    	WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = workPmMsTimeUInsDetailService.findDetail(workPmMsTimeUInsListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        workPmMsTimeUInsDetailForm.setWorkPmMsTimeUInsDetailDTO(workPmMsTimeUInsDetailDTO);
    }
    /**
     * detail update
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailForm
     * @param request
     */
    private void updateDetail(WorkPmMsTimeUInsDetailForm workPmMsTimeUInsDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmMsTimeUInsDetailService workPmMsTimeUInsDetailService = (WorkPmMsTimeUInsDetailService) getBean("workPmMsTimeUInsDetailService");
    	
        WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsDetailDTO();
        
        workPmMsTimeUInsDetailService.updateDetail(workPmMsTimeUInsDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailForm
     * @param request
     */
    private void insertDetail(WorkPmMsTimeUInsDetailForm workPmMsTimeUInsDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmMsTimeUInsDetailService workPmMsTimeUInsDetailService = (WorkPmMsTimeUInsDetailService) getBean("workPmMsTimeUInsDetailService");
        
        WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsDetailDTO();
        workPmMsTimeUInsDetailDTO.setEnterBy(getUser(request).getUserId());
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmMsTimeUInsDetailForm.getMaPmMstrCommonDTO();
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmMsTimeUInsDetailService.insertDetail(workPmMsTimeUInsDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * check time
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmMsTimeUInsDetailForm
     * @param request
     * @throws Exception
     */
    private void validTime(WorkPmMsTimeUInsDetailForm workPmMsTimeUInsDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmMsTimeUInsDetailService workPmMsTimeUInsDetailService = (WorkPmMsTimeUInsDetailService) getBean("workPmMsTimeUInsDetailService");
        
    	WorkPmMsTimeUInsDetailDTO workPmMsTimeUInsDetailDTO = workPmMsTimeUInsDetailForm.getWorkPmMsTimeUInsDetailDTO();
        
    	MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmMsTimeUInsDetailForm.getMaPmMstrCommonDTO();
        String isValid = workPmMsTimeUInsDetailService.validTime(workPmMsTimeUInsDetailDTO,maPmMstrMstrCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}