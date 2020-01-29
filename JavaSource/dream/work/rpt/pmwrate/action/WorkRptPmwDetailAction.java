package dream.work.rpt.pmwrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmwrate.dto.WorkRptPmwDetailDTO;
import dream.work.rpt.pmwrate.form.WorkRptPmwDetailForm;
import dream.work.rpt.pmwrate.service.WorkRptPmwDetailService;

/**
 * 주기정비 실행 비율 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPmwDetailList" name="workRptPmwDetailForm"
 *                input="/dream/work/rpt/pmwrate/workRptPmwDetailList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptPmwDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmwDetailForm workRptPmwDetailForm = (WorkRptPmwDetailForm) form;
        switch (workRptPmwDetailForm.getStrutsAction())
        {
            case WorkRptPmwDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPmwDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmwDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPmwDetailForm.getListId(), workRptPmwDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmwDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPmwDetailForm, true);
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
     * @param workRptPmwDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPmwDetailForm workRptPmwDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPmwDetailService workRptPmwDetailService = (WorkRptPmwDetailService) getBean("workRptPmwDetailService");
        
        WorkRptPmwDetailDTO workRptPmwDetailDTO = workRptPmwDetailForm.getWorkRptPmwDetailDTO();
        
        //Paging
        workRptPmwDetailDTO.setIsLoadMaxCount("Y".equals(workRptPmwDetailForm.getIsLoadMaxCount())?true:false);
        workRptPmwDetailDTO.setFirstRow(workRptPmwDetailForm.getFirstRow());
        workRptPmwDetailDTO.setOrderBy(workRptPmwDetailForm.getOrderBy());
        workRptPmwDetailDTO.setDirection(workRptPmwDetailForm.getDirection());
        
        List resultList = workRptPmwDetailService.findDetail(workRptPmwDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmwDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmwDetailService.findTotalCount(workRptPmwDetailDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptPmwDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}