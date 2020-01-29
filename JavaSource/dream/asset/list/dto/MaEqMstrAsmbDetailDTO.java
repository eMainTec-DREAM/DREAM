package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비부위 상세 dto
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrAsmbDetailDTO extends BaseDTO
{
	/** 부위 id */
	private String eqAsmbId			= "";
	/** 부위 명 */
	private String eqAsmbDesc		= "";
	/** 제작사 */
    private String maker       		= "";
    /** 모델 */
    private String modelNo       	= "";
    /** 구입금액 */
    private String buyAmt       	= "";
    /** 중요스펙 */
    private String spec       		= "";
    /** 설치일자 */
    private String setupDate       	= "";
	/** 유사설비공통부위 여부 */
	private String isEqTypeAsmb		= "";
	/** 사용여부 */
	private String isUse			= "";
	/** 순서 */
	private String ordNo			= "";

	
	/** 부위 NO */
	private String eqAsmbNo			= "";
	/** 상위 부위 ID */
	private String peqAsmbId		= "0";
	/** 상위 부위 DESC */
	private String peqAsmbDesc		= "";
	/** 부위설명 */
    private String remark          	= "";
	
    /** OLD 부위 id */
    private String oldEqAsmbId		= "";
    /** OLD 부위 NO */
    private String oldEqAsmbNo		= "";
    
    /** TAG번호 */
    private String tagNo			= "";
    
	/** 설비번호 */
	private String equipId			= "";
	/** 설비종류 id */
	private String eqCtgId		    = "";
	/** 설비종류별부위 id */
	private String eqCtgAsmbId		= "";
	/** 상위 설비종류별부위 id */
	private String peqCtgAsmbId		= "";
	/** 설비종류별부위 no */
	private String eqCtgAsmbNo		= "";
	/** AS업체정보 */
	private String asInfo			= "";
	/** full desc */
	private String fullDesc			= "";

	/** 보증기간 */
	private String guarantyDate		= "";
	/** Serial# */
	private String serialNo			= "";
	/** Size */
	private String eqSize			= "";
	/** 중량 */
	private String weight			= "";
	/** 부품ID */ 
	private String partId			= "";
	/** 부품번호 */
	private String partNo			= "";
	/** 부품명/규격 */
	private String partNameSize		= "";
	/** 생성일자 */
	private String creDate			= "";
	/** 생성자 */
	private String creById			= "";
	/** 생성자 */
	private String creBy			= "";
	/** 수정일자 */
	private String updDate			= "";
	/** 수정자 */
	private String modifyById		= "";
	/** 수정자 */
	private String modifyBy			= "";

	
	public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getCreById()
    {
        return creById;
    }
    public void setCreById(String creById)
    {
        this.creById = creById;
    }
    public String getModifyById()
    {
        return modifyById;
    }
    public void setModifyById(String modifyById)
    {
        this.modifyById = modifyById;
    }
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getFullDesc()
    {
        return fullDesc;
    }
    public void setFullDesc(String fullDesc)
    {
        this.fullDesc = fullDesc;
    }
    public String getEqCtgAsmbNo()
    {
        return eqCtgAsmbNo;
    }
    public String getGuarantyDate() {
		return guarantyDate;
	}
	public void setGuarantyDate(String guarantyDate) {
		this.guarantyDate = CommonUtil.convertDate(guarantyDate);
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getEqSize() {
		return eqSize;
	}
	public void setEqSize(String eqSize) {
		this.eqSize = eqSize;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartNameSize() {
		return partNameSize;
	}
	public void setPartNameSize(String partNameSize) {
		this.partNameSize = partNameSize;
	}

	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDateTime(creDate);
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDateTime(updDate);
	}
	public String getCreBy() {
		return creBy;
	}
	public void setCreBy(String creBy) {
		this.creBy = creBy;
	}

	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public void setEqCtgAsmbNo(String eqCtgAsmbNo)
    {
        this.eqCtgAsmbNo = eqCtgAsmbNo;
    }
    public String getPeqCtgAsmbId()
    {
        return peqCtgAsmbId;
    }
    public void setPeqCtgAsmbId(String peqCtgAsmbId)
    {
        this.peqCtgAsmbId = peqCtgAsmbId;
    }
    public String getAsInfo() {
		return asInfo;
	}
	public void setAsInfo(String asInfo) {
		this.asInfo = asInfo;
	}
	public String getEqCtgAsmbId() {
		return eqCtgAsmbId;
	}
	public void setEqCtgAsmbId(String eqCtgAsmbId) {
		this.eqCtgAsmbId = eqCtgAsmbId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getTagNo() {
		return tagNo;
	}
	public void setTagNo(String tagNo) {
		this.tagNo = tagNo;
	}
	public String getMaker()
    {
        return maker;
    }
	public String getOldEqAsmbId() {
		return oldEqAsmbId;
	}
	public void setOldEqAsmbId(String oldEqAsmbId) {
		this.oldEqAsmbId = oldEqAsmbId;
	}
	public String getOldEqAsmbNo() {
		return oldEqAsmbNo;
	}
	public void setOldEqAsmbNo(String oldEqAsmbNo) {
		this.oldEqAsmbNo = oldEqAsmbNo;
	}
	public void setMaker(String maker)
    {
        this.maker = maker;
    }
    public String getModelNo()
    {
        return modelNo;
    }
    public void setModelNo(String modelNo)
    {
        this.modelNo = modelNo;
    }
    public String getBuyAmt()
    {
        return buyAmt;
    }
    public void setBuyAmt(String buyAmt)
    {
        this.buyAmt = CommonUtil.convertMoney(buyAmt);
    }
    public String getSpec()
    {
        return spec;
    }
    public void setSpec(String spec)
    {
        this.spec = spec;
    }
    public String getSetupDate()
    {
        return setupDate;
    }
    public void setSetupDate(String setupDate)
    {
        this.setupDate = CommonUtil.convertDate(setupDate);
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getEqAsmbNo()
    {
        return eqAsmbNo;
    }
    public void setEqAsmbNo(String eqAsmbNo)
    {
        this.eqAsmbNo = eqAsmbNo;
    }
    public String getPeqAsmbId()
    {
        return peqAsmbId;
    }
    public void setPeqAsmbId(String peqAsmbId)
    {
        this.peqAsmbId = "".equals(peqAsmbId)?"0":peqAsmbId;
    }
    public String getPeqAsmbDesc()
    {
        return peqAsmbDesc;
    }
    public void setPeqAsmbDesc(String peqAsmbDesc)
    {
        this.peqAsmbDesc = peqAsmbDesc;
    }
    public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
		super.setAuditKey(eqAsmbId);
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}
	public String getIsEqTypeAsmb() {
		return isEqTypeAsmb;
	}
	public void setIsEqTypeAsmb(String isEqTypeAsmb) {
		this.isEqTypeAsmb = isEqTypeAsmb;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
}