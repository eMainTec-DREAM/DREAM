package dream.work.rpt.pmins.ach.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �������� ������(�����) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsAchCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    = "";
    /** ����(��) */
    private String filterEndDate    = "";
    /** �μ� */
    private String filterDeptId    = "";
    /** �μ� desc */
    private String filterDeptDesc    = "";
    
    /** �����id - ���� */
    private String filterEmpId          = "";
    /** ����ڸ� - ���� */
    private String filterEmpDesc        = "";
    /** ����-���� ID */
    private String filterPlantId            = "";
    /** ����-���� DESC */
    private String filterPlantDesc          = "";
    
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
    public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
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