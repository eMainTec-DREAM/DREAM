package dream.work.rpt.pmins.orgach.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmins.orgach.dto.WorkRptPminsOrgAchDetailDTO;
import dream.work.rpt.pmins.orgach.form.WorkRptPminsOrgAchDetailForm;
import dream.work.rpt.pmins.orgach.service.WorkRptPminsOrgAchDetailService;

/**
 * 예방점검 이행율(조직) 상세
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPminsOrgAchDetailList" name="workRptPminsOrgAchDetailForm"
 *                input="/dream/work/rpt/pmins/orgach/workRptPminsOrgAchDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptPminsOrgAchDetailChart" name="workRptPminsOrgAchDetailForm"
 *                input="/dream/work/rpt/pmins/orgach/workRptPminsOrgAchDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class WorkRptPminsOrgAchDetailAction extends AuthAction
{
    public static final int PMINS_ACH_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPminsOrgAchDetailForm workRptPminsOrgAchDetailForm = (WorkRptPminsOrgAchDetailForm) form;
        switch (workRptPminsOrgAchDetailForm.getStrutsAction())
        {
            case WorkRptPminsOrgAchDetailAction.PMINS_ACH_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPminsOrgAchDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsOrgAchDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPminsOrgAchDetailForm.getListId(), workRptPminsOrgAchDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPminsOrgAchDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPminsOrgAchDetailForm, true);
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
     * @author sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param workRptPminsOrgAchDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPminsOrgAchDetailForm workRptPminsOrgAchDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPminsOrgAchDetailService workRptPminsOrgAchDetailService = (WorkRptPminsOrgAchDetailService) getBean("workRptPminsOrgAchDetailService");
        WorkRptPminsOrgAchDetailDTO workRptPminsOrgAchDetailDTO = workRptPminsOrgAchDetailForm.getWorkRptPminsOrgAchDetailDTO();

        //Paging
        workRptPminsOrgAchDetailDTO.setIsLoadMaxCount("Y".equals(workRptPminsOrgAchDetailForm.getIsLoadMaxCount())?true:false);
        workRptPminsOrgAchDetailDTO.setFirstRow(workRptPminsOrgAchDetailForm.getFirstRow());
        workRptPminsOrgAchDetailDTO.setOrderBy(workRptPminsOrgAchDetailForm.getOrderBy());
        workRptPminsOrgAchDetailDTO.setDirection(workRptPminsOrgAchDetailForm.getDirection());
        
        List resultList = workRptPminsOrgAchDetailService.findDetail(workRptPminsOrgAchDetailDTO, getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPminsOrgAchDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPminsOrgAchDetailService.findTotalCount(workRptPminsOrgAchDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptPminsOrgAchDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}