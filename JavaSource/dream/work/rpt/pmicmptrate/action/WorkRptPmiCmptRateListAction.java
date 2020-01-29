package dream.work.rpt.pmicmptrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptRateCommonDTO;
import dream.work.rpt.pmicmptrate.form.WorkRptPmiCmptRateListForm;
import dream.work.rpt.pmicmptrate.service.WorkRptPmiCmptRateListService;

/**
 * 예방점검항목 실행 비율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptPmiCmptRateList" name="workRptPmiCmptRateListForm"
 *                input="/dream/work/rpt/pmicmptrate/workRptPmiCmptRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmiCmptRateList" path="/dream/work/rpt/pmicmptrate/workRptPmiCmptRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmiCmptRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiCmptRateListForm workRptPmiCmptRateListForm = (WorkRptPmiCmptRateListForm) form;
        
        switch (workRptPmiCmptRateListForm.getStrutsAction())
        {
            case WorkRptPmiCmptRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptPmiCmptRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiCmptRateListAction.LIST_FIND:
                findList(request, response, workRptPmiCmptRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptPmiCmptRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptPmiCmptRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptPmiCmptRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptPmiCmptRateListForm workRptPmiCmptRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptPmiCmptRateListForm.getListId(), workRptPmiCmptRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPmiCmptRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptPmiCmptRateListForm workRptPmiCmptRateListForm, boolean excelExport) throws Exception
    {
        WorkRptPmiCmptRateListService workRptPmiCmptRateListService = (WorkRptPmiCmptRateListService) getBean("workRptPmiCmptRateListService");
        WorkRptPmiCmptRateCommonDTO workRptPmiCmptRateCommonDTO = workRptPmiCmptRateListForm.getWorkRptPmiCmptRateCommonDTO();
      
        //Paging
        workRptPmiCmptRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmiCmptRateListForm.getIsLoadMaxCount())?true:false);
        workRptPmiCmptRateCommonDTO.setFirstRow(workRptPmiCmptRateListForm.getFirstRow());
        workRptPmiCmptRateCommonDTO.setOrderBy(workRptPmiCmptRateListForm.getOrderBy());
        workRptPmiCmptRateCommonDTO.setDirection(workRptPmiCmptRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmiCmptRateListService.findList(workRptPmiCmptRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiCmptRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiCmptRateListService.findTotalCount(workRptPmiCmptRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPmiCmptRateListForm.getListId(),workRptPmiCmptRateListForm.getCurrentPageId(), workRptPmiCmptRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}