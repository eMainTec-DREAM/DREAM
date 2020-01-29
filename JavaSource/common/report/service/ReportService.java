package common.report.service;

import java.io.IOException;
import java.util.List;

import common.bean.User;
import common.report.form.ReportForm;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;


/**
 * Report ��� ��ȸ
 * @author  javaworker
 * @version $Id: ReportService.java,v 1.1 2013/08/30 09:14:49 javaworker Exp $
 * @since   1.0
 *
 */
public interface ReportService
{
    /**
     * Report �����ȸ�� �Ѵ�.
     * @author  javaworker
     * @version $Id: ReportService.java,v 1.1 2013/08/30 09:14:49 javaworker Exp $
     * @since   1.0
     * 
     * @param reportForm
     */
    void makeResultFile(ReportForm reportForm);
    
    public String viewReport(String jasperReportName, List resultList, User user) throws JRException, DRException, IOException;
    
    String viewReport(String jasperReportName, String jsonStr, User user) throws JRException, DRException, IOException;
}
