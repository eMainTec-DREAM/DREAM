package dream.work.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;
import dream.work.list.form.WorkListGnlCavalDetailForm;
import dream.work.list.service.WorkListGnlCavalDetailService;

/**
 * 작업상세  - 검교정 - 측정값
 * @author  kim21017
 * @version $Id: WorkListGnlCavalDetailAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListGnlCavalDetail" name="workListGnlCavalDetailForm"
 *                input="/dream/work/list/pmc/workListGnlCavalDetail.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListPrsCavalDetail" name="workListGnlCavalDetailForm"
 *                input="/dream/work/list/pmc/workListPrsCavalDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListGnlCavalDetail" path="/dream/work/list/pmc/workListGnlCavalDetail.jsp"
 *                        redirect="false"
 */
public class WorkListGnlCavalDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_GNL_CAVAL_DETAIL_INIT 	= 8001;
    /** 수정 */
    public static final int WORK_LIST_GNL_CAVAL_DETAIL_UPDATE 	= 6002;
    /** 입력 */
    public static final int WORK_LIST_GNL_CAVAL_DETAIL_INPUT 	= 5003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListGnlCavalDetailForm workListGnlCavalDetailForm = (WorkListGnlCavalDetailForm) form;
        
        super.updateAudit(workListGnlCavalDetailForm.getWorkListGnlCavalDetailDTO().getAuditKey()==""?workListGnlCavalDetailForm.getMaWoResultMstrCommonDTO().getAuditKey():workListGnlCavalDetailForm.getWorkListGnlCavalDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListGnlCavalDetailForm.getStrutsAction())
        {
            case WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_INIT:
                this.findDetail(request, workListGnlCavalDetailForm);
                returnActionForward = mapping.getInputForward();
                break;
            case WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_UPDATE:
            	updateDetail(workListGnlCavalDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCavalDetailAction.WORK_LIST_GNL_CAVAL_DETAIL_INPUT:
            	insertDetail(workListGnlCavalDetailForm, request);
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
     * @version $Id: WorkListGnlCavalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListGnlCavalDetailForm
     */
    private void findDetail(HttpServletRequest request, WorkListGnlCavalDetailForm workListGnlCavalDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	WorkListGnlCavalDetailService workListGnlCavalDetailService = (WorkListGnlCavalDetailService)getBean("workListGnlCavalDetailService");

        // 조회된 상세 부분
        WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO = workListGnlCavalDetailService.findDetail(workListGnlCavalDetailForm.getMaWoResultMstrCommonDTO(), getUser(request));
        
        // 조회된 상세  셋팅한다.
        workListGnlCavalDetailForm.setWorkListGnlCavalDetailDTO(workListGnlCavalDetailDTO);
    }
    /**
     * detail update
     * @author  kim21017
     * @version $Id: WorkListGnlCavalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailForm
     * @param request
     */
    private void updateDetail(WorkListGnlCavalDetailForm workListGnlCavalDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListGnlCavalDetailService workListGnlCavalDetailService = (WorkListGnlCavalDetailService) getBean("workListGnlCavalDetailService");
        
        WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO = workListGnlCavalDetailForm.getWorkListGnlCavalDetailDTO();
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCavalDetailForm.getMaWoResultMstrCommonDTO();
        workListGnlCavalDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListGnlCavalDetailService.updateDetail(workListGnlCavalDetailDTO,maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
    /**
     * detail insert
     * @author  kim21017
     * @version $Id: WorkListGnlCavalDetailAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workListGnlCavalDetailForm
     * @param request
     */
    private void insertDetail(WorkListGnlCavalDetailForm workListGnlCavalDetailForm, HttpServletRequest request) throws Exception
    {
    	WorkListGnlCavalDetailService workListGnlCavalDetailService = (WorkListGnlCavalDetailService) getBean("workListGnlCavalDetailService");
        
        WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO = workListGnlCavalDetailForm.getWorkListGnlCavalDetailDTO();
        
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCavalDetailForm.getMaWoResultMstrCommonDTO();
        workListGnlCavalDetailDTO.setEnterBy(getUser(request).getUserId());
        maWoResultMstrCommonDTO.setCompNo(getUser(request).getCompNo());
        
        workListGnlCavalDetailService.insertDetail(workListGnlCavalDetailDTO, maWoResultMstrCommonDTO);
        
        setAjaxStatus(request);
    }
}