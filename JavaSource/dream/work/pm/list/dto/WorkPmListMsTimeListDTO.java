package dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * �۾��ð� ��� DTO
 * @author  js.lee
 * @version $Id: Exp $
 * @since   1.0
 */
public class WorkPmListMsTimeListDTO extends BaseDTO
{
	/** �۾��ð� ID */
	private String pmMsTimeId		= "";
    
	public String getPmMsTimeId() {
		return pmMsTimeId;
	}
	public void setPmMsTimeId(String pmMsTimeId) {
		this.pmMsTimeId = pmMsTimeId;
		super.setAuditKey(pmMsTimeId);
	}
    
}