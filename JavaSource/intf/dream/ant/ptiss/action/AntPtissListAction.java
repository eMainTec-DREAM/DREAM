package intf.dream.ant.ptiss.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.ant.ptiss.form.AntPtissListForm;
import intf.dream.ant.ptiss.service.AntPtissListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antPtissList" name="antPtissListForm"
 *                input="/ant/ptiss/antPtissList.jsp" scope="request"
 *                validate="false"
 */
public class AntPtissListAction extends IfAuthAction
{
    //TAPTSTOCK SAVE
    public static final String ISS_SAVE 				= "ISS_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntPtissListForm antPtissListForm = (AntPtissListForm) form;
        
        switch (antPtissListForm.getServiceName())
        {
            case AntPtissListAction.ISS_SAVE:
            	savePtiss(request, response, antPtissListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void savePtiss(HttpServletRequest request, HttpServletResponse response, AntPtissListForm antPtissListForm)  throws Exception
    {
    	AntPtissListService antPtissListService = (AntPtissListService) getBean("antPtissListService");

    	List list = getListData(request);
    	int qty = antPtissListService.savePtiss(list);
    	setMessage(response, "","OK");
    }
}
