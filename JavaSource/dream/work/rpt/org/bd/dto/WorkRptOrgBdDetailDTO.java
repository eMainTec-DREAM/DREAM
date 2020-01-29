package dream.work.rpt.org.bd.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 조직별 고장분석 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptOrgBdDetailDTO extends BaseDTO
{
	/** KEY 부서 */
	private String deptId    	= "";

	/** 부서 desc */
    private String deptDesc    	= "";
    /**공장 ID */ 
    private String plantId		= "";
    /**공장 DESC */ 
    private String plantDesc    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    	= "";
    /** 선택된 차트 value */
    private String chartValue   = "";
    
    
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
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
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