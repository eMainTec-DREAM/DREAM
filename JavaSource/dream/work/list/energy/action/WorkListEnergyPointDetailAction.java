package dream.work.list.energy.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointDetailDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;
import dream.work.list.energy.form.WorkListEnergyPointDetailForm;
import dream.work.list.energy.service.WorkListEnergyPointDetailService;

/**
 * 에너지 값 측정항목 상세 action
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointDetailAction.java,v 1.0 2015/12/04 09:09:30 sy.yang Exp $
 * @since   1.0
 * @struts:action path="/workListEnergyPointDetail" name="workListEnergyPointDetailForm"
 *                input="/dream/work/list/energy/workListEnergyPointDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListEnergyPointDetail" path="/dream/work/list/energy/workListEnergyPointDetail.jsp"
 *                        redirect="false"
 */
public class WorkListEnergyPointDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PMU_POINT_DETAIL_INIT 		= 8001;
    /** 수정 */
    public static final int WORK_PMU_POINT_DETAIL_UPDATE 	= 6001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListEnergyPointDetailForm workListEnergyPointDetailForm = (WorkListEnergyPointDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListEnergyPointDetailForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workListEnergyPointDetailForm.getWorkListEnergyPointDetailDTO().getAuditKey()==""?workListEnergyPointDetailForm.getWorkListEnergyPointListDTO().getAuditKey():workListEnergyPointDetailForm.getWorkListEnergyPointDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListEnergyPointDetailForm.getStrutsAction())
        {
            case WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workListEnergyPointDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListEnergyPointDetailAction.WORK_PMU_POINT_DETAIL_UPDATE:
            	updateDetail(workListEnergyPointDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 에너지값 - 측정항목 상세 조회
     * @author sy.yang
     * @version $Id: WorkListEnergyPointDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListEnergyPointDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListEnergyPointDetailForm workListEnergyPointDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkListEnergyPointDetailService workListEnergyPointDetailService = (WorkListEnergyPointDetailService)getBean("workListEnergyPointDetailService");

    	WorkListEnergyPointListDTO workListEnergyPointListDTO = workListEnergyPointDetailForm.getWorkListEnergyPointListDTO();
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyPointDetailForm.getWorkListEnergyMstrListCommonDTO();
        
//    	// 작업결과Id 구함
//        String pminslistId = workListEnergyPointDetailForm.getWorkListEnergyMstrListCommonDTO().getPminslistId();
//        // 넘겨진 점검항목id 구함
//        String pmInsPointId = workListEnergyPointDetailForm.getWorkListEnergyPointListDTO().getPmInsPointId();
//        // 넘겨진 점검항목id 구함
//        String pmPointId = workListEnergyPointDetailForm.getWorkListEnergyPointListDTO().getPmPointId();
        
        // 조회된 상세 부분
        WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = workListEnergyPointDetailService.findDetail(workListEnergyMstrListCommonDTO, workListEnergyPointListDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        workListEnergyPointDetailForm.setWorkListEnergyPointDetailDTO(workListEnergyPointDetailDTO);
    }
    /**
     * detail update
     * @author  sy.yang
     * @version $Id: WorkListEnergyPointDetailAction.java,v 1.2 2015/12/02 01:21:30 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyPointDetailForm
     * @param request
     */
    private void updateDetail(WorkListEnergyPointDetailForm workListEnergyPointDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListEnergyPointDetailService workListEnergyPointDetailService = (WorkListEnergyPointDetailService) getBean("workListEnergyPointDetailService");
        
        WorkListEnergyPointDetailDTO workListEnergyPointDetailDTO = workListEnergyPointDetailForm.getWorkListEnergyPointDetailDTO();
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyPointDetailForm.getWorkListEnergyMstrListCommonDTO();
        
        User user = getUser(request);
        
        workListEnergyPointDetailService.updateDetail(workListEnergyPointDetailDTO,workListEnergyMstrListCommonDTO,user);
        
        setAjaxStatus(request);
    }
    
}