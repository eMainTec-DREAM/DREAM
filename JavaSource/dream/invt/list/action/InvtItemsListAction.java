package dream.invt.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.form.InvtItemsListForm;
import dream.invt.list.service.InvtItemsListService;


/**
 * 목록
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @struts:action path="/invtItemsList" name="invtItemsListForm"
 *                input="/dream/invt/list/invtItemsList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtItemsList" path="/dream/invt/list/invtItemsList.jsp"
 *                        redirect="false"
 */
public class InvtItemsListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtItemsListForm invtItemsListForm = (InvtItemsListForm) form;
        
        switch (invtItemsListForm.getStrutsAction())
        {
            case InvtListAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtItemsListForm.getListId(), invtItemsListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtItemsListAction.LIST_FIND:
                findList(request, response, invtItemsListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtItemsListAction.LIST_DELETE:
            	deleteList(request,invtItemsListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtItemsListAction.BASE_GRID_EXPORT:
            	findList(request,response, invtItemsListForm, true);
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
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param invtItemsListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtItemsListForm invtItemsListForm, boolean excelExport) throws Exception
    {
        InvtItemsListService invtItemsListService = (InvtItemsListService) getBean("invtItemsListService", request);
        InvtCommonDTO invtCommonDTO = invtItemsListForm.getInvtCommonDTO();
        invtCommonDTO.setCompNo(getUser(request).getCompNo());

        //Paging
        invtCommonDTO.setIsLoadMaxCount("Y".equals(invtItemsListForm.getIsLoadMaxCount())?true:false);
        invtCommonDTO.setFirstRow(invtItemsListForm.getFirstRow());
        invtCommonDTO.setOrderBy(invtItemsListForm.getOrderBy());
        invtCommonDTO.setDirection(invtItemsListForm.getDirection());
        
        User user = getUser(request);
        
        List resultList = invtItemsListService.findList(invtCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtItemsListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtItemsListService.findTotalCount(invtCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtItemsListForm.getListId(),invtItemsListForm.getCurrentPageId(), invtItemsListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param request
     * @param invtItemsListForm
     * @throws Exception
     */
    private void deleteList(HttpServletRequest request, InvtItemsListForm invtItemsListForm) throws Exception
    {
    	InvtItemsListService invtItemsListService = (InvtItemsListService) getBean("invtItemsListService", request);
        
    	invtItemsListService.deleteList(invtItemsListForm.getDeleteRows(), getUser(request));
    	
    	setAjaxStatus(request);
    }
}