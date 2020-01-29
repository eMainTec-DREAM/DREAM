package dream.work.let.permit.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾� - �����۾��㰡�� �۾����� �� DTO
 * @author  syyang
 * @version $Id$
 * @since   1.0
 */
public class WorkLetPermitDetailDTO extends BaseDTO
{
	/** �����۾��㰡�� ���� ID */
	private String woLetListId			= "";
	/** �����۾��㰡�� ���� NO */
	private String woLetListNo			= "";
	/** �����۾��㰡������ ������� */
	private String woLetListStatus		= "";
	/** �����۾��㰡������ ������� �� */
	private String woLetListStatusDesc	= "";
	/** �����۾����� */
	private String woLetCtgType			= "";
	/** �����۾����� �� */
	private String woLetCtgTypeDesc		= "";
	/** �۾����  */
	private String place				= "";
	/** �����۾��㰡�Ⱓ FROM �۾����� */
	private String startDate 			= "";
	/** �����۾��㰡�Ⱓ FROM �۾��ð� */
	private String startTime 			= "";
	/** �����۾��㰡�Ⱓ TO �۾����� */
	private String endDate 				= "";
	/** �����۾��㰡�Ⱓ TO �۾��ð� */
	private String endTime 				= "";
	/** �������� */
	private String woLetDate	 		= "";
	/** ��� */
	private String remark 				= "";
	
	/** ���ʻ��� �ð� */
	private String creTime 			= "";
	/** ������ �������� */
	private String updTime 			= "";
	
	
	public String getWoLetListId() {
		return woLetListId;
	}
	public void setWoLetListId(String woLetListId) {
		this.woLetListId = woLetListId;
		super.setAuditKey(woLetListId);
	}
	public String getWoLetListNo() {
		return woLetListNo;
	}
	public void setWoLetListNo(String woLetListNo) {
		this.woLetListNo = woLetListNo;
	}
	public String getWoLetListStatus() {
		return woLetListStatus;
	}
	public void setWoLetListStatus(String woLetListStatus) {
		this.woLetListStatus = woLetListStatus;
	}
	public String getWoLetListStatusDesc() {
		return woLetListStatusDesc;
	}
	public void setWoLetListStatusDesc(String woLetListStatusDesc) {
		this.woLetListStatusDesc = woLetListStatusDesc;
	}
	public String getWoLetCtgType() {
		return woLetCtgType;
	}
	public void setWoLetCtgType(String woLetCtgType) {
		this.woLetCtgType = woLetCtgType;
	}
	public String getWoLetCtgTypeDesc() {
		return woLetCtgTypeDesc;
	}
	public void setWoLetCtgTypeDesc(String woLetCtgTypeDesc) {
		this.woLetCtgTypeDesc = woLetCtgTypeDesc;
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
	public String getWoLetDate() {
		return woLetDate;
	}
	public void setWoLetDate(String woLetDate) {
		this.woLetDate = CommonUtil.convertDate(woLetDate);
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