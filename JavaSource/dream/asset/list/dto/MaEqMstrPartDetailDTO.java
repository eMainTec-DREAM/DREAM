package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비 구성자재 상세 dto
 * @author  kim21017
 * @version $Id: MaEqMstrPartDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPartDetailDTO extends BaseDTO
{
	/** 구성자재 id */
	private String eqPartId			= "";
	/** 자재ID */
	private String partId			= "";
	/** 자재코드 */
	private String partNo			= "";
	/** 자재명, 규격 */
	private String partDesc			= "";
	/** 자재명 */
	private String partName			= "";
	/** 규격 */
	private String ptSize			= "";
	/** 모델 */
	private String model			= "";
	/** 구성수량 */
	private String consistQty		= "";
	/** 부위id */
	private String eqAsmbId			= "";
	/** 부위명 */
	private String eqAsmbDesc		= "";
	/** 총출고수량 */
	private String useQty			= "";
	/** 출고횟수 */
	private String issueTime		= "";
	/** 최초출고일자 */
	private String issueFirstDate	= "";
	/** 최종출고일자 */
	private String issueLastDate	= "";
	/** 설비번호 */
	private String equipId			= "";
	/** 종류별작업부품id*/
    private String eqCtgPartId      = "";
    /** 작업부위id*/
    private String eqCtgAsmbId      = "";
    /** 작업부위명*/
    private String eqCtgAsmbDesc    = "";
    /** 조회순서 */
    private String ordNo            = "";
    /** 사용여부 */
    private String isUse            = "";
    /** (종류별)공통부위여부 */
    private String isEqTypePart     = "";
    /** OLD 구성자재 id */
    private String oldEqPartId		= "";
    /** 구성순번 */
    private String consistNbr		= "";
    /** 도면번호 */
    private String dwgNo		    = "";
    /** 도면Section번호 */
    private String dwgSectionNo		= "";
    
    public String getConsistNbr()
    {
        return consistNbr;
    }
    public void setConsistNbr(String consistNbr)
    {
        this.consistNbr = consistNbr;
    }
    public String getDwgNo()
    {
        return dwgNo;
    }
    public void setDwgNo(String dwgNo)
    {
        this.dwgNo = dwgNo;
    }
    public String getDwgSectionNo()
    {
        return dwgSectionNo;
    }
    public void setDwgSectionNo(String dwgSectionNo)
    {
        this.dwgSectionNo = dwgSectionNo;
    }
    public String getPartName()
    {
        return partName;
    }
    public void setPartName(String partName)
    {
        this.partName = partName;
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
	public String getEqCtgPartId()
    {
        return eqCtgPartId;
    }
    public void setEqCtgPartId(String eqCtgPartId)
    {
        this.eqCtgPartId = eqCtgPartId;
    }
    public String getIsEqTypePart()
    {
        return isEqTypePart;
    }
	public String getOldEqPartId() {
		return oldEqPartId;
	}
	public void setOldEqPartId(String oldEqPartId) {
		this.oldEqPartId = oldEqPartId;
	}
	public void setIsEqTypePart(String isEqTypePart)
    {
        this.isEqTypePart = isEqTypePart;
    }
    public String getEqCtgAsmbId()
    {
        return eqCtgAsmbId;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public void setEqCtgAsmbId(String eqCtgAsmbId)
    {
        this.eqCtgAsmbId = eqCtgAsmbId;
    }
    public String getEqCtgAsmbDesc()
    {
        return eqCtgAsmbDesc;
    }
    public void setEqCtgAsmbDesc(String eqCtgAsmbDesc)
    {
        this.eqCtgAsmbDesc = eqCtgAsmbDesc;
    }
    public String getOrdNo()
    {
        return ordNo;
    }
    public void setOrdNo(String ordNo)
    {
        this.ordNo = ordNo;
    }
    public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEqPartId() {
		return eqPartId;
	}
	public void setEqPartId(String eqPartId) {
		this.eqPartId = eqPartId;
		super.setAuditKey(eqPartId);
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
		super.setAuditKey(partId);
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
	public String getConsistQty() {
		return consistQty;
	}
	public void setConsistQty(String consistQty) {
		this.consistQty = CommonUtil.convertMoney(consistQty);
	}
	public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = CommonUtil.convertMoney(useQty);
	}
	public String getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(String issueTime) {
		this.issueTime = CommonUtil.convertMoney(issueTime);
	}
	public String getIssueFirstDate() {
		return issueFirstDate;
	}
	public void setIssueFirstDate(String issueFirstDate) {
		this.issueFirstDate = CommonUtil.convertDate(issueFirstDate);
	}
	public String getIssueLastDate() {
		return issueLastDate;
	}
	public void setIssueLastDate(String issueLastDate) {
		this.issueLastDate = CommonUtil.convertDate(issueLastDate);
	}
}