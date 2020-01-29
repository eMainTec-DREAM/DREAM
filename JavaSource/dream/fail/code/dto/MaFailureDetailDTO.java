package dream.fail.code.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����ڵ� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaFailureDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 	       = "";
	/** ����ID */
	private String failureId       = "";
	/** �����ڵ� */
	private String failureNo       = "";
	/** �����ڵ�� */
	private String failureDesc 	   = "";
	/** ���屸��(�ڵ�����) */
	private String failType	       = "";
	/** ���屸��(�ڵ�����) */
	private String failTypeDesc    = "";
	/** ��뿩�� */
	private String isUse           = "";
	/** ���� */
	private String ordNo           = "";
	/** ��� */
	private String remark          = "";
	/** �ٱ��� keyno */
	private String keyNo			= "";
	/** �ٱ��� keytype */
	private String keyType			= "";
	//key_no validation �� �ѱ�� key_type
	private String keyTypeStr		= "";
	/** �ٱ��� ǥ�ø� */
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
