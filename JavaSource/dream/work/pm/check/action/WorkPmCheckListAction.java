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
import dream.work.pm.check.dto.WorkPmCheckCommonDTO;
import dream.work.pm.check.form.WorkPmCheckListForm;
import dream.work.pm.check.service.WorkPmCheckListService;

/**
 * WorkPmCheck Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: WorkPmCheckListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/workPmCheckList" name="workPmCheckListForm"
 *                input="/dream/work/pm/check/workPmCheckList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmCheckList" path="/dream/work/pm/check/workPmCheckList.jsp"
 *                        redirect="false"
 */
public class WorkPmCheckListAction extends AuthAction
{
    
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND       = 8001;
    /** 삭제 */
    public static final int LIST_DELETE     = 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmCheckListForm workPmCheckListForm = (WorkPmCheckListForm) form;
        
        super.updateAudit(workPmCheckListForm.getWorkPmCheckCommonDTO().getAuditKey()==""?workPmCheckListForm.getWorkPmCheckCommonDTO().getAuditKey():workPmCheckListForm.getWorkPmCheckCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmCheckListForm.getStrutsAction())
        {
            case WorkPmCheckListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmCheckListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmCheckListAction.LIST_FIND:
                findList(request, response, workPmCheckListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmCheckListAction.LIST_DELETE:
                deleteList(request, workPmCheckListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmCheckListAction.BASE_GRID_EXPORT:
                findList(request, response, workPmCheckListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmCheckList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmCheckListForm workPmCheckListForm) throws IOException
    {
        super.setHeader(request, response, workPmCheckListForm.getListId(), workPmCheckListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmCheckListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmCheckListForm workPmCheckListForm, boolean excelExport) throws Exception
    {
        WorkPmCheckListService workPmCheckListService = (WorkPmCheckListService) getBean("workPmCheckListService");
        WorkPmCheckCommonDTO workPmCheckCommonDTO = workPmCheckListForm.getWorkPmCheckCommonDTO();
      
        //Paging
        workPmCheckCommonDTO.setIsLoadMaxCount("Y".equals(workPmCheckListForm.getIsLoadMaxCount())?true:false);
        workPmCheckCommonDTO.setFirstRow(workPmCheckListForm.getFirstRow());
        workPmCheckCommonDTO.setOrderBy(workPmCheckListForm.getOrderBy());
        workPmCheckCommonDTO.setDirection(workPmCheckListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workPmCheckListService.findList(workPmCheckCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workPmCheckListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmCheckListService.findTotalCount(workPmCheckCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workPmCheckListForm.getListId(),workPmCheckListForm.getCurrentPageId(), workPmCheckListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmCheckListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmCheckListForm workPmCheckListForm) throws Exception
    {
        WorkPmCheckListService workPmCheckListService = (WorkPmCheckListService) getBean("workPmCheckListService");
        String[] deleteRows = workPmCheckListForm.getDeleteRows();
        
        User user = getUser(request);
        workPmCheckListService.deleteList(deleteRows, user);
        setAjaxStatus(request);
    }
    
    
}