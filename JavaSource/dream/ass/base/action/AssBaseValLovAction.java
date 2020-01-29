package dream.ass.base.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.ass.base.dto.AssBaseValLovDTO;
import dream.ass.base.form.AssBaseValLovForm;
import dream.ass.base.service.AssBaseValLovService;

/**
 * 설비등급 평가기준 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: AssBaseValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/assBaseValLov" name="assBaseValLovForm"
 *                input="/dream/ass/base/assBaseValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="assBaseValLov" path="/dream/ass/base/assBaseValLov.jsp"
 *                        redirect="false"
 */

public class AssBaseValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AssBaseValLovForm assBaseValLovForm = (AssBaseValLovForm) form;
        
        switch (assBaseValLovForm.getStrutsAction())
        {
            case AssBaseValLovAction.BASE_SET_HEADER:
                setHeader(request, response, assBaseValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case AssBaseValLovAction.LIST_FIND:
                findList(request, response, assBaseValLovForm,false);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("assBaseValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, AssBaseValLovForm assBaseValLovForm) throws IOException
    {
        super.setHeader(request, response, assBaseValLovForm.getListId(), assBaseValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param assBaseValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, AssBaseValLovForm assBaseValLovForm, boolean excelExport) throws Exception
    {
    	AssBaseValLovService assBaseValLovService = (AssBaseValLovService) getBean("assBaseValLovService");
    	AssBaseValLovDTO assBaseValLovDTO = assBaseValLovForm.getAssBaseValLovDTO();
    	
    	//Paging
    	assBaseValLovDTO.setIsLoadMaxCount("Y".equals(assBaseValLovForm.getIsLoadMaxCount())?true:false);
    	assBaseValLovDTO.setFirstRow(assBaseValLovForm.getFirstRow());
    	assBaseValLovDTO.setOrderBy(assBaseValLovForm.getOrderBy());
    	assBaseValLovDTO.setDirection(assBaseValLovForm.getDirection());
    	
        List resultList = assBaseValLovService.findList(assBaseValLovForm, getUser(request));
        
        //Paging
        String totalCount = "";
        if(Integer.parseInt(assBaseValLovForm.getIsTotalCount()) == 0 && !excelExport) totalCount = assBaseValLovService.findTotalCount(assBaseValLovForm,getUser(request));

        if(excelExport) super.makeGridExport(resultList, request, response,assBaseValLovForm.getListId(),assBaseValLovForm.getCurrentPageId(), assBaseValLovForm.getFileName());
        else super.makeJsonResult(resultList, request, response, totalCount);
    }
    
}
