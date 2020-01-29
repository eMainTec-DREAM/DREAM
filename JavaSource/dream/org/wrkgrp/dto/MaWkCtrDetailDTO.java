package dream.org.wrkgrp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업그룹 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaWkCtrDetailDTO extends BaseDTO
{
	/** 작업그룹ID */
	private String wkCtrId 			= "";
	/** 작업그룹No */
	private String wkCtrNo 			= "";
	/** 작업그룹명 */
	private String wkCtrDesc 		= "";
	/** 상위작업그룹Id */
	private String paWkCtrId		= "";
	/** 상위작업그룹명 */
	private String paWkCtrDesc		= "";
	/** 사용여부 */
	private String isUse 			= "";
	/** 조회순서 */
	private String ordNo 			= "";
	/** 비고 */
	private String remark			= "";
	
	
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
		super.setAuditKey(wkCtrId);
	}
	public String getWkCtrNo() {
		return wkCtrNo;
	}
	public void setWkCtrNo(String wkCtrNo) {
		this.wkCtrNo = wkCtrNo;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getPaWkCtrId() {
		return paWkCtrId;
	}
	public void setPaWkCtrId(String paWkCtrId) {
		this.paWkCtrId = paWkCtrId;
	}
	public String getPaWkCtrDesc() {
		return paWkCtrDesc;
	}
	public void setPaWkCtrDesc(String paWkCtrDesc) {
		this.paWkCtrDesc = paWkCtrDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
