package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeDetailDTO;
import dream.work.pm.list.dto.WorkPmListMsTimeListDTO;
import dream.work.pm.list.form.WorkPmListMsTimeDetailForm;
import dream.work.pm.list.service.WorkPmListMsTimeDetailService;

/**
 * 작업시간 상세 Action
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @struts:action path="/workPmListMsTimeDetail" name="workPmListMsTimeDetailForm"
 *                input="/dream/work/pm/list/workPmListMsTimeDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListMsTimeDetail" path="/dream/work/pm/list/workPmListMsTimeDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmListMsTimeDetailAction extends AuthAction
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
        WorkPmListMsTimeDetailForm workPmListMsTimeDetailForm = (WorkPmListMsTimeDetailForm) form;
        
        super.updateAudit(workPmListMsTimeDetailForm.getWorkPmListMsTimeDetailDTO().getAuditKey()==""?workPmListMsTimeDetailForm.getWorkPmListMsTimeDetailDTO().getAuditKey():workPmListMsTimeDetailForm.getWorkPmListMsTimeDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmListMsTimeDetailForm.getStrutsAction())
        {
            case WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workPmListMsTimeDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_UPDATE:
            	updateDetail(workPmListMsTimeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_INPUT:
            	insertDetail(workPmListMsTimeDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListMsTimeDetailAction.PM_MS_TIME_DETAIL_CHECK:
            	validTime(workPmListMsTimeDetailForm, request);
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
     * @param workPmListMsTimeDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmListMsTimeDetailForm workPmListMsTimeDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkPmListMsTimeDetailService workPmListMsTimeDetailService = (WorkPmListMsTimeDetailService)getBean("workPmListMsTimeDetailService");
    	
    	WorkPmListMsTimeListDTO workPmListMsTimeListDTO = workPmListMsTimeDetailForm.getWorkPmListMsTimeListDTO();
        // 조회된 상세 부분
    	WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO = workPmListMsTimeDetailService.findDetail(workPmListMsTimeListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        workPmListMsTimeDetailForm.setWorkPmListMsTimeDetailDTO(workPmListMsTimeDetailDTO);
    }
    /**
     * detail update
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailForm
     * @param request
     */
    private void updateDetail(WorkPmListMsTimeDetailForm workPmListMsTimeDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListMsTimeDetailService workPmListMsTimeDetailService = (WorkPmListMsTimeDetailService) getBean("workPmListMsTimeDetailService");
    	
        WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO = workPmListMsTimeDetailForm.getWorkPmListMsTimeDetailDTO();
        
        workPmListMsTimeDetailService.updateDetail(workPmListMsTimeDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailForm
     * @param request
     */
    private void insertDetail(WorkPmListMsTimeDetailForm workPmListMsTimeDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListMsTimeDetailService workPmListMsTimeDetailService = (WorkPmListMsTimeDetailService) getBean("workPmListMsTimeDetailService");
        
        WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO = workPmListMsTimeDetailForm.getWorkPmListMsTimeDetailDTO();
        workPmListMsTimeDetailDTO.setEnterBy(getUser(request).getUserId());
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListMsTimeDetailForm.getMaPmMstrCommonDTO();
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListMsTimeDetailService.insertDetail(workPmListMsTimeDetailDTO, maPmMstrMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    /**
     * check time
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param workPmListMsTimeDetailForm
     * @param request
     * @throws Exception
     */
    private void validTime(WorkPmListMsTimeDetailForm workPmListMsTimeDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListMsTimeDetailService workPmListMsTimeDetailService = (WorkPmListMsTimeDetailService) getBean("workPmListMsTimeDetailService");
        
    	WorkPmListMsTimeDetailDTO workPmListMsTimeDetailDTO = workPmListMsTimeDetailForm.getWorkPmListMsTimeDetailDTO();
        
    	MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListMsTimeDetailForm.getMaPmMstrCommonDTO();
        String isValid = workPmListMsTimeDetailService.validTime(workPmListMsTimeDetailDTO,maPmMstrMstrCommonDTO, getUser(request));
        setAjaxDesc(request, isValid);
    }
}