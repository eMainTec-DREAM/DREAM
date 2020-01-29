package dream.work.list.energy.dto;

import common.bean.BaseDTO;

/**
 * 에너지 값 측정항목 목록 DTO
 * @author  sy.yang
 * @version $Id: WorkListEnergyPointList.java,v 1.1 2015/12/04 09:10:45 sy.yang Exp $
 * @since   1.0
 */
public class WorkListEnergyPointListDTO extends BaseDTO
{
	/** 검사항목 id */
	private String pmInsPointId 	= "";
	/** 검사항목 id */
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