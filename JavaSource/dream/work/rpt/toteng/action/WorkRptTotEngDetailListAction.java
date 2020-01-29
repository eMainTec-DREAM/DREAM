package dream.work.rpt.toteng.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.toteng.form.WorkRptTotEngDetailListForm;
import dream.work.rpt.toteng.service.WorkRptTotEngDetailListService;

/**
 * 에너지사용량(집계) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptTotEngLocDetailList" name="workRptTotEngDetailListForm"
 *                input="/dream/work/rpt/toteng/workRptTotEngLocDetailList.jsp" scope="request"
 *                validate="false"               
 * @struts:action path="/workRptTotEngUsageDeptDetailList" name="workRptTotEngDetailListForm"
 *                input="/dream/work/rpt/toteng/workRptTotEngUsageDeptDetailList.jsp" scope="request"
 *                validate="false"               
 * @struts:action path="/workRptTotEngCtgDetailList" name="workRptTotEngDetailListForm"
 *                input="/dream/work/rpt/toteng/workRptTotEngCtgDetailList.jsp" scope="request"
 *                validate="false"               
 * @struts.action-forward name="workRptTotEngLocDetailList" path="/dream/work/rpt/toteng/workRptTotEngLocDetailList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="workRptTotEngUsageDeptDetailList" path="/dream/work/rpt/toteng/workRptTotEngUsageDeptDetailList.jsp"
 *                        redirect="false"
 * @struts.action-forward name="workRptTotEngCtgDetailList" path="/dream/work/rpt/toteng/workRptTotEngCtgDetailList.jsp"
 *                        redirect="false"
 */
public class WorkRptTotEngDetailListAction extends AuthAction
{
    public static final int DETAIL_LIST_FIND 	= 1001;
    public static final int DETAIL_CHART_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;	
        WorkRptTotEngDetailListForm workRptTotEngDetailListForm = (WorkRptTotEngDetailListForm) form;
        switch (workRptTotEngDetailListForm.getStrutsAction())
        {
            case WorkRptTotEngDetailListAction.DETAIL_LIST_FIND:
                // 페이지 조회
                this.findDetailList(request, response, workRptTotEngDetailListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptTotEngDetailListAction.DETAIL_CHART_FIND:
            	// 페이지 조회
            	this.findDetailChart(request, response, workRptTotEngDetailListForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case WorkRptTotEngDetailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptTotEngDetailListForm.getListId(), workRptTotEngDetailListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptTotEngDetailListAction.BASE_GRID_EXPORT:
                findDetailList(request,response, workRptTotEngDetailListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workRptTotEngDetailListForm
     */
    private void findDetailList(HttpServletRequest request,HttpServletResponse response, WorkRptTotEngDetailListForm workRptTotEngDetailListForm, boolean excelExport) throws Exception
    {
        WorkRptTotEngDetailListService workRptTotEngDetailListService = (WorkRptTotEngDetailListService) getBean("workRptTotEngDetailListService");
        
        List resultList = workRptTotEngDetailListService.findDetailList(workRptTotEngDetailListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptTotEngDetailListForm.getListId(),workRptTotEngDetailListForm.getCurrentPageId(), workRptTotEngDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptTotEngDetailListForm.getListId());
    }
    
    private void findDetailChart(HttpServletRequest request,HttpServletResponse response, WorkRptTotEngDetailListForm workRptTotEngDetailListForm, boolean excelExport) throws Exception
    {
    	WorkRptTotEngDetailListService workRptTotEngDetailListService = (WorkRptTotEngDetailListService) getBean("workRptTotEngDetailListService");
    	
    	List resultList = workRptTotEngDetailListService.findDetailChart(workRptTotEngDetailListForm, getUser(request));
    	
    	if(excelExport) super.makeGridExport(resultList, request, response,workRptTotEngDetailListForm.getListId(),workRptTotEngDetailListForm.getCurrentPageId(), workRptTotEngDetailListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, workRptTotEngDetailListForm.getListId());
    }
    
}