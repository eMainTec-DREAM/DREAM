package dream.edu.list.dto;

import common.bean.BaseDTO;

/**
 * �ڰ����з� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class EduCommonDTO extends BaseDTO
{
	/** ID */
	private String courseListId         = "";
	/** �ڰ�������*/
	private String filterEduTypeDesc         = "";
	/** �ڰ����� */
	private String filterEduName         = "";
	/** �ڰ������� Desc */
	private String filterEduType         = "";
	
	public String getCourseListId() {
		return courseListId;
	}
	public void setCourseListId(String courseListId) {
		this.courseListId = courseListId;
		super.setAuditKey(courseListId);
	}
	public String getFilterEduTypeDesc() {
		return filterEduTypeDesc;
	}
	public void setFilterEduTypeDesc(String filterEduTypeDesc) {
		this.filterEduTypeDesc = filterEduTypeDesc;
	}
	public String getFilterEduName() {
		return filterEduName;
	}
	public void setFilterEduName(String filterEduName) {
		this.filterEduName = filterEduName;
	}
	public String getFilterEduType() {
		return filterEduType;
	}
	public void setFilterEduType(String filterEduType) {
		this.filterEduType = filterEduType;
	}
	


}
