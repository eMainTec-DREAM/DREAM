package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비의 정기점검-점검항목 목록 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsPointListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmInsPointListDTO extends BaseDTO
{
	private String pmId			= "";
	private String pmPointId	= "";
	
	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getPmPointId() {
		return pmPointId;
	}

	public void setPmPointId(String pmPointId) {
		this.pmPointId = pmPointId;
	}
}