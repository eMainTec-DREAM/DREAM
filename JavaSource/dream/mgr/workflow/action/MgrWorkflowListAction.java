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
import dream.mgr.workflow.form.MgrWorkflowListForm;
import dream.mgr.workflow.service.MgrWorkflowListService;

/**
 * Wokrflow Page - List Action
 * 
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/mgrWorkflowList" name="mgrWorkflowListForm"
 *                input="/dream/mgr/workflow/mgrWorkflowList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="mgrWorkflowList" path="/dream/mgr/workflow/mgrWorkflowList.jsp"
 *                        redirect="false"
 */

public class MgrWorkflowListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        MgrWorkflowListForm mgrWorkflowListForm = (MgrWorkflowListForm) form;
        
        switch (mgrWorkflowListForm.getStrutsAction())
        {
            case MgrWorkflowListAction.BASE_SET_HEADER:
                setHeader(request, response, mgrWorkflowListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case MgrWorkflowListAction.LIST_FIND:
                findList(request, response, mgrWorkflowListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case MgrWorkflowListAction.LIST_DELETE:
            	deleteList(request, mgrWorkflowListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case MgrWorkflowListAction.BASE_GRID_EXPORT:
            	findList(request, response, mgrWorkflowListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("mgrWorkflowList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, MgrWorkflowListForm mgrWorkflowListForm) throws IOException
    {
        super.setHeader(request, response, mgrWorkflowListForm.getListId(), mgrWorkflowListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param mgrWorkflowListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, MgrWorkflowListForm mgrWorkflowListForm, boolean excelExport) throws Exception
    {
    	MgrWorkflowListService mgrWorkflowListService = (MgrWorkflowListService) getBean("mgrWorkflowListService");
    	MgrWorkflowCommonDTO mgrWorkflowCommonDTO = mgrWorkflowListForm.getMgrWorkflowCommonDTO();

    	//Paging
    	mgrWorkflowCommonDTO.setIsLoadMaxCount("Y".equals(mgrWorkflowListForm.getIsLoadMaxCount())?true:false);
    	mgrWorkflowCommonDTO.setFirstRow(mgrWorkflowListForm.getFirstRow());
    	mgrWorkflowCommonDTO.setOrderBy(mgrWorkflowListForm.getOrderBy());
    	mgrWorkflowCommonDTO.setDirection(mgrWorkflowListForm.getDirection());
    	
    	User user = getUser(request);
    	mgrWorkflowCommonDTO.setCompNo(user.getCompNo());
    	
        List resultList = mgrWorkflowListService.findWorkflowList(mgrWorkflowCommonDTO, user);
      //Paging
        String totalCount = "";
        if(Integer.parseInt(mgrWorkflowListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = mgrWorkflowListService.findTotalCount(mgrWorkflowCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,mgrWorkflowListForm.getListId(),mgrWorkflowListForm.getCurrentPageId(), mgrWorkflowListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  ghlee
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param mgrWorkflowListForm
     */
    private void deleteList(HttpServletRequest request, MgrWorkflowListForm mgrWorkflowListForm) throws Exception
    {
    	MgrWorkflowListService mgrWorkflowListService = (MgrWorkflowListService) getBean("mgrWorkflowListService");
        String[] deleteRows = mgrWorkflowListForm.getDeleteRows();
        
    	User user = getUser(request);
        
        mgrWorkflowListService.deleteWorkflowList(deleteRows, user);
        setAjaxStatus(request);
    }
    
}
