package dream.work.rpt.org.mtbfmttr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.org.mtbfmttr.dto.WorkRptOrgMtbfmttrDetailDTO;
import dream.work.rpt.org.mtbfmttr.form.WorkRptOrgMtbfmttrDetailForm;
import dream.work.rpt.org.mtbfmttr.service.WorkRptOrgMtbfmttrDetailService;

/**
 * 조직별MTBF,MTTR 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptOrgMtbfmttrDetailList" name="workRptOrgMtbfmttrDetailForm"
 *                input="/dream/work/rpt/org/mtbfmttr/workRptOrgMtbfmttrDetailList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workRptOrgMtbfmttrDetailChart" name="workRptOrgMtbfmttrDetailForm"
 *                input="/dream/work/rpt/org/mtbfmttr/workRptOrgMtbfmttrDetailChart.jsp" scope="request"
 *                validate="false"            
 */
public class WorkRptOrgMtbfmttrDetailAction extends AuthAction
{
    public static final int MTTRMTBF_EQUIP_DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptOrgMtbfmttrDetailForm workRptOrgMtbfmttrDetailForm = (WorkRptOrgMtbfmttrDetailForm) form;
        switch (workRptOrgMtbfmttrDetailForm.getStrutsAction())
        {
            case WorkRptOrgMtbfmttrDetailAction.MTTRMTBF_EQUIP_DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptOrgMtbfmttrDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgMtbfmttrDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptOrgMtbfmttrDetailForm.getListId(), workRptOrgMtbfmttrDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptOrgMtbfmttrDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptOrgMtbfmttrDetailForm, true);
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
     * @param workRptOrgMtbfmttrDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptOrgMtbfmttrDetailForm workRptOrgMtbfmttrDetailForm, boolean excelExport) throws Exception
    {
        WorkRptOrgMtbfmttrDetailService workRptOrgMtbfmttrDetailService = (WorkRptOrgMtbfmttrDetailService) getBean("workRptOrgMtbfmttrDetailService");
        
        WorkRptOrgMtbfmttrDetailDTO workRptOrgMtbfmttrDetailDTO = workRptOrgMtbfmttrDetailForm.getWorkRptOrgMtbfmttrDetailDTO();
        
        //Paging
        workRptOrgMtbfmttrDetailDTO.setIsLoadMaxCount("Y".equals(workRptOrgMtbfmttrDetailForm.getIsLoadMaxCount())?true:false);
        workRptOrgMtbfmttrDetailDTO.setFirstRow(workRptOrgMtbfmttrDetailForm.getFirstRow());
        workRptOrgMtbfmttrDetailDTO.setOrderBy(workRptOrgMtbfmttrDetailForm.getOrderBy());
        workRptOrgMtbfmttrDetailDTO.setDirection(workRptOrgMtbfmttrDetailForm.getDirection());
        
        List resultList = workRptOrgMtbfmttrDetailService.findDetail(workRptOrgMtbfmttrDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptOrgMtbfmttrDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptOrgMtbfmttrDetailService.findTotalCount(workRptOrgMtbfmttrDetailDTO,getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workRptOrgMtbfmttrDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}