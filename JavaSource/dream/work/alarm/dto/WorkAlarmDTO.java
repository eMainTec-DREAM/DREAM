package dream.work.alarm.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * Alarm List ���� DTO 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public class WorkAlarmDTO extends BaseDTO
{
	/** ID */
	private String alarmListId        		= "";
	/** ���� - �߻�����(From) */
	private String filterFromDate         	= "";
	/** ���� - �߻�����(TO) */
	private String filterToDate           	= "";
	/** ���� - ���� ID */
    private String filterEquipId        	= "";
    /** ���� - ���� �� */
    private String filterEquipDesc      	= "";  
    /** ���� - Alarm �����ڵ� */
	private String filterAlarmMcCode		= "";
	/** ���� - Alarm ����� */
	private String filterAlarmMcDesc		= "";
	/** ���� - Alarm �ڵ� */
	private String filterAlarmCode			= "";
	/** ���� - Alarm �� */
	private String filterAlarmDesc			= "";
	/** ���� - Alarm �߻�ä�� ID */
	private String filterAlarmOcChId		= "";
	/** ���� - Alarm �߻�ä�� Desc */
	private String filterAlarmOcChDesc		= "";
	/** ���� - ������û�� ���࿩�� ID */
	private String filterIsRepairWork		= "";
	/** ���� - Alarm ��� */
	private String filterAlarmGrade			= "";
	/** ���� - Alarm ����Ʈ */
	private String filterAlarmPoint			= "";
	/** ���� - Alarm ���� */
	private String filterAlarmType			= "";
	/** �߻��ð� */
	private String alarmStartTime         	= "";
	/** ����ð� */
	private String alarmEndTime         	= "";
	/** Alarm �����ڵ� */
	private String alarmMcCode         		= "";
	/** Alarm ����� */
	private String alarmMcDesc         		= "";
	/** Alarm ����Ʈ */
	private String alarmPoint         		= "";
	/** Alarm ���� */
	private String alarmType         		= "";
	/** Alarm �ڵ� */
	private String alarmCode         		= "";
	/** Alarm ��� */
	private String alarmGrade         		= "";
	/** Alarm �� */
	private String alarmName         		= "";
	/** Alarm ���� */
	private String alarmDesc         		= "";
	/** ���� ID */
	private String equipId         			= "";
	/** ���� DESC */
	private String equipDesc       			= "";
	/** ���� DESC */
	private String eqLocId       			= "";
	/** ���� DESC */
	private String eqLocDesc       			= "";
	/** ��� */
	private String remark         			= "";
	/** �˶�ó������ */
	private String alarmStatus     			= "";
	/** �˶�ó������ */
	private String alarmChannel    			= "";
	/** �˶���ϼ�������(YN) */
	private String isAlarmSuccess     		= "";
	/** �˶����� �޽��� */
	private String alarmFailMsg     		= "";
	/** ��û ID */
	private String woReqId     				= "";
	/** ��û�� ID */
	private String reqEmpId 				= "";
	/** ��û�� PHONE */
	private String reqEmpPhone 				= "";
	/** ��û�� MAIL */
	private String reqEmpMail 				= "";
	/** ���� ID */
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
