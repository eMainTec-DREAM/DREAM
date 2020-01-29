package dream.mgr.contract.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;
/**
 * �ܰ���� LOV DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class LovMgrContractListDTO extends BaseDTO
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
