package dream.work.let.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.let.dto.WorkLetCommonDTO;
import dream.work.let.form.WorkLetListForm;
import dream.work.let.service.WorkLetListService;

/**
 * 작업결과 - 목록 action
 * @author  syyang
 * @version $Id: WorkLetListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
 * @since   1.0
 * @struts:action path="/workLetList" name="workLetListForm"
 *                input="/dream/work/let/workLetList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workLetList" path="/dream/work/let/workLetList.jsp"
 *                        redirect="false"
 */
public class WorkLetListAction extends AuthAction
{
    /** 조회 */
    public static final int WO_LET_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WO_LET_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkLetListForm workLetListForm = (WorkLetListForm) form;
        
        switch (workLetListForm.getStrutsAction())
        {
            case WorkLetListAction.WO_LET_LIST_FIND:
            	findWoLetList(request, workLetListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetListAction.WO_LET_LIST_DELETE:
            	deleteWoLet(request, workLetListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkLetListAction.BASE_SET_HEADER:
                setHeader(request, response, workLetListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkLetListAction.BASE_GRID_EXPORT:
            	findWoLetList(request, workLetListForm, response, true);
            	returnActionForward =new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkLetListForm workLetListForm) throws IOException
    {
        super.setHeader(request, response, workLetListForm.getListId(), workLetListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkLetListAction.java,v 1.0 2015/12/02 09:10:21 syyang Exp $
     * @since   1.0
     * 
     * @param request
     * @param workLetListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findWoLetList(HttpServletRequest request, WorkLetListForm workLetListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkLetListService workLetListService = (WorkLetListService) getBean("workLetListService");        

    	WorkLetCommonDTO workLetCommonDTO = workLetListForm.getWorkLetCommonDTO();
    	
    	workLetCommonDTO.setIsLoadMaxCount("Y".equals(workLetListForm.getIsLoadMaxCount())?true:false);
    	workLetCommonDTO.setFirstRow(workLetListForm.getFirstRow());
    	workLetCommonDTO.setOrderBy(workLetListForm.getOrderBy());
    	workLetCommonDTO.setDirection(workLetListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workLetListService.findWoLetList(workLetCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(workLetListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workLetListService.findTotalCount(workLetCommonDTO,getUser(request),"");

        if(excelExport) super.makeGridExport(resultList, request, response,workLetListForm.getListId(),workLetListForm.getCurrentPageId(), workLetListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * delete
     * @author  syyang
     * @version $Id: WorkLetListAction.java,v 1.2 2015/12/02 01:21:30 syyang Exp $
     * @since   1.0
     * 
     * @param workLetListForm
     * @param request
     */
    private void deleteWoLet(HttpServletRequest request, WorkLetListForm workLetListForm) throws Exception
    {
    	WorkLetListService workLetListService = (WorkLetListService) getBean("workLetListService");
    	String[] deleteRows = workLetListForm.getDeleteRows();    // sheet 내역
        
        workLetListService.deleteWoLet(deleteRows, getUser(request));
        
        setAjaxStatus(request);
    }
    
}
