package dream.asset.rpt.eqParts.dto;

import common.bean.BaseDTO;

/**
 * ���񱸼���ǰ - DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptEqPartsDTO extends BaseDTO
{
	/** �����ȣ */
    private String itemNo          = "";
    /** ����� */
    private String equipDesc       = "";
    /** ��ǰ��ȣ */
    private String partNo          = "";
    /** ��ǰ�� */
    private String partDesc        = "";
    /** ��������ID */
    private String eqCtgId         = "";
    /** ���������� */
    private String eqCtgDesc       = "";
    /** �����ڵ� */
    private String plant           = "";
    /** ����� */
    private String plantDesc       = "";
    
    public String getItemNo()
    {
        return itemNo;
    }
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getPartDesc()
    {
        return partDesc;
    }
    public void setPartDesc(String partDesc)
    {
        this.partDesc = partDesc;
    }
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getEqCtgDesc()
    {
        return eqCtgDesc;
    }
    public void setEqCtgDesc(String eqCtgDesc)
    {
        this.eqCtgDesc = eqCtgDesc;
    }
    public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
}
