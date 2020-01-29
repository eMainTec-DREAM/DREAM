package dream.mgr.contract.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���޷¼��� ����
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrContractDTO extends BaseDTO
{
	/** �ܰ����ID */
	private String contractId 						= "";
	/** ���� ���# */
	private String filterContractNo 				= "";
	/** ���� ���� */
	private String filterContractDesc         		= "";
	/** ���� ��üID */
	private String filterVendorId         			= "";
	/** ���� ��ü�� */
	private String filterVendorDesc         		= "";
	/** ���� ����ID */
	private String filterServiceId         			= "";
	/** ���� ���񽺸� */
	private String filterServiceDesc         		= "";
	/** ���� ���������� */
	private String filterContractStartDate         	= "";
	/** ���� ����������� */
	private String filterContractEndDate         	= "";
	/** ���� From���Ⱓ�������� */
	private String filterContractFromStartDate      = "";
	/** ���� To���Ⱓ�������� */
	private String filterContractFromEndDate        = "";
	/** ���� From���Ⱓ�������� */
	private String filterContractToStartDate        = "";
	/** ���� To���Ⱓ�������� */
	private String filterContractToEndDate         	= "";
	/** ���� ��뿩�� */
	private String filterIsUse         				= "";
	
	/** ���# */
	private String contractNo						= "";
	/** ���� */
	private String contractDesc						= "";
	/** ��뿩�� */
	private String isUse							= "";
	/** ��ü ID */
	private String vendorId							= "";
	/** ��ü # */
	private String vendorNo							= "";
	/** ��ü�� */
	private String vendorDesc						= "";
	/** ������� */
	private String contractDate						= "";
	/** ���������� */
	private String contractStartDate				= "";
	/** ����������� */
	private String contractEndDate					= "";
	/** ���ݾ� */
	private String contractAmount					= "";
	/** ��� */
	private String remark							= "";
	/** �������� */
	private String creTime							= "";
	/** �������� */
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
