package dream.invt.prc.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.form.InvtPrcTpListForm;
import dream.invt.prc.service.InvtPrcTpListService;

/**
 * 구매절차 - 목록 action
 * @author  kim21017
 * @version $Id: InvtPrcTpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/invtPrcTpList" name="invtPrcTpListForm"
 *                input="/dream/invt/prc/invtPrcTpList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtPrcTpList" path="/dream/invt/prc/invtPrcTpList.jsp"
 *                        redirect="false"
 */
public class InvtPrcTpListAction extends AuthAction
{
    /** 조회 */
    public static final int INVTPRC_LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int INVTPRC_LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtPrcTpListForm invtPrcTpListForm = (InvtPrcTpListForm) form;
        
        super.updateAudit(invtPrcTpListForm.getInvtPrcTpCommonDTO().getAuditKey()==""?invtPrcTpListForm.getInvtPrcTpCommonDTO().getAuditKey():invtPrcTpListForm.getInvtPrcTpCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtPrcTpListForm.getStrutsAction())
        {
            case InvtPrcTpListAction.INVTPRC_LIST_FIND:
                findInvtPrcTpList(request, invtPrcTpListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcTpListAction.BASE_SET_HEADER:
                setHeader(request, response, invtPrcTpListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcTpListAction.INVTPRC_LIST_DELETE:
                deleteQna(request, invtPrcTpListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case InvtPrcTpListAction.BASE_GRID_EXPORT:
            	findInvtPrcTpList(request, invtPrcTpListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtPrcTpList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtPrcTpListForm invtPrcTpListForm) throws IOException
    {
        super.setHeader(request, response, invtPrcTpListForm.getListId(), invtPrcTpListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: InvtPrcTpListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtPrcTpListForm
     * @throws IOException 
     * @throws Exception
     */
    private void findInvtPrcTpList(HttpServletRequest request, InvtPrcTpListForm invtPrcTpListForm, HttpServletResponse response, boolean excelExport) throws IOException 
    {
    	InvtPrcTpListService invtPrcTpListService = (InvtPrcTpListService) getBean("invtPrcTpListService");        

    	InvtPrcTpCommonDTO invtPrcTpCommonDTO = invtPrcTpListForm.getInvtPrcTpCommonDTO();
//    	invtPrcTpCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
    	invtPrcTpCommonDTO.setIsLoadMaxCount("Y".equals(invtPrcTpListForm.getIsLoadMaxCount())?true:false);
    	invtPrcTpCommonDTO.setFirstRow(invtPrcTpListForm.getFirstRow());
    	invtPrcTpCommonDTO.setOrderBy(invtPrcTpListForm.getOrderBy());
    	invtPrcTpCommonDTO.setDirection(invtPrcTpListForm.getDirection());
    	
        //리스트를 조회한다.
        List resultList = invtPrcTpListService.findInvtPrcTpList(invtPrcTpCommonDTO, getUser(request));

    	//Paging
    	String totalCount = "";    	        
    	if(Integer.parseInt(invtPrcTpListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtPrcTpListService.findTotalCount(invtPrcTpCommonDTO, getUser(request));
        
    	// 조회한 List 를 form에 셋팅한다.
        if(excelExport) super.makeGridExport(resultList, request, response, invtPrcTpListForm.getListId(),invtPrcTpListForm.getCurrentPageId(), invtPrcTpListForm.getFileName());
    	else super.makeJsonResult(resultList, request, response, totalCount);
    }
    /**
     * delete
     * @author  kim21017
     * @version $Id: InvtPrcTpListAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpListForm
     * @param request
     */
    private void deleteQna(HttpServletRequest request, InvtPrcTpListForm invtPrcTpListForm) throws Exception
    {
    	InvtPrcTpListService invtPrcTpListService = (InvtPrcTpListService) getBean("invtPrcTpListService");        

    	String[] deleteRows = invtPrcTpListForm.getDeleteRows();    // sheet 내역
        
    	invtPrcTpListService.deleteQna(deleteRows,getUser(request));
        
        setAjaxStatus(request);
    }
}
