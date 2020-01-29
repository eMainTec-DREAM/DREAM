package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준점검항목 팝업 DTO
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class LovStdWrkWorkListDTO extends BaseDTO
{
    /** 작업종류 */
    private String stWrkId       = "";
    /** 작업종류 */
    private String woType       = "";
    
    
    public String getWoType() {
		return woType;
	}
	public void setWoType(String woType) {
		this.woType = woType;
	}
	public String getStWrkId() {
		return stWrkId;
	}
	public void setStWrkId(String stWrkId) {
		this.stWrkId = stWrkId;
	}

}
