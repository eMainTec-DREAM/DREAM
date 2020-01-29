package intf.dream.android.offline.maptiss.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.IfAuthAction;
import intf.dream.android.offline.maptiss.form.AnIfPtissListForm;
import intf.dream.android.offline.maptiss.service.AnIfPtissListService;

/**
 * inspection 
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfPtissList" name="anIfPtissListForm"
 *                input="/android/offline/maptiss/anIfPtissList.jsp" scope="request"
 *                validate="false"
 */
public class AnIfPtissListAction extends IfAuthAction
{
    //TAPTSTOCK SAVE
    public static final String ISS_SAVE 				= "ISS_SAVE";
    
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfPtissListForm anIfPtissListForm = (AnIfPtissListForm) form;
        
        switch (anIfPtissListForm.getServiceName())
        {
            case AnIfPtissListAction.ISS_SAVE:
            	savePtiss(request, response, anIfPtissListForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break; 
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    
    private void savePtiss(HttpServletRequest request, HttpServletResponse response, AnIfPtissListForm anIfPtissListForm)  throws Exception
    {
    	AnIfPtissListService anIfPtissListService = (AnIfPtissListService) getBean("anIfPtissListService");

    	List list = getListData(request);
    	int qty = anIfPtissListService.savePtiss(list);
    	setMessage(response, "","OK");
    }
}
