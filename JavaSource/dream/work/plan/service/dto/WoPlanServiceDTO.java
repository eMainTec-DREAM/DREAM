package dream.work.plan.service.dto;

import common.bean.BaseDTO;

/**
 * �����۾� DTO 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public class WoPlanServiceDTO extends BaseDTO
{
	/** ���� �۾� ID */
	private String woPlanServiceId		= "";
	/** ���� */
	private String ordNo     			= "";
	/** �۾� Id */
	private String wkOrId     			= "";
	/** �۾��� */
	private String woDesc     			= "";
	/** ���� ID */
	private String serviceId     		= "";
	/** ���� No */
	private String serviceNo     		= "";
	/** ���� DESC */
	private String serviceDesc     		= "";
	/** ��� ID */
	private String contractId     		= "";
	/** ��� No */
	private String contractNo     		= "";
	/** ��� DESC */
	private String contractDesc     	= "";
	/** �ŷ�ó ID */
	private String vendorId     		= "";
	/** �ŷ�ó No */
	private String vendorNo     		= "";
	/** �ŷ�ó DESC */
	private String vendorDesc     		= "";
	/** �۾��ð� */
	private String planWorkTime     	= "";
	/** ���� ID */
	private String uomId   				= "";
	/** ���� DESC */
	private String uomDesc 				= "";
	/** �ܰ� */
	private String unitPrice     		= "";
	/** �ݾ� */
	private String amt     				= "";
	/** ��� */
	private String remark     			= "";
	
	public String getWoPlanServiceId() {
		return woPlanServiceId;
	}
	public void setWoPlanServiceId(String woPlanServiceId) {
		this.woPlanServiceId = woPlanServiceId;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getWoDesc() {
		return woDesc;
	}
	public void setWoDesc(String woDesc) {
		this.woDesc = woDesc;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	public String getPlanWorkTime() {
		return planWorkTime;
	}
	public void setPlanWorkTime(String planWorkTime) {
		this.planWorkTime = planWorkTime;
	}
	public String getUomId() {
		return uomId;
	}
	public void setUomId(String uomId) {
		this.uomId = uomId;
	}
	public String getUomDesc() {
		return uomDesc;
	}
	public void setUomDesc(String uomDesc) {
		this.uomDesc = uomDesc;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
