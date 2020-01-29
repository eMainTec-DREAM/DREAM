package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * 요일별 설정 목록 dto
 * @author  euna0207
 * @version $Id: MgrCalLineTimeDowSetDTO.java,v 1.1 2015/12/04 09:10:45 euna0207 Exp $
 * @since   1.0
 */
public class MgrCalLineTimeDowSetDTO extends BaseDTO
{
    /** 회사No */
    private String compNo               		= "";
	/** 요일별 설정 id */
	private String eqLocDowRunId 				= "";
	/** 가동달력ID */
    private String lnWrkListId                  = "";
    /** 회사명 */
    private String compDesc              		= "";
	/** 위치분류ID */
	private String eqLocId 						= "";
	/** 위치명 */
	private String eqLocDesc 					= "";
	/** 가동달력No */
    private String lnWrkListNo                  = "";
    /** 가동달력명 */
    private String lnWrkListDesc                = "";
    /** 근무달력ID */
    private String wrkCalListId                 = "";
    /** 근무달력명 */
    private String wrkCalListDesc               = "";
	/** 사용여부 */
	private String isUse            			= "";
	/** 비고 */
	private String remark						= "";
	/** 공장 ID */
	private String plantId						= "";
	/** 공장명 */
	private String plantDesc					= "";
	/** 설비명 ID*/
	private String equipNameId					= "";
	/** 설비명 */
	private String equipNameDesc				= "";
	/** 가동시간 설정방법 ID */
	private String runTimeSettingId				= "";
	/** 가동시간 설정방법 */
	private String runTimeSettingDesc			= "";
	/** 설비번호 */
	private String equipNo						= "";
	/** 요일 */
	private String dow							= "";
	/** 요일 명*/
	private String dowDesc						= "";
	/** 1교대(주간조) */
	private String dayRunTime					= "";
	/** 2교대(야간조) */
	private String nightRunTime					= "";
	/** 3교대(새벽조) */
	private String extraRunTime					= "";
	/** 조회순서 */
	private String ordNo						= "";
	/** 사용횟수 */
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