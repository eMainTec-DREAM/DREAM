package dream.invt.list.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.bean.User;
import common.struts.AuthAction;
import common.util.CommonUtil;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.form.InvtListForm;
import dream.invt.list.service.InvtListService;

/**
 * 목록 action
 * @author  kim21017
 * @version $Id: InvtListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
 * @since   1.0
 * @struts:action path="/invtList" name="invtListForm"
 *                input="/dream/invt/list/invtList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="invtList" path="/dream/invt/list/invtList.jsp"
 *                        redirect="false"
 */
public class InvtListAction extends AuthAction
{
    /** 조회 */
    public static final int INVT_LIST_FIND 		= 8001;
    
    public static final int INVT_LIST_DELETE 	= 7002;
    /** TAEXCELTAB 데이터 가져오기 */
    public static final int GET_DATA		 	= 1003;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        InvtListForm invtListForm = (InvtListForm) form;
        
        super.updateAudit(invtListForm.getInvtCommonDTO().getAuditKey()==""?invtListForm.getInvtCommonDTO().getAuditKey():invtListForm.getInvtCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (invtListForm.getStrutsAction())
        {
            case InvtListAction.INVT_LIST_FIND:
                findList(request, invtListForm, response, false);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtListAction.INVT_LIST_DELETE:
                deleteList(request, invtListForm, response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtListAction.GET_DATA:
            	getData(request, invtListForm, response);
            	returnActionForward = mapping.findForward("ajaxXmlVal");
            	break;
            case InvtListAction.BASE_SET_HEADER:
                setHeader(request, response, invtListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtListAction.BASE_GRID_EXPORT:
            	findList(request, invtListForm, response, true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("invtList");
                break;
        }
        return returnActionForward;
    }

    private void deleteList(HttpServletRequest request, InvtListForm invtListForm, HttpServletResponse response) {
    	InvtListService invtListService = (InvtListService) getBean("invtListService", request);        

        String[] deleteRows = invtListForm.getDeleteRows();    // sheet 내역
        
        invtListService.deleteList(deleteRows, getUser(request));
        
        setAjaxStatus(request);
	}

	private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtListForm invtListForm) throws IOException
    {
        super.setHeader(request, response, invtListForm.getListId(), invtListForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  kim2107
     * @version $Id: InvtListAction.java,v 1.0 2015/12/02 09:10:21 kim21017 Exp $
     * @since   1.0
     * 
     * @param request
     * @param invtListForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, InvtListForm invtListForm, HttpServletResponse response, boolean excelExport) throws Exception 
    {
    	InvtListService invtListService = (InvtListService) getBean("invtListService", request);               

    	InvtCommonDTO invtCommonDTO = invtListForm.getInvtCommonDTO();
    	invtCommonDTO.setCompNo(getUser(request).getCompNo());
    	
    	//Paging
        invtCommonDTO.setIsLoadMaxCount("Y".equals(invtListForm.getIsLoadMaxCount())?true:false);
        invtCommonDTO.setFirstRow(invtListForm.getFirstRow());
        invtCommonDTO.setOrderBy(invtListForm.getOrderBy());
        invtCommonDTO.setDirection(invtListForm.getDirection());
    	
        User user = getUser(request);
        //리스트를 조회한다.
        List resultList = invtListService.findList(invtCommonDTO, getUser(request));
 
        //Paging
        String totalCount = "";
        
        if(Integer.parseInt(invtListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = invtListService.findTotalCount(invtCommonDTO,user);
        
        
        if(excelExport) CommonUtil.makeGridExport(resultList, request, response,invtListForm);
        else CommonUtil.makeJsonResult(resultList, request, response, totalCount);
    }
    
    private void getData(HttpServletRequest request, InvtListForm invtListForm, HttpServletResponse response) throws Exception
    {
    	InvtListService invtListService = (InvtListService) getBean("invtListService", request);               

    	User user = getUser(request);
    	
    	String result = invtListService.getData(user);
    	
    	setAjaxDesc(request, result);
    }
}
