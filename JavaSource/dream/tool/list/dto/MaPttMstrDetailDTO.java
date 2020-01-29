package dream.tool.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 보전자재분류(마스터) - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaPttMstrDetailDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 부품ID */
	private String partId 			= "";
	/** 부서번호 */
	private String partNo 			= "";
	/** 부품명 */
	private String description 		= "";
	/** 부품규격 */
	private String ptSize			= "";
	/** 단위 */
	private String uom              = "";
	/** 부품명/규격/단위 - 통합검색 */
	private String fullDesc         = "";
	/** 모델 */
	private String model            = "";
	/** 제작사 */
	private String maker            = "";
	/** 사용처 */
	private String usage            = "";
	/** 최신구매단가 */
	private String lastPrice        = "";
	/** 내자,외자구분 */
	private String plfType          = "";
	/** 내자,외자구분 명 */
	private String plfTypeDesc      = "";
	/** 수리품/소모품 구분 */
	private String mroType          = "";
	/** 수리품/소모품 구분 명 */
	private String mroTypeDesc      = "";
	/** 개별,공용구분 */
	private String pcoType          = "";
	/** 개별,공용구분 명 */
	private String pcoTypeDesc      = "";
	/** 구입처 */
	private String seller           = "";
	/** 예상리드타일[일자] */
	private String leadTime         = "";
	/** 사용여부 */
	private String isUse 			= "Y";
	
	/** 제질 */
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
	/** 사용여부 */
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
