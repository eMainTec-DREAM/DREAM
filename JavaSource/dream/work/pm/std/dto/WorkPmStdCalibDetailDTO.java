package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * ����ǥ�ذ� Ÿ�� - Detail DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibDetailDTO extends BaseDTO
{
	/** ����ǥ�ذ� Ÿ�� ID */
	private String pmCalibStdTpId				= "";
	/** ����ǥ�ذ� No */
	private String pmCalibStdTpNo				= "";
	/** �������� ID */
	private String pmcTypeId					= "";
	/** �������� �� */
	private String pmcTypeDesc					= "";
	/** �������� */
	private String measureRange					= "";
	/** ��� */
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
