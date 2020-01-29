package dream.mgr.message.dto;

import common.bean.BaseDTO;

/**
 * Message Transfer - Detail DTO
 * @author syyang
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrMessageTransDetailDTO extends BaseDTO
{
	/** 메일링 ID */
	private String messageId    	= "";
	/** 제목 */
	private String description	    = "";
	/** 수신자 */
	private String receiver 		= "";
	/** 전송방법 ID */
	private String methodTypeId		= "";
	/** 전송방법 명 */
	private String methodTypeDesc   = "";
	/** 전송시도 횟수 */
	private String retryCnt			= "";
	/** 메세지 전송상태 ID */
	private String msgStatusId     	= "";
	/** 메세지 전송상태 명 */
	private String msgStatusDesc    = "";
	/** 생성시간 */
    private String creTime          = "";
    /** 전송시간 */
    private String sendTime        	= "";
    /** 전송내용 */
	private String contents      	= "";
	/** 에러내용(Error발생시) */
	private String failMsg       	= "";
	/** 보내는 사람 사번 */
	private String sendEmpNo       	= "";
	/** 받는 사람 사번 */
	private String recEmpNo       	= "";
	/** 메시지전송유형 */
	private String msgObjType       = "";
	/** objectId */
	private String objectId	        = "";
	/** objectNo */
	private String objectNo	        = "";
	
	public String getSendEmpNo() {
		return sendEmpNo;
	}
	public void setSendEmpNo(String sendEmpNo) {
		this.sendEmpNo = sendEmpNo;
	}
	public String getRecEmpNo() {
		return recEmpNo;
	}
	public void setRecEmpNo(String recEmpNo) {
		this.recEmpNo = recEmpNo;
	}
	public String getMsgObjType() {
		return msgObjType;
	}
	public void setMsgObjType(String msgObjType) {
		this.msgObjType = msgObjType;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectNo() {
		return objectNo;
	}
	public void setObjectNo(String objectNo) {
		this.objectNo = objectNo;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMethodTypeId() {
		return methodTypeId;
	}
	public void setMethodTypeId(String methodTypeId) {
		this.methodTypeId = methodTypeId;
	}
	public String getMethodTypeDesc() {
		return methodTypeDesc;
	}
	public void setMethodTypeDesc(String methodTypeDesc) {
		this.methodTypeDesc = methodTypeDesc;
	}
	public String getRetryCnt() {
		return retryCnt;
	}
	public void setRetryCnt(String retryCnt) {
		this.retryCnt = retryCnt;
	}
	public String getMsgStatusId() {
		return msgStatusId;
	}
	public void setMsgStatusId(String msgStatusId) {
		this.msgStatusId = msgStatusId;
	}
	public String getMsgStatusDesc() {
		return msgStatusDesc;
	}
	public void setMsgStatusDesc(String msgStatusDesc) {
		this.msgStatusDesc = msgStatusDesc;
	}
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getFailMsg() {
		return failMsg;
	}
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
		
}
