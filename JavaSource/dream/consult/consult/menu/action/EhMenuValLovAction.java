package dream.consult.consult.menu.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.consult.menu.dto.EhMenuValLovDTO;
import dream.consult.consult.menu.form.EhMenuValLovForm;
import dream.consult.consult.menu.service.EhMenuValLovService;

/**
 * 컨설트 메뉴 LOV- List Action
 * 
 * @author kim21017
 * @version $Id: EhMenuValLovAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/ehMenuValLov" name="ehMenuValLovForm"
 *                input="/dream/consult/consult/menu/ehMenuValLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="ehMenuValLov" path="/dream/consult/consult/menu/ehMenuValLov.jsp"
 *                        redirect="false"
 */

public class EhMenuValLovAction extends AuthAction
{
    /** 조회 */
    public static final int LIST_FIND 		= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        EhMenuValLovForm ehMenuValLovForm = (EhMenuValLovForm) form;
        
        switch (ehMenuValLovForm.getStrutsAction())
        {
            case EhMenuValLovAction.BASE_SET_HEADER:
                setHeader(request, response, ehMenuValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case EhMenuValLovAction.LIST_FIND:
                findList(request, response, ehMenuValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("ehMenuValLov");
                break;
        }
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, EhMenuValLovForm ehMenuValLovForm) throws IOException
    {
        super.setHeader(request, response, ehMenuValLovForm.getListId(), ehMenuValLovForm.getCurrentPageId()); 
    }
    
    /**
     * FIND LIST
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param response
     * @param ehMenuValLovForm
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, EhMenuValLovForm ehMenuValLovForm) throws Exception
    {
    	EhMenuValLovService ehMenuValLovService = (EhMenuValLovService) getBean("ehMenuValLovService");
    	
        List resultList = ehMenuValLovService.findList(ehMenuValLovForm, getUser(request));
        
        super.makeTreeJsonResult(resultList, request, response, ehMenuValLovForm.getListId());
    }
    
}
