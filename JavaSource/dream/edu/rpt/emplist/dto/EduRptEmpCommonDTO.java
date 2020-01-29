package dream.edu.rpt.emplist.dto;

import common.bean.BaseDTO;

/**
 * �ڰ��� ��� ��� DTO
 * @author  kim21017
 * @version $Id: EduEmpListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class EduRptEmpCommonDTO extends BaseDTO
{

	/** �ڰ�������*/
	private String filterEduTypeDesc         = "";
	/** �ڰ����� */
	private String filterEduName         = "";
	/** �ڰ������� Desc */
	private String filterEduType         = "";
	private String filterEduTypeId         = "";

	/** id*/
	private String filterEmpId			= "";
	/** ���No*/
	private String filterEmpNo			= "";
	/** ����� */
	private String filterEmpName			= "";
	
	
	public String getFilterEduTypeId() {
		return filterEduTypeId;
	}
	public void setFilterEduTypeId(String filterEduTypeId) {
		this.filterEduTypeId = filterEduTypeId;
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
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpNo() {
		return filterEmpNo;
	}
	public void setFilterEmpNo(String filterEmpNo) {
		this.filterEmpNo = filterEmpNo;
	}
	public String getFilterEmpName() {
		return filterEmpName;
	}
	public void setFilterEmpName(String filterEmpName) {
		this.filterEmpName = filterEmpName;
	}
	
	






}