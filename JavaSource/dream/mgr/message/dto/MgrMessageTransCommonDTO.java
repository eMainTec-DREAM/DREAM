package dream.mgr.message.dto;

import common.bean.BaseDTO;
/**
 * Message Transfer Page - ���� DTO
 * @author syyang
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrMessageTransCommonDTO extends BaseDTO
{
	/** ���ϸ� ID */
	private String messageId			= "";
	
	/** Filter ���۱��� ID */
	private String filterMethodTypeID   	= "";
	/** Filter ���۱��� �� */
	private String filterMethodTypeDesc		= "";	
	/** Filter ������ */
	private String filterReceiversDesc  	= "";
	/** Filter ��������  From */
	private String filterStartDate	 		= "";
	/** Filter �������� To */
	private String filterEndDate	 		= "";
	/** Filter ���� ID */
	private String filterMsgStatusID	 	= "";
	/** Filter ���� �� */
	private String filterMsgStatusDesc	 	= "";
	/** Filter ��������  From */
	private String filterCreStartDate	 	= "";
	/** Filter �������� To */
	private String filterCreEndDate	 		= "";
	/** Filter ���� */
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
