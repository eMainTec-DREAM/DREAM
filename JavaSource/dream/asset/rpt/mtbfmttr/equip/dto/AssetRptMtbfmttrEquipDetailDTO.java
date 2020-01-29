package dream.asset.rpt.mtbfmttr.equip.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(����) �� dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrEquipDetailDTO extends BaseDTO
{
    /** ���� id */
    private String equipId    = "";
    /** ���� desc */
    private String itemDesc    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    = "";
    
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getItemDesc()
    {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
}