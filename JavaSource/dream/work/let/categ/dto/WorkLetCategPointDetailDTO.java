package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * 안전작업유형 점검항목 detail DTO - Detail DTO
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategPointDetailDTO extends BaseDTO
{
	/** 안전작업 표준점검항목 id (key값) */
	private String woLetCtgPointId = 			 "";
	
	/** 안전작업유형 id */
	private String woLetCtgId =					 "";

	/** 순서 */
	private String stepNum = 					 "";

	/** 점검부위 */
	private String checkPosition = 				 "";

	/** 점검항목 */
	private String checkPoint = 				 "";

	/** 점검방법 */
	private String checkMethod = 				 "";

	/** 적정기준 */
	private String fitBasis = 					 "";

	/** 수치판정구분 id */
	private String checkTypeId = 				 "";
	
	/** 수치판정구분 */
	private String checkTypeDesc = 				 "";

	/** 하한값 */
	private String checkMin = 					 "";

	/** 점검기준값 */
	private String checkBasisVal = 				 "";

	/** 상한값 */
	private String checkMax = 					 "";

	/** 단위 */
	private String uom = 						 "";

	/** 사용여부 Id */	
	private String isUseId =					 "";

	/** 사용여부 */
	private String isUseDesc =					 "";

	/** 비고 */
	private String remark = 					 "";

	public String getWoLetCtgPointId() {
		return woLetCtgPointId;
	}

	public void setWoLetCtgPointId(String woLetCtgPointId) {
		this.woLetCtgPointId = woLetCtgPointId;
		super.setAuditKey(woLetCtgPointId);

	}

	public String getWoLetCtgId() {
		return woLetCtgId;
	}

	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
	}

	public String getStepNum() {
		return stepNum;
	}

	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}

	public String getCheckPosition() {
		return checkPosition;
	}

	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}

	public String getCheckPoint() {
		return checkPoint;
	}

	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}

	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}

	public String getFitBasis() {
		return fitBasis;
	}

	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}

	public String getCheckTypeId() {
		return checkTypeId;
	}

	public void setCheckTypeId(String checkTypeId) {
		this.checkTypeId = checkTypeId;
	}

	public String getCheckTypeDesc() {
		return checkTypeDesc;
	}

	public void setCheckTypeDesc(String checkTypeDesc) {
		this.checkTypeDesc = checkTypeDesc;
	}

	public String getCheckMin() {
		return checkMin;
	}

	public void setCheckMin(String checkMin) {
		this.checkMin = checkMin;
	}

	public String getCheckBasisVal() {
		return checkBasisVal;
	}

	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = checkBasisVal;
	}

	public String getCheckMax() {
		return checkMax;
	}

	public void setCheckMax(String checkMax) {
		this.checkMax = checkMax;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getIsUseId() {
		return isUseId;
	}

	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}

	public String getIsUseDesc() {
		return isUseDesc;
	}

	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
