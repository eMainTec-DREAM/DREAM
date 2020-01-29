package dream.part.adj.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 무상입고 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtFcRecDetailDTO extends BaseDTO
{
	/** 무상입고ID */
	private String fcRecListId     = "";
	/** 입고번호 */
	private String fcRecListNo     = "";
	/** 입고상태Id */
	private String fcRecListStatus = "";
	/** 입고상태명 */
	private String fcRecListStatusDesc = "";
	/** 담당부서Id */
	private String deptId          = "";
	/** 담당부서명 */
	private String deptDesc        = "";
	/** 입고창고Id */
	private String wcodeId         = "";
	/** 입고창고명 */
	private String wname           = "";
	/** 입고일자 */
	private String recDate         = "";
	/** 입고품목 자재Id */
	private String partId          = "";
	/** 입고품목 자재번호 */
	private String partNo          = "";
	/** 입고품목 자재명/규격 */
	private String partNameSize    = "";
	/** 입고품목 자재명 */
	private String partDesc    	   = "";
	/** 입고품목 모델 */
	private String partModel   	   = "";
	/** 입고품목 규격 */
	private String partSize    	   = "";
	/** 입고수량 */
	private String recQty          = "";
	/** 입고단가 */
	private String unitPrice       = "";
	/** 입고금액 */
	private String totPrice        = "";
	/** 비고 */
	private String remark          = "";
	private String vendorId        = "";
	private String vendorDesc      = "";
	private String partGrade       = "";
	private String partGradeDesc   = "";
	/** 화폐단위 ID */
	private String currencyId		= "";
	/** 화폐단위 DESC */
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
	/** 입고이력 - C:입고완료, R:입고취소 */
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
