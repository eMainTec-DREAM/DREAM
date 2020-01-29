package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���漳�� �� DTO
 * @author  jung7126
 * @version $Id: MaPmEqClnDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaPmEqClnDetailDTO extends BaseDTO
{
	/** �����۾�����ID */
	private String pmEquipId			= "";
	/** ����id*/
	private String equipId				= "";
	/** �����*/
	private String equipDesc			= "";
	/** ������ġ�� */
	private String eqLocDesc			= "";
	/** �۾����� */
	private String workPart				= "";
	/** �����۾������� */
	private String initWrkDate			= "";
	
	public String getInitWrkDate() {
		return initWrkDate;
	}
	public void setInitWrkDate(String initWrkDate) {
		this.initWrkDate = CommonUtil.convertDate(initWrkDate);
	}
	public String getWorkPart() {
		return workPart;
	}
	public void setWorkPart(String workPart) {
		this.workPart = workPart;
	}
	public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
		super.setAuditKey(pmEquipId);
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
}