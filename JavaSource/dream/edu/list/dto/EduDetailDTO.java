package dream.edu.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �ڰ����з� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class EduDetailDTO extends BaseDTO
{
	/** Edu ID */
	private String courseListId     = "";
	/** Edu ���� */
	private String courseType     = "";
	/** Edu ��ȣ */
	private String courseListNo     = "";
	/** Edu ���� */
	private String courseTypeDesc     = "";
	/** Edu �� */
	private String description     = "";
	/** ������ */
	private String creDate     = "";
	/** ��뿩�� */
	private String isUse     = "";
	/** ����� */
	private String empId     = "";
	/** ����ڸ� */
	private String empDesc     = "";
	/** �μ� */
	private String deptId     = "";
	/** �μ� */
	private String deptDesc     = "";
	/** �������� */
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
