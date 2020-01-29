package dream.asset.rpt.mtbfmttr.loc.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(��ġ) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrLocCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    = "";
    /** ����(��) */
    private String filterEndDate    = "";
    /** ������ġ */
    private String filterEqLocId    = "";
    /** ������ġ desc */
    private String filterEqLocDesc    = "";
    /** ������ġ level */
    private String filterEqLocLevel    = "";
    /** ������ġ level desc */
    private String filterEqLocLevelDesc    = "";
    /** ����-���� ID */
    private String filterPlantId            = "";
    /** ����-���� DESC */
    private String filterPlantDesc          = "";
    
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
    public String getFilterEqLocId()
    {
        return filterEqLocId;
    }
    public void setFilterEqLocId(String filterEqLocId)
    {
        this.filterEqLocId = filterEqLocId;
    }
    public String getFilterEqLocDesc()
    {
        return filterEqLocDesc;
    }
    public void setFilterEqLocDesc(String filterEqLocDesc)
    {
        this.filterEqLocDesc = filterEqLocDesc;
    }
    public String getFilterEqLocLevel()
    {
        return filterEqLocLevel;
    }
    public void setFilterEqLocLevel(String filterEqLocLevel)
    {
        this.filterEqLocLevel = filterEqLocLevel;
    }
    public String getFilterEqLocLevelDesc()
    {
        return filterEqLocLevelDesc;
    }
    public void setFilterEqLocLevelDesc(String filterEqLocLevelDesc)
    {
        this.filterEqLocLevelDesc = filterEqLocLevelDesc;
    }
    
}