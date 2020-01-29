package dream.work.rpt.pmins.deptach.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmins.deptach.dto.WorkRptPminsDeptAchDetailDTO;
import dream.work.rpt.pmins.deptach.form.WorkRptPminsDeptAchDetailForm;
import dream.work.rpt.pmins.deptach.service.WorkRptPminsDeptAchDetailService;

/**
 * 예방점검 이행율(부서) 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPminsDeptAchDetailList" name="workRptPminsDeptAchDetailForm"
 *                input="/dream/work/rpt/pmins/deptach/workRptPminsDeptAchDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptPminsDeptAchDetailChart" name="workRptPminsDeptAchDetailForm"
 *                input="/dream/work/rpt/pmins/deptach/workRptPminsDeptAchDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class WorkRptPminsDeptAchDetailAction extends AuthAction
{
    public static final int PMINS_ACH_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPminsDeptAchDetailForm workRptPminsDeptAchDetailForm = (WorkRptPminsDeptAchDetailForm) form;
        switch (workRptPminsDeptAchDetailForm.getStrutsAction())
        {
            case WorkRptPminsDeptAchDetailAction.PMINS_ACH_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPminsDeptAchDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsDeptAchDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPminsDeptAchDetailForm.getListId(), workRptPminsDeptAchDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsDeptAchDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPminsDeptAchDetailForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workRptPminsDeptAchDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPminsDeptAchDetailForm workRptPminsDeptAchDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPminsDeptAchDetailService workRptPminsDeptAchDetailService = (WorkRptPminsDeptAchDetailService) getBean("workRptPminsDeptAchDetailService");
        
        WorkRptPminsDeptAchDetailDTO workRptPminsDeptAchDetailDTO = workRptPminsDeptAchDetailForm.getWorkRptPminsDeptAchDetailDTO();
        
        //Paging
        workRptPminsDeptAchDetailDTO.setIsLoadMaxCount("Y".equals(workRptPminsDeptAchDetailForm.getIsLoadMaxCount())?true:false);
        workRptPminsDeptAchDetailDTO.setFirstRow(workRptPminsDeptAchDetailForm.getFirstRow());
        workRptPminsDeptAchDetailDTO.setOrderBy(workRptPminsDeptAchDetailForm.getOrderBy());
        workRptPminsDeptAchDetailDTO.setDirection(workRptPminsDeptAchDetailForm.getDirection());
        
        List resultList = workRptPminsDeptAchDetailService.findDetail(workRptPminsDeptAchDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPminsDeptAchDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPminsDeptAchDetailService.findTotalCount(workRptPminsDeptAchDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptPminsDeptAchDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}