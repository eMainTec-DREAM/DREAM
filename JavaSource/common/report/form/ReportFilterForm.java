package common.report.form;

import common.struts.BaseForm;

/**
 * @author  wondo
 * @version $Id: ReportFilterForm.java,v 1.1 2013/08/30 09:14:43 javaworker Exp $
 * @since   1.0
 * 
 * @struts.form name="reportFilterForm"
 */
public class ReportFilterForm extends BaseForm
{
    /** report name */
    private String reportName = "";
    /** equip no */
    private String equipNo = "";
    /** description */
    private String itemDesc = "";
    /** item no */
    private String itemNo = "";
    
    /** 하위포함 */
    private boolean allTree;

    public String getReportName()
    {
        return reportName;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public String getEquipNo()
    {
        return equipNo;
    }

    public void setEquipNo(String equipNo)
    {
        this.equipNo = equipNo;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public String getItemNo()
    {
        return itemNo;
    }

    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }

    public boolean getAllTree()
    {
        return allTree;
    }

    public void setAllTree(boolean allTree)
    {
        this.allTree = allTree;
    }
    
}
