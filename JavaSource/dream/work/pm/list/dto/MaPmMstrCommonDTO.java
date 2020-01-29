package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����۾����� ���� DTO
 * @author  jung7126
 * @version $Id: MaPmMstrCommonDTO.java,v 1.0 2015/12/02 09:13:08 jung7126 Exp $
 * @since   1.0
 * 
 */
public class MaPmMstrCommonDTO extends BaseDTO
{
    private String wkcntStart   = "";
    
    private String wkcntEnd     = "";
	/** PM ID */
	private String pmId			= "";
	/** PM��ȣ */
	private String pmNo		    = "";
	/** PM�� */
	private String pmDesc		= "";
	/** �۾����� */
	private String pmType		= "";
	/** �۾����¸� */
	private String pmTypeDesc   = "";
	/** �۾����� */
	private String woType		= "";
	/** �۾������� */
	private String woTypeDesc   = "";
	/** ������������ */
	private String scheduleType		= "";
	/** �������������� */
	private String scheduleTypeDesc = "";
	/** ���� */
    private String equipId      ="";
    /** ����� */
    private String equipDesc    = "";
	/** ��ġ */
    private String eqLocId      ="";
    /** ��ġ�� */
    private String eqLocDesc    = "";
	/** ���� */
    private String eqCtgId      ="";
    /** ������ */
    private String eqCtgDesc    = "";
    /** �������� */
    private String eqCtgTypeId  ="";
    /** ���������� */
    private String eqCtgTypeDesc= "";
	/** ��/���� */
    private String plfType      ="";
    /** ��/���� �� */
    private String plfTypeDesc  = "";
    /** �������񿩺� */
    private String isLawEq      = "";
	/** ������(��) */
    private String mainMngId      ="";
    /** �����ڸ�(��) */
    private String mainMngName   = "";
	/** ������(��) */
    private String subMngId      ="";
    /** �����ڸ�(��) */
    private String subMngName    = "";
    /** �μ� */
    private String deptId       = "";
    /** �μ��� */
    private String deptDesc     = "";
    
    private String pmPartId     = "";
    
    private String pmPointId    = "";
    
    private String selectedPmType = "";
    
    private String selectedWoType   = "";
    
    private String empId        = "";
    
    private String empName      = "";
    
    private String periodType   = "";
    
    private String periodTypeDesc   = "";
    
    private String cycle        = "";
    
    private String pmWrkShiftId     = "";
    
    private String param     = "";
    
    private String isLastVersion     = "";
    
    private String revisionStatus     = "";
    
    private String revisionStatusDesc     = "";
    
    private String revNo     = "";
    
    private String isLastVersionDesc     = "";
    
    /** ����-���� ID */
    private String filterPlantId         	= "";
    /** ����-���� DESC */
    private String filterPlantDesc         	= "";
    
    /** �������� from */
    private String filterFromCreDate		= "";
    /** �������� to */
    private String filterToCreDate			= "";
    
    /** �۾��ð� id */
    private String pmMsTimeId				= "";
    /** �����׸� �� ������  */
    private String pointDetailPageId		= "";
    /** �����׸� Ÿ�� (������ ���õ� ��)  */
    private String pointDetailCheckTypeId	= "";
    
    /** ���� - �����ȣ */
    private String filterEquipNo			= "";
    /** ���� - �������� ID */
    private String filterPEquipId			= "";
    /** ���� - �������� No */
    private String filterPEquipNo           = "";
    /** ���� - �������� DESC */
    private String filterPEquipDesc			= "";
    /** ���� - ������μ� ID */
    private String filterEqUsaDeptId		= "";
    /** ���� - ������μ� DESC */
    private String filterEqUsaDeptDesc		= "";
    /** ���� - ����������μ� ID */
    private String filterPEqUsaDeptId		= "";
    /** ���� - ����������μ� DESC */
    private String filterPEqUsaDeptDesc		= "";
    /** ���� ���μ� */
    private String filterUsageDeptId        = "";
    /** ���� ���μ��� */
    private String filterUsageDeptDesc      = "";
    /** MultiSelect ǥ�������׸��� ID */
    private String multiCheckPartKey		= "";
    /** MultiSelect ǥ�������׸��� DESC */
    private String multiCheckPartDesc 		= "";
	/** �������ε�� set�� exceltab_no  */
	private String exceltabNo				= "";
    /** ������  */
	private String filterMaker              = "";
	/** �� */
	private String filterModel              = "";
	/** ������ */
	private String filterEqGrade            = "";
	/** �����޸� */
	private String filterEqGradeDesc        = "";
	
	
	
