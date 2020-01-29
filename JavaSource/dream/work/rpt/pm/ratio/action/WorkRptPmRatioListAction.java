package dream.work.rpt.pm.ratio.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioCommonDTO;
import dream.work.rpt.pm.ratio.form.WorkRptPmRatioListForm;
import dream.work.rpt.pm.ratio.service.WorkRptPmRatioListService;

/**
 * 계획보전율(위치)
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPmRatioList" name="workRptPmRatioListForm"
 *                input="/dream/work/rpt/pm/ratio/workRptPmRatioList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptPmRatioListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int PM_RATIO_LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmRatioListForm workRptPmRatioListForm = (WorkRptPmRatioListForm) form;
        
        switch (workRptPmRatioListForm.getStrutsAction())
        {
        
            case WorkRptPmRatioListAction.PM_RATIO_LIST_FIND:
                findList(request,response, workRptPmRatioListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmRatioListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workRptPmRatioListForm.getListId(), workRptPmRatioListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmRatioListAction.BASE_GRID_EXPORT:
            	findList(request,response, workRptPmRatioListForm, true);
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
     * @param workRptPmRatioListForm
     * @param excelExport
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkRptPmRatioListForm workRptPmRatioListForm, boolean excelExport) throws Exception
    {
        WorkRptPmRatioListService workRptPmRatioListService = (WorkRptPmRatioListService) getBean("workRptPmRatioListService");
        
        WorkRptPmRatioCommonDTO workRptPmRatioCommonDTO = workRptPmRatioListForm.getWorkRptPmRatioCommonDTO();
        
        //Paging
        workRptPmRatioCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmRatioListForm.getIsLoadMaxCount())?true:false);
        workRptPmRatioCommonDTO.setFirstRow(workRptPmRatioListForm.getFirstRow());
        workRptPmRatioCommonDTO.setOrderBy(workRptPmRatioListForm.getOrderBy());
        workRptPmRatioCommonDTO.setDirection(workRptPmRatioListForm.getDirection());
        
        List resultList = workRptPmRatioListService.findList(workRptPmRatioCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmRatioListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmRatioListService.findTotalCount(workRptPmRatioCommonDTO,getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPmRatioListForm.getListId(),workRptPmRatioListForm.getCurrentPageId(), workRptPmRatioListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}