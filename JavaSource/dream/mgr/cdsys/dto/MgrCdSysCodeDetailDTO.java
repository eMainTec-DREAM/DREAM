package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 - 분류  DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MgrCdSysCodeDetailDTO extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysDId 	= "";
    /** 분류코드 */
    private String code 		= "";
    /** 분류명 */
    private String codeDesc 	= "";
    /** 분류설명 */
    private String remark 		= "";
    /** 조회순서 */
    private String ordNo 		= "";
    /** 사용여부 */
    private String isUse 		= "";
    /** 사용여부desc */
    private String isUseDesc	= "";
    /** 다국어 keyno */
	private String keyNo			= "";
	/** 다국어 keytype */
	private String keyType			= "";
	//key_no validation 시 넘기는 key_type
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