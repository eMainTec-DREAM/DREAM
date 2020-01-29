package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListCavalDetailDTO;
import dream.work.list.form.WorkListCavalDetailForm;
import dream.work.list.service.WorkListCavalDetailService;

/**
 * 작업상세  - 검교정 - 측정값
 * @author  kim21017
 * @version $Id: WorkListCavalDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListCavalDetail" name="workListCavalDetailForm"
 *                input="/dream/work/list/pmc/workListCavalDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListCavalDetail" path="/dream/work/list/pmc/workListCavalDetail.jsp"
 *                        redirect="false"
 */
public class WorkListCavalDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_CAVAL_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int WORK_LIST_CAVAL_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WORK_LIST_CAVAL_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListCavalDetailForm workListCavalDetailForm = (WorkListCavalDetailForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListCavalDetailForm.setStrutsAction(Integer.parseInt(strutsAction));

        super.updateAudit(workListCavalDetailForm.getWorkListCavalDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListCavalDetailForm.getStrutsAction())
        {
            case WorkListCavalDetailAction.WORK_LIST_CAVAL_DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, workListCavalDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListCavalDetailAction.WORK_LIST_CAVAL_DETAIL_UPDATE:
            	updateDetail(workListCavalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListCavalDetailAction.WORK_LIST_CAVAL_DETAIL_INPUT:
            	insertDetail(workListCavalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 작업상세  - 검교정 - 측정값 상세 조회
     * @author kim2107
     * @version $Id: WorkListCavalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListCavalDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListCavalDetailForm workListCavalDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkListCavalDetailService workListCavalDetailService = (WorkListCavalDetailService)getBean("workListCavalDetailService");

    	// 작업결과Id 구함
        String wkOrId = workListCavalDetailForm.getMaWoResultMstrCommonDTO().getWkOrId();
        // 넘겨진 작업자id 구함
        String woCalibValueId = workListCavalDetailForm.getWorkListCavalListDTO().getWoCalibValueId();
        
        // 조회된 상세 부분
        WorkListCavalDetailDTO workListCavalDetailDTO = workListCavalDetailService.findDetail(wkOrId, woCalibValueId, getUser(request).getCompNo());
        
        // 조회된 상세  셋팅한다.
        workListCavalDetailForm.setWorkListCavalDetailDTO(workListCavalDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkListCavalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailForm
     * @param request
     */
    private void updateDetail(WorkListCavalDetailForm workListCavalDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListCavalDetailService workListCavalDetailService = (WorkListCavalDetailService) getBean("workListCavalDetailService");
        
        WorkListCavalDetailDTO workListCavalDetailDTO = workListCavalDetailForm.getWorkListCavalDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListCavalDetailForm.getMaWoResultMstrCommonDTO();
        workListCavalDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListCavalDetailService.updateDetail(workListCavalDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkListCavalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListCavalDetailForm
     * @param request
     */
    private void insertDetail(WorkListCavalDetailForm workListCavalDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListCavalDetailService workListCavalDetailService = (WorkListCavalDetailService) getBean("workListCavalDetailService");
        
        WorkListCavalDetailDTO workListCavalDetailDTO = workListCavalDetailForm.getWorkListCavalDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListCavalDetailForm.getMaWoResultMstrCommonDTO();
        workListCavalDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListCavalDetailService.insertDetail(workListCavalDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}