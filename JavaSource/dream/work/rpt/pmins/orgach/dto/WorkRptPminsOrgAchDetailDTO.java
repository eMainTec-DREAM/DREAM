package dream.work.rpt.pmins.orgach.dto;

import common.bean.BaseDTO;

/**
 * �������� ������(����) �� dto
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptPminsOrgAchDetailDTO extends BaseDTO
{
    /** �μ� id */
    private String deptId    	= "";
    /** �μ� desc */
    private String deptDesc    	= "";
    /** ������ */
    private String startDate    = "";
    /** ������ */
    private String endDate    	= "";
    /** ���� ID */
    private String plantId        = "";
    /** ���� DESC */
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