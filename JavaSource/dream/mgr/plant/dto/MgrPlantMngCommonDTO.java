package dream.mgr.plant.dto;

import common.bean.BaseDTO;

/**
 * 공장설정 공통 DTO
 * @author  euna0207
 * @version $Id: MgrPlantMngCommonDTO.java,v 1.0 2015/12/02 09:13:08 euna0207 Exp $
 * @since   1.0
 *
 */
public class MgrPlantMngCommonDTO extends BaseDTO
{
	/** 공장설정 ID */
	private String plantNo 				= "";
	private String plantId 				= "";

	/** 필터 공장명 No */
	private String filterPlantDesc 		= "";
	
	private String plantIdValid			= "";
	private String plantNoValid			= "";
	private String plantDesc 			= "";
	
	public String getPlantDesc() {
		return plantDesc;
	}
	public String getPlantNoValid() {
		return plantNoValid;
	}
	public void setPlantNoValid(String plantNoValid) {
		this.plantNoValid = plantNoValid;
	}
	public String getPlantIdValid() {
		return plantIdValid;
	}
	public void setPlantIdValid(String plantIdValid) {
		this.plantIdValid = plantIdValid;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantNo() {
		return plantNo;
	}
	public void setPlantNo(String plantNo) {
		this.plantNo = plantNo;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}

	
	
	
	
	
	
}
