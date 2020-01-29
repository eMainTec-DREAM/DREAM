package dream.cert.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자격증 자격직원 상세 DTO
 * @author  kim21017
 * @version $Id: CertEmpDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class CertEmpDetailDTO extends BaseDTO
{
	/** 자격증 사원 id */
	private String empCertListId	= "";
	/** id*/
	private String empId			= "";
	/** 사원No*/
	private String empNo			= "";
	/** 사원명 */
	private String empName			= "";
	/** 자격증  id */
	private String certListId		= "";
	/** 자격증 번호 */
	private String certNo		    = "";
	/** 자격증 명 */
	private String certName		    = "";
	/** 취득일자 */
	private String acqDate 		    = "";
	/** 만료예정일자 */
	private String expDate 			= "";
	/** 자격증 보유상태 */
	private String empCertStatusCode = "";
	/** 작업시간 종료시간 */
	private String empCertStatusName = "";
	/** 비고 */
	private String remark 			= "";
	/** 부서 */
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