package dream.work.rpt.toteng.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.toteng.form.WorkRptTotEngListForm;
import dream.work.rpt.toteng.service.WorkRptTotEngListService;

/**
 * 에너지사용량(집계)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptTotEngList" name="workRptTotEngListForm"
 *                input="/dream/work/rpt/toteng/workRptTotEngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptTotEngList" path="/dream/work/rpt/toteng/workRptTotEngList.jsp"
 *                        redirect="false"
 */
public class WorkRptTotEngListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    public static final int CHART_FIND 			= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptTotEngListForm workRptTotEngListForm = (WorkRptTotEngListForm) form;
        
        switch (workRptTotEngListForm.getStrutsAction())
        {
        
            case WorkRptTotEngListAction.LIST_FIND:
                findList(request,response, workRptTotEngListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptTotEngListAction.CHART_FIND:
            	findChart(request,response, workRptTotEngListForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case WorkRptTotEngListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptTotEngListForm.getListId(), workRptTotEngListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptTotEngListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptTotEngListForm, true);
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
     * @param workRptTotEngListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptTotEngListForm workRptTotEngListForm, boolean excelExport) throws Exception
    {
        WorkRptTotEngListService workRptTotEngListService = (WorkRptTotEngListService) getBean("workRptTotEngListService");
        
        List resultList = workRptTotEngListService.findList(workRptTotEngListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptTotEngListForm.getListId(),workRptTotEngListForm.getCurrentPageId(), workRptTotEngListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptTotEngListForm.getListId());
    }
    
    private void findChart(HttpServletRequest request,HttpServletResponse response, WorkRptTotEngListForm workRptTotEngListForm, boolean excelExport) throws Exception
    {
    	WorkRptTotEngListService workRptTotEngListService = (WorkRptTotEngListService) getBean("workRptTotEngListService");
    	
    	List resultList = workRptTotEngListService.findChart(workRptTotEngListForm, getUser(request));
    	
    	if(excelExport) super.makeGridExport(resultList, request, response,workRptTotEngListForm.getListId(),workRptTotEngListForm.getCurrentPageId(), workRptTotEngListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, workRptTotEngListForm.getListId());
    }
    
}