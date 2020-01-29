package dream.mgr.mail.dto;

import common.bean.BaseDTO;

/**
 * ���ϼ����ڼ��� - �з�  DTO
 * @author  kim21017
 * @version $Id: MaMailUsrDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaMailUsrDetailDTO extends BaseDTO
{
	/** ������ID */
	private String mailUsrId 	= "";
    /** ���id */
    private String empId 		= "";
    /** ���no */
    private String empNo 		= "";
    /** ����� */
    private String empDesc		= "";
    /** �̸��� */
    private String email 		= "";
    /** ��ȭ��ȣ */
    private String phone 		= "";
    
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
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMailUsrId() {
		return mailUsrId;
	}
	public void setMailUsrId(String mailUsrId) {
		this.mailUsrId = mailUsrId;
		super.setAuditKey(mailUsrId);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}