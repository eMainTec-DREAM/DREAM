package dream.work.list.dto;

import common.bean.BaseDTO;

/**
 *  DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class MaWoResultSelectDTO extends BaseDTO
{
	private String selectedPmType  = "";
	private String selectedWoType = "";
	/** param2 */
	private String param2          = "";
	
	
	public String getSelectedWoType() {
		return selectedWoType;
	}

	public void setSelectedWoType(String selectedWoType) {
		this.selectedWoType = selectedWoType;
	}

	public String getParam2()
    {
        return param2;
    }

    public void setParam2(String param2)
    {
        this.param2 = param2;
    }

    public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}

	public String getSelectedPmType() {
		return selectedPmType;
	}
	
}
