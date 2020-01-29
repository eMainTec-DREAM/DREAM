package dream.mgr.cal.dto;

import common.bean.BaseDTO;

/**
 * 근무일달력설정 - 상세 DTO
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDetailDTO.java,v 1.0 2015/12/02 08:44:16 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrCalCompWkrcalDetailDTO extends BaseDTO
{

	/** 회사설정ID */
	private String compNo 			= "";
	/** 고장설정ID */
	private String plantNo 			= "";
	/** 명명 */
	private String plantDesc 		= "";
	/** 회사명 */
	private String compDesc 		= "";
	/** 근무달력# */
	private String wrkcalListNo 	= "";
	/** 근무달력명 */
	private String wrkcalListDesc 	= "";
	/** 비고 */
	private String remark 			= "";
	/** 근무달력 ID */
	private String wrkcalListId 	= "";
	/** 사용여부 */
	private String isUse 			= "";
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getPlantNo() {
		return plantNo;
	}
	public void setPlantNo(String plantNo) {
		this.plantNo = plantNo;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
	}
	public String getWrkcalListNo() {
		return wrkcalListNo;
	}
	public void setWrkcalListNo(String wrkcalListNo) {
		this.wrkcalListNo = wrkcalListNo;
	}
	public String getWrkcalListDesc() {
		return wrkcalListDesc;
	}
	public void setWrkcalListDesc(String wrkcalListDesc) {
		this.wrkcalListDesc = wrkcalListDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWrkcalListId() {
		return wrkcalListId;
	}
	public void setWrkcalListId(String wrkcalListId) {
		this.wrkcalListId = wrkcalListId;
	}
}
