package dream.ass.base.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBasePointListDTO;
import dream.ass.base.dto.AssBasePointValListDTO;
import dream.ass.base.form.AssBasePointValListForm;
import dream.ass.base.service.AssBasePointValListService;

/**
 * 평가기준 - List Action
 * 
 * @author kim21017
 * @version $Id: AssBasePointValListAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBasePointValList" name="assBasePointValListForm"
 *                input="/dream/ass/base/assBasePointValList.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBasePointValList" path="/dream/ass/base/assBasePointValList.jsp"
 *                        redirect="false"
 */

public class AssBasePointValListAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    /** 삭제 */
    public static final int LIST_DELETE 	= 7002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBasePointValListForm assBasePointValListForm = (AssBasePointValListForm) form;
        
        switch (assBasePointValListForm.getStrutsAction())
        {
            case AssBasePointValListAction.BASE_SET_HEADER:
                setHeader(request, response, assBasePointValListForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBasePointValListAction.LIST_FIND:
                findList(request, response, assBasePointValListForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            case AssBasePointValListAction.LIST_DELETE:
            	deleteList(request, assBasePointValListForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;       
            case AssBasePointValListAction.BASE_GRID_EXPORT:
            	findList(request, response, assBasePointValListForm,true);
                returnActionForward = new ActionForward("/gridExport");
                break;
            default:
                returnActionForward = mapping.findForward("assBasePointValList");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBasePointValListForm assBasePointValListForm) throws IOException
    {
        super.setHeader(request, response, assBasePointValListForm.getListId(), assBasePointValListForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assBasePointValListForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssBasePointValListForm assBasePointValListForm, boolean excelExport) throws Exception
    {
    	AssBasePointValListService assBasePointValListService = (AssBasePointValListService) getBean("assBasePointValListService");
    	AssBaseCommonDTO assBaseCommonDTO = assBasePointValListForm.getAssBaseCommonDTO();
    	AssBasePointListDTO assBasePointListDTO = assBasePointValListForm.getAssBasePointListDTO();
    	AssBasePointValListDTO assBasePointValListDTO = assBasePointValListForm.getAssBasePointValListDTO();

    	//Paging
    	assBaseCommonDTO.setIsLoadMaxCount("Y".equals(assBasePointValListForm.getIsLoadMaxCount())?true:false);
    	assBaseCommonDTO.setFirstRow(assBasePointValListForm.getFirstRow());
    	assBaseCommonDTO.setOrderBy(assBasePointValListForm.getOrderBy());
    	assBaseCommonDTO.setDirection(assBasePointValListForm.getDirection());
    	
    	List resultList = assBasePointValListService.findList(assBaseCommonDTO,assBasePointListDTO,assBasePointValListDTO, getUser(request));
        
    	//Paging
        String totalCount = "";
        if(Integer.parseInt(assBasePointValListForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assBasePointValListService.findTotalCount(assBaseCommonDTO, assBasePointListDTO, assBasePointValListDTO, getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assBasePointValListForm.getListId(),assBasePointValListForm.getCurrentPageId(), assBasePointValListForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
        
    }
    
    /**
     * DELETE LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param assBasePointValListForm
     */
    private void deleteList(HttpServletRequest request, AssBasePointValListForm assBasePointValListForm) throws Exception
    {
    	AssBasePointValListService assBasePointValListService = (AssBasePointValListService) getBean("assBasePointValListService");
        String[] deleteRows = assBasePointValListForm.getDeleteRows();
        assBasePointValListService.deleteList(deleteRows, getUser(request));
        setAjaxStatus(request);
    }
    
}
