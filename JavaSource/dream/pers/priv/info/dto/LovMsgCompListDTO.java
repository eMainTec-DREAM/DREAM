package dream.pers.priv.info.dto;

import common.bean.BaseDTO;

/**
 * 메시지타입 Lov 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovMsgCompListDTO extends BaseDTO
{
    /** 메시지타입 ID */
	private String msgCompSetId 			= "";
	
	/** Filter 메시지 전송유형 ID */
    private String filterMsgObjType 	= "";
    /** Filter 메시지 전송유형 DESC */
    private String filterMsgObjTypeDesc = "";
    /** Filter 메일 사용여부 ID */
    private String filterIsMailUse 		= "";
    /** Filter 메일 사용여부 DESC */
    private String filterIsMailUseDesc 	= "";
    /** Filter SMS 사용여부 ID */
    private String filterIsSmsUse 		= "";
    /** Filter SMS 사용여부 DESC */
    private String filterIsSmsUseDesc 	= "";
    /** Filter 사용여부 ID */
    private String filterIsUse 			= "";
    /** Filter 사용여부 DESC */
    private String filterIsUseDesc 		= "";

    /** extCode1 */
    private String extCode1 			= "";
    /** extCode2 */
    private String extCode2 			= "";
    
    /** Multy Select Y */
    private String multiSelect    		= "";

	public String getmsgCompSetId() {
		return msgCompSetId;
	}

	public void setmsgCompSetId(String msgCompSetId) {
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

	public String getExtCode1() {
		return extCode1;
	}

	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}

	public String getExtCode2() {
		return extCode2;
	}

	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
}
