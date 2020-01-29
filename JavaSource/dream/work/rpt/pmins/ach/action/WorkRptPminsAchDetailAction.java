package dream.work.rpt.pmins.ach.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmins.ach.dto.WorkRptPminsAchDetailDTO;
import dream.work.rpt.pmins.ach.form.WorkRptPminsAchDetailForm;
import dream.work.rpt.pmins.ach.service.WorkRptPminsAchDetailService;

/**
 * 예방점검 이행율(담당자) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPminsAchDetailList" name="workRptPminsAchDetailForm"
 *                input="/dream/work/rpt/pmins/ach/workRptPminsAchDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptPminsAchDetailChart" name="workRptPminsAchDetailForm"
 *                input="/dream/work/rpt/pmins/ach/workRptPminsAchDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class WorkRptPminsAchDetailAction extends AuthAction
{
    public static final int PMINS_ACH_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPminsAchDetailForm workRptPminsAchDetailForm = (WorkRptPminsAchDetailForm) form;
        switch (workRptPminsAchDetailForm.getStrutsAction())
        {
            case WorkRptPminsAchDetailAction.PMINS_ACH_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPminsAchDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsAchDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPminsAchDetailForm.getListId(), workRptPminsAchDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsAchDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPminsAchDetailForm, true);
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
     * @param workRptPminsAchDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPminsAchDetailForm workRptPminsAchDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPminsAchDetailService workRptPminsAchDetailService = (WorkRptPminsAchDetailService) getBean("workRptPminsAchDetailService");
        
        WorkRptPminsAchDetailDTO workRptPminsAchDetailDTO = workRptPminsAchDetailForm.getWorkRptPminsAchDetailDTO();
        
     	//Paging
        workRptPminsAchDetailDTO.setIsLoadMaxCount("Y".equals(workRptPminsAchDetailForm.getIsLoadMaxCount())?true:false);
        workRptPminsAchDetailDTO.setFirstRow(workRptPminsAchDetailForm.getFirstRow());
        workRptPminsAchDetailDTO.setOrderBy(workRptPminsAchDetailForm.getOrderBy());
        workRptPminsAchDetailDTO.setDirection(workRptPminsAchDetailForm.getDirection());
        
        //Paging
        String totalCount = "";
        
        List resultList = workRptPminsAchDetailService.findDetail(workRptPminsAchDetailDTO, getUser(request));

        if(Integer.parseInt(workRptPminsAchDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPminsAchDetailService.findTotalCount(workRptPminsAchDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptPminsAchDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}