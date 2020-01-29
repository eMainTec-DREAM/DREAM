package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 *  DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 */
public class MaEqCtgTypeSelectDTO extends BaseDTO
{
	private String selectedEqCtgType  = "";
	
	public String getSelectedEqCtgType() {
		return selectedEqCtgType;
	}
	public void setSelectedEqCtgType(String selectedEqCtgType) {
		this.selectedEqCtgType = selectedEqCtgType;
	}
	
}
