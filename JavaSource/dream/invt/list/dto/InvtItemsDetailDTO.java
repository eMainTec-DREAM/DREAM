package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��  DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class InvtItemsDetailDTO extends BaseDTO
{
	/** �������� ����Ʈ id */
	private String invtlistId 	= "";
	/** �����׸�ID */
	private String invtItemsId 	= "";
	/** �����׸�� */
	private String itemDesc 	= "";
	/** ���ڱݾ� */
	private String amt 			= "";
	/** ������� */
	private String ordNo 		= "";
	/** ��� */
	private String remark 		= "";
	
	/** ���� */
	private String spec            = "";
	/** ��û���� */
	private String recQty          = "";
	/** ��û�ܰ� */
	private String unitPrice       = "";
	/** ��ȭ */
	private String currency        = "";
	/** ��ȭ�� */
	private String currencyDesc    = "";
	/** ���ű׷� */
	private String dwbuygroup      = "";
	/** �������� */
	private String dwtaxgubun      = "";
	/** ���ű׷��  */
	private String dwbuygroupDesc  = "";
	/** �������и� */
	private String dwtaxgubunDesc  = "";
	
	private String dwptcontitemStatus      = "";
	
	private String dwptcontitemStatusDesc  = "";
	/** ����ID */
	private String equipId         = "";
	/** ����� */
	private String equipDesc       = "";
	/** ���� */
	private String unit            = "";
	/** ǰ�� ID */
	private String partId          = "";
	/** ǰ�� �ڵ�  */
	private String partNo          = "";
	
	/** OLD �����׸�ID */
	private String oldInvtItemsId 	= "";

	
	public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getUnit()
    {
        return unit;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getDwptcontitemStatus()
    {
        return dwptcontitemStatus;
    }
    public void setDwptcontitemStatus(String dwptcontitemStatus)
    {
        this.dwptcontitemStatus = dwptcontitemStatus;
    }
    public String getDwptcontitemStatusDesc()
    {
        return dwptcontitemStatusDesc;
    }
    public void setDwptcontitemStatusDesc(String dwptcontitemStatusDesc)
    {
        this.dwptcontitemStatusDesc = dwptcontitemStatusDesc;
    }
    public String getSpec()
    {
        return spec;
    }
    public void setSpec(String spec)
    {
        this.spec = spec;
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
    public String getCurrency()
    {
        return currency;
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
    public String getCurrencyDesc()
    {
        return currencyDesc;
    }
    public void setCurrencyDesc(String currencyDesc)
    {
        this.currencyDesc = currencyDesc;
    }
    public String getDwbuygroup()
    {
        return dwbuygroup;
    }
    public void setDwbuygroup(String dwbuygroup)
    {
        this.dwbuygroup = dwbuygroup;
    }
    public String getDwtaxgubun()
    {
        return dwtaxgubun;
    }
    public void setDwtaxgubun(String dwtaxgubun)
    {
        this.dwtaxgubun = dwtaxgubun;
    }
    public String getDwbuygroupDesc()
    {
        return dwbuygroupDesc;
    }
    public void setDwbuygroupDesc(String dwbuygroupDesc)
    {
        this.dwbuygroupDesc = dwbuygroupDesc;
    }
    public String getDwtaxgubunDesc()
    {
        return dwtaxgubunDesc;
    }
    public void setDwtaxgubunDesc(String dwtaxgubunDesc)
    {
        this.dwtaxgubunDesc = dwtaxgubunDesc;
    }
    public String getInvtlistId() {
		return invtlistId;
	}
	public String getOldInvtItemsId() {
		return oldInvtItemsId;
	}
	public void setOldInvtItemsId(String oldInvtItemsId) {
		this.oldInvtItemsId = oldInvtItemsId;
	}
	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
		super.setAuditKey(invtlistId);
	}
	public String getInvtItemsId() {
		return invtItemsId;
	}
	public void setInvtItemsId(String invtItemsId) {
		this.invtItemsId = invtItemsId;
		super.setAuditKey(invtItemsId);
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = CommonUtil.convertMoney(amt);
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}