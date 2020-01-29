package intf.dream.ant.check.action;


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
import intf.dream.ant.check.form.AntSessionForm;

/**
 * check  
 * @author  
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/antSession" name="antSessionForm"
 *                input="/ant/check/antSession.jsp" scope="request"
 *                validate="false"
 */
public class AntSessionAction extends IfOnlineAuthAction
{
    //SESSION CHECK
    public static final String SESSION_CHECK 	= "SESSIONCHECK";
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        AntSessionForm antSessionForm = (AntSessionForm) form;
        
        switch (antSessionForm.getServiceName())
        {
            case AntSessionAction.SESSION_CHECK:
            	netCheck(request, response, antSessionForm);
            	returnActionForward = mapping.findForward("jsonPage");
            	break;
            default:
                returnActionForward = mapping.findForward("jsonPage");
                break;
        }

        return returnActionForward;
    }
    

    private void netCheck(HttpServletRequest request, HttpServletResponse response, AntSessionForm antSessionForm)  throws IOException, ParseException
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
