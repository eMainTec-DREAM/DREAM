package dream.asset.loc.list.dto;

import common.bean.BaseDTO;

/**
 * ������ġ - ��
 * @author  kim21017
 * @version $Id: MaEqLocDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqLocDetailDTO extends BaseDTO
{
	/** ��ġ�з�ID */
	private String eqLocId 			= "";
	/** ��ġ�ڵ� */
	private String eqLocNo 			= "";
	/** ��ġ�� */
	private String eqLocDesc 		= "";
	/** ������ġ�з�ID */
	private String peqLocId 		= "";
	/** ������ġ�з��� */
	private String peqLocDesc 		= "";
	/** ��ġ�з����� */
	private String levelType		= "";
	/** ��ġ�з����и� */
	private String levelTypeDesc 	= "";
	/** ���� */
	private String level 			= "";
	/** ���� */
	private String ordNo 			= "";
	/** �����ð��� �����ϴ� ��ġ���� */
	private String isOperation 		= "";
	/** KPI��ǥ�� �����ϴ� ��ġ���� */
	private String isKpi 			= "";
	/** ��뿩�� */
	private String isUse 			= "";
	/** �����ڵ� */
	private String plant 			= "";
	/** ����� */
	private String plantDesc		= "";
	/** CP ID */
	private String ctCtrId 			= "";
	/** CP�� */
	private String ctCtrDesc		= "";
	/** Mes Line ID */
	private String mesLineId		= "";
	/** Mes Line �� */
	private String mesLineDesc		= "";
	/** ��� */
	private String remark			= "";
	/** 1����(�ְ���) */
	private String dayRunTime		= "";
	/** 2����(�߰���) */
	private String nightRunTime		= "";
	/** 3����(������) */
	private String extraRunTime		= "";
	/** ��ġ�� �ü��� ��ġ���� ����*/
	private String isBdLoc			= "";
	/** ��ġ�� �ü����� ��� �ü��� ����id*/
	private String bdEquipId		= "";
	/** �����ڵ�*/
	private String mngCd			= "";
	
	/** �����޷� ID */
	private String lnWrkListId		= "";
	/** �����޷¸� */
	private String lnWrkListDesc	= "";
	
	/** ���������� */
	private String eqCnt			= "";
	/** �۾��׷� ID */
    private String wkCtrId          = "";
    /** �۾��׷�� */
    private String wkCtrDesc        = "";
	
	
	
	public String getWkCtrId()
    {
        return wkCtrId;
    }
    public void setWkCtrId(String wkCtrId)
    {
        this.wkCtrId = wkCtrId;
    }
    public String getWkCtrDesc()
    {
        return wkCtrDesc;
    }
    public void setWkCtrDesc(String wkCtrDesc)
    {
        this.wkCtrDesc = wkCtrDesc;
    }
    public String getEqCnt() {
		return eqCnt;
	}
	public void setEqCnt(String eqCnt) {
		this.eqCnt = eqCnt;
	}
	public String getLnWrkListId() {
		return lnWrkListId;
	}
	public void setLnWrkListId(String lnWrkListId) {
		this.lnWrkListId = lnWrkListId;
	}
	public String getLnWrkListDesc() {
		return lnWrkListDesc;
	}
	public void setLnWrkListDesc(String lnWrkListDesc) {
		this.lnWrkListDesc = lnWrkListDesc;
	}
	public String getMngCd() {
		return mngCd;
	}
	public void setMngCd(String mngCd) {
		this.mngCd = mngCd;
	}
	public String getBdEquipId() {
		return bdEquipId;
	}
	public void setBdEquipId(String bdEquipId) {
		this.bdEquipId = bdEquipId;
	}
	public String getIsBdLoc() {
		return isBdLoc;
	}
	public void setIsBdLoc(String isBdLoc) {
		this.isBdLoc = isBdLoc;
	}
	public String getMesLineId() {
		return mesLineId;
	}
	public void setMesLineId(String mesLineId) {
		this.mesLineId = mesLineId;
	}
	public String getMesLineDesc() {
		return mesLineDesc;
	}
	public void setMesLineDesc(String mesLineDesc) {
		this.mesLineDesc = mesLineDesc;
	}
	public String getCtCtrId() {
		return ctCtrId;
	}
	public void setCtCtrId(String ctCtrId) {
		this.ctCtrId = ctCtrId;
	}
	public String getCtCtrDesc() {
		return ctCtrDesc;
	}
	public void setCtCtrDesc(String ctCtrDesc) {
		this.ctCtrDesc = ctCtrDesc;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
		super.setAuditKey(eqLocId);
	}
	public String getEqLocNo() {
		return eqLocNo;
	}
	public void setEqLocNo(String eqLocNo) {
		this.eqLocNo = eqLocNo;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getPeqLocId() {
		return peqLocId;
	}
	public void setPeqLocId(String peqLocId) {
		this.peqLocId = peqLocId;
	}
	public String getPeqLocDesc() {
		return peqLocDesc;
	}
	public void setPeqLocDesc(String peqLocDesc) {
		this.peqLocDesc = peqLocDesc;
	}
	public String getLevelType() {
		return levelType;
	}
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	public String getLevelTypeDesc() {
		return levelTypeDesc;
	}
	public void setLevelTypeDesc(String levelTypeDesc) {
		this.levelTypeDesc = levelTypeDesc;
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
	public String getIsOperation() {
		return isOperation;
	}
	public void setIsOperation(String isOperation) {
		this.isOperation = isOperation;
	}
	public String getIsKpi() {
		return isKpi;
	}
	public void setIsKpi(String isKpi) {
		this.isKpi = isKpi;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDayRunTime() {
		return dayRunTime;
	}
	public void setDayRunTime(String dayRunTime) {
		this.dayRunTime = dayRunTime;
	}
	public String getNightRunTime() {
		return nightRunTime;
	}
	public void setNightRunTime(String nightRunTime) {
		this.nightRunTime = nightRunTime;
	}
	public String getExtraRunTime() {
		return extraRunTime;
	}
	public void setExtraRunTime(String extraRunTime) {
		this.extraRunTime = extraRunTime;
	}
	
}
