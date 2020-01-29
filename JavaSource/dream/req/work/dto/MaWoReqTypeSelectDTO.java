package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 *  DTO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public class MaWoReqTypeSelectDTO extends BaseDTO
{
	private String selectedWoReqType  = "";

	public String getSelectedWoReqType() {
		return selectedWoReqType;
	}

	public void setSelectedWoReqType(String selectedWoReqType) {
		this.selectedWoReqType = selectedWoReqType;
	}
	
}
