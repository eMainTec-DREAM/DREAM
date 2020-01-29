package dream.work.rpt.deptworkprecon.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.deptworkprecon.dto.WorkRptDeptWorkPreconListDTO;
import dream.work.rpt.deptworkprecon.form.WorkRptDeptWorkPreconListForm;
import dream.work.rpt.deptworkprecon.service.WorkRptDeptWorkPreconListService;

/**
 * 부서별 작업진행현황 Action
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/workRptDeptWorkPreconList" name="workRptDeptWorkPreconListForm"
 *                input="/dream/work/rpt/deptworkprecon/workRptDeptWorkPreconList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptDeptWorkPreconList" path="/dream/work/rpt/deptworkprecon/workRptDeptWorkPreconList.jsp"
 *                        redirect="false"
 */
public class WorkRptDeptWorkPreconListAction extends AuthAction
{
    /** 부서별 작업진행현황 리스트 조회 */
    public static final int LIST_FIND			= 1001;
    public static final int SET_GRID_HEADER		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptDeptWorkPreconListForm workRptDeptWorkPreconListForm = (WorkRptDeptWorkPreconListForm) form;
        
        switch (workRptDeptWorkPreconListForm.getStrutsAction())
        {
	        case WorkRptDeptWorkPreconListAction.LIST_FIND:
	            findList(request, workRptDeptWorkPreconListForm, response,false);
	            returnActionForward = mapping.findForward("jsonPage");
	            break;
            case WorkRptDeptWorkPreconListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptDeptWorkPreconListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
	        case WorkRptDeptWorkPreconListAction.SET_GRID_HEADER:
	        	setGridHeader(request);
	        	returnActionForward = mapping.findForward("ajaxXmlVal");
	        	break;
	        case WorkRptDeptWorkPreconListAction.BASE_GRID_EXPORT:
	        	findList(request, workRptDeptWorkPreconListForm, response, true);
	        	returnActionForward = new ActionForward("/gridExport");
	        	break;
            default:
                returnActionForward = mapping.findForward("workRptDeptWorkPreconList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param workRptDeptWorkPreconListForm
     * @param response
     * @throws Exception
     */
    private void findList(HttpServletRequest request, WorkRptDeptWorkPreconListForm workRptDeptWorkPreconListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkRptDeptWorkPreconListService workRptDeptWorkPreconListService = (WorkRptDeptWorkPreconListService) getBean("workRptDeptWorkPreconListService");        

    	WorkRptDeptWorkPreconListDTO workRptDeptWorkPreconListDTO = workRptDeptWorkPreconListForm.getWorkRptDeptWorkPreconListDTO();
    	workRptDeptWorkPreconListDTO.setCompNo(getUser(request).getCompNo());
    	
    	User loginUser = getUser(request);
        //리스트를 조회한다.
        List resultList = workRptDeptWorkPreconListService.findList(workRptDeptWorkPreconListDTO, loginUser);

        if(excelExport) super.makeGridExport(resultList, request, response,workRptDeptWorkPreconListForm.getListId(),workRptDeptWorkPreconListForm.getCurrentPageId(), workRptDeptWorkPreconListForm.getFileName());
        else super.makeTreeJsonResult(resultList, request, response, workRptDeptWorkPreconListForm.getListId());
	}
    
    private void setGridHeader(HttpServletRequest request) throws Exception
    {
    	WorkRptDeptWorkPreconListService workRptDeptWorkPreconListService = (WorkRptDeptWorkPreconListService) getBean("workRptDeptWorkPreconListService");        
        User user = getUser(request);
        String woTypes = workRptDeptWorkPreconListService.findWoTypes(user);
        setAjaxDesc(request, woTypes);
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptDeptWorkPreconListForm workRptDeptWorkPreconListForm) throws IOException
    {
        super.setHeader(request, response, workRptDeptWorkPreconListForm.getListId(),workRptDeptWorkPreconListForm.getCurrentPageId()); 
    }
}
