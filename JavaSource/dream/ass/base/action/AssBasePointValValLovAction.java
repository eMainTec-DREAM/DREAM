package dream.ass.base.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBasePointValValLovDTO;
import dream.ass.base.form.AssBasePointValValLovForm;
import dream.ass.base.service.AssBasePointValValLovService;

/**
 * 평가항목 평가기준 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: AssBasePointValValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBasePointValValLov" name="assBasePointValValLovForm"
 *                input="/dream/ass/base/assBasePointValValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBasePointValValLov" path="/dream/ass/base/assBasePointValValLov.jsp"
 *                        redirect="false"
 */

public class AssBasePointValValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBasePointValValLovForm assBasePointValValLovForm = (AssBasePointValValLovForm) form;
        
        switch (assBasePointValValLovForm.getStrutsAction())
        {
            case AssBasePointValValLovAction.BASE_SET_HEADER:
                setHeader(request, response, assBasePointValValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBasePointValValLovAction.LIST_FIND:
                findList(request, response, assBasePointValValLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("assBasePointValValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBasePointValValLovForm assBasePointValValLovForm) throws IOException
    {
        super.setHeader(request, response, assBasePointValValLovForm.getListId(), assBasePointValValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assBasePointValValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssBasePointValValLovForm assBasePointValValLovForm, boolean excelExport) throws Exception
    {
    	AssBasePointValValLovService assBasePointValValLovService = (AssBasePointValValLovService) getBean("assBasePointValValLovService");
    	AssBasePointValValLovDTO assBasePointValValLovDTO = assBasePointValValLovForm.getAssBasePointValValLovDTO();
    	
    	//Paging
    	assBasePointValValLovDTO.setIsLoadMaxCount("Y".equals(assBasePointValValLovForm.getIsLoadMaxCount())?true:false);
    	assBasePointValValLovDTO.setFirstRow(assBasePointValValLovForm.getFirstRow());
    	assBasePointValValLovDTO.setOrderBy(assBasePointValValLovForm.getOrderBy());
    	assBasePointValValLovDTO.setDirection(assBasePointValValLovForm.getDirection());
    	
        List resultList = assBasePointValValLovService.findList(assBasePointValValLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(assBasePointValValLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assBasePointValValLovService.findTotalCount(assBasePointValValLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assBasePointValValLovForm.getListId(),assBasePointValValLovForm.getCurrentPageId(), assBasePointValValLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
