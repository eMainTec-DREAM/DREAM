package dream.edu.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자격증 자격직원 상세 DTO
 * @author  kim21017
 * @version $Id: EduEmpDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class EduEmpDetailDTO extends BaseDTO
{
								
	/** 교육이수 사원 id */
	private String empTrainListId	= "";
	/** id*/
	private String empId			= "";
	/** 사원No*/
	private String empNo			= "";
	/** 사원명 */
	private String empName			= "";
	/** 교육  id */
	private String courseListId		= "";
	/** 교육From */
	private String trainFdate 		    = "";
	/** 교육To */
	private String trainTdate 			= "";
	/** 교육기관 */
	private String trainAgency 			= "";
	/** 장소 */
	private String place = "";
	/** 강사 */
	private String teacher = "";
	/** 비고 */
	private String remark 			= "";
	/** 부서 */
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