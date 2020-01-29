package dream.work.alarm.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Alarm List 공통 DTO 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public class WorkAlarmDTO extends BaseDTO
{
	/** ID */
	private String alarmListId        		= "";
	/** 필터 - 발생일자(From) */
	private String filterFromDate         	= "";
	/** 필터 - 발생일자(TO) */
	private String filterToDate           	= "";
	/** 필터 - 설비 ID */
    private String filterEquipId        	= "";
    /** 필터 - 설비 명 */
    private String filterEquipDesc      	= "";  
    /** 필터 - Alarm 설비코드 */
	private String filterAlarmMcCode		= "";
	/** 필터 - Alarm 설비명 */
	private String filterAlarmMcDesc		= "";
	/** 필터 - Alarm 코드 */
	private String filterAlarmCode			= "";
	/** 필터 - Alarm 명 */
	private String filterAlarmDesc			= "";
	/** 필터 - Alarm 발생채널 ID */
	private String filterAlarmOcChId		= "";
	/** 필터 - Alarm 발생채널 Desc */
	private String filterAlarmOcChDesc		= "";
	/** 필터 - 수리요청서 발행여부 ID */
	private String filterIsRepairWork		= "";
	/** 필터 - Alarm 등급 */
	private String filterAlarmGrade			= "";
	/** 필터 - Alarm 포인트 */
	private String filterAlarmPoint			= "";
	/** 필터 - Alarm 유형 */
	private String filterAlarmType			= "";
	/** 발생시간 */
	private String alarmStartTime         	= "";
	/** 종료시간 */
	private String alarmEndTime         	= "";
	/** Alarm 설비코드 */
	private String alarmMcCode         		= "";
	/** Alarm 설비명 */
	private String alarmMcDesc         		= "";
	/** Alarm 포인트 */
	private String alarmPoint         		= "";
	/** Alarm 유형 */
	private String alarmType         		= "";
	/** Alarm 코드 */
	private String alarmCode         		= "";
	/** Alarm 등급 */
	private String alarmGrade         		= "";
	/** Alarm 명 */
	private String alarmName         		= "";
	/** Alarm 내용 */
	private String alarmDesc         		= "";
	/** 설비 ID */
	private String equipId         			= "";
	/** 설비 DESC */
	private String equipDesc       			= "";
	/** 설비 DESC */
	private String eqLocId       			= "";
	/** 설비 DESC */
	private String eqLocDesc       			= "";
	/** 비고 */
	private String remark         			= "";
	/** 알람처리상태 */
	private String alarmStatus     			= "";
	/** 알람처리상태 */
	private String alarmChannel    			= "";
	/** 알람등록성공여부(YN) */
	private String isAlarmSuccess     		= "";
	/** 알람실패 메시지 */
	private String alarmFailMsg     		= "";
	/** 요청 ID */
	private String woReqId     				= "";
	/** 요청자 ID */
	private String reqEmpId 				= "";
	/** 요청자 PHONE */
	private String reqEmpPhone 				= "";
	/** 요청자 MAIL */
	private String reqEmpMail 				= "";
	/** 공장 ID */
	private String plant 					= "";
	/** Alarm Object */
	private String alarmObjectId 			= "";
	
	public String getFilterAlarmType() {
		return filterAlarmType;
	}
	public void setFilterAlarmType(String filterAlarmType) {
		this.filterAlarmType = filterAlarmType;
	}
	public String getFilterAlarmPoint() {
		return filterAlarmPoint;
	}
	public void setFilterAlarmPoint(String filterAlarmPoint) {
		this.filterAlarmPoint = filterAlarmPoint;
	}
	public String getFilterAlarmGrade() {
		return filterAlarmGrade;
	}
	public void setFilterAlarmGrade(String filterAlarmGrade) {
		this.filterAlarmGrade = filterAlarmGrade;
	}
	public String getReqEmpId() {
		return reqEmpId;
	}
	public void setReqEmpId(String reqEmpId) {
		this.reqEmpId = reqEmpId;
	}
	public String getReqEmpPhone() {
		return reqEmpPhone;
	}
	public void setReqEmpPhone(String reqEmpPhone) {
		this.reqEmpPhone = reqEmpPhone;
	}
	public String getReqEmpMail() {
		return reqEmpMail;
	}
	public void setReqEmpMail(String reqEmpMail) {
		this.reqEmpMail = reqEmpMail;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getAlarmObjectId() {
		return alarmObjectId;
	}
	public void setAlarmObjectId(String alarmObjectId) {
		this.alarmObjectId = alarmObjectId;
	}
	public String getAlarmFailMsg() {
		return alarmFailMsg;
	}
	public void setAlarmFailMsg(String alarmFailMsg) {
		this.alarmFailMsg = alarmFailMsg;
	}
	public String getAlarmChannel() {
		return alarmChannel;
	}
	public void setAlarmChannel(String alarmChannel) {
		this.alarmChannel = alarmChannel;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getIsAlarmSuccess() {
		return isAlarmSuccess;
	}
	public void setIsAlarmSuccess(String isAlarmSuccess) {
		this.isAlarmSuccess = isAlarmSuccess;
	}
	public String getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public String getAlarmListId() {
		return alarmListId;
	}
	public void setAlarmListId(String alarmListId) {
		this.alarmListId = alarmListId;
	}
	public String getFilterFromDate() {
		return filterFromDate;
	}
	public void setFilterFromDate(String filterFromDate) {
		this.filterFromDate = filterFromDate;
	}
	public String getFilterToDate() {
		return filterToDate;
	}
	public void setFilterToDate(String filterToDate) {
		this.filterToDate = filterToDate;
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
	public String getFilterAlarmMcCode() {
		return filterAlarmMcCode;
	}
	public void setFilterAlarmMcCode(String filterAlarmMcCode) {
		this.filterAlarmMcCode = filterAlarmMcCode;
	}
	public String getFilterAlarmMcDesc() {
		return filterAlarmMcDesc;
	}
	public void setFilterAlarmMcDesc(String filterAlarmMcDesc) {
		this.filterAlarmMcDesc = filterAlarmMcDesc;
	}
	public String getFilterAlarmCode() {
		return filterAlarmCode;
	}
	public void setFilterAlarmCode(String filterAlarmCode) {
		this.filterAlarmCode = filterAlarmCode;
	}
	public String getFilterAlarmDesc() {
		return filterAlarmDesc;
	}
	public void setFilterAlarmDesc(String filterAlarmDesc) {
		this.filterAlarmDesc = filterAlarmDesc;
	}
	public String getFilterAlarmOcChId() {
		return filterAlarmOcChId;
	}
	public void setFilterAlarmOcChId(String filterAlarmOcChId) {
		this.filterAlarmOcChId = filterAlarmOcChId;
	}
	public String getFilterAlarmOcChDesc() {
		return filterAlarmOcChDesc;
	}
	public void setFilterAlarmOcChDesc(String filterAlarmOcChDesc) {
		this.filterAlarmOcChDesc = filterAlarmOcChDesc;
	}
	public String getFilterIsRepairWork() {
		return filterIsRepairWork;
	}
	public void setFilterIsRepairWork(String filterIsRepairWork) {
		this.filterIsRepairWork = filterIsRepairWork;
	}
	public String getAlarmStartTime() {
		return alarmStartTime;
	}
	public void setAlarmStartTime(String alarmStartTime) {
		this.alarmStartTime = CommonUtil.convertDateTime(alarmStartTime); 
	}
	public String getAlarmEndTime() {
		return alarmEndTime;
	}
	public void setAlarmEndTime(String alarmEndTime) {
		this.alarmEndTime = CommonUtil.convertDateTime(alarmEndTime);
	}
	public String getAlarmMcCode() {
		return alarmMcCode;
	}
	public void setAlarmMcCode(String alarmMcCode) {
		this.alarmMcCode = alarmMcCode;
	}
	public String getAlarmMcDesc() {
		return alarmMcDesc;
	}
	public void setAlarmMcDesc(String alarmMcDesc) {
		this.alarmMcDesc = alarmMcDesc;
	}
	public String getAlarmPoint() {
		return alarmPoint;
	}
	public void setAlarmPoint(String alarmPoint) {
		this.alarmPoint = alarmPoint;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmCode() {
		return alarmCode;
	}
	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	public String getAlarmGrade() {
		return alarmGrade;
	}
	public void setAlarmGrade(String alarmGrade) {
		this.alarmGrade = alarmGrade;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public String getAlarmDesc() {
		return alarmDesc;
	}
	public void setAlarmDesc(String alarmDesc) {
		this.alarmDesc = alarmDesc;
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
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
