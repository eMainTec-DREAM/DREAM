package dream.work.pm.std.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.WorkPmStdCalibCommonDTO;
import dream.work.pm.std.dto.WorkPmStdCalibValListDTO;
import dream.work.pm.std.form.WorkPmStdCalibValListForm;
import dream.work.pm.std.service.WorkPmStdCalibValListService;

/**
 * 표준교정값 - List Action
 * 
 * @author kim21017
 * @version $Id: WorkPmStdCalibValListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmStdCalibValList" name="workPmStdCalibValListForm"
 *                input="/dream/work/pm/std/workPmStdCalibValList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmStdCalibValList" path="/dream/work/pm/std/workPmStdCalibValList.jsp"
 *                        redirect="false"
 */

public class WorkPmStdCalibValListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmStdCalibValListForm workPmStdCalibValListForm = (WorkPmStdCalibValListForm) form;
        
        super.updateAudit(workPmStdCalibValListForm.getWorkPmStdCalibValListDTO().getAuditKey()==""?workPmStdCalibValListForm.getWorkPmStdCalibValListDTO().getAuditKey():workPmStdCalibValListForm.getWorkPmStdCalibValListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmStdCalibValListForm.getStrutsAction())
        {
            case WorkPmStdCalibValListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmStdCalibValListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmStdCalibValListAction.LIST_FIND:
                findList(request, response, workPmStdCalibValListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmStdCalibValListAction.LIST_DELETE:
            	deleteList(request, workPmStdCalibValListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmStdCalibValListAction.BASE_GRID_EXPORT:
            	findList(request, response, workPmStdCalibValListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmStdCalibValList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValListForm workPmStdCalibValListForm) throws IOException
    {
        super.setHeader(request, response, workPmStdCalibValListForm.getListId(), workPmStdCalibValListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmStdCalibValListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValListForm workPmStdCalibValListForm, boolean excelExport) throws Exception
    {
    	WorkPmStdCalibValListService workPmStdCalibValListService = (WorkPmStdCalibValListService) getBean("workPmStdCalibValListService");
    	WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = workPmStdCalibValListForm.getWorkPmStdCalibCommonDTO();
    	WorkPmStdCalibValListDTO workPmStdCalibValListDTO = workPmStdCalibValListForm.getWorkPmStdCalibValListDTO();
        
    	//Paging
    	workPmStdCalibCommonDTO.setIsLoadMaxCount("Y".equals(workPmStdCalibValListForm.getIsLoadMaxCount())?true:false);
    	workPmStdCalibCommonDTO.setFirstRow(workPmStdCalibValListForm.getFirstRow());
    	workPmStdCalibCommonDTO.setOrderBy(workPmStdCalibValListForm.getOrderBy());
    	workPmStdCalibCommonDTO.setDirection(workPmStdCalibValListForm.getDirection());
    	
    	List resultList = workPmStdCalibValListService.findList(workPmStdCalibCommonDTO,workPmStdCalibValListDTO, getUser(request));
        
    	//Paging
        String totalCount = "";
        if(Integer.parseInt(workPmStdCalibValListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmStdCalibValListService.findTotalCount(workPmStdCalibCommonDTO, workPmStdCalibValListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workPmStdCalibValListForm.getListId(),workPmStdCalibValListForm.getCurrentPageId(), workPmStdCalibValListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmStdCalibValListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmStdCalibValListForm workPmStdCalibValListForm) throws Exception
    {
    	WorkPmStdCalibValListService workPmStdCalibValListService = (WorkPmStdCalibValListService) getBean("workPmStdCalibValListService");
        String[] deleteRows = workPmStdCalibValListForm.getDeleteRows();
        workPmStdCalibValListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
