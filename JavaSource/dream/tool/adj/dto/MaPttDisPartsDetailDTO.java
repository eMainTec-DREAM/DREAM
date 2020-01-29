package dream.tool.adj.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세  DTO
 * @author  kim21017
 * @version $Id: MaPttDisPartsDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaPttDisPartsDetailDTO extends BaseDTO
{
    /** Id */
    private String ptdisuseitemId = "";
    /** 자재 Id */
    private String partNo = "";
    /** 자재번호 */
    private String partId = "";
    /** 자재명 */
    private String partDesc = "";
    /** 폐기수량 */
    private String disQty = "";
    /** 비고 */
    private String remark = "";
    
	public String getPtdisuseitemId() {
		return ptdisuseitemId;
	}
	public void setPtdisuseitemId(String ptdisuseitemId) {
		this.ptdisuseitemId = ptdisuseitemId;
		super.setAuditKey(ptdisuseitemId);
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getDisQty() {
		return disQty;
	}
	public void setDisQty(String disQty) {
		this.disQty = disQty;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

    
}