package dream.consult.comp.plant.dto;

import common.bean.BaseDTO;

/**
 * 회사설정 공통 DTO
 * @author  kim21017
 * @version $Id: MaPlantMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaPlantMngCommonDTO extends BaseDTO
{
	/** 공장설정 ID */
	private String plantNo 				= "";
	private String plantId 				= "";
	/** 필터 회사설정 No */
	private String filterCompNo 		= "";
	/** 필터 회사명 */
	private String filterCompDesc 		= "";
	/** 필터 회사설정 No */
	private String filterPlantNo 		= "";
	
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getFilterCompDesc() {
		return filterCompDesc;
	}
	public void setFilterCompDesc(String filterCompDesc) {
		this.filterCompDesc = filterCompDesc;
	}
	public String getPlantNo() {
		return plantNo;
	}
	public void setPlantNo(String plantNo) {
		this.plantNo = plantNo;
	}
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	public String getFilterPlantNo() {
		return filterPlantNo;
	}
	public void setFilterPlantNo(String filterPlantNo) {
		this.filterPlantNo = filterPlantNo;
	}

	
	
	
	
	
	
}
