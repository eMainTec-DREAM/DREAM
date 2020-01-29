package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���ȹ��� - �����۾� �� DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WoPlanWoLetDetailDTO extends BaseDTO
{
	/** WO�����۾����� id */
	private String woWoLetListId	= "";
	/** �����۾����� id */
	private String woLetCtgId		= "";
	/** �����۾����� No */
	private String woLetCtgNo		= "";
	/** �����۾� �� */
	private String woLetCtgDesc		= "";
	/** �����۾����� */
	private String woLetCtgType		= "";
	/** �����۾����� �� */
	private String woLetCtgTypeDesc	= "";
	/** ��� */
	private String remark 			= "";
	
	
	public String getWoWoLetListId() {
		return woWoLetListId;
	}
	public void setWoWoLetListId(String woWoLetListId) {
		this.woWoLetListId = woWoLetListId;
	}
	public String getWoLetCtgId() {
		return woLetCtgId;
	}
	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
	}
	public String getWoLetCtgNo() {
		return woLetCtgNo;
	}
	public void setWoLetCtgNo(String woLetCtgNo) {
		this.woLetCtgNo = woLetCtgNo;
	}
	public String getWoLetCtgDesc() {
		return woLetCtgDesc;
	}
	public void setWoLetCtgDesc(String woLetCtgDesc) {
		this.woLetCtgDesc = woLetCtgDesc;
	}
	public String getWoLetCtgType() {
		return woLetCtgType;
	}
	public void setWoLetCtgType(String woLetCtgType) {
		this.woLetCtgType = woLetCtgType;
	}
	public String getWoLetCtgTypeDesc() {
		return woLetCtgTypeDesc;
	}
	public void setWoLetCtgTypeDesc(String woLetCtgTypeDesc) {
		this.woLetCtgTypeDesc = woLetCtgTypeDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}