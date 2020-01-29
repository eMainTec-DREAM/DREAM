package dream.work.rpt.pmiequipplanrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.rpt.pmiequipplanrate.dto.WorkRptPmiEquipPlanRateCommonDTO;
import dream.work.rpt.pmiequipplanrate.form.WorkRptPmiEquipPlanRateListForm;
import dream.work.rpt.pmiequipplanrate.service.WorkRptPmiEquipPlanRateListService;

/**
 * 예방점검 설비 계획대비 실행 비율 목록 - List Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptPmiEquipPlanRateList" name="workRptPmiEquipPlanRateListForm"
 *                input="/dream/work/rpt/pmiequipplanrate/workRptPmiEquipPlanRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmiEquipPlanRateList" path="/dream/work/rpt/pmiequipplanrate/workRptPmiEquipPlanRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmiEquipPlanRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiEquipPlanRateListForm workRptPmiEquipPlanRateListForm = (WorkRptPmiEquipPlanRateListForm) form;
        
        switch (workRptPmiEquipPlanRateListForm.getStrutsAction())
        {
            case WorkRptPmiEquipPlanRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptPmiEquipPlanRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiEquipPlanRateListAction.LIST_FIND:
                findList(request, response, workRptPmiEquipPlanRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptPmiEquipPlanRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptPmiEquipPlanRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptPmiEquipPlanRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptPmiEquipPlanRateListForm workRptPmiEquipPlanRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptPmiEquipPlanRateListForm.getListId(), workRptPmiEquipPlanRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPmiEquipPlanRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptPmiEquipPlanRateListForm workRptPmiEquipPlanRateListForm, boolean excelExport) throws Exception
    {
        WorkRptPmiEquipPlanRateListService workRptPmiEquipPlanRateListService = (WorkRptPmiEquipPlanRateListService) getBean("workRptPmiEquipPlanRateListService");
        WorkRptPmiEquipPlanRateCommonDTO workRptPmiEquipPlanRateCommonDTO = workRptPmiEquipPlanRateListForm.getWorkRptPmiEquipPlanRateCommonDTO();
      
        //Paging
        workRptPmiEquipPlanRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmiEquipPlanRateListForm.getIsLoadMaxCount())?true:false);
        workRptPmiEquipPlanRateCommonDTO.setFirstRow(workRptPmiEquipPlanRateListForm.getFirstRow());
        workRptPmiEquipPlanRateCommonDTO.setOrderBy(workRptPmiEquipPlanRateListForm.getOrderBy());
        workRptPmiEquipPlanRateCommonDTO.setDirection(workRptPmiEquipPlanRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmiEquipPlanRateListService.findList(workRptPmiEquipPlanRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiEquipPlanRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiEquipPlanRateListService.findTotalCount(workRptPmiEquipPlanRateCommonDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,workRptPmiEquipPlanRateListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
}