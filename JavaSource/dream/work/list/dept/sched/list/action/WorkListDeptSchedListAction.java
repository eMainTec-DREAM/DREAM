package dream.work.list.dept.sched.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.form.WorkListDeptSchedListForm;
import dream.work.list.dept.sched.list.service.WorkListDeptSchedListService;

/**
 * 업체별 작업스케줄
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workListDeptSchedList" name="workListDeptSchedListForm"
 *                input="/dream/work/list/dept/sched/list/workListDeptSchedList.jsp" scope="request"
 *                validate="false"
 */
public class WorkListDeptSchedListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListDeptSchedListForm workListDeptSchedListForm = (WorkListDeptSchedListForm) form;
        
        switch (workListDeptSchedListForm.getStrutsAction())
        {
        
            case WorkListDeptSchedListAction.LIST_FIND:
                findList(request,response, workListDeptSchedListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListDeptSchedListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListDeptSchedListForm.getListId(), workListDeptSchedListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListDeptSchedListAction.BASE_GRID_EXPORT:
            	findList(request,response, workListDeptSchedListForm, true);
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
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workListDeptSchedListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkListDeptSchedListForm workListDeptSchedListForm, boolean excelExport) throws Exception
    {
        WorkListDeptSchedListService workListDeptSchedListService = (WorkListDeptSchedListService) getBean("workListDeptSchedListService");
        
        WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO = workListDeptSchedListForm.getWorkListDeptSchedCommonDTO();
        
        //Paging
        workListDeptSchedCommonDTO.setIsLoadMaxCount("Y".equals(workListDeptSchedListForm.getIsLoadMaxCount())?true:false);
        workListDeptSchedCommonDTO.setFirstRow(workListDeptSchedListForm.getFirstRow());
        workListDeptSchedCommonDTO.setOrderBy(workListDeptSchedListForm.getOrderBy());
        workListDeptSchedCommonDTO.setDirection(workListDeptSchedListForm.getDirection());
        
        List resultList = workListDeptSchedListService.findList(workListDeptSchedCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListDeptSchedListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListDeptSchedListService.findTotalCount(workListDeptSchedCommonDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workListDeptSchedListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}