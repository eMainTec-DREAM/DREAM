package dream.work.fmea.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장영향성평가 - Detail DTO
 * @author kim21017
 * @version $Id: WorkFmeaResDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkFmeaResDetailDTO extends BaseDTO
{
	/** 고장영향평가 ID */
	private String woFmeaId						= "";
	/** 작업 ID */
	private String wkorId						= "";
	/** 고장영향평가 NO */
	private String woFmeaNo						= "";
	/** 고장영향평가상태 ID */
	private String woFmeaStatusId				= "";
	/** 고장영향평가상태 명 */
	private String woFmeaStatusDesc				= "";
	/** 설비 ID */
	private String equipId						= "";
	/** 설비 No */
	private String itemNo						= "";
	/** 위치 ID */
	private String eqLocId						= "";
	/** 위치 명 */
	private String eqLocDesc					= "";
	/** 설비 명 */
	private String equipDesc					= "";
	/** 의뢰일자 */
	private String reqDate						= "";
	/** 의뢰자 ID */
	private String reqById						= "";
	/** 의뢰자 명 */
	private String reqByDesc					= "";
	/** 의뢰부서 ID */
	private String reqDeptId					= "";
	/** 의뢰부서 명 */
	private String reqDeptDesc					= "";
	/** 발생현상 */
	private String situation					= "";
	/** 수리내용 */
	private String repair						= "";
	/** 영향성평가 ID */
	private String fmeaPriorityId				= "";
	/** 영향성평가 명 */
	private String fmeaPriorityDesc				= "";
	/** 작업구분 ID */
	private String fmeaWoTypeId					= "";
	/** 작업구분 명 */
	private String fmeaWoTypeDesc				= "";
	/** Calibration 여부 ID */
	private String isCalibId					= "";
	/** Calibration 여부 명 */
	private String isCalibDesc					= "";
	/** Qualification 여부 ID */
	private String isQualId						= "";
	/** Qualification 여부 명 */
	private String isQualDesc					= "";
	/** 검토일자  */
	private String reviewDate					= "";
	/** 검토자 ID */
	private String reviewById					= "";
	/** 검토자 명 */
	private String reviewByDesc					= "";
	/** 검토부서 ID */
	private String reviewDeptId					= "";
	/** 검토부서 명 */
	private String reviewDeptDesc				= "";
	/** 검토의견 */
	private String reviewComments				= "";
	
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getReqDeptId() {
		return reqDeptId;
	}
	public void setReqDeptId(String reqDeptId) {
		this.reqDeptId = reqDeptId;
	}
	public String getReqDeptDesc() {
		return reqDeptDesc;
	}
	public void setReqDeptDesc(String reqDeptDesc) {
		this.reqDeptDesc = reqDeptDesc;
	}
	public String getReviewDeptId() {
		return reviewDeptId;
	}
	public void setReviewDeptId(String reviewDeptId) {
		this.reviewDeptId = reviewDeptId;
	}
	public String getReviewDeptDesc() {
		return reviewDeptDesc;
	}
	public void setReviewDeptDesc(String reviewDeptDesc) {
		this.reviewDeptDesc = reviewDeptDesc;
	}
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getWoFmeaNo() {
		return woFmeaNo;
	}
	public void setWoFmeaNo(String woFmeaNo) {
		this.woFmeaNo = woFmeaNo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getWoFmeaId() {
		return woFmeaId;
	}
	public void setWoFmeaId(String woFmeaId) {
		this.woFmeaId = woFmeaId;
		super.setAuditKey(woFmeaId);
	}
	public String getWoFmeaStatusId() {
		return woFmeaStatusId;
	}
	public void setWoFmeaStatusId(String woFmeaStatusId) {
		this.woFmeaStatusId = woFmeaStatusId;
	}
	public String getWoFmeaStatusDesc() {
		return woFmeaStatusDesc;
	}
	public void setWoFmeaStatusDesc(String woFmeaStatusDesc) {
		this.woFmeaStatusDesc = woFmeaStatusDesc;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = CommonUtil.convertDate(reqDate);
	}
	public String getReqById() {
		return reqById;
	}
	public void setReqById(String reqById) {
		this.reqById = reqById;
	}
	public String getReqByDesc() {
		return reqByDesc;
	}
	public void setReqByDesc(String reqByDesc) {
		this.reqByDesc = reqByDesc;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getRepair() {
		return repair;
	}
	public void setRepair(String repair) {
		this.repair = repair;
	}
	public String getFmeaPriorityId() {
		return fmeaPriorityId;
	}
	public void setFmeaPriorityId(String fmeaPriorityId) {
		this.fmeaPriorityId = fmeaPriorityId;
	}
	public String getFmeaPriorityDesc() {
		return fmeaPriorityDesc;
	}
	public void setFmeaPriorityDesc(String fmeaPriorityDesc) {
		this.fmeaPriorityDesc = fmeaPriorityDesc;
	}
	public String getFmeaWoTypeId() {
		return fmeaWoTypeId;
	}
	public void setFmeaWoTypeId(String fmeaWoTypeId) {
		this.fmeaWoTypeId = fmeaWoTypeId;
	}
	public String getFmeaWoTypeDesc() {
		return fmeaWoTypeDesc;
	}
	public void setFmeaWoTypeDesc(String fmeaWoTypeDesc) {
		this.fmeaWoTypeDesc = fmeaWoTypeDesc;
	}
	public String getIsCalibId() {
		return isCalibId;
	}
	public void setIsCalibId(String isCalibId) {
		this.isCalibId = isCalibId;
	}
	public String getIsCalibDesc() {
		return isCalibDesc;
	}
	public void setIsCalibDesc(String isCalibDesc) {
		this.isCalibDesc = isCalibDesc;
	}
	public String getIsQualId() {
		return isQualId;
	}
	public void setIsQualId(String isQualId) {
		this.isQualId = isQualId;
	}
	public String getIsQualDesc() {
		return isQualDesc;
	}
	public void setIsQualDesc(String isQualDesc) {
		this.isQualDesc = isQualDesc;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = CommonUtil.convertDate(reviewDate);
	}
	public String getReviewById() {
		return reviewById;
	}
	public void setReviewById(String reviewById) {
		this.reviewById = reviewById;
	}
	public String getReviewByDesc() {
		return reviewByDesc;
	}
	public void setReviewByDesc(String reviewByDesc) {
		this.reviewByDesc = reviewByDesc;
	}
	public String getReviewComments() {
		return reviewComments;
	}
	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	
}
