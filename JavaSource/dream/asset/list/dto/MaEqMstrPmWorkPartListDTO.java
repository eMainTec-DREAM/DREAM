package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비의 정기작업-교체부품 목록 DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkPartListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmWorkPartListDTO extends BaseDTO
{
	private String pmId			= "";
	private String pmPartId		= "";
	
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmPartId() {
		return pmPartId;
	}
	public void setPmPartId(String pmPartId) {
		this.pmPartId = pmPartId;
	}
	
	
}