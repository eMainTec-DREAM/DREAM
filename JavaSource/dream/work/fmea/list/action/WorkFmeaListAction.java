package dream.work.fmea.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.fmea.list.form.WorkFmeaListForm;
import dream.work.fmea.list.service.WorkFmeaListService;

/**
 * 고장영향성평가 - List Action
 * 
 * @author kim21017
 * @version $Id: WorkFmeaListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workFmeaList" name="workFmeaListForm"
 *                input="/dream/work/fmea/list/workFmeaList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workFmeaList" path="/dream/work/fmea/list/workFmeaList.jsp"
 *                        redirect="false"
 */

public class WorkFmeaListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkFmeaListForm workFmeaListForm = (WorkFmeaListForm) form;
        
        super.updateAudit(workFmeaListForm.getWorkFmeaCommonDTO().getAuditKey()==""?workFmeaListForm.getWorkFmeaCommonDTO().getAuditKey():workFmeaListForm.getWorkFmeaCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workFmeaListForm.getStrutsAction())
        {
            case WorkFmeaListAction.BASE_SET_HEADER:
                setHeader(request, response, workFmeaListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkFmeaListAction.LIST_FIND:
                findList(request, response, workFmeaListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkFmeaListAction.LIST_DELETE:
            	deleteList(request, workFmeaListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkFmeaListAction.BASE_GRID_EXPORT:
            	findList(request, response, workFmeaListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workFmeaList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkFmeaListForm workFmeaListForm) throws IOException
    {
        super.setHeader(request, response, workFmeaListForm.getListId(), workFmeaListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workFmeaListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkFmeaListForm workFmeaListForm, boolean excelExport) throws Exception
    {
    	WorkFmeaListService workFmeaListService = (WorkFmeaListService) getBean("workFmeaListService");
    	WorkFmeaCommonDTO workFmeaCommonDTO = workFmeaListForm.getWorkFmeaCommonDTO();
    	
    	//Paging
    	workFmeaCommonDTO.setIsLoadMaxCount("Y".equals(workFmeaListForm.getIsLoadMaxCount())?true:false);
    	workFmeaCommonDTO.setFirstRow(workFmeaListForm.getFirstRow());
    	workFmeaCommonDTO.setOrderBy(workFmeaListForm.getOrderBy());
    	workFmeaCommonDTO.setDirection(workFmeaListForm.getDirection());
    	
        List resultList = workFmeaListService.findList(workFmeaCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workFmeaListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workFmeaListService.findTotalCount(workFmeaCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workFmeaListForm.getListId(),workFmeaListForm.getCurrentPageId(), workFmeaListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workFmeaListForm
     */
    private void deleteList(HttpServletRequest request, WorkFmeaListForm workFmeaListForm) throws Exception
    {
    	WorkFmeaListService workFmeaListService = (WorkFmeaListService) getBean("workFmeaListService");
        String[] deleteRows = workFmeaListForm.getDeleteRows();
        workFmeaListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
