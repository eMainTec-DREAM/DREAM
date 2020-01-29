package dream.work.service.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 서비스 설정 목록 dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class WorkServicePriceDTO extends BaseDTO
{
	/** 단가계약 설정 ITEM ID */
	private String servicePriceId 				= "";
	/** 단가계약 설정 ID */
    private String serviceId                    = "";
    
    /** 적용기간 from */
    private String fromDate 					= "";
    /** 적용기간 to */
    private String toDate						= "";
	/** 단가 */
	private String unitPrice 					= "";
	/** 비고 */
	private String remark						= "";

	/** 생성일자 */
	private String creTime						= "";
	/** 수정일자 */
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