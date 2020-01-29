package dream.budget.account.dto;

import common.bean.BaseDTO;

/**
 * 예산계정 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class BudgetAccountCommonDTO extends BaseDTO
{
	/** 예산계정Id */
	private String accntId                     = "";
	/** 필터 예산계정코드 */
	private String filterAccntNo               = "";
	/** 필터 예산계정명 */
	private String filterAccntDesc 		       = "";
	/** 필터 예산계정구분 */
	private String filterAccntType             = "";
	/** 필터 예산계정구분명 */
	private String filterAccntTypeDesc         = "";
	/** 사용여부 */
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
