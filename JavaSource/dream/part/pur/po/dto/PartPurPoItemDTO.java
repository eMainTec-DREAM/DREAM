package dream.part.pur.po.dto;

import common.bean.BaseDTO;

/**
 * 발주품목 - DTO
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class PartPurPoItemDTO extends BaseDTO
{
	/** 발주품목ID */
	private String ptPoItemId	= "";
	/** 발주번호ID */
	private String poListId		= "";
	/** 구매요청품목ID */
	private String ptPrItemId	= "";
	/** 부품ID */
	private String partId       = "";
	/** 부품등급 */
	private String partGrade    = "";
	/** 발주수량 */
	private String poQty        = "";
	/** 발주단가 */
	private String unitPrice    = "";
	/** 발주금액 */
	private String totPrice     = "";
	/** 입고수량 */
	private String grQty        = "";
	/** 입고작성수량 */
	private String grOnQty      = "";
	/** 비고 */
	private String remark       = "";
	/** 발주상태 */
	private String poListStatus = "";
	/** 공장 */
	private String plant        = "";
	/** 부서ID */
	private String deptId       = "";
	/** 창고ID */
	private String wcodeId      = "";
	/** 거래처ID */
	private String vendorId     = "";
	/** 발주일자 */
	private String poDate       = "";
	/** 부품명 */
	private String partDesc     = "";
	/** 규격 */
	private String ptSize       = "";
	/** 단위 */
	private String uom          = "";
	
    public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getPoDate()
    {
        return poDate;
    }
    public void setPoDate(String poDate)
    {
        this.poDate = poDate;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getPtPoItemId()
    {
        return ptPoItemId;
    }
    public void setPtPoItemId(String ptPoItemId)
    {
        this.ptPoItemId = ptPoItemId;
    }
    public String getPoListId()
    {
        return poListId;
    }
    public void setPoListId(String poListId)
    {
        this.poListId = poListId;
    }
    public String getPtPrItemId()
    {
        return ptPrItemId;
    }
    public void setPtPrItemId(String ptPrItemId)
    {
        this.ptPrItemId = ptPrItemId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getPoQty()
    {
        return poQty;
    }
    public void setPoQty(String poQty)
    {
        this.poQty = poQty;
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    public String getTotPrice()
    {
        return totPrice;
    }
    public void setTotPrice(String totPrice)
    {
        this.totPrice = totPrice;
    }
    public String getGrQty()
    {
        return grQty;
    }
    public void setGrQty(String grQty)
    {
        this.grQty = grQty;
    }
    public String getGrOnQty()
    {
        return grOnQty;
    }
    public void setGrOnQty(String grOnQty)
    {
        this.grOnQty = grOnQty;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getPoListStatus()
    {
        return poListStatus;
    }
    public void setPoListStatus(String poListStatus)
    {
        this.poListStatus = poListStatus;
    }
}
