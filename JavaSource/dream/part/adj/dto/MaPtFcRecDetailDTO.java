package dream.part.adj.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����԰� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtFcRecDetailDTO extends BaseDTO
{
	/** �����԰�ID */
	private String fcRecListId     = "";
	/** �԰��ȣ */
	private String fcRecListNo     = "";
	/** �԰����Id */
	private String fcRecListStatus = "";
	/** �԰���¸� */
	private String fcRecListStatusDesc = "";
	/** ���μ�Id */
	private String deptId          = "";
	/** ���μ��� */
	private String deptDesc        = "";
	/** �԰�â��Id */
	private String wcodeId         = "";
	/** �԰�â��� */
	private String wname           = "";
	/** �԰����� */
	private String recDate         = "";
	/** �԰�ǰ�� ����Id */
	private String partId          = "";
	/** �԰�ǰ�� �����ȣ */
	private String partNo          = "";
	/** �԰�ǰ�� �����/�԰� */
	private String partNameSize    = "";
	/** �԰�ǰ�� ����� */
	private String partDesc    	   = "";
	/** �԰�ǰ�� �� */
	private String partModel   	   = "";
	/** �԰�ǰ�� �԰� */
	private String partSize    	   = "";
	/** �԰���� */
	private String recQty          = "";
	/** �԰�ܰ� */
	private String unitPrice       = "";
	/** �԰�ݾ� */
	private String totPrice        = "";
	/** ��� */
	private String remark          = "";
	private String vendorId        = "";
	private String vendorDesc      = "";
	private String partGrade       = "";
	private String partGradeDesc   = "";
	/** ȭ����� ID */
	private String currencyId		= "";
	/** ȭ����� DESC */
	private String currencyDesc		= "";
	
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyDesc() {
		return currencyDesc;
	}
	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}
	/** �԰��̷� - C:�԰�Ϸ�, R:�԰���� */
	private String fcRecMode   = "";
	

    public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getPartModel() {
		return partModel;
	}
	public void setPartModel(String partModel) {
		this.partModel = partModel;
	}
	public String getPartSize() {
		return partSize;
	}
	public void setPartSize(String partSize) {
		this.partSize = partSize;
	}
	public String getPartGrade() {
		return partGrade;
	}
	public String getPartGradeDesc() {
		return partGradeDesc;
	}
	public void setPartGradeDesc(String partGradeDesc) {
		this.partGradeDesc = partGradeDesc;
	}
	public void setPartGrade(String partGrade) {
		this.partGrade = partGrade;
	}
	public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getRecDate()
    {
        return recDate;
    }
    public void setRecDate(String recDate)
    {
        this.recDate = CommonUtil.convertDate(recDate);
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getRecQty()
    {
        return recQty;
    }
    public void setRecQty(String recQty)
    {
        this.recQty = CommonUtil.convertMoney(recQty);
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = CommonUtil.convertMoney(unitPrice);
    }
    public String getTotPrice()
    {
        return totPrice;
    }
    public void setTotPrice(String totPrice)
    {
        this.totPrice = CommonUtil.convertMoney(totPrice);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	public String getFcRecListId() {
		return fcRecListId;
	}
	public void setFcRecListId(String fcRecListId) {
		this.fcRecListId = fcRecListId;
		super.setAuditKey(fcRecListId);
	}
	public String getFcRecListNo() {
		return fcRecListNo;
	}
	public void setFcRecListNo(String fcRecListNo) {
		this.fcRecListNo = fcRecListNo;
	}
	public String getFcRecListStatus() {
		return fcRecListStatus;
	}
	public void setFcRecListStatus(String fcRecListStatus) {
		this.fcRecListStatus = fcRecListStatus;
	}
	public String getFcRecListStatusDesc() {
		return fcRecListStatusDesc;
	}
	public void setFcRecListStatusDesc(String fcRecListStatusDesc) {
		this.fcRecListStatusDesc = fcRecListStatusDesc;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getFcRecMode() {
		return fcRecMode;
	}
	public void setFcRecMode(String fcRecMode) {
		this.fcRecMode = fcRecMode;
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
	
}
