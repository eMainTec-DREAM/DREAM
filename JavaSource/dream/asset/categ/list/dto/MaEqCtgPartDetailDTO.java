package dream.asset.categ.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비종류별 부품 상세 dto
 * @author  kim21017
 * @version $Id: MaEqCtgPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqCtgPartDetailDTO extends BaseDTO
{
	/** 설비종류별 부품 id */
	private String eqCtgPartId		= "";
	/** 자재 id*/
	private String partId			= "";
	/** 자재코드*/
	private String partNo			= "";
	/** 자재명 규격*/
	private String partNameSize		= "";
	/** 자재명*/
	private String partDesc			= "";
	/** 규격*/
	private String ptSize			= "";
	/** 모델*/
	private String model			= "";
	/** 사용수량*/
	private String useQty			= "";
	/** 작업부위id*/
	private String eqCtgAsmbId		= "";
	/** 작업부위명*/
	private String eqCtgAsmbDesc	= "";
	/** 조회순서 */
	private String ordNo			= "";
	/** 사용여부 */
	private String isUse			= "";
	/** 예상수명-년,월,주, 일 */
    private String periodType 		= "";
    private String periodTypeDesc   = "";
    
    /** 예상수명 확인방법 ID */
    private String scheduleTypeId	= "";
    /** 예상수명 확인방법 */
    private String scheduleTypeDesc	= "";
    /** 예상수명-사용량,생산량,가동시간 */
    private String usage	 		= "";
    /** 예상수명-기간 */
    private String cycle 			= "";
	
	/** (종류별)공통부위여부 */
    private String isEqTypePart 	= "";
    
    /** OLD 설비종류별 부품 id */
    private String oldEqCtgPartId	= "";
    /** 설비종류 id */
    private String eqCtgId      	= "";
	
    
	public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getPartNameSize()
    {
        return partNameSize;
    }
    public String getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(String scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}
	public void setScheduleTypeDesc(String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = CommonUtil.convertMoney(usage);
	}
	public void setPartNameSize(String partNameSize)
    {
        this.partNameSize = partNameSize;
    }
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
    public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getIsEqTypePart()
    {
        return isEqTypePart;
    }
    public String getOldEqCtgPartId() {
		return oldEqCtgPartId;
	}
	public void setOldEqCtgPartId(String oldEqCtgPartId) {
		this.oldEqCtgPartId = oldEqCtgPartId;
	}
	public void setIsEqTypePart(String isEqTypePart)
    {
        this.isEqTypePart = isEqTypePart;
    }
    public String getEqCtgPartId() {
		return eqCtgPartId;
	}
	public void setEqCtgPartId(String eqCtgPartId) {
		this.eqCtgPartId = eqCtgPartId;
		super.setAuditKey(eqCtgPartId);
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
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}
	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
	}
	public String getEqCtgAsmbDesc() {
		return eqCtgAsmbDesc;
	}
	public void setEqCtgAsmbDesc(String eqCtgAsmbDesc) {
		this.eqCtgAsmbDesc = eqCtgAsmbDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	
}