package dream.org.wrkgrp.dto;

import common.bean.BaseDTO;

/**
 * 작업그룹 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaWkCtrCommonDTO extends BaseDTO
{
	
	/** 작업그룹Id */
	private String wkCtrId                  = "";
	/** 필터 - 작업그룹No */
	private String filterWkCtrNo            = "";
	/** 필터 - 작업그룹명 */
	private String filterWkCtrDesc          = "";
	
	/** 필터-사용여부 */
	private String filterIsUse	 		    = "";
	/** 필터 상위 작업그룹 Id */
	private String filterPaWkCtrId         	= "";
	/** 필터 상위 작업그룹 명 */
	private String filterPaWkCtrDesc 		= "";
	/** 생성시 detail 상위 작업그룹 Id */
	private String detailPaWkCtrId         	= "";
	/** 생성시 detail 상위 작업그룹 명 */
	private String detailPaWkCtrDesc 		= "";
	
	
	public String getDetailPaWkCtrId() {
		return detailPaWkCtrId;
	}
	public void setDetailPaWkCtrId(String detailPaWkCtrId) {
		this.detailPaWkCtrId = detailPaWkCtrId;
	}
	public String getDetailPaWkCtrDesc() {
		return detailPaWkCtrDesc;
	}
	public void setDetailPaWkCtrDesc(String detailPaWkCtrDesc) {
		this.detailPaWkCtrDesc = detailPaWkCtrDesc;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
		super.setAuditKey(wkCtrId);
	}
	public String getFilterWkCtrNo() {
		return filterWkCtrNo;
	}
	public void setFilterWkCtrNo(String filterWkCtrNo) {
		this.filterWkCtrNo = filterWkCtrNo;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterIsUse() {
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) {
		this.filterIsUse = filterIsUse;
	}
	public String getFilterPaWkCtrId() {
		return filterPaWkCtrId;
	}
	public void setFilterPaWkCtrId(String filterPaWkCtrId) {
		this.filterPaWkCtrId = filterPaWkCtrId;
	}
	public String getFilterPaWkCtrDesc() {
		return filterPaWkCtrDesc;
	}
	public void setFilterPaWkCtrDesc(String filterPaWkCtrDesc) {
		this.filterPaWkCtrDesc = filterPaWkCtrDesc;
	}
}
