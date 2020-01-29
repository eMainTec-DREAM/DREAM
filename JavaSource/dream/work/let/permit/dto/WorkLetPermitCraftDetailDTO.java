package dream.work.let.permit.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾��㰡�� �۾����� - �۾��� ��� DTO
 * @author  syyang
 * @version $Id: WorkLetPermitCraftDetailDTO.java,v 1.1 2015/12/04 09:10:45 syyang Exp $
 * @since   1.0
 */
public class WorkLetPermitCraftDetailDTO extends BaseDTO
{
	/** �����۾��㰡�� �۾��� ID */
	private String woLetListCraftId 	= "";
	
	/** �����۾��㰡��ID */
	private String woLetListId 			= "";

	/** ���� */
	private String stepNum 				= "";
	/** ����/�ܺ� �۾��ڱ��� */
	private String craftType 			= "";
	/** ����/�ܺ� �۾��ڱ��� */
	private String craftTypeDesc		= "";
	/** �۾��ڸ� */
	private String workName				= "";
	
	/** �����۾��㰡�Ⱓ FROM �۾����� */
	private String startDate			= "";
	/** �����۾��㰡�Ⱓ FROM�۾��ð� */
	private String startTime			= "";
	/** �����۾��㰡�Ⱓ TO�۾����� */
	private String endDate				= "";
	/** �����۾��㰡�Ⱓ TO�۾��ð� */
	private String endTime				= "";
	
	/** �۾��ð�(��) */
	private String workTime 			= "";
	/** ��� */
	private String remark 				= "";
	
	/** ���ʻ����ð� */
	private String creTime				= "";
	/** �������������� */
	private String updTime				= "";
	
	public String getWoLetListCraftId() {
		return woLetListCraftId;
	}
	public void setWoLetListCraftId(String woLetListCraftId) {
		this.woLetListCraftId = woLetListCraftId;
		super.setAuditKey(woLetListCraftId);
	}
	public String getWoLetListId() {
		return woLetListId;
	}
	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = CommonUtil.convertMoney(stepNum);
	}
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getCraftTypeDesc() {
		return craftTypeDesc;
	}
	public void setCraftTypeDesc(String craftTypeDesc) {
		this.craftTypeDesc = craftTypeDesc;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
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
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime); 
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

	
}