package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * List - DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class AssetListITSWListDTO extends BaseDTO
{
    /**Key ��� ID */ 
    private String itEqId		= "";
    /**Key ��ġ SW ID */ 
    private String eqItInstSwId		= "";

	public String getItEqId() {
		return itEqId;
	}

	public void setItEqId(String itEqId) {
		this.itEqId = itEqId;
	}

	public String getEqItInstSwId() {
		return eqItInstSwId;
	}

	public void setEqItInstSwId(String eqItInstSwId) {
		this.eqItInstSwId = eqItInstSwId;
		super.setAuditKey(eqItInstSwId);
	}
}
