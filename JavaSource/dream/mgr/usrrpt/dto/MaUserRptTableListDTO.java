package dream.mgr.usrrpt.dto;

import common.bean.BaseDTO;

/**
 * ¸ñ·Ï dto
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class MaUserRptTableListDTO extends BaseDTO
{
	/** key ID */
	private String usrrptlistId 		= "";

	public String getUsrrptlistId() {
		return usrrptlistId;
	}

	public void setUsrrptlistId(String usrrptlistId) {
		this.usrrptlistId = usrrptlistId;
	}
}