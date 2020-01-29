package dream.consult.program.foreport.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.struts.AuthAction;
import common.struts.ConsultAuthAction;
import common.util.FileUtil;
import dream.consult.program.foreport.dto.MaReportCommonDTO;
import dream.consult.program.foreport.form.MaReportForm;
import dream.consult.program.foreport.service.MaReportService;

/**
 * 레포트변환 action
 * 
 * @author kim2107
 * @version $Id: MaReportAction.java,v 1.2 2015/12/02 01:21:30 kim21017 Exp $
 * @since 1.0
 * @struts:action path="/maReport" name="maReportForm"
 *                input="/dream/consult/program/foreport/maReport.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="maReport" path="/dream/consult/program/foreport/maReport.jsp"
 *                        redirect="false"
 */
public class MaReportAction extends ConsultAuthAction
{
    /** Convert Excel To XSL-FO  */
    public static final int MA_REPORT_XSLFO = 1001;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ActionForward returnActionForward = null;
        MaReportForm maReportForm = (MaReportForm) form;
        
        switch (maReportForm.getStrutsAction())
        {
            case MaReportAction.MA_REPORT_XSLFO:
            	createXslFo(request, maReportForm);
                returnActionForward = mapping.findForward("ajaxXmlVal");
                break;
            case MaReportAction.BASE_ACTION_REPORT:
            	viewFoPDF(request, maReportForm);
                returnActionForward = mapping.findForward("pdfViewer");
            	break;
            default:
                returnActionForward = mapping.findForward("maReport");
                break;
        }
        return returnActionForward;
    }
    
    /**
     * Convert Excel to XSL-FO
     * @author  javaworker
     * @version $Id: MaReportAction.java,v 1.1 2015/09/03 07:57:16 brpark Exp $
     * @since   1.0
     * 
     * @param request
     * @param maReportForm
     */
    private void createXslFo(HttpServletRequest request, MaReportForm maReportForm) throws Exception
    {
        MaReportService maReportService = (MaReportService) getBean("maReportService");
        
        MaReportCommonDTO maReportCommonDTO = maReportForm.getMaReportCommonDTO();
        String webRoot = getServlet().getServletContext().getRealPath("/");
        
        maReportService.createXslFo(maReportCommonDTO, webRoot);
        
        setAjaxStatus(request, "VALID");
    }
    
    private void viewFoPDF(HttpServletRequest request, MaReportForm maReportForm)
    {
        String filePath = maReportForm.getMaReportCommonDTO().getFilePath();
//        System.out.println("!!!!filePath ::"+filePath);
//        System.out.println("!!!!fileName :::"+FileUtil.getFileName(filePath));
        request.setAttribute(REPORT_NAME_ATTRIBUTE, FileUtil.getFileName(filePath));
        request.setAttribute(REPORT_MAP_ATTRIBUTE, new HashMap());
    }
}