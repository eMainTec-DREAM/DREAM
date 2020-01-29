package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 *  DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class MaPmMstrSelectDTO extends BaseDTO
{
	private String selectedWoType = "";
	private String selectedPmType  = "";
	/** param2 */
	private String param2          = "";
	
	
	public String getSelectedPmType() {
		return selectedPmType;
	}
	
	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}
	
	public String getSelectedWoType() {
		return selectedWoType;
	}
	
	public void setSelectedWoType(String selectedWoType) {
		this.selectedWoType = selectedWoType;
	}
	
	public String getParam2() {
		return param2;
	}
	
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	
	

}
