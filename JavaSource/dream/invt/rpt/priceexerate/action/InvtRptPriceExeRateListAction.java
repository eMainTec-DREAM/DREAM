package dream.invt.rpt.priceexerate.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import dream.invt.rpt.priceexerate.dto.InvtRptPriceExeRateCommonDTO;
import dream.invt.rpt.priceexerate.form.InvtRptPriceExeRateListForm;
import dream.invt.rpt.priceexerate.service.InvtRptPriceExeRateListService;

/**
 * 투자비 집행현황 목록 - List Action
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @struts:action path="/invtRptPriceExeRateList" name="invtRptPriceExeRateListForm"
 *                input="/dream/invt/rpt/priceexerate/invtRptPriceExeRateList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtRptPriceExeRateList" path="/dream/invt/rpt/priceexerate/invtRptPriceExeRateList.jsp"
 *                        redirect="false"
 */
public class InvtRptPriceExeRateListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptPriceExeRateListForm invtRptPriceExeRateListForm = (InvtRptPriceExeRateListForm) form;
        
        switch (invtRptPriceExeRateListForm.getStrutsAction())
        {
            case InvtRptPriceExeRateListAction.BASE_SET_HEADER:
                setHeader(request, response, invtRptPriceExeRateListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptPriceExeRateListAction.LIST_FIND:
                findList(request, response, invtRptPriceExeRateListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case InvtRptPriceExeRateListAction.BASE_GRID_EXPORT:
                findList(request, response, invtRptPriceExeRateListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtRptPriceExeRateList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtRptPriceExeRateListForm invtRptPriceExeRateListForm) throws IOException
    {
        super.setHeader(request, response, invtRptPriceExeRateListForm.getListId(), invtRptPriceExeRateListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  cjscjs9
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param invtRptPriceExeRateListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtRptPriceExeRateListForm invtRptPriceExeRateListForm, boolean excelExport) throws Exception
    {
        InvtRptPriceExeRateListService invtRptPriceExeRateListService = (InvtRptPriceExeRateListService) getBean("invtRptPriceExeRateListService");
        InvtRptPriceExeRateCommonDTO invtRptPriceExeRateCommonDTO = invtRptPriceExeRateListForm.getInvtRptPriceExeRateCommonDTO();
      
        //Paging
        invtRptPriceExeRateCommonDTO.setIsLoadMaxCount("Y".equals(invtRptPriceExeRateListForm.getIsLoadMaxCount())?true:false);
        invtRptPriceExeRateCommonDTO.setFirstRow(invtRptPriceExeRateListForm.getFirstRow());
        invtRptPriceExeRateCommonDTO.setOrderBy(invtRptPriceExeRateListForm.getOrderBy());
        invtRptPriceExeRateCommonDTO.setDirection(invtRptPriceExeRateListForm.getDirection());
        
        User user = getUser(request);
        List resultList = invtRptPriceExeRateListService.findList(invtRptPriceExeRateCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtRptPriceExeRateListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtRptPriceExeRateListService.findTotalCount(invtRptPriceExeRateCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptPriceExeRateListForm.getListId(),invtRptPriceExeRateListForm.getCurrentPageId(), invtRptPriceExeRateListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}