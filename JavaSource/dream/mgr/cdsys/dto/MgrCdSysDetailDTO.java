package dream.mgr.cdsys.dto;

import common.bean.BaseDTO;

/**
 * �ý����ڵ� - �� DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class MgrCdSysDetailDTO extends BaseDTO
{
	/** �ý����ڵ�������ID */
	private String cdSysMId 			= "";
	/** �ڵ����� */
	private String listType 			= "";
	/** �ڵ������� */
	private String listTypeDesc 		= "";
	/** �ڵ弳�� */
	private String remark 				= "";
	/** �ٱ��� keyno */
	private String keyNo				= "";
	/** �ٱ��� keytype */
	private String keyType				= "";
	//key_no validation �� �ѱ�� key_type
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
