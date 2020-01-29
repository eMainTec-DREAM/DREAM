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
import dream.invt.rpt.invtprecon.dto.InvtRptInvtPreConCommonDTO;
import dream.invt.rpt.invtprecon.form.InvtRptInvtPreConListForm;
import dream.invt.rpt.invtprecon.service.InvtRptInvtPreConListService;

/**
 * InvtRptInvtPreCon Page - List Action
 * 
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConListAction.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 * @struts:action path="/invtRptInvtPreConList" name="invtRptInvtPreConListForm"
 *                input="/dream/invt/rpt/invtprecon/invtRptInvtPreConList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtRptInvtPreConList" path="/dream/invt/rpt/invtprecon/invtRptInvtPreConList.jsp"
 *                        redirect="false"
 */
public class InvtRptInvtPreConListAction extends AuthAction
{
    
  //일반 페이지 적용시 AuthAction 으로 변경해야합니다.
    /** 조회 */
    public static final int LIST_FIND       = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtRptInvtPreConListForm invtRptInvtPreConListForm = (InvtRptInvtPreConListForm) form;
        
        switch (invtRptInvtPreConListForm.getStrutsAction())
        {
            case InvtRptInvtPreConListAction.BASE_SET_HEADER:
                setHeader(request, response, invtRptInvtPreConListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtRptInvtPreConListAction.LIST_FIND:
                findList(request, response, invtRptInvtPreConListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case InvtRptInvtPreConListAction.BASE_GRID_EXPORT:
                findList(request, response, invtRptInvtPreConListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtRptInvtPreConList");
                break;
        }
        return returnActionForward;    
}
    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtRptInvtPreConListForm invtRptInvtPreConListForm) throws IOException
    {
        super.setHeader(request, response, invtRptInvtPreConListForm.getListId(), invtRptInvtPreConListForm.getCurrentPageId()); 
    }
   
    /**
     * FIND LIST
     * @author  youngjoo38
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param invtRptInvtPreConListForm
     */
    
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtRptInvtPreConListForm invtRptInvtPreConListForm, boolean excelExport) throws Exception
    {
        InvtRptInvtPreConListService invtRptInvtPreConListService = (InvtRptInvtPreConListService) getBean("invtRptInvtPreConListService");
        InvtRptInvtPreConCommonDTO invtRptInvtPreConCommonDTO = invtRptInvtPreConListForm.getInvtRptInvtPreConCommonDTO();
      
        //Paging
        invtRptInvtPreConCommonDTO.setIsLoadMaxCount("Y".equals(invtRptInvtPreConListForm.getIsLoadMaxCount())?true:false);
        invtRptInvtPreConCommonDTO.setFirstRow(invtRptInvtPreConListForm.getFirstRow());
        invtRptInvtPreConCommonDTO.setOrderBy(invtRptInvtPreConListForm.getOrderBy());
        invtRptInvtPreConCommonDTO.setDirection(invtRptInvtPreConListForm.getDirection());
        
        User user = getUser(request);
        List resultList = invtRptInvtPreConListService.findList(invtRptInvtPreConCommonDTO, user);
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtRptInvtPreConListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtRptInvtPreConListService.findTotalCount(invtRptInvtPreConCommonDTO,user);
        
        if(excelExport) super.makeGridExport(resultList, request, response,invtRptInvtPreConListForm.getListId(),invtRptInvtPreConListForm.getCurrentPageId(), invtRptInvtPreConListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
}