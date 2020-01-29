package dream.mgr.message.dto;

import common.bean.BaseDTO;
/**
 * Message Transfer Page - 공통 DTO
 * @author syyang
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrMessageTransCommonDTO extends BaseDTO
{
	/** 메일링 ID */
	private String messageId			= "";
	
	/** Filter 전송구분 ID */
	private String filterMethodTypeID   	= "";
	/** Filter 전송구분 명 */
	private String filterMethodTypeDesc		= "";	
	/** Filter 수신자 */
	private String filterReceiversDesc  	= "";
	/** Filter 전송일자  From */
	private String filterStartDate	 		= "";
	/** Filter 전송일자 To */
	private String filterEndDate	 		= "";
	/** Filter 상태 ID */
	private String filterMsgStatusID	 	= "";
	/** Filter 상태 명 */
	private String filterMsgStatusDesc	 	= "";
	/** Filter 생성일자  From */
	private String filterCreStartDate	 	= "";
	/** Filter 생성일자 To */
	private String filterCreEndDate	 		= "";
	/** Filter 제목 */
	private String filterDescription  		= "";
	
	
	public String getFilterCreStartDate() {
		return filterCreStartDate;
	}
	public void setFilterCreStartDate(String filterCreStartDate) {
		this.filterCreStartDate = filterCreStartDate;
	}
	public String getFilterCreEndDate() {
		return filterCreEndDate;
	}
	public void setFilterCreEndDate(String filterCreEndDate) {
		this.filterCreEndDate = filterCreEndDate;
	}
	public String getFilterDescription() {
		return filterDescription;
	}
	public void setFilterDescription(String filterDescription) {
		this.filterDescription = filterDescription;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getFilterMethodTypeID() {
		return filterMethodTypeID;
	}
	public void setFilterMethodTypeID(String filterMethodTypeID) {
		this.filterMethodTypeID = filterMethodTypeID;
	}
	public String getFilterMethodTypeDesc() {
		return filterMethodTypeDesc;
	}
	public void setFilterMethodTypeDesc(String filterMethodTypeDesc) {
		this.filterMethodTypeDesc = filterMethodTypeDesc;
	}
	public String getFilterReceiversDesc() {
		return filterReceiversDesc;
	}
	public void setFilterReceiversDesc(String filterReceiversDesc) {
		this.filterReceiversDesc = filterReceiversDesc;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
	}
	public String getFilterMsgStatusID() {
		return filterMsgStatusID;
	}
	public void setFilterMsgStatusID(String filterMsgStatusID) {
		this.filterMsgStatusID = filterMsgStatusID;
	}
	public String getFilterMsgStatusDesc() {
		return filterMsgStatusDesc;
	}
	public void setFilterMsgStatusDesc(String filterMsgStatusDesc) {
		this.filterMsgStatusDesc = filterMsgStatusDesc;
	}
		
}
