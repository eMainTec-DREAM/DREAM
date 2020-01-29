package dream.work.rpt.woendrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.woendrate.dto.WorkRptWoEndDetailListDTO;
import dream.work.rpt.woendrate.form.WorkRptWoEndDetailListForm;
import dream.work.rpt.woendrate.service.WorkRptWoEndDetailListService;

/**
 * 작업오더 일마감 처리율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptWoEndDetailList" name="workRptWoEndDetailListForm"
 *                input="/dream/work/rpt/woendrate/workRptWoEndDetailList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptWoEndDetailList" path="/dream/work/rpt/woendrate/workRptWoEndDetailList.jsp"
 *                        redirect="false"
 */
public class WorkRptWoEndDetailListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWoEndDetailListForm workRptWoEndDetailListForm = (WorkRptWoEndDetailListForm) form;
        
        switch (workRptWoEndDetailListForm.getStrutsAction())
        {
            case WorkRptWoEndDetailListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptWoEndDetailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWoEndDetailListAction.LIST_FIND:
                findList(request, response, workRptWoEndDetailListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptWoEndDetailListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptWoEndDetailListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptWoEndDetailList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptWoEndDetailListForm workRptWoEndDetailListForm) throws IOException
    {
        super.setHeader(request, response, workRptWoEndDetailListForm.getListId(), workRptWoEndDetailListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptWoEndDetailListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptWoEndDetailListForm workRptWoEndDetailListForm, boolean excelExport) throws Exception
    {
        WorkRptWoEndDetailListService workRptWoEndDetailListService = (WorkRptWoEndDetailListService) getBean("workRptWoEndDetailListService");
        WorkRptWoEndDetailListDTO workRptWoEndDetailListDTO = workRptWoEndDetailListForm.getWorkRptWoEndDetailListDTO();
      
        //Paging
        workRptWoEndDetailListDTO.setIsLoadMaxCount("Y".equals(workRptWoEndDetailListForm.getIsLoadMaxCount())?true:false);
        workRptWoEndDetailListDTO.setFirstRow(workRptWoEndDetailListForm.getFirstRow());
        workRptWoEndDetailListDTO.setOrderBy(workRptWoEndDetailListForm.getOrderBy());
        workRptWoEndDetailListDTO.setDirection(workRptWoEndDetailListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptWoEndDetailListService.findList(workRptWoEndDetailListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWoEndDetailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWoEndDetailListService.findTotalCount(workRptWoEndDetailListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptWoEndDetailListForm.getListId(),workRptWoEndDetailListForm.getCurrentPageId(), workRptWoEndDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}