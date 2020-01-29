package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * 설비 관련자산 목록 dto
 * @author  kim21017
 * @version $Id: MaEqMstrAssetListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrAssetListDTO extends BaseDTO
{
	/** 관련자산 id */
	private String eqAssetId 	= "";

	public String getEqAssetId() {
		return eqAssetId;
	}

	public void setEqAssetId(String eqAssetId) {
		this.eqAssetId = eqAssetId;
		super.setAuditKey(eqAssetId);
	}
	
}