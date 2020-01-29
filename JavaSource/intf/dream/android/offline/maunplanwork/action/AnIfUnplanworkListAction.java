package intf.dream.android.offline.maunplanwork.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.android.offline.maunplanwork.form.AnIfUnplanworkListForm;
import intf.dream.android.offline.maunplanwork.service.AnIfUnplanworkListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfUnplanworkList" name="anIfUnplanworkListForm"
 *                input="/android/offline/maunplanwork/anIfUnplanworkList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfUnplanworkListAction extends IfAuthAction
{
    //PMWORK_UPLOAD
    public static final String WORKORDER_SAVE			= "WORKORDER_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfUnplanworkListForm anIfUnplanworkListForm = (AnIfUnplanworkListForm) form;
        
        switch (anIfUnplanworkListForm.getServiceName())
        {
            case AnIfUnplanworkListAction.WORKORDER_SAVE:
            	saveWorkorder(request, response, anIfUnplanworkListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void saveWorkorder(HttpServletRequest request, HttpServletResponse response, AnIfUnplanworkListForm anIfUnplanworkListForm)  throws Exception
    {
    	AnIfUnplanworkListService anIfUnplanworkListService = (AnIfUnplanworkListService) getBean("anIfUnplanworkListService");

    	List list = getListData(request);
    	int qty = anIfUnplanworkListService.saveWorkorder(list);
    	setMessage(response, "","OK");
    }

}
