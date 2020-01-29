package dream.part.rpt.orgptusehist.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * PartRptOrgPtUseHist Page - detail DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartRptOrgPtUseHistDetailDTO extends BaseDTO
{
    /**공장 ID */ 
    private String plantId              = "";
    /**공장 DESC */ 
    private String plantDesc            = "";
    /**부서 ID */ 
    private String deptId               = "";
    /**부서 DESC */ 
    private String deptDesc             = "";
    /**시작일자 */ 
    private String startYyyymm          = "";
    /**종료일자 */ 
    private String endYyyymm            = "";

    /**선택된 차트 value */ 
    private String chartValue 			= "";
    
    
	public String getChartValue() {
		return chartValue;
	}
	public void setChartValue(String chartValue) {
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
    public String getStartYyyymm()
    {
        return startYyyymm;
    }
    public void setStartYyyymm(String startYyyymm)
    {
        this.startYyyymm = startYyyymm;
    }
    public String getEndYyyymm()
    {
        return endYyyymm;
    }
    public void setEndYyyymm(String endYyyymm)
    {
        this.endYyyymm = endYyyymm;
    }
    
}
