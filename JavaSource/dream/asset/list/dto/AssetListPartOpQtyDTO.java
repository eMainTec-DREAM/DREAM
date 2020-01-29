package dream.asset.list.dto;

import common.bean.BaseDTO;
/**
 * 설비운용기간사용예상수량
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class AssetListPartOpQtyDTO extends BaseDTO
{
	/**설비구성부품 기간별 운영기준ID*/
	private String eqPartOpQtyId       = "";
	/**설비구성부품 ID*/
	private String eqPartId            = "";
	/**설비 ID*/
	private String equipId             = "";
	/**설비부위 ID*/
	private String eqAsmbId            = "";
	/**부품 ID*/
	private String partId              = "";
	/**운영기간[년]*/
	private String opPeriod            = "";
	/**운영수량*/
	private String opQty               = "";
	/**납품업체PO번호*/
	private String venPoNo             = "";
	/**거래처ID*/
	private String vendorId            = "";
	/**거래처코드*/
	private String vendorNo            = "";
	/**거래처명*/
	private String vendorDesc          = "";
	/**정렬순서*/
	private String ordNo               = "";
	/**비고*/
	private String remark              = "";
	
    public String getVendorNo()
    {
        return vendorNo;
    }
    public void setVendorNo(String vendorNo)
    {
        this.vendorNo = vendorNo;
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getEqPartOpQtyId()
    {
        return eqPartOpQtyId;
    }
    public void setEqPartOpQtyId(String eqPartOpQtyId)
    {
        this.eqPartOpQtyId = eqPartOpQtyId;
    }
    public String getEqPartId()
    {
        return eqPartId;
    }
    public void setEqPartId(String eqPartId)
    {
        this.eqPartId = eqPartId;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getOpPeriod()
    {
        return opPeriod;
    }
    public void setOpPeriod(String opPeriod)
    {
        this.opPeriod = opPeriod;
    }
    public String getOpQty()
    {
        return opQty;
    }
    public void setOpQty(String opQty)
    {
        this.opQty = opQty;
    }
    public String getVenPoNo()
    {
        return venPoNo;
    }
    public void setVenPoNo(String venPoNo)
    {
        this.venPoNo = venPoNo;
    }
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
