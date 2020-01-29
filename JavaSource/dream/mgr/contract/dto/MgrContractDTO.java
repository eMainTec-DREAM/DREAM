package dream.mgr.contract.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사용달력설정 공통
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrContractDTO extends BaseDTO
{
	/** 단가계약ID */
	private String contractId 						= "";
	/** 필터 계약# */
	private String filterContractNo 				= "";
	/** 필터 계약명 */
	private String filterContractDesc         		= "";
	/** 필터 업체ID */
	private String filterVendorId         			= "";
	/** 필터 업체명 */
	private String filterVendorDesc         		= "";
	/** 필터 서비스ID */
	private String filterServiceId         			= "";
	/** 필터 서비스명 */
	private String filterServiceDesc         		= "";
	/** 필터 계약시작일자 */
	private String filterContractStartDate         	= "";
	/** 필터 계약종료일자 */
	private String filterContractEndDate         	= "";
	/** 필터 From계약기간시작일자 */
	private String filterContractFromStartDate      = "";
	/** 필터 To계약기간종료일자 */
	private String filterContractFromEndDate        = "";
	/** 필터 From계약기간시작일자 */
	private String filterContractToStartDate        = "";
	/** 필터 To계약기간종료일자 */
	private String filterContractToEndDate         	= "";
	/** 필터 사용여부 */
	private String filterIsUse         				= "";
	
	/** 계약# */
	private String contractNo						= "";
	/** 계약명 */
	private String contractDesc						= "";
	/** 사용여부 */
	private String isUse							= "";
	/** 업체 ID */
	private String vendorId							= "";
	/** 업체 # */
	private String vendorNo							= "";
	/** 업체명 */
	private String vendorDesc						= "";
	/** 계약일자 */
	private String contractDate						= "";
	/** 계약시작일자 */
	private String contractStartDate				= "";
	/** 계약종료일자 */
	private String contractEndDate					= "";
	/** 계약금액 */
	private String contractAmount					= "";
	/** 비고 */
	private String remark							= "";
	/** 생성일자 */
	private String creTime							= "";
	/** 수정일자 */
	private String updTime							= "";
	
	public String getContractId() {
		return contractId;
	}
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}
	public String getUpdTime() {
		return updTime;
	}
	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
		super.setAuditKey(contractId);
	}
	public String getFilterContractNo() {
		return filterContractNo;
	}
	public void setFilterContractNo(String filterContractNo) {
		this.filterContractNo = filterContractNo;
	}
	public String getFilterContractDesc() {
		return filterContractDesc;
	}
	public void setFilterContractDesc(String filterContractDesc) {
		this.filterContractDesc = filterContractDesc;
	}
	public String getFilterVendorId() {
		return filterVendorId;
	}
	public void setFilterVendorId(String filterVendorId) {
		this.filterVendorId = filterVendorId;
	}
	public String getFilterVendorDesc() {
		return filterVendorDesc;
	}
	public void setFilterVendorDesc(String filterVendorDesc) {
		this.filterVendorDesc = filterVendorDesc;
	}
	public String getFilterServiceId() {
		return filterServiceId;
	}
	public void setFilterServiceId(String filterServiceId) {
		this.filterServiceId = filterServiceId;
	}
	public String getFilterServiceDesc() {
		return filterServiceDesc;
	}
	public void setFilterServiceDesc(String filterServiceDesc) {
		this.filterServiceDesc = filterServiceDesc;
	}
	public String getFilterContractStartDate() {
		return filterContractStartDate;
	}
	public void setFilterContractStartDate(String filterContractStartDate) {
		this.filterContractStartDate = CommonUtil.convertDate(filterContractStartDate);
	}
	public String getFilterContractEndDate() {
		return filterContractEndDate;
	}
	public void setFilterContractEndDate(String filterContractEndDate) {
		this.filterContractEndDate = CommonUtil.convertDate(filterContractEndDate);
	}
	public String getFilterContractFromStartDate() {
		return filterContractFromStartDate;
	}
	public void setFilterContractFromStartDate(String filterContractFromStartDate) {
		this.filterContractFromStartDate = CommonUtil.convertDate(filterContractFromStartDate);
	}
	public String getFilterContractFromEndDate() {
		return filterContractFromEndDate;
	}
	public void setFilterContractFromEndDate(String filterContractFromEndDate) {
		this.filterContractFromEndDate = CommonUtil.convertDate(filterContractFromEndDate);
	}
	public String getFilterContractToStartDate() {
		return filterContractToStartDate;
	}
	public void setFilterContractToStartDate(String filterContractToStartDate) {
		this.filterContractToStartDate = CommonUtil.convertDate(filterContractToStartDate);
	}
	public String getFilterContractToEndDate() {
		return filterContractToEndDate;
	}
	public void setFilterContractToEndDate(String filterContractToEndDate) {
		this.filterContractToEndDate = CommonUtil.convertDate(filterContractToEndDate);
	}
	public String getFilterIsUse() {
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) {
		this.filterIsUse = filterIsUse;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractDesc() {
		return contractDesc;
	}
	public void setContractDesc(String contractDesc) {
		this.contractDesc = contractDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorNo() {
		return vendorNo;
	}
	public void setVendorNo(String vendorNo) {
		this.vendorNo = vendorNo;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = CommonUtil.convertDate(contractDate);
	}
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = CommonUtil.convertDate(contractStartDate);
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = CommonUtil.convertDate(contractEndDate);
	}
	public String getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(String contractAmount) {
		this.contractAmount = CommonUtil.convertMoney(contractAmount);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
