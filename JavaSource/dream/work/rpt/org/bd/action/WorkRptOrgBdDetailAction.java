package dream.work.rpt.org.bd.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.org.bd.dto.WorkRptOrgBdDetailDTO;
import dream.work.rpt.org.bd.form.WorkRptOrgBdDetailForm;
import dream.work.rpt.org.bd.service.WorkRptOrgBdDetailService;

/**
 * 조직별 고장분석 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptOrgBdDetailList" name="workRptOrgBdDetailForm"
 *                input="/dream/work/rpt/org/bd/workRptOrgBdDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptOrgBdDetailChart" name="workRptOrgBdDetailForm"
 *                input="/dream/work/rpt/org/bd/workRptOrgBdDetailChart.jsp" scope="request"
 *                validate="false"            
 * @struts:action path="/workRptOrgBdWorkTimeDetailChart" name="workRptOrgBdDetailForm"
 *                input="/dream/work/rpt/org/bd/workRptOrgBdWorkTimeDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class WorkRptOrgBdDetailAction extends AuthAction
{
    public static final int LCC_EQUIP_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptOrgBdDetailForm workRptOrgBdDetailForm = (WorkRptOrgBdDetailForm) form;
        switch (workRptOrgBdDetailForm.getStrutsAction())
        {
            case WorkRptOrgBdDetailAction.LCC_EQUIP_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptOrgBdDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgBdDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptOrgBdDetailForm.getListId(), workRptOrgBdDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgBdDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptOrgBdDetailForm, true);
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
     * @param workRptOrgBdDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptOrgBdDetailForm workRptOrgBdDetailForm, boolean excelExport) throws Exception
    {
        WorkRptOrgBdDetailService workRptOrgBdDetailService = (WorkRptOrgBdDetailService) getBean("workRptOrgBdDetailService");
        
        WorkRptOrgBdDetailDTO workRptOrgBdDetailDTO = workRptOrgBdDetailForm.getWorkRptOrgBdDetailDTO();
        
        //Paging
        workRptOrgBdDetailDTO.setIsLoadMaxCount("Y".equals(workRptOrgBdDetailForm.getIsLoadMaxCount())?true:false);
        workRptOrgBdDetailDTO.setFirstRow(workRptOrgBdDetailForm.getFirstRow());
        workRptOrgBdDetailDTO.setOrderBy(workRptOrgBdDetailForm.getOrderBy());
        workRptOrgBdDetailDTO.setDirection(workRptOrgBdDetailForm.getDirection());

        String totalCount = "";
        
        List resultList = workRptOrgBdDetailService.findDetail(workRptOrgBdDetailDTO, getUser(request));
        
        if(Integer.parseInt(workRptOrgBdDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptOrgBdDetailService.findTotalCount(workRptOrgBdDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptOrgBdDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}