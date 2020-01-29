package dream.budget.plan.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.budget.plan.list.form.MaPtDeptBgListForm;
import dream.budget.plan.list.service.MaPtDeptBgListService;

/**
 * 부품거래처 목록
 * @author  ssong
 * @version $Id$
 * @since   1.0
 * @struts:action path="/maPtDeptBgList" name="maPtDeptBgListForm"
 *                input="/dream/budget/plan/list/maPtDeptBgList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maPtDeptBgList" path="/dream/budget/plan/list/maPtDeptBgList.jsp"
 *                        redirect="false"
 */
public class MaPtDeptBgListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PTDEPT_BUDGET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int PTDEPT_BUDGET_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaPtDeptBgListForm maPtDeptBgListForm = (MaPtDeptBgListForm) form;
        
        switch (maPtDeptBgListForm.getStrutsAction())
        {
        
            case MaPtDeptBgListAction.PTDEPT_BUDGET_LIST_FIND:
                findList(request,response, maPtDeptBgListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtDeptBgListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, maPtDeptBgListForm.getListId(), maPtDeptBgListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MaPtDeptBgListAction.PTDEPT_BUDGET_LIST_DELETE:
            	deleteList(request,maPtDeptBgListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            default:
                returnActionForward = mapping.findForward("maPtDeptBgList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtDeptBgListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, MaPtDeptBgListForm maPtDeptBgListForm) throws Exception
    {
        MaPtDeptBgListService maPtDeptBgListService = (MaPtDeptBgListService) getBean("maPtDeptBgListService");

        List resultList = maPtDeptBgListService.findList(maPtDeptBgListForm.getMaPtBudgetCommonDTO(), getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, maPtDeptBgListForm.getListId());
        //super.makeJsonResult(resultList, request, response, maPtDeptBgListForm.getListId());
    }
    
    /**
     * delete
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param maPtDeptBgListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, MaPtDeptBgListForm maPtDeptBgListForm) throws Exception
    {
    	MaPtDeptBgListService maPtDeptBgListService = (MaPtDeptBgListService) getBean("maPtDeptBgListService");

    	String[] deleteRows = maPtDeptBgListForm.getDeleteRows();
    
    	maPtDeptBgListService.deleteList(deleteRows, getUser(request));
    	
    	setAjaxStatus(request);
    }
}