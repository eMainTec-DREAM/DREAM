package dream.work.rpt.pmicmptrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.pmicmptrate.dto.WorkRptPmiCmptDetailDTO;
import dream.work.rpt.pmicmptrate.form.WorkRptPmiCmptDetailForm;
import dream.work.rpt.pmicmptrate.service.WorkRptPmiCmptDetailService;

/**
 * 고장TOP(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPmiCmptDetailList" name="workRptPmiCmptDetailForm"
 *                input="/dream/work/rpt/pmicmptrate/workRptPmiCmptDetailList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptPmiCmptDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiCmptDetailForm workRptPmiCmptDetailForm = (WorkRptPmiCmptDetailForm) form;
        switch (workRptPmiCmptDetailForm.getStrutsAction())
        {
            case WorkRptPmiCmptDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPmiCmptDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiCmptDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPmiCmptDetailForm.getListId(), workRptPmiCmptDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiCmptDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPmiCmptDetailForm, true);
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
     * @param workRptPmiCmptDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPmiCmptDetailForm workRptPmiCmptDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPmiCmptDetailService workRptPmiCmptDetailService = (WorkRptPmiCmptDetailService) getBean("workRptPmiCmptDetailService");
        
        WorkRptPmiCmptDetailDTO workRptPmiCmptDetailDTO = workRptPmiCmptDetailForm.getWorkRptPmiCmptDetailDTO();

        //Paging
        workRptPmiCmptDetailDTO.setIsLoadMaxCount("Y".equals(workRptPmiCmptDetailForm.getIsLoadMaxCount())?true:false);
        workRptPmiCmptDetailDTO.setFirstRow(workRptPmiCmptDetailForm.getFirstRow());
        workRptPmiCmptDetailDTO.setOrderBy(workRptPmiCmptDetailForm.getOrderBy());
        workRptPmiCmptDetailDTO.setDirection(workRptPmiCmptDetailForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmiCmptDetailService.findDetail(workRptPmiCmptDetailDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiCmptDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiCmptDetailService.findTotalCount(workRptPmiCmptDetailDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPmiCmptDetailForm.getListId(),workRptPmiCmptDetailForm.getCurrentPageId(), workRptPmiCmptDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}