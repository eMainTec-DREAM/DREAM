package dream.org.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���Ž�û item - ��  DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class OrgEmpCertDetailDTO extends BaseDTO
{
	/** ���Ž�û itemID */
	private String empCertListId 	= "";
	/** ����Id */
	private String certListId 		= "";
	/** ����No */
	private String certNo 		= "";
	/** ǰ�� */
	private String certName 	= "";
	/** ��� */
	private String remark 		= "";

	/** ���� */
	private String empCertStatusDesc 		= "";
	private String empCertStatus 		= "";
	private String certType	= "";
	private String certTypeDesc	= "";

	/** �������� */
	private String expDate	= "";
	/** ������� */
	private String acqDate	= "";
	


	public String getEmpCertListId() {
		return empCertListId;
	}
	public void setEmpCertListId(String empCertListId) {
		this.empCertListId = empCertListId;
		super.setAuditKey(empCertListId);
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmpCertStatusDesc() {
		return empCertStatusDesc;
	}
	public void setEmpCertStatusDesc(String empCertStatusDesc) {
		this.empCertStatusDesc = empCertStatusDesc;
	}
	public String getEmpCertStatus() {
		return empCertStatus;
	}
	public void setEmpCertStatus(String empCertStatus) {
		this.empCertStatus = empCertStatus;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertTypeDesc() {
		return certTypeDesc;
	}
	public void setCertTypeDesc(String certTypeDesc) {
		this.certTypeDesc = certTypeDesc;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = CommonUtil.convertDate(expDate) ;
	}
	public String getAcqDate() {
		return acqDate;
	}
	public void setAcqDate(String acqDate) {
		this.acqDate = CommonUtil.convertDate(acqDate);
	}
	
	
}