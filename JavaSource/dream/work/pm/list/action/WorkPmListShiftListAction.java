package dream.work.pm.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.WorkPmListShiftListForm;
import dream.work.pm.list.service.WorkPmListShiftListService;

/**
 * 교대조 목록
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workPmListPtrlShiftList" name="workPmListShiftListForm"
 *                input="/dream/work/pm/list/workPmListPtrlShiftList.jsp" scope="request"
 *                validate="false"
 */
public class WorkPmListShiftListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PM_SHIFT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_PM_SHIFT_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListShiftListForm workPmListShiftListForm = (WorkPmListShiftListForm) form;
        
        switch (workPmListShiftListForm.getStrutsAction())
        {
        
            case WorkPmListShiftListAction.WORK_PM_SHIFT_LIST_FIND:
                findShiftList(request,response, workPmListShiftListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListShiftListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workPmListShiftListForm.getListId(), workPmListShiftListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListShiftListAction.WORK_PM_SHIFT_LIST_DELETE:
            	deleteShiftList(request,workPmListShiftListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListShiftListAction.BASE_GRID_EXPORT:
            	findShiftList(request,response, workPmListShiftListForm, true);
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
     * @param workPmListShiftListForm
     * @throws Exception
     */
    private void findShiftList(HttpServletRequest request,HttpServletResponse response, WorkPmListShiftListForm workPmListShiftListForm, boolean excelExport) throws Exception
    {
        WorkPmListShiftListService workPmListShiftListService = (WorkPmListShiftListService) getBean("workPmListShiftListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListShiftListForm.getMaPmMstrCommonDTO();
        
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(workPmListShiftListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(workPmListShiftListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(workPmListShiftListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(workPmListShiftListForm.getDirection());
        
        List resultList = workPmListShiftListService.findShiftList(maPmMstrCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmListShiftListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmListShiftListService.findTotalCount(maPmMstrCommonDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workPmListShiftListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workPmListShiftListForm
     * @throws Exception
     */
    private void deleteShiftList(HttpServletRequest request, WorkPmListShiftListForm workPmListShiftListForm) throws Exception
    {
    	WorkPmListShiftListService workPmListShiftListService = (WorkPmListShiftListService) getBean("workPmListShiftListService");
        
    	String[] deleteRows = workPmListShiftListForm.getDeleteRows();
    
    	workPmListShiftListService.deleteShiftList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}