package dream.work.rpt.pmwcmptrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmwcmptrate.dto.WorkRptPmwCmptDetailDTO;
import dream.work.rpt.pmwcmptrate.form.WorkRptPmwCmptDetailForm;
import dream.work.rpt.pmwcmptrate.service.WorkRptPmwCmptDetailService;

/**
 * 주기정비 계획대비 실행 비율 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPmwCmptDetailList" name="workRptPmwCmptDetailForm"
 *                input="/dream/work/rpt/pmwcmptrate/workRptPmwCmptDetailList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptPmwCmptDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmwCmptDetailForm workRptPmwCmptDetailForm = (WorkRptPmwCmptDetailForm) form;
        switch (workRptPmwCmptDetailForm.getStrutsAction())
        {
            case WorkRptPmwCmptDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPmwCmptDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmwCmptDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPmwCmptDetailForm.getListId(), workRptPmwCmptDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmwCmptDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPmwCmptDetailForm, true);
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
     * @param workRptPmwCmptDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPmwCmptDetailForm workRptPmwCmptDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPmwCmptDetailService workRptPmwCmptDetailService = (WorkRptPmwCmptDetailService) getBean("workRptPmwCmptDetailService");
        
        WorkRptPmwCmptDetailDTO workRptPmwCmptDetailDTO = workRptPmwCmptDetailForm.getWorkRptPmwCmptDetailDTO();
        
        //Paging
        workRptPmwCmptDetailDTO.setIsLoadMaxCount("Y".equals(workRptPmwCmptDetailForm.getIsLoadMaxCount())?true:false);
        workRptPmwCmptDetailDTO.setFirstRow(workRptPmwCmptDetailForm.getFirstRow());
        workRptPmwCmptDetailDTO.setOrderBy(workRptPmwCmptDetailForm.getOrderBy());
        workRptPmwCmptDetailDTO.setDirection(workRptPmwCmptDetailForm.getDirection());
        
        List resultList = workRptPmwCmptDetailService.findDetail(workRptPmwCmptDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmwCmptDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmwCmptDetailService.findTotalCount(workRptPmwCmptDetailDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptPmwCmptDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}