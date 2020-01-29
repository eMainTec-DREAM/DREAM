package dream.tool.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��������з�(������) - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaPttMstrDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** ��ǰID */
	private String partId 			= "";
	/** �μ���ȣ */
	private String partNo 			= "";
	/** ��ǰ�� */
	private String description 		= "";
	/** ��ǰ�԰� */
	private String ptSize			= "";
	/** ���� */
	private String uom              = "";
	/** ��ǰ��/�԰�/���� - ���հ˻� */
	private String fullDesc         = "";
	/** �� */
	private String model            = "";
	/** ���ۻ� */
	private String maker            = "";
	/** ���ó */
	private String usage            = "";
	/** �ֽű��Ŵܰ� */
	private String lastPrice        = "";
	/** ����,���ڱ��� */
	private String plfType          = "";
	/** ����,���ڱ��� �� */
	private String plfTypeDesc      = "";
	/** ����ǰ/�Ҹ�ǰ ���� */
	private String mroType          = "";
	/** ����ǰ/�Ҹ�ǰ ���� �� */
	private String mroTypeDesc      = "";
	/** ����,���뱸�� */
	private String pcoType          = "";
	/** ����,���뱸�� �� */
	private String pcoTypeDesc      = "";
	/** ����ó */
	private String seller           = "";
	/** ���󸮵�Ÿ��[����] */
	private String leadTime         = "";
	/** ��뿩�� */
	private String isUse 			= "Y";
	
	/** ���� */
	private String kind            = "";
	/** Variation Class */
	private String varClass        = "";
	/** Part Group  */
	private String partGroup       = "";
	/** part Group Desc */
	private String partGroupDesc   = "";
	/** vendor part code */
	private String vendorPtCode    = "";
	private String partCateg       = "";
	private String partCategDesc   = "";
	/** ��뿩�� */
	private String isSerial 	   = "N";
	
    public String getIsSerial() {
		return isSerial;
	}
	public void setIsSerial(String isSerial) {
		this.isSerial = isSerial;
	}
	public String getKind()
    {
        return kind;
    }
    public void setKind(String kind)
    {
        this.kind = kind;
    }
    public String getVarClass()
    {
        return varClass;
    }
    public void setVarClass(String varClass)
    {
        this.varClass = varClass;
    }
    public String getPartGroup()
    {
        return partGroup;
    }
    public void setPartGroup(String partGroup)
    {
        this.partGroup = partGroup;
    }
    public String getPartGroupDesc()
    {
        return partGroupDesc;
    }
    public void setPartGroupDesc(String partGroupDesc)
    {
        this.partGroupDesc = partGroupDesc;
    }
    public String getMroType() {
		return mroType;
	}
	public void setMroType(String mroType) {
		this.mroType = mroType;
	}
	public String getMroTypeDesc() {
		return mroTypeDesc;
	}
	public void setMroTypeDesc(String mroTypeDesc) {
		this.mroTypeDesc = mroTypeDesc;
	}
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
        super.setAuditKey(partId);
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
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
    public String getFullDesc()
    {
        return fullDesc;
    }
    public void setFullDesc(String fullDesc)
    {
        this.fullDesc = fullDesc;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }

    public String getMaker()
    {
        return maker;
    }
    public void setMaker(String maker)
    {
        this.maker = maker;
    }
    public String getUsage()
    {
        return usage;
    }
    public void setUsage(String usage)
    {
        this.usage = usage;
    }
    public String getLastPrice()
    {
        return lastPrice;
    }
    public void setLastPrice(String lastPrice)
    {
        this.lastPrice = CommonUtil.convertMoney(lastPrice);
    }
    public String getPlfType()
    {
        return plfType;
    }
    public void setPlfType(String plfType)
    {
        this.plfType = plfType;
    }
    public String getPlfTypeDesc()
    {
        return plfTypeDesc;
    }
    public void setPlfTypeDesc(String plfTypeDesc)
    {
        this.plfTypeDesc = plfTypeDesc;
    }
    public String getPcoType()
    {
        return pcoType;
    }
    public void setPcoType(String pcoType)
    {
        this.pcoType = pcoType;
    }
    public String getPcoTypeDesc()
    {
        return pcoTypeDesc;
    }
    public void setPcoTypeDesc(String pcoTypeDesc)
    {
        this.pcoTypeDesc = pcoTypeDesc;
    }
    public String getSeller()
    {
        return seller;
    }
    public void setSeller(String seller)
    {
        this.seller = seller;
    }
    public String getLeadTime()
    {
        return leadTime;
    }
    public void setLeadTime(String leadTime)
    {
        this.leadTime = leadTime;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
	public String getVendorPtCode() {
		return vendorPtCode;
	}
	public void setVendorPtCode(String vendorPtCode) {
		this.vendorPtCode = vendorPtCode;
	}
	public String getPartCateg() {
		return partCateg;
	}
	public void setPartCateg(String partCateg) {
		this.partCateg = partCateg;
	}
	public String getPartCategDesc() {
		return partCategDesc;
	}
	public void setPartCategDesc(String partCategDesc) {
		this.partCategDesc = partCategDesc;
	}
    
    
	
}
