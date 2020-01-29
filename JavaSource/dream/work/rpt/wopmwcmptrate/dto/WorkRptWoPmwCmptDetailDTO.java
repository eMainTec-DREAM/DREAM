package dream.work.rpt.wopmwcmptrate.dto;

import common.bean.BaseDTO;

/**
 * 예방작업 계획대비 실행 비율 상세 dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptWoPmwCmptDetailDTO extends BaseDTO
{
    /** 공장 */
    private String plantId    = "";
    /** 공장 desc */
    private String plantDesc    = "";
    /** 월 */
    private String yyyymm    = "";
    
    
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
    public String getYyyymm()
    {
        return yyyymm;
    }
    public void setYyyymm(String yyyymm)
    {
        this.yyyymm = yyyymm;
    }
    
    
}