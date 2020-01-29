package dream.fail.code.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.SuperAuthAction;
import dream.fail.code.dto.MaFailureCommonDTO;
import dream.fail.code.form.FailCodeLovForm;
import dream.fail.code.service.FailCodeLovService;

/**
 * �����ڵ� LOV - ��� action
 * @author  ssong
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/failCodeLov" name="failCodeLovForm"
 *                input="/dream/fail/code/failCodeLov.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="failCodeLov" path="/dream/fail/code/failCodeLov.jsp"
 *                        redirect="false"
 */
public class FailCodeLovAction extends SuperAuthAction
{
    /** ��ȸ */
    public static final int FAILURE_LIST_FIND = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        FailCodeLovForm failCodeLovForm = (FailCodeLovForm) form;
        
        switch (failCodeLovForm.getStrutsAction())
        {
            case FailCodeLovAction.BASE_SET_HEADER:
                setHeader(request, response, failCodeLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;
            case FailCodeLovAction.FAILURE_LIST_FIND:
                findList(request, response, failCodeLovForm);
                returnActionForward = mapping.findForward("jsonPage");
                break;    
            default:
                returnActionForward = mapping.findForward("failCodeLov");
                break;
        }

        return returnActionForward;
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response, FailCodeLovForm failCodeLovForm) throws IOException
    {
        super.setHeader(request, response, failCodeLovForm.getListId(), failCodeLovForm.getCurrentPageId()); 
    }
    
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param request
     * @param failCodeLovForm
     * @throws Exception
     */
    private void findList(HttpServletRequest request, HttpServletResponse response, FailCodeLovForm failCodeLovForm) throws IOException
    {
    	FailCodeLovService failCodeLovService = (FailCodeLovService) getBean("failCodeLovService");        

    	MaFailureCommonDTO maFailureCommonDTO = failCodeLovForm.getMaFailureCommonDTO();

    	// �α� comp_no �� �����Ѵ�.
    	maFailureCommonDTO.setFilterCompNo((getUser(request).getCompNo()));
        
        //����Ʈ�� ��ȸ�Ѵ�.
        List resultList = failCodeLovService.findList(maFailureCommonDTO,getUser(request), failCodeLovForm);

        // ��ȸ�� List �� form�� �����Ѵ�.
        super.makeJsonResult(resultList, request, response, failCodeLovForm.getListId());
    }
}
