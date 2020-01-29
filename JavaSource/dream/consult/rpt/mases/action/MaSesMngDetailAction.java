package dream.consult.rpt.mases.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import dream.consult.rpt.mases.form.MaSesMngDetailForm;

/**
 * 실시간 접속자 상세
 * @author  kim21017
 * @version $Id: MaSesMngDetailAction.java,v 1.1 2013/08/30 09:12:22 kim21017 Exp $
 * @since   1.0
 * 
 * @struts:action path="/maSesMngDetail" name="maSesMngDetailForm"
 *                input="/dream/consult/rpt/mases/maSesMngDetail.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maSesMngDetail" path="/dream/consult/rpt/mases/maSesMngDetail.jsp"
 *                        redirect="false"
 */
public class MaSesMngDetailAction
        extends AuthAction
{
    /** 상세 조회시 */
    public static final int SES_DETAIL_INIT	= 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        MaSesMngDetailForm maSesMngDetailForm = (MaSesMngDetailForm)form;
        ActionForward returnActionForward = null;
        
        switch (maSesMngDetailForm.getStrutsAction())
        {
            case MaSesMngDetailAction.SES_DETAIL_INIT :
            default :
                returnActionForward = mapping.findForward("maSesMngDetail");
                break;
        }
        
        return returnActionForward;
    }
}