package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * ǥ���׸� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaStdPartDetailDTO extends BaseDTO
{
	/** ����Id */
	private String stWrkPartId			= "";
	/** ����Id */
	private String partNo				= "";
	/** ����Id */
	private String partDesc				= "";
	/** ����Id */
	private String remark				= "";
	/** ����Id */
	private String partId				= "";
	/** ������ */
	private String useQty				= "";
	
	public String getStWrkPartId() {
		return stWrkPartId;
	}
	public String getUseQty() {
		return useQty;
	}
	public void setUseQty(String useQty) {
		this.useQty = useQty;
	}
	public void setStWrkPartId(String stWrkPartId) {
		this.stWrkPartId = stWrkPartId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	

}
