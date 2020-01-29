package dream.work.rpt.maintrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.maintrate.dto.WorkRptMaintRateCommonDTO;
import dream.work.rpt.maintrate.form.WorkRptMaintRateListForm;
import dream.work.rpt.maintrate.service.WorkRptMaintRateListService;

/**
 * WorkRptMaintRate Page - List Action
 * 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptMaintRateList" name="workRptMaintRateListForm"
 *                input="/dream/work/rpt/maintrate/workRptMaintRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptMaintRateList" path="/dream/work/rpt/maintrate/workRptMaintRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptMaintRateListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptMaintRateListForm workRptMaintRateListForm = (WorkRptMaintRateListForm) form;
        
        switch (workRptMaintRateListForm.getStrutsAction())
        {
            case WorkRptMaintRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptMaintRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptMaintRateListAction.LIST_FIND:
                findList(request, response, workRptMaintRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptMaintRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptMaintRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptMaintRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptMaintRateListForm workRptMaintRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptMaintRateListForm.getListId(), workRptMaintRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptMaintRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptMaintRateListForm workRptMaintRateListForm, boolean excelExport) throws Exception
    {
        WorkRptMaintRateListService workRptMaintRateListService = (WorkRptMaintRateListService) getBean("workRptMaintRateListService");
        WorkRptMaintRateCommonDTO workRptMaintRateCommonDTO = workRptMaintRateListForm.getWorkRptMaintRateCommonDTO();
      
        //Paging
        workRptMaintRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptMaintRateListForm.getIsLoadMaxCount())?true:false);
        workRptMaintRateCommonDTO.setFirstRow(workRptMaintRateListForm.getFirstRow());
        workRptMaintRateCommonDTO.setOrderBy(workRptMaintRateListForm.getOrderBy());
        workRptMaintRateCommonDTO.setDirection(workRptMaintRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptMaintRateListService.findList(workRptMaintRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptMaintRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptMaintRateListService.findTotalCount(workRptMaintRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptMaintRateListForm.getListId(),workRptMaintRateListForm.getCurrentPageId(), workRptMaintRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}