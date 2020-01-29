package dream.consult.program.page.dto;

import common.bean.BaseDTO;

/**
 * 화면별 필드 상세 dto
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 */
public class ConsultPgmPgLinkedFuncDetailDTO extends BaseDTO
{
	/** pgLinkedFunc ID */
	private String pgLinkedFuncId		= "";
	/** Page ID */
	private String pageId				= "";
	/** Linked Function ID */
	private String linkedFuncId			= "";
	/** Linked Function 명 */
	private String linkedFuncDesc		= "";
	/** Linked Function method */
	private String linkedFuncMethod		= "";
	/** 라벨 keyType */
	private String keyType				= "";
	/** 라벨 keyNo */
	private String keyNo				= "";
	/** 화면표시명 */
	private String keyName				= "";
	/** 사용 여부 ID */
	private String isUseId				= "";
	/** 사용 여부 DESC */
	private String isUseDesc			= "";
	/** 조회순서 */
	private String ordNo				= "";
	/** 비고 */
	private String remark           	= "";
	
	public String getPageId() {
		return pageId;
	}
	public String getLinkedFuncMethod() {
		return linkedFuncMethod;
	}
	public void setLinkedFuncMethod(String linkedFuncMethod) {
		this.linkedFuncMethod = linkedFuncMethod;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPgLinkedFuncId() {
		return pgLinkedFuncId;
	}
	public void setPgLinkedFuncId(String pgLinkedFuncId) {
		this.pgLinkedFuncId = pgLinkedFuncId;
	}
	public String getLinkedFuncId() {
		return linkedFuncId;
	}
	public void setLinkedFuncId(String linkedFuncId) {
		this.linkedFuncId = linkedFuncId;
	}
	public String getLinkedFuncDesc() {
		return linkedFuncDesc;
	}
	public void setLinkedFuncDesc(String linkedFuncDesc) {
		this.linkedFuncDesc = linkedFuncDesc;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
	public String getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}