    public String getFilterMaker()
    {
        return filterMaker;
    }
    public void setFilterMaker(String filterMaker)
    {
        this.filterMaker = filterMaker;
    }
    public String getFilterModel()
    {
        return filterModel;
    }
    public void setFilterModel(String filterModel)
    {
        this.filterModel = filterModel;
    }
    public String getFilterEqGrade()
    {
        return filterEqGrade;
    }
    public void setFilterEqGrade(String filterEqGrade)
    {
        this.filterEqGrade = filterEqGrade;
    }
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
    }
    public String getFilterPEquipNo()
    {
        return filterPEquipNo;
    }
    public void setFilterPEquipNo(String filterPEquipNo)
    {
        this.filterPEquipNo = filterPEquipNo;
    }
    public String getExceltabNo() {
		return exceltabNo;
	}
	public void setExceltabNo(String exceltabNo) {
		this.exceltabNo = exceltabNo;
	}
	public String getScheduleType()
    {
        return scheduleType;
    }
    public void setScheduleType(String scheduleType)
    {
        this.scheduleType = scheduleType;
    }
    public String getScheduleTypeDesc()
    {
        return scheduleTypeDesc;
    }
    public void setScheduleTypeDesc(String scheduleTypeDesc)
    {
        this.scheduleTypeDesc = scheduleTypeDesc;
    }
    public String getFilterUsageDeptId()
    {
        return filterUsageDeptId;
    }
    public void setFilterUsageDeptId(String filterUsageDeptId)
    {
        this.filterUsageDeptId = filterUsageDeptId;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getMultiCheckPartKey() {
		return multiCheckPartKey;
	}
	public void setMultiCheckPartKey(String multiCheckPartKey) {
		this.multiCheckPartKey = multiCheckPartKey;
	}
	public String getMultiCheckPartDesc() {
		return multiCheckPartDesc;
	}
	public void setMultiCheckPartDesc(String multiCheckPartDesc) {
		this.multiCheckPartDesc = multiCheckPartDesc;
	}
	public String getPointDetailCheckTypeId() {
		return pointDetailCheckTypeId;
	}
	public void setPointDetailCheckTypeId(String pointDetailCheckTypeId) {
		this.pointDetailCheckTypeId = pointDetailCheckTypeId;
	}
	public String getPointDetailPageId() {
		return pointDetailPageId;
	}
	public void setPointDetailPageId(String pointDetailPageId) {
		this.pointDetailPageId = pointDetailPageId;
	}
	public String getPmMsTimeId() {
		return pmMsTimeId;
	}
	public void setPmMsTimeId(String pmMsTimeId) {
		this.pmMsTimeId = pmMsTimeId;
	}
	public String getFilterFromCreDate() {
		return filterFromCreDate;
	}
	public void setFilterFromCreDate(String filterFromCreDate) {
		this.filterFromCreDate = CommonUtil.convertDate(filterFromCreDate);
	}
	public String getFilterToCreDate() {
		return filterToCreDate;
	}
	public void setFilterToCreDate(String filterToCreDate) {
		this.filterToCreDate = CommonUtil.convertDate(filterToCreDate);
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
	public String getIsLastVersionDesc()
    {
        return isLastVersionDesc;
    }
    public void setIsLastVersionDesc(String isLastVersionDesc)
    {
        this.isLastVersionDesc = isLastVersionDesc;
    }
    public String getIsLastVersion() {
		return isLastVersion;
	}
	public void setIsLastVersion(String isLastVersion) {
		this.isLastVersion = isLastVersion;
	}
	public String getRevisionStatus() {
		return revisionStatus;
	}
	public void setRevisionStatus(String revisionStatus) {
		this.revisionStatus = revisionStatus;
	}
	public String getRevisionStatusDesc() {
		return revisionStatusDesc;
	}
	public void setRevisionStatusDesc(String revisionStatusDesc) {
		this.revisionStatusDesc = revisionStatusDesc;
	}
	public String getRevNo() {
		return revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = revNo;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getPmWrkShiftId()
    {
        return pmWrkShiftId;
    }
    public void setPmWrkShiftId(String pmWrkShiftId)
    {
        this.pmWrkShiftId = pmWrkShiftId;
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
	/** û��,��Ȱ ����id */
    private String pmEquipId	= "";
    /** �۾��׷� Id */
    private String wkCtrId		= "";
    /** �۾��׷� �� */
    private String wkCtrDesc	= "";
    /** ���� id */
    private String pmEventSchedId	= "";
    
    public String getPmEventSchedId() {
		return pmEventSchedId;
	}
	public void setPmEventSchedId(String pmEventSchedId) {
		this.pmEventSchedId = pmEventSchedId;
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
	public String getWkcntStart()
    {
        return wkcntStart;
    }
    public void setWkcntStart(String wkcntStart)
    {
        this.wkcntStart = wkcntStart;
    }
    public String getWkcntEnd()
    {
        return wkcntEnd;
    }
    public void setWkcntEnd(String wkcntEnd)
    {
        this.wkcntEnd = wkcntEnd;
    }
    public String getCycle()
    {
        return cycle;
    }
    public void setCycle(String cycle)
    {
        this.cycle = cycle;
    }
    public String getPeriodType()
    {
        return periodType;
    }
    public void setPeriodType(String periodType)
    {
        this.periodType = periodType;
    }
    public String getPeriodTypeDesc()
    {
        return periodTypeDesc;
    }
    public void setPeriodTypeDesc(String periodTypeDesc)
    {
        this.periodTypeDesc = periodTypeDesc;
    }
    public String getWoType() {
		return woType;
	}
	public void setWoType(String woType) {
		this.woType = woType;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}
	public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
    }
    public String getEmpName()
    {
        return empName;
    }
    public void setEmpName(String empName)
    {
        this.empName = empName;
    }
    public String getSelectedWoType()
    {
        return selectedWoType;
    }
    public void setSelectedWoType(String selectedWoType)
    {
        this.selectedWoType = selectedWoType;
    }
    public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
	}
	public String getSelectedPmType() {
		return selectedPmType;
	}
	public void setSelectedPmType(String selectedPmType) {
		this.selectedPmType = selectedPmType;
	}
	public String getPlfType() {
		return plfType;
	}
	public void setPlfType(String plfType) {
		this.plfType = plfType;
	}
	public String getPlfTypeDesc() {
		return plfTypeDesc;
	}
	public void setPlfTypeDesc(String plfTypeDesc) {
		this.plfTypeDesc = plfTypeDesc;
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
	public String getPmPointId()
    {
        return pmPointId;
    }
    public void setPmPointId(String pmPointId)
    {
        this.pmPointId = pmPointId;
        super.setAuditKey(pmPointId);
    }
    public String getPmPartId()
    {
        return pmPartId;
    }
    public void setPmPartId(String pmPartId)
    {
        this.pmPartId = pmPartId;
    }
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
        super.setAuditKey(pmId);
    }
    public String getPmNo()
    {
        return pmNo;
    }
    public void setPmNo(String pmNo)
    {
        this.pmNo = pmNo;
    }
    public String getPmDesc()
    {
        return pmDesc;
    }
    public void setPmDesc(String pmDesc)
    {
        this.pmDesc = pmDesc;
    }
    public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getPmTypeDesc()
    {
        return pmTypeDesc;
    }
    public void setPmTypeDesc(String pmTypeDesc)
    {
        this.pmTypeDesc = pmTypeDesc;
    } 
    public String getEquipId()
    {
        return equipId;
    }
    public void setEquipId(String equipId)
    {
        this.equipId = equipId;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
    }
    public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getDeptDesc()
    {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc)
    {
        this.deptDesc = deptDesc;
    }
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterEqUsaDeptId() {
		return filterEqUsaDeptId;
	}
	public void setFilterEqUsaDeptId(String filterEqUsaDeptId) {
		this.filterEqUsaDeptId = filterEqUsaDeptId;
	}
	public String getFilterEqUsaDeptDesc() {
		return filterEqUsaDeptDesc;
	}
	public void setFilterEqUsaDeptDesc(String filterEqUsaDeptDesc) {
		this.filterEqUsaDeptDesc = filterEqUsaDeptDesc;
	}
	public String getFilterPEqUsaDeptId() {
		return filterPEqUsaDeptId;
	}
	public void setFilterPEqUsaDeptId(String filterPEqUsaDeptId) {
		this.filterPEqUsaDeptId = filterPEqUsaDeptId;
	}
	public String getFilterPEqUsaDeptDesc() {
		return filterPEqUsaDeptDesc;
	}
	public void setFilterPEqUsaDeptDesc(String filterPEqUsaDeptDesc) {
		this.filterPEqUsaDeptDesc = filterPEqUsaDeptDesc;
	}
	public String getFilterPEquipId() {
		return filterPEquipId;
	}
	public void setFilterPEquipId(String filterPEquipId) {
		this.filterPEquipId = filterPEquipId;
	}
	public String getFilterPEquipDesc() {
		return filterPEquipDesc;
	}
	public void setFilterPEquipDesc(String filterPEquipDesc) {
		this.filterPEquipDesc = filterPEquipDesc;
	}
}
