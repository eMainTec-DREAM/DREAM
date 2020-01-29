package common.report.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.report.form.ReportFilterForm;
import common.struts.AuthAction;

/**
 * @author  wondo
 * @version $Id: ReportFilterAction.java,v 1.1 2013/08/30 09:13:23 javaworker Exp $
 * @since   1.0
 * 
 * @struts:action path="/reportFilter" name="reportFilterForm"
 *                input="/report/reportFilter.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="reportFilter" path="/common/report/reportFilter.jsp"
 *                        redirect="false" 
 */
public class ReportFilterAction extends AuthAction
{
    /** inventory structure report filter */
    public static final int INVENTORY_FILTER  = 1010;
    /** system code report filter page */
    public static final int SYSTEMCODE_FILTER = 1020;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        ReportFilterForm reportFilterForm = (ReportFilterForm) form;
        
        ActionForward returnActionForward = null;
        
        switch (reportFilterForm.getStrutsAction())
        {
            case ReportFilterAction.INVENTORY_FILTER:
                returnActionForward = mapping.findForward("reportFilter");
                break;
            case ReportFilterAction.SYSTEMCODE_FILTER:
                returnActionForward = mapping.findForward("reportFilter");
                break;
            default:
                break;
        }
        
        return returnActionForward;
    }
}