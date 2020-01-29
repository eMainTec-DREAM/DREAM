package dream.note.daily.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 *  �� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaWoDailyDetailDTO extends BaseDTO
{
	/** �����۾� Id */
	private String woDayListId         = "";
	/** �����۾� # */
    private String woDayListNo         = "";
    /** �����۾� �ۼ����� */
    private String woDayListStatus     = "";
    /** �����۾� �ۼ����� Desc */
    private String woDayListStatusDesc = "";
    /** ���� */
    private String title               = "";
    /** Ȯ������ */
    private String woDate              = "";
    /** �۾��μ� Id */
    private String woDeptId            = "";
    /** �۾��μ� Desc */
    private String woDeptDesc          = "";
    /** �۾������� */
    private String startFdate          = "";
    /** �۾����۽ð� */
    private String startFtime          = "";
    /** �۾������� */
    private String startEdate          = "";
    /** �۾�����ð� */
    private String startEtime          = "";
	/** �ۼ��� Id */
    private String updById             = "";
    /** �ۼ��� Desc */
    private String updByDesc           = "";
    /** ���� */
    private String plant               = "";
    /** ���� Desc */
    private String plantDesc           = "";
    /** �۾��׷� Id */
    private String wkCtrId             = "";
    /** �۾��׷� Desc */
    private String wkCtrDesc           = "";
    /** ���� */
    private String equipId             = "";
    /** ���� Desc */
    private String equipDesc           = "";
    /** ��� */
    private String remark              = "";
    /** �۾���ȣ */
    private String wkorId              = "";
    /** �������˹�ȣ */
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
