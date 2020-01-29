package dream.mgr.contract.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �ܰ���� ���� ��� dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class MgrContractItemDTO extends BaseDTO
{
	/** �ܰ���� ���� ITEM ID */
	private String contractItemId 				= "";
	/** �ܰ���� ���� ID */
    private String contractId                   = "";
    
    /** ���� ID */
    private String serviceId              		= "";
    /** ���� # */
    private String serviceNo              		= "";
    /** Item �� */
    private String contractItemDesc             = "";
	/** ��ȸ���� */
	private String ordNo						= "";
	/** ���� */
	private String qty		 					= "";
	/** �ܰ� */
	private String unitPrice 					= "";
	/** �ݾ� */
	private String amount 						= "";
	/** ���ݾ� */
	private String contractAmount				= "";
	/** ���� */
	private String uom 							= "";
	/** ��� */
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