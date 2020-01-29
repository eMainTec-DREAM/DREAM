package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���  - �˱��� - ������ �� DTO
 * @author  kim21017
 * @version $Id: WorkListCavalDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListCavalDetailDTO extends BaseDTO
{
	/** �۾� id */
	private String wkOrId			= "";
	/** ������ id */
	private String woCalibValueId	= "";
	/** ����ġ */
	private String setVal			= "";
	/** ǥ��ġ */
	private String basisVal			= "";
	/** ����������ġ */
	private String befVal			= "";
	/** ���������� */
	private String befDiffVal 		= "";
	/** ����������ġ */
	private String aftVal 			= "";
	/** �����Ŀ��� */
	private String aftDiffVal 		= "";
	/** ��� */
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