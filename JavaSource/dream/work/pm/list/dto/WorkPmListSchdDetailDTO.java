package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾� ���� �� DTO
 * @author  jung7126
 * @version $Id: WorkPmListSchdDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class WorkPmListSchdDetailDTO extends BaseDTO
{
	/** �����۾�����ID */
	private String pmEventSchedId		= "";
	/** ����*/
	private String planDate				= "";
	public String getPmEventSchedId() {
		return pmEventSchedId;
	}
	public void setPmEventSchedId(String pmEventSchedId) {
		this.pmEventSchedId = pmEventSchedId;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = CommonUtil.convertDate(planDate);
	}
	
}