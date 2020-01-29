package dream.asset.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ��������  �� DTO
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsDetailDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class MaEqMstrPmInsDetailDTO extends BaseDTO
{
	/** ����ID */
	private String equipId				= "";
	/** �����۾�ID */
	private String pmId					= "";
	/** �����۾�����ID */
	private String pmEquipId			= "";
	
	/** �����۾�No */
	private String pmNo					= "";
	

	/** �۾�����ID */
	private String woTypeId				= "";
	
	/** �۾�����ID */
	private String pmTypeId				= "";
	/** �۾����¸� */
	private String pmTypeDesc			= "";
	/** �ֱ� */
	private String cycle				= "";
	/** OLD �ֱ�(UPDATE �� �ֱⰡ ����Ǿ����� Ȯ���� �� ���) */
	private String oldCycle				= "";
	/** �ֱ�Ÿ��ID */
	private String periodTypeId			= "";
	/** OLD �ֱ�Ÿ��(UPDATE �� �ֱ�Ÿ���� ����Ǿ����� Ȯ���� �� ���) */
	private String oldPeriodTypeId		= "";
	/** �ֱ�Ÿ�Ը� */
	private String periodTypeDesc		= "";
	/** ���࿩�� ID */
	private String isActiveId			= "";
	/** ���࿩�� �� */
	private String isActiveDesc			= "";
	/** �۾������� */
	private String initWrkDate			= "";
	/** �۾������� (UPDATE �� �������� ����Ǿ����� Ȯ���� �� ���) */
	private String oldInitWrkDate		= "";
	/** �μ�ID */
	private String deptId				= "";
	/** �μ��� */
	private String deptDesc				= "";
	/** �۾��׷�ID */
	private String wkCtrId				= "";
	/** �۾��׷�� */
	private String wkCtrDesc			= "";
	/** �����ID */
	private String empId				= "";
	/** ����ڸ� */
	private String empDesc				= "";
	/** �ٹ��޷�ID */
	private String wrkCalListId			= "";
	/** �ٹ��޷¸� */
	private String wrkCalListDesc		= "";
	/** revision History ID */
	private String revisionHistId		= "";
	/** ��� */
	private String remark				= "";
	/** �۾��� */
	private String description			= "";

	/** ����ID */
	private String plantId				= "";
	/** ����� */
	private String plantDesc			= "";
	/** ����������� ID */
	private String scheduleTypeId		= "";
	/** ������������� */
	private String scheduleTypeDesc		= "";
	/** �����ð� */
	private String usage				= "";
	/** �������۾����� */
	private String woResBef				= "";
	/** �����޷� ID */
	private String lnWrkListId			= "";
	/** �����޷¸� */
	private String lnWrkListDesc		= "";
	/** �����۾��ð�(��) */
	private String predWoTimeMin		= "";

	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getScheduleTypeId() {
		return scheduleTypeId;
	}
	public void setScheduleTypeId(String scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}
	public String getScheduleTypeDesc() {
		return scheduleTypeDesc;
	}
	public void setScheduleTypeDesc(String scheduleTypeDesc) {
		this.scheduleTypeDesc = scheduleTypeDesc;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getWoResBef() {
		return woResBef;
	}
	public void setWoResBef(String woResBef) {
		this.woResBef = woResBef;
	}
	public String getLnWrkListId() {
		return lnWrkListId;
	}
	public void setLnWrkListId(String lnWrkListId) {
		this.lnWrkListId = lnWrkListId;
	}
	public String getLnWrkListDesc() {
		return lnWrkListDesc;
	}
	public void setLnWrkListDesc(String lnWrkListDesc) {
		this.lnWrkListDesc = lnWrkListDesc;
	}
	public String getPredWoTimeMin() {
		return predWoTimeMin;
	}
	public void setPredWoTimeMin(String predWoTimeMin) {
		this.predWoTimeMin = predWoTimeMin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOldInitWrkDate() {
		return oldInitWrkDate;
	}
	public void setOldInitWrkDate(String oldInitWrkDate) {
		this.oldInitWrkDate = oldInitWrkDate;
	}
	public String getOldCycle() {
		return oldCycle;
	}
	public void setOldCycle(String oldCycle) {
		this.oldCycle = oldCycle;
	}
	public String getOldPeriodTypeId() {
		return oldPeriodTypeId;
	}
	public void setOldPeriodTypeId(String oldPeriodTypeId) {
		this.oldPeriodTypeId = oldPeriodTypeId;
	}
	public String getRevisionHistId() {
		return revisionHistId;
	}
	public void setRevisionHistId(String revisionHistId) {
		this.revisionHistId = revisionHistId;
	}
	public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getPeriodTypeId() {
		return periodTypeId;
	}
	public void setPeriodTypeId(String periodTypeId) {
		this.periodTypeId = periodTypeId;
	}
	public String getPeriodTypeDesc() {
		return periodTypeDesc;
	}
	public void setPeriodTypeDesc(String periodTypeDesc) {
		this.periodTypeDesc = periodTypeDesc;
	}
	public String getIsActiveId() {
		return isActiveId;
	}
	public void setIsActiveId(String isActiveId) {
		this.isActiveId = isActiveId;
	}
	public String getIsActiveDesc() {
		return isActiveDesc;
	}
	public void setIsActiveDesc(String isActiveDesc) {
		this.isActiveDesc = isActiveDesc;
	}
	public String getInitWrkDate() {
		return initWrkDate;
	}
	public void setInitWrkDate(String initWrkDate) {
		this.initWrkDate = CommonUtil.convertDate(initWrkDate);
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
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpDesc() {
		return empDesc;
	}
	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}
	public String getWrkCalListId() {
		return wrkCalListId;
	}
	public void setWrkCalListId(String wrkCalListId) {
		this.wrkCalListId = wrkCalListId;
	}
	public String getWrkCalListDesc() {
		return wrkCalListDesc;
	}
	public void setWrkCalListDesc(String wrkCalListDesc) {
		this.wrkCalListDesc = wrkCalListDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}