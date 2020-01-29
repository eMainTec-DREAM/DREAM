package dream.asset.rpt.lcc.loc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장TOP(위치) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccLocCommonDTO extends BaseDTO
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
    
    private String filterUsageDept      = "";
    
    private String filterUsageDeptDesc  = "";
    
    
    public String getFilterUsageDept()
    {
        return filterUsageDept;
    }
    public void setFilterUsageDept(String filterUsageDept)
    {
        this.filterUsageDept = filterUsageDept;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
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
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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