package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 * 작업상세  - 검교정(저울) - 측정값 상세 DTO
 * @author  kim21017
 * @version $Id: WorkListSclCavalListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class WorkListSclCavalListDTO extends BaseDTO
{
	/** 저울측정값 ID */
	private String woCalibSclValueId 		= "";
	/** 작업ID */
	private String wkOrId 					= "";
	/** 직선성(증가) 0% */
	private String liValue0 				= "";
	/** 직선성(증가) 25% */
	private String liValue25 				= "";
	/** 직선성(증가) 50% */
	private String liValue50 				= "";
	/** 직선성(증가) 75% */
	private String liValue75 				= "";
	/** 직선성(증가) 100% */
	private String liValue100 				= "";
	/** 직선성(감소) 75% */
	private String ldValue75 				= "";
	/** 직선성(감소) 50% */
	private String ldValue50 				= "";
	/** 직선성(감소) 25% */
	private String ldValue25 				= "";
	/** 직선성(감소) 0% */
	private String ldValue0 				= "";
	/** 편심오차(중앙) */
	private String ecntrValue 				= "";
	/** 편심오차(전) */
	private String ebefValue 				= "";
	/** 편심오차(후) */
	private String eaftValue 				= "";
	/** 편심오차(좌) */
	private String elftValue 				= "";
	/** 편심오차(우) */
	private String erigValue 				= "";
	/** 정밀도 1*/
	private String degree1	 				= "";
	/** 정밀도 2*/
	private String degree2	 				= "";
	/** 정밀도 3*/
	private String degree3	 				= "";
	/** 비고 */
	private String remark	 				= "";
	
	public String getWoCalibSclValueId() {
		return woCalibSclValueId;
	}
	public void setWoCalibSclValueId(String woCalibSclValueId) {
		this.woCalibSclValueId = woCalibSclValueId;
	}
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getLiValue0() {
		return liValue0;
	}
	public void setLiValue0(String liValue0) {
		this.liValue0 = liValue0;
	}
	public String getLiValue25() {
		return liValue25;
	}
	public void setLiValue25(String liValue25) {
		this.liValue25 = liValue25;
	}
	public String getLiValue50() {
		return liValue50;
	}
	public void setLiValue50(String liValue50) {
		this.liValue50 = liValue50;
	}
	public String getLiValue75() {
		return liValue75;
	}
	public void setLiValue75(String liValue75) {
		this.liValue75 = liValue75;
	}
	public String getLiValue100() {
		return liValue100;
	}
	public void setLiValue100(String liValue100) {
		this.liValue100 = liValue100;
	}
	public String getLdValue75() {
		return ldValue75;
	}
	public void setLdValue75(String ldValue75) {
		this.ldValue75 = ldValue75;
	}
	public String getLdValue50() {
		return ldValue50;
	}
	public void setLdValue50(String ldValue50) {
		this.ldValue50 = ldValue50;
	}
	public String getLdValue25() {
		return ldValue25;
	}
	public void setLdValue25(String ldValue25) {
		this.ldValue25 = ldValue25;
	}
	public String getLdValue0() {
		return ldValue0;
	}
	public void setLdValue0(String ldValue0) {
		this.ldValue0 = ldValue0;
	}
	public String getEcntrValue() {
		return ecntrValue;
	}
	public void setEcntrValue(String ecntrValue) {
		this.ecntrValue = ecntrValue;
	}
	public String getEbefValue() {
		return ebefValue;
	}
	public void setEbefValue(String ebefValue) {
		this.ebefValue = ebefValue;
	}
	public String getEaftValue() {
		return eaftValue;
	}
	public void setEaftValue(String eaftValue) {
		this.eaftValue = eaftValue;
	}
	public String getElftValue() {
		return elftValue;
	}
	public void setElftValue(String elftValue) {
		this.elftValue = elftValue;
	}
	public String getErigValue() {
		return erigValue;
	}
	public void setErigValue(String erigValue) {
		this.erigValue = erigValue;
	}
	public String getDegree1() {
		return degree1;
	}
	public void setDegree1(String degree1) {
		this.degree1 = degree1;
	}
	public String getDegree2() {
		return degree2;
	}
	public void setDegree2(String degree2) {
		this.degree2 = degree2;
	}
	public String getDegree3() {
		return degree3;
	}
	public void setDegree3(String degree3) {
		this.degree3 = degree3;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}