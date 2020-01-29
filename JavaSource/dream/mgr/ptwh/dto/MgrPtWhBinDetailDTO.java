package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� ������ġ - Detail DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrPtWhBinDetailDTO extends BaseDTO
{
	/** â�� ID */
	private String wcodeId			= "";
    /** ��ǰ ������ġ ID */
    private String ptBinListId		= "";
    /** ������ġ */
    private String binNo			= "";
    /** ���� */
    private String loc 				= "";
    /** �� */
    private String col     			= "";
    /** �� */
    private String ro     			= "";
    /** ��� */
    private String remark 			= "";
    /** ��뿩�� */
    private String isUse 			= "";
    
    
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
	}	
	public String getPtBinListId() {
		return ptBinListId;
	}
	public void setPtBinListId(String ptBinListId) {
		this.ptBinListId = ptBinListId;
		super.setAuditKey(ptBinListId);
	}
	public String getBinNo() {
		return binNo;
	}
	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public String getRo() {
		return ro;
	}
	public void setRo(String ro) {
		this.ro = ro;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
