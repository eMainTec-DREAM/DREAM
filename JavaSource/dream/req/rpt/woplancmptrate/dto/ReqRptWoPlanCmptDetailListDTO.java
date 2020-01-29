package dream.req.rpt.woplancmptrate.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾��Ƿ� �ʱ��ȹ ��û ��� - ���� DTO
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 *
 */
public class ReqRptWoPlanCmptDetailListDTO extends BaseDTO
{
	/** �� */ 
    private String month                    = "";
    /** ���� ID */ 
    private String plantId                  = "";
    /** ���� DEST */ 
    private String plantDesc                = "";
    
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
    public String getMonth()
    {
        return month;
    }
    public void setMonth(String month)
    {
        this.month = CommonUtil.convertDate(month);
    }
}
