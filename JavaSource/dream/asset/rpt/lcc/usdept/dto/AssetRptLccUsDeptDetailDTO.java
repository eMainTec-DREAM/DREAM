package dream.asset.rpt.lcc.usdept.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장TOP(사용부서) 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccUsDeptDetailDTO extends BaseDTO
{
	/** 사용부서 ID */
	private String usageDeptId	   	= "";
	/** 사용부서 DESC */
	private String usageDeptDesc   	= "";
    /** 시작일 */
    private String startDate    	= "";
    /** 종료일 */
    private String endDate    		= "";
    /** 선택된 차트 value */
    private String chartValue   	= "";
    /** 공장 ID */
    private String plantId        	= "";
    /** 공장 DESC */
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