package dream.work.rpt.pmwcmptrate.action;

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
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptRateCommonDTO;
import dream.work.rpt.pmwcmptrate.form.WorkRptPmwCmptRateListForm;
import dream.work.rpt.pmwcmptrate.service.WorkRptPmwCmptRateListService;

/**
 * 주기정비 계획대비 실행 비율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptPmwCmptRateList" name="workRptPmwCmptRateListForm"
 *                input="/dream/work/rpt/pmwcmptrate/workRptPmwCmptRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmwCmptRateList" path="/dream/work/rpt/pmwcmptrate/workRptPmwCmptRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmwCmptRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmwCmptRateListForm workRptPmwCmptRateListForm = (WorkRptPmwCmptRateListForm) form;
        
        switch (workRptPmwCmptRateListForm.getStrutsAction())
        {
            case WorkRptPmwCmptRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptPmwCmptRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmwCmptRateListAction.LIST_FIND:
                findList(request, response, workRptPmwCmptRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptPmwCmptRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptPmwCmptRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptPmwCmptRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptPmwCmptRateListForm workRptPmwCmptRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptPmwCmptRateListForm.getListId(), workRptPmwCmptRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPmwCmptRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptPmwCmptRateListForm workRptPmwCmptRateListForm, boolean excelExport) throws Exception
    {
        WorkRptPmwCmptRateListService workRptPmwCmptRateListService = (WorkRptPmwCmptRateListService) getBean("workRptPmwCmptRateListService");
        WorkRptPmwCmptRateCommonDTO workRptPmwCmptRateCommonDTO = workRptPmwCmptRateListForm.getWorkRptPmwCmptRateCommonDTO();
      
        //Paging
        workRptPmwCmptRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmwCmptRateListForm.getIsLoadMaxCount())?true:false);
        workRptPmwCmptRateCommonDTO.setFirstRow(workRptPmwCmptRateListForm.getFirstRow());
        workRptPmwCmptRateCommonDTO.setOrderBy(workRptPmwCmptRateListForm.getOrderBy());
        workRptPmwCmptRateCommonDTO.setDirection(workRptPmwCmptRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmwCmptRateListService.findList(workRptPmwCmptRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmwCmptRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmwCmptRateListService.findTotalCount(workRptPmwCmptRateCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptPmwCmptRateListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}