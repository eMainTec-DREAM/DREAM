package dream.asset.rpt.nyearpo.dto;

import common.bean.BaseDTO;

/**
 * N Year Spare Part dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptNYearPOCommonDTO extends BaseDTO
{
    /** Filter 거래처 ID */
    private String filterVendorId        = "";
    /** Filter 거래처 DESC */
    private String filterVendorDesc      = "";
    /** Filter PO#  */
    private String filterPoNumber       = "";
    /** Filter 운영기간(년)  */
    private String filterOpPeriod       = "";
    
	public String getFilterVendorId() {
		return filterVendorId;
	}
	public void setFilterVendorId(String filterVendorId) {
		this.filterVendorId = filterVendorId;
	}
	public String getFilterVendorDesc() {
		return filterVendorDesc;
	}
	public void setFilterVendorDesc(String filterVendorDesc) {
		this.filterVendorDesc = filterVendorDesc;
	}
	public String getFilterPoNumber() {
		return filterPoNumber;
	}
	public void setFilterPoNumber(String filterPoNumber) {
		this.filterPoNumber = filterPoNumber;
	}
	public String getFilterOpPeriod() {
		return filterOpPeriod;
	}
	public void setFilterOpPeriod(String filterOpPeriod) {
		this.filterOpPeriod = filterOpPeriod;
	}
}