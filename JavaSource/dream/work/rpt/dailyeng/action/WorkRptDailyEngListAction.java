package dream.work.rpt.dailyeng.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.dailyeng.form.WorkRptDailyEngListForm;
import dream.work.rpt.dailyeng.service.WorkRptDailyEngListService;

/**
 * 에너지사용량(일별)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptDailyEngList" name="workRptDailyEngListForm"
 *                input="/dream/work/rpt/dailyeng/workRptDailyEngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptDailyEngList" path="/dream/work/rpt/dailyeng/workRptDailyEngList.jsp"
 *                        redirect="false"
 */
public class WorkRptDailyEngListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptDailyEngListForm workRptDailyEngListForm = (WorkRptDailyEngListForm) form;
        
        switch (workRptDailyEngListForm.getStrutsAction())
        {
        
            case WorkRptDailyEngListAction.LIST_FIND:
                findList(request,response, workRptDailyEngListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptDailyEngListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptDailyEngListForm.getListId(), workRptDailyEngListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptDailyEngListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptDailyEngListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workRptDailyEngListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptDailyEngListForm workRptDailyEngListForm, boolean excelExport) throws Exception
    {
        WorkRptDailyEngListService workRptDailyEngListService = (WorkRptDailyEngListService) getBean("workRptDailyEngListService");
        
        List resultList = workRptDailyEngListService.findList(workRptDailyEngListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptDailyEngListForm.getListId(),workRptDailyEngListForm.getCurrentPageId(), workRptDailyEngListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptDailyEngListForm.getListId());
    }
    
}