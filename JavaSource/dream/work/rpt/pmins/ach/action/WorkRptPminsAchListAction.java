package dream.work.rpt.pmins.ach.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchCommonDTO;
import dream.work.rpt.pmins.ach.form.WorkRptPminsAchListForm;
import dream.work.rpt.pmins.ach.service.WorkRptPminsAchListService;

/**
 * 예방점검 이행율(담당자)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPminsAchList" name="workRptPminsAchListForm"
 *                input="/dream/work/rpt/pmins/ach/workRptPminsAchList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPminsAchList" path="/dream/work/rpt/pmins/ach/workRptPminsAchList.jsp"
 *                        redirect="false"
 */
public class WorkRptPminsAchListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PMINS_ACH_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPminsAchListForm workRptPminsAchListForm = (WorkRptPminsAchListForm) form;
        
        switch (workRptPminsAchListForm.getStrutsAction())
        {
        
            case WorkRptPminsAchListAction.PMINS_ACH_LIST_FIND:
                findList(request,response, workRptPminsAchListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsAchListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptPminsAchListForm.getListId(), workRptPminsAchListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsAchListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptPminsAchListForm, true);
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
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPminsAchListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptPminsAchListForm workRptPminsAchListForm, boolean excelExport) throws Exception
    {
        WorkRptPminsAchListService workRptPminsAchListService = (WorkRptPminsAchListService) getBean("workRptPminsAchListService");
        
        WorkRptPminsAchCommonDTO workRptPminsAchCommonDTO = workRptPminsAchListForm.getWorkRptPminsAchCommonDTO();
        
        //Paging
        workRptPminsAchCommonDTO.setIsLoadMaxCount("Y".equals(workRptPminsAchListForm.getIsLoadMaxCount())?true:false);
        workRptPminsAchCommonDTO.setFirstRow(workRptPminsAchListForm.getFirstRow());
        workRptPminsAchCommonDTO.setOrderBy(workRptPminsAchListForm.getOrderBy());
        workRptPminsAchCommonDTO.setDirection(workRptPminsAchListForm.getDirection());
        
        List resultList = workRptPminsAchListService.findList(workRptPminsAchCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPminsAchListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPminsAchListService.findTotalCount(workRptPminsAchCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPminsAchListForm.getListId(),workRptPminsAchListForm.getCurrentPageId(), workRptPminsAchListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}