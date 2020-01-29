package dream.invt.rpt.invtprecon.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * InvtRptInvtPreCon Page - Detail DTO
 * @author youngjoo38
 * @version $Id: InvtRptInvtPreConDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class InvtRptInvtPreConDetailDTO extends BaseDTO
{
    /** �μ� ID */
    private String deptId                 = "";
    /** �μ� DESC */ 
    private String deptDesc               = "";
    /** �⵵ */ 
    private String year                   = "";
    /** �����ݾ� */ 
    private String planAmt                = "";
    /** ������� */ 
    private String curResult              = "";
    /** �ܾ� */ 
    private String balance                = "";
    
    
    public String getBalance()
    {
        return balance;
    }
    public void setBalance(String balance)
    {
        this.balance = CommonUtil.convertMoney(balance);
    }
    public String getCurResult()
    {
        return curResult;
    }
    public void setCurResult(String curResult)
    {
        this.curResult = CommonUtil.convertMoney(curResult);
    }
    public String getPlanAmt()
    {
        return planAmt;
    }
    public void setPlanAmt(String planAmt)
    {
        this.planAmt = CommonUtil.convertMoney(planAmt);
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getYear()
    {
        return year;
    }
    public void setYear(String year)
    {
        this.year = year;
    }
}
