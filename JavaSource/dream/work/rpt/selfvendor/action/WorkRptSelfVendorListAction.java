package dream.work.rpt.selfvendor.action;

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
import dream.work.rpt.selfvendor.dto.WorkRptSelfVendorCommonDTO;
import dream.work.rpt.selfvendor.form.WorkRptSelfVendorListForm;
import dream.work.rpt.selfvendor.service.WorkRptSelfVendorListService;

/**
 * 사내, 외주 작업 현황 Report - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptSelfVendorList" name="workRptSelfVendorListForm"
 *                input="/dream/work/rpt/selfvendor/workRptSelfVendorList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptSelfVendorList" path="/dream/work/rpt/selfvendor/workRptSelfVendorList.jsp"
 *                        redirect="false"
 */
public class WorkRptSelfVendorListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptSelfVendorListForm workRptSelfVendorListForm = (WorkRptSelfVendorListForm) form;
        
        switch (workRptSelfVendorListForm.getStrutsAction())
        {
            case WorkRptSelfVendorListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptSelfVendorListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptSelfVendorListAction.LIST_FIND:
                findList(request, response, workRptSelfVendorListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptSelfVendorListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptSelfVendorListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptSelfVendorList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptSelfVendorListForm workRptSelfVendorListForm) throws IOException
    {
        super.setHeader(request, response, workRptSelfVendorListForm.getListId(), workRptSelfVendorListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptSelfVendorListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptSelfVendorListForm workRptSelfVendorListForm, boolean excelExport) throws Exception
    {
        WorkRptSelfVendorListService workRptSelfVendorListService = (WorkRptSelfVendorListService) getBean("workRptSelfVendorListService");
        WorkRptSelfVendorCommonDTO workRptSelfVendorCommonDTO = workRptSelfVendorListForm.getWorkRptSelfVendorCommonDTO();
      
        //Paging
        workRptSelfVendorCommonDTO.setIsLoadMaxCount("Y".equals(workRptSelfVendorListForm.getIsLoadMaxCount())?true:false);
        workRptSelfVendorCommonDTO.setFirstRow(workRptSelfVendorListForm.getFirstRow());
        workRptSelfVendorCommonDTO.setOrderBy(workRptSelfVendorListForm.getOrderBy());
        workRptSelfVendorCommonDTO.setDirection(workRptSelfVendorListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptSelfVendorListService.findList(workRptSelfVendorCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptSelfVendorListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptSelfVendorListService.findTotalCount(workRptSelfVendorCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptSelfVendorListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}