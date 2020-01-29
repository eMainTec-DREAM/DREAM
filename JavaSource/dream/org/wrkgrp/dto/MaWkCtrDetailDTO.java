package dream.org.wrkgrp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾��׷� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaWkCtrDetailDTO extends BaseDTO
{
	/** �۾��׷�ID */
	private String wkCtrId 			= "";
	/** �۾��׷�No */
	private String wkCtrNo 			= "";
	/** �۾��׷�� */
	private String wkCtrDesc 		= "";
	/** �����۾��׷�Id */
	private String paWkCtrId		= "";
	/** �����۾��׷�� */
	private String paWkCtrDesc		= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** ��ȸ���� */
	private String ordNo 			= "";
	/** ��� */
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
