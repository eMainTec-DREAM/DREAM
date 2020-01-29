package dream.pers.priv.notice.dto;

import common.bean.BaseDTO;

/**
 * Notice 설정 - 상세 Dto
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 */
public class PersPrivNoticeDetailDTO extends BaseDTO
{
    /** 알림 기능 설정 ID (Key) */
    private String alarmEmpSetId            = "";
    /** 알림코드 */
	private String alarmCode                = "";
	/** 알림 명 */
    private String alarmName                = "";
    /** 알림 여부 */
    private String isNotice                 = "";
    /** 알림 여부 Desc */
    private String isNoticeDesc             = "";
    
    public String getAlarmEmpSetId()
    {
        return alarmEmpSetId;
    }
    public void setAlarmEmpSetId(String alarmEmpSetId)
    {
        this.alarmEmpSetId = alarmEmpSetId;
    }
    public String getIsNotice()
    {
        return isNotice;
    }
    public void setIsNotice(String isNotice)
    {
        this.isNotice = isNotice;
    }
    public String getIsNoticeDesc()
    {
        return isNoticeDesc;
    }
    public void setIsNoticeDesc(String isNoticeDesc)
    {
        this.isNoticeDesc = isNoticeDesc;
    }
    public String getAlarmCode()
    {
        return alarmCode;
    }
    public void setAlarmCode(String alarmCode)
    {
        this.alarmCode = alarmCode;
    }
    public String getAlarmName()
    {
        return alarmName;
    }
    public void setAlarmName(String alarmName)
    {
        this.alarmName = alarmName;
    }
}