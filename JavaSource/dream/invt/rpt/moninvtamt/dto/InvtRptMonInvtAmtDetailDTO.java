package dream.invt.rpt.moninvtamt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * InvtRptMonInvtAmt Page - detail DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class InvtRptMonInvtAmtDetailDTO extends BaseDTO
{
    /**공장 ID */ 
    private String plantId              = "";
    /**공장 DESC */ 
    private String plantDesc            = "";
    /**사용부서 ID */ 
    private String deptId              = "";
    /**사용부서 DESC */ 
    private String deptDesc            = "";
    /**시작일자 */ 
    private String yyyy         		= "";
    /**invtListId */ 
    private String invtListId         		= "";


	/**선택된 차트 value */ 
    private String chartValue 			= "";
    
    
	public String getChartValue() {
		return chartValue;
	}
	public void setChartValue(String chartValue) {
		this.chartValue = chartValue;
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
    public String getYyyy()
    {
        return yyyy;
    }
    public void setYyyy(String yyyy)
    {
        this.yyyy = yyyy;
    }
    public String getInvtListId() {
		return invtListId;
	}
	public void setInvtListId(String invtListId) {
		this.invtListId = invtListId;
	}

}
