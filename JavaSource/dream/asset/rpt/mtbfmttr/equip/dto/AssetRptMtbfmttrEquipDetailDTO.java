package dream.asset.rpt.mtbfmttr.equip.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(설비) 상세 dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrEquipDetailDTO extends BaseDTO
{
    /** 설비 id */
    private String equipId    = "";
    /** 설비 desc */
    private String itemDesc    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
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