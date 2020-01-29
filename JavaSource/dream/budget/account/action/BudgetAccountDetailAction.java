package dream.budget.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.dto.BudgetAccountDetailDTO;
import dream.budget.account.form.BudgetAccountDetailForm;
import dream.budget.account.service.BudgetAccountDetailService;

/**
 * 예산계정 - 상세 action
 * 
 * @author ghlee
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/budgetAccountDetail" name="budgetAccountDetailForm"
 *                input="/dream/budget/account/budgetAccountDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="budgetAccountDetail" path="/dream/budget/account/budgetAccountDetail.jsp"
 *                        redirect="false"
 */
public class BudgetAccountDetailAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int DETAIL_INIT 		= 8001;
    /** 저장 */
    public static final int DETAIL_INPUT 		= 5002;
    /** 수정 */
    public static final int DETAIL_UPDATE 	    = 6003;
    /** 중복 체크 */
    public static final int DETAIL_CHECK 		= 1004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        BudgetAccountDetailForm budgetAccountDetailForm = (BudgetAccountDetailForm) form;
        
        switch (budgetAccountDetailForm.getStrutsAction())
        {
            case BudgetAccountDetailAction.DETAIL_INIT:
                // 페이지 조회
                this.findDetail(request, budgetAccountDetailForm);
                returnActionForward = mapping.findForward("budgetAccountDetail");
                break;
            case BudgetAccountDetailAction.DETAIL_INPUT:
            	insertDetail(budgetAccountDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case BudgetAccountDetailAction.DETAIL_UPDATE:
            	updateDetail(budgetAccountDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case BudgetAccountDetailAction.DETAIL_CHECK:
                validAccountNo(budgetAccountDetailForm, request);
                returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("budgetAccountDetail");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 상세 조회
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param budgetAccountDetailForm
     */
    private void findDetail(HttpServletRequest request, BudgetAccountDetailForm budgetAccountDetailForm)throws Exception 
    {   
        // Service 객체 생성
    	BudgetAccountDetailService budgetAccountDetailService = (BudgetAccountDetailService)getBean("budgetAccountDetailService");
    	
        BudgetAccountCommonDTO budgetAccountCommonDTO = budgetAccountDetailForm.getBudgetAccountCommonDTO();
        
        // 조회된 상세 부분
        BudgetAccountDetailDTO budgetAccountDetailDTO = budgetAccountDetailService.findDetail(budgetAccountCommonDTO, getUser(request));
        
        // 조회된 상세  셋팅한다.
        budgetAccountDetailForm.setBudgetAccountDetailDTO(budgetAccountDetailDTO);
    }
    /**
     * detail insert
     * @author  
     * @version $Id:  $
     * @since   1.0
     * 
     * @param budgetAccountDetailForm
     * @param request
     */
    private void insertDetail(BudgetAccountDetailForm budgetAccountDetailForm, HttpServletRequest request) throws Exception
    {
        BudgetAccountDetailService budgetAccountDetailService = (BudgetAccountDetailService) getBean("budgetAccountDetailService");
        
        BudgetAccountDetailDTO budgetAccountDetailDTO = budgetAccountDetailForm.getBudgetAccountDetailDTO();
        
        budgetAccountDetailService.insertDetail(budgetAccountDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * detail update
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param budgetAccountDetailForm
     * @param request
     */
    private void updateDetail(BudgetAccountDetailForm budgetAccountDetailForm, HttpServletRequest request) throws Exception
    {
    	BudgetAccountDetailService budgetAccountDetailService = (BudgetAccountDetailService) getBean("budgetAccountDetailService");
        
        BudgetAccountDetailDTO budgetAccountDetailDTO = budgetAccountDetailForm.getBudgetAccountDetailDTO();
        
        budgetAccountDetailService.updateDetail(budgetAccountDetailDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    /**
     * valid account no
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param budgetAccountDetailForm
     * @param request
     */
    private void validAccountNo(BudgetAccountDetailForm budgetAccountDetailForm, HttpServletRequest request) throws Exception
    {
    	BudgetAccountDetailService budgetAccountDetailService = (BudgetAccountDetailService) getBean("budgetAccountDetailService");
        
        BudgetAccountDetailDTO budgetAccountDetailDTO = budgetAccountDetailForm.getBudgetAccountDetailDTO();
        
        String isValid = budgetAccountDetailService.validAccountNo(budgetAccountDetailDTO, getUser(request));
        
        setAjaxDesc(request, isValid);
    }
    
}