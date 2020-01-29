package dream.work.rpt.basicunitanalysis.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 원단위분석 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class BasicUnitAnalysisDetailDTO extends BaseDTO
{
    /** 공장 ID */
    private String plantId    = "";
    /** 공장 DESC */
    private String plantDesc    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    = "";
    
    /** 항목DESC */
    private String stdCheckPointDesc    = "";
    
    public String getStdCheckPointDesc()
    {
        return stdCheckPointDesc;
    }
    public void setStdCheckPointDesc(String stdCheckPointDesc)
    {
        this.stdCheckPointDesc = stdCheckPointDesc;
    }
    public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = CommonUtil.convertDate(startDate);
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = CommonUtil.convertDate(endDate);
    }
    
}