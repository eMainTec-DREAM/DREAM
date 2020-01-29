package dream.org.wrkgrp.dto;

import common.bean.BaseDTO;

/**
 * �۾��׷� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaWkCtrCommonDTO extends BaseDTO
{
	
	/** �۾��׷�Id */
	private String wkCtrId                  = "";
	/** ���� - �۾��׷�No */
	private String filterWkCtrNo            = "";
	/** ���� - �۾��׷�� */
	private String filterWkCtrDesc          = "";
	
	/** ����-��뿩�� */
	private String filterIsUse	 		    = "";
	/** ���� ���� �۾��׷� Id */
	private String filterPaWkCtrId         	= "";
	/** ���� ���� �۾��׷� �� */
	private String filterPaWkCtrDesc 		= "";
	/** ������ detail ���� �۾��׷� Id */
	private String detailPaWkCtrId         	= "";
	/** ������ detail ���� �۾��׷� �� */
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
