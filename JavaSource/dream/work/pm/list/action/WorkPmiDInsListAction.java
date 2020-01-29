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
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.form.WorkPmiDInsListForm;
import dream.work.pm.list.service.WorkPmiDInsListService;

/**
 * WorkPmiDIns Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiDInsListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiDInsList" name="workPmiDInsListForm"
 *                input="/dream/work/pmi/list/workPmiDInsList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workPmDInsSelect" name="workPmiDInsListForm"
 *                input="/dream/work/pm/list/ins/workPmDInsSelect.jsp" scope="request"
 *                validate="false"
 */
public class WorkPmiDInsListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       	  = 1001;
    /** 삭제 */
    public static final int LIST_DELETE  		  = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiDInsListForm workPmiDInsListForm = (WorkPmiDInsListForm) form;
        
        switch (workPmiDInsListForm.getStrutsAction())
        {
            case WorkPmiDInsListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmiDInsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiDInsListAction.LIST_FIND:
                findList(request, response, workPmiDInsListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmiDInsListAction.LIST_DELETE:
                deleteList(request, workPmiDInsListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;     
            case WorkPmiDInsListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmiDInsListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsListForm workPmiDInsListForm) throws IOException
    {
        super.setHeader(request, response, workPmiDInsListForm.getListId(), workPmiDInsListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmiDInsListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmiDInsListForm workPmiDInsListForm, boolean excelExport) throws Exception
    {
        WorkPmiDInsListService workPmiDInsListService = (WorkPmiDInsListService) getBean("workPmiDInsListService");
        WorkPmiDInsCommonDTO workPmiDInsCommonDTO = workPmiDInsListForm.getWorkPmiDInsCommonDTO();

        //Paging
        workPmiDInsCommonDTO.setIsLoadMaxCount("Y".equals(workPmiDInsListForm.getIsLoadMaxCount())?true:false);
        workPmiDInsCommonDTO.setFirstRow(workPmiDInsListForm.getFirstRow());
        workPmiDInsCommonDTO.setOrderBy(workPmiDInsListForm.getOrderBy());
        workPmiDInsCommonDTO.setDirection(workPmiDInsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmiDInsListService.findList(workPmiDInsCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmiDInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmiDInsListService.findTotalCount(workPmiDInsCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmiDInsListForm.getListId(),workPmiDInsListForm.getCurrentPageId(), workPmiDInsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmiDInsListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmiDInsListForm workPmiDInsListForm) throws Exception
    {
        WorkPmiDInsListService workPmiDInsListService = (WorkPmiDInsListService) getBean("workPmiDInsListService");
        String[] deleteRows = workPmiDInsListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmiDInsListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}