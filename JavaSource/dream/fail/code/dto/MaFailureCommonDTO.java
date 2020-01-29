package dream.fail.code.dto;

import common.bean.BaseDTO;

/**
 * 고장코드 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaFailureCommonDTO extends BaseDTO
{
    
    /** 회사코드 */
    private String compNo                   = "";
    /** 거래처Id */
    private String failureId                = "";
    
    /** Filter - 회사코드 */
    private String filterCompNo             = "";
    /** Filter - 고장구분 */
    private String filterFailType           = "";
    /** Filter - 고장구분명 */
    private String filterFailTypeDesc       = "";
    /** Filter - 고장명 */
    private String filterFailureDesc        = "";
    /** Filter - 화면표시명 */
    private String filterFailName           = "";
    
    public String getFilterFailName() {
		return filterFailName;
	}

	public void setFilterFailName(String filterFailName) {
		this.filterFailName = filterFailName;
	}

	public String getFilterCompNo()
    {
        return filterCompNo;
    }

    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }

    public String getFilterFailType()
    {
        return filterFailType;
    }

    public void setFilterFailType(String filterFailType)
    {
        this.filterFailType = filterFailType;
    }

    public String getFilterFailTypeDesc()
    {
        return filterFailTypeDesc;
    }

    public void setFilterFailTypeDesc(String filterFailTypeDesc)
    {
        this.filterFailTypeDesc = filterFailTypeDesc;
    }

    public String getCompNo()
    {
        return compNo;
    }

    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }

    public String getFailureId()
    {
        return failureId;
    }

    public void setFailureId(String failureId)
    {
        this.failureId = failureId;
        super.setAuditKey(failureId);
    }

    public String getFilterFailureDesc()
    {
        return filterFailureDesc;
    }

    public void setFilterFailureDesc(String filterFailureDesc)
    {
        this.filterFailureDesc = filterFailureDesc;
    }
    
}
