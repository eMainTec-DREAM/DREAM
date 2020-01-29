package dream.work.pm.list.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmListShiftDetailDTO;
import dream.work.pm.list.form.WorkPmListShiftDetailForm;
import dream.work.pm.list.service.WorkPmListShiftDetailService;

/**
 * 교대조
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workPmListPtrlShiftDetail" name="workPmListShiftDetailForm"
 *                input="/dream/work/pm/list/workPmListPtrlShiftDetail.jsp" scope="request"
 *                validate="false"
 */
public class WorkPmListShiftDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PM_SHIFT_DETAIL_INIT 		= 1001;
    /** 수정 */
    public static final int WORK_PM_SHIFT_DETAIL_UPDATE 	= 1002;
    /** 입력 */
    public static final int WORK_PM_SHIFT_DETAIL_INPUT 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListShiftDetailForm workPmListShiftDetailForm = (WorkPmListShiftDetailForm) form;
        switch (workPmListShiftDetailForm.getStrutsAction())
        {
            case WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workPmListShiftDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_UPDATE:
            	updateDetail(workPmListShiftDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListShiftDetailAction.WORK_PM_SHIFT_DETAIL_INPUT:
            	insertDetail(workPmListShiftDetailForm, request);
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
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workPmListShiftDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkPmListShiftDetailForm workPmListShiftDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkPmListShiftDetailService workPmListShiftDetailService = (WorkPmListShiftDetailService)getBean("workPmListShiftDetailService");

    	// 순회코스Id 구함
        String pmId = workPmListShiftDetailForm.getMaPmMstrCommonDTO().getPmId();
        // 넘겨진 교대조id 구함
        String pmWrkShiftId = workPmListShiftDetailForm.getMaPmMstrCommonDTO().getPmWrkShiftId();
        
        User loginUser = getUser(request);
        
        // 조회된 상세 부분
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = workPmListShiftDetailService.findDetail(pmId, pmWrkShiftId, loginUser);
        
        // 조회된 상세  셋팅한다.
        workPmListShiftDetailForm.setWorkPmListShiftDetailDTO(workPmListShiftDetailDTO);
    }
    /**
     * detail update
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailForm
     * @param request
     */
    private void updateDetail(WorkPmListShiftDetailForm workPmListShiftDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListShiftDetailService workPmListShiftDetailService = (WorkPmListShiftDetailService) getBean("workPmListShiftDetailService");
        
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = workPmListShiftDetailForm.getWorkPmListShiftDetailDTO();
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListShiftDetailForm.getMaPmMstrCommonDTO();
        workPmListShiftDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListShiftDetailService.updateDetail(workPmListShiftDetailDTO,maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workPmListShiftDetailForm
     * @param request
     */
    private void insertDetail(WorkPmListShiftDetailForm workPmListShiftDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkPmListShiftDetailService workPmListShiftDetailService = (WorkPmListShiftDetailService) getBean("workPmListShiftDetailService");
        
        WorkPmListShiftDetailDTO workPmListShiftDetailDTO = workPmListShiftDetailForm.getWorkPmListShiftDetailDTO();
        
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmListShiftDetailForm.getMaPmMstrCommonDTO();
        workPmListShiftDetailDTO.setEnterBy(getUser(request).getUserId());
        maPmMstrMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workPmListShiftDetailService.insertDetail(workPmListShiftDetailDTO, maPmMstrMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}