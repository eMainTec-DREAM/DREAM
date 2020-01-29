package dream.main.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Inspection Chart DTO
 * @author  pochul2423
 * @version $Id: PartChartDTO.java,v 1.2 2014/03/11 02:03:59 pochul2423 Exp $
 * @since   1.0
 */
public class PartChartDTO extends BaseDTO
{
    /**  입고 1달*/
    private String monthRec1 = "";
    /**  입고 2달*/
    private String monthRec2 = "";
    /**  입고 3달*/
    private String monthRec3 = "";
    /**  입고 4달*/
    private String monthRec4 = "";
    /**  입고 5달*/
    private String monthRec5 = "";
    /**  입고 6달*/
    private String monthRec6 = "";
    
    
    /**  출고 1달*/
    private String monthIss1 = "";
    /**  출고 2달*/
    private String monthIss2 = "";
    /**  출고 3달*/
    private String monthIss3 = "";
    /**  출고 4달*/
    private String monthIss4 = "";
    /**  출고 5달*/
    private String monthIss5 = "";
    /**  출고 6달*/
    private String monthIss6 = "";
    
    /**  Month Array*/
    private String monthArray = "";
    
    /**  입고 Total*/
    private String recTotal = "";
    /**  출고 Total*/
    private String issTotal = "";
    
    public String getRecTotal()
    {
        return recTotal;
    }
    public void setRecTotal(String recTotal)
    {
        this.recTotal = CommonUtil.convertMoney(recTotal);
    }
    public String getIssTotal()
    {
        return issTotal;
    }
    public void setIssTotal(String issTotal)
    {
        this.issTotal = CommonUtil.convertMoney(issTotal);
    }
    public String getMonthArray()
    {
        return monthArray;
    }
    public void setMonthArray(String monthArray)
    {
        this.monthArray = monthArray;
    }
    public String getMonthRec1()
    {
        return monthRec1;
    }
    public void setMonthRec1(String monthRec1)
    {
        this.monthRec1 = monthRec1;
    }
    public String getMonthRec2()
    {
        return monthRec2;
    }
    public void setMonthRec2(String monthRec2)
    {
        this.monthRec2 = monthRec2;
    }
    public String getMonthRec3()
    {
        return monthRec3;
    }
    public void setMonthRec3(String monthRec3)
    {
        this.monthRec3 = monthRec3;
    }
    public String getMonthRec4()
    {
        return monthRec4;
    }
    public void setMonthRec4(String monthRec4)
    {
        this.monthRec4 = monthRec4;
    }
    public String getMonthRec5()
    {
        return monthRec5;
    }
    public void setMonthRec5(String monthRec5)
    {
        this.monthRec5 = monthRec5;
    }
    public String getMonthRec6()
    {
        return monthRec6;
    }
    public void setMonthRec6(String monthRec6)
    {
        this.monthRec6 = monthRec6;
    }
    public String getMonthIss1()
    {
        return monthIss1;
    }
    public void setMonthIss1(String monthIss1)
    {
        this.monthIss1 = monthIss1;
    }
    public String getMonthIss2()
    {
        return monthIss2;
    }
    public void setMonthIss2(String monthIss2)
    {
        this.monthIss2 = monthIss2;
    }
    public String getMonthIss3()
    {
        return monthIss3;
    }
    public void setMonthIss3(String monthIss3)
    {
        this.monthIss3 = monthIss3;
    }
    public String getMonthIss4()
    {
        return monthIss4;
    }
    public void setMonthIss4(String monthIss4)
    {
        this.monthIss4 = monthIss4;
    }
    public String getMonthIss5()
    {
        return monthIss5;
    }
    public void setMonthIss5(String monthIss5)
    {
        this.monthIss5 = monthIss5;
    }
    public String getMonthIss6()
    {
        return monthIss6;
    }
    public void setMonthIss6(String monthIss6)
    {
        this.monthIss6 = monthIss6;
    }
}