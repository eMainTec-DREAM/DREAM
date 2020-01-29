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
import dream.work.rpt.pmiequipcmptrate.dto.WorkRptPmiEquipCmptDetailListDTO;
import dream.work.rpt.pmiequipcmptrate.form.WorkRptPmiEquipCmptDetailListForm;
import dream.work.rpt.pmiequipcmptrate.service.WorkRptPmiEquipCmptDetailListService;

/**
 * 예방점검 실행 상세 목록 Action
 * 
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/workRptPmiEquipCmptDetailList" name="workRptPmiEquipCmptDetailListForm"
 *                input="/dream/work/rpt/pmiequipcmptrate/workRptPmiEquipCmptDetailList.jsp" scope="request"
 *                validate="false"
 */
public class WorkRptPmiEquipCmptDetailListAction extends AuthAction
{
    /** 조회 */
    public static final int DETAIL_LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkRptPmiEquipCmptDetailListForm workRptPmiEquipCmptDetailListForm = (WorkRptPmiEquipCmptDetailListForm) form;
        
        switch (workRptPmiEquipCmptDetailListForm.getStrutsAction())
        {
            case WorkRptPmiEquipCmptDetailListAction.BASE_SET_HEADER:
                setHeader(request, response, workRptPmiEquipCmptDetailListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkRptPmiEquipCmptDetailListAction.DETAIL_LIST_FIND:
                findList(request, response, workRptPmiEquipCmptDetailListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case WorkRptPmiEquipCmptDetailListAction.BASE_GRID_EXPORT:
                findList(request, response, workRptPmiEquipCmptDetailListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkRptPmiEquipCmptDetailListForm workRptPmiEquipCmptDetailListForm) throws IOException
    {
        super.setHeader(request, response, workRptPmiEquipCmptDetailListForm.getListId(), workRptPmiEquipCmptDetailListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  sy.yang
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param workRptPmiEquipCmptDetailListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, WorkRptPmiEquipCmptDetailListForm workRptPmiEquipCmptDetailListForm, boolean excelExport) throws Exception
    {
        WorkRptPmiEquipCmptDetailListService workRptPmiEquipCmptDetailListService = (WorkRptPmiEquipCmptDetailListService) getBean("workRptPmiEquipCmptDetailListService");
        WorkRptPmiEquipCmptDetailListDTO workRptPmiEquipCmptDetailListDTO = workRptPmiEquipCmptDetailListForm.getWorkRptPmiEquipCmptDetailListDTO();
      
        //Paging
        workRptPmiEquipCmptDetailListDTO.setIsLoadMaxCount("Y".equals(workRptPmiEquipCmptDetailListForm.getIsLoadMaxCount())?true:false);
        workRptPmiEquipCmptDetailListDTO.setFirstRow(workRptPmiEquipCmptDetailListForm.getFirstRow());
        workRptPmiEquipCmptDetailListDTO.setOrderBy(workRptPmiEquipCmptDetailListForm.getOrderBy());
        workRptPmiEquipCmptDetailListDTO.setDirection(workRptPmiEquipCmptDetailListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workRptPmiEquipCmptDetailListService.findList(workRptPmiEquipCmptDetailListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workRptPmiEquipCmptDetailListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workRptPmiEquipCmptDetailListService.findTotalCount(workRptPmiEquipCmptDetailListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workRptPmiEquipCmptDetailListForm.getListId(),workRptPmiEquipCmptDetailListForm.getCurrentPageId(), workRptPmiEquipCmptDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}