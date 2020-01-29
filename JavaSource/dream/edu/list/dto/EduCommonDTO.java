package dream.edu.list.dto;

import common.bean.BaseDTO;

/**
 * 자격증분류 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class EduCommonDTO extends BaseDTO
{
	/** ID */
	private String courseListId         = "";
	/** 자격증구분*/
	private String filterEduTypeDesc         = "";
	/** 자격증명 */
	private String filterEduName         = "";
	/** 자격증구분 Desc */
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
