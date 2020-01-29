package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업계획목록 - 안전작업 상세 DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WoPlanWoLetDetailDTO extends BaseDTO
{
	/** WO안전작업유형 id */
	private String woWoLetListId	= "";
	/** 안전작업유형 id */
	private String woLetCtgId		= "";
	/** 안전작업유형 No */
	private String woLetCtgNo		= "";
	/** 안전작업 명 */
	private String woLetCtgDesc		= "";
	/** 안전작업유형 */
	private String woLetCtgType		= "";
	/** 안전작업유형 명 */
	private String woLetCtgTypeDesc	= "";
	/** 비고 */
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