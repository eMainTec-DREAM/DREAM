package dream.asset.rpt.mtbfmttr.usdept.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(���μ�) �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrUsDeptDetailDTO extends BaseDTO
{
    /** ���μ� ID */
    private String usageDeptId    	= "";
    /** ���μ� DESC */
    private String usageDeptDesc  	= "";
    /** ����� ID */
    private String plantId    		= "";
    /** ����� DESC */
    private String plantDesc  		= "";
    /** ������ */
    private String startDate    	= "";
    /** ������ */
    private String endDate    		= "";
    
    private String filterEqGradeDesc    = "";
    
    private String filterEqGrade        = "";
    
    
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
    }
    public String getFilterEqGrade()
    {
        return filterEqGrade;
    }
    public void setFilterEqGrade(String filterEqGrade)
    {
        this.filterEqGrade = filterEqGrade;
    }
    public String getUsageDeptId() {
		return usageDeptId;
	}
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
	public void setUsageDeptId(String usageDeptId) {
		this.usageDeptId = usageDeptId;
	}
	public String getUsageDeptDesc() {
		return usageDeptDesc;
	}
	public void setUsageDeptDesc(String usageDeptDesc) {
		this.usageDeptDesc = usageDeptDesc;
	}
	public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
}