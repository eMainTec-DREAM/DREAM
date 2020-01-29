package dream.work.pm.check.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.dto.WorkPmCheckMonthlyUnitPriceListDTO;
import dream.work.pm.check.form.WorkPmCheckMonthlyUnitPriceListForm;
import dream.work.pm.check.service.WorkPmCheckMonthlyUnitPriceListService;

/**
 * WorkPmCheckMonthlyUnitPrice Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmCheckMonthlyUnitPriceListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmCheckMonthlyUnitPriceList" name="workPmCheckMonthlyUnitPriceListForm"
 *                input="/dream/work/pm/check/workPmCheckMonthlyUnitPriceList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmCheckMonthlyUnitPriceList" path="/dream/work/pm/check/workPmCheckMonthlyUnitPriceList.jsp"
 *                        redirect="false"
 */
public class WorkPmCheckMonthlyUnitPriceListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 8001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmCheckMonthlyUnitPriceListForm workPmCheckMonthlyUnitPriceListForm = (WorkPmCheckMonthlyUnitPriceListForm) form;
        
        super.updateAudit(workPmCheckMonthlyUnitPriceListForm.getWorkPmCheckMonthlyUnitPriceListDTO().getAuditKey()==""?workPmCheckMonthlyUnitPriceListForm.getWorkPmCheckMonthlyUnitPriceListDTO().getAuditKey():workPmCheckMonthlyUnitPriceListForm.getWorkPmCheckMonthlyUnitPriceListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmCheckMonthlyUnitPriceListForm.getStrutsAction())
        {
            case WorkPmCheckMonthlyUnitPriceListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmCheckMonthlyUnitPriceListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmCheckMonthlyUnitPriceListAction.LIST_FIND:
                findList(request, response, workPmCheckMonthlyUnitPriceListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmCheckMonthlyUnitPriceListAction.LIST_DELETE:
                deleteList(request, workPmCheckMonthlyUnitPriceListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmCheckMonthlyUnitPriceListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmCheckMonthlyUnitPriceListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmCheckMonthlyUnitPriceList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmCheckMonthlyUnitPriceListForm workPmCheckMonthlyUnitPriceListForm) throws IOException
    {
        super.setHeader(request, response, workPmCheckMonthlyUnitPriceListForm.getListId(), workPmCheckMonthlyUnitPriceListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmCheckMonthlyUnitPriceListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmCheckMonthlyUnitPriceListForm workPmCheckMonthlyUnitPriceListForm, boolean excelExport) throws Exception
    {
        WorkPmCheckMonthlyUnitPriceListService workPmCheckMonthlyUnitPriceListService = (WorkPmCheckMonthlyUnitPriceListService) getBean("workPmCheckMonthlyUnitPriceListService");
        WorkPmCheckMonthlyUnitPriceListDTO workPmCheckMonthlyUnitPriceListDTO = workPmCheckMonthlyUnitPriceListForm.getWorkPmCheckMonthlyUnitPriceListDTO();
        WorkPmCheckCommonDTO workPmCheckCommonDTO = workPmCheckMonthlyUnitPriceListForm.getWorkPmCheckCommonDTO();
      
        //Paging
        workPmCheckMonthlyUnitPriceListDTO.setIsLoadMaxCount("Y".equals(workPmCheckMonthlyUnitPriceListForm.getIsLoadMaxCount())?true:false);
        workPmCheckMonthlyUnitPriceListDTO.setFirstRow(workPmCheckMonthlyUnitPriceListForm.getFirstRow());
        workPmCheckMonthlyUnitPriceListDTO.setOrderBy(workPmCheckMonthlyUnitPriceListForm.getOrderBy());
        workPmCheckMonthlyUnitPriceListDTO.setDirection(workPmCheckMonthlyUnitPriceListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmCheckMonthlyUnitPriceListService.findList(workPmCheckCommonDTO, workPmCheckMonthlyUnitPriceListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmCheckMonthlyUnitPriceListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmCheckMonthlyUnitPriceListService.findTotalCount(workPmCheckCommonDTO,workPmCheckMonthlyUnitPriceListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workPmCheckMonthlyUnitPriceListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmCheckMonthlyUnitPriceListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmCheckMonthlyUnitPriceListForm workPmCheckMonthlyUnitPriceListForm) throws Exception
    {
        WorkPmCheckMonthlyUnitPriceListService workPmCheckMonthlyUnitPriceListService = (WorkPmCheckMonthlyUnitPriceListService) getBean("workPmCheckMonthlyUnitPriceListService");
        String[] deleteRows = workPmCheckMonthlyUnitPriceListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmCheckMonthlyUnitPriceListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}