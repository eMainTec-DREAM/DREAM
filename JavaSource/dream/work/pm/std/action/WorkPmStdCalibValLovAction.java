package dream.work.pm.std.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pm.std.dto.WorkPmStdCalibValLovDTO;
import dream.work.pm.std.form.WorkPmStdCalibValLovForm;
import dream.work.pm.std.service.WorkPmStdCalibValLovService;

/**
 * 교정표준값 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: WorkPmStdCalibValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/workPmStdCalibValLov" name="workPmStdCalibValLovForm"
 *                input="/dream/work/pm/std/workPmStdCalibValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmStdCalibValLov" path="/dream/work/pm/std/workPmStdCalibValLov.jsp"
 *                        redirect="false"
 */

public class WorkPmStdCalibValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmStdCalibValLovForm workPmStdCalibValLovForm = (WorkPmStdCalibValLovForm) form;
        
        switch (workPmStdCalibValLovForm.getStrutsAction())
        {
            case WorkPmStdCalibValLovAction.BASE_SET_HEADER:
                setHeader(request, response, workPmStdCalibValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmStdCalibValLovAction.LIST_FIND:
                findList(request, response, workPmStdCalibValLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("workPmStdCalibValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValLovForm workPmStdCalibValLovForm) throws IOException
    {
        super.setHeader(request, response, workPmStdCalibValLovForm.getListId(), workPmStdCalibValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workPmStdCalibValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkPmStdCalibValLovForm workPmStdCalibValLovForm, boolean excelExport) throws Exception
    {
    	WorkPmStdCalibValLovService workPmStdCalibValLovService = (WorkPmStdCalibValLovService) getBean("workPmStdCalibValLovService");
    	WorkPmStdCalibValLovDTO workPmStdCalibValLovDTO = workPmStdCalibValLovForm.getWorkPmStdCalibValLovDTO();
    	
    	//Paging
    	workPmStdCalibValLovDTO.setIsLoadMaxCount("Y".equals(workPmStdCalibValLovForm.getIsLoadMaxCount())?true:false);
    	workPmStdCalibValLovDTO.setFirstRow(workPmStdCalibValLovForm.getFirstRow());
    	workPmStdCalibValLovDTO.setOrderBy(workPmStdCalibValLovForm.getOrderBy());
    	workPmStdCalibValLovDTO.setDirection(workPmStdCalibValLovForm.getDirection());
    	
        List resultList = workPmStdCalibValLovService.findList(workPmStdCalibValLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(workPmStdCalibValLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmStdCalibValLovService.findTotalCount(workPmStdCalibValLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workPmStdCalibValLovForm.getListId(),workPmStdCalibValLovForm.getCurrentPageId(), workPmStdCalibValLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
