package intf.dream.android.online.woreq.dto;

import common.bean.BaseDTO;

/**
 * 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class AnOnWoReqCommonDTO extends BaseDTO
{
	
	/** ID */
	private String hdeleAlarmListId        	= "";
	/** 이상점검응답 ID */
	private String hdeleAlarmReqId	= "";
	/** 작업요청 ID */
	private String woReqId			= "";
	/** 작업요청 명 */
	private String woReqDesc		= "";
	
	/** 회사코드 */
	private String compNo                  = "";

	public String getCompNo() {
		return compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getHdeleAlarmListId() {
		return hdeleAlarmListId;
	}
	public void setHdeleAlarmListId(String hdeleAlarmListId) {
		this.hdeleAlarmListId = hdeleAlarmListId;
	}
	public String getHdeleAlarmReqId() {
		return hdeleAlarmReqId;
	}
	public void setHdeleAlarmReqId(String hdeleAlarmReqId) {
		this.hdeleAlarmReqId = hdeleAlarmReqId;
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
}
