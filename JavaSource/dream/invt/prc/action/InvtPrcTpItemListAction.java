package dream.invt.prc.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;
import dream.invt.prc.form.InvtPrcTpItemListForm;
import dream.invt.prc.service.InvtPrcTpItemListService;

/**
 * 구매절차 Item  목록
 * @author  hyosung
 * @version $Id: InvtPrcTpItemListAction.java,v 1.0 2015/12/04 09:09:30 hyosung Exp $
 * @since   1.0
 * @struts:action path="/invtPrcTpItemList" name="invtPrcTpItemListForm"
 *                input="/dream/invt/prc/invtPrcTpItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPrcTpItemList" path="/dream/invt/prc/invtPrcTpItemList.jsp"
 *                        redirect="false"
 */
public class InvtPrcTpItemListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int INVT_LIST_FIND 			= 8001;
    /** 삭제 */
    public static final int INVT_LIST_DELETE 		= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPrcTpItemListForm invtPrcTpItemListForm = (InvtPrcTpItemListForm) form;
        
        super.updateAudit(invtPrcTpItemListForm.getInvtPrcTpItemListDTO().getAuditKey()==""?invtPrcTpItemListForm.getInvtPrcTpItemListDTO().getAuditKey():invtPrcTpItemListForm.getInvtPrcTpItemListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPrcTpItemListForm.getStrutsAction())
        {
            case InvtPrcTpListAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtPrcTpItemListForm.getListId(), invtPrcTpItemListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcTpItemListAction.INVT_LIST_FIND:
                findItemList(request, response, invtPrcTpItemListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcTpItemListAction.INVT_LIST_DELETE:
            	deleteItemList(request,invtPrcTpItemListForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtPrcTpItemListAction.BASE_GRID_EXPORT:
            	findItemList(request,response, invtPrcTpItemListForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtPrcTpItemList");
                break;
        }

        return returnActionForward;
    }
    
    /**
     * grid find
     * @author  hyosung
     * @version $Id: InvtPrcTpItemListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcTpItemListForm
     * @throws Exception
     */
    private void findItemList(HttpServletRequest request, HttpServletResponse response, InvtPrcTpItemListForm invtPrcTpItemListForm, boolean excelExport) throws Exception
    {
        InvtPrcTpItemListService invtPrcTpItemListService = (InvtPrcTpItemListService) getBean("invtPrcTpItemListService");
        InvtPrcTpCommonDTO invtPrcTpCommonDTO = invtPrcTpItemListForm.getInvtPrcTpCommonDTO();
        InvtPrcTpItemListDTO invtPrcTpItemListDTO = invtPrcTpItemListForm.getInvtPrcTpItemListDTO();
//        invtPrcTpCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        invtPrcTpItemListDTO.setIsLoadMaxCount("Y".equals(invtPrcTpItemListForm.getIsLoadMaxCount())?true:false);
    	invtPrcTpItemListDTO.setFirstRow(invtPrcTpItemListForm.getFirstRow());
    	invtPrcTpItemListDTO.setOrderBy(invtPrcTpItemListForm.getOrderBy());
    	invtPrcTpItemListDTO.setDirection(invtPrcTpItemListForm.getDirection());
    	    	
        List resultList = invtPrcTpItemListService.findItemList(invtPrcTpCommonDTO, invtPrcTpItemListDTO,getUser(request));
        
        //Paging
        String totalCount = "";                
        if(Integer.parseInt(invtPrcTpItemListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtPrcTpItemListService.findTotalCount(invtPrcTpCommonDTO, invtPrcTpItemListDTO,getUser(request));
                
        if(excelExport) super.makeGridExport(resultList, request, response, invtPrcTpItemListForm.getListId(),invtPrcTpItemListForm.getCurrentPageId(), invtPrcTpItemListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  hyosung
     * @version $Id: InvtPrcTpItemListAction.java,v 1.0 2015/12/02 09:10:21 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcTpItemListForm
     * @throws Exception
     */
    private void deleteItemList(HttpServletRequest request, InvtPrcTpItemListForm invtPrcTpItemListForm) throws Exception
    {
    	InvtPrcTpItemListService invtPrcTpItemListService = (InvtPrcTpItemListService) getBean("invtPrcTpItemListService");
        
    	invtPrcTpItemListService.deleteItemList(invtPrcTpItemListForm.getDeleteRows(),getUser(request));
    	
    	setAjaxStatus(request);
    }
}