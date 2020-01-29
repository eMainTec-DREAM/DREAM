package dream.work.let.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾� - �� DTO
 * @author  syyang
 * @version $Id: WorkLetDetailDTO.java,v 1.0 2015/12/02 08:44:16 syyang Exp $
 * @since   1.0
 *
 */
public class WorkLetDetailDTO extends BaseDTO
{
	/** �����۾��㰡�� ID */
	private String woLetId			= "";
	/** �����۾��� */
	private String woLetDesc		= "";
	/** �����۾���ȣ */
	private String woLetNo			= "";
	
	/** �����۾��㰡 ���� ID */
	private String woLetStatus		= "";
	/** �����۾� �㰡 ���¸� */
	private String woLetStatusDesc	= "";

	/** ����/��� */
	private String itemDesc			= "";
	/** �۾���� */
	private String place			= "";
	
	/** �����۾� �㰡�Ⱓ FROM �۾����� */
	private String startDate 		= "";
	/** �����۾� �㰡�Ⱓ FROM �۾��ð� */
	private String startTime 		= "";
	/** �����۾� �㰡�Ⱓ TO �۾����� */
	private String endDate 			= "";
	/** �����۾� �㰡�Ⱓ TO �۾��ð� */
	private String endTime 			= "";
	/** �����۾� �㰡�ð� */
	private String workTime 		= "";
	
	/** ��û����� ID */
	private String reqById			= "";
	/** ��û����� �� */
	private String reqByDesc		= "";
	/** ��û�μ� ID */
	private String reqDeptId		= "";
	/** ��û�μ��� */
	private String reqDeptDesc		= "";
	/** �㰡��ID */
	private String recById			= "";
	/** �㰡�� �� */
	private String recByDesc		= "";
	/** �㰡�μ�id */
	private String recDeptId		= "";
	/** �㰡�μ��� */
	private String recDeptDesc		= "";
	
	/** ����Id */
    private String plant        	= "";
    /** ����� */
    private String plantDesc      	= "";

	/** �۾� No */
	private String woNo 			= "";
	/** �۾� ID */
	private String wkOrId 			= "";
	/** ��� */
	private String remark 			= "";

	/** ���ʻ��� �ð� */
	private String creTime 			= "";
	/** ������ �������� */
	private String updTime 			= "";
	/** �����ȣ  */
	private String equipId			= "";
	
	/** �����۾� �㰡���� */
	private String letDate 			= "";
	/** �۾��Ϸ� Ȯ��(��ȸ) ����� */
	private String letBy			= "";
	/** �۾��Ϸ� Ȯ��(��ȸ) ����� */
	private String letByDesc		= "";
	
	
	public String getWoLetId() {
		return woLetId;
	}
	public void setWoLetId(String woLetId) {
		this.woLetId = woLetId;
		super.setAuditKey(woLetId);
	}
	public String getWoLetDesc() {
		return woLetDesc;
	}
	public void setWoLetDesc(String woLetDesc) {
		this.woLetDesc = woLetDesc;
	}
	public String getWoLetNo() {
		return woLetNo;
	}
	public void setWoLetNo(String woLetNo) {
		this.woLetNo = woLetNo;
	}
	public String getWoLetStatus() {
		return woLetStatus;
	}
	public void setWoLetStatus(String woLetStatus) {
		this.woLetStatus = woLetStatus;
	}
	public String getWoLetStatusDesc() {
		return woLetStatusDesc;
	}
	public void setWoLetStatusDesc(String woLetStatusDesc) {
		this.woLetStatusDesc = woLetStatusDesc;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = CommonUtil.convertTime(endTime);
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
	public String getRecById() {
		return recById;
	}
	public void setRecById(String recById) {
		this.recById = recById;
	}
	public String getRecByDesc() {
		return recByDesc;
	}
	public void setRecByDesc(String recByDesc) {
		this.recByDesc = recByDesc;
	}
	public String getRecDeptId() {
		return recDeptId;
	}
	public void setRecDeptId(String recDeptId) {
		this.recDeptId = recDeptId;
	}
	public String getRecDeptDesc() {
		return recDeptDesc;
	}
	public void setRecDeptDesc(String recDeptDesc) {
		this.recDeptDesc = recDeptDesc;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime);
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getLetDate() {
		return letDate;
	}
	public void setLetDate(String letDate) {
		this.letDate = CommonUtil.convertDate(letDate);
	}
	public String getLetBy() {
		return letBy;
	}
	public void setLetBy(String letBy) {
		this.letBy = letBy;
	}
	public String getLetByDesc() {
		return letByDesc;
	}
	public void setLetByDesc(String letByDesc) {
		this.letByDesc = letByDesc;
	}
	
}
