package dream.work.cal.pmrinsmonth.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������������ ���� DTO
 * @author  kim21017
 * @version $Id: WorkCalPmRInsMonthCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 *
 */
public class WorkCalPmRInsMonthCommonDTO extends BaseDTO
{
	/** ������ ID */
	private String pmInsSchedId 				= "";
	/** ���� �� */
	private String filterYyyymm	 			= "";
	/** ���� ��ȹ�μ�id */
	private String filterDeptId 			= "";
	/** ���� ��ȹ�μ��� */
	private String filterDeptDesc 			= "";
	/** ���� ����μ�id */
	private String filterExeDeptId 			= "";
	/** ���� ����μ��� */
	private String filterExeDeptDesc 		= "";
	/** ���� ����id */
	private String filterEquipId 			= "";
	/** ���� ����� */
	private String filterEquipDesc 			= "";
	/** ����-�������񿩺� */
	private String filterIsLawEq			= "";
	/** ����-������(��)id */
	private String filterMainMngId			= "";
	/** ����-������(��)�� */
	private String filterMainMngName		= "";
	/** ����-������(��)id */
	private String filterSubMngId			= "";
	/** ����-������(��)�� */
	private String filterSubMngName			= "";
	/** ����-��ġid */
	private String filterEqLocId			= "";
	/** ����-��ġ�� */
	private String filterEqLocDesc			= "";
	/** ����-����id */
	private String filterEqCtgId			= "";
	/** ����-���� */
	private String filterEqCtgDesc			= "";
	/** ����-��/���� */
	private String filterPlfTypeId			= "";
	/** ����-��/���� �� */
	private String filterPlfTypeDesc		= "";
	/** ����-�۾��׷�id */
	private String filterWkCtrId			= "";
	/** ����-�۾��׷� �� */
	private String filterWkCtrDesc			= "";
	/** ����-pmNo */
	private String filterPmNo				= "";
	/** Filter-��������Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-�������� */
	private String filterEqCtgTypeDesc 	   = "";
	/** Filter-�ֱ�Ÿ�� Id */
	private String filterPeriodType 	   = "";
	/** Filter-�ֱ�Ÿ��  */
	private String filterPeriodTypeDesc	   = "";
	/** Filter-�ֱ�  */
	private String filterCycle				= "";
	/** ����� */
	private String yyyymmdd 				= "";
	/** schedType */
	private String schedType 				= "";
	/** ��ȹ�μ�id(�˻��� �μ�id ����)*/
	private String deptId 					= "";
	/** ��ȹ�μ���(�˻��� �μ��� ����) */
	private String deptDesc 				= "";
	/** ����μ�id(�˻��� �μ�id ����)*/
	private String exeDeptId 				= "";
	/** ����μ���(�˻��� �μ��� ����) */
	private String exeDeptDesc 				= "";
	/** �������񿩺� */
	private String isLawEq					= "";
	/** ������(��)id */
	private String mainMngId				= "";
	/** ������(��)�� */
	private String mainMngName				= "";
	/** ������(��)id */
	private String subMngId					= "";
	/** ������(��)�� */
	private String subMngName				= "";
	/** ��ġid */
	private String eqLocId					= "";
	/** ��ġ�� */
	private String eqLocDesc				= "";
	/** ����id */
	private String eqCtgId					= "";
	/** ���� */
	private String eqCtgDesc				= "";
	/** ����id */
	private String equipId					= "";
	/** ���� */
	private String equipDesc				= "";
	/** ��/���� */
	private String plfTypeId				= "";
	/** ��/���� �� */
	private String plfTypeDesc				= "";
	/** pm��ȣ */
	private String pmNo						= "";
	/** �۾��׷�id */
	private String wkCtrId					= "";
	/** �۾��׷� �� */
	private String wkCtrDesc				= "";

	private String cycle                    ="";

	private String periodTypeDesc           = "";

	private String periodType               = "";
	/** ��������Id */
	private String eqCtgTypeId 	   			= "";
	/** �������� */
	private String eqCtgTypeDesc 	   		= "";

