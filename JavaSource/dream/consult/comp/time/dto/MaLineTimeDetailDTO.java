package dream.consult.comp.time.dto;

import common.bean.BaseDTO;

/**
 * �����ð����� - ��
 * @author  kim21017
 * @version $Id: MaLineTimeDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaLineTimeDetailDTO extends BaseDTO
{
    /** �����޷�ID */
    private String lnWrkListId                  = "";
    /** ȸ�� */
    private String compNo               		= "";
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
