package common.export.excel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.export.excel.form.ExcelExportForm;
import common.struts.SuperAuthAction;

/**
 * Excel Export Pop Action
 * @author  javaworker
 * @version $Id:$
 * @since   1.0
 * 
 * @struts:action path="/excelExport" name="excelExportForm"
 *                input="/common/jsp/excelExport.jsp" scope="request"
 *                validate="false"
 */
public class ExcelExportAction extends SuperAuthAction
{
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ExcelExportForm excelExportForm = (ExcelExportForm)form;
        ActionForward returnActionForward = null;

        switch (excelExportForm.getStrutsAction())
        {
            default :
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }

}