package mobile.dream.work.pm.list.dto;

import common.bean.BaseDTO;

/**
 * ��ȹ�۾� ���� DTO
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
	/** ����From */
	private String woDateFrom	= "";
	/** ����To */
	private String woDateTo		= "";
	/** �μ�ID */
	private String deptId		= "";
	/** �μ��� */
	private String deptDesc		= "";
	/** �۾��׷�ID */
	private String wkCtrId		= "";
	/** �۾��׷�� */
	private String wkCtrDesc	= "";
	/** ������ġID */
	private String eqLocId		= "";
	/** ������ġ�� */
	private String eqLocDesc	= "";
	/** �������� */
	private String eqCtgType	= "";
	/** ���������� */
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
