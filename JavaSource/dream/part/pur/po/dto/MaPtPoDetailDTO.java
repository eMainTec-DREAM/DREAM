package dream.part.pur.po.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 발주이력 - 상세 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtPoDetailDTO extends BaseDTO
{
	/** 발주번호ID */
	private String poListId		= "";
	/** 발주번호No */
	private String poListNo		= "";
	/** 부서Id */
	private String deptId		= "";
	/** 부서명 */
	private String deptDesc		= "";
	/** 발주일자 */
	private String poDate		= "";
	/** 입고일자 */
	private String recDate		= "";
	/** 부품Id */
	private String partId		= "";
	/** 부품No */
	private String partNo		= "";
	/** 부품명/규격 */
	private String partNameSize	= "";
	/** 청구수량 */
	private String reqQty		= "";
	/** 발주수량 */
	private String poQty		= "";
	/** 입고수량 */
	private String recQty		= "";
	/** 발주상태 */
	private String poStatusId	= "";
	/** 발주상태명 */
	private String poStatusDesc	= "";
	/** 거래처id */
	private String vendorId		= "";
	/** 거래처명 */
	private String vendorDesc	= "";
	/** 입고창고id */
	private String wcodeId		= "";
	/** 입고창고명 */
	private String wcodeDesc	= "";
	/** 청구번호 */
	private String requestNo	= "";
	/** 비고 */
	private String remark		= "";
	/** 재고상태 */
	private String partGrade	= "";
	/** 발주단가 */
	private String unitPrice	= "";
	/** 발주금액 */
	private String totPrice		= "";
	/** 공장 */
	private String plant		= "";
	/** 환율 */
	private String currency		= "";
	
	
	public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getCurrency()
    {
        return currency;
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
    public String getRecDate() {
		return recDate;
	}
	public void setRecDate(String recDate) {
		this.recDate = CommonUtil.convertDate(recDate);
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getWcodeDesc() {
		return wcodeDesc;
	}
	public void setWcodeDesc(String wcodeDesc) {
		this.wcodeDesc = wcodeDesc;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(String totPrice) {
		this.totPrice = totPrice;
	}
	public String getPartGrade() {
		return partGrade;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getPoListId() {
		return poListId;
	}
	public void setPoListId(String poListId) {
		this.poListId = poListId;
	}
	public String getPoListNo() {
		return poListNo;
	}
	public void setPoListNo(String poListNo) {
		this.poListNo = poListNo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getPoDate() {
		return poDate;
	}
	public void setPoDate(String poDate) {
		this.poDate = CommonUtil.convertDate(poDate);
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	public String getPartNameSize() {
		return partNameSize;
	}
	public void setPartNameSize(String partNameSize) {
		this.partNameSize = partNameSize;
	}
	public String getReqQty() {
		return reqQty;
	}
	public void setReqQty(String reqQty) {
		this.reqQty = reqQty;
	}
	public String getPoQty() {
		return poQty;
	}
	public void setPoQty(String poQty) {
		this.poQty = poQty;
	}
	public String getRecQty() {
		return recQty;
	}
	public void setRecQty(String recQty) {
		this.recQty = recQty;
	}
	public String getPoStatusId() {
		return poStatusId;
	}
	public void setPoStatusId(String poStatusId) {
		this.poStatusId = poStatusId;
	}
	public String getPoStatusDesc() {
		return poStatusDesc;
	}
	public void setPoStatusDesc(String poStatusDesc) {
		this.poStatusDesc = poStatusDesc;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
