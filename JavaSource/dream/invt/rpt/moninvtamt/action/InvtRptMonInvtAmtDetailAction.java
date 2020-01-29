package dream.invt.rpt.moninvtamt.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.rpt.moninvtamt.form.InvtRptMonInvtAmtDetailForm;
import dream.invt.rpt.moninvtamt.service.InvtRptMonInvtAmtDetailService;

/**
 * InvtRptMonInvtAmt Page - Detail Action
 * 
 * @author cjscjs9
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/invtRptMonInvtAmtDetailChart" name="invtRptMonInvtAmtDetailForm"
 *                input="/dream/invt/rpt/moninvtamt/invtRptMonInvtAmtDetailChart.jsp" scope="request"
 *                validate="false"
  * @struts.action-forward name="invtRptMonInvtAmtDetailChart" path="/dream/invt/rpt/moninvtamt/invtRptMonInvtAmtDetailChart.jsp"
 *                        redirect="false"
 */
public class InvtRptMonInvtAmtDetailAction extends AuthAction
{
    public static final int DETAIL_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm = (InvtRptMonInvtAmtDetailForm) form;
        switch (invtRptMonInvtAmtDetailForm.getStrutsAction())
        {
            case InvtRptMonInvtAmtDetailAction.DETAIL_LIST_FIND:
                // 페이지 조회
                this.findDetailList(request, response, invtRptMonInvtAmtDetailForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptMonInvtAmtDetailAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtRptMonInvtAmtDetailForm.getListId(), invtRptMonInvtAmtDetailForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptMonInvtAmtDetailAction.BASE_GRID_EXPORT:
                findDetailList(request,response, invtRptMonInvtAmtDetailForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    /**
     * 조회 
     * @author cjscjs9
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param invtRptMonInvtAmtDetailForm
     */
    private void findDetailList(HttpServletRequest request,HttpServletResponse response, InvtRptMonInvtAmtDetailForm invtRptMonInvtAmtDetailForm, boolean excelExport) throws Exception
    {
        InvtRptMonInvtAmtDetailService invtRptMonInvtAmtDetailService = (InvtRptMonInvtAmtDetailService) getBean("invtRptMonInvtAmtDetailService");
        
        List resultList = invtRptMonInvtAmtDetailService.findDetailList(invtRptMonInvtAmtDetailForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptMonInvtAmtDetailForm.getListId(),invtRptMonInvtAmtDetailForm.getCurrentPageId(), invtRptMonInvtAmtDetailForm.getFileName());
        else super.makeJsonResult(resultList, request, response, invtRptMonInvtAmtDetailForm.getListId());
    }
    
}