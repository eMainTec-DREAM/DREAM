package dream.invt.list.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.invt.list.dto.InvtWoRsltDTO;
import dream.invt.list.form.InvtWoRsltForm;
import dream.invt.list.service.InvtWoRsltService;


/**
 * @author  ghlee
 * @version $Id$
 * @since   1.0
 * @struts:action path="/invtWoRsltList" name="invtWoRsltForm"
 *                input="/dream/invt/list/invtWoRsltList.jsp" scope="request"
 *                validate="false"
 */
public class InvtWoRsltAction extends AuthAction
{
    /** 목록에서 선택후 TAB이동 조회를 하는경우 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    /** 기존작업 선택 후 저장 */
    public static final int LIST_LINK	    = 5004;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        InvtWoRsltForm invtWoRsltForm = (InvtWoRsltForm) form;
        
        super.updateAudit(invtWoRsltForm.getInvtCommonDTO().getAuditKey()==""?invtWoRsltForm.getInvtCommonDTO().getAuditKey():invtWoRsltForm.getInvtCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtWoRsltForm.getStrutsAction())
        {
            case InvtListAction.BASE_SET_HEADER:
                super.setHeader(request, response, invtWoRsltForm.getListId(), invtWoRsltForm.getCurrentPageId());
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtWoRsltAction.LIST_FIND:
                findList(request, response, invtWoRsltForm, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtWoRsltAction.LIST_DELETE:
            	deleteList(request,invtWoRsltForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtWoRsltAction.LIST_LINK:
                linkList(request,response,invtWoRsltForm);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtWoRsltAction.BASE_GRID_EXPORT:
            	findList(request,response, invtWoRsltForm, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
    
    private void findList(HttpServletRequest request, HttpServletResponse response, InvtWoRsltForm invtWoRsltForm, boolean excelExport) throws Exception
    {
        InvtWoRsltService invtWoRsltService = (InvtWoRsltService) getBean("invtWoRsltService");
        InvtWoRsltDTO invtWoRsltDTO = invtWoRsltForm.getInvtWoRsltDTO();

        //Paging
        invtWoRsltDTO.setIsLoadMaxCount("Y".equals(invtWoRsltForm.getIsLoadMaxCount())?true:false);
        invtWoRsltDTO.setFirstRow(invtWoRsltForm.getFirstRow());
        invtWoRsltDTO.setOrderBy(invtWoRsltForm.getOrderBy());
        invtWoRsltDTO.setDirection(invtWoRsltForm.getDirection());
        
        List resultList = invtWoRsltService.findList(invtWoRsltDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtWoRsltForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtWoRsltService.findTotalCount(invtWoRsltDTO, getUser(request));
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,invtWoRsltForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void deleteList(HttpServletRequest request, InvtWoRsltForm invtWoRsltForm) throws Exception
    {
    	InvtWoRsltService invtWoRsltService = (InvtWoRsltService) getBean("invtWoRsltService");
        
    	invtWoRsltService.delete(invtWoRsltForm.getDeleteRows(), getUser(request));
    	
    	setAjaxStatus(request);
    }
    
    private void linkList(HttpServletRequest request, HttpServletResponse response, InvtWoRsltForm invtWoRsltForm) throws Exception
    {
    	InvtWoRsltService invtWoRsltService = (InvtWoRsltService) getBean("invtWoRsltService");
    	InvtWoRsltDTO invtWoRsltDTO = invtWoRsltForm.getInvtWoRsltDTO();
    	
    	invtWoRsltService.linkList(invtWoRsltDTO, getUser(request));
    	
    	setAjaxStatus(request);
    }
}
