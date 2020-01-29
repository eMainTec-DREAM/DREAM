package dream.edu.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자격증분류 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class EduDetailDTO extends BaseDTO
{
	/** Edu ID */
	private String courseListId     = "";
	/** Edu 구분 */
	private String courseType     = "";
	/** Edu 번호 */
	private String courseListNo     = "";
	/** Edu 구분 */
	private String courseTypeDesc     = "";
	/** Edu 명 */
	private String description     = "";
	/** 개설일 */
	private String creDate     = "";
	/** 사용여부 */
	private String isUse     = "";
	/** 담당자 */
	private String empId     = "";
	/** 담당자명 */
	private String empDesc     = "";
	/** 부서 */
	private String deptId     = "";
	/** 부서 */
	private String deptDesc     = "";
	/** 교육내용 */
	private String contents     = "";
	public String getCourseListId() {
		return courseListId;
	}
	public void setCourseListId(String courseListId) {
		this.courseListId = courseListId;
		super.setAuditKey(courseListId);
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getCourseListNo() {
		return courseListNo;
	}
	public void setCourseListNo(String courseListNo) {
		this.courseListNo = courseListNo;
	}
	public String getCourseTypeDesc() {
		return courseTypeDesc;
	}
	public void setCourseTypeDesc(String courseTypeDesc) {
		this.courseTypeDesc = courseTypeDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDate(creDate);
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
