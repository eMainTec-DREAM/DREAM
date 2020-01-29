package dream.work.fmea.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���念�⼺�� - Detail DTO
 * @author kim21017
 * @version $Id: WorkFmeaResDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkFmeaResDetailDTO extends BaseDTO
{
	/** ���念���� ID */
	private String woFmeaId						= "";
	/** �۾� ID */
	private String wkorId						= "";
	/** ���念���� NO */
	private String woFmeaNo						= "";
	/** ���念���򰡻��� ID */
	private String woFmeaStatusId				= "";
	/** ���念���򰡻��� �� */
	private String woFmeaStatusDesc				= "";
	/** ���� ID */
	private String equipId						= "";
	/** ���� No */
	private String itemNo						= "";
	/** ��ġ ID */
	private String eqLocId						= "";
	/** ��ġ �� */
	private String eqLocDesc					= "";
	/** ���� �� */
	private String equipDesc					= "";
	/** �Ƿ����� */
	private String reqDate						= "";
	/** �Ƿ��� ID */
	private String reqById						= "";
	/** �Ƿ��� �� */
	private String reqByDesc					= "";
	/** �Ƿںμ� ID */
	private String reqDeptId					= "";
	/** �Ƿںμ� �� */
	private String reqDeptDesc					= "";
	/** �߻����� */
	private String situation					= "";
	/** �������� */
	private String repair						= "";
	/** ���⼺�� ID */
	private String fmeaPriorityId				= "";
	/** ���⼺�� �� */
	private String fmeaPriorityDesc				= "";
	/** �۾����� ID */
	private String fmeaWoTypeId					= "";
	/** �۾����� �� */
	private String fmeaWoTypeDesc				= "";
	/** Calibration ���� ID */
	private String isCalibId					= "";
	/** Calibration ���� �� */
	private String isCalibDesc					= "";
	/** Qualification ���� ID */
	private String isQualId						= "";
	/** Qualification ���� �� */
	private String isQualDesc					= "";
	/** ��������  */
	private String reviewDate					= "";
	/** ������ ID */
	private String reviewById					= "";
	/** ������ �� */
	private String reviewByDesc					= "";
	/** ����μ� ID */
	private String reviewDeptId					= "";
	/** ����μ� �� */
	private String reviewDeptDesc				= "";
	/** �����ǰ� */
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
