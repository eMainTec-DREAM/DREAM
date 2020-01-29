package dream.asset.rpt.mtbfmttr.loc.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(위치) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrLocCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    = "";
    /** 일자(끝) */
    private String filterEndDate    = "";
    /** 설비위치 */
    private String filterEqLocId    = "";
    /** 설비위치 desc */
    private String filterEqLocDesc    = "";
    /** 설비위치 level */
    private String filterEqLocLevel    = "";
    /** 설비위치 level desc */
    private String filterEqLocLevelDesc    = "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
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