package dream.work.close.check.dto;

import common.bean.BaseDTO;

/**
 * 표준항목  리스트 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MgrWorkCloseCheckPointListDTO extends BaseDTO
{
	/** 점검Id */
	private String stWrkPointId			= "";
	
	public String getStWrkPointId() {
		return stWrkPointId;
	}
	public void setStWrkPointId(String stWrkPointId) {
		this.stWrkPointId = stWrkPointId;
	}
}
