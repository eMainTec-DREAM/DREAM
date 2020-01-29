package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �۾����� �� DTO
 * @author  kim21017
 * @version $Id: MaWoResultClnDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultClnDetailDTO extends BaseDTO
{
	/** �۾�����ID */
	private String woEquipId			= "";
	/** ����id*/
	private String equipId				= "";
	/** �����*/
	private String equipDesc			= "";
	/** ������ġ�� */
	private String eqLocDesc			= "";
	/** �۾����� */
	private String workPart				= "";
	/** �۾��ð� */
	private String workTime				= "";
	/** ��� */
	private String remark				= "";
	/** �۾��߻����� */
	private String woGenType			= "";
	
	public String getWoGenType() {
		return woGenType;
	}
	public void setWoGenType(String woGenType) {
		this.woGenType = woGenType;
	}
	public String getWoEquipId() {
		return woEquipId;
	}
	public void setWoEquipId(String woEquipId) {
		this.woEquipId = woEquipId;
		super.setAuditKey(woEquipId);
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getWorkPart() {
		return workPart;
	}
	public void setWorkPart(String workPart) {
		this.workPart = workPart;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}