package dream.work.cal.pminsappr.dto;

import common.bean.BaseDTO;

/**
 * �������˰�ȹ����-�����۾� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class WorkCalPmInsApprSchedListDTO extends BaseDTO
{
    /** ���� ID */
    private String pmInsSchedId       = "";

	public String getPmInsSchedId() {
		return pmInsSchedId;
	}

	public void setPmInsSchedId(String pmInsSchedId) {
		this.pmInsSchedId = pmInsSchedId;
	}
    
}
