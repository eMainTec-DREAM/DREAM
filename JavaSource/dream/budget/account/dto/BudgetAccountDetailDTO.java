package dream.budget.account.dto;

import common.bean.BaseDTO;

/**
 * ������� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class BudgetAccountDetailDTO extends BaseDTO
{
	/** �������ID */
	private String accntId 			= "";
	/** ��������ڵ� */
	private String accntNo 			= "";
	/** ��������� */
	private String accntDesc 		= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** ��� */
	private String remark           = "";
	/** ����������� */
	private String accntType        = "";
	/** ����������и� */
	private String accntTypeDesc    = "";
	
    public String getAccntId()
    {
        return accntId;
    }
    public void setAccntId(String accntId)
    {
        this.accntId = accntId;
    }
    public String getAccntNo()
    {
        return accntNo;
    }
    public void setAccntNo(String accntNo)
    {
        this.accntNo = accntNo;
    }
    public String getAccntDesc()
    {
        return accntDesc;
    }
    public void setAccntDesc(String accntDesc)
    {
        this.accntDesc = accntDesc;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getAccntType()
    {
        return accntType;
    }
    public void setAccntType(String accntType)
    {
        this.accntType = accntType;
    }
    public String getAccntTypeDesc()
    {
        return accntTypeDesc;
    }
    public void setAccntTypeDesc(String accntTypeDesc)
    {
        this.accntTypeDesc = accntTypeDesc;
    }
	
}
