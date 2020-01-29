package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비의 정기점검-점검항목  상세 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmInsPointDetailDTO extends BaseDTO
{
	/** 설비ID */
	private String equipId				= "";
	/** 예방작업ID */
	private String pmId					= "";
	/** 예방작업항목ID */
	private String pmPointId			= "";
	/** 점검순서 */
	private String stepNum				= "";
	/** 부위ID */
	private String eqAsmbId				= "";
	/** 부위명 */
	private String eqAsmbDesc			= "";
	/** 점검항목 */
	private String checkPoint			= "";
	/** 적정기준 */
	private String fitBasis				= "";
	/** 점검방법 */
	private String checkMethod			= "";
	/** 수치/판정 ID */
	private String checkTypeId			= "";
	/** 수치/판정 명 */
	private String checkTypeDesc		= "";
	/** 기준값 */
	private String checkBasisVal		= "";
	/** 하한값 */
	private String checkMin				= "";
	/** 상한값 */
	private String checkMax				= "";
	/** 단위 */
	private String uom					= "";
	/** 시행여부ID */
	private String isActiveId			= "";
	/** 시행여부명  */
	private String isActiveDesc			= "";
	/** 비고 */
	private String remark				= "";
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
	public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	public String getCheckPoint() {
		return checkPoint;
	}
	public void setCheckPoint(String checkPoint) {
		this.checkPoint = checkPoint;
	}
	public String getFitBasis() {
		return fitBasis;
	}
	public void setFitBasis(String fitBasis) {
		this.fitBasis = fitBasis;
	}
	public String getCheckMethod() {
		return checkMethod;
	}
	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
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
	public String getCheckBasisVal() {
		return checkBasisVal;
	}
	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = CommonUtil.convertMoney(checkBasisVal);
	}
	public String getCheckMin() {
		return checkMin;
	}
	public void setCheckMin(String checkMin) {
		this.checkMin = CommonUtil.convertMoney(checkMin);
	}
	public String getCheckMax() {
		return checkMax;
	}
	public void setCheckMax(String checkMax) {
		this.checkMax = CommonUtil.convertMoney(checkMax);
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getIsActiveId() {
		return isActiveId;
	}
	public void setIsActiveId(String isActiveId) {
		this.isActiveId = isActiveId;
	}
	public String getIsActiveDesc() {
		return isActiveDesc;
	}
	public void setIsActiveDesc(String isActiveDesc) {
		this.isActiveDesc = isActiveDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}