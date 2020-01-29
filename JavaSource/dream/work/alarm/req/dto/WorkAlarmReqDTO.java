package dream.work.alarm.req.dto;

import common.bean.BaseDTO;

/**
 * ������û ���� - ��� DTO 
 * @author  nhkim8548
 * @version $Id:$
 * @since   1.0
 */
public class WorkAlarmReqDTO extends BaseDTO
{
	/** Alarm ID */
	private String alarmListId        		= "";
	/** ID */
	private String alarmReqId        		= "";
	/** ������û ID */
	private String woReqId   	     		= "";
	/** ������û ID */
	private String woReqDesc   	     		= "";
	/** ������û STATUS */
	private String woreqCreType   	    	= "";
	
	public String getAlarmListId() {
		return alarmListId;
	}
	public void setAlarmListId(String alarmListId) {
		this.alarmListId = alarmListId;
	}
	public String getAlarmReqId() {
		return alarmReqId;
	}
	public void setAlarmReqId(String alarmReqId) {
		this.alarmReqId = alarmReqId;
	}
	public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getWoReqDesc() {
		return woReqDesc;
	}
	public void setWoReqDesc(String woReqDesc) {
		this.woReqDesc = woReqDesc;
	}
	public String getWoreqCreType() {
		return woreqCreType;
	}
	public void setWoreqCreType(String woreqCreType) {
		this.woreqCreType = woreqCreType;
	}
}
