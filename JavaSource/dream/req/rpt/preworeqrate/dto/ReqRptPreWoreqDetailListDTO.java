package dream.req.rpt.preworeqrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업의뢰 시스템 요청 목록 - 공통 DTO
 * 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptPreWoreqDetailListDTO extends BaseDTO
{
	/** 월 */ 
    private String month            = "";
    /** 공장 ID */ 
    private String plantId          = "";
    /** 공장명 */ 
    private String plantDesc        = "";
    
    public String getMonth()
    {
        return month;
    }
    public void setMonth(String month)
    {
        this.month = month;
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
}
