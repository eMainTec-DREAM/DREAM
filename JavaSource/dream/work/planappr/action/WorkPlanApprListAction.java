package dream.work.planappr.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.planappr.form.WorkPlanApprListForm;
import dream.work.planappr.service.WorkPlanApprListService;

/**
 * 작업계획승인 - 목록 action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workPlanApprList" name="workPlanApprListForm"
 *                input="/dream/work/planappr/workPlanApprList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPlanApprList" path="/dream/work/planappr/workPlanApprList.jsp"
 *                        redirect="false"
 */
public class WorkPlanApprListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPlanApprListForm workPlanApprListForm = (WorkPlanApprListForm) form;
        
        super.updateAudit(workPlanApprListForm.getWorkPlanApprCommonDTO().getAuditKey()==""?workPlanApprListForm.getWorkPlanApprCommonDTO().getAuditKey():workPlanApprListForm.getWorkPlanApprCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPlanApprListForm.getStrutsAction())
        {
            case WorkPlanApprListAction.LIST_FIND:
            	findList(request, workPlanApprListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPlanApprListAction.BASE_SET_HEADER:
                setHeader(request, response, workPlanApprListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPlanApprListAction.LIST_DELETE:
            	deleteList(request, workPlanApprListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPlanApprListAction.BASE_GRID_EXPORT:
            	findList(request, workPlanApprListForm,response, true);
            	returnActionForward =new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPlanApprListForm workPlanApprListForm) throws IOException
    {
        super.setHeader(request, response, workPlanApprListForm.getListId(), workPlanApprListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkPlanApprListForm workPlanApprListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkPlanApprListService workPlanApprListService = (WorkPlanApprListService) getBean("workPlanApprListService");

    	WorkPlanApprCommonDTO workPlanApprCommonDTO = workPlanApprListForm.getWorkPlanApprCommonDTO();
    	workPlanApprCommonDTO.setIsLoadMaxCount("Y".equals(workPlanApprListForm.getIsLoadMaxCount())?true:false);
    	workPlanApprCommonDTO.setFirstRow(workPlanApprListForm.getFirstRow());
    	workPlanApprCommonDTO.setOrderBy(workPlanApprListForm.getOrderBy());
    	workPlanApprCommonDTO.setDirection(workPlanApprListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workPlanApprListService.findList(workPlanApprCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(workPlanApprListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPlanApprListService.findTotalCount(workPlanApprCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workPlanApprListForm.getListId(),workPlanApprListForm.getCurrentPageId(), workPlanApprListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, WorkPlanApprListForm workPlanApprListForm) throws Exception
    {
    	WorkPlanApprListService workPlanApprListService = (WorkPlanApprListService) getBean("workPlanApprListService");
    	String[] deleteRows = workPlanApprListForm.getDeleteRows();    // sheet 내역
        
    	workPlanApprListService.deleteList(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
