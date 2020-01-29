package dream.mgr.msgrec.dto;

import common.bean.BaseDTO;

/**
 * MgrMsgRec Page - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrMsgRecDetailDTO extends BaseDTO
{
	/** �޼���Ÿ�� ȸ�缳�� id (key) */
	private String msgCompSetId 		= "";

    /** �޽��� �������� ID */
    private String msgObjType 			= "";
    /** �޽��� �������� DESC */
    private String msgObjTypeDesc 		= "";
    /** ���� ��뿩�� ID */
    private String isMailUse 			= "";
    /** ���� ��뿩�� DESC */
    private String isMailUseDesc 		= "";
    /** SMS ��뿩�� ID */
    private String isSmsUse 			= "";
    /** SMS ��뿩�� DESC */
    private String isSmsUseDesc 		= "";
    /** ��뿩�� ID */
    private String isUse 				= "";
    /** ��뿩�� DESC */
    private String isUseDesc 			= "";
    /** ��� */
    private String remark		 		= "";
    
	public String getMsgCompSetId() {
		return msgCompSetId;
	}
	public void setMsgCompSetId(String msgCompSetId) {
		this.msgCompSetId = msgCompSetId;
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
