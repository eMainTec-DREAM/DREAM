package dream.work.pm.std.dto;

import common.bean.BaseDTO;
/**
 * 교정표준값 타입
 * @author kim21017
 * @version $Id: WorkPmStdCalibCommonDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibCommonDTO extends BaseDTO
{
	/** 교정표준값 타입 ID */
	private String pmCalibStdTpId					= "";
	/** Filter 교정표준값 No */
	private String filterPmCalibStdTpNo				= "";
	/** Filter 교정구분 ID */
	private String filterPmcTypeId					= "";
	/** Filter 교정구분 명 */
	private String filterPmcTypeDesc				= "";
	
	// 중복확인용 id
	private String validId							= "";
	// 중복확인용 교정구분 id
	private String validPmcTypeId					= "";
	
	public String getFilterPmCalibStdTpNo() {
		return filterPmCalibStdTpNo;
	}
	public String getValidId() {
		return validId;
	}
	public void setValidId(String validId) {
		this.validId = validId;
	}
	public String getValidPmcTypeId() {
		return validPmcTypeId;
	}
	public void setValidPmcTypeId(String validPmcTypeId) {
		this.validPmcTypeId = validPmcTypeId;
	}
	public void setFilterPmCalibStdTpNo(String filterPmCalibStdTpNo) {
		this.filterPmCalibStdTpNo = filterPmCalibStdTpNo;
	}
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
		super.setAuditKey(pmCalibStdTpId);
	}
	public String getFilterPmcTypeId() {
		return filterPmcTypeId;
	}
	public void setFilterPmcTypeId(String filterPmcTypeId) {
		this.filterPmcTypeId = filterPmcTypeId;
	}
	public String getFilterPmcTypeDesc() {
		return filterPmcTypeDesc;
	}
	public void setFilterPmcTypeDesc(String filterPmcTypeDesc) {
		this.filterPmcTypeDesc = filterPmcTypeDesc;
	}
	
	
}
