package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����ֱ� �� dto
 * @author  kim21017
 * @version $Id: AssetListTcycleDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class AssetListTcycleDetailDTO extends BaseDTO
{
	/** �����ֱ� id */
	private String eqPmCycleId		= "";
	/** PM ID*/
	private String pmId				= "";
	/** �ֱ� */
	private String cycle			= "";
	/** �ֱⱸ��ID */
	private String periodType		= "";
	/** �ֱⱸ�и� */
	private String periodTypeDesc	= "";
	/** �����۾������� */
	private String initWrkDate		= "";
	/** ������ �۾��˸� */
	private String woResBef			= "";
	/** �۾��׷�ID */
	private String wkCtrId			= "";
	/** �۾��׷�� */
	private String wkCtrDesc		= "";
	/** �۾��׷�� */
	private String equipDesc		= "";
	/** �۾��׷�� */
	private String deptId			= "";
	/** ���࿩�� */
	private String isActive			= "";
	/** ��� */
	private String remark			= "";
	/** ����Ÿ�� */
	private String pmcTypeId		= "";
	/** �ٹ��޷�ID */
	private String wrkcalListId		= "";
	/** �ٹ��޷¸� */
	private String wrkcalListDesc	= "";
	/** PM ����ID */
	private String pmEquipId		= "";
	/** �������� */
	private String calDate			= "";
	/** ���ⱳ���� */
	private String nextCalDate		= "";
	
	/** Revision Status */
	private String revisionStatusId	= "";
	/** �ֽŹ������� */
	private String isLastVersion	= "";
	/** �����ڵ� */
	private String plant            = "";
	/** ����� */
	private String plantDesc        = "";
	
	
	public String getPlant()
    {
        return plant;
    }
    public void setPlant(String plant)
    {
        this.plant = plant;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getRevisionStatusId() {
		return revisionStatusId;
	}
	public void setRevisionStatusId(String revisionStatusId) {
		this.revisionStatusId = revisionStatusId;
	}
	public String getIsLastVersion() {
		return isLastVersion;
	}
	public void setIsLastVersion(String isLastVersion) {
		this.isLastVersion = isLastVersion;
	}
	public String getCalDate() {
		return calDate;
	}
	public void setCalDate(String calDate) {
		this.calDate = CommonUtil.convertDate(calDate);
	}
	public String getNextCalDate() {
		return nextCalDate;
	}
	public void setNextCalDate(String nextCalDate) {
		this.nextCalDate = CommonUtil.convertDate(nextCalDate);
	}
	public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
	}
	public String getWrkcalListId() {
		return wrkcalListId;
	}
	public void setWrkcalListId(String wrkcalListId) {
		this.wrkcalListId = wrkcalListId;
	}
	public String getWrkcalListDesc() {
		return wrkcalListDesc;
	}
	public void setWrkcalListDesc(String wrkcalListDesc) {
		this.wrkcalListDesc = wrkcalListDesc;
	}
	public String getPmcTypeId() {
		return pmcTypeId;
	}
	public void setPmcTypeId(String pmcTypeId) {
		this.pmcTypeId = pmcTypeId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getEqPmCycleId() {
		return eqPmCycleId;
	}
	public void setEqPmCycleId(String eqPmCycleId) {
		this.eqPmCycleId = eqPmCycleId;
		super.setAuditKey(eqPmCycleId);
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getInitWrkDate() {
		return initWrkDate;
	}
	public void setInitWrkDate(String initWrkDate) {
		this.initWrkDate = CommonUtil.convertDate(initWrkDate);
	}
	public String getWoResBef() {
		return woResBef;
	}
	public void setWoResBef(String woResBef) {
		this.woResBef = woResBef;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}