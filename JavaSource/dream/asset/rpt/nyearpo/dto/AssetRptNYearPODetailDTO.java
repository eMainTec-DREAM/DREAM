package dream.asset.rpt.nyearpo.dto;

import common.bean.BaseDTO;

/**
 * N Year Spare Part »ó¼¼ dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptNYearPODetailDTO extends BaseDTO
{
	/** PO# */
	private String venPoNo	   	= "";

	public String getVenPoNo() {
		return venPoNo;
	}

	public void setVenPoNo(String venPoNo) {
		this.venPoNo = venPoNo;
	}
}