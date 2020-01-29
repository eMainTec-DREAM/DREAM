package dream.asset.list.dto;

import common.bean.BaseDTO;
/**
 * ������Ⱓ��뿹�����
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 */
public class AssetListPartOpQtyDTO extends BaseDTO
{
	/**���񱸼���ǰ �Ⱓ�� �����ID*/
	private String eqPartOpQtyId       = "";
	/**���񱸼���ǰ ID*/
	private String eqPartId            = "";
	/**���� ID*/
	private String equipId             = "";
	/**������� ID*/
	private String eqAsmbId            = "";
	/**��ǰ ID*/
	private String partId              = "";
	/**��Ⱓ[��]*/
	private String opPeriod            = "";
	/**�����*/
	private String opQty               = "";
	/**��ǰ��üPO��ȣ*/
	private String venPoNo             = "";
	/**�ŷ�óID*/
	private String vendorId            = "";
	/**�ŷ�ó�ڵ�*/
	private String vendorNo            = "";
	/**�ŷ�ó��*/
	private String vendorDesc          = "";
	/**���ļ���*/
	private String ordNo               = "";
	/**���*/
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
