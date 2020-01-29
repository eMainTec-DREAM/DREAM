package dream.consult.comp.plant.dto;

import common.bean.BaseDTO;

/**
 * 회사설정 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaPlantMngDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaPlantMngDetailDTO extends BaseDTO
{

	/** 회사설정ID */
	private String compNo 			= "";
	private String compId 			= "";
	/** 회사설정ID */
	private String compDesc			= "";
	/** 공장설정ID */
	private String plantNo 			= "";
	/** 공장ID */
	private String plantId 			= "";
	/** 명명 */
	private String plantDesc 		= "";
	/** 사용여부 */
	private String isUse 			= "";
	/** 근무달력 ID */
	private String wrkCalListId		= "";
	/** 근무달력명 */
	private String wrkCalListDesc	= "";
	
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getWrkCalListId() {
		return wrkCalListId;
	}
	public void setWrkCalListId(String wrkCalListId) {
		this.wrkCalListId = wrkCalListId;
	}
	public String getWrkCalListDesc() {
		return wrkCalListDesc;
	}
	public void setWrkCalListDesc(String wrkCalListDesc) {
		this.wrkCalListDesc = wrkCalListDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCompDesc() {
		return compDesc;
	}
	public void setCompDesc(String compDesc) {
		this.compDesc = compDesc;
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
	

}
