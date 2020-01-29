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
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.form.AssBasePointListForm;
import dream.ass.base.service.AssBasePointListService;

/**
 * 평가항목 - List Action
 * 
 * @author kim21017
 * @version $Id: AssBasePointListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBasePointList" name="assBasePointListForm"
 *                input="/dream/ass/base/assBasePointList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBasePointList" path="/dream/ass/base/assBasePointList.jsp"
 *                        redirect="false"
 */

public class AssBasePointListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 8001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBasePointListForm assBasePointListForm = (AssBasePointListForm) form;
        
        super.updateAudit(assBasePointListForm.getAssBasePointListDTO().getAuditKey()==""?assBasePointListForm.getAssBasePointListDTO().getAuditKey():assBasePointListForm.getAssBasePointListDTO().getAuditKey(), (Map)request.getAttribute("auditMap"), getUser(request));
        
        switch (assBasePointListForm.getStrutsAction())
        {
            case AssBasePointListAction.BASE_SET_HEADER:
                setHeader(request, response, assBasePointListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBasePointListAction.LIST_FIND:
                findList(request, response, assBasePointListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssBasePointListAction.LIST_DELETE:
            	deleteList(request, assBasePointListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssBasePointListAction.BASE_GRID_EXPORT:
            	findList(request, response, assBasePointListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assBasePointList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBasePointListForm assBasePointListForm) throws IOException
    {
        super.setHeader(request, response, assBasePointListForm.getListId(), assBasePointListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assBasePointListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssBasePointListForm assBasePointListForm, boolean excelExport) throws Exception
    {
    	AssBasePointListService assBasePointListService = (AssBasePointListService) getBean("assBasePointListService");
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointListForm.getAssBaseCommonDTO();
    	AssBasePointListDTO assBasePointListDTO = assBasePointListForm.getAssBasePointListDTO();

    	//Paging
    	assBaseCommonDTO.setIsLoadMaxCount("Y".equals(assBasePointListForm.getIsLoadMaxCount())?true:false);
    	assBaseCommonDTO.setFirstRow(assBasePointListForm.getFirstRow());
    	assBaseCommonDTO.setOrderBy(assBasePointListForm.getOrderBy());
    	assBaseCommonDTO.setDirection(assBasePointListForm.getDirection());
    	
    	List resultList = assBasePointListService.findList(assBaseCommonDTO,assBasePointListDTO, getUser(request));
        
    	//Paging
        String totalCount = "";
        if(Integer.parseInt(assBasePointListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assBasePointListService.findTotalCount(assBaseCommonDTO, assBasePointListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assBasePointListForm.getListId(),assBasePointListForm.getCurrentPageId(), assBasePointListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assBasePointListForm
     */
    private void deleteList(HttpServletRequest request, AssBasePointListForm assBasePointListForm) throws Exception
    {
    	AssBasePointListService assBasePointListService = (AssBasePointListService) getBean("assBasePointListService");
        String[] deleteRows = assBasePointListForm.getDeleteRows();
        assBasePointListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
