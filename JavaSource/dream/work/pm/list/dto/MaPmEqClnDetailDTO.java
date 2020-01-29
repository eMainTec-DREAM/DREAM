package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방설비 상세 DTO
 * @author  jung7126
 * @version $Id: MaPmEqClnDetailDTO.java,v 1.1 2015/12/04 09:10:45 jung7126 Exp $
 * @since   1.0
 */
public class MaPmEqClnDetailDTO extends BaseDTO
{
	/** 예방작업설비ID */
	private String pmEquipId			= "";
	/** 설비id*/
	private String equipId				= "";
	/** 설비명*/
	private String equipDesc			= "";
	/** 설비위치명 */
	private String eqLocDesc			= "";
	/** 작업부위 */
	private String workPart				= "";
	/** 최초작업예정일 */
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