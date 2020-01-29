package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 - 분류  DTO
 * @author  kim21017
 * @version $Id: MaCdSysCodeDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaCdSysCodeDetailDTO extends BaseDTO
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
    /** parameter1 */
    private String param1		= "";
    /** parameter2 */
    private String param2		= "";
    /** parameter3 */
    private String param3		= "";
    /** 다국어 keyno */
	private String keyNo			= "";
	/** 다국어 keytype */
	private String keyType			= "";
	//key_no validation 시 넘기는 key_type
	private String keyTypeStr		= "";
	private String listType		= "";
	
	
    
    
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
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