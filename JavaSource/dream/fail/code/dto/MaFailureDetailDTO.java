package dream.fail.code.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장코드 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaFailureDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 	       = "";
	/** 고장ID */
	private String failureId       = "";
	/** 고장코드 */
	private String failureNo       = "";
	/** 고장코드명 */
	private String failureDesc 	   = "";
	/** 고장구분(코드유형) */
	private String failType	       = "";
	/** 고장구분(코드유형) */
	private String failTypeDesc    = "";
	/** 사용여부 */
	private String isUse           = "";
	/** 순서 */
	private String ordNo           = "";
	/** 비고 */
	private String remark          = "";
	/** 다국어 keyno */
	private String keyNo			= "";
	/** 다국어 keytype */
	private String keyType			= "";
	//key_no validation 시 넘기는 key_type
	private String keyTypeStr		= "";
	/** 다국어 표시명 */
	private String failName			= "";
	
    public String getFailName() {
		return failName;
	}
	public void setFailName(String failName) {
		this.failName = failName;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyTypeStr() {
		return keyTypeStr;
	}
	public void setKeyTypeStr(String keyTypeStr) {
		this.keyTypeStr = keyTypeStr;
	}
	public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
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
    public String getFailureNo()
    {
        return failureNo;
    }
    public void setFailureNo(String failureNo)
    {
        this.failureNo = failureNo;
    }
   
    
    public String getFailureDesc()
    {
        return failureDesc;
    }
    public void setFailureDesc(String failureDesc)
    {
        this.failureDesc = failureDesc;
    }
    public String getFailType()
    {
        return failType;
    }
    public void setFailType(String failType)
    {
        this.failType = failType;
    }
    public String getFailTypeDesc()
    {
        return failTypeDesc;
    }
    public void setFailTypeDesc(String failTypeDesc)
    {
        this.failTypeDesc = failTypeDesc;
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
	
}
