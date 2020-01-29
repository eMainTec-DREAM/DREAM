package dream.login.login.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.NoneAuthAction;

/**
 * SSO Login Action
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 * @struts.action path="/ssoindex" name="loginForm"
 *                input="/dream/index.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="goLoginAction"
 *                        path="/index.do?strutsAction=9001" redirect="true"
 */
public class SsoLoginAction
        extends NoneAuthAction
{
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	ActionForward returnActionForward = null;
        
        returnActionForward = mapping.findForward("goLoginAction");
        
        return returnActionForward;
    }
    
}