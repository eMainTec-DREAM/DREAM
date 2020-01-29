package dream.auth.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.auth.form.NoAuthPageListForm;

/**
 * 권한없음 페이지
 * @author  kim21017
 * @version $Id: $
 * @since   1.0
 * @struts:action path="/noAuthPageList" name="noAuthPageListForm"
 *                input="/dream/auth/noAuthPageList.jsp" scope="request"
 *                validate="false"
 * @struts:action path="/noAuthPageDetail" name="noAuthPageListForm"
 *                input="/dream/auth/noAuthPageDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="noAuthPageList" path="/dream/auth/noAuthPageList.jsp"
 *                        redirect="false"
 */
public class NoAuthPageListAction extends AuthAction
{
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ActionForward returnActionForward = null;
        NoAuthPageListForm noAuthPageListForm = (NoAuthPageListForm) form;
        
        switch (noAuthPageListForm.getStrutsAction())
        {
            default:
                returnActionForward = mapping.getInputForward();
                break;
        }

        return returnActionForward;
    }
}
