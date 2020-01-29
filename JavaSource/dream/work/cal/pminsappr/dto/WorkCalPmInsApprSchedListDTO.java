package dream.work.cal.pminsappr.dto;

import common.bean.BaseDTO;

/**
 * 예방점검계획승인-점검작업 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class WorkCalPmInsApprSchedListDTO extends BaseDTO
{
    /** 점검 ID */
    private String pmInsSchedId       = "";

	public String getPmInsSchedId() {
		return pmInsSchedId;
	}

	public void setPmInsSchedId(String pmInsSchedId) {
		this.pmInsSchedId = pmInsSchedId;
	}
    
}
