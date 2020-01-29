package dream.work.plan.service.dto;

import common.bean.BaseDTO;

/**
 * 서비스작업 DTO 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public class WoPlanServiceDTO extends BaseDTO
{
	/** 서비스 작업 ID */
	private String woPlanServiceId		= "";
	/** 순서 */
	private String ordNo     			= "";
	/** 작업 Id */
	private String wkOrId     			= "";
	/** 작업명 */
	private String woDesc     			= "";
	/** 서비스 ID */
	private String serviceId     		= "";
	/** 서비스 No */
	private String serviceNo     		= "";
	/** 서비스 DESC */
	private String serviceDesc     		= "";
	/** 계약 ID */
	private String contractId     		= "";
	/** 계약 No */
	private String contractNo     		= "";
	/** 계약 DESC */
	private String contractDesc     	= "";
	/** 거래처 ID */
	private String vendorId     		= "";
	/** 거래처 No */
	private String vendorNo     		= "";
	/** 거래처 DESC */
	private String vendorDesc     		= "";
	/** 작업시간 */
	private String planWorkTime     	= "";
	/** 단위 ID */
	private String uomId   				= "";
	/** 단위 DESC */
	private String uomDesc 				= "";
	/** 단가 */
	private String unitPrice     		= "";
	/** 금액 */
	private String amt     				= "";
	/** 비고 */
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
