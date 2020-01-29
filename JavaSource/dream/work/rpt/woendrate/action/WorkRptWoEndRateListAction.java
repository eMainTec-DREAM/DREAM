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
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;
import dream.work.rpt.woendrate.form.WorkRptWoEndRateListForm;
import dream.work.rpt.woendrate.service.WorkRptWoEndRateListService;

/**
 * 작업오더 일마감 처리율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptWoEndRateList" name="workRptWoEndRateListForm"
 *                input="/dream/work/rpt/woendrate/workRptWoEndRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptWoEndRateList" path="/dream/work/rpt/woendrate/workRptWoEndRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptWoEndRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWoEndRateListForm workRptWoEndRateListForm = (WorkRptWoEndRateListForm) form;
        
        switch (workRptWoEndRateListForm.getStrutsAction())
        {
            case WorkRptWoEndRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptWoEndRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWoEndRateListAction.LIST_FIND:
                findList(request, response, workRptWoEndRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptWoEndRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptWoEndRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptWoEndRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptWoEndRateListForm workRptWoEndRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptWoEndRateListForm.getListId(), workRptWoEndRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptWoEndRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptWoEndRateListForm workRptWoEndRateListForm, boolean excelExport) throws Exception
    {
        WorkRptWoEndRateListService workRptWoEndRateListService = (WorkRptWoEndRateListService) getBean("workRptWoEndRateListService");
        WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO = workRptWoEndRateListForm.getWorkRptWoEndRateCommonDTO();
      
        //Paging
        workRptWoEndRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptWoEndRateListForm.getIsLoadMaxCount())?true:false);
        workRptWoEndRateCommonDTO.setFirstRow(workRptWoEndRateListForm.getFirstRow());
        workRptWoEndRateCommonDTO.setOrderBy(workRptWoEndRateListForm.getOrderBy());
        workRptWoEndRateCommonDTO.setDirection(workRptWoEndRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptWoEndRateListService.findList(workRptWoEndRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWoEndRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWoEndRateListService.findTotalCount(workRptWoEndRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptWoEndRateListForm.getListId(),workRptWoEndRateListForm.getCurrentPageId(), workRptWoEndRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}