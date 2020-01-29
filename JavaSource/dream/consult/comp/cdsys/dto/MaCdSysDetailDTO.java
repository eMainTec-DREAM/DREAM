package dream.consult.comp.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� - �� DTO
 * @author  kim21017
 * @version $Id: MaCdSysDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaCdSysDetailDTO extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysMId 			= "";
	/** �ڵ����� */
	private String listType 			= "";
	/** �ڵ������� */
	private String listTypeDesc 		= "";
	/** �ڵ弳�� */
	private String remark 				= "";
	/** ��뿩�� */
	private String isUse 				= "";
	/** �ٱ��� keyno */
	private String keyNo				= "";
	/** �ٱ��� keytype */
	private String keyType				= "";
	//key_no validation �� �ѱ�� key_type
	private String keyTypeStr			= "";
	
	private String listCateg			= "";
	/** �ý��ۿ��� */
	private String isSystem 			= "";
	
	
	
	public String getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
	public String getListCateg() {
		return listCateg;
	}
	public void setListCateg(String listCateg) {
		this.listCateg = listCateg;
	}
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
