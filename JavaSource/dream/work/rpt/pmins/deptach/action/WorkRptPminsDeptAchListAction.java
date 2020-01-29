package dream.work.rpt.pmins.deptach.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchCommonDTO;
import dream.work.rpt.pmins.deptach.form.WorkRptPminsDeptAchListForm;
import dream.work.rpt.pmins.deptach.service.WorkRptPminsDeptAchListService;

/**
 * 예방점검 이행율(부서)
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPminsDeptAchList" name="workRptPminsDeptAchListForm"
 *                input="/dream/work/rpt/pmins/deptach/workRptPminsDeptAchList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPminsDeptAchList" path="/dream/work/rpt/pmins/deptach/workRptPminsDeptAchList.jsp"
 *                        redirect="false"
 */
public class WorkRptPminsDeptAchListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PMINS_ACH_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPminsDeptAchListForm workRptPminsDeptAchListForm = (WorkRptPminsDeptAchListForm) form;
        
        switch (workRptPminsDeptAchListForm.getStrutsAction())
        {
        
            case WorkRptPminsDeptAchListAction.PMINS_ACH_LIST_FIND:
                findList(request,response, workRptPminsDeptAchListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsDeptAchListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptPminsDeptAchListForm.getListId(), workRptPminsDeptAchListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsDeptAchListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptPminsDeptAchListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPminsDeptAchListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptPminsDeptAchListForm workRptPminsDeptAchListForm, boolean excelExport) throws Exception
    {
        WorkRptPminsDeptAchListService workRptPminsDeptAchListService = (WorkRptPminsDeptAchListService) getBean("workRptPminsDeptAchListService");
        
        WorkRptPminsDeptAchCommonDTO workRptPminsDeptAchCommonDTO = workRptPminsDeptAchListForm.getWorkRptPminsDeptAchCommonDTO();
        
        //Paging
        workRptPminsDeptAchCommonDTO.setIsLoadMaxCount("Y".equals(workRptPminsDeptAchListForm.getIsLoadMaxCount())?true:false);
        workRptPminsDeptAchCommonDTO.setFirstRow(workRptPminsDeptAchListForm.getFirstRow());
        workRptPminsDeptAchCommonDTO.setOrderBy(workRptPminsDeptAchListForm.getOrderBy());
        workRptPminsDeptAchCommonDTO.setDirection(workRptPminsDeptAchListForm.getDirection());
        
        List resultList = workRptPminsDeptAchListService.findList(workRptPminsDeptAchCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPminsDeptAchListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPminsDeptAchListService.findTotalCount(workRptPminsDeptAchCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPminsDeptAchListForm.getListId(),workRptPminsDeptAchListForm.getCurrentPageId(), workRptPminsDeptAchListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}