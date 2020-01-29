package dream.work.rpt.wopmwcmptrate.action;

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
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptRateCommonDTO;
import dream.work.rpt.wopmwcmptrate.form.WorkRptWoPmwCmptRateListForm;
import dream.work.rpt.wopmwcmptrate.service.WorkRptWoPmwCmptRateListService;

/**
 * 예방작업 계획대비 실행 비율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptWoPmwCmptRateList" name="workRptWoPmwCmptRateListForm"
 *                input="/dream/work/rpt/woppmwcmptrate/workRptWoPmwCmptRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptWoPmwCmptRateList" path="/dream/work/rpt/wopmwcmptrate/workRptWoPmwCmptRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptWoPmwCmptRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWoPmwCmptRateListForm workRptWoPmwCmptRateListForm = (WorkRptWoPmwCmptRateListForm) form;
        
        switch (workRptWoPmwCmptRateListForm.getStrutsAction())
        {
            case WorkRptWoPmwCmptRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptWoPmwCmptRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWoPmwCmptRateListAction.LIST_FIND:
                findList(request, response, workRptWoPmwCmptRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptWoPmwCmptRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptWoPmwCmptRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptWoPmwCmptRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptWoPmwCmptRateListForm workRptWoPmwCmptRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptWoPmwCmptRateListForm.getListId(), workRptWoPmwCmptRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptWoPmwCmptRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptWoPmwCmptRateListForm workRptWoPmwCmptRateListForm, boolean excelExport) throws Exception
    {
        WorkRptWoPmwCmptRateListService workRptWoPmwCmptRateListService = (WorkRptWoPmwCmptRateListService) getBean("workRptWoPmwCmptRateListService");
        WorkRptWoPmwCmptRateCommonDTO workRptWoPmwCmptRateCommonDTO = workRptWoPmwCmptRateListForm.getWorkRptWoPmwCmptRateCommonDTO();
      
        //Paging
        workRptWoPmwCmptRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptWoPmwCmptRateListForm.getIsLoadMaxCount())?true:false);
        workRptWoPmwCmptRateCommonDTO.setFirstRow(workRptWoPmwCmptRateListForm.getFirstRow());
        workRptWoPmwCmptRateCommonDTO.setOrderBy(workRptWoPmwCmptRateListForm.getOrderBy());
        workRptWoPmwCmptRateCommonDTO.setDirection(workRptWoPmwCmptRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptWoPmwCmptRateListService.findList(workRptWoPmwCmptRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWoPmwCmptRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWoPmwCmptRateListService.findTotalCount(workRptWoPmwCmptRateCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptWoPmwCmptRateListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}