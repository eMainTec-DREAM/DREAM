package dream.fail.code.dto;

import common.bean.BaseDTO;

/**
 * �����ڵ� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaFailureCommonDTO extends BaseDTO
{
    
    /** ȸ���ڵ� */
    private String compNo                   = "";
    /** �ŷ�óId */
    private String failureId                = "";
    
    /** Filter - ȸ���ڵ� */
    private String filterCompNo             = "";
    /** Filter - ���屸�� */
    private String filterFailType           = "";
    /** Filter - ���屸�и� */
    private String filterFailTypeDesc       = "";
    /** Filter - ����� */
    private String filterFailureDesc        = "";
    /** Filter - ȭ��ǥ�ø� */
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
