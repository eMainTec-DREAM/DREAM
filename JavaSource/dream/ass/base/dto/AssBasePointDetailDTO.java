package dream.ass.base.dto;

import common.bean.BaseDTO;

/**
 * 평가항목 - Detail DTO
 * @author kim21017
 * @version $Id: AssBasePointDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBasePointDetailDTO extends BaseDTO
{
	/** 평가기준 ID */
	private String assBaseListId			= "";
	/** 평가항목 ID*/
	private String assBasePointId 			= "";
	/** 재평가기준 ID */
	private String reAssBaseId				= "";
	/** 재평가기준 명*/
	private String reAssBaseDesc 			= "";
	/** 구분 ID */
	private String assPointTypeId			= "";
	/** 구분 명 */
	private String assPointTypeDesc			= "";
	/** 항목 */
	private String assPoint					= "";
	/** 조회순서*/
	private String ordNo 					= "";
	/** 사용여부 ID */
	private String isUseId					= "";
	/** 사용여부 명 */
	private String isUseDesc				= "";
	/** 비고 */
	private String remark					= "";
	/** 비고 */
	private String reAssScript				= "";
	
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
	}
	public String getAssBasePointId() {
		return assBasePointId;
	}
	public String getReAssBaseId() {
		return reAssBaseId;
	}
	public void setReAssBaseId(String reAssBaseId) {
		this.reAssBaseId = reAssBaseId;
	}
	public String getReAssBaseDesc() {
		return reAssBaseDesc;
	}
	public void setReAssBaseDesc(String reAssBaseDesc) {
		this.reAssBaseDesc = reAssBaseDesc;
	}
	public void setAssBasePointId(String assBasePointId) {
		this.assBasePointId = assBasePointId;
		super.setAuditKey(assBasePointId);
	}
	public String getAssPointTypeId() {
		return assPointTypeId;
	}
	public void setAssPointTypeId(String assPointTypeId) {
		this.assPointTypeId = assPointTypeId;
	}
	public String getAssPointTypeDesc() {
		return assPointTypeDesc;
	}
	public void setAssPointTypeDesc(String assPointTypeDesc) {
		this.assPointTypeDesc = assPointTypeDesc;
	}
	public String getAssPoint() {
		return assPoint;
	}
	public void setAssPoint(String assPoint) {
		this.assPoint = assPoint;
	}
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getReAssScript() {
		return reAssScript;
	}
	public void setReAssScript(String reAssScript) {
		this.reAssScript = reAssScript;
	}
	
}
