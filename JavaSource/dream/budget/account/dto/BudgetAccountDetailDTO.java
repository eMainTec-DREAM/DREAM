package dream.budget.account.dto;

import common.bean.BaseDTO;

/**
 * 예산계정 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class BudgetAccountDetailDTO extends BaseDTO
{
	/** 예산계정ID */
	private String accntId 			= "";
	/** 예산계정코드 */
	private String accntNo 			= "";
	/** 예산계정명 */
	private String accntDesc 		= "";
	/** 사용여부 */
	private String isUse 			= "";
	/** 비고 */
	private String remark           = "";
	/** 예산계정구분 */
	private String accntType        = "";
	/** 예산계정구분명 */
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
