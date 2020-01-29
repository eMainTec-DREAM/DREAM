package intf.dream.android.macheck.action;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.macheck.form.AnIfSessionForm;

/**
 * check  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/anIfSession" name="anIfSessionForm"
 *                input="/android/macheck/anIfSession.jsp" scope="request"
 *                validate="false"
 */
public class AnIfSessionAction extends IfOnlineAuthAction
{
    //SESSION CHECK
    public static final String SESSION_CHECK 	= "SESSIONCHECK";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AnIfSessionForm anIfSessionForm = (AnIfSessionForm) form;
        
        switch (anIfSessionForm.getServiceName())
        {
            case AnIfSessionAction.SESSION_CHECK:
            	netCheck(request, response, anIfSessionForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    

    private void netCheck(HttpServletRequest request, HttpServletResponse response, AnIfSessionForm anIfSessionForm)  throws IOException, ParseException
    {
    	Map result = new HashMap();
        result.put("ResultStatus", "OK");        
        result.put("ResultMsg", "");        
        
       // Gson gson = new Gson();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create(); //유니코드 처리
        
        String jsonString = gson.toJson(result); 
        response.getWriter().print(jsonString);
    }
}
