package dream.asset.rpt.lcc.usdept.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����TOP(���μ�) �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccUsDeptDetailDTO extends BaseDTO
{
	/** ���μ� ID */
	private String usageDeptId	   	= "";
	/** ���μ� DESC */
	private String usageDeptDesc   	= "";
    /** ������ */
    private String startDate    	= "";
    /** ������ */
    private String endDate    		= "";
    /** ���õ� ��Ʈ value */
    private String chartValue   	= "";
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
	public String getUsageDeptId() {
		return usageDeptId;
	}
	public void setUsageDeptId(String usageDeptId) {
		this.usageDeptId = usageDeptId;
	}
	public String getUsageDeptDesc() {
		return usageDeptDesc;
	}
	public void setUsageDeptDesc(String usageDeptDesc) {
		this.usageDeptDesc = usageDeptDesc;
	}
	public String getChartValue()
    {
        return chartValue;
    }
    public void setChartValue(String chartValue)
    {
        this.chartValue = chartValue;
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