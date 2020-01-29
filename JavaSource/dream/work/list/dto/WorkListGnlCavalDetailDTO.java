package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업상세  - 검교정 - 측정값 상세 DTO
 * @author  kim21017
 * @version $Id: WorkListGnlCavalDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListGnlCavalDetailDTO extends BaseDTO
{
	/** 조회순서 */
	private String ordNo = "";
	/** AS-LEFT(편차값) */
	private String aslDiffValue = "";
	/** AS-LEFT(측정값) */
	private String aslCalValue = "";
	/** AS-LEFT(표준값) */
	private String aslStdValue = "";
	/** AS-FOUND(편차값) */
	private String asfDiffValue = "";
	/** AS-FOUND(측정값) */
	private String asfCalValue = "";
	/** AS-FOUND(표준값) */
	private String asfStdValue = "";
	/** 허용오차 */
	private String allowValue = "";
	/** 교정POINT */
	private String calibPoint = "";
	/** 회차/구분 */
	private String calibPointType = "";
	
	private String calibPointTypeDesc = "";
	/** 작업ID */
	private String wkOrId = "";
	/** 검교정작업 일반측정결과 ID */
	private String wocalibgnlvalueId = "";
	/** 회사코드 */
	private String compNo = "";
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