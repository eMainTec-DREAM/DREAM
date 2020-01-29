package dream.work.let.permit.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.let.permit.dto.WorkLetPermitCraftListDTO;
import dream.work.let.permit.dto.WorkLetPermitDetailDTO;
import dream.work.let.permit.form.WorkLetPermitCraftListForm;
import dream.work.let.permit.service.WorkLetPermitCraftListService;

/**
 * 안전작업허가서유형 - 작업자 목록 action
 * @author  syyang
 * @version $Id: WorkLetPermitCraftListAction.java,v 1.0 2015/12/04 09:09:30 syyang Exp $
 * @since   1.0
 * @struts:action path="/workLetPermitCraftList" name="workLetPermitCraftListForm"
 *                input="/dream/work/let/permit/workLetPermitCraftList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetPermitCraftList" path="/dream/work/let/permit/workLetPermitCraftList.jsp"
 *                        redirect="false"
 */
public class WorkLetPermitCraftListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WO_LET_PERMIT_CRAFT_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WO_LET_PERMIT_CRAFT_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetPermitCraftListForm workLetPermitCraftListForm = (WorkLetPermitCraftListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workLetPermitCraftListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (workLetPermitCraftListForm.getStrutsAction())
        {
        
            case WorkLetPermitCraftListAction.WO_LET_PERMIT_CRAFT_LIST_FIND:
                findCraftList(request,response, workLetPermitCraftListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetPermitCraftListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workLetPermitCraftListForm.getListId(), workLetPermitCraftListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetPermitCraftListAction.WO_LET_PERMIT_CRAFT_LIST_DELETE:
            	deleteCraftList(request,workLetPermitCraftListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkLetPermitCraftListAction.BASE_GRID_EXPORT:
            	findCraftList(request,response, workLetPermitCraftListForm, true);
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
     * @author  kim2107
     * @version $Id: WorkLetPermitCraftListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitCraftListForm
     * @throws Exception
     */
    private void findCraftList(HttpServletRequest request,HttpServletResponse response, WorkLetPermitCraftListForm workLetPermitCraftListForm, boolean excelExport) throws Exception
    {
        WorkLetPermitCraftListService workLetPermitCraftListService = (WorkLetPermitCraftListService) getBean("workLetPermitCraftListService");

        WorkLetPermitDetailDTO workLetPermitDetailDTO = workLetPermitCraftListForm.getWorkLetPermitDetailDTO();
        WorkLetPermitCraftListDTO workLetPermitCraftListDTO = workLetPermitCraftListForm.getWorkLetPermitCraftListDTO();
        
        //Paging
        workLetPermitCraftListDTO.setIsLoadMaxCount("Y".equals(workLetPermitCraftListForm.getIsLoadMaxCount())?true:false);
        workLetPermitCraftListDTO.setFirstRow(workLetPermitCraftListForm.getFirstRow());
        workLetPermitCraftListDTO.setOrderBy(workLetPermitCraftListForm.getOrderBy());
        workLetPermitCraftListDTO.setDirection(workLetPermitCraftListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workLetPermitCraftListService.findCraftList(workLetPermitDetailDTO, workLetPermitCraftListDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workLetPermitCraftListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetPermitCraftListService.findTotalCount(workLetPermitDetailDTO,workLetPermitCraftListDTO,user);
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response, workLetPermitCraftListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkLetPermitCraftListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workLetPermitCraftListForm
     * @throws Exception
     */
    private void deleteCraftList(HttpServletRequest request, WorkLetPermitCraftListForm workLetPermitCraftListForm) throws Exception
    {
    	WorkLetPermitCraftListService workLetPermitCraftListService = (WorkLetPermitCraftListService) getBean("workLetPermitCraftListService");
        
    	String[] deleteRows = workLetPermitCraftListForm.getDeleteRows();
    
    	workLetPermitCraftListService.deleteCraftList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}