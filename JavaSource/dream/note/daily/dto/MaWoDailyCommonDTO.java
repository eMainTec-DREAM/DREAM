package dream.note.daily.dto;

import common.bean.BaseDTO;

/**
 * ���� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDailyCommonDTO extends BaseDTO
{
	/** �۾����� From */
	private String startDate    = "";
	/** �۾����� To */
    private String endDate      = "";
    /** �μ�ID */
    private String deptId       = "";
    /** �μ��� */
    private String deptDesc     = "";
    /** ���� */
    private String title       = "";
    /** ����ID */
    private String plantId       = "";
    /** ����� */
    private String plantDesc     = "";
    /** �����۾�ID */
    private String woDayListId  = "";
    
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
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
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
    public String getWoDayListId()
    {
        return woDayListId;
    }
    public void setWoDayListId(String woDayListId)
    {
        this.woDayListId = woDayListId;
        super.setAuditKey(woDayListId);
    }
    
}
