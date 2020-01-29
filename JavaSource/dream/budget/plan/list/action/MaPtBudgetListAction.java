package dream.budget.plan.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.budget.plan.list.dto.MaPtBudgetCommonDTO;
import dream.budget.plan.list.form.MaPtBudgetListForm;
import dream.budget.plan.list.service.MaPtBudgetListService;

/**
 * 예산관리 - 목록 action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/maPtBudgetList" name="maPtBudgetListForm"
 *                input="/dream/budget/plan/list/maPtBudgetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtBudgetList" path="/dream/budget/plan/list/maPtBudgetList.jsp"
 *                        redirect="false"
 */
public class MaPtBudgetListAction extends AuthAction
{
    /** 조회 */
    public static final int PTBUDGET_LIST_FIND      = 1001;
    /** 삭제 */
    public static final int PTBUDGET_LIST_DELETE    = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtBudgetListForm maPtBudgetListForm = (MaPtBudgetListForm) form;
        
        switch (maPtBudgetListForm.getStrutsAction())
        {
            case MaPtBudgetListAction.BASE_SET_HEADER:
                setHeader(request, response, maPtBudgetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtBudgetListAction.PTBUDGET_LIST_FIND:
                findList(request, response, maPtBudgetListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MaPtBudgetListAction.PTBUDGET_LIST_DELETE:
            	deleteList(request, maPtBudgetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            default:
                returnActionForward = mapping.findForward("maPtBudgetList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MaPtBudgetListForm maPtBudgetListForm) throws IOException
    {
        super.setHeader(request, response, maPtBudgetListForm.getListId(), maPtBudgetListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param maPtBudgetListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MaPtBudgetListForm maPtBudgetListForm, boolean excelExport) throws IOException
    {
    	MaPtBudgetListService maPtBudgetListService = (MaPtBudgetListService) getBean("maPtBudgetListService");        

    	MaPtBudgetCommonDTO maPtBudgetCommonDTO = maPtBudgetListForm.getMaPtBudgetCommonDTO();

    	// 로긴 comp_no 를 셋팅한다.
//    	maPtBudgetCommonDTO.setFilterCompNo((getUser(request).getCompNo()));

    	//Paging
    	maPtBudgetCommonDTO.setIsLoadMaxCount("Y".equals(maPtBudgetListForm.getIsLoadMaxCount())?true:false);
    	maPtBudgetCommonDTO.setFirstRow(maPtBudgetListForm.getFirstRow());
    	maPtBudgetCommonDTO.setOrderBy(maPtBudgetListForm.getOrderBy());
    	maPtBudgetCommonDTO.setDirection(maPtBudgetListForm.getDirection());

        //리스트를 조회한다.
        List resultList = maPtBudgetListService.findList(maPtBudgetCommonDTO, getUser(request));
    	        
    	//Paging
    	String totalCount = "";    	        
    	if(Integer.parseInt(maPtBudgetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = maPtBudgetListService.findTotalCount(maPtBudgetCommonDTO,getUser(request));
    	        
    	if(excelExport) CommonUtil.makeGridExport(resultList, request, response, maPtBudgetListForm);
    	// 조회한 List 를 form에 셋팅한다.
    	else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maPtBudgetListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, MaPtBudgetListForm maPtBudgetListForm) throws Exception
    {
    	MaPtBudgetListService maPtBudgetListService = (MaPtBudgetListService) getBean("maPtBudgetListService");        

        String[] deleteRows = maPtBudgetListForm.getDeleteRows();    // sheet 내역
        
        maPtBudgetListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
}
