package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 작업시간 목록 DTO
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public class WorkPmListMsTimeListDTO extends BaseDTO
{
	/** 작업시간 ID */
	private String pmMsTimeId		= "";
    
	public String getPmMsTimeId() {
		return pmMsTimeId;
	}
	public void setPmMsTimeId(String pmMsTimeId) {
		this.pmMsTimeId = pmMsTimeId;
		super.setAuditKey(pmMsTimeId);
	}
    
}