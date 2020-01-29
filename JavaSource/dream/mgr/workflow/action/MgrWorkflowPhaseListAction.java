package dream.mgr.workflow.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.mgr.workflow.dto.MgrWorkflowCommonDTO;
import dream.mgr.workflow.dto.MgrWorkflowPhaseListDTO;
import dream.mgr.workflow.form.MgrWorkflowPhaseListForm;
import dream.mgr.workflow.service.MgrWorkflowPhaseListService;

/**
 * Wokrflow Phase Page - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrWorkflowPhaseList" name="mgrWorkflowPhaseListForm"
 *                input="/dream/mgr/workflow/mgrWorkflowPhaseList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkflowPhaseList" path="/dream/mgr/workflow/mgrWorkflowPhaseList.jsp"
 *                        redirect="false"
 */

public class MgrWorkflowPhaseListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrWorkflowPhaseListForm mgrWorkflowPhaseListForm = (MgrWorkflowPhaseListForm) form;
        
        switch (mgrWorkflowPhaseListForm.getStrutsAction())
        {
            case MgrWorkflowPhaseListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrWorkflowPhaseListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrWorkflowPhaseListAction.LIST_FIND:
                findList(request, response, mgrWorkflowPhaseListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrWorkflowPhaseListAction.LIST_DELETE:
            	deleteList(request, mgrWorkflowPhaseListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrWorkflowPhaseListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrWorkflowPhaseListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrWorkflowPhaseList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrWorkflowPhaseListForm mgrWorkflowPhaseListForm) throws IOException
    {
        super.setHeader(request, response, mgrWorkflowPhaseListForm.getListId(), mgrWorkflowPhaseListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrWorkflowPhaseListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrWorkflowPhaseListForm mgrWorkflowPhaseListForm, boolean excelExport) throws Exception
    {
    	MgrWorkflowPhaseListService mgrWorkflowPhaseListService = (MgrWorkflowPhaseListService) getBean("mgrWorkflowPhaseListService");
    	MgrWorkflowPhaseListDTO mgrWorkflowPhaseListDTO = mgrWorkflowPhaseListForm.getMgrWorkflowPhaseListDTO();
    	MgrWorkflowCommonDTO mgrWorkflowCommonDTO = mgrWorkflowPhaseListForm.getMgrWorkflowCommonDTO();

    	//Paging
    	mgrWorkflowPhaseListDTO.setIsLoadMaxCount("Y".equals(mgrWorkflowPhaseListForm.getIsLoadMaxCount())?true:false);
    	mgrWorkflowPhaseListDTO.setFirstRow(mgrWorkflowPhaseListForm.getFirstRow());
    	mgrWorkflowPhaseListDTO.setOrderBy(mgrWorkflowPhaseListForm.getOrderBy());
    	mgrWorkflowPhaseListDTO.setDirection(mgrWorkflowPhaseListForm.getDirection());
    	
    	User user = getUser(request);
    	mgrWorkflowPhaseListDTO.setWflistId(mgrWorkflowCommonDTO.getWflistId());
    	
        List resultList = mgrWorkflowPhaseListService.findWorkflowPhaseList(mgrWorkflowPhaseListDTO, user);
      //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrWorkflowPhaseListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrWorkflowPhaseListService.findTotalCount(mgrWorkflowPhaseListDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrWorkflowPhaseListForm.getListId(),mgrWorkflowPhaseListForm.getCurrentPageId(), mgrWorkflowPhaseListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrWorkflowPhaseListForm
     */
    private void deleteList(HttpServletRequest request, MgrWorkflowPhaseListForm mgrWorkflowPhaseListForm) throws Exception
    {
    	MgrWorkflowPhaseListService mgrWorkflowPhaseListService = (MgrWorkflowPhaseListService) getBean("mgrWorkflowPhaseListService");
        String[] deleteRows = mgrWorkflowPhaseListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrWorkflowPhaseListService.deleteWorkflowPhaseList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
