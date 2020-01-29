package dream.mgr.contract.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 단가계약 설정 목록 dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MgrContractItemDTO extends BaseDTO
{
	/** 단가계약 설정 ITEM ID */
	private String contractItemId 				= "";
	/** 단가계약 설정 ID */
    private String contractId                   = "";
    
    /** 서비스 ID */
    private String serviceId              		= "";
    /** 서비스 # */
    private String serviceNo              		= "";
    /** Item 명 */
    private String contractItemDesc             = "";
	/** 조회순서 */
	private String ordNo						= "";
	/** 수량 */
	private String qty		 					= "";
	/** 단가 */
	private String unitPrice 					= "";
	/** 금액 */
	private String amount 						= "";
	/** 계약금액 */
	private String contractAmount				= "";
	/** 단위 */
	private String uom 							= "";
	/** 비고 */
	private String remark						= "";
	
	public String getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(String contractAmount) {
		this.contractAmount = CommonUtil.convertMoney(contractAmount);
	}
	public String getContractItemId() {
		return contractItemId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getContractItemDesc() {
		return contractItemDesc;
	}
	public void setContractItemDesc(String contractItemDesc) {
		this.contractItemDesc = contractItemDesc;
	}
	public void setContractItemId(String contractItemId) {
		this.contractItemId = contractItemId;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = CommonUtil.convertMoney(qty);
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = CommonUtil.convertMoney(unitPrice);
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = CommonUtil.convertMoney(amount);
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}