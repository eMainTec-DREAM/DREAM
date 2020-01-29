package dream.invt.rpt.invtcateg.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.rpt.invtcateg.form.InvtRptInvtCategListForm;
import dream.invt.rpt.invtcateg.service.InvtRptInvtCategListService;

/**
 * 투자구분분석
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @struts:action path="/invtRptInvtCategList" name="invtRptInvtCategListForm"
 *                input="/dream/invt/rpt/invtcateg/invtRptInvtCategList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtRptInvtCategList" path="/dream/invt/rpt/invtcateg/invtRptInvtCategList.jsp"
 *                        redirect="false"
 */
public class InvtRptInvtCategListAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 			= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptInvtCategListForm invtRptInvtCategListForm = (InvtRptInvtCategListForm) form;
        
        switch (invtRptInvtCategListForm.getStrutsAction())
        {
        
            case InvtRptInvtCategListAction.LIST_FIND:
                findList(request,response, invtRptInvtCategListForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptInvtCategListAction.BASE_SET_HEADER:
            	super.setHeader(request, response, invtRptInvtCategListForm.getListId(), invtRptInvtCategListForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptInvtCategListAction.BASE_GRID_EXPORT:
            	findList(request,response, invtRptInvtCategListForm, true);
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
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param invtRptInvtCategListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request,HttpServletResponse response, InvtRptInvtCategListForm invtRptInvtCategListForm, boolean excelExport) throws Exception
    {
        InvtRptInvtCategListService invtRptInvtCategListService = (InvtRptInvtCategListService) getBean("invtRptInvtCategListService");
        
        List resultList = invtRptInvtCategListService.findList(invtRptInvtCategListForm, getUser(request));
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptInvtCategListForm.getListId(),invtRptInvtCategListForm.getCurrentPageId(), invtRptInvtCategListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, invtRptInvtCategListForm.getListId());
    }
}