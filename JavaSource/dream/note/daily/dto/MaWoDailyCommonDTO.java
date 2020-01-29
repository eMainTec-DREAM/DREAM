package dream.note.daily.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaWoDailyCommonDTO extends BaseDTO
{
	/** 작업일자 From */
	private String startDate    = "";
	/** 작업일지 To */
    private String endDate      = "";
    /** 부서ID */
    private String deptId       = "";
    /** 부서명 */
    private String deptDesc     = "";
    /** 제목 */
    private String title       = "";
    /** 공장ID */
    private String plantId       = "";
    /** 공장명 */
    private String plantDesc     = "";
    /** 일일작업ID */
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
