package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * 시스템코드 - 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class MgrCdSysDetailDTO extends BaseDTO
{
	/** 시스템코드유형상세ID */
	private String cdSysMId 			= "";
	/** 코드유형 */
	private String listType 			= "";
	/** 코드유형명 */
	private String listTypeDesc 		= "";
	/** 코드설명 */
	private String remark 				= "";
	/** 다국어 keyno */
	private String keyNo				= "";
	/** 다국어 keytype */
	private String keyType				= "";
	//key_no validation 시 넘기는 key_type
	private String keyTypeStr			= "";
	
	public String getCdSysMId() {
		return cdSysMId;
	}
	public void setCdSysMId(String cdSysMId) {
		this.cdSysMId = cdSysMId;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getListTypeDesc() {
		return listTypeDesc;
	}
	public void setListTypeDesc(String listTypeDesc) {
		this.listTypeDesc = listTypeDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
