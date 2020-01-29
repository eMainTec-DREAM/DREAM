package dream.work.let.permit.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 안전작업허가서 작업유형 - 점검항목 상세 DTO
 * @author syyang
 * @version $Id: WorkLetPermitCraftDetailDTO.java,v 1.0 2015/12/02 09:12:40 syyang Exp $
 * @since 1.0
 *
 */
public class WorkLetPermitPointDetailDTO extends BaseDTO
{
	/** 안전작업 표준점검항목 id (key값) */
	private String woLetListPointId 		=	"";
	/** 안전작업허가서 id */
	private String woLetListId 				=	"";
	/** 안전작업유형 id */
	private String woLetCtgId 				=	"";
	
	/** 순서 */
	private String stepNum 					=	"";
	/** 점검부위 */
	private String checkPosition 			=	"";
	/** 점검항목 */
	private String checkPoint 				=	"";
	/** 점검방법 */
	private String checkMethod 				=	"";
	/** 적정기준 */
	private String fitBasis 				=	"";
	/** 수치판정구분 id */
	private String checkTypeId 				=	"";
	/** 수치판정구분 */
	private String checkTypeDesc 			=	"";
	/** 하한값 */
	private String checkMin 				=	"";
	/** 점검기준값 */
	private String checkBasisVal 			= 	"";
	/** 상한값 */
	private String checkMax 				= 	"";
	/** 하한/기준/상한값 */
	private String minBasisMax 				= 	"";
	/** 단위 */
	private String uom 						=	"";
	/** 점검결과 ID */		
	private String woLetRsltStatus			=	"";
	/** 점검결과 */	
	private String woLetRsltStatusDesc		=	"";
	/** 점검값 */
	private String resultValue 				=	"";
	/** 점검일자 */
	private String checkDate 				=	"";
	/** 점검시간 */
	private String checkTime 				=	"";
	/** 점검자 */
	private String checkBy 					=	"";
	/** 비고 */
	private String remark 					=	"";
	
	/** 최초생성 시간 */
	private String creTime 					= "";
	/** 마지막 수정일자 */
	private String updTime 					= "";

	
	public String getWoLetListPointId() {
		return woLetListPointId;
	}

	public void setWoLetListPointId(String woLetListPointId) {
		this.woLetListPointId = woLetListPointId;
		super.setAuditKey(woLetListPointId);
	}

	public String getWoLetListId() {
		return woLetListId;
	}

	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
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
		this.stepNum = CommonUtil.convertMoney(stepNum);
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
		this.checkMin = CommonUtil.convertMoney(checkMin);
	}

	public String getCheckBasisVal() {
		return checkBasisVal;
	}

	public void setCheckBasisVal(String checkBasisVal) {
		this.checkBasisVal = CommonUtil.convertMoney(checkBasisVal);
	}

	public String getCheckMax() {
		return checkMax;
	}

	public void setCheckMax(String checkMax) {
		this.checkMax = CommonUtil.convertMoney(checkMax);
	}

	public String getMinBasisMax() {
		return minBasisMax;
	}

	public void setMinBasisMax(String minBasisMax) {
		this.minBasisMax = minBasisMax;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getWoLetRsltStatus() {
		return woLetRsltStatus;
	}

	public void setWoLetRsltStatus(String woLetRsltStatus) {
		this.woLetRsltStatus = woLetRsltStatus;
	}

	public String getWoLetRsltStatusDesc() {
		return woLetRsltStatusDesc;
	}

	public void setWoLetRsltStatusDesc(String woLetRsltStatusDesc) {
		this.woLetRsltStatusDesc = woLetRsltStatusDesc;
	}

	public String getResultValue() {
		return resultValue;
	}

	public void setResultValue(String resultValue) {
		this.resultValue = CommonUtil.convertMoney(resultValue);
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = CommonUtil.convertDate(checkDate);
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = CommonUtil.convertTime(checkTime);
	}

	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = CommonUtil.convertDateTime(creTime);
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = CommonUtil.convertDateTime(updTime);
	}

}