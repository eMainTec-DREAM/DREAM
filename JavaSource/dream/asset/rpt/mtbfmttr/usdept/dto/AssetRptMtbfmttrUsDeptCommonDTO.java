package dream.asset.rpt.mtbfmttr.usdept.dto;

import common.bean.BaseDTO;

/**
 * MTBF,MTTR(���μ�) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptMtbfmttrUsDeptCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    	= "";
    /** ����(��) */
    private String filterEndDate    	= "";
    /** ����-���� ID */
    private String filterPlantId        = "";
    /** ����-���� DESC */
    private String filterPlantDesc      = "";
    /** ���μ� �ڵ� */
    private String filterUsageDeptId    = "";
    /** ���μ��� */
    private String filterUsageDeptDesc  = "";
    /** ���� */
    private String filterSeparation     = "";
    /** ���� desc */
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