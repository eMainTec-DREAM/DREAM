package dream.ass.base.dto;

import common.bean.BaseDTO;

/**
 * ��ޱ��� - Detail DTO
 * @author kim21017
 * @version $Id: AssBaseGradeDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBaseGradeDetailDTO extends BaseDTO
{
	/** �򰡱��� ID */
	private String assBaseListId			= "";
	/** ��ޱ��� ID*/
	private String assBaseGradeId 			= "";
	/** ��� ID */
	private String eqGradeId				= "";
	/** ��� �� */
	private String eqGradeDesc				= "";
	/** ��ȸ����*/
	private String ordNo 					= "";
	/** ��ް� FROM*/
	private String gradeFrom 				= "";
	/** ��ް� TO*/
	private String gradeTo 					= "";
	/** ��� */
	private String remark					= "";
	/** �ֱ� */
	private String cycle					= "";
	
	private String periodType				= "";
	
	private String periodTypeDesc			= "";
	
	
	
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getAssBaseGradeId() {
		return assBaseGradeId;
	}
	public void setAssBaseGradeId(String assBaseGradeId) {
		this.assBaseGradeId = assBaseGradeId;
		super.setAuditKey(assBaseGradeId);
	}
	public String getEqGradeId() {
		return eqGradeId;
	}
	public void setEqGradeId(String eqGradeId) {
		this.eqGradeId = eqGradeId;
	}
	public String getEqGradeDesc() {
		return eqGradeDesc;
	}
	public void setEqGradeDesc(String eqGradeDesc) {
		this.eqGradeDesc = eqGradeDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getGradeFrom() {
		return gradeFrom;
	}
	public void setGradeFrom(String gradeFrom) {
		this.gradeFrom = gradeFrom;
	}
	public String getGradeTo() {
		return gradeTo;
	}
	public void setGradeTo(String gradeTo) {
		this.gradeTo = gradeTo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
