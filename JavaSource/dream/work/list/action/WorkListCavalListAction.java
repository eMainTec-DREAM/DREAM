package dream.work.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WorkListCavalListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.WorkListCavalListForm;
import dream.work.list.service.WorkListCavalListService;

/**
 * 작업상세  - 검교정 - 측정값 목록
 * @author  kim21017
 * @version $Id: WorkListCavalListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListCavalList" name="workListCavalListForm"
 *                input="/dream/work/list/pmc/workListCavalList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListCavalList" path="/dream/work/list/pmc/workListCavalList.jsp"
 *                        redirect="false"
 */
public class WorkListCavalListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_CAVAL_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WORK_LIST_CAVAL_LIST_DELETE 		= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListCavalListForm workListCavalListForm = (WorkListCavalListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListCavalListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        switch (workListCavalListForm.getStrutsAction())
        {
        
            case WorkListCavalListAction.WORK_LIST_CAVAL_LIST_FIND:
                findCavalList(request,response, workListCavalListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListCavalListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListCavalListForm.getListId(), workListCavalListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListCavalListAction.WORK_LIST_CAVAL_LIST_DELETE:
            	deleteCavalList(request,workListCavalListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListCavalListAction.BASE_GRID_EXPORT:
            	findCavalList(request,response, workListCavalListForm, true);
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
     * @version $Id: WorkListCavalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListCavalListForm
     * @throws Exception
     */
    private void findCavalList(HttpServletRequest request,HttpServletResponse response, WorkListCavalListForm workListCavalListForm, boolean excelExport) throws Exception
    {
        WorkListCavalListService workListCavalListService = (WorkListCavalListService) getBean("workListCavalListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListCavalListForm.getMaWoResultMstrCommonDTO();
        WorkListCavalListDTO workListCavalListDTO = workListCavalListForm.getWorkListCavalListDTO();
        
        //Paging
        workListCavalListDTO.setIsLoadMaxCount("Y".equals(workListCavalListForm.getIsLoadMaxCount())?true:false);
        workListCavalListDTO.setFirstRow(workListCavalListForm.getFirstRow());
        workListCavalListDTO.setOrderBy(workListCavalListForm.getOrderBy());
        workListCavalListDTO.setDirection(workListCavalListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListCavalListService.findCavalList(maWoResultMstrCommonDTO, workListCavalListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListCavalListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListCavalListService.findTotalCount(maWoResultMstrCommonDTO, workListCavalListDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListCavalListForm.getListId(),workListCavalListForm.getCurrentPageId(), workListCavalListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkListCavalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListCavalListForm
     * @throws Exception
     */
    private void deleteCavalList(HttpServletRequest request, WorkListCavalListForm workListCavalListForm) throws Exception
    {
    	WorkListCavalListService workListCavalListService = (WorkListCavalListService) getBean("workListCavalListService");
        
    	String[] deleteRows = workListCavalListForm.getDeleteRows();
    
    	workListCavalListService.deleteCavalList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}