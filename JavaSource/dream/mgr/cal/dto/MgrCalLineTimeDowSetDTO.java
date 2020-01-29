package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * ���Ϻ� ���� ��� dto
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetDTO.java,v 1.1 2015/12/04 09:10:45 euna0207 Exp $
 * @since   1.0
 */
public class MgrCalLineTimeDowSetDTO extends BaseDTO
{
    /** ȸ��No */
    private String compNo               		= "";
	/** ���Ϻ� ���� id */
	private String eqLocDowRunId 				= "";
	/** �����޷�ID */
    private String lnWrkListId                  = "";
    /** ȸ��� */
    private String compDesc              		= "";
	/** ��ġ�з�ID */
	private String eqLocId 						= "";
	/** ��ġ�� */
	private String eqLocDesc 					= "";
	/** �����޷�No */
    private String lnWrkListNo                  = "";
    /** �����޷¸� */
    private String lnWrkListDesc                = "";
    /** �ٹ��޷�ID */
    private String wrkCalListId                 = "";
    /** �ٹ��޷¸� */
    private String wrkCalListDesc               = "";
	/** ��뿩�� */
	private String isUse            			= "";
	/** ��� */
	private String remark						= "";
	/** ���� ID */
	private String plantId						= "";
	/** ����� */
	private String plantDesc					= "";
	/** ����� ID*/
	private String equipNameId					= "";
	/** ����� */
	private String equipNameDesc				= "";
	/** �����ð� ������� ID */
	private String runTimeSettingId				= "";
	/** �����ð� ������� */
	private String runTimeSettingDesc			= "";
	/** �����ȣ */
	private String equipNo						= "";
	/** ���� */
	private String dow							= "";
	/** ���� ��*/
	private String dowDesc						= "";
	/** 1����(�ְ���) */
	private String dayRunTime					= "";
	/** 2����(�߰���) */
	private String nightRunTime					= "";
	/** 3����(������) */
	private String extraRunTime					= "";
	/** ��ȸ���� */
	private String ordNo						= "";
	/** ���Ƚ�� */
	private String ucnt							= "";
	
	public String getDowDesc() {
		return dowDesc;
	}

	public void setDowDesc(String dowDesc) {
		this.dowDesc = dowDesc;
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

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getUcnt() {
		return ucnt;
	}

	public void setUcnt(String ucnt) {
		this.ucnt = ucnt;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}

	public String getCompNo()
    {
        return compNo;
    }

    public String getLnWrkListId() {
		return lnWrkListId;
	}

	public void setLnWrkListId(String lnWrkListId) {
		this.lnWrkListId = lnWrkListId;
	}

	public String getCompDesc() {
		return compDesc;
	}

	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}

	public String getEqLocId() {
		return eqLocId;
	}

	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}

	public String getEqLocDesc() {
		return eqLocDesc;
	}

	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}

	public String getLnWrkListNo() {
		return lnWrkListNo;
	}

	public void setLnWrkListNo(String lnWrkListNo) {
		this.lnWrkListNo = lnWrkListNo;
	}

	public String getLnWrkListDesc() {
		return lnWrkListDesc;
	}

	public void setLnWrkListDesc(String lnWrkListDesc) {
		this.lnWrkListDesc = lnWrkListDesc;
	}

	public String getWrkCalListId() {
		return wrkCalListId;
	}

	public void setWrkCalListId(String wrkCalListId) {
		this.wrkCalListId = wrkCalListId;
	}

	public String getWrkCalListDesc() {
		return wrkCalListDesc;
	}

	public void setWrkCalListDesc(String wrkCalListDesc) {
		this.wrkCalListDesc = wrkCalListDesc;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getEquipNameId() {
		return equipNameId;
	}

	public void setEquipNameId(String equipNameId) {
		this.equipNameId = equipNameId;
	}

	public String getEquipNameDesc() {
		return equipNameDesc;
	}

	public void setEquipNameDesc(String equipNameDesc) {
		this.equipNameDesc = equipNameDesc;
	}

	public String getRunTimeSettingId() {
		return runTimeSettingId;
	}

	public void setRunTimeSettingId(String runTimeSettingId) {
		this.runTimeSettingId = runTimeSettingId;
	}

	public String getRunTimeSettingDesc() {
		return runTimeSettingDesc;
	}

	public void setRunTimeSettingDesc(String runTimeSettingDesc) {
		this.runTimeSettingDesc = runTimeSettingDesc;
	}

	public String getEquipNo() {
		return equipNo;
	}

	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}

	public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }

    public String getEqLocDowRunId() {
		return eqLocDowRunId;
	}

	public void setEqLocDowRunId(String eqLocDowRunId) {
		this.eqLocDowRunId = eqLocDowRunId;
	}
}