package dream.work.rpt.pmins.orgach.dto;

import common.bean.BaseDTO;

/**
 * 예방점검 이행율(조직) 상세 dto
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsOrgAchDetailDTO extends BaseDTO
{
    /** 부서 id */
    private String deptId    	= "";
    /** 부서 desc */
    private String deptDesc    	= "";
    /** 시작일 */
    private String startDate    = "";
    /** 종료일 */
    private String endDate    	= "";
    /** 공장 ID */
    private String plantId        = "";
    /** 공장 DESC */
    private String plantDesc      = "";
    
    public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
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
	
}