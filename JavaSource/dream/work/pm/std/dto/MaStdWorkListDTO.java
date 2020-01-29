package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준항목  리스트 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaStdWorkListDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkWorkPrcId			= "";

	public String getStWrkWorkPrcId() {
		return stWrkWorkPrcId;
	}

	public void setStWrkWorkPrcId(String stWrkWorkPrcId) {
		this.stWrkWorkPrcId = stWrkWorkPrcId;
	}

	
	
	
}
