package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� - �з�  DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MgrCdSysCodeDetailDTO extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysDId 	= "";
    /** �з��ڵ� */
    private String code 		= "";
    /** �з��� */
    private String codeDesc 	= "";
    /** �з����� */
    private String remark 		= "";
    /** ��ȸ���� */
    private String ordNo 		= "";
    /** ��뿩�� */
    private String isUse 		= "";
    /** ��뿩��desc */
    private String isUseDesc	= "";
    /** �ٱ��� keyno */
	private String keyNo			= "";
	/** �ٱ��� keytype */
	private String keyType			= "";
	//key_no validation �� �ѱ�� key_type
	private String keyTypeStr		= "";
	private String listType		= "";
	
	
    
    
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getCdSysDId() {
		return cdSysDId;
	}
	public void setCdSysDId(String cdSysDId) {
		this.cdSysDId = cdSysDId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
	
}