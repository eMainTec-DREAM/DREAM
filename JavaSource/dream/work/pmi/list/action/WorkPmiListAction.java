package dream.work.pmi.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.form.WorkPmiListForm;
import dream.work.pmi.list.service.WorkPmiListService;

/**
 * 점검작업 - 목록 action
 * @author  kim21017
 * @version $Id: WorkPmiListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/workPmiList" name="workPmiListForm"
 *                input="/dream/work/pmi/list/workPmiList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="workPmiList" path="/dream/work/pmi/list/workPmiList.jsp"
 *                        redirect="false"
 */
public class WorkPmiListAction extends AuthAction
{
    /** 조회 */
    public static final int WORK_PMI_LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int WORK_PMI_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        WorkPmiListForm workPmiListForm = (WorkPmiListForm) form;
        
        super.updateAudit(workPmiListForm.getWorkPmiCommonDTO().getAuditKey()==""?workPmiListForm.getWorkPmiCommonDTO().getAuditKey():workPmiListForm.getWorkPmiCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (workPmiListForm.getStrutsAction())
        {
            case WorkPmiListAction.WORK_PMI_LIST_FIND:
            	findList(request, workPmiListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiListAction.BASE_SET_HEADER:
                setHeader(request, response, workPmiListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case WorkPmiListAction.WORK_PMI_LIST_DELETE:
            	deleteList(request, workPmiListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case WorkPmiListAction.BASE_GRID_EXPORT:
            	findList(request, workPmiListForm,response, true);
            	returnActionForward =new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("workPmiList");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, WorkPmiListForm workPmiListForm) throws IOException
    {
        super.setHeader(request, response, workPmiListForm.getListId(), workPmiListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: WorkPmiListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param workPmiListForm
     * @param excelExport 
     * @throws Exception
     */
    private void findList(HttpServletRequest request, WorkPmiListForm workPmiListForm, HttpServletResponse response, boolean excelExport) throws IOException
    {
    	WorkPmiListService workPmiListService = (WorkPmiListService) getBean("workPmiListService");        

    	WorkPmiCommonDTO workPmiCommonDTO = workPmiListForm.getWorkPmiCommonDTO();
    	workPmiCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	workPmiCommonDTO.setIsLoadMaxCount("Y".equals(workPmiListForm.getIsLoadMaxCount())?true:false);
    	workPmiCommonDTO.setFirstRow(workPmiListForm.getFirstRow());
    	workPmiCommonDTO.setOrderBy(workPmiListForm.getOrderBy());
    	workPmiCommonDTO.setDirection(workPmiListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = workPmiListService.findList(workPmiCommonDTO,getUser(request));
        
        String totalCount = "";
        if(Integer.parseInt(workPmiListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = workPmiListService.findTotalCount(workPmiCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,workPmiListForm.getListId(),workPmiListForm.getCurrentPageId(), workPmiListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: WorkPmiListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param workPmiListForm
     * @param request
     */
    private void deleteList(HttpServletRequest request, WorkPmiListForm workPmiListForm) throws Exception
    {
    	WorkPmiListService workPmiListService = (WorkPmiListService) getBean("workPmiListService");
    	String[] deleteRows = workPmiListForm.getDeleteRows();    // sheet 내역
        
        workPmiListService.deleteList(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
