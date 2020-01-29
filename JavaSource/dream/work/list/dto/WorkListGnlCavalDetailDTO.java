package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * �۾���  - �˱��� - ������ �� DTO
 * @author  kim21017
 * @version $Id: WorkListGnlCavalDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListGnlCavalDetailDTO extends BaseDTO
{
	/** ��ȸ���� */
	private String ordNo = "";
	/** AS-LEFT(������) */
	private String aslDiffValue = "";
	/** AS-LEFT(������) */
	private String aslCalValue = "";
	/** AS-LEFT(ǥ�ذ�) */
	private String aslStdValue = "";
	/** AS-FOUND(������) */
	private String asfDiffValue = "";
	/** AS-FOUND(������) */
	private String asfCalValue = "";
	/** AS-FOUND(ǥ�ذ�) */
	private String asfStdValue = "";
	/** ������ */
	private String allowValue = "";
	/** ����POINT */
	private String calibPoint = "";
	/** ȸ��/���� */
	private String calibPointType = "";
	
	private String calibPointTypeDesc = "";
	/** �۾�ID */
	private String wkOrId = "";
	/** �˱����۾� �Ϲ�������� ID */
	private String wocalibgnlvalueId = "";
	/** ȸ���ڵ� */
	private String compNo = "";
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
	
	
	
	public String getCalibPointTypeDesc() {
		return calibPointTypeDesc;
	}
	public void setCalibPointTypeDesc(String calibPointTypeDesc) {
		this.calibPointTypeDesc = calibPointTypeDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getAslDiffValue() {
		return aslDiffValue;
	}
	public void setAslDiffValue(String aslDiffValue) {
		this.aslDiffValue = aslDiffValue;
	}
	public String getAslCalValue() {
		return aslCalValue;
	}
	public void setAslCalValue(String aslCalValue) {
		this.aslCalValue = aslCalValue;
	}
	public String getAslStdValue() {
		return aslStdValue;
	}
	public void setAslStdValue(String aslStdValue) {
		this.aslStdValue = aslStdValue;
	}
	public String getAsfDiffValue() {
		return asfDiffValue;
	}
	public void setAsfDiffValue(String asfDiffValue) {
		this.asfDiffValue = asfDiffValue;
	}
	public String getAsfCalValue() {
		return asfCalValue;
	}
	public void setAsfCalValue(String asfCalValue) {
		this.asfCalValue = asfCalValue;
	}
	public String getAsfStdValue() {
		return asfStdValue;
	}
	public void setAsfStdValue(String asfStdValue) {
		this.asfStdValue = asfStdValue;
	}
	public String getAllowValue() {
		return allowValue;
	}
	public void setAllowValue(String allowValue) {
		this.allowValue = allowValue;
	}
	public String getCalibPoint() {
		return calibPoint;
	}
	public void setCalibPoint(String calibPoint) {
		this.calibPoint = calibPoint;
	}
	public String getCalibPointType() {
		return calibPointType;
	}
	public void setCalibPointType(String calibPointType) {
		this.calibPointType = calibPointType;
	}
	public String getWocalibgnlvalueId() {
		return wocalibgnlvalueId;
	}
	public void setWocalibgnlvalueId(String wocalibgnlvalueId) {
		this.wocalibgnlvalueId = wocalibgnlvalueId;
		super.setAuditKey(wocalibgnlvalueId);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
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