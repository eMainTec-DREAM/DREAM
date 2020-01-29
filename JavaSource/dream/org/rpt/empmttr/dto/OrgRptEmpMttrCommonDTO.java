package dream.org.rpt.empmttr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * MTTR(�����) dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class OrgRptEmpMttrCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    = "";
    /** ����(��) */
    private String filterEndDate    = "";
    /** �μ� */
    private String filterDeptId    = "";
    /** �μ� desc */
    private String filterDeptDesc    = "";
    /** ���μ� */
    private String filterUsageDept      = "";
    /** ���μ��� */
    private String filterUsageDeptDesc  = "";
    /** ����� */
    private String filterPlantDesc      = "";
    /** ����ID */
    private String filterPlantId        = "";
    
    private String filterEqGrade        = "";
    
    private String filterEqGradeDesc    = "";
    

    public String getFilterEqGrade()
    {
        return filterEqGrade;
    }
    public void setFilterEqGrade(String filterEqGrade)
    {
        this.filterEqGrade = filterEqGrade;
    }
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterUsageDept()
    {
        return filterUsageDept;
    }
    public void setFilterUsageDept(String filterUsageDept)
    {
        this.filterUsageDept = filterUsageDept;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
	
}