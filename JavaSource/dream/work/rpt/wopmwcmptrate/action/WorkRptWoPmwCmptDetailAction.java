package dream.work.rpt.wopmwcmptrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.wopmwcmptrate.dto.WorkRptWoPmwCmptDetailDTO;
import dream.work.rpt.wopmwcmptrate.form.WorkRptWoPmwCmptDetailForm;
import dream.work.rpt.wopmwcmptrate.service.WorkRptWoPmwCmptDetailService;

/**
 * 예방작업 계획대비 실행 비율 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptWoPmwCmptDetailList" name="workRptWoPmwCmptDetailForm"
 *                input="/dream/work/rpt/wopmwcmptrate/workRptWoPmwCmptDetailList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptWoPmwCmptDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptWoPmwCmptDetailForm workRptWoPmwCmptDetailForm = (WorkRptWoPmwCmptDetailForm) form;
        switch (workRptWoPmwCmptDetailForm.getStrutsAction())
        {
            case WorkRptWoPmwCmptDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptWoPmwCmptDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWoPmwCmptDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptWoPmwCmptDetailForm.getListId(), workRptWoPmwCmptDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptWoPmwCmptDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptWoPmwCmptDetailForm, true);
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
     * @param workRptWoPmwCmptDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptWoPmwCmptDetailForm workRptWoPmwCmptDetailForm, boolean excelExport) throws Exception
    {
        WorkRptWoPmwCmptDetailService workRptWoPmwCmptDetailService = (WorkRptWoPmwCmptDetailService) getBean("workRptWoPmwCmptDetailService");
        
        WorkRptWoPmwCmptDetailDTO workRptWoPmwCmptDetailDTO = workRptWoPmwCmptDetailForm.getWorkRptWoPmwCmptDetailDTO();
        
        //Paging
        workRptWoPmwCmptDetailDTO.setIsLoadMaxCount("Y".equals(workRptWoPmwCmptDetailForm.getIsLoadMaxCount())?true:false);
        workRptWoPmwCmptDetailDTO.setFirstRow(workRptWoPmwCmptDetailForm.getFirstRow());
        workRptWoPmwCmptDetailDTO.setOrderBy(workRptWoPmwCmptDetailForm.getOrderBy());
        workRptWoPmwCmptDetailDTO.setDirection(workRptWoPmwCmptDetailForm.getDirection());
        
        List resultList = workRptWoPmwCmptDetailService.findDetail(workRptWoPmwCmptDetailDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptWoPmwCmptDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptWoPmwCmptDetailService.findTotalCount(workRptWoPmwCmptDetailDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptWoPmwCmptDetailForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
}