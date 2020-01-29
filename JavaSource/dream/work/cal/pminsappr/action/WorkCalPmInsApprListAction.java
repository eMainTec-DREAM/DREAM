package dream.work.cal.pminsappr.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.cal.pminsappr.form.WorkCalPmInsApprListForm;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprListService;

/**
 * 예방점검계획승인 - 목록 action
 * @author  kim21017
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workCalPmInsApprList" name="workCalPmInsApprListForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmInsApprNotList" name="workCalPmInsApprListForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprNotList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workCalPmInsApprCompList" name="workCalPmInsApprListForm"
 *                input="/dream/work/cal/pminsappr/workCalPmInsApprCompList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workCalPmInsApprList" path="/dream/work/cal/pminsappr/workCalPmInsApprList.jsp"
 *                        redirect="false"
 */
public class WorkCalPmInsApprListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;

    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkCalPmInsApprListForm workCalPmInsApprListForm = (WorkCalPmInsApprListForm) form;
        
        super.updateAudit(workCalPmInsApprListForm.getWorkCalPmInsApprCommonDTO().getAuditKey()==""?workCalPmInsApprListForm.getWorkCalPmInsApprCommonDTO().getAuditKey():workCalPmInsApprListForm.getWorkCalPmInsApprCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workCalPmInsApprListForm.getStrutsAction())
        {
            case WorkCalPmInsApprListAction.LIST_FIND:
            	findList(request, workCalPmInsApprListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmInsApprListAction.BASE_SET_HEADER:
                setHeader(request, response, workCalPmInsApprListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkCalPmInsApprListAction.LIST_DELETE:
            	deleteList(request, workCalPmInsApprListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkCalPmInsApprListAction.BASE_GRID_EXPORT:
            	findList(request, workCalPmInsApprListForm,response, true);
            	returnActionForward =new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkCalPmInsApprListForm workCalPmInsApprListForm) throws IOException
    {
        super.setHeader(request, response, workCalPmInsApprListForm.getListId(), workCalPmInsApprListForm.getCurrentPageId()); 
    }
    
    private void findList(HttpServletRequest request, WorkCalPmInsApprListForm workCalPmInsApprListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkCalPmInsApprListService workCalPmInsApprListService = (WorkCalPmInsApprListService) getBean("workCalPmInsApprListService");

    	WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = workCalPmInsApprListForm.getWorkCalPmInsApprCommonDTO();
    	workCalPmInsApprCommonDTO.setIsLoadMaxCount("Y".equals(workCalPmInsApprListForm.getIsLoadMaxCount())?true:false);
    	workCalPmInsApprCommonDTO.setFirstRow(workCalPmInsApprListForm.getFirstRow());
    	workCalPmInsApprCommonDTO.setOrderBy(workCalPmInsApprListForm.getOrderBy());
    	workCalPmInsApprCommonDTO.setDirection(workCalPmInsApprListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workCalPmInsApprListService.findList(workCalPmInsApprCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(workCalPmInsApprListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workCalPmInsApprListService.findTotalCount(workCalPmInsApprCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workCalPmInsApprListForm.getListId(),workCalPmInsApprListForm.getCurrentPageId(), workCalPmInsApprListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, WorkCalPmInsApprListForm workCalPmInsApprListForm) throws Exception
    {
    	WorkCalPmInsApprListService workCalPmInsApprListService = (WorkCalPmInsApprListService) getBean("workCalPmInsApprListService");
    	String[] deleteRows = workCalPmInsApprListForm.getDeleteRows();    // sheet 내역
        
    	workCalPmInsApprListService.deleteList(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
