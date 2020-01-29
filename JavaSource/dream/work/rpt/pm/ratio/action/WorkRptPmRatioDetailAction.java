package dream.work.rpt.pm.ratio.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.rpt.pm.ratio.dto.WorkRptPmRatioDetailDTO;
import dream.work.rpt.pm.ratio.form.WorkRptPmRatioDetailForm;
import dream.work.rpt.pm.ratio.service.WorkRptPmRatioDetailService;

/**
 * 계획보전율(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPmRatioDetailList" name="workRptPmRatioDetailForm"
 *                input="/dream/work/rpt/pm/ratio/workRptPmRatioDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptPmRatioDetailChart" name="workRptPmRatioDetailForm"
 *                input="/dream/work/rpt/pm/ratio/workRptPmRatioDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class WorkRptPmRatioDetailAction extends AuthAction
{
    public static final int PM_RATIO_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmRatioDetailForm workRptPmRatioDetailForm = (WorkRptPmRatioDetailForm) form;
        switch (workRptPmRatioDetailForm.getStrutsAction())
        {
            case WorkRptPmRatioDetailAction.PM_RATIO_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPmRatioDetailForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmRatioDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPmRatioDetailForm.getListId(), workRptPmRatioDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmRatioDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPmRatioDetailForm);
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
     * @author ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workRptPmRatioDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPmRatioDetailForm workRptPmRatioDetailForm) throws Exception
    {
        WorkRptPmRatioDetailService workRptPmRatioDetailService = (WorkRptPmRatioDetailService) getBean("workRptPmRatioDetailService");
        
        WorkRptPmRatioDetailDTO workRptPmRatioDetailDTO = workRptPmRatioDetailForm.getWorkRptPmRatioDetailDTO();
        
        List resultList = workRptPmRatioDetailService.findDetail(workRptPmRatioDetailDTO, getUser(request));
        
        super.makeJsonResult(resultList, request, response, workRptPmRatioDetailForm.getListId());
    }
    
}