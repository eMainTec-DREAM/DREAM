package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준항목  리스트 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaStdWoTypeListDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkWorkId			= "";

	public String getStWrkWorkId() {
		return stWrkWorkId;
	}

	public void setStWrkWorkId(String stWrkWorkId) {
		this.stWrkWorkId = stWrkWorkId;
	}
	
	
}
