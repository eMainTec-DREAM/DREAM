package dream.work.fmea.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * 고장영향성평가
 * @author kim21017
 * @version $Id: WorkFmeaResCommonDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class WorkFmeaResCommonDTO extends BaseDTO
{
	/** 고장영향평가 ID */
	private String woFmeaId							= "";
	/** Filter 의뢰일자 시작 */
	private String filterReqStartDate				= "";
	/** Filter 의뢰일자 종료 */
	private String filterReqEndDate					= "";
	/** Filter 고장영향평가상태 ID */
	private String filterWoFmeaStatusId				= "";
	/** Filter 고장영향평가상태 명 */
	private String filterWoFmeaStatusDesc			= "";
	/** Filter 의뢰부서 ID */
	private String filterReqDeptId					= "";
	/** Filter 의뢰부서 명 */
	private String filterReqDeptDesc				= "";
	/** Filter 의뢰자 ID */
	private String filterReqById					= "";
	/** Filter 의뢰자 명 */
	private String filterReqByDesc					= "";
	/** Filter 접수번호 */
	private String filterWoFmeaNo					= "";
	/** Filter 설비 ID */
	private String filterEquipId					= "";
	/** Filter 설비 명 */
	private String filterEquipDesc					= "";
	/** Filter 검토일자 시작 */
	private String filterReviewStartDate			= "";
	/** Filter 검토일자 종료 */
	private String filterReviewEndDate				= "";
	/** Filter 검토자 ID */
	private String filterReviewById					= "";
	/** Filter 검토자 명 */
	private String filterReviewByDesc				= "";
	/** Filter 영향성평가 ID */
	private String filterFmeaPriorityId				= "";
	/** Filter 영향성평가 명 */
	private String filterFmeaPriorityDesc			= "";
	/** Filter 작업구분 ID */
	private String filterFmeaWoTypeId				= "";
	/** Filter 작업구분 명 */
	private String filterFmeaWoTypeDesc				= "";
	/** Filter Calibration 여부 ID */
	private String filterIsCalibId					= "";
	/** Filter Calibration 여부 명 */
	private String filterIsCalibDesc				= "";
	/** Filter Qualification 여부 ID */
	private String filterIsQualId					= "";
	/** Filter Qualification 여부 명 */
	private String filterIsQualDesc					= "";
	
	public String getWoFmeaId() {
		return woFmeaId;
	}
	public void setWoFmeaId(String woFmeaId) {
		this.woFmeaId = woFmeaId;
		super.setAuditKey(woFmeaId);
	}
	public String getFilterReqStartDate() {
		return filterReqStartDate;
	}
	public void setFilterReqStartDate(String filterReqStartDate) {
		this.filterReqStartDate = CommonUtil.convertDate(filterReqStartDate);
	}
	public String getFilterReqEndDate() {
		return filterReqEndDate;
	}
	public void setFilterReqEndDate(String filterReqEndDate) {
		this.filterReqEndDate = CommonUtil.convertDate(filterReqEndDate);
	}
	public String getFilterWoFmeaStatusId() {
		return filterWoFmeaStatusId;
	}
	public void setFilterWoFmeaStatusId(String filterWoFmeaStatusId) {
		this.filterWoFmeaStatusId = filterWoFmeaStatusId;
	}
	public String getFilterWoFmeaStatusDesc() {
		return filterWoFmeaStatusDesc;
	}
	public void setFilterWoFmeaStatusDesc(String filterWoFmeaStatusDesc) {
		this.filterWoFmeaStatusDesc = filterWoFmeaStatusDesc;
	}
	public String getFilterReqDeptId() {
		return filterReqDeptId;
	}
	public void setFilterReqDeptId(String filterReqDeptId) {
		this.filterReqDeptId = filterReqDeptId;
	}
	public String getFilterReqDeptDesc() {
		return filterReqDeptDesc;
	}
	public void setFilterReqDeptDesc(String filterReqDeptDesc) {
		this.filterReqDeptDesc = filterReqDeptDesc;
	}
	public String getFilterReqById() {
		return filterReqById;
	}
	public void setFilterReqById(String filterReqById) {
		this.filterReqById = filterReqById;
	}
	public String getFilterReqByDesc() {
		return filterReqByDesc;
	}
	public void setFilterReqByDesc(String filterReqByDesc) {
		this.filterReqByDesc = filterReqByDesc;
	}
	public String getFilterWoFmeaNo() {
		return filterWoFmeaNo;
	}
	public void setFilterWoFmeaNo(String filterWoFmeaNo) {
		this.filterWoFmeaNo = filterWoFmeaNo;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	public String getFilterReviewStartDate() {
		return filterReviewStartDate;
	}
	public void setFilterReviewStartDate(String filterReviewStartDate) {
		this.filterReviewStartDate = CommonUtil.convertDate(filterReviewStartDate);
	}
	public String getFilterReviewEndDate() {
		return filterReviewEndDate;
	}
	public void setFilterReviewEndDate(String filterReviewEndDate) {
		this.filterReviewEndDate = CommonUtil.convertDate(filterReviewEndDate);
	}
	public String getFilterReviewById() {
		return filterReviewById;
	}
	public void setFilterReviewById(String filterReviewById) {
		this.filterReviewById = filterReviewById;
	}
	public String getFilterReviewByDesc() {
		return filterReviewByDesc;
	}
	public void setFilterReviewByDesc(String filterReviewByDesc) {
		this.filterReviewByDesc = filterReviewByDesc;
	}
	public String getFilterFmeaPriorityId() {
		return filterFmeaPriorityId;
	}
	public void setFilterFmeaPriorityId(String filterFmeaPriorityId) {
		this.filterFmeaPriorityId = filterFmeaPriorityId;
	}
	public String getFilterFmeaPriorityDesc() {
		return filterFmeaPriorityDesc;
	}
	public void setFilterFmeaPriorityDesc(String filterFmeaPriorityDesc) {
		this.filterFmeaPriorityDesc = filterFmeaPriorityDesc;
	}
	public String getFilterFmeaWoTypeId() {
		return filterFmeaWoTypeId;
	}
	public void setFilterFmeaWoTypeId(String filterFmeaWoTypeId) {
		this.filterFmeaWoTypeId = filterFmeaWoTypeId;
	}
	public String getFilterFmeaWoTypeDesc() {
		return filterFmeaWoTypeDesc;
	}
	public void setFilterFmeaWoTypeDesc(String filterFmeaWoTypeDesc) {
		this.filterFmeaWoTypeDesc = filterFmeaWoTypeDesc;
	}
	public String getFilterIsCalibId() {
		return filterIsCalibId;
	}
	public void setFilterIsCalibId(String filterIsCalibId) {
		this.filterIsCalibId = filterIsCalibId;
	}
	public String getFilterIsCalibDesc() {
		return filterIsCalibDesc;
	}
	public void setFilterIsCalibDesc(String filterIsCalibDesc) {
		this.filterIsCalibDesc = filterIsCalibDesc;
	}
	public String getFilterIsQualId() {
		return filterIsQualId;
	}
	public void setFilterIsQualId(String filterIsQualId) {
		this.filterIsQualId = filterIsQualId;
	}
	public String getFilterIsQualDesc() {
		return filterIsQualDesc;
	}
	public void setFilterIsQualDesc(String filterIsQualDesc) {
		this.filterIsQualDesc = filterIsQualDesc;
	}

}
