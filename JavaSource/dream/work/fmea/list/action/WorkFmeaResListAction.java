package dream.work.fmea.list.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.fmea.list.dto.WorkFmeaResCommonDTO;
import dream.work.fmea.list.form.WorkFmeaResListForm;
import dream.work.fmea.list.service.WorkFmeaResListService;

/**
 * 고장영향성평가 - List Action
 * 
 * @author kim21017
 * @version $Id: WorkFmeaResListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workFmeaResList" name="workFmeaResListForm"
 *                input="/dream/work/fmea/list/workFmeaResList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workFmeaResList" path="/dream/work/fmea/list/workFmeaResList.jsp"
 *                        redirect="false"
 */
public class WorkFmeaResListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkFmeaResListForm workFmeaResListForm = (WorkFmeaResListForm) form;
        
        switch (workFmeaResListForm.getStrutsAction())
        {
            case WorkFmeaResListAction.BASE_SET_HEADER:
                setHeader(request, response, workFmeaResListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkFmeaResListAction.LIST_FIND:
                findList(request, response, workFmeaResListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkFmeaResListAction.LIST_DELETE:
            	deleteList(request, workFmeaResListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkFmeaResListAction.BASE_GRID_EXPORT:
            	findList(request, response, workFmeaResListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workFmeaResList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkFmeaResListForm workFmeaResListForm) throws IOException
    {
        super.setHeader(request, response, workFmeaResListForm.getListId(), workFmeaResListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workFmeaResListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkFmeaResListForm workFmeaResListForm, boolean excelExport) throws Exception
    {
    	WorkFmeaResListService workFmeaResListService = (WorkFmeaResListService) getBean("workFmeaResListService");
    	WorkFmeaResCommonDTO workFmeaResCommonDTO = workFmeaResListForm.getWorkFmeaResCommonDTO();
    	
    	//Paging
    	workFmeaResCommonDTO.setIsLoadMaxCount("Y".equals(workFmeaResListForm.getIsLoadMaxCount())?true:false);
    	workFmeaResCommonDTO.setFirstRow(workFmeaResListForm.getFirstRow());
    	workFmeaResCommonDTO.setOrderBy(workFmeaResListForm.getOrderBy());
    	workFmeaResCommonDTO.setDirection(workFmeaResListForm.getDirection());
    	
        List resultList = workFmeaResListService.findList(workFmeaResCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workFmeaResListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workFmeaResListService.findTotalCount(workFmeaResCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workFmeaResListForm.getListId(),workFmeaResListForm.getCurrentPageId(), workFmeaResListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workFmeaResListForm
     */
    private void deleteList(HttpServletRequest request, WorkFmeaResListForm workFmeaResListForm) throws Exception
    {
    	WorkFmeaResListService workFmeaResListService = (WorkFmeaResListService) getBean("workFmeaResListService");
        String[] deleteRows = workFmeaResListForm.getDeleteRows();
        workFmeaResListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
