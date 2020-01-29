package dream.work.list.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WorkListCinsPlanMstrCommonDTO;
import dream.work.list.form.WorkListCinsPlanMstrListForm;
import dream.work.list.service.WorkListCinsPlanMstrListService;

/**
 * WorkListCinsPlanMstr Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkListCinsPlanMstrListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workListCinsPlanMstrList" name="workListCinsPlanMstrListForm"
 *                input="/dream/work/list/workListCinsPlanMstrList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListCinsPlanMstrList" path="/dream/work/list/workListCinsPlanMstrList.jsp"
 *                        redirect="false"
 */
public class WorkListCinsPlanMstrListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    /** 생성시 선택 Popup */
    public static final int POPUP_SELECT    = 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListCinsPlanMstrListForm workListCinsPlanMstrListForm = (WorkListCinsPlanMstrListForm) form;
        
        switch (workListCinsPlanMstrListForm.getStrutsAction())
        {
            case WorkListCinsPlanMstrListAction.BASE_SET_HEADER:
                setHeader(request, response, workListCinsPlanMstrListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListCinsPlanMstrListAction.LIST_FIND:
                findList(request, response, workListCinsPlanMstrListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkListCinsPlanMstrListAction.LIST_DELETE:
                deleteList(request, workListCinsPlanMstrListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkListCinsPlanMstrListAction.BASE_GRID_EXPORT:
                findList(request, response, workListCinsPlanMstrListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workListCinsPlanMstrList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkListCinsPlanMstrListForm workListCinsPlanMstrListForm) throws IOException
    {
        super.setHeader(request, response, workListCinsPlanMstrListForm.getListId(), workListCinsPlanMstrListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workListCinsPlanMstrListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkListCinsPlanMstrListForm workListCinsPlanMstrListForm, boolean excelExport) throws Exception
    {
        WorkListCinsPlanMstrListService workListCinsPlanMstrListService = (WorkListCinsPlanMstrListService) getBean("workListCinsPlanMstrListService");
        WorkListCinsPlanMstrCommonDTO workListCinsPlanMstrCommonDTO = workListCinsPlanMstrListForm.getWorkListCinsPlanMstrCommonDTO();

        //Paging
        workListCinsPlanMstrCommonDTO.setIsLoadMaxCount("Y".equals(workListCinsPlanMstrListForm.getIsLoadMaxCount())?true:false);
        workListCinsPlanMstrCommonDTO.setFirstRow(workListCinsPlanMstrListForm.getFirstRow());
        workListCinsPlanMstrCommonDTO.setOrderBy(workListCinsPlanMstrListForm.getOrderBy());
        workListCinsPlanMstrCommonDTO.setDirection(workListCinsPlanMstrListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListCinsPlanMstrListService.findList(workListCinsPlanMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListCinsPlanMstrListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListCinsPlanMstrListService.findTotalCount(workListCinsPlanMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListCinsPlanMstrListForm.getListId(),workListCinsPlanMstrListForm.getCurrentPageId(), workListCinsPlanMstrListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workListCinsPlanMstrListForm
     */
    private void deleteList(HttpServletRequest request, WorkListCinsPlanMstrListForm workListCinsPlanMstrListForm) throws Exception
    {
        WorkListCinsPlanMstrListService workListCinsPlanMstrListService = (WorkListCinsPlanMstrListService) getBean("workListCinsPlanMstrListService");
        String[] deleteRows = workListCinsPlanMstrListForm.getDeleteRows();
        
        User user = getUser(request);
        workListCinsPlanMstrListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}