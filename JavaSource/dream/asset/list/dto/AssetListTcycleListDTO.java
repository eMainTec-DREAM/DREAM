package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 교정주기 목록 dto
 * @author  kim21017
 * @version $Id: AssetListTcycleListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class AssetListTcycleListDTO extends BaseDTO
{
	/** 교정주기 id */
	private String eqPmCycleId 	= "";

	public String getEqPmCycleId() {
		return eqPmCycleId;
	}

	public void setEqPmCycleId(String eqPmCycleId) {
		this.eqPmCycleId = eqPmCycleId;
		super.setAuditKey(eqPmCycleId);
	}

	
}