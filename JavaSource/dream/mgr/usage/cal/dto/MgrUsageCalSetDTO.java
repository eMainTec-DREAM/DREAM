package dream.mgr.usage.cal.dto;

import common.bean.BaseDTO;

/**
 * ���޷¼��� ����
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * 
 */
public class MgrUsageCalSetDTO extends BaseDTO
{
	/** ���޷�ID */
	private String lnWrkListId 					= "";
	/** ȸ�� */
	private String compNo 		     			= "";
	/** ���� ȸ��� */
	private String filterCompDesc 				= "";
	/** ���� ���޷¸� */
	private String filterLnWrkListDesc         	= "";
    /** ȸ��� */
    private String compDesc              		= "";
	/** ��ġ�з�ID */
	private String eqLocId 						= "";
	/** ��ġ�� */
	private String eqLocDesc 					= "";
	/** ���޷�No */
    private String lnWrkListNo                  = "";
    /** ���޷¸� */
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
    public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
    }
    public String getFilterLnWrkListDesc()
    {
        return filterLnWrkListDesc;
    }
    public void setFilterLnWrkListDesc(String filterLnWrkListDesc)
    {
        this.filterLnWrkListDesc = filterLnWrkListDesc;
    }
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
