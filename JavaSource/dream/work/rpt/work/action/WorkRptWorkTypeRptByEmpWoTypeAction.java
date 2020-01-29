package dream.work.rpt.work.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.work.dto.WorkRptWorkTypeRptByEmpWoTypeDTO;
import dream.work.rpt.work.form.WorkRptWorkTypeRptByEmpWoTypeForm;
import dream.work.rpt.work.service.WorkRptWorkTypeRptByEmpWoTypeService;

/**
 * 담당자별작업현황 - 담당자 작업종류별현황 탭 목록 Action
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptWorkTypeRptByEmpWoType" name="workRptWorkTypeRptByEmpWoTypeForm"
 *                input="/dream/work/rpt/work/workRptWorkTypeRptByEmpWoType.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptWorkTypeRptByEmpWoTypeAction extends AuthAction 
{
    public static final int WOTYPE_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWorkTypeRptByEmpWoTypeForm workRptWorkTypeRptByEmpWoTypeForm = (WorkRptWorkTypeRptByEmpWoTypeForm) form;
        switch (workRptWorkTypeRptByEmpWoTypeForm.getStrutsAction())
        {
            case WorkRptWorkTypeRptByEmpWoTypeAction.WOTYPE_LIST_FIND:
            	// 페이지 조회
            	this.findWoTypeList(request, response, workRptWorkTypeRptByEmpWoTypeForm, false);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case WorkRptWorkTypeRptByEmpWoTypeAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptWorkTypeRptByEmpWoTypeForm.getListId(), workRptWorkTypeRptByEmpWoTypeForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWorkTypeRptByEmpWoTypeAction.BASE_GRID_EXPORT:
            	findWoTypeList(request,response, workRptWorkTypeRptByEmpWoTypeForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 담당자 작업종류별현황 조회
     * @author js.lee
     * @since   1.0
     *
     * @param request
     * @param response
     * @param workRptWorkTypeRptByEmpWoTypeForm
     * @param excelExport
     * @throws Exception
     */
    private void findWoTypeList(HttpServletRequest request,HttpServletResponse response, WorkRptWorkTypeRptByEmpWoTypeForm workRptWorkTypeRptByEmpWoTypeForm, boolean excelExport) throws Exception
    {
    	WorkRptWorkTypeRptByEmpWoTypeService workRptWorkTypeRptByEmpWoTypeService = (WorkRptWorkTypeRptByEmpWoTypeService) getBean("workRptWorkTypeRptByEmpWoTypeService");
    	
    	WorkRptWorkTypeRptByEmpWoTypeDTO workRptWorkTypeRptByEmpWoTypeDTO = workRptWorkTypeRptByEmpWoTypeForm.getWorkRptWorkTypeRptByEmpWoTypeDTO();
    	
    	//Paging
    	workRptWorkTypeRptByEmpWoTypeDTO.setIsLoadMaxCount("Y".equals(workRptWorkTypeRptByEmpWoTypeForm.getIsLoadMaxCount())?true:false);
    	workRptWorkTypeRptByEmpWoTypeDTO.setFirstRow(workRptWorkTypeRptByEmpWoTypeForm.getFirstRow());
    	workRptWorkTypeRptByEmpWoTypeDTO.setOrderBy(workRptWorkTypeRptByEmpWoTypeForm.getOrderBy());
    	workRptWorkTypeRptByEmpWoTypeDTO.setDirection(workRptWorkTypeRptByEmpWoTypeForm.getDirection());
    	
    	List resultList = workRptWorkTypeRptByEmpWoTypeService.findWoTypeList(workRptWorkTypeRptByEmpWoTypeDTO, getUser(request));
    	
    	//Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWorkTypeRptByEmpWoTypeForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWorkTypeRptByEmpWoTypeService.findTotalCount(workRptWorkTypeRptByEmpWoTypeDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptWorkTypeRptByEmpWoTypeForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}