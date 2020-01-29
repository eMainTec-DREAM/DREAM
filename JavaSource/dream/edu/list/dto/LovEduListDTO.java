package dream.edu.list.dto;

import common.bean.BaseDTO;

/**
 * �������� �˾� DTO
 * @author  hyosung
 * @version $Id: LovEduListDTO.java,v 1.1 2016/01/18 09:12:12 hyosung Exp $
 * @since   1.0
 */
public class LovEduListDTO extends BaseDTO
{
    /** Search Code */
    private String courseNo 		= "";
    /** Search Description */
    private String description 	= "";
    /** ��뿩�� */
    private String isUse 	= "";
    
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    
}
