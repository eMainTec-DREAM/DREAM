package dream.work.rpt.pmiequipcmptrate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptRateCommonDTO;
import dream.work.rpt.pmiequipcmptrate.form.WorkRptPmiEquipCmptRateListForm;
import dream.work.rpt.pmiequipcmptrate.service.WorkRptPmiEquipCmptRateListService;

/**
 * 예방점검설비 실행 비율 목록 - List Action
 * 
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptPmiEquipCmptRateList" name="workRptPmiEquipCmptRateListForm"
 *                input="/dream/work/rpt/pmiequipcmptrate/workRptPmiEquipCmptRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workRptPmiEquipCmptRateList" path="/dream/work/rpt/pmiequipcmptrate/workRptPmiEquipCmptRateList.jsp"
 *                        redirect="false"
 */
public class WorkRptPmiEquipCmptRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiEquipCmptRateListForm workRptPmiEquipCmptRateListForm = (WorkRptPmiEquipCmptRateListForm) form;
        
        switch (workRptPmiEquipCmptRateListForm.getStrutsAction())
        {
            case WorkRptPmiEquipCmptRateListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptPmiEquipCmptRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiEquipCmptRateListAction.LIST_FIND:
                findList(request, response, workRptPmiEquipCmptRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptPmiEquipCmptRateListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptPmiEquipCmptRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workRptPmiEquipCmptRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptPmiEquipCmptRateListForm workRptPmiEquipCmptRateListForm) throws IOException
    {
        super.setHeader(request, response, workRptPmiEquipCmptRateListForm.getListId(), workRptPmiEquipCmptRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPmiEquipCmptRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptPmiEquipCmptRateListForm workRptPmiEquipCmptRateListForm, boolean excelExport) throws Exception
    {
        WorkRptPmiEquipCmptRateListService workRptPmiEquipCmptRateListService = (WorkRptPmiEquipCmptRateListService) getBean("workRptPmiEquipCmptRateListService");
        WorkRptPmiEquipCmptRateCommonDTO workRptPmiEquipCmptRateCommonDTO = workRptPmiEquipCmptRateListForm.getWorkRptPmiEquipCmptRateCommonDTO();
      
        //Paging
        workRptPmiEquipCmptRateCommonDTO.setIsLoadMaxCount("Y".equals(workRptPmiEquipCmptRateListForm.getIsLoadMaxCount())?true:false);
        workRptPmiEquipCmptRateCommonDTO.setFirstRow(workRptPmiEquipCmptRateListForm.getFirstRow());
        workRptPmiEquipCmptRateCommonDTO.setOrderBy(workRptPmiEquipCmptRateListForm.getOrderBy());
        workRptPmiEquipCmptRateCommonDTO.setDirection(workRptPmiEquipCmptRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmiEquipCmptRateListService.findList(workRptPmiEquipCmptRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiEquipCmptRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiEquipCmptRateListService.findTotalCount(workRptPmiEquipCmptRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPmiEquipCmptRateListForm.getListId(),workRptPmiEquipCmptRateListForm.getCurrentPageId(), workRptPmiEquipCmptRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}