package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비의 정기점검 목록 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmInsListDTO extends BaseDTO
{
	private String pmId 		= "";
	private String pmEquipId 	= "";
	
	private String selectedPmType = "";
	
	public String getSelectedPmType() {
		return selectedPmType;
	}

	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getPmEquipId() {
		return pmEquipId;
	}

	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
	}
}