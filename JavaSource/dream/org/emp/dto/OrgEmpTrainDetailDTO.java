package dream.org.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매신청 item - 상세  DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class OrgEmpTrainDetailDTO extends BaseDTO
{
	/**  ID */
	private String empTrainListId 	= "";
	/** 교육Id */
	private String courseListId 		= "";
	/** 교육시작일자 */
	private String trainFdate 		= "";
	/** 교육종료일자 */
	private String trainTdate 		= "";
	/** 교육기관 */
	private String trainAgency 		= "";
	/** 교육명 */
	private String trainDesc 		= "";
	/** 교육장소 */
	private String place 		= "";
	/** 교육자 */
	private String teacher 		= "";
	/** 교육상세설명 */
	private String remark 		= "";
	/** 교육상세설명 */
	private String courseListNo 		= "";
	
	public String getCourseListNo() {
		return courseListNo;
	}
	public void setCourseListNo(String courseListNo) {
		this.courseListNo = courseListNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmpTrainListId() {
		return empTrainListId;
	}
	public void setEmpTrainListId(String empTrainListId) {
		this.empTrainListId = empTrainListId;
		super.setAuditKey(empTrainListId);
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
	public String getTrainAgency() {
		return trainAgency;
	}
	public void setTrainAgency(String trainAgency) {
		this.trainAgency = trainAgency;
	}
	public String getTrainDesc() {
		return trainDesc;
	}
	public void setTrainDesc(String trainDesc) {
		this.trainDesc = trainDesc;
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
	
}