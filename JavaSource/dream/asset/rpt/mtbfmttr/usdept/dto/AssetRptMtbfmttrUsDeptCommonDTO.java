package dream.asset.rpt.mtbfmttr.usdept.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(사용부서) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrUsDeptCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    	= "";
    /** 일자(끝) */
    private String filterEndDate    	= "";
    /** 필터-공장 ID */
    private String filterPlantId        = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc      = "";
    /** 사용부서 코드 */
    private String filterUsageDeptId    = "";
    /** 사용부서명 */
    private String filterUsageDeptDesc  = "";
    /** 구분 */
    private String filterSeparation     = "";
    /** 구분 desc */
    private String filterSeparationDesc = "";
    
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
    public String getFilterSeparation()
    {
        return filterSeparation;
    }
    public void setFilterSeparation(String filterSeparation)
    {
        this.filterSeparation = filterSeparation;
    }
    public String getFilterSeparationDesc()
    {
        return filterSeparationDesc;
    }
    public void setFilterSeparationDesc(String filterSeparationDesc)
    {
        this.filterSeparationDesc = filterSeparationDesc;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
    }
	public String getFilterUsageDeptId() {
		return filterUsageDeptId;
	}
	public void setFilterUsageDeptId(String filterUsageDeptId) {
		this.filterUsageDeptId = filterUsageDeptId;
	}
}