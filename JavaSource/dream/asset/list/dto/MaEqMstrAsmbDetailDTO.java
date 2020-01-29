package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� �� dto
 * @author  kim21017
 * @version $Id: MaEqMstrAsmbDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrAsmbDetailDTO extends BaseDTO
{
	/** ���� id */
	private String eqAsmbId			= "";
	/** ���� �� */
	private String eqAsmbDesc		= "";
	/** ���ۻ� */
    private String maker       		= "";
    /** �� */
    private String modelNo       	= "";
    /** ���Աݾ� */
    private String buyAmt       	= "";
    /** �߿佺�� */
    private String spec       		= "";
    /** ��ġ���� */
    private String setupDate       	= "";
	/** ���缳�������� ���� */
	private String isEqTypeAsmb		= "";
	/** ��뿩�� */
	private String isUse			= "";
	/** ���� */
	private String ordNo			= "";

	
	/** ���� NO */
	private String eqAsmbNo			= "";
	/** ���� ���� ID */
	private String peqAsmbId		= "0";
	/** ���� ���� DESC */
	private String peqAsmbDesc		= "";
	/** �������� */
    private String remark          	= "";
	
    /** OLD ���� id */
    private String oldEqAsmbId		= "";
    /** OLD ���� NO */
    private String oldEqAsmbNo		= "";
    
    /** TAG��ȣ */
    private String tagNo			= "";
    
	/** �����ȣ */
	private String equipId			= "";
	/** �������� id */
	private String eqCtgId		    = "";
	/** �������������� id */
	private String eqCtgAsmbId		= "";
	/** ���� �������������� id */
	private String peqCtgAsmbId		= "";
	/** �������������� no */
	private String eqCtgAsmbNo		= "";
	/** AS��ü���� */
	private String asInfo			= "";
	/** full desc */
	private String fullDesc			= "";

	/** �����Ⱓ */
	private String guarantyDate		= "";
	/** Serial# */
	private String serialNo			= "";
	/** Size */
	private String eqSize			= "";
	/** �߷� */
	private String weight			= "";
	/** ��ǰID */ 
	private String partId			= "";
	/** ��ǰ��ȣ */
	private String partNo			= "";
	/** ��ǰ��/�԰� */
	private String partNameSize		= "";
	/** �������� */
	private String creDate			= "";
	/** ������ */
	private String creById			= "";
	/** ������ */
	private String creBy			= "";
	/** �������� */
	private String updDate			= "";
	/** ������ */
	private String modifyById		= "";
	/** ������ */
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