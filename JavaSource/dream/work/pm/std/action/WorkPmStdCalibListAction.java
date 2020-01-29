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
import dream.work.pm.std.form.WorkPmStdCalibListForm;
import dream.work.pm.std.service.WorkPmStdCalibListService;

/**
 * 교정표준값 타입 - List Action
 * 
 * @author kim21017
 * @version $Id: WorkPmStdCalibListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmStdCalibList" name="workPmStdCalibListForm"
 *                input="/dream/work/pm/std/workPmStdCalibList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmStdCalibList" path="/dream/work/pm/std/workPmStdCalibList.jsp"
 *                        redirect="false"
 */

public class WorkPmStdCalibListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmStdCalibListForm workPmStdCalibListForm = (WorkPmStdCalibListForm) form;
        
        super.updateAudit(workPmStdCalibListForm.getWorkPmStdCalibCommonDTO().getAuditKey()==""?workPmStdCalibListForm.getWorkPmStdCalibCommonDTO().getAuditKey():workPmStdCalibListForm.getWorkPmStdCalibCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmStdCalibListForm.getStrutsAction())
        {
            case WorkPmStdCalibListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmStdCalibListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmStdCalibListAction.LIST_FIND:
                findList(request, response, workPmStdCalibListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkPmStdCalibListAction.LIST_DELETE:
            	deleteList(request, workPmStdCalibListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case WorkPmStdCalibListAction.BASE_GRID_EXPORT:
            	findList(request, response, workPmStdCalibListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmStdCalibList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibListForm workPmStdCalibListForm) throws IOException
    {
        super.setHeader(request, response, workPmStdCalibListForm.getListId(), workPmStdCalibListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmStdCalibListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibListForm workPmStdCalibListForm, boolean excelExport) throws Exception
    {
    	WorkPmStdCalibListService workPmStdCalibListService = (WorkPmStdCalibListService) getBean("workPmStdCalibListService");
    	WorkPmStdCalibCommonDTO workPmStdCalibCommonDTO = workPmStdCalibListForm.getWorkPmStdCalibCommonDTO();
    	
    	//Paging
    	workPmStdCalibCommonDTO.setIsLoadMaxCount("Y".equals(workPmStdCalibListForm.getIsLoadMaxCount())?true:false);
    	workPmStdCalibCommonDTO.setFirstRow(workPmStdCalibListForm.getFirstRow());
    	workPmStdCalibCommonDTO.setOrderBy(workPmStdCalibListForm.getOrderBy());
    	workPmStdCalibCommonDTO.setDirection(workPmStdCalibListForm.getDirection());
    	
        List resultList = workPmStdCalibListService.findList(workPmStdCalibCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workPmStdCalibListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmStdCalibListService.findTotalCount(workPmStdCalibCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workPmStdCalibListForm.getListId(),workPmStdCalibListForm.getCurrentPageId(), workPmStdCalibListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param workPmStdCalibListForm
     */
    private void deleteList(HttpServletRequest request, WorkPmStdCalibListForm workPmStdCalibListForm) throws Exception
    {
    	WorkPmStdCalibListService workPmStdCalibListService = (WorkPmStdCalibListService) getBean("workPmStdCalibListService");
        String[] deleteRows = workPmStdCalibListForm.getDeleteRows();
        workPmStdCalibListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
