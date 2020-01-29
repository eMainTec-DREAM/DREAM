package dream.budget.account.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.budget.account.dto.BudgetAccountCommonDTO;
import dream.budget.account.form.BudgetAccountListForm;
import dream.budget.account.service.BudgetAccountListService;

/**
 * 예산계정 - 목록 action
 * @author  ghlee
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/budgetAccountList" name="budgetAccountListForm"
 *                input="/dream/budget/account/budgetAccountList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="budgetAccountList" path="/dream/budget/account/budgetAccountList.jsp"
 *                        redirect="false"
 */
public class BudgetAccountListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND     = 1001;
    /** 삭제 */
    public static final int LIST_DELETE   = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        BudgetAccountListForm budgetAccountListForm = (BudgetAccountListForm) form;
        
        switch (budgetAccountListForm.getStrutsAction())
        {
            case BudgetAccountListAction.BASE_SET_HEADER:
                setHeader(request, response, budgetAccountListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case BudgetAccountListAction.LIST_FIND:
                findList(request, response, budgetAccountListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case BudgetAccountListAction.LIST_DELETE:
            	deleteList(request, budgetAccountListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case BudgetAccountListAction.BASE_GRID_EXPORT:
            	findList(request, response, budgetAccountListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, BudgetAccountListForm budgetAccountListForm) throws IOException
    {
        super.setHeader(request, response, budgetAccountListForm.getListId(), budgetAccountListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param budgetAccountListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, BudgetAccountListForm budgetAccountListForm, boolean excelExport) throws Exception
    {
    	BudgetAccountListService budgetAccountListService = (BudgetAccountListService) getBean("budgetAccountListService");        

    	BudgetAccountCommonDTO budgetAccountCommonDTO = budgetAccountListForm.getBudgetAccountCommonDTO();
        
    	//Paging
    	budgetAccountCommonDTO.setIsLoadMaxCount("Y".equals(budgetAccountListForm.getIsLoadMaxCount())?true:false);
    	budgetAccountCommonDTO.setFirstRow(budgetAccountListForm.getFirstRow());
        budgetAccountCommonDTO.setOrderBy(budgetAccountListForm.getOrderBy());
        budgetAccountCommonDTO.setDirection(budgetAccountListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = budgetAccountListService.findList(budgetAccountCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";

        if(Integer.parseInt(budgetAccountListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = budgetAccountListService.findTotalCount(budgetAccountCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,budgetAccountListForm.getListId(),budgetAccountListForm.getCurrentPageId(), budgetAccountListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param budgetAccountListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, BudgetAccountListForm budgetAccountListForm) throws Exception
    {
    	BudgetAccountListService budgetAccountListService = (BudgetAccountListService) getBean("budgetAccountListService");        

        String[] deleteRows = budgetAccountListForm.getDeleteRows();    // sheet 내역
        
        budgetAccountListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
