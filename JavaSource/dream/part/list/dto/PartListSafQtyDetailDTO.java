package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� ������ġ - Detail DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class PartListSafQtyDetailDTO extends BaseDTO
{
	/** â�� ID */
	private String wcodeId			= "";
	/** part ID */
	private String partId			= "";
    /** ��ǰ ������ġ ID */
    private String minSaftyQty		= "";
    /** ��ǰ ������ġ ID */
    private String maxSaftyQty		= "";
    /** â���� */
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
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}

    
	
}