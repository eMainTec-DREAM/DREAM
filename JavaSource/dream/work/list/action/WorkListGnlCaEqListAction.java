package dream.work.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.WorkListGnlCaEqListForm;
import dream.work.list.service.WorkListGnlCaEqListService;

/**
 * 작업상세  - 검교정 - 표준기 목록
 * @author  kim21017
 * @version $Id: WorkListGnlCaEqListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListGnlCaEqList" name="workListGnlCaEqListForm"
 *                input="/dream/work/list/pmc/workListGnlCaEqList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListSclCaEqList" name="workListGnlCaEqListForm"
 *                input="/dream/work/list/pmc/workListSclCaEqList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListPrsCaEqList" name="workListGnlCaEqListForm"
 *                input="/dream/work/list/pmc/workListPrsCaEqList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListGnlCaEqList" path="/dream/work/list/pmc/workListGnlCaEqList.jsp"
 *                        redirect="false"
 */
public class WorkListGnlCaEqListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_GNLCAEQ_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_LIST_GNLCAEQ_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListGnlCaEqListForm workListGnlCaEqListForm = (WorkListGnlCaEqListForm) form;
        
        super.updateAudit(workListGnlCaEqListForm.getMaWoResultMstrCommonDTO().getAuditKey()==""?workListGnlCaEqListForm.getMaWoResultMstrCommonDTO().getAuditKey():workListGnlCaEqListForm.getMaWoResultMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListGnlCaEqListForm.getStrutsAction())
        {
        
            case WorkListGnlCaEqListAction.WORK_LIST_GNLCAEQ_LIST_FIND:
                findCavalList(request,response, workListGnlCaEqListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListGnlCaEqListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListGnlCaEqListForm.getListId(), workListGnlCaEqListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListGnlCaEqListAction.WORK_LIST_GNLCAEQ_LIST_DELETE:
            	deleteCavalList(request,workListGnlCaEqListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCaEqListAction.BASE_GRID_EXPORT:
            	findCavalList(request,response, workListGnlCaEqListForm, true);
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
     * @version $Id: WorkListGnlCaEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListGnlCaEqListForm
     * @throws Exception
     */
    private void findCavalList(HttpServletRequest request,HttpServletResponse response, WorkListGnlCaEqListForm workListGnlCaEqListForm, boolean excelExport) throws Exception
    {
        WorkListGnlCaEqListService workListGnlCaEqListService = (WorkListGnlCaEqListService) getBean("workListGnlCaEqListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCaEqListForm.getMaWoResultMstrCommonDTO();
        
        //Paging
        maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(workListGnlCaEqListForm.getIsLoadMaxCount())?true:false);
        maWoResultMstrCommonDTO.setFirstRow(workListGnlCaEqListForm.getFirstRow());
        maWoResultMstrCommonDTO.setOrderBy(workListGnlCaEqListForm.getOrderBy());
        maWoResultMstrCommonDTO.setDirection(workListGnlCaEqListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListGnlCaEqListService.findCavalList(maWoResultMstrCommonDTO,user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListGnlCaEqListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListGnlCaEqListService.findTotalCount(maWoResultMstrCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListGnlCaEqListForm.getListId(),workListGnlCaEqListForm.getCurrentPageId(), workListGnlCaEqListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkListGnlCaEqListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListGnlCaEqListForm
     * @throws Exception
     */
    private void deleteCavalList(HttpServletRequest request, WorkListGnlCaEqListForm workListGnlCaEqListForm) throws Exception
    {
    	WorkListGnlCaEqListService workListGnlCaEqListService = (WorkListGnlCaEqListService) getBean("workListGnlCaEqListService");
        
    	String[] deleteRows = workListGnlCaEqListForm.getDeleteRows();
    
    	workListGnlCaEqListService.deleteCavalList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}