package dream.note.dayrpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 *  상세 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaWoDayRptDetailDTO extends BaseDTO
{
	/** ID */
	private String wrkDayRptId      = "";
	/** 작성자ID */
    private String empId            = "";
    /** 작성자명 */
    private String empDesc          = "";
    /** 부서ID */
    private String deptId           = "";
    /** 부서명 */
    private String deptDesc         = "";
    /** 일자 */
    private String workDate         = "";
    /** 제목 */
    private String title            = "";
    /** 내용 */
    private String contents         = "";
    /** 시간 */
    private String workTime         = "";
    /** 계획일자 */
    private String planDate         = "";
    /** 계획시간 */
    private String planTime         = "";
    /** 계획내용 */
    private String planContents     = "";
    /** 비고 */
    private String remark	        = "";
    
    
    
    
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = CommonUtil.convertDate(planDate);
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getPlanContents() {
		return planContents;
	}
	public void setPlanContents(String planContents) {
		this.planContents = planContents;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getWrkDayRptId() {
		return wrkDayRptId;
	}
	public void setWrkDayRptId(String wrkDayRptId) {
		this.wrkDayRptId = wrkDayRptId;
		super.setAuditKey(wrkDayRptId);
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
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = CommonUtil.convertDate(workDate);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
    
}
