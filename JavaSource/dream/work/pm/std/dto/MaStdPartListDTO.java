package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준항목  리스트 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaStdPartListDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkPartId			= "";

	public String getStWrkPartId() {
		return stWrkPartId;
	}

	public void setStWrkPartId(String stWrkPartId) {
		this.stWrkPartId = stWrkPartId;
	}

	
	
	
}
