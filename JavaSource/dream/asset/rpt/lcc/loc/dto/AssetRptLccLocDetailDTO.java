package dream.asset.rpt.lcc.loc.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����TOP(��ġ) �� dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccLocDetailDTO extends BaseDTO
{
    /** ��ġ */
    private String eqLocId    = "";
    /** ��ġ desc */
    private String eqLocDesc    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    = "";
    /** ���õ� ��Ʈ value */
    private String chartValue   = "";
    /** ���� ID */
    private String plantId        	= "";
    /** ���� DESC */
    private String plantDesc      	= "";
    
    
    public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getChartValue()
    {
        return chartValue;
    }
    public void setChartValue(String chartValue)
    {
        this.chartValue = chartValue;
    }
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
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