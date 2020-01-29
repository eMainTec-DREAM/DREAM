package dream.mgr.msgrec.dto;

import common.bean.BaseDTO;

/**
 * 메시지 수신설정 List Page - 공통 DTO
 * @author youngjoo38 
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrMsgRecCommonDTO extends BaseDTO
{
	/** 메세지타입 회사설정 id (key) */
	private String msgCompSetId 			= "";
    /** Filter 메시지 전송유형 ID */
    private String filterMsgObjType 		= "";
    /** Filter 메시지 전송유형 DESC */
    private String filterMsgObjTypeDesc 	= "";
    /** Filter 메일 사용여부 ID */
    private String filterIsMailUse 			= "";
    /** Filter 메일 사용여부 DESC */
    private String filterIsMailUseDesc 		= "";
    /** Filter SMS 사용여부 ID */
    private String filterIsSmsUse 			= "";
    /** Filter SMS 사용여부 DESC */
    private String filterIsSmsUseDesc 		= "";
    /** Filter 사용여부 ID */
    private String filterIsUse 				= "";
    /** Filter 사용여부 DESC */
    private String filterIsUseDesc 			= "";
    
	public String getMsgCompSetId() {
		return msgCompSetId;
	}
	public void setMsgCompSetId(String msgCompSetId) {
		this.msgCompSetId = msgCompSetId;
	}
	public String getFilterMsgObjType() {
		return filterMsgObjType;
	}
	public void setFilterMsgObjType(String filterMsgObjType) {
		this.filterMsgObjType = filterMsgObjType;
	}
	public String getFilterMsgObjTypeDesc() {
		return filterMsgObjTypeDesc;
	}
	public void setFilterMsgObjTypeDesc(String filterMsgObjTypeDesc) {
		this.filterMsgObjTypeDesc = filterMsgObjTypeDesc;
	}
	public String getFilterIsMailUse() {
		return filterIsMailUse;
	}
	public void setFilterIsMailUse(String filterIsMailUse) {
		this.filterIsMailUse = filterIsMailUse;
	}
	public String getFilterIsMailUseDesc() {
		return filterIsMailUseDesc;
	}
	public void setFilterIsMailUseDesc(String filterIsMailUseDesc) {
		this.filterIsMailUseDesc = filterIsMailUseDesc;
	}
	public String getFilterIsSmsUse() {
		return filterIsSmsUse;
	}
	public void setFilterIsSmsUse(String filterIsSmsUse) {
		this.filterIsSmsUse = filterIsSmsUse;
	}
	public String getFilterIsSmsUseDesc() {
		return filterIsSmsUseDesc;
	}
	public void setFilterIsSmsUseDesc(String filterIsSmsUseDesc) {
		this.filterIsSmsUseDesc = filterIsSmsUseDesc;
	}
	public String getFilterIsUse() {
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) {
		this.filterIsUse = filterIsUse;
	}
	public String getFilterIsUseDesc() {
		return filterIsUseDesc;
	}
	public void setFilterIsUseDesc(String filterIsUseDesc) {
		this.filterIsUseDesc = filterIsUseDesc;
	}
}
