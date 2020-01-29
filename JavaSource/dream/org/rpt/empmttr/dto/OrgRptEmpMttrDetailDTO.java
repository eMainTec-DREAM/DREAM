package dream.org.rpt.empmttr.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * MTTR(담당자) 상세 dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class OrgRptEmpMttrDetailDTO extends BaseDTO
{
    /** 사원 id */
    private String empId    = "";
    /** 사원 desc */
    private String empDesc    = "";
    /** 부서 id */
    private String deptId    = "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    = "";
    /** 공장ID */
    private String plantId      = "";
    /** 공장명 */
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