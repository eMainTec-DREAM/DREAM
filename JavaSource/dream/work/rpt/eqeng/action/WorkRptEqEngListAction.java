package dream.work.rpt.eqeng.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.eqeng.form.WorkRptEqEngListForm;
import dream.work.rpt.eqeng.service.WorkRptEqEngListService;

/**
 * 에너지사용량(설비별)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptEqEngList" name="workRptEqEngListForm"
 *                input="/dream/work/rpt/eqeng/workRptEqEngList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptEqEngList" path="/dream/work/rpt/eqeng/workRptEqEngList.jsp"
 *                        redirect="false"
 */
public class WorkRptEqEngListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptEqEngListForm workRptEqEngListForm = (WorkRptEqEngListForm) form;
        
        switch (workRptEqEngListForm.getStrutsAction())
        {
        
            case WorkRptEqEngListAction.LIST_FIND:
                findList(request,response, workRptEqEngListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptEqEngListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptEqEngListForm.getListId(), workRptEqEngListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptEqEngListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptEqEngListForm, true);
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
     * @param workRptEqEngListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptEqEngListForm workRptEqEngListForm, boolean excelExport) throws Exception
    {
        WorkRptEqEngListService workRptEqEngListService = (WorkRptEqEngListService) getBean("workRptEqEngListService");
        
        List resultList = workRptEqEngListService.findList(workRptEqEngListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptEqEngListForm.getListId(),workRptEqEngListForm.getCurrentPageId(), workRptEqEngListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, workRptEqEngListForm.getListId());
    }
    
}