package dream.note.daily.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 *  상세 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaWoDailyDetailDTO extends BaseDTO
{
	/** 일일작업 Id */
	private String woDayListId         = "";
	/** 일일작업 # */
    private String woDayListNo         = "";
    /** 일일작업 작성상태 */
    private String woDayListStatus     = "";
    /** 일일작업 작성상태 Desc */
    private String woDayListStatusDesc = "";
    /** 제목 */
    private String title               = "";
    /** 확인일자 */
    private String woDate              = "";
    /** 작업부서 Id */
    private String woDeptId            = "";
    /** 작업부서 Desc */
    private String woDeptDesc          = "";
    /** 작업시작일 */
    private String startFdate          = "";
    /** 작업시작시간 */
    private String startFtime          = "";
    /** 작업종료일 */
    private String startEdate          = "";
    /** 작업종료시간 */
    private String startEtime          = "";
	/** 작성자 Id */
    private String updById             = "";
    /** 작성자 Desc */
    private String updByDesc           = "";
    /** 공장 */
    private String plant               = "";
    /** 공장 Desc */
    private String plantDesc           = "";
    /** 작업그룹 Id */
    private String wkCtrId             = "";
    /** 작업그룹 Desc */
    private String wkCtrDesc           = "";
    /** 공장 */
    private String equipId             = "";
    /** 공장 Desc */
    private String equipDesc           = "";
    /** 비고 */
    private String remark              = "";
    /** 작업번호 */
    private String wkorId              = "";
    /** 예방점검번호 */
    private String pminslistId         = "";
    
    public String getWkorId() {
		return wkorId;
	}
	public String getPminslistId() {
		return pminslistId;
	}
	public void setPminslistId(String pminslistId) {
		this.pminslistId = pminslistId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getWkCtrId()
    {
        return wkCtrId;
    }
    public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public void setWkCtrId(String wkCtrId)
    {
        this.wkCtrId = wkCtrId;
    }
    public String getWkCtrDesc()
    {
        return wkCtrDesc;
    }
    public void setWkCtrDesc(String wkCtrDesc)
    {
        this.wkCtrDesc = wkCtrDesc;
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
    public String getWoDayListNo()
    {
        return woDayListNo;
    }
    public void setWoDayListNo(String woDayListNo)
    {
        this.woDayListNo = woDayListNo;
    }
    public String getWoDayListStatus()
    {
        return woDayListStatus;
    }
    public void setWoDayListStatus(String woDayListStatus)
    {
        this.woDayListStatus = woDayListStatus;
    }
    public String getWoDayListStatusDesc()
    {
        return woDayListStatusDesc;
    }
    public void setWoDayListStatusDesc(String woDayListStatusDesc)
    {
        this.woDayListStatusDesc = woDayListStatusDesc;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getWoDate()
    {
        return woDate;
    }
    public void setWoDate(String woDate)
    {
        this.woDate = CommonUtil.convertDate(woDate);
    }
    public String getWoDeptId()
    {
        return woDeptId;
    }
    public void setWoDeptId(String woDeptId)
    {
        this.woDeptId = woDeptId;
    }
    public String getWoDeptDesc()
    {
        return woDeptDesc;
    }
    public void setWoDeptDesc(String woDeptDesc)
    {
        this.woDeptDesc = woDeptDesc;
    }
    public String getStartFdate()
    {
        return startFdate;
    }
    public void setStartFdate(String startFdate)
    {
        this.startFdate = CommonUtil.convertDate(startFdate);
    }
    public String getStartFtime()
    {
        return startFtime;
    }
    public void setStartFtime(String startFtime)
    {
        this.startFtime = CommonUtil.convertTime(startFtime);
    }
    public String getStartEdate()
    {
        return startEdate;
    }
    public void setStartEdate(String startEdate)
    {
        this.startEdate = CommonUtil.convertDate(startEdate);
    }
    public String getStartEtime()
    {
        return startEtime;
    }
    public void setStartEtime(String startEtime)
    {
        this.startEtime = CommonUtil.convertTime(startEtime);
    }
    public String getUpdById()
    {
        return updById;
    }
    public void setUpdById(String updById)
    {
        this.updById = updById;
    }
    public String getUpdByDesc()
    {
        return updByDesc;
    }
    public void setUpdByDesc(String updByDesc)
    {
        this.updByDesc = updByDesc;
    }
    public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
}
