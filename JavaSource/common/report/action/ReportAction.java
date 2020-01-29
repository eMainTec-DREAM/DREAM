package common.report.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.report.form.ReportForm;
import common.report.service.ReportService;
import common.struts.SuperAuthAction;

/**
 * Report Designer Common Action
 * @author  javaworker
 * @version $Id: ReportAction.java,v 1.1 2014/03/08 14:29:38 hankyul Exp $
 * @since   1.0
 * @struts:action path="/report" name="reportForm"
 *                input="/report/report.jsp" scope="request"
 *                validate="false"
 * @struts.action-forward name="report" path="/common/report/report.jsp"
 *                        redirect="false" 
 * @struts.action-forward name="rdReport" path="/common/report/rdReport.jsp"
 *                        redirect="false" 
 */
public class ReportAction extends SuperAuthAction
{

    /** 레포트 디자이너 기본 호출 */
    public static final int REPORT_CALL     = 4001;
    /** 팝업창에서 선택하여 호출 */
    public static final int RD_CALL         = 4002;
    
    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ReportForm reportForm = (ReportForm) form;
        ActionForward returnActionForward = null;
     
        Map map = (Map)request.getAttribute("auditMap");
        
        map.put("currentPageId", reportForm.getAuditKey());
        super.updateAudit("", map, getUser(request));
        
        switch (reportForm.getStrutsAction())
        {
            case ReportAction.REPORT_CALL:
                makeReportResult(reportForm);
                returnActionForward = mapping.findForward("report");
                break;
            case ReportAction.RD_CALL:
                returnActionForward = mapping.findForward("rdReport");
                break;
            default:
            	returnActionForward = mapping.getInputForward();
                break;
        }
        
        return returnActionForward;
    }
    
    /**
     * 조회 결과 생성 처리
     * @author  javaworker
     * @version $Id: ReportAction.java,v 1.2 2013/12/23 06:34:52 pochul2423 Exp $
     * @since   1.0
     * 
     * @param reportForm
     */
    private void makeReportResult(ReportForm reportForm) throws Exception
    {
        //점검결과입력
        if ("pmiCheckWorker".equals(reportForm.getReportName()) | "pmiCheckTeam".equals(reportForm.getReportName()))
        {
//        	PmiCheckMngService pmiCheckMngService = (PmiCheckMngService) getBean("pmiCheckMngServiceTarget");
//        	
//        	pmiCheckMngService.makeResultFile(reportForm);
        }
        //일상점검입력
        else if ("pmiDaySingle".equals(reportForm.getReportName()))
        {
//            PmiDaySingleService pmiDaySingleService = (PmiDaySingleService) getBean("pmiDaySingleServiceTarget");
//            
//            pmiDaySingleService.makeResultFile(reportForm);
        }
        //일상점검입력
        else if ("pmiDayMulti".equals(reportForm.getReportName()))
        {
//            PmiDayMultiService pmiDayMultiService = (PmiDayMultiService) getBean("pmiDayMultiServiceTarget");
//            
//            pmiDayMultiService.makeResultFile(reportForm);
        }
        else
        {
            ReportService reportService = (ReportService)getBean("reportServiceTarget");
            
            reportService.makeResultFile(reportForm);
        }
        
    }
}