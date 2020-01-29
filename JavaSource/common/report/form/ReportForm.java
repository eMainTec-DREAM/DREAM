package common.report.form;

import common.struts.BaseForm;

/**
 * ReportForm
 * @author  chivalryjjs
 * @version $Id: ReportForm.java,v 1.1 2013/08/30 09:14:43 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="reportForm"
 */
public class ReportForm extends BaseForm
{
    /** report name */
    private String reportName = "";
    /** report path */
    private String reportPath = "";
    /** qrd name */
    private String qrdName = "";
    /** result file name */
    private String resultFileName = "";

    /** 파라메터 갯수 */
    private int paramCount = 0;
    /** parameter 1 */
    private String param1 = "";
    /** parameter 2 */
    private String param2 = "";
    /** parameter 3 */
    private String param3 = "";
    /** parameter 4 */
    private String param4 = "";
    /** parameter 5 */
    private String param5 = "";
    /** parameter 6 */
    private String param6 = "";
    /** parameter 7 */
    private String param7 = "";
    /** parameter 8 */
    private String param8 = "";
    /** parameter 9 */
    private String param9 = "";
    /** parameter 10 */
    private String param10 = "";
    /** parameter 11 */
    private String param11 = "";
    /** parameter 12 */
    private String param12 = "";
    /** parameter 13 */
    private String param13 = "";
    /** parameter 14 */
    private String param14 = "";
    /** parameter 15 */
    private String param15 = "";
    
    public String getReportPath()
    {
        return reportPath;
    }

    public void setReportPath(String reportPath)
    {
        this.reportPath = reportPath;
    }

    public String getReportName()
    {
        return reportName;
    }

    public void setReportName(String reportName)
    {
        super.setAuditKey(reportName);
        this.reportName = reportName;
    }

    public String getParam1()
    {
        return param1;
    }

    public void setParam1(String param1)
    {
        this.param1 = initParam(param1);
    }

    public String getParam2()
    {
        return param2;
    }

    public void setParam2(String param2)
    {
        this.param2 = initParam(param2);
    }

    public String getParam3()
    {
        return param3;
    }

    public void setParam3(String param3)
    {
        this.param3 = initParam(param3);
    }

    public String getParam4()
    {
        return param4;
    }

    public void setParam4(String param4)
    {
        this.param4 = initParam(param4);
    }

    public String getParam5()
    {
        return param5;
    }

    public void setParam5(String param5)
    {
        this.param5 = initParam(param5);
    }

    public String getParam6()
    {
        return param6;
    }

    public void setParam6(String param6)
    {
        this.param6 = initParam(param6);
    }

    public String getParam7()
    {
        return param7;
    }

    public void setParam7(String param7)
    {
        this.param7 = initParam(param7);
    }

    public String getParam8()
    {
        return param8;
    }

    public void setParam8(String param8)
    {
        this.param8 = initParam(param8);
    }

    public String getParam9()
    {
        return param9;
    }

    public void setParam9(String param9)
    {
        this.param9 = initParam(param9);
    }

    public String getResultFileName()
    {
        return resultFileName;
    }

    public void setResultFileName(String resultFileName)
    {
        this.resultFileName = resultFileName;
    }

    public int getParamCount()
    {
        return paramCount;
    }

    public void setParamCount(int paramCount)
    {
        this.paramCount = paramCount;
    }
    
    private String initParam(String value)
    {
        return value==null?"":value;
    }

    public String getQrdName()
    {
        return qrdName;
    }

    public void setQrdName(String qrdName)
    {
        this.qrdName = qrdName;
    }

    public String getParam10()
    {
        return param10;
    }

    public void setParam10(String param10)
    {
        this.param10 = initParam(param10);
    }

    public String getParam11()
    {
        return param11;
    }

    public void setParam11(String param11)
    {
        this.param11 = initParam(param11);
    }

    public String getParam12()
    {
        return param12;
    }

    public void setParam12(String param12)
    {
        this.param12 = initParam(param12);
    }

    public String getParam13()
    {
        return param13;
    }

    public void setParam13(String param13)
    {
        this.param13 = initParam(param13);
    }

    public String getParam14()
    {
        return param14;
    }

    public void setParam14(String param14)
    {
        this.param14 = initParam(param14);
    }

    public String getParam15()
    {
        return param15;
    }

    public void setParam15(String param15)
    {
        this.param15 = initParam(param15);
    }
}
