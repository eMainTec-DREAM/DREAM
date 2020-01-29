package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * ǥ�� ������ - Detail DTO
 * @author kim21017
 * @version $Id: WorkPmStdCalibValDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkPmStdCalibValDetailDTO extends BaseDTO
{
	/** ǥ�ر����� Ÿ�� ID */
	private String pmCalibStdTpId				= "";
	/** ������ ID*/
	private String pmCalibStdValueId 			= "";
	/** ȸ��/���� */
	private String calibPointTypeId				= "";
	/** ȸ��/���� */
	private String calibPointTypeDesc			= "";
	/** ����POINT */
	private String calibPoint					= "";
	/** ������ */
	private String allowValue 					= "";
	/** AS-FOUND(ǥ�ذ�)*/
	private String asfStdValue	 				= "";
	/** AS-LEFT(ǥ�ذ�)*/
	private String aslStdValue 					= "";
	/** ��ȸ���� */
	private String ordNo						= "";
	/** ��� */
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
