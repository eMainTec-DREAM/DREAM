package dream.work.rpt.wopmwcmptrate.dto;

import common.bean.BaseDTO;

/**
 * �����۾� ��ȹ��� ���� ���� �� dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptWoPmwCmptDetailDTO extends BaseDTO
{
    /** ���� */
    private String plantId    = "";
    /** ���� desc */
    private String plantDesc    = "";
    /** �� */
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