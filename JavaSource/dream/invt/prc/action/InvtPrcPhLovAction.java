package dream.invt.prc.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.invt.prc.dto.InvtPrcPhLovDTO;
import dream.invt.prc.form.InvtPrcPhLovForm;
import dream.invt.prc.service.InvtPrcPhLovService;

/**
 * 구매절차 소분류 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/invtPrcPhLov" name="invtPrcPhLovForm"
 *                input="/dream/invt/prc/invtPrcPhLov.jsp" scope="request"
 *                validate="false"
 * 
 */
public class InvtPrcPhLovAction extends SuperAuthAction
{
    public static final int LOV_AC_FIND 	= 1002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        InvtPrcPhLovForm invtPrcPhLovForm = (InvtPrcPhLovForm)form;
        ActionForward returnActionForward = null;
        
        switch (invtPrcPhLovForm.getStrutsAction())
        {
            case InvtPrcPhLovAction.LOV_AC_FIND :
                findTaskMapList(request, invtPrcPhLovForm,response);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case InvtPrcPhLovAction.BASE_SET_HEADER:
                setHeader(request, response, invtPrcPhLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            default :
                returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, InvtPrcPhLovForm invtPrcPhLovForm) throws IOException
    {
        super.setHeader(request, response, invtPrcPhLovForm.getListId(),invtPrcPhLovForm.getCurrentPageId()); 
    }

    /**
     * 구매절차 소분류를 검색한다.
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param request
     * @param lovTaskMapListForm
     */
    private void findTaskMapList(HttpServletRequest request,
            InvtPrcPhLovForm invtPrcPhLovForm,HttpServletResponse response) throws IOException
    {
        InvtPrcPhLovService invtPrcPhLovService = (InvtPrcPhLovService)getBean("invtPrcPhLovService");
        
        InvtPrcPhLovDTO invtPrcPhLovDTO = invtPrcPhLovForm.getInvtPrcPhLovDTO();
        List resultList = invtPrcPhLovService.findTaskMapAcList(invtPrcPhLovDTO,getUser(request),invtPrcPhLovForm );
        
        super.makeJsonResult(resultList, request, response, invtPrcPhLovForm.getListId());
    	
    }

}