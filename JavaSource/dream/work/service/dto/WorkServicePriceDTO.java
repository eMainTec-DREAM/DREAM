package dream.work.service.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� ���� ��� dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class WorkServicePriceDTO extends BaseDTO
{
	/** �ܰ���� ���� ITEM ID */
	private String servicePriceId 				= "";
	/** �ܰ���� ���� ID */
    private String serviceId                    = "";
    
    /** ����Ⱓ from */
    private String fromDate 					= "";
    /** ����Ⱓ to */
    private String toDate						= "";
	/** �ܰ� */
	private String unitPrice 					= "";
	/** ��� */
	private String remark						= "";

	/** �������� */
	private String creTime						= "";
	/** �������� */
	private String updTime						= "";
	
	public String getUnitPrice() {
		return unitPrice;
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
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = CommonUtil.convertMoney(unitPrice);
	}
	public String getServicePriceId() {
		return servicePriceId;
	}
	public void setServicePriceId(String servicePriceId) {
		this.servicePriceId = servicePriceId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = CommonUtil.convertDate(fromDate);
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = CommonUtil.convertDate(toDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}