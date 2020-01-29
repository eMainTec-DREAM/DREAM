package dream.work.list.dept.sched.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;
import dream.work.list.dept.sched.list.form.WorkListDeptSchedListDeptListForm;
import dream.work.list.dept.sched.list.service.WorkListDeptSchedListDeptListService;

/**
 * 업체별 작업스케줄탭부서별 작업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workListDeptSchedListDeptList" name="workListDeptSchedListDeptListForm"
 *                input="/dream/work/list/dept/sched/list/workListDeptSchedListDeptList.jsp" scope="request"
 *                validate="false"
 */
public class WorkListDeptSchedListDeptListAction extends AuthAction
{
    public static final int LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListDeptSchedListDeptListForm workListDeptSchedListDeptListForm = (WorkListDeptSchedListDeptListForm) form;
        switch (workListDeptSchedListDeptListForm.getStrutsAction())
        {
            case WorkListDeptSchedListDeptListAction.LIST_FIND:
                // 페이지 조회
                this.findList(request, workListDeptSchedListDeptListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListDeptSchedListDeptListAction.BASE_SET_HEADER:
                super.setHeader(request, response, workListDeptSchedListDeptListForm.getListId(), workListDeptSchedListDeptListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListDeptSchedListDeptListAction.BASE_GRID_EXPORT:
            	findList(request, workListDeptSchedListDeptListForm, response, true);
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
     * @author js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workListDeptSchedListDeptListForm
     */
    private void findList(HttpServletRequest request, WorkListDeptSchedListDeptListForm workListDeptSchedListDeptListForm, HttpServletResponse response, boolean excelExport) throws Exception
    {
        WorkListDeptSchedListDeptListService workListDeptSchedListDeptListService = (WorkListDeptSchedListDeptListService) getBean("workListDeptSchedListDeptListService");
        WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO = workListDeptSchedListDeptListForm.getWorkListDeptSchedCommonDTO();
        WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO = workListDeptSchedListDeptListForm.getWorkListDeptSchedListDeptListDTO();
        
        //Paging
        workListDeptSchedListDeptListDTO.setIsLoadMaxCount("Y".equals(workListDeptSchedListDeptListForm.getIsLoadMaxCount())?true:false);
        workListDeptSchedListDeptListDTO.setFirstRow(workListDeptSchedListDeptListForm.getFirstRow());
        workListDeptSchedListDeptListDTO.setOrderBy(workListDeptSchedListDeptListForm.getOrderBy());
        workListDeptSchedListDeptListDTO.setDirection(workListDeptSchedListDeptListForm.getDirection());
        
        List resultList = workListDeptSchedListDeptListService.findList(workListDeptSchedCommonDTO, workListDeptSchedListDeptListDTO, getUser(request));
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListDeptSchedListDeptListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListDeptSchedListDeptListService.findTotalCount(workListDeptSchedCommonDTO, workListDeptSchedListDeptListDTO, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListDeptSchedListDeptListForm.getListId(),workListDeptSchedListDeptListForm.getCurrentPageId(), workListDeptSchedListDeptListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    
        
        
    }
    
}