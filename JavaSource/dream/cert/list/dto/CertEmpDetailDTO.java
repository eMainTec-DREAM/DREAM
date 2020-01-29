package dream.cert.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �ڰ��� �ڰ����� �� DTO
 * @author  kim21017
 * @version $Id: CertEmpDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class CertEmpDetailDTO extends BaseDTO
{
	/** �ڰ��� ��� id */
	private String empCertListId	= "";
	/** id*/
	private String empId			= "";
	/** ���No*/
	private String empNo			= "";
	/** ����� */
	private String empName			= "";
	/** �ڰ���  id */
	private String certListId		= "";
	/** �ڰ��� ��ȣ */
	private String certNo		    = "";
	/** �ڰ��� �� */
	private String certName		    = "";
	/** ������� */
	private String acqDate 		    = "";
	/** ���Ό������ */
	private String expDate 			= "";
	/** �ڰ��� �������� */
	private String empCertStatusCode = "";
	/** �۾��ð� ����ð� */
	private String empCertStatusName = "";
	/** ��� */
	private String remark 			= "";
	/** �μ� */
	private String deptName 			= "";
	
	
	
	
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCertListId() {
		return empCertListId;
	}
	public void setEmpCertListId(String empCertListId) {
		this.empCertListId = empCertListId;
		super.setAuditKey(empCertListId);
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
	public String getCertListId() {
		return certListId;
	}
	public void setCertListId(String certListId) {
		this.certListId = certListId;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getAcqDate() {
		return acqDate;
	}
	public void setAcqDate(String acqDate) {
		this.acqDate = CommonUtil.convertDate(acqDate);
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = CommonUtil.convertDate(expDate);;
	}
	public String getEmpCertStatusCode() {
		return empCertStatusCode;
	}
	public void setEmpCertStatusCode(String empCertStatusCode) {
		this.empCertStatusCode = empCertStatusCode;
	}
	public String getEmpCertStatusName() {
		return empCertStatusName;
	}
	public void setEmpCertStatusName(String empCertStatusName) {
		this.empCertStatusName = empCertStatusName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	

}