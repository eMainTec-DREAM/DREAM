package dream.edu.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �ڰ��� �ڰ����� �� DTO
 * @author  kim21017
 * @version $Id: EduEmpDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class EduEmpDetailDTO extends BaseDTO
{
								
	/** �����̼� ��� id */
	private String empTrainListId	= "";
	/** id*/
	private String empId			= "";
	/** ���No*/
	private String empNo			= "";
	/** ����� */
	private String empName			= "";
	/** ����  id */
	private String courseListId		= "";
	/** ����From */
	private String trainFdate 		    = "";
	/** ����To */
	private String trainTdate 			= "";
	/** ������� */
	private String trainAgency 			= "";
	/** ��� */
	private String place = "";
	/** ���� */
	private String teacher = "";
	/** ��� */
	private String remark 			= "";
	/** �μ� */
	private String deptName 			= "";
	
	
	
	public String getTrainAgency() {
		return trainAgency;
	}
	public void setTrainAgency(String trainAgency) {
		this.trainAgency = trainAgency;
	}
	public String getEmpTrainListId() {
		return empTrainListId;
	}
	public void setEmpTrainListId(String empTrainListId) {
		this.empTrainListId = empTrainListId;
		super.setAuditKey(empTrainListId);
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCourseListId() {
		return courseListId;
	}
	public void setCourseListId(String courseListId) {
		this.courseListId = courseListId;
	}
	public String getTrainFdate() {
		return trainFdate;
	}
	public void setTrainFdate(String trainFdate) {
		this.trainFdate = CommonUtil.convertDate(trainFdate);
	}
	public String getTrainTdate() {
		return trainTdate;
	}
	public void setTrainTdate(String trainTdate) {
		this.trainTdate = CommonUtil.convertDate(trainTdate);
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}







}