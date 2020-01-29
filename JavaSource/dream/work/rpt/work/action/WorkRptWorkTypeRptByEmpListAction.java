package dream.work.rpt.work.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpCommonDTO;
import dream.work.rpt.work.form.WorkRptWorkTypeRptByEmpListForm;
import dream.work.rpt.work.service.WorkRptWorkTypeRptByEmpListService;

/**
 * 담당자별작업현황
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptWorkTypeRptByEmpList" name="workRptWorkTypeRptByEmpListForm"
 *                input="/dream/work/rpt/work/workRptWorkTypeRptByEmpList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptWorkTypeRptByEmpListAction extends AuthAction 
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWorkTypeRptByEmpListForm workRptWorkTypeRptByEmpListForm = (WorkRptWorkTypeRptByEmpListForm) form;
        
        switch (workRptWorkTypeRptByEmpListForm.getStrutsAction())
        {
        
            case WorkRptWorkTypeRptByEmpListAction.LIST_FIND:
                findList(request,response, workRptWorkTypeRptByEmpListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWorkTypeRptByEmpListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptWorkTypeRptByEmpListForm.getListId(), workRptWorkTypeRptByEmpListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWorkTypeRptByEmpListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptWorkTypeRptByEmpListForm, true);
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
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptWorkTypeRptByEmpListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptWorkTypeRptByEmpListForm workRptWorkTypeRptByEmpListForm, boolean excelExport) throws Exception
    {
        WorkRptWorkTypeRptByEmpListService workRptWorkTypeRptByEmpListService = (WorkRptWorkTypeRptByEmpListService) getBean("workRptWorkTypeRptByEmpListService");
        
        WorkRptWorkTypeRptByEmpCommonDTO workRptWorkTypeRptByEmpCommonDTO = workRptWorkTypeRptByEmpListForm.getWorkRptWorkTypeRptByEmpCommonDTO();
        
        //Paging
        workRptWorkTypeRptByEmpCommonDTO.setIsLoadMaxCount("Y".equals(workRptWorkTypeRptByEmpListForm.getIsLoadMaxCount())?true:false);
        workRptWorkTypeRptByEmpCommonDTO.setFirstRow(workRptWorkTypeRptByEmpListForm.getFirstRow());
        workRptWorkTypeRptByEmpCommonDTO.setOrderBy(workRptWorkTypeRptByEmpListForm.getOrderBy());
        workRptWorkTypeRptByEmpCommonDTO.setDirection(workRptWorkTypeRptByEmpListForm.getDirection());
        
        List resultList = workRptWorkTypeRptByEmpListService.findList(workRptWorkTypeRptByEmpCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWorkTypeRptByEmpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWorkTypeRptByEmpListService.findTotalCount(workRptWorkTypeRptByEmpCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptWorkTypeRptByEmpListForm.getListId(),workRptWorkTypeRptByEmpListForm.getCurrentPageId(), workRptWorkTypeRptByEmpListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}