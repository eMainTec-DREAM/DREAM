package dream.invt.rpt.moninvtamt.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.rpt.moninvtamt.dto.InvtRptMonInvtAmtCommonDTO;
import dream.invt.rpt.moninvtamt.form.InvtRptMonInvtAmtListForm;
import dream.invt.rpt.moninvtamt.service.InvtRptMonInvtAmtListService;

/**
 * InvtRptMonInvtAmt Page - List Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/invtRptMonInvtAmtList" name="invtRptMonInvtAmtListForm"
 *                input="/dream/invt/rpt/moninvtamt/invtRptMonInvtAmtList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtRptMonInvtAmtList" path="/dream/invt/rpt/moninvtamt/invtRptMonInvtAmtList.jsp"
 *                        redirect="false"
 */
public class InvtRptMonInvtAmtListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptMonInvtAmtListForm invtRptMonInvtAmtListForm = (InvtRptMonInvtAmtListForm) form;
        
        switch (invtRptMonInvtAmtListForm.getStrutsAction())
        {
            case InvtRptMonInvtAmtListAction.BASE_SET_HEADER:
                setHeader(request, response, invtRptMonInvtAmtListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptMonInvtAmtListAction.LIST_FIND:
                findList(request, response, invtRptMonInvtAmtListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case InvtRptMonInvtAmtListAction.BASE_GRID_EXPORT:
                findList(request, response, invtRptMonInvtAmtListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtRptMonInvtAmtList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtRptMonInvtAmtListForm invtRptMonInvtAmtListForm) throws IOException
    {
        super.setHeader(request, response, invtRptMonInvtAmtListForm.getListId(), invtRptMonInvtAmtListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param invtRptMonInvtAmtListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtRptMonInvtAmtListForm invtRptMonInvtAmtListForm, boolean excelExport) throws Exception
    {
        InvtRptMonInvtAmtListService invtRptMonInvtAmtListService = (InvtRptMonInvtAmtListService) getBean("invtRptMonInvtAmtListService");
        InvtRptMonInvtAmtCommonDTO invtRptMonInvtAmtCommonDTO = invtRptMonInvtAmtListForm.getInvtRptMonInvtAmtCommonDTO();
      
        //Paging
        invtRptMonInvtAmtCommonDTO.setIsLoadMaxCount("Y".equals(invtRptMonInvtAmtListForm.getIsLoadMaxCount())?true:false);
        invtRptMonInvtAmtCommonDTO.setFirstRow(invtRptMonInvtAmtListForm.getFirstRow());
        invtRptMonInvtAmtCommonDTO.setOrderBy(invtRptMonInvtAmtListForm.getOrderBy());
        invtRptMonInvtAmtCommonDTO.setDirection(invtRptMonInvtAmtListForm.getDirection());
        
        User user = getUser(request);
        List resultList = invtRptMonInvtAmtListService.findList(invtRptMonInvtAmtCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtRptMonInvtAmtListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtRptMonInvtAmtListService.findTotalCount(invtRptMonInvtAmtCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptMonInvtAmtListForm.getListId(),invtRptMonInvtAmtListForm.getCurrentPageId(), invtRptMonInvtAmtListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}