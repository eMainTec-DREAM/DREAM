package dream.rcm.crity.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.rcm.crity.dto.CrityValLovDTO;
import dream.rcm.crity.form.CrityValLovForm;
import dream.rcm.crity.service.CrityValLovService;

/**
 * Criticality Matrix Page - List Action
 * 
 * @author hyosung
 * @version $Id: CrityValLovAction.java,v 1.0 2015/12/02 09:12:40 hyosung Exp $
 * @since 1.0
 * @struts:action path="/crityValLov" name="crityValLovForm"
 *                input="/dream/rcm/crity/crityValLov.jsp" scope="request"
 *                validate="false"
 * 
 */
public class CrityValLovAction extends SuperAuthAction
{
    
    
    public static final int LOV_DEFAULT    = 1001;
    public static final int LOV_AC_FIND    = 1002;
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        CrityValLovForm crityValLovForm = (CrityValLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (crityValLovForm.getStrutsAction())
        {
            case CrityValLovAction.LOV_DEFAULT :
                returnActionForward = mapping.findForward("crityValLov");
                break;
            case CrityValLovAction.LOV_AC_FIND :
                findList(request, crityValLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case CrityValLovAction.BASE_SET_HEADER:
                setHeader(request, response, crityValLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward= mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, CrityValLovForm crityValLovForm) throws IOException
    {
        super.setHeader(request, response, crityValLovForm.getListId(),crityValLovForm.getCurrentPageId()); 
    }

    /**
     * criticality 검색한다.
     * @author  hyosung
     * @version $Id: CrityValLovAction.java,v 1.2 2014/01/28 07:49:29 hyosung Exp $
     * @since   1.0
     * 
     * @param request
     * @param crityValLovForm
     */
    private void findList(HttpServletRequest request,CrityValLovForm crityValLovForm,HttpServletResponse response) throws IOException
    {
        CrityValLovService crityValLovService = (CrityValLovService)getBean("crityValLovService");
        
        CrityValLovDTO crityValLovDTO = crityValLovForm.getCrityValLovDTO();
        List resultList = crityValLovService.findList(crityValLovDTO,getUser(request),crityValLovForm);
        
        super.makeJsonResult(resultList, request, response, crityValLovForm.getListId());
        
    }
    
}
