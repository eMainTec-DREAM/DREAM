package dream.cert.rpt.emplist.dto;

import common.bean.BaseDTO;

/**
 * �ڰ��� ��� ��� DTO
 * @author  kim21017
 * @version $Id: CertEmpListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class CertRptEmpCommonDTO extends BaseDTO
{

	/** �ڰ�������*/
	private String filterCertTypeDesc     = "";
	/** �ڰ����� */
	private String filterCertName         = "";
	/** �ڰ������� Desc */
	private String filterCertType         = "";
	private String filterCertTypeId         = "";
	
	/** id*/
	private String filterEmpId			= "";
	/** ���No*/
	private String filterEmpNo			= "";
	/** ����� */
	private String filterEmpName			= "";
	
	
	
	
	public String getFilterCertTypeId() {
		return filterCertTypeId;
	}
	public void setFilterCertTypeId(String filterCertTypeId) {
		this.filterCertTypeId = filterCertTypeId;
	}
	public String getFilterCertTypeDesc() {
		return filterCertTypeDesc;
	}
	public void setFilterCertTypeDesc(String filterCertTypeDesc) {
		this.filterCertTypeDesc = filterCertTypeDesc;
	}
	public String getFilterCertName() {
		return filterCertName;
	}
	public void setFilterCertName(String filterCertName) {
		this.filterCertName = filterCertName;
	}
	public String getFilterCertType() {
		return filterCertType;
	}
	public void setFilterCertType(String filterCertType) {
		this.filterCertType = filterCertType;
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