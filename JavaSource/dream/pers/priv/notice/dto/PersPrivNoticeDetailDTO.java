package dream.pers.priv.notice.dto;

import common.bean.BaseDTO;

/**
 * Notice ���� - �� Dto
 * @author  nhkim8548
 * @version $Id$
 * @since   1.0
 */
public class PersPrivNoticeDetailDTO extends BaseDTO
{
    /** �˸� ��� ���� ID (Key) */
    private String alarmEmpSetId            = "";
    /** �˸��ڵ� */
	private String alarmCode                = "";
	/** �˸� �� */
    private String alarmName                = "";
    /** �˸� ���� */
    private String isNotice                 = "";
    /** �˸� ���� Desc */
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