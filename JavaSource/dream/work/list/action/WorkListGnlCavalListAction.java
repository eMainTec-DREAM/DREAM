package dream.work.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.fins.org.json.JSONObject;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.form.WorkListGnlCavalListForm;
import dream.work.list.service.WorkListGnlCavalListService;

/**
 * 작업상세  - 검교정 - 측정값 목록
 * @author  kim21017
 * @version $Id: WorkListGnlCavalListAction.java,v 1.0 2015/12/04 09:09:30 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workListGnlCavalList" name="workListGnlCavalListForm"
 *                input="/dream/work/list/pmc/workListGnlCavalList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListPrsCavalList" name="workListGnlCavalListForm"
 *                input="/dream/work/list/pmc/workListPrsCavalList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/workListGnlCavalPopup" name="workListGnlCavalListForm"
 *                input="/dream/work/list/pmc/workListGnlCavalPopup.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListGnlCavalList" path="/dream/work/list/pmc/workListGnlCavalList.jsp"
 *                        redirect="false"
 */
public class WorkListGnlCavalListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_GNL_CAVAL_LIST_FIND 			= 1001;
    /** 삭제 */
    public static final int WORK_LIST_GNL_CAVAL_LIST_DELETE 		= 7002;
    /** 복사 */
    public static final int WORK_LIST_GNL_CAVAL_LIST_COPY 			= 1003;
    /** 일괄등록 저장 */
    public static final int WORK_LIST_GNL_BATCH_SAVE	 			= 6004;
    /** 목록 저장 */
    public static final int WORK_LIST_GNL_SAVE	 					= 1005;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListGnlCavalListForm workListGnlCavalListForm = (WorkListGnlCavalListForm) form;
        String strutsAction = request.getParameter("strutsAction");
        if(strutsAction!="") workListGnlCavalListForm.setStrutsAction(Integer.parseInt(strutsAction));
        
        super.updateAudit(workListGnlCavalListForm.getMaWoResultMstrCommonDTO().getAuditKey()==""?workListGnlCavalListForm.getMaWoResultMstrCommonDTO().getAuditKey():workListGnlCavalListForm.getMaWoResultMstrCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workListGnlCavalListForm.getStrutsAction())
        {
        
            case WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_FIND:
                findCavalList(request,response, workListGnlCavalListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListGnlCavalListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListGnlCavalListForm.getListId(), workListGnlCavalListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_DELETE:
            	deleteCavalList(request,workListGnlCavalListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCavalListAction.WORK_LIST_GNL_CAVAL_LIST_COPY:
            	copyCavalList(request,workListGnlCavalListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCavalListAction.WORK_LIST_GNL_BATCH_SAVE:
            	batchCavalList(request,workListGnlCavalListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListGnlCavalListAction.WORK_LIST_GNL_SAVE:
            	saveList(request,response,workListGnlCavalListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            case WorkListGnlCavalListAction.BASE_GRID_EXPORT:
            	findCavalList(request,response, workListGnlCavalListForm, true);
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
     * @version $Id: WorkListGnlCavalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListGnlCavalListForm
     * @throws Exception
     */
    private void findCavalList(HttpServletRequest request,HttpServletResponse response, WorkListGnlCavalListForm workListGnlCavalListForm, boolean excelExport) throws Exception
    {
        WorkListGnlCavalListService workListGnlCavalListService = (WorkListGnlCavalListService) getBean("workListGnlCavalListService");
        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCavalListForm.getMaWoResultMstrCommonDTO();
        
        //Paging
        maWoResultMstrCommonDTO.setIsLoadMaxCount("Y".equals(workListGnlCavalListForm.getIsLoadMaxCount())?true:false);
        maWoResultMstrCommonDTO.setFirstRow(workListGnlCavalListForm.getFirstRow());
        maWoResultMstrCommonDTO.setOrderBy(workListGnlCavalListForm.getOrderBy());
        maWoResultMstrCommonDTO.setDirection(workListGnlCavalListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListGnlCavalListService.findCavalList(maWoResultMstrCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListGnlCavalListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListGnlCavalListService.findTotalCount(maWoResultMstrCommonDTO, user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,workListGnlCavalListForm.getListId(),workListGnlCavalListForm.getCurrentPageId(), workListGnlCavalListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim2107
     * @version $Id: WorkListGnlCavalListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListGnlCavalListForm
     * @throws Exception
     */
    private void deleteCavalList(HttpServletRequest request, WorkListGnlCavalListForm workListGnlCavalListForm) throws Exception
    {
    	WorkListGnlCavalListService workListGnlCavalListService = (WorkListGnlCavalListService) getBean("workListGnlCavalListService");
        
    	String[] deleteRows = workListGnlCavalListForm.getDeleteRows();
    
    	workListGnlCavalListService.deleteCavalList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
    private void copyCavalList(HttpServletRequest request, WorkListGnlCavalListForm workListGnlCavalListForm) throws Exception
    {
    	WorkListGnlCavalListService workListGnlCavalListService = (WorkListGnlCavalListService) getBean("workListGnlCavalListService");
        
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCavalListForm.getMaWoResultMstrCommonDTO();
        workListGnlCavalListService.copyCavalList(maWoResultMstrCommonDTO, getUser(request));
        
        setAjaxStatus(request);
    }
    
    private void batchCavalList(HttpServletRequest request, WorkListGnlCavalListForm workListGnlCavalListForm) throws Exception
    {
    	WorkListGnlCavalListService workListGnlCavalListService = (WorkListGnlCavalListService) getBean("workListGnlCavalListService");
        
    	MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = workListGnlCavalListForm.getMaWoResultMstrCommonDTO();
    	String jsonStr = workListGnlCavalListForm.getAllArray();
        workListGnlCavalListService.batchCavalList(maWoResultMstrCommonDTO, getUser(request),jsonStr);
        
        setAjaxStatus(request);
    }
    
    private void saveList(HttpServletRequest request, HttpServletResponse response, WorkListGnlCavalListForm workListGnlCavalListForm) throws Exception
    {
    	WorkListGnlCavalListService workListGnlCavalListService = (WorkListGnlCavalListService) getBean("workListGnlCavalListService");

    	 List<Map> gridList = CommonUtil.setGridMap(request);

    	 workListGnlCavalListService.saveList(gridList, getUser(request));
         
         JSONObject jsonObj = new JSONObject();
         jsonObj.put("status", "ok");
         response.getWriter().print(jsonObj.toString());
    }
}