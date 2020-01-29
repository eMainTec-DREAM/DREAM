package dream.work.rpt.dailyeng.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngDetailListForm;
import dream.work.rpt.dailyeng.service.WorkRptDailyEngDetailListService;

/**
 * 에너지사용량(일별) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptDailyEngDetailList" name="workRptDailyEngDetailListForm"
 *                input="/dream/work/rpt/dailyeng/workRptDailyEngDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptDailyEngDetailChart" name="workRptDailyEngDetailListForm"
 *                input="/dream/work/rpt/dailyeng/workRptDailyEngDetailChart.jsp" scope="request"
 *                validate="false"               
 * @struts.action-forward name="workRptDailyEngDetailList" path="/dream/work/rpt/dailyeng/workRptDailyEngDetailList.jsp"
 *                        redirect="false"
 */
public class WorkRptDailyEngDetailListAction extends AuthAction
{
    public static final int DETAIL_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm = (WorkRptDailyEngDetailListForm) form;
        switch (workRptDailyEngDetailListForm.getStrutsAction())
        {
            case WorkRptDailyEngDetailListAction.DETAIL_LIST_FIND:
                // 페이지 조회
                this.findDetailList(request, response, workRptDailyEngDetailListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptDailyEngDetailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptDailyEngDetailListForm.getListId(), workRptDailyEngDetailListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptDailyEngDetailListAction.BASE_GRID_EXPORT:
                findDetailList(request,response, workRptDailyEngDetailListForm, true);
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
     * @param workRptDailyEngDetailListForm
     */
    private void findDetailList(HttpServletRequest request,HttpServletResponse response, WorkRptDailyEngDetailListForm workRptDailyEngDetailListForm, boolean excelExport) throws Exception
    {
        WorkRptDailyEngDetailListService workRptDailyEngDetailListService = (WorkRptDailyEngDetailListService) getBean("workRptDailyEngDetailListService");
        
        List resultList = workRptDailyEngDetailListService.findDetailList(workRptDailyEngDetailListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptDailyEngDetailListForm.getListId(),workRptDailyEngDetailListForm.getCurrentPageId(), workRptDailyEngDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptDailyEngDetailListForm.getListId());
    }
    
}