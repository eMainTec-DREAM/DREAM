package dream.policy.android.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.NoneAuthAction;

/**
 * 안드로이드 정책 Action
 * 
 * @author kim21017
 * @version $Id: PolicyAndroidAction.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/policyAndroid" name="policyAndroidForm"
 *                input="/dream/policy/android/policyAndroid.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="policyAndroid" path="/dream/policy/android/policyAndroid.jsp"
 *                        redirect="false"
 */

public class PolicyAndroidAction extends NoneAuthAction
{
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        returnActionForward = mapping.getInputForward();
        return returnActionForward;
    }
}
