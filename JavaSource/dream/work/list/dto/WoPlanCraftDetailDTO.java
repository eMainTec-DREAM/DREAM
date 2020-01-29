package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업계획목록 - 작업자 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class WoPlanCraftDetailDTO extends BaseDTO
{
	/** 작업계획목록 작업자 id */
	private String woCraftId		= "";
	/** 작업자id*/
	private String empId			= "";
	/** 작업자No*/
	private String empNo			= "";
	/** 작업자명 */
	private String empDesc			= "";
	/** 작업일자 시작일 */
	private String startDate 		= "";
	/** 작업시간 시작시간 */
	private String endDate 		= "";
	/** 작업일자 종료일 */
	private String startTime 		= "";
	/** 작업시간 종료시간 */
	private String endTime 		= "";
	/** 작업시간(분) */
	private String workTime 		= "";
	/** 비고 */
	private String remark 			= "";
	
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getWoCraftId() {
		return woCraftId;
	}
	public void setWoCraftId(String woCraftId) {
		this.woCraftId = woCraftId;
		super.setAuditKey(woCraftId);
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
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
}