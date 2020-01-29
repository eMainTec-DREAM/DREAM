package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * 부품창고 보관위치 - Detail DTO
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 *
 */
public class MgrPtWhBinDetailDTO extends BaseDTO
{
	/** 창고 ID */
	private String wcodeId			= "";
    /** 부품 저장위치 ID */
    private String ptBinListId		= "";
    /** 보관위치 */
    private String binNo			= "";
    /** 구역 */
    private String loc 				= "";
    /** 열 */
    private String col     			= "";
    /** 행 */
    private String ro     			= "";
    /** 비고 */
    private String remark 			= "";
    /** 사용여부 */
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
