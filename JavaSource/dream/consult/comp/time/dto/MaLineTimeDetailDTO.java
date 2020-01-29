package dream.consult.comp.time.dto;

import common.bean.BaseDTO;

/**
 * 가동시간설정 - 상세
 * @author  kim21017
 * @version $Id: MaLineTimeDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaLineTimeDetailDTO extends BaseDTO
{
    /** 가동달력ID */
    private String lnWrkListId                  = "";
    /** 회사 */
    private String compNo               		= "";
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
	
    public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
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
	public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getCompDesc()
    {
        return compDesc;
    }
    public void setCompDesc(String compDesc)
    {
        this.compDesc = compDesc;
    }
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getLnWrkListNo()
    {
        return lnWrkListNo;
    }
    public void setLnWrkListNo(String lnWrkListNo)
    {
        this.lnWrkListNo = lnWrkListNo;
    }
    public String getLnWrkListDesc()
    {
        return lnWrkListDesc;
    }
    public void setLnWrkListDesc(String lnWrkListDesc)
    {
        this.lnWrkListDesc = lnWrkListDesc;
    }
    public String getWrkCalListId()
    {
        return wrkCalListId;
    }
    public void setWrkCalListId(String wrkCalListId)
    {
        this.wrkCalListId = wrkCalListId;
    }
    public String getWrkCalListDesc()
    {
        return wrkCalListDesc;
    }
    public void setWrkCalListDesc(String wrkCalListDesc)
    {
        this.wrkCalListDesc = wrkCalListDesc;
    }
    public String getIsUse()
    {
        return isUse;
    }
    public void setIsUse(String isUse)
    {
        this.isUse = isUse;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
	
}
