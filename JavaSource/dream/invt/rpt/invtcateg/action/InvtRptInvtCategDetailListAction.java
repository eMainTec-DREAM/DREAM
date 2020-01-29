package dream.invt.rpt.invtcateg.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategDetailListForm;
import dream.invt.rpt.invtcateg.service.InvtRptInvtCategDetailListService;

/**
 * 투자구분분석 상세
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/invtRptInvtCategDetailList" name="invtRptInvtCategDetailListForm"
 *                input="/dream/invt/rpt/invtcateg/invtRptInvtCategDetailList.jsp" scope="request"
 *                validate="false"               
 * @struts.action-forward name="invtRptInvtCategDetailList" path="/dream/invt/rpt/invtcateg/invtRptInvtCategDetailList.jsp"
 *                        redirect="false"
 */
public class InvtRptInvtCategDetailListAction extends AuthAction
{
    public static final int DETAIL_LIST_FIND 	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;	
        InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm = (InvtRptInvtCategDetailListForm) form;
        switch (invtRptInvtCategDetailListForm.getStrutsAction())
        {
            case InvtRptInvtCategDetailListAction.DETAIL_LIST_FIND:
                // 페이지 조회
                this.findDetailList(request, response, invtRptInvtCategDetailListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptInvtCategDetailListAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtRptInvtCategDetailListForm.getListId(), invtRptInvtCategDetailListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptInvtCategDetailListAction.BASE_GRID_EXPORT:
                findDetailList(request,response, invtRptInvtCategDetailListForm, true);
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
     * @author youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param invtRptInvtCategDetailListForm
     */
    private void findDetailList(HttpServletRequest request,HttpServletResponse response, InvtRptInvtCategDetailListForm invtRptInvtCategDetailListForm, boolean excelExport) throws Exception
    {
        InvtRptInvtCategDetailListService invtRptInvtCategDetailListService = (InvtRptInvtCategDetailListService) getBean("invtRptInvtCategDetailListService");
        
        List resultList = invtRptInvtCategDetailListService.findDetailList(invtRptInvtCategDetailListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptInvtCategDetailListForm.getListId(),invtRptInvtCategDetailListForm.getCurrentPageId(), invtRptInvtCategDetailListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, invtRptInvtCategDetailListForm.getListId());
    }
}