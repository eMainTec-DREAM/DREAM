package dream.work.rpt.pmwrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmwrate.dto.WorkRptPmwRateCommonDTO;
import dream.work.rpt.pmwrate.form.WorkRptPmwRateListForm;
import dream.work.rpt.pmwrate.service.WorkRptPmwRateListService;

/**
 * 주기정비 실행 비율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptPmwRateList" name="workRptPmwRateListForm"
 *                input="/dream/work/rpt/pmwrate/workRptPmwRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmwRateList" path="/dream/work/rpt/pmwrate/workRptPmwRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmwRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmwRateListForm workRptPmwRateListForm = (WorkRptPmwRateListForm) form;
        
        switch (workRptPmwRateListForm.getStrutsAction())
        {
            case WorkRptPmwRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptPmwRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmwRateListAction.LIST_FIND:
                findList(request, response, workRptPmwRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptPmwRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptPmwRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptPmwRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptPmwRateListForm workRptPmwRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptPmwRateListForm.getListId(), workRptPmwRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPmwRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptPmwRateListForm workRptPmwRateListForm, boolean excelExport) throws Exception
    {
        WorkRptPmwRateListService workRptPmwRateListService = (WorkRptPmwRateListService) getBean("workRptPmwRateListService");
        WorkRptPmwRateCommonDTO workRptPmwRateCommonDTO = workRptPmwRateListForm.getWorkRptPmwRateCommonDTO();
      
        //Paging
        workRptPmwRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmwRateListForm.getIsLoadMaxCount())?true:false);
        workRptPmwRateCommonDTO.setFirstRow(workRptPmwRateListForm.getFirstRow());
        workRptPmwRateCommonDTO.setOrderBy(workRptPmwRateListForm.getOrderBy());
        workRptPmwRateCommonDTO.setDirection(workRptPmwRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmwRateListService.findList(workRptPmwRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmwRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmwRateListService.findTotalCount(workRptPmwRateCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptPmwRateListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}