package dream.asset.list.dto;

import common.bean.BaseDTO;

/**
 * ���񸶽��� - �ǹ� �� DTO
 * @author  kim21017
 * @version $Id: MaEqMoldMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaEqDeviceMstrDetailDTO extends BaseDTO
{
	/** �����ڻ��ǰ ���纸����ġ */
	private String eqdeviceLocNo = "";
	/** ��ǰ��ȣ */
	private String partId = "";
	/** �����ڵ� */
	private String equipId = "";
	
	private String partDesc	= "";
	/** ȸ���ڵ� */
	private String compNo = "";
	
	private String eqdeviceLocNoDesc	 = "";
	
	
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getEqdeviceLocNoDesc() {
		return eqdeviceLocNoDesc;
	}
	public void setEqdeviceLocNoDesc(String eqdeviceLocNoDesc) {
		this.eqdeviceLocNoDesc = eqdeviceLocNoDesc;
	}
	public String getEqdeviceLocNo() {
		return eqdeviceLocNo;
	}
	public void setEqdeviceLocNo(String eqdeviceLocNo) {
		this.eqdeviceLocNo = eqdeviceLocNo;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
}
