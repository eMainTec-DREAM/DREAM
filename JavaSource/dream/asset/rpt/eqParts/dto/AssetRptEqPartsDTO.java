package dream.asset.rpt.eqParts.dto;

import common.bean.BaseDTO;

/**
 * 설비구성부품 - DTO
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptEqPartsDTO extends BaseDTO
{
	/** 설비번호 */
    private String itemNo          = "";
    /** 설비명 */
    private String equipDesc       = "";
    /** 부품번호 */
    private String partNo          = "";
    /** 부품명 */
    private String partDesc        = "";
    /** 설비종류ID */
    private String eqCtgId         = "";
    /** 설비종류명 */
    private String eqCtgDesc       = "";
    /** 공장코드 */
    private String plant           = "";
    /** 공장명 */
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
