package dream.ass.base.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비등급 평가기준 - Detail DTO
 * @author kim21017
 * @version $Id: AssBaseDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class AssBaseDetailDTO extends BaseDTO
{
	/**평가기준 리스트 ID*/
	private String assBaseListId 		= "";
	/**평가기준 리스트 NO*/
	private String assBaseListNo 		= "";
	/**평가기준 리스트 명*/
	private String assBaseListDesc 		= "";
	/**설비종류 ID*/
	private String eqCtgId				= "";
	/**설비종류 ID*/
	private String eqCtgDesc			= "";
	/**사용여부 ID*/
	private String isUseId 				= "";
	/**사용여부 */
	private String isUseDesc			= "";
	/**등록일 */
	private String regDate				= "";
	/**비고 */
	private String remark				= "";
	
	public String getAssBaseListId() {
		return assBaseListId;
	}
	public void setAssBaseListId(String assBaseListId) {
		this.assBaseListId = assBaseListId;
		super.setAuditKey(assBaseListId);
	}
	public String getAssBaseListNo() {
		return assBaseListNo;
	}
	public void setAssBaseListNo(String assBaseListNo) {
		this.assBaseListNo = assBaseListNo;
	}
	public String getAssBaseListDesc() {
		return assBaseListDesc;
	}
	public void setAssBaseListDesc(String assBaseListDesc) {
		this.assBaseListDesc = assBaseListDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
