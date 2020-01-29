package dream.invt.list.action;


import java.io.IOException;
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
import dream.invt.list.form.InvtPrcListForm;
import dream.invt.list.service.InvtPrcListService;

/**
 * 목록 action
 * @author  kim21017
 * @version $Id: InvtPrcListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/invtPrcList" name="invtPrcListForm"
 *                input="/dream/invt/list/invtPrcList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPrcList" path="/dream/invt/list/invtPrcList.jsp"
 *                        redirect="false"
 */
public class InvtPrcListAction extends AuthAction
{
    /** 조회 */
    public static final int INVT_PRC_LIST_FIND 		= 1001;
    
    public static final int INVT_PRC_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPrcListForm invtPrcListForm = (InvtPrcListForm) form;

        switch (invtPrcListForm.getStrutsAction())
        {
            case InvtPrcListAction.INVT_PRC_LIST_FIND:
                findList(request, invtPrcListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcListAction.INVT_PRC_LIST_DELETE:
                deleteList(request, invtPrcListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcListAction.BASE_SET_HEADER:
                setHeader(request, response, invtPrcListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcListAction.BASE_GRID_EXPORT:
            	findList(request, invtPrcListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtPrcList");
                break;
        }
        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, InvtPrcListForm invtPrcListForm, HttpServletResponse response) {
    	InvtPrcListService invtPrcListService = (InvtPrcListService) getBean("invtPrcListService");        

        String[] deleteRows = invtPrcListForm.getDeleteRows();    // sheet 내역
        
        invtPrcListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtPrcListForm invtPrcListForm) throws IOException
    {
        super.setHeader(request, response, invtPrcListForm.getListId(), invtPrcListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: InvtPrcListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, InvtPrcListForm invtPrcListForm, HttpServletResponse response, boolean excelExport) throws Exception 
    {
    	InvtPrcListService invtPrcListService = (InvtPrcListService) getBean("invtPrcListService");        

    	InvtCommonDTO invtCommonDTO = invtPrcListForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        invtCommonDTO.setIsLoadMaxCount("Y".equals(invtPrcListForm.getIsLoadMaxCount())?true:false);
        invtCommonDTO.setFirstRow(invtPrcListForm.getFirstRow());
        invtCommonDTO.setOrderBy(invtPrcListForm.getOrderBy());
        invtCommonDTO.setDirection(invtPrcListForm.getDirection());
    	
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = invtPrcListService.findList(invtCommonDTO, user);
 
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtPrcListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtPrcListService.findTotalCount(invtCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtPrcListForm.getListId(),invtPrcListForm.getCurrentPageId(), invtPrcListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}