	/** filter-��ȹ����� Id */
    private String filterPlanEmpId          = "";
    /** filter-��ȹ����� */
    private String filterPlanEmpDesc        = "";
    /** filter-�������� Id */
    private String filterEmpId              = "";
    /** filter-�������� */
    private String filterEmpDesc            = "";
    /** ��ȹ����� Id */
    private String planEmpId                = "";
    /** ��ȹ����� */
    private String planEmpDesc              = "";
    /** �������� Id */
    private String empId                    = "";
    /** �������� */
    private String empDesc                  = "";

    /** filter-���� Id */
	private String filterPlantId 	   = "";
	/** filter-����� */
	private String filterPlantDesc 	   = "";
	/** ���� Id */
	private String plantId 	   			= "";
	/** ����� */
	private String plantDesc 	   		= "";
	/** ���ͻ��μ��� */
	private String filterUsageDeptDesc     = "";
	/** ���ͻ��μ� */
	private String filterUsageDeptId       = "";

	private String pmInsListId          = "";
    
    public String getFilterExeDeptId()
    {
        return filterExeDeptId;
    }
    public void setFilterExeDeptId(String filterExeDeptId)
    {
        this.filterExeDeptId = filterExeDeptId;
    }
    public String getFilterExeDeptDesc()
    {
        return filterExeDeptDesc;
    }
    public void setFilterExeDeptDesc(String filterExeDeptDesc)
    {
        this.filterExeDeptDesc = filterExeDeptDesc;
    }
    public String getExeDeptId()
    {
        return exeDeptId;
    }
    public void setExeDeptId(String exeDeptId)
    {
        this.exeDeptId = exeDeptId;
    }
    public String getExeDeptDesc()
    {
        return exeDeptDesc;
    }
    public void setExeDeptDesc(String exeDeptDesc)
    {
        this.exeDeptDesc = exeDeptDesc;
    }
    public String getFilterPlanEmpId()
    {
        return filterPlanEmpId;
    }
    public void setFilterPlanEmpId(String filterPlanEmpId)
    {
        this.filterPlanEmpId = filterPlanEmpId;
    }
    public String getFilterPlanEmpDesc()
    {
        return filterPlanEmpDesc;
    }
    public void setFilterPlanEmpDesc(String filterPlanEmpDesc)
    {
        this.filterPlanEmpDesc = filterPlanEmpDesc;
    }
    public String getPlanEmpId()
    {
        return planEmpId;
    }
    public void setPlanEmpId(String planEmpId)
    {
        this.planEmpId = planEmpId;
    }
    public String getPlanEmpDesc()
    {
        return planEmpDesc;
    }
    public void setPlanEmpDesc(String planEmpDesc)
    {
        this.planEmpDesc = planEmpDesc;
    }
    public String getPmInsListId()
    {
        return pmInsListId;
    }
    public void setPmInsListId(String pmInsListId)
    {
        this.pmInsListId = pmInsListId;
    }
	public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getFilterUsageDeptId()
    {
        return filterUsageDeptId;
    }
    public void setFilterUsageDeptId(String filterUsageDeptId)
    {
        this.filterUsageDeptId = filterUsageDeptId;
    }
    public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
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
	public String getFilterPeriodType() {
		return filterPeriodType;
	}
	public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
    }
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpDesc()
    {
        return empDesc;
    }
    public void setEmpDesc(String empDesc)
    {
        this.empDesc = empDesc;
    }
    public void setFilterPeriodType(String filterPeriodType) {
		this.filterPeriodType = filterPeriodType;
	}
	public String getFilterPeriodTypeDesc() {
		return filterPeriodTypeDesc;
	}
	public void setFilterPeriodTypeDesc(String filterPeriodTypeDesc) {
		this.filterPeriodTypeDesc = filterPeriodTypeDesc;
	}
	public String getFilterCycle() {
		return filterCycle;
	}
	public void setFilterCycle(String filterCycle) {
		this.filterCycle = filterCycle;
	}
	public String getPmInsSchedId() {
		return pmInsSchedId;
	}
	public void setPmInsSchedId(String pmInsSchedId) {
		this.pmInsSchedId = pmInsSchedId;
	}
	public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}
	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}
	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}
	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
	}
	public String getEqCtgTypeId() {
		return eqCtgTypeId;
	}
	public void setEqCtgTypeId(String eqCtgTypeId) {
		this.eqCtgTypeId = eqCtgTypeId;
	}
	public String getEqCtgTypeDesc() {
		return eqCtgTypeDesc;
	}
	public void setEqCtgTypeDesc(String eqCtgTypeDesc) {
		this.eqCtgTypeDesc = eqCtgTypeDesc;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
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
	public String getCycle()
    {
        return cycle;
    }
    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }
    public String getPeriodTypeDesc()
    {
        return periodTypeDesc;
    }
    public void setPeriodTypeDesc(String periodTypeDesc)
    {
        this.periodTypeDesc = periodTypeDesc;
    }
    public String getPeriodType()
    {
        return periodType;
    }
    public void setPeriodType(String periodType)
    {
        this.periodType = periodType;
    }
    public String getFilterPmNo() {
		return filterPmNo;
	}
	public void setFilterPmNo(String filterPmNo) {
		this.filterPmNo = filterPmNo;
	}
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
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
	public String getFilterYyyymm() {
		return filterYyyymm;
	}
	public void setFilterYyyymm(String filterYyyymm) {
		this.filterYyyymm = CommonUtil.convertDate(filterYyyymm);
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getYyyymmdd() {
		return yyyymmdd;
	}
	public void setYyyymmdd(String yyyymmdd) {
		this.yyyymmdd = yyyymmdd;
	}
	public String getSchedType() {
		return schedType;
	}
	public void setSchedType(String schedType) {
		this.schedType = schedType;
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
	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}
	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
	}
	public String getFilterMainMngId() {
		return filterMainMngId;
	}
	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}
	public String getFilterMainMngName() {
		return filterMainMngName;
	}
	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}
	public String getFilterSubMngId() {
		return filterSubMngId;
	}
	public void setFilterSubMngId(String filterSubMngId) {
		this.filterSubMngId = filterSubMngId;
	}
	public String getFilterSubMngName() {
		return filterSubMngName;
	}
	public void setFilterSubMngName(String filterSubMngName) {
		this.filterSubMngName = filterSubMngName;
	}
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterPlfTypeId() {
		return filterPlfTypeId;
	}
	public void setFilterPlfTypeId(String filterPlfTypeId) {
		this.filterPlfTypeId = filterPlfTypeId;
	}
	public String getFilterPlfTypeDesc() {
		return filterPlfTypeDesc;
	}
	public void setFilterPlfTypeDesc(String filterPlfTypeDesc) {
		this.filterPlfTypeDesc = filterPlfTypeDesc;
	}
	public String getIsLawEq() {
		return isLawEq;
	}
	public void setIsLawEq(String isLawEq) {
		this.isLawEq = isLawEq;
	}
	public String getMainMngId() {
		return mainMngId;
	}
	public void setMainMngId(String mainMngId) {
		this.mainMngId = mainMngId;
	}
	public String getMainMngName() {
		return mainMngName;
	}
	public void setMainMngName(String mainMngName) {
		this.mainMngName = mainMngName;
	}
	public String getSubMngId() {
		return subMngId;
	}
	public void setSubMngId(String subMngId) {
		this.subMngId = subMngId;
	}
	public String getSubMngName() {
		return subMngName;
	}
	public void setSubMngName(String subMngName) {
		this.subMngName = subMngName;
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
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
	}
	public String getPlfTypeId() {
		return plfTypeId;
	}
	public void setPlfTypeId(String plfTypeId) {
		this.plfTypeId = plfTypeId;
	}
	public String getPlfTypeDesc() {
		return plfTypeDesc;
	}
	public void setPlfTypeDesc(String plfTypeDesc) {
		this.plfTypeDesc = plfTypeDesc;
	}

}
