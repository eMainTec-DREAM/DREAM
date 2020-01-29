package dream.ass.base.action;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.form.AssBaseListForm;
import dream.ass.base.service.AssBaseListService;

/**
 * 설비등급 평가기준 - List Action
 * 
 * @author kim21017
 * @version $Id: AssBaseListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBaseList" name="assBaseListForm"
 *                input="/dream/ass/base/assBaseList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBaseList" path="/dream/ass/base/assBaseList.jsp"
 *                        redirect="false"
 */

public class AssBaseListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBaseListForm assBaseListForm = (AssBaseListForm) form;
        
        super.updateAudit(assBaseListForm.getAssBaseCommonDTO().getAuditKey()==""?assBaseListForm.getAssBaseCommonDTO().getAuditKey():assBaseListForm.getAssBaseCommonDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBaseListForm.getStrutsAction())
        {
            case AssBaseListAction.BASE_SET_HEADER:
                setHeader(request, response, assBaseListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBaseListAction.LIST_FIND:
                findList(request, response, assBaseListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssBaseListAction.LIST_DELETE:
            	deleteList(request, assBaseListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssBaseListAction.BASE_GRID_EXPORT:
            	findList(request, response, assBaseListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assBaseList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBaseListForm assBaseListForm) throws IOException
    {
        super.setHeader(request, response, assBaseListForm.getListId(), assBaseListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assBaseListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssBaseListForm assBaseListForm, boolean excelExport) throws Exception
    {
    	AssBaseListService assBaseListService = (AssBaseListService) getBean("assBaseListService");
    	AssBaseCommonDTO assBaseCommonDTO = assBaseListForm.getAssBaseCommonDTO();
    	
    	//Paging
    	assBaseCommonDTO.setIsLoadMaxCount("Y".equals(assBaseListForm.getIsLoadMaxCount())?true:false);
    	assBaseCommonDTO.setFirstRow(assBaseListForm.getFirstRow());
    	assBaseCommonDTO.setOrderBy(assBaseListForm.getOrderBy());
    	assBaseCommonDTO.setDirection(assBaseListForm.getDirection());
    	
        List resultList = assBaseListService.findList(assBaseCommonDTO, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(assBaseListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assBaseListService.findTotalCount(assBaseCommonDTO,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assBaseListForm.getListId(),assBaseListForm.getCurrentPageId(), assBaseListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assBaseListForm
     */
    private void deleteList(HttpServletRequest request, AssBaseListForm assBaseListForm) throws Exception
    {
    	AssBaseListService assBaseListService = (AssBaseListService) getBean("assBaseListService");
        String[] deleteRows = assBaseListForm.getDeleteRows();
        assBaseListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
