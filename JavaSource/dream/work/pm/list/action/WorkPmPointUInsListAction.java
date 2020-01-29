package dream.work.pm.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.form.WorkPmPointUInsListForm;
import dream.work.pm.list.service.WorkPmPointUInsListService;

/**
 * 사용량 항목 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workPmPointUInsList" name="workPmPointUInsListForm"
 *                input="/dream/work/pm/list/ins/workPmPointUInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmPointUInsList" path="/dream/work/pm/list/ins/workPmPointUInsList.jsp"
 *                        redirect="false"
 */

public class WorkPmPointUInsListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmPointUInsListForm workPmPointUInsListForm = (WorkPmPointUInsListForm) form;
        
        switch (workPmPointUInsListForm.getStrutsAction())
        {
            case WorkPmPointUInsListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmPointUInsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmPointUInsListAction.LIST_FIND:
                findList(request, response, workPmPointUInsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmPointUInsListAction.LIST_DELETE:
            	deleteList(request, workPmPointUInsListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmPointUInsListAction.BASE_GRID_EXPORT:
            	findList(request, response, workPmPointUInsListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmPointUInsList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmPointUInsListForm workPmPointUInsListForm) throws IOException
    {
        super.setHeader(request, response, workPmPointUInsListForm.getListId(), workPmPointUInsListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmPointUInsListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmPointUInsListForm workPmPointUInsListForm, boolean excelExport) throws Exception
    {
    	WorkPmPointUInsListService workPmPointUInsListService = (WorkPmPointUInsListService) getBean("workPmPointUInsListService");
    	MaPmMstrCommonDTO maPmMstrCommonDTO = workPmPointUInsListForm.getMaPmMstrCommonDTO();

    	//Paging
        maPmMstrCommonDTO.setIsLoadMaxCount("Y".equals(workPmPointUInsListForm.getIsLoadMaxCount())?true:false);
        maPmMstrCommonDTO.setFirstRow(workPmPointUInsListForm.getFirstRow());
        maPmMstrCommonDTO.setOrderBy(workPmPointUInsListForm.getOrderBy());
        maPmMstrCommonDTO.setDirection(workPmPointUInsListForm.getDirection());
        
    	User user = getUser(request);
    	
        List resultList = workPmPointUInsListService.findList(maPmMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workPmPointUInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmPointUInsListService.findTotalCount(maPmMstrCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workPmPointUInsListForm.getListId(),workPmPointUInsListForm.getCurrentPageId(), workPmPointUInsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmPointUInsListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmPointUInsListForm workPmPointUInsListForm) throws Exception
    {
    	WorkPmPointUInsListService workPmPointUInsListService = (WorkPmPointUInsListService) getBean("workPmPointUInsListService");
        String[] deleteRows = workPmPointUInsListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        workPmPointUInsListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
