package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세 DTO
 * @author  kim21017
 * @version $Id: WorkListCavalDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListCavalDetailDTO extends BaseDTO
{
	/** 작업 id */
	private String wkOrId			= "";
	/** 측정값 id */
	private String woCalibValueId	= "";
	/** 설정치 */
	private String setVal			= "";
	/** 표준치 */
	private String basisVal			= "";
	/** 조정전지시치 */
	private String befVal			= "";
	/** 조정전오차 */
	private String befDiffVal 		= "";
	/** 조정후지시치 */
	private String aftVal 			= "";
	/** 조정후오차 */
	private String aftDiffVal 		= "";
	/** 비고 */
	private String remark 			= "";
	
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getWoCalibValueId() {
		return woCalibValueId;
	}
	public void setWoCalibValueId(String woCalibValueId) {
		this.woCalibValueId = woCalibValueId;
		super.setAuditKey(woCalibValueId);
	}
	public String getSetVal() {
		return setVal;
	}
	public void setSetVal(String setVal) {
		this.setVal = setVal;
	}
	public String getBasisVal() {
		return basisVal;
	}
	public void setBasisVal(String basisVal) {
		this.basisVal = basisVal;
	}
	public String getBefVal() {
		return befVal;
	}
	public void setBefVal(String befVal) {
		this.befVal = befVal;
	}
	public String getBefDiffVal() {
		return befDiffVal;
	}
	public void setBefDiffVal(String befDiffVal) {
		this.befDiffVal = befDiffVal;
	}
	public String getAftVal() {
		return aftVal;
	}
	public void setAftVal(String aftVal) {
		this.aftVal = aftVal;
	}
	public String getAftDiffVal() {
		return aftDiffVal;
	}
	public void setAftDiffVal(String aftDiffVal) {
		this.aftDiffVal = aftDiffVal;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}