package dream.consult.program.msg.dto;

import common.bean.BaseDTO;

/**
 * 메시지 설정(메일, SMS) - 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class ConsultPgmMsgDetailDTO extends BaseDTO
{
	/** 메세지타입 id (key) */
	private String msgCategId 			= "";
	
    /** 메시지 전송유형 ID */
    private String msgObjType 			= "";
    /** 메시지 전송유형 DESC */
    private String msgObjTypeDesc 		= "";
    /** 메일 사용여부 ID */
    private String isMailUse 			= "";
    /** 메일 사용여부 DESC */
    private String isMailUseDesc 		= "";
    /** SMS 사용여부 ID */
    private String isSmsUse 			= "";
    /** SMS 사용여부 DESC */
    private String isSmsUseDesc 		= "";
    /** 사용여부 ID */
    private String isUse 				= "";
    /** 사용여부 DESC */
    private String isUseDesc 			= "";
    /** 비고 */
    private String remark		 		= "";
    
	public String getMsgCategId() {
		return msgCategId;
	}
	public void setMsgCategId(String msgCategId) {
		this.msgCategId = msgCategId;
	}
	public String getMsgObjType() {
		return msgObjType;
	}
	public void setMsgObjType(String msgObjType) {
		this.msgObjType = msgObjType;
	}
	public String getMsgObjTypeDesc() {
		return msgObjTypeDesc;
	}
	public void setMsgObjTypeDesc(String msgObjTypeDesc) {
		this.msgObjTypeDesc = msgObjTypeDesc;
	}
	public String getIsMailUse() {
		return isMailUse;
	}
	public void setIsMailUse(String isMailUse) {
		this.isMailUse = isMailUse;
	}
	public String getIsMailUseDesc() {
		return isMailUseDesc;
	}
	public void setIsMailUseDesc(String isMailUseDesc) {
		this.isMailUseDesc = isMailUseDesc;
	}
	public String getIsSmsUse() {
		return isSmsUse;
	}
	public void setIsSmsUse(String isSmsUse) {
		this.isSmsUse = isSmsUse;
	}
	public String getIsSmsUseDesc() {
		return isSmsUseDesc;
	}
	public void setIsSmsUseDesc(String isSmsUseDesc) {
		this.isSmsUseDesc = isSmsUseDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
