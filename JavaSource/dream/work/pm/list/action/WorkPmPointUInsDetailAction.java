package dream.work.pm.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.WorkPmPointUInsDetailDTO;
import dream.work.pm.list.form.WorkPmPointUInsDetailForm;
import dream.work.pm.list.service.WorkPmPointUInsDetailService;

/**
 * 사용량 항목 - Detail Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workPmPointUInsDetail" name="workPmPointUInsDetailForm"
 *                input="/dream/work/pm/list/ins/workPmPointUInsDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmPointUInsDetail" path="/dream/work/pm/list/ins/workPmPointUInsDetail.jsp"
 *                        redirect="false"
 */
public class WorkPmPointUInsDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 		= 6003;
    /** 측정순서 셋팅 */
    public static final int SET_STEP_NUM		= 8002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmPointUInsDetailForm workPmPointUInsDetailForm = (WorkPmPointUInsDetailForm) form;
        
        super.updateAudit(workPmPointUInsDetailForm.getWorkPmPointUInsDetailDTO().getAuditKey()==""?workPmPointUInsDetailForm.getMaPmMstrCommonDTO().getAuditKey():workPmPointUInsDetailForm.getWorkPmPointUInsDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmPointUInsDetailForm.getStrutsAction())
        {
            case WorkPmPointUInsDetailAction.DETAIL_INIT:
                this.findDetail(request, response, workPmPointUInsDetailForm);
                returnActionForward = mapping.findForward("workPmPointUInsDetail");
                break;
            case WorkPmPointUInsDetailAction.DETAIL_INPUT:
            	insertDetail(request, response, workPmPointUInsDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmPointUInsDetailAction.DETAIL_UPDATE:
            	updateDetail(request, response, workPmPointUInsDetailForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmPointUInsDetailAction.SET_STEP_NUM:
            	setStepNum(request, response, workPmPointUInsDetailForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("workPmPointUInsDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 사용량 항목 FIND DETAIL
     * @author js.lee
     * @param request
     * @param response
     * @param workPmPointUInsDetailForm
     * @throws Exception
     */
    private void findDetail(HttpServletRequest request, HttpServletResponse response, WorkPmPointUInsDetailForm workPmPointUInsDetailForm) throws Exception 
    {   
    	WorkPmPointUInsDetailService workPmPointUInsDetailService = (WorkPmPointUInsDetailService)getBean("workPmPointUInsDetailService");
        MaPmMstrCommonDTO maPmMstrMstrCommonDTO = workPmPointUInsDetailForm.getMaPmMstrCommonDTO();

    	User user = getUser(request);
    	
    	WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO = workPmPointUInsDetailService.findDetail(maPmMstrMstrCommonDTO, user);
    	workPmPointUInsDetailForm.setWorkPmPointUInsDetailDTO(workPmPointUInsDetailDTO);
    }

    /**
     * 사용량 항목 INSERT DETAIL
     * @author js.lee
     * @param request
     * @param response
     * @param workPmPointUInsDetailForm
     * @throws Exception
     */
    private void insertDetail(HttpServletRequest request, HttpServletResponse response, WorkPmPointUInsDetailForm workPmPointUInsDetailForm) throws Exception
    {
    	WorkPmPointUInsDetailService workPmPointUInsDetailService = (WorkPmPointUInsDetailService)getBean("workPmPointUInsDetailService");
    	WorkPmPointUInsDetailDTO  workPmPointUInsDetailDTO = workPmPointUInsDetailForm.getWorkPmPointUInsDetailDTO(); 
    	
    	User user = getUser(request);
    	
    	workPmPointUInsDetailService.insertDetail(workPmPointUInsDetailDTO, user);
        setAjaxStatus(request);
    }

    /**
     * 사용량 항목 UPDATE DETAIL
     * @author js.lee
     * @param request
     * @param response
     * @param workPmPointUInsDetailForm
     * @throws Exception
     */
    private void updateDetail(HttpServletRequest request, HttpServletResponse response, WorkPmPointUInsDetailForm workPmPointUInsDetailForm) throws Exception
    {
    	WorkPmPointUInsDetailService workPmPointUInsDetailService = (WorkPmPointUInsDetailService)getBean("workPmPointUInsDetailService");
    	WorkPmPointUInsDetailDTO  workPmPointUInsDetailDTO = workPmPointUInsDetailForm.getWorkPmPointUInsDetailDTO();
    	
    	User user = getUser(request);
    	
    	workPmPointUInsDetailService.updateDetail(workPmPointUInsDetailDTO, user);
        
        setAjaxStatus(request);
    }
    
    
    /**
     * 측정순서 자동 셋팅
     * @author js.lee
     * @since   1.0
     *
     * @param request
     * @param response
     * @param workPmPointUInsDetailForm
     */
    private void setStepNum(HttpServletRequest request, HttpServletResponse response, WorkPmPointUInsDetailForm workPmPointUInsDetailForm) throws Exception{
    	WorkPmPointUInsDetailService workPmPointUInsDetailService = (WorkPmPointUInsDetailService)getBean("workPmPointUInsDetailService");
    	WorkPmPointUInsDetailDTO workPmPointUInsDetailDTO = workPmPointUInsDetailForm.getWorkPmPointUInsDetailDTO();
    	
    	User user = getUser(request);
    	
    	String stepNum = workPmPointUInsDetailService.setStepNum(workPmPointUInsDetailDTO, user);
    	setAjaxDesc(request, stepNum);
    	
    }
    

}