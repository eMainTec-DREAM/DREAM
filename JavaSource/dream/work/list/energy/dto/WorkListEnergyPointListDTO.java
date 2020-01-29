package dream.work.list.energy.dto;

import common.bean.BaseDTO;

/**
 * ������ �� �����׸� ��� DTO
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointList.java,v 1.1 2015/12/04 09:10:45 sy.yang Exp $
 * @since   1.0
 */
public class WorkListEnergyPointListDTO extends BaseDTO
{
	/** �˻��׸� id */
	private String pmInsPointId 	= "";
	/** �˻��׸� id */
	private String pmPointId 		= "";

	public String getPmPointId() {
		return pmPointId;
	}
	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
	public String getPmInsPointId() {
		return pmInsPointId;
	}
	public void setPmInsPointId(String pmInsPointId) {
		this.pmInsPointId = pmInsPointId;
		super.setAuditKey(pmInsPointId);
	}

}