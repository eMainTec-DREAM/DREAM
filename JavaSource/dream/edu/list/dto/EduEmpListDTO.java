package dream.edu.list.dto;

import common.bean.BaseDTO;

/**
 * �ڰ��� ��� ��� DTO
 * @author  kim21017
 * @version $Id: EduEmpListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class EduEmpListDTO extends BaseDTO
{

	/** �����̼��� id */
	private String empTrainListId 	= "";

	/** �������� ��� id */
	private String courseListId 	= "";

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







}