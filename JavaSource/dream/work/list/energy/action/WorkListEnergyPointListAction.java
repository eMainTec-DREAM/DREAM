package dream.work.list.energy.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;
import dream.work.list.energy.dto.WorkListEnergyPointListDTO;
import dream.work.list.energy.form.WorkListEnergyPointListForm;
import dream.work.list.energy.service.WorkListEnergyPointListService;

/**
 * 에너지 값 측정항목 목록 Action
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointListAction.java,v 1.0 2015/12/04 09:09:30 sy.yang Exp $
 * @since   1.0
 * @struts:action path="/workListEnergyPointList" name="workListEnergyPointListForm"
 *                input="/dream/work/list/energy/workListEnergyPointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListEnergyPointList" path="/dream/work/list/energy/workListEnergyPointList.jsp"
 *                        redirect="false"
 */
public class WorkListEnergyPointListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_PMU_POINT_LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListEnergyPointListForm workListEnergyPointListForm = (WorkListEnergyPointListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListEnergyPointListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workListEnergyPointListForm.getWorkListEnergyPointListDTO().getAuditKey()==""?workListEnergyPointListForm.getWorkListEnergyPointListDTO().getAuditKey():workListEnergyPointListForm.getWorkListEnergyPointListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListEnergyPointListForm.getStrutsAction())
        {
        
            case WorkListEnergyPointListAction.WORK_PMU_POINT_LIST_FIND:
                findPointList(request,response, workListEnergyPointListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListEnergyPointListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListEnergyPointListForm.getListId(), workListEnergyPointListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListEnergyPointListAction.BASE_GRID_EXPORT:
            	findPointList(request,response, workListEnergyPointListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
	/**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyPointListAction.java,v 1.0 2015/12/02 09:10:21 sy.yang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListEnergyPointListForm
     * @throws Exception
     */
    private void findPointList(HttpServletRequest request,HttpServletResponse response, WorkListEnergyPointListForm workListEnergyPointListForm, boolean excelExport) throws Exception
    {
        WorkListEnergyPointListService workListEnergyPointListService = (WorkListEnergyPointListService) getBean("workListEnergyPointListService");
        WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO = workListEnergyPointListForm.getWorkListEnergyMstrListCommonDTO();
        WorkListEnergyPointListDTO workListEnergyPointListDTO = workListEnergyPointListForm.getWorkListEnergyPointListDTO();
        
        //Paging
        workListEnergyMstrListCommonDTO.setIsLoadMaxCount("Y".equals(workListEnergyPointListForm.getIsLoadMaxCount())?true:false);
        workListEnergyMstrListCommonDTO.setFirstRow(workListEnergyPointListForm.getFirstRow());
        workListEnergyMstrListCommonDTO.setOrderBy(workListEnergyPointListForm.getOrderBy());
        workListEnergyMstrListCommonDTO.setDirection(workListEnergyPointListForm.getDirection());

        User user = getUser(request);
        List resultList = workListEnergyPointListService.findPointList(workListEnergyMstrListCommonDTO,workListEnergyPointListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListEnergyPointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListEnergyPointListService.findTotalCount(workListEnergyMstrListCommonDTO,workListEnergyPointListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListEnergyPointListForm.getListId(),workListEnergyPointListForm.getCurrentPageId(), workListEnergyPointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}