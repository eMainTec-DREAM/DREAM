package dream.part.pur.po.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����̷� - �� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtPoDetailDTO extends BaseDTO
{
	/** ���ֹ�ȣID */
	private String poListId		= "";
	/** ���ֹ�ȣNo */
	private String poListNo		= "";
	/** �μ�Id */
	private String deptId		= "";
	/** �μ��� */
	private String deptDesc		= "";
	/** �������� */
	private String poDate		= "";
	/** �԰����� */
	private String recDate		= "";
	/** ��ǰId */
	private String partId		= "";
	/** ��ǰNo */
	private String partNo		= "";
	/** ��ǰ��/�԰� */
	private String partNameSize	= "";
	/** û������ */
	private String reqQty		= "";
	/** ���ּ��� */
	private String poQty		= "";
	/** �԰���� */
	private String recQty		= "";
	/** ���ֻ��� */
	private String poStatusId	= "";
	/** ���ֻ��¸� */
	private String poStatusDesc	= "";
	/** �ŷ�óid */
	private String vendorId		= "";
	/** �ŷ�ó�� */
	private String vendorDesc	= "";
	/** �԰�â��id */
	private String wcodeId		= "";
	/** �԰�â��� */
	private String wcodeDesc	= "";
	/** û����ȣ */
	private String requestNo	= "";
	/** ��� */
	private String remark		= "";
	/** ������ */
	private String partGrade	= "";
	/** ���ִܰ� */
	private String unitPrice	= "";
	/** ���ֱݾ� */
	private String totPrice		= "";
	/** ���� */
	private String plant		= "";
	/** ȯ�� */
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
