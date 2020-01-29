package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준 교정값 - Detail DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibValDetailDTO extends BaseDTO
{
	/** 표준교정값 타입 ID */
	private String pmCalibStdTpId				= "";
	/** 교정값 ID*/
	private String pmCalibStdValueId 			= "";
	/** 회차/구분 */
	private String calibPointTypeId				= "";
	/** 회차/구분 */
	private String calibPointTypeDesc			= "";
	/** 교정POINT */
	private String calibPoint					= "";
	/** 허용오차 */
	private String allowValue 					= "";
	/** AS-FOUND(표준값)*/
	private String asfStdValue	 				= "";
	/** AS-LEFT(표준값)*/
	private String aslStdValue 					= "";
	/** 조회순서 */
	private String ordNo						= "";
	/** 비고 */
	private String remark						= "";
	
	
	public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getPmCalibStdValueId() {
		return pmCalibStdValueId;
	}
	public void setPmCalibStdValueId(String pmCalibStdValueId) {
		this.pmCalibStdValueId = pmCalibStdValueId;
		super.setAuditKey(pmCalibStdValueId);
	}
	public String getCalibPoint() {
		return calibPoint;
	}
	public void setCalibPoint(String calibPoint) {
		this.calibPoint = calibPoint;
	}
	public String getAllowValue() {
		return allowValue;
	}
	public void setAllowValue(String allowValue) {
		this.allowValue = allowValue;
	}
	public String getAsfStdValue() {
		return asfStdValue;
	}
	public void setAsfStdValue(String asfStdValue) {
		this.asfStdValue = asfStdValue;
	}
	public String getAslStdValue() {
		return aslStdValue;
	}
	public void setAslStdValue(String aslStdValue) {
		this.aslStdValue = aslStdValue;
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
	public String getCalibPointTypeId() {
		return calibPointTypeId;
	}
	public void setCalibPointTypeId(String calibPointTypeId) {
		this.calibPointTypeId = calibPointTypeId;
	}
	public String getCalibPointTypeDesc() {
		return calibPointTypeDesc;
	}
	public void setCalibPointTypeDesc(String calibPointTypeDesc) {
		this.calibPointTypeDesc = calibPointTypeDesc;
	}
}
