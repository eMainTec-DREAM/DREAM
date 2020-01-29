package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 교정표준값 타입 - Detail DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibDetailDTO extends BaseDTO
{
	/** 교정표준값 타입 ID */
	private String pmCalibStdTpId				= "";
	/** 교정표준값 No */
	private String pmCalibStdTpNo				= "";
	/** 교정구분 ID */
	private String pmcTypeId					= "";
	/** 교정구분 명 */
	private String pmcTypeDesc					= "";
	/** 계측범위 */
	private String measureRange					= "";
	/** 비고 */
	private String remark						= "";
	
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
		super.setAuditKey(pmCalibStdTpId);
	}
	public String getPmCalibStdTpNo() {
		return pmCalibStdTpNo;
	}
	public void setPmCalibStdTpNo(String pmCalibStdTpNo) {
		this.pmCalibStdTpNo = pmCalibStdTpNo;
	}
	public String getPmcTypeId() {
		return pmcTypeId;
	}
	public void setPmcTypeId(String pmcTypeId) {
		this.pmcTypeId = pmcTypeId;
	}
	public String getPmcTypeDesc() {
		return pmcTypeDesc;
	}
	public void setPmcTypeDesc(String pmcTypeDesc) {
		this.pmcTypeDesc = pmcTypeDesc;
	}
	public String getMeasureRange() {
		return measureRange;
	}
	public void setMeasureRange(String measureRange) {
		this.measureRange = measureRange;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
