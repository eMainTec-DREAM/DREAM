package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 보관위치 - List DTO
 * 
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 */
public class PartListSafQtyListDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId		= "";
    /** 창고 담당자 ID */
    private String partId		= "";
    /** 창고 담당자 ID */
    private String minSaftyQty		= "";
    /** 창고 담당자 ID */
    private String maxSaftyQty		= "";
    /** 창고명 */
    private String wname		= "";
    
    
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getMinSaftyQty() {
		return minSaftyQty;
	}
	public void setMinSaftyQty(String minSaftyQty) {
		this.minSaftyQty = minSaftyQty;
	}
	public String getMaxSaftyQty() {
		return maxSaftyQty;
	}
	public void setMaxSaftyQty(String maxSaftyQty) {
		this.maxSaftyQty = maxSaftyQty;
	}
    
    

}
