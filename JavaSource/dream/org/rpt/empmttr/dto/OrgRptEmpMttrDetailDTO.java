package dream.org.rpt.empmttr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * MTTR(�����) �� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class OrgRptEmpMttrDetailDTO extends BaseDTO
{
    /** ��� id */
    private String empId    = "";
    /** ��� desc */
    private String empDesc    = "";
    /** �μ� id */
    private String deptId    = "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    = "";
    /** ����ID */
    private String plantId      = "";
    /** ����� */
    private String plantDesc    = "";
    
    
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
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
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