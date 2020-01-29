package dream.consult.program.work.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.program.work.form.ConsultPgmUnderWorkForm;

/**
 * 프로그램 개발중 
 * 
 * @author euna0207
 * @version $Id: ConsultPgmUnderWorkAction.java,v 1.2 2015/12/02 01:21:30 euna0207 Exp $
 * @since 1.0
 * @struts:action path="/consultPgmUnderWork" name="consultPgmUnderWorkForm"
 *                input="/dream/consult/program/work/consultPgmUnderWork.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="consultPgmUnderWork" path="/dream/consult/program/work/consultPgmUnderWork.jsp"
 *                        redirect="false"
 */
public class ConsultPgmUnderWorkAction extends AuthAction
{
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        ConsultPgmUnderWorkForm consultPgmUnderWorkForm = (ConsultPgmUnderWorkForm) form;
        
        switch (consultPgmUnderWorkForm.getStrutsAction())
        {
            default: 
            	returnActionForward = mapping.getInputForward();
                break;
        }
        return returnActionForward;
    }
}