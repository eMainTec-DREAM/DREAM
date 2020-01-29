package dream.invt.rpt.invtprecon.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.rpt.invtprecon.dto.InvtItemListDTO;
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.form.InvtItemListForm;
import dream.invt.rpt.invtprecon.service.InvtItemListService;

/**
 * InvtItem Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: InvtItemListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/invtItemList" name="invtItemListForm"
 *                input="/dream/invt/rpt/invtprecon/invtItemList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtItemList" path="/dream/invt/rpt/invtprecon/invtItemList.jsp"
 *                        redirect="false"
 */
public class InvtItemListAction extends AuthAction
{
    /** Á¶È¸ */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtItemListForm invtItemListForm = (InvtItemListForm) form;
        
        switch (invtItemListForm.getStrutsAction())
        {
            case InvtItemListAction.BASE_SET_HEADER:
                setHeader(request, response, invtItemListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtItemListAction.LIST_FIND:
                findList(request, response, invtItemListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case InvtItemListAction.BASE_GRID_EXPORT:
                findList(request, response, invtItemListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtItemList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtItemListForm invtItemListForm) throws IOException
    {
        super.setHeader(request, response, invtItemListForm.getListId(), invtItemListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param invtItemListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtItemListForm invtItemListForm, boolean excelExport) throws Exception
    {
        InvtItemListService invtItemListService = (InvtItemListService) getBean("invtItemListService");
        InvtItemListDTO invtItemListDTO = invtItemListForm.getInvtItemListDTO();
        InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO = invtItemListForm.getInvtRptInvtPreConCommonDTO();
      
        //Paging
        invtItemListDTO.setIsLoadMaxCount("Y".equals(invtItemListForm.getIsLoadMaxCount())?true:false);
        invtItemListDTO.setFirstRow(invtItemListForm.getFirstRow());
        invtItemListDTO.setOrderBy(invtItemListForm.getOrderBy());
        invtItemListDTO.setDirection(invtItemListForm.getDirection());
        
        User user = getUser(request);
        List resultList = invtItemListService.findList(invtRptInvtPreConCommonDTO, invtItemListDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtItemListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtItemListService.findTotalCount(invtRptInvtPreConCommonDTO,invtItemListDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtItemListForm.getListId(),invtItemListForm.getCurrentPageId(), invtItemListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}