package dream.work.rpt.eqeng.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.eqeng.form.WorkRptEqEngDetailListForm;
import dream.work.rpt.eqeng.service.WorkRptEqEngDetailListService;

/**
 * 에너지사용량(설비별) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptEqEngDetailChart" name="workRptEqEngDetailListForm"
 *                input="/dream/work/rpt/eqeng/workRptEqEngDetailChart.jsp" scope="request"
 *                validate="false"               
 * @struts.action-forward name="workRptEqEngDetailChart" path="/dream/work/rpt/eqeng/workRptEqEngDetailChart.jsp"
 *                        redirect="false"
 */
public class WorkRptEqEngDetailListAction extends AuthAction
{
    public static final int DETAIL_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;	
        WorkRptEqEngDetailListForm workRptEqEngDetailListForm = (WorkRptEqEngDetailListForm) form;
        switch (workRptEqEngDetailListForm.getStrutsAction())
        {
            case WorkRptEqEngDetailListAction.DETAIL_LIST_FIND:
                // 페이지 조회
                this.findDetailList(request, response, workRptEqEngDetailListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptEqEngDetailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptEqEngDetailListForm.getListId(), workRptEqEngDetailListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptEqEngDetailListAction.BASE_GRID_EXPORT:
                findDetailList(request,response, workRptEqEngDetailListForm, true);
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
     * @param workRptEqEngDetailListForm
     */
    private void findDetailList(HttpServletRequest request,HttpServletResponse response, WorkRptEqEngDetailListForm workRptEqEngDetailListForm, boolean excelExport) throws Exception
    {
        WorkRptEqEngDetailListService workRptEqEngDetailListService = (WorkRptEqEngDetailListService) getBean("workRptEqEngDetailListService");
        
        List resultList = workRptEqEngDetailListService.findDetailList(workRptEqEngDetailListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptEqEngDetailListForm.getListId(),workRptEqEngDetailListForm.getCurrentPageId(), workRptEqEngDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptEqEngDetailListForm.getListId());
    }
    
}