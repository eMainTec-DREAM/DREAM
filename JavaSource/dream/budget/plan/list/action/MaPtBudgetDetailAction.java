package dream.budget.plan.list.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.budget.plan.list.dto.MaPtBudgetDetailDTO;
import dream.budget.plan.list.form.MaPtBudgetDetailForm;
import dream.budget.plan.list.service.MaPtBudgetDetailService;

/**
 * 예산관리 - 상세 action
 * 
 * @author 
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/maPtBudgetDetail" name="maPtBudgetDetailForm"
 *                input="/dream/budget/plan/list/maPtBudgetDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtBudgetDetail" path="/dream/budget/plan/list/maPtBudgetDetail.jsp"
 *                        redirect="false"
 */
public class MaPtBudgetDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTBUDGET_DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int PTBUDGET_DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int PTBUDGET_DETAIL_UPDATE 		= 6003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtBudgetDetailForm maPtBudgetDetailForm = (MaPtBudgetDetailForm) form;
       
        super.updateAudit(maPtBudgetDetailForm.getMaPtBudgetDetailDTO().getAuditKey()==""?maPtBudgetDetailForm.getMaPtBudgetCommonDTO().getAuditKey():maPtBudgetDetailForm.getMaPtBudgetDetailDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (maPtBudgetDetailForm.getStrutsAction())
        {
            case MaPtBudgetDetailAction.PTBUDGET_DETAIL_INIT:
                this.findDetail(request, maPtBudgetDetailForm);
                returnActionForward = mapping.findForward("maPtBudgetDetail");
                break;
            case MaPtBudgetDetailAction.PTBUDGET_DETAIL_INPUT:
            	insertDetail(maPtBudgetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case MaPtBudgetDetailAction.PTBUDGET_DETAIL_UPDATE:
            	updateDetail(maPtBudgetDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtBudgetDetail");
                break;
        }

        return returnActionForward;
    }

    /**
     * 보전자재분류(마스터) 상세 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPtBudgetDetailForm
     */
    private void findDetail(HttpServletRequest request, MaPtBudgetDetailForm maPtBudgetDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	MaPtBudgetDetailService maPtBudgetDetailService = (MaPtBudgetDetailService)getBean("maPtBudgetDetailService");

        // 넘겨진 부서코드 구함
//        String compNo = maPtBudgetDetailForm.getMaPtBudgetCommonDTO().getCompNo();
//        String partId = maPtBudgetDetailForm.getMaPtBudgetCommonDTO().getPartId();
        
        // 조회된 상세 부분
        MaPtBudgetDetailDTO maPtBudgetDetailDTO = maPtBudgetDetailService.findDetail(maPtBudgetDetailForm.getMaPtBudgetCommonDTO(), getUser(request));

        // 조회된 상세  셋팅한다.
        maPtBudgetDetailForm.setMaPtBudgetDetailDTO(maPtBudgetDetailDTO);
    }
    
    
    
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param maPtBudgetDetailForm
     * @param request
     */
    private void insertDetail(MaPtBudgetDetailForm maPtBudgetDetailForm, HttpServletRequest request) throws Exception
    {
        MaPtBudgetDetailService maPtBudgetDetailService = (MaPtBudgetDetailService) getBean("maPtBudgetDetailService");
        
        MaPtBudgetDetailDTO maPtBudgetDetailDTO = maPtBudgetDetailForm.getMaPtBudgetDetailDTO();
        
        maPtBudgetDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtBudgetDetailService.insertDetail(maPtBudgetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetDetailForm
     * @param request
     */
    private void updateDetail(MaPtBudgetDetailForm maPtBudgetDetailForm, HttpServletRequest request) throws Exception
    {
    	MaPtBudgetDetailService maPtBudgetDetailService = (MaPtBudgetDetailService) getBean("maPtBudgetDetailService");
        
        MaPtBudgetDetailDTO maPtBudgetDetailDTO = maPtBudgetDetailForm.getMaPtBudgetDetailDTO();
        
        maPtBudgetDetailDTO.setCompNo((getUser(request).getCompNo()));
        
        maPtBudgetDetailService.updateDetail(maPtBudgetDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
}