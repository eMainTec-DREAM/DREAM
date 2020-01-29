package dream.work.pm.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.WorkPmListSchdListForm;
import dream.work.pm.list.service.WorkPmListSchdListService;

/**
 * 예방작업 일자 목록
 * @author  kim21017
 * @version $Id: WorkPmListSchdListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmListSchdList" name="workPmListSchdListForm"
 *                input="/dream/work/pm/list/workPmListSchdList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmListSchdList" path="/dream/work/pm/list/workPmListSchdList.jsp"
 *                        redirect="false"
 */
public class WorkPmListSchdListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PM_SCH_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_PM_SCH_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmListSchdListForm workPmListSchdListForm = (WorkPmListSchdListForm) form;
        
        switch (workPmListSchdListForm.getStrutsAction())
        {
        
            case WorkPmListSchdListAction.WORK_PM_SCH_LIST_FIND:
                findSchList(request,response, workPmListSchdListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListSchdListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workPmListSchdListForm.getListId(), workPmListSchdListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmListSchdListAction.WORK_PM_SCH_LIST_DELETE:
            	deleteSchList(request,workPmListSchdListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkPmListSchdListAction.BASE_GRID_EXPORT:
            	findSchList(request,response, workPmListSchdListForm, true);
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
     * @author  kim2107
     * @version $Id: WorkPmListSchdListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListSchdListForm
     * @throws Exception
     */
    private void findSchList(HttpServletRequest request,HttpServletResponse response, WorkPmListSchdListForm workPmListSchdListForm, boolean excelExport) throws Exception
    {
        WorkPmListSchdListService workPmListSchdListService = (WorkPmListSchdListService) getBean("workPmListSchdListService");
        MaPmMstrCommonDTO maPmMstrCommonDTO = workPmListSchdListForm.getMaPmMstrCommonDTO();
        
        //Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(workPmListSchdListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(workPmListSchdListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(workPmListSchdListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(workPmListSchdListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmListSchdListService.findSchList(maPmMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmListSchdListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmListSchdListService.findTotalCount(maPmMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmListSchdListForm.getListId(),workPmListSchdListForm.getCurrentPageId(), workPmListSchdListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkPmListSchdListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmListSchdListForm
     * @throws Exception
     */
    private void deleteSchList(HttpServletRequest request, WorkPmListSchdListForm workPmListSchdListForm) throws Exception
    {
    	WorkPmListSchdListService workPmListSchdListService = (WorkPmListSchdListService) getBean("workPmListSchdListService");
        
    	String[] deleteRows = workPmListSchdListForm.getDeleteRows();
    
    	workPmListSchdListService.deleteSchList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}