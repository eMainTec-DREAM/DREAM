package dream.org.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 구매신청 item - 상세  DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class OrgEmpCertDetailDTO extends BaseDTO
{
	/** 구매신청 itemID */
	private String empCertListId 	= "";
	/** 자재Id */
	private String certListId 		= "";
	/** 자재No */
	private String certNo 		= "";
	/** 품명 */
	private String certName 	= "";
	/** 비고 */
	private String remark 		= "";

	/** 상태 */
	private String empCertStatusDesc 		= "";
	private String empCertStatus 		= "";
	private String certType	= "";
	private String certTypeDesc	= "";

	/** 만료일자 */
	private String expDate	= "";
	/** 취득일자 */
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