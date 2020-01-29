package dream.mgr.contract.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * 단가계약 LOV DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class LovMgrContractListDTO extends BaseDTO
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
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	public String getFilterIsUse() {
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) {
		this.filterIsUse = filterIsUse;
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
}
