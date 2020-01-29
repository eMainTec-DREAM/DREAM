package dream.asset.list.dto;

import common.bean.BaseDTO;
/**
 * LOV DTO
 * @author youngjoo38
 * @version $Id: LovEqToolAcListDTO.java,v 1.0 2015/12/02 09:12:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class LovEqToolAcListDTO extends BaseDTO
{
	/**   설비코드 ID */
	private String equipId			= "";
	/**   설비코드명 */
	private String equipDesc		= "";
	
	private String itemNo           = "";
	
	private String eqDesc           = "";
	
	private String toolNo           = "";
	
	private String toolDesc         = "";
	
	private String eqlocId          = "";
	
	private String eqlocDesc        = "";
	
	private String plant            = "";
	
	private String plantDesc        = "";

	
    public String getItemNo()
    {
        return itemNo;
    }
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    public String getEqDesc()
    {
        return eqDesc;
    }
    public void setEqDesc(String eqDesc)
    {
        this.eqDesc = eqDesc;
    }
    public String getToolNo()
    {
        return toolNo;
    }
    public void setToolNo(String toolNo)
    {
        this.toolNo = toolNo;
    }
    public String getToolDesc()
    {
        return toolDesc;
    }
    public void setToolDesc(String toolDesc)
    {
        this.toolDesc = toolDesc;
    }
    public String getEqlocId()
    {
        return eqlocId;
    }
    public void setEqlocId(String eqlocId)
    {
        this.eqlocId = eqlocId;
    }
    public String getEqlocDesc()
    {
        return eqlocDesc;
    }
    public void setEqlocDesc(String eqlocDesc)
    {
        this.eqlocDesc = eqlocDesc;
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
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
	
}
