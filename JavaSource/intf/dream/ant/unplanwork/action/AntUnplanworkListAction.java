package intf.dream.ant.unplanwork.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.ant.unplanwork.form.AntUnplanworkListForm;
import intf.dream.ant.unplanwork.service.AntUnplanworkListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antUnplanworkList" name="antUnplanworkListForm"
 *                input="/ant/unplanwork/antUnplanworkList.jsp" scope="request"
 *                validate="false"
 */
public class AntUnplanworkListAction extends IfAuthAction
{
    //PMWORK_UPLOAD
    public static final String WORKORDER_SAVE			= "WORKORDER_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntUnplanworkListForm antUnplanworkListForm = (AntUnplanworkListForm) form;
        
        switch (antUnplanworkListForm.getServiceName())
        {
            case AntUnplanworkListAction.WORKORDER_SAVE:
            	saveWorkorder(request, response, antUnplanworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void saveWorkorder(HttpServletRequest request, HttpServletResponse response, AntUnplanworkListForm antUnplanworkListForm)  throws Exception
    {
    	AntUnplanworkListService antUnplanworkListService = (AntUnplanworkListService) getBean("antUnplanworkListService");

    	List list = getListData(request);
    	int qty = antUnplanworkListService.saveWorkorder(list);
    	setMessage(response, "","OK");
    }

}
