package dream.work.list.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.form.WorkListPtrlResultListForm;
import dream.work.list.service.WorkListPtrlResultListService;

/**
 * 순회점검 작업결과 목록
 * @author  youngjoo38
 * @version $Id: WorkListPtrlResultListAction.java,v 1.0 2017/09/15 10:22:27 youngjoo38 Exp $
 * @since   1.0
 * @struts:action path="/workListPtrlResultList" name="workListPtrlResultListForm"
 *                input="/dream/work/list/workListPtrlResultList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workListPtrlResultList" path="/dream/work/list/workListPtrlResultList.jsp"
 *                        redirect="false"
 */
public class WorkListPtrlResultListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int WORK_LIST_PTRL_RESULT_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_LIST_PTRL_RESULT_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        WorkListPtrlResultListForm workListPtrlResultListForm = (WorkListPtrlResultListForm) form;
        
        switch (workListPtrlResultListForm.getStrutsAction())
        {
        
            case WorkListPtrlResultListAction.WORK_LIST_PTRL_RESULT_LIST_FIND:
                findList(request,response, workListPtrlResultListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListPtrlResultListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, workListPtrlResultListForm.getListId(), workListPtrlResultListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkListPtrlResultListAction.WORK_LIST_PTRL_RESULT_LIST_DELETE:
            	deleteList(request,workListPtrlResultListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case WorkListPtrlResultListAction.BASE_GRID_EXPORT:
            	findList(request,response, workListPtrlResultListForm,true);
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
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListAction.java,v 1.0 2017/09/15 15:26:21 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListPtrlResultListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, WorkListPtrlResultListForm workListPtrlResultListForm, boolean excelExport) throws Exception
    {
        WorkListPtrlResultListService workListPtrlResultListService = (WorkListPtrlResultListService) getBean("workListPtrlResultListService");
        WorkListPtrlResultCommonDTO workListPtrlResultCommonDTO = workListPtrlResultListForm.getWorkListPtrlResultCommonDTO();
        
        //Paging
        workListPtrlResultCommonDTO.setIsLoadMaxCount("Y".equals(workListPtrlResultListForm.getIsLoadMaxCount())?true:false);
        workListPtrlResultCommonDTO.setFirstRow(workListPtrlResultListForm.getFirstRow());
        workListPtrlResultCommonDTO.setOrderBy(workListPtrlResultListForm.getOrderBy());
        workListPtrlResultCommonDTO.setDirection(workListPtrlResultListForm.getDirection());
        
        User user = getUser(request);
        List resultList = workListPtrlResultListService.findList(workListPtrlResultCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(workListPtrlResultListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workListPtrlResultListService.findTotalCount(workListPtrlResultCommonDTO,user);
        
        //super.makeJsonResult(resultList, request, response, workListPtrlResultListForm.getListId());
        if(excelExport) super.makeGridExport(resultList, request, response,workListPtrlResultListForm.getListId(),workListPtrlResultListForm.getCurrentPageId(), workListPtrlResultListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);

    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id: WorkListPtrlResultListAction.java,v 1.0 2017/09/15 15:26:21 youngjoo38 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workListPtrlResultListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, WorkListPtrlResultListForm workListPtrlResultListForm) throws Exception
    {
    	WorkListPtrlResultListService workListPtrlResultListService = (WorkListPtrlResultListService) getBean("workListPtrlResultListService");
        
    	String[] deleteRows = workListPtrlResultListForm.getDeleteRows();
    
    	workListPtrlResultListService.deleteList(deleteRows, getUser(request).getCompNo());
    	
    	setAjaxStatus(request);
    }
}