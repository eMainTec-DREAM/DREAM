package dream.budget.account.dto;

import common.bean.BaseDTO;

/**
 * ������� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class BudgetAccountCommonDTO extends BaseDTO
{
	/** �������Id */
	private String accntId                     = "";
	/** ���� ��������ڵ� */
	private String filterAccntNo               = "";
	/** ���� ��������� */
	private String filterAccntDesc 		       = "";
	/** ���� ����������� */
	private String filterAccntType             = "";
	/** ���� ����������и� */
	private String filterAccntTypeDesc         = "";
	/** ��뿩�� */
	private String filterIsUse                 = "";
	
    public String getAccntId()
    {
        return accntId;
    }
    public void setAccntId(String accntId)
    {
        this.accntId = accntId;
    }
    public String getFilterAccntNo()
    {
        return filterAccntNo;
    }
    public void setFilterAccntNo(String filterAccntNo)
    {
        this.filterAccntNo = filterAccntNo;
    }
    public String getFilterAccntDesc()
    {
        return filterAccntDesc;
    }
    public void setFilterAccntDesc(String filterAccntDesc)
    {
        this.filterAccntDesc = filterAccntDesc;
    }
    public String getFilterAccntType()
    {
        return filterAccntType;
    }
    public void setFilterAccntType(String filterAccntType)
    {
        this.filterAccntType = filterAccntType;
    }
    public String getFilterAccntTypeDesc()
    {
        return filterAccntTypeDesc;
    }
    public void setFilterAccntTypeDesc(String filterAccntTypeDesc)
    {
        this.filterAccntTypeDesc = filterAccntTypeDesc;
    }
    public String getFilterIsUse()
    {
        return filterIsUse;
    }
    public void setFilterIsUse(String filterIsUse)
    {
        this.filterIsUse = filterIsUse;
    }
	
}
