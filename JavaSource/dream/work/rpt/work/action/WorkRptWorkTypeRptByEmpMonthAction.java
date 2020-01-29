package dream.work.rpt.work.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpMonthDTO;
import dream.work.rpt.work.form.WorkRptWorkTypeRptByEmpMonthForm;
import dream.work.rpt.work.service.WorkRptWorkTypeRptByEmpMonthService;

/**
 * 담당자별작업현황 - 담당자 월별작업현황 탭 목록 Action
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptWorkTypeRptByEmpMonth" name="workRptWorkTypeRptByEmpMonthForm"
 *                input="/dream/work/rpt/work/workRptWorkTypeRptByEmpMonth.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptWorkTypeRptByEmpMonthAction extends AuthAction 
{
    public static final int MONTH_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWorkTypeRptByEmpMonthForm workRptWorkTypeRptByEmpMonthForm = (WorkRptWorkTypeRptByEmpMonthForm) form;
        switch (workRptWorkTypeRptByEmpMonthForm.getStrutsAction())
        {
            case WorkRptWorkTypeRptByEmpMonthAction.MONTH_LIST_FIND:
                // 페이지 조회
                this.findMonthList(request, response, workRptWorkTypeRptByEmpMonthForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWorkTypeRptByEmpMonthAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptWorkTypeRptByEmpMonthForm.getListId(), workRptWorkTypeRptByEmpMonthForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWorkTypeRptByEmpMonthAction.BASE_GRID_EXPORT:
            	findMonthList(request,response, workRptWorkTypeRptByEmpMonthForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 담당자 월별작업현황 조회
     * @author js.lee
     * @since   1.0
     *
     * @param request
     * @param response
     * @param workRptWorkTypeRptByEmpMonthForm
     * @param excelExport
     * @throws Exception
     */
    private void findMonthList(HttpServletRequest request,HttpServletResponse response, WorkRptWorkTypeRptByEmpMonthForm workRptWorkTypeRptByEmpMonthForm, boolean excelExport) throws Exception
    {
        WorkRptWorkTypeRptByEmpMonthService workRptWorkTypeRptByEmpMonthService = (WorkRptWorkTypeRptByEmpMonthService) getBean("workRptWorkTypeRptByEmpMonthService");
        
        WorkRptWorkTypeRptByEmpMonthDTO workRptWorkTypeRptByEmpMonthDTO = workRptWorkTypeRptByEmpMonthForm.getWorkRptWorkTypeRptByEmpMonthDTO();
        
        //Paging
        workRptWorkTypeRptByEmpMonthDTO.setIsLoadMaxCount("Y".equals(workRptWorkTypeRptByEmpMonthForm.getIsLoadMaxCount())?true:false);
        workRptWorkTypeRptByEmpMonthDTO.setFirstRow(workRptWorkTypeRptByEmpMonthForm.getFirstRow());
        workRptWorkTypeRptByEmpMonthDTO.setOrderBy(workRptWorkTypeRptByEmpMonthForm.getOrderBy());
        workRptWorkTypeRptByEmpMonthDTO.setDirection(workRptWorkTypeRptByEmpMonthForm.getDirection());
        
        List resultList = workRptWorkTypeRptByEmpMonthService.findMonthList(workRptWorkTypeRptByEmpMonthDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWorkTypeRptByEmpMonthForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWorkTypeRptByEmpMonthService.findTotalCount(workRptWorkTypeRptByEmpMonthDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptWorkTypeRptByEmpMonthForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}