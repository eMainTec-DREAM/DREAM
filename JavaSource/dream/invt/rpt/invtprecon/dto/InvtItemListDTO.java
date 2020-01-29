package dream.invt.rpt.invtprecon.dto;

import common.bean.BaseDTO;

/**
 * InvtItem Page - DTO
 * @author youngjoo38
 * @version $Id: InvtItemListDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class InvtItemListDTO extends BaseDTO
{
    
    /** �������� ID */ 
    private String invtListId             = "";
    /** �����׸� DESC  */ 
    private String invtItem               = "";
    /** �����ݾ� */ 
    private String planAmt                = "";
    /** ������� */ 
    private String curResult              = "";
    /** �ܾ� */ 
    private String balance                = "";
    
    public String getInvtListId()
    {
        return invtListId;
    }
    public void setInvtListId(String invtListId)
    {
        this.invtListId = invtListId;
    }
    public String getInvtItem()
    {
        return invtItem;
    }
    public void setInvtItem(String invtItem)
    {
        this.invtItem = invtItem;
    }
    public String getPlanAmt()
    {
        return planAmt;
    }
    public void setPlanAmt(String planAmt)
    {
        this.planAmt = planAmt;
    }
    public String getCurResult()
    {
        return curResult;
    }
    public void setCurResult(String curResult)
    {
        this.curResult = curResult;
    }
    public String getBalance()
    {
        return balance;
    }
    public void setBalance(String balance)
    {
        this.balance = balance;
    }
    
    
}
