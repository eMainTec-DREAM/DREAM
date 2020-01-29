package dream.req.rpt.preworeqrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾��Ƿ� �ý��� ��û ��� - ���� DTO
 * 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 *
 */
public class ReqRptPreWoreqDetailListDTO extends BaseDTO
{
	/** �� */ 
    private String month            = "";
    /** ���� ID */ 
    private String plantId          = "";
    /** ����� */ 
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
