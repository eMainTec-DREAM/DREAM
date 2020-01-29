package dream.work.rpt.pmiequipplanrate.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanDetailDTO;
import dream.work.rpt.pmiequipplanrate.form.WorkRptPmiEquipPlanDetailForm;
import dream.work.rpt.pmiequipplanrate.service.WorkRptPmiEquipPlanDetailService;

/**
 * 고장TOP(위치) 상세
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/workRptPmiEquipPlanDetailList" name="workRptPmiEquipPlanDetailForm"
 *                input="/dream/work/rpt/pmiequipplanrate/workRptPmiEquipPlanDetailList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptPmiEquipPlanDetailAction extends AuthAction
{
    public static final int DETAIL_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiEquipPlanDetailForm workRptPmiEquipPlanDetailForm = (WorkRptPmiEquipPlanDetailForm) form;
        switch (workRptPmiEquipPlanDetailForm.getStrutsAction())
        {
            case WorkRptPmiEquipPlanDetailAction.DETAIL_FIND:
                // 페이지 조회
                this.findDetail(request, response, workRptPmiEquipPlanDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiEquipPlanDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, workRptPmiEquipPlanDetailForm.getListId(), workRptPmiEquipPlanDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiEquipPlanDetailAction.BASE_GRID_EXPORT:
                findDetail(request,response, workRptPmiEquipPlanDetailForm, true);
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
     * @param workRptPmiEquipPlanDetailForm
     */
    private void findDetail(HttpServletRequest request,HttpServletResponse response, WorkRptPmiEquipPlanDetailForm workRptPmiEquipPlanDetailForm, boolean excelExport) throws Exception
    {
        WorkRptPmiEquipPlanDetailService workRptPmiEquipPlanDetailService = (WorkRptPmiEquipPlanDetailService) getBean("workRptPmiEquipPlanDetailService");
        WorkRptPmiEquipPlanDetailDTO workRptPmiEquipPlanDetailDTO = workRptPmiEquipPlanDetailForm.getWorkRptPmiEquipPlanDetailDTO();

        //Paging
        workRptPmiEquipPlanDetailDTO.setIsLoadMaxCount("Y".equals(workRptPmiEquipPlanDetailForm.getIsLoadMaxCount())?true:false);
        workRptPmiEquipPlanDetailDTO.setFirstRow(workRptPmiEquipPlanDetailForm.getFirstRow());
        workRptPmiEquipPlanDetailDTO.setOrderBy(workRptPmiEquipPlanDetailForm.getOrderBy());
        workRptPmiEquipPlanDetailDTO.setDirection(workRptPmiEquipPlanDetailForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmiEquipPlanDetailService.findDetail(workRptPmiEquipPlanDetailDTO, getUser(request));

        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiEquipPlanDetailForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiEquipPlanDetailService.findTotalCount(workRptPmiEquipPlanDetailDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPmiEquipPlanDetailForm.getListId(),workRptPmiEquipPlanDetailForm.getCurrentPageId(), workRptPmiEquipPlanDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}