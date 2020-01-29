package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업설비 상세 DTO
 * @author  kim21017
 * @version $Id: MaWoResultClnDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaWoResultClnDetailDTO extends BaseDTO
{
	/** 작업설비ID */
	private String woEquipId			= "";
	/** 설비id*/
	private String equipId				= "";
	/** 설비명*/
	private String equipDesc			= "";
	/** 설비위치명 */
	private String eqLocDesc			= "";
	/** 작업부위 */
	private String workPart				= "";
	/** 작업시간 */
	private String workTime				= "";
	/** 비고 */
	private String remark				= "";
	/** 작업발생구분 */
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