package dream.part.iss.wo.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 자재출고확정 - 상세 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaPtIssDetailDTO extends BaseDTO
{
    /** 출고번호 */
    private String ptisslistNo      = "";
    /** 작업일자 */
    private String startDate    = "";
    /** 출고구분명 */
    private String ptissTypeDesc    = "";
    /** 작업명 */
    private String woDesc           = "";
    /** 작업상태 */
    private String woStatus         = "";
    /** 작업상태명 */
    private String woStatusDesc     = "";
    /** 설비명 */
    private String equipDesc        = "";
    /** 설비위치 */
    private String eqLocDesc        = "";
    /** 부품명/규격 */
    private String partDesc         = "";
    /** 출고일자 */
    private String issueDate        = "";
    /** 작업번호 */
    private String woNo             = "";
    /** ERP 창고  */
    private String outWcode         = "";
    /** ERP 플랜트 */
    private String outPlant         = "";
    /** 전기일 */
    private String budat            = "";
    /** 창고 타입 (LEGACY:ERP) */
    private String whType           = "";
    
    /**  */
    private String totPrice 		= "";
    /**  */
    private String unitPrice 		= "";
    /** 사용수량 */
    private String issueQty 		= "";
    /**  */
    private String wkorId 			= "";
    /**  */
    private String wopartId 		= "";
    /**  */
    private String ptisslistId 		= "";
    /**  */
    private String compNo 			= "";
    /** 출고종류 */
    private String ptissType 		= "";
    /** ERP생성년도 */
    private String erpYyyy 			= "";
    /** ERP출고번호 */
    private String erpIssNo 		= "";
    /** 수령자 */
    private String recBy 			= "";
    /** 출고자 */
    private String issueBy 			= "";
    /** 출고부서 */
    private String issueDept 		= "";
    /** 출고상태[미출고,출고완료] */
    private String ptissStatus 		= "";
    /** 출고상태명 */
    private String ptissStatusDesc  = "";
    
    private String costCenter   	= "";
    /** 단위 */
    private String uom      		= "";
    /**is serial part*/
    private String isSerial 		="";
    
    /**serialNo */
    private String serialNo 		="";
    /** equip_id */
    private String equipId  		="";

	/** 공장  */
	private String plantId			= "";
	/** 공장명  */
	private String plantDesc		= "";
	/** 출고부서 id  */
	private String issueDeptId		= "";
	/** 출고부서 desc  */
	private String issueDeptDesc	= "";
	/** 출고자 id  */
	private String issueById		= "";
	/** 출고자 desc  */
	private String issueByDesc		= "";
	/** 수령자 id  */
	private String recById			= "";
	/** 수령자 desc  */
	private String recByDesc		= "";
	
	private String ptissTypeId		= "";
	private String UserPtissType	= "";
    
	/** 비고  */
	private String remark			= "";
	
	/** 제작사  */
	private String maker			= "";
	
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPtisslistNo()
    {
        return ptisslistNo;
    }
    public void setPtisslistNo(String ptisslistNo)
    {
        this.ptisslistNo = ptisslistNo;
    }
    public String getIssueDeptId()
    {
        return issueDeptId;
    }
    public void setIssueDeptId(String issueDeptId)
    {
        this.issueDeptId = issueDeptId;
    }
    public String getIssueDeptDesc()
    {
        return issueDeptDesc;
    }
    public void setIssueDeptDesc(String issueDeptDesc)
    {
        this.issueDeptDesc = issueDeptDesc;
    }
    public String getIssueById()
    {
        return issueById;
    }
    public void setIssueById(String issueById)
    {
        this.issueById = issueById;
    }
    public String getIssueByDesc()
    {
        return issueByDesc;
    }
    public void setIssueByDesc(String issueByDesc)
    {
        this.issueByDesc = issueByDesc;
    }
    public String getRecById()
    {
        return recById;
    }
    public void setRecById(String recById)
    {
        this.recById = recById;
    }
    public String getRecByDesc()
    {
        return recByDesc;
    }
    public void setRecByDesc(String recByDesc)
    {
        this.recByDesc = recByDesc;
    }
    public String getPtissTypeId() {
		return ptissTypeId;
	}
	public void setPtissTypeId(String ptissTypeId) {
		this.ptissTypeId = ptissTypeId;
	}
	public String getUserPtissType() {
		return UserPtissType;
	}
	public void setUserPtissType(String userPtissType) {
		UserPtissType = userPtissType;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getUom()
    {
        return uom;
    }
    public void setUom(String uom)
    {
        this.uom = uom;
    }
    public String getCostCenter()
    {
        return costCenter;
    }
    public void setCostCenter(String costCenter)
    {
        this.costCenter = costCenter;
    }
    /** 창고ID */
	private String wcodeId         = "";
	/** 창고명 */
	private String wname           = "";
	/** 자재Id */
	private String partId          = "";
	/** 자재번호 */
	private String partNo          = "";
	/** 자재 품명/규격 */
	private String partNameSize    = "";
	/** 규격 */
	private String ptSize          = "";
	/** 모델 */
	private String model           = "";
	/** 자재상태 */
	private String partGrade       = "";
	
	/** 자재상태명 */
	private String partGradeDesc   = "";
	/** 현재고 */
	private String stockQty        = "";
	/** 안전재고 */
	private String saftyQty        = "";
	/** 보관위치 */
	private String binNo           = "";
	
	
    public String getPtSize()
    {
        return ptSize;
    }
    public void setPtSize(String ptSize)
    {
        this.ptSize = ptSize;
    }
    public String getModel()
    {
        return model;
    }
    public void setModel(String model)
    {
        this.model = model;
    }
    public String getWhType()
    {
        return whType;
    }
    public void setWhType(String whType)
    {
        this.whType = whType;
    }
    public String getBudat()
    {
        return budat;
    }
    public void setBudat(String budat)
    {
        this.budat = budat;
    }
    public String getOutWcode()
    {
        return outWcode;
    }
    public void setOutWcode(String outWcode)
    {
        this.outWcode = outWcode;
    }
    public String getOutPlant()
    {
        return outPlant;
    }
    public void setOutPlant(String outPlant)
    {
        this.outPlant = outPlant;
    }
    public String getWoNo()
    {
        return woNo;
    }
    public void setWoNo(String woNo)
    {
        this.woNo = woNo;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = CommonUtil.convertDate(startDate);
    }
    public String getPtissStatusDesc()
    {
        return ptissStatusDesc;
    }
    public void setPtissStatusDesc(String ptissStatusDesc)
    {
        this.ptissStatusDesc = ptissStatusDesc;
    }
    public String getPtissTypeDesc()
    {
        return ptissTypeDesc;
    }
    public void setPtissTypeDesc(String ptissTypeDesc)
    {
        this.ptissTypeDesc = ptissTypeDesc;
    }
    public String getWoDesc()
    {
        return woDesc;
    }
    public void setWoDesc(String woDesc)
    {
        this.woDesc = woDesc;
    }
    public String getWoStatus()
    {
        return woStatus;
    }
    public void setWoStatus(String woStatus)
    {
        this.woStatus = woStatus;
    }
    public String getWoStatusDesc()
    {
        return woStatusDesc;
    }
    public void setWoStatusDesc(String woStatusDesc)
    {
        this.woStatusDesc = woStatusDesc;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getIssueDate()
    {
        return issueDate;
    }
    public void setIssueDate(String issueDate)
    {
        this.issueDate = CommonUtil.convertDate(issueDate);
    }
    public String getTotPrice()
    {
        return totPrice;
    }
    public void setTotPrice(String totPrice)
    {
        this.totPrice = totPrice;
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    public String getIssueQty()
    {
        return issueQty;
    }
    public void setIssueQty(String issueQty)
    {
        this.issueQty = issueQty;
    }
    public String getWkorId()
    {
        return wkorId;
    }
    public void setWkorId(String wkorId)
    {
        this.wkorId = wkorId;
    }
    public String getWopartId()
    {
        return wopartId;
    }
    public void setWopartId(String wopartId)
    {
        this.wopartId = wopartId;
    }
    public String getPtisslistId()
    {
        return ptisslistId;
    }
    public void setPtisslistId(String ptisslistId)
    {
        this.ptisslistId = ptisslistId;
        super.setAuditKey(ptisslistId);
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getPtissType()
    {
        return ptissType;
    }
    public void setPtissType(String ptissType)
    {
        this.ptissType = ptissType;
    }
    public String getErpYyyy()
    {
        return erpYyyy;
    }
    public void setErpYyyy(String erpYyyy)
    {
        this.erpYyyy = erpYyyy;
    }
    public String getErpIssNo()
    {
        return erpIssNo;
    }
    public void setErpIssNo(String erpIssNo)
    {
        this.erpIssNo = erpIssNo;
    }
    public String getRecBy()
    {
        return recBy;
    }
    public void setRecBy(String recBy)
    {
        this.recBy = recBy;
    }
    public String getIssueBy()
    {
        return issueBy;
    }
    public void setIssueBy(String issueBy)
    {
        this.issueBy = issueBy;
    }
    public String getIssueDept()
    {
        return issueDept;
    }
    public void setIssueDept(String issueDept)
    {
        this.issueDept = issueDept;
    }
    public String getPtissStatus()
    {
        return ptissStatus;
    }
    public void setPtissStatus(String ptissStatus)
    {
        this.ptissStatus = ptissStatus;
    }
    public String getPartGradeDesc()
    {
        return partGradeDesc;
    }
    public void setPartGradeDesc(String partGradeDesc)
    {
        this.partGradeDesc = partGradeDesc;
    }
    public String getWcodeId()
    {
        return wcodeId;
    }
    public void setWcodeId(String wcodeId)
    {
        this.wcodeId = wcodeId;
    }
    public String getWname()
    {
        return wname;
    }
    public void setWname(String wname)
    {
        this.wname = wname;
    }
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
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
    public String getPartGrade()
    {
        return partGrade;
    }
    public void setPartGrade(String partGrade)
    {
        this.partGrade = partGrade;
    }
    public String getStockQty()
    {
        return stockQty;
    }
    public void setStockQty(String stockQty)
    {
        this.stockQty = CommonUtil.convertMoney(stockQty);
    }
    public String getSaftyQty()
    {
        return saftyQty;
    }
    public void setSaftyQty(String saftyQty)
    {
        this.saftyQty = CommonUtil.convertMoney(saftyQty);
    }
    public String getBinNo()
    {
        return binNo;
    }
    public void setBinNo(String binNo)
    {
        this.binNo = binNo;
    }
    public String getIsSerial()
    {
        return isSerial;
    }
    public void setIsSerial(String isSerial)
    {
        this.isSerial = isSerial;
    }
    public String getSerialNo()
    {
        return serialNo;
    }
    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }
    
	
	
}
