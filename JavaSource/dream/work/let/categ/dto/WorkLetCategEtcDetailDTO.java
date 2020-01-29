package dream.work.let.categ.dto;

import common.bean.BaseDTO;

/**
 * 안전작업유형 - 보완사항 detail DTO - Detail DTO
 * @author euna0207
 * @version $Id: WorkLetCategPointDetailDTO.java,v 1.0 2015/12/02 09:12:40 euna0207 Exp $
 * @since 1.0
 *
 */
public class WorkLetCategEtcDetailDTO extends BaseDTO
{
	/** 안전작업 보완사항 id (key) */
	private String woLetCtgEtcId =		 "";
	
	/** 안전작업유형 id */
	private String woLetCtgId =			 "";

	/** 추가보완사항 */
	private String description = 		 "";
	
	/** 정렬값(조회순서) */
	private String ordNo = 			     "";
	
	/** 사용여부 id */
	private String isUseId =			 "";
	
	/** 사용여부 */
	private String isUseDesc =			 "";
	
	/** 비고 */
	private String remark = 			 "";

	public String getWoLetCtgEtcId() {
		return woLetCtgEtcId;
	}

	public void setWoLetCtgEtcId(String woLetCtgEtcId) {
		this.woLetCtgEtcId = woLetCtgEtcId;
		super.setAuditKey(woLetCtgEtcId);
	}

	public String getWoLetCtgId() {
		return woLetCtgId;
	}

	public void setWoLetCtgId(String woLetCtgId) {
		this.woLetCtgId = woLetCtgId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	
}
