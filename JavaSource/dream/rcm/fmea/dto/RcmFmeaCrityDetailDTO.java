package dream.rcm.fmea.dto;

import common.bean.BaseDTO;

/**
 * 상세  DTO
 * @author  kim21017
 * @version $Id: RcmFmeaCrityDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class RcmFmeaCrityDetailDTO extends BaseDTO
{
	/**  */
	private String critylistId	= "";
	/** 비고 */
	private String remark = "";
	/** Critical 여부[Y/N] */
	private String isCritical = "";
	/** 색상 */
	private String critycolor = "";
	/** 설정값 */
	private String crityvalue = "";
	/** 로우항목명 */
	private String rowName = "";
	/** 컬럼항목명 */
	private String colName = "";
	/** RCM 시스템 분석 리스트 id */
	private String rcmlistId = "";
	/** RCM FMEA ID */
	private String rcmfmeaId = "";
	/** Criticality ID */
	private String rcmcrityId = "";
	/** 회사코드 */
	private String compNo = "";
	/** 중요도레벨 */
	private String crityLvl = "";
	
	private String rowOrdNo	= "";
	
	private String colOrdNo	= "";
	/** Crity Value ID */
	private String crityvalueId	= "";
	
	private String critycolId	= "";

	private String crityrowId	= "";
	
	
	public String getCritycolId() {
		return critycolId;
	}
	public void setCritycolId(String critycolId) {
		this.critycolId = critycolId;
	}
	public String getCrityrowId() {
		return crityrowId;
	}
	public void setCrityrowId(String crityrowId) {
		this.crityrowId = crityrowId;
	}
	public String getCrityvalueId() {
		return crityvalueId;
	}
	public void setCrityvalueId(String crityvalueId) {
		this.crityvalueId = crityvalueId;
	}
	public String getRowOrdNo() {
		return rowOrdNo;
	}
	public void setRowOrdNo(String rowOrdNo) {
		this.rowOrdNo = rowOrdNo;
	}
	public String getColOrdNo() {
		return colOrdNo;
	}
	public void setColOrdNo(String colOrdNo) {
		this.colOrdNo = colOrdNo;
	}
	public String getCritylistId() {
		return critylistId;
	}
	public void setCritylistId(String critylistId) {
		this.critylistId = critylistId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsCritical() {
		return isCritical;
	}
	public void setIsCritical(String isCritical) {
		this.isCritical = isCritical;
	}
	public String getCritycolor() {
		return critycolor;
	}
	public void setCritycolor(String critycolor) {
		this.critycolor = critycolor;
	}
	public String getCrityvalue() {
		return crityvalue;
	}
	public void setCrityvalue(String crityvalue) {
		this.crityvalue = crityvalue;
	}
	public String getRowName() {
		return rowName;
	}
	public void setRowName(String rowName) {
		this.rowName = rowName;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getRcmlistId() {
		return rcmlistId;
	}
	public void setRcmlistId(String rcmlistId) {
		this.rcmlistId = rcmlistId;
	}
	public String getRcmfmeaId() {
		return rcmfmeaId;
	}
	public void setRcmfmeaId(String rcmfmeaId) {
		this.rcmfmeaId = rcmfmeaId;
	}
	public String getRcmcrityId() {
		return rcmcrityId;
	}
	public void setRcmcrityId(String rcmcrityId) {
		this.rcmcrityId = rcmcrityId;
		super.setAuditKey(rcmcrityId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getCrityLvl() {
		return crityLvl;
	}
	public void setCrityLvl(String crityLvl) {
		this.crityLvl = crityLvl;
	}
	
	
}