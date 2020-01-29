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
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.form.WorkPmiCInsListForm;
import dream.work.pm.list.service.WorkPmiCInsListService;

/**
 * WorkPmiCIns Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmiCInsListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmiCInsList" name="workPmiCInsListForm"
 *                input="/dream/work/pmi/list/workPmiCInsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiCInsList" path="/dream/work/pmi/list/workPmiCInsList.jsp"
 *                        redirect="false"
 */
public class WorkPmiCInsListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiCInsListForm workPmiCInsListForm = (WorkPmiCInsListForm) form;
        
        switch (workPmiCInsListForm.getStrutsAction())
        {
            case WorkPmiCInsListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmiCInsListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiCInsListAction.LIST_FIND:
                findList(request, response, workPmiCInsListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmiCInsListAction.LIST_DELETE:
                deleteList(request, workPmiCInsListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmiCInsListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmiCInsListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsListForm workPmiCInsListForm) throws IOException
    {
        super.setHeader(request, response, workPmiCInsListForm.getListId(), workPmiCInsListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmiCInsListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmiCInsListForm workPmiCInsListForm, boolean excelExport) throws Exception
    {
        WorkPmiCInsListService workPmiCInsListService = (WorkPmiCInsListService) getBean("workPmiCInsListService");
        WorkPmiCInsCommonDTO workPmiCInsCommonDTO = workPmiCInsListForm.getWorkPmiCInsCommonDTO();

        //Paging
        workPmiCInsCommonDTO.setIsLoadMaxCount("Y".equals(workPmiCInsListForm.getIsLoadMaxCount())?true:false);
        workPmiCInsCommonDTO.setFirstRow(workPmiCInsListForm.getFirstRow());
        workPmiCInsCommonDTO.setOrderBy(workPmiCInsListForm.getOrderBy());
        workPmiCInsCommonDTO.setDirection(workPmiCInsListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmiCInsListService.findList(workPmiCInsCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmiCInsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmiCInsListService.findTotalCount(workPmiCInsCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmiCInsListForm.getListId(),workPmiCInsListForm.getCurrentPageId(), workPmiCInsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmiCInsListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmiCInsListForm workPmiCInsListForm) throws Exception
    {
        WorkPmiCInsListService workPmiCInsListService = (WorkPmiCInsListService) getBean("workPmiCInsListService");
        String[] deleteRows = workPmiCInsListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmiCInsListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}