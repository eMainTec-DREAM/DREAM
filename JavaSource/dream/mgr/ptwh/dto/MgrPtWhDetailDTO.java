package dream.mgr.ptwh.dto;

import common.bean.BaseDTO;

/**
 * ��ǰâ�� - Detail DTO
 * @author sy.yang
 * @version $Id: $
 * @since 1.0
 *
 */
public class MgrPtWhDetailDTO extends BaseDTO
{
	/**â�� ID*/
	private String wcodeId     = "";
		
	/** â���ڵ� */
	private String wcode       = "";
	/** â��� */
	private String wname       = "";
	/** ����  */
	private String plant       = "";
	/** �����  */
	private String plantDesc   = "";
	/** â��з�(ERP, MMS) */
	private String whType      = "";
	private String whTypeDesc  = "";
	/** ��뿩�� */
	private String isUse       = "";
	/** ��� */
	private String remark      = "";
	
	
	public String getWcodeId() {
		return wcodeId;
	}
	public void setWcodeId(String wcodeId) {
		this.wcodeId = wcodeId;
		super.setAuditKey(wcodeId);
	}
	public String getWcode() {
		return wcode;
	}
	public void setWcode(String wcode) {
		this.wcode = wcode;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWhType() {
		return whType;
	}
	public void setWhType(String whType) {
		this.whType = whType;
	}
	public String getWhTypeDesc() {
		return whTypeDesc;
	}
	public void setWhTypeDesc(String whTypeDesc) {
		this.whTypeDesc = whTypeDesc;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	
}
