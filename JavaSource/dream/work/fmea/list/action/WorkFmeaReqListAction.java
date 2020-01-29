package dream.work.fmea.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.fmea.list.dto.WorkFmeaReqCommonDTO;
import dream.work.fmea.list.form.WorkFmeaReqListForm;
import dream.work.fmea.list.service.WorkFmeaReqListService;

/**
 * 고장영향성평가 - List Action
 * 
 * @author kim21017
 * @version $Id: WorkFmeaReqListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workFmeaReqList" name="workFmeaReqListForm"
 *                input="/dream/work/fmea/list/workFmeaReqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workFmeaReqList" path="/dream/work/fmea/list/workFmeaReqList.jsp"
 *                        redirect="false"
 */

public class WorkFmeaReqListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkFmeaReqListForm workFmeaReqListForm = (WorkFmeaReqListForm) form;
        
        switch (workFmeaReqListForm.getStrutsAction())
        {
            case WorkFmeaReqListAction.BASE_SET_HEADER:
                setHeader(request, response, workFmeaReqListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkFmeaReqListAction.LIST_FIND:
                findList(request, response, workFmeaReqListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkFmeaReqListAction.LIST_DELETE:
            	deleteList(request, workFmeaReqListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkFmeaReqListAction.BASE_GRID_EXPORT:
            	findList(request, response, workFmeaReqListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workFmeaReqList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkFmeaReqListForm workFmeaReqListForm) throws IOException
    {
        super.setHeader(request, response, workFmeaReqListForm.getListId(), workFmeaReqListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workFmeaReqListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkFmeaReqListForm workFmeaReqListForm, boolean excelExport) throws Exception
    {
    	WorkFmeaReqListService workFmeaReqListService = (WorkFmeaReqListService) getBean("workFmeaReqListService");
    	WorkFmeaReqCommonDTO workFmeaReqCommonDTO = workFmeaReqListForm.getWorkFmeaReqCommonDTO();
    	
    	//Paging
    	workFmeaReqCommonDTO.setIsLoadMaxCount("Y".equals(workFmeaReqListForm.getIsLoadMaxCount())?true:false);
    	workFmeaReqCommonDTO.setFirstRow(workFmeaReqListForm.getFirstRow());
    	workFmeaReqCommonDTO.setOrderBy(workFmeaReqListForm.getOrderBy());
    	workFmeaReqCommonDTO.setDirection(workFmeaReqListForm.getDirection());
    	
        List resultList = workFmeaReqListService.findList(workFmeaReqCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workFmeaReqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workFmeaReqListService.findTotalCount(workFmeaReqCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workFmeaReqListForm.getListId(),workFmeaReqListForm.getCurrentPageId(), workFmeaReqListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workFmeaReqListForm
     */
    private void deleteList(HttpServletRequest request, WorkFmeaReqListForm workFmeaReqListForm) throws Exception
    {
    	WorkFmeaReqListService workFmeaReqListService = (WorkFmeaReqListService) getBean("workFmeaReqListService");
        String[] deleteRows = workFmeaReqListForm.getDeleteRows();
        workFmeaReqListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
