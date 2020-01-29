package mobile.dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * 계획작업 공통 DTO
 * @author  jung7126
 * @version $Id: WorkPmWorkCommonDTO.java,v 1.0 2015/12/02 09:13:08 jung7126 Exp $
 * @since   1.0
 * 
 */
public class WorkPmWorkCommonDTO extends BaseDTO
{
	/** Work ID */
	private String wkorId		= "";
	
	private String equipId		= "";
	/** PM ID */
	private String pmId			= "";
	/** 일자From */
	private String woDateFrom	= "";
	/** 일자To */
	private String woDateTo		= "";
	/** 부서ID */
	private String deptId		= "";
	/** 부서명 */
	private String deptDesc		= "";
	/** 작업그룹ID */
	private String wkCtrId		= "";
	/** 작업그룹명 */
	private String wkCtrDesc	= "";
	/** 설비위치ID */
	private String eqLocId		= "";
	/** 설비위치명 */
	private String eqLocDesc	= "";
	/** 설비유형 */
	private String eqCtgType	= "";
	/** 설비유형명 */
	private String eqCtgTypeDesc	= "";
	
	
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getWkorId() {
		return wkorId;
	}
	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getWoDateFrom() {
		return woDateFrom;
	}
	public void setWoDateFrom(String woDateFrom) {
		this.woDateFrom = woDateFrom;
	}
	public String getWoDateTo() {
		return woDateTo;
	}
	public void setWoDateTo(String woDateTo) {
		this.woDateTo = woDateTo;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getEqCtgType() {
		return eqCtgType;
	}
	public void setEqCtgType(String eqCtgType) {
		this.eqCtgType = eqCtgType;
	}
	public String getEqCtgTypeDesc() {
		return eqCtgTypeDesc;
	}
	public void setEqCtgTypeDesc(String eqCtgTypeDesc) {
		this.eqCtgTypeDesc = eqCtgTypeDesc;
	}
}
