package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방작업기준 공통 DTO
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
	/** PM번호 */
	private String pmNo		    = "";
	/** PM명 */
	private String pmDesc		= "";
	/** 작업형태 */
	private String pmType		= "";
	/** 작업형태명 */
	private String pmTypeDesc   = "";
	/** 작업종류 */
	private String woType		= "";
	/** 작업종류명 */
	private String woTypeDesc   = "";
	/** 일정생성종류 */
	private String scheduleType		= "";
	/** 일정생성종류명 */
	private String scheduleTypeDesc = "";
	/** 설비 */
    private String equipId      ="";
    /** 설비명 */
    private String equipDesc    = "";
	/** 위치 */
    private String eqLocId      ="";
    /** 위치명 */
    private String eqLocDesc    = "";
	/** 종류 */
    private String eqCtgId      ="";
    /** 종류명 */
    private String eqCtgDesc    = "";
    /** 설비유형 */
    private String eqCtgTypeId  ="";
    /** 설비유형명 */
    private String eqCtgTypeDesc= "";
	/** 내/외자 */
    private String plfType      ="";
    /** 내/외자 명 */
    private String plfTypeDesc  = "";
    /** 법정설비여부 */
    private String isLawEq      = "";
	/** 관리자(정) */
    private String mainMngId      ="";
    /** 관리자명(정) */
    private String mainMngName   = "";
	/** 관리자(부) */
    private String subMngId      ="";
    /** 관리자명(부) */
    private String subMngName    = "";
    /** 부서 */
    private String deptId       = "";
    /** 부서명 */
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
    
    /** 필터-공장 ID */
    private String filterPlantId         	= "";
    /** 필터-공장 DESC */
    private String filterPlantDesc         	= "";
    
    /** 생성일자 from */
    private String filterFromCreDate		= "";
    /** 생성일자 to */
    private String filterToCreDate			= "";
    
    /** 작업시간 id */
    private String pmMsTimeId				= "";
    /** 점검항목 상세 페이지  */
    private String pointDetailPageId		= "";
    /** 점검항목 타입 (생성시 선택된 것)  */
    private String pointDetailCheckTypeId	= "";
    
    /** 필터 - 설비번호 */
    private String filterEquipNo			= "";
    /** 필터 - 상위설비 ID */
    private String filterPEquipId			= "";
    /** 필터 - 상위설비 No */
    private String filterPEquipNo           = "";
    /** 필터 - 상위설비 DESC */
    private String filterPEquipDesc			= "";
    /** 필터 - 설비사용부서 ID */
    private String filterEqUsaDeptId		= "";
    /** 필터 - 설비사용부서 DESC */
    private String filterEqUsaDeptDesc		= "";
    /** 필터 - 상위설비사용부서 ID */
    private String filterPEqUsaDeptId		= "";
    /** 필터 - 상위설비사용부서 DESC */
    private String filterPEqUsaDeptDesc		= "";
    /** 필터 사용부서 */
    private String filterUsageDeptId        = "";
    /** 필터 사용부서명 */
    private String filterUsageDeptDesc      = "";
    /** MultiSelect 표준점검항목선택 ID */
    private String multiCheckPartKey		= "";
    /** MultiSelect 표준점검항목선택 DESC */
    private String multiCheckPartDesc 		= "";
	/** 엑셀업로드시 set할 exceltab_no  */
	private String exceltabNo				= "";
    /** 제조사  */
	private String filterMaker              = "";
	/** 모델 */
	private String filterModel              = "";
	/** 설비등급 */
	private String filterEqGrade            = "";
	/** 설비등급명 */
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
	/** 청소,윤활 설비id */
    private String pmEquipId	= "";
    /** 작업그룹 Id */
    private String wkCtrId		= "";
    /** 작업그룹 명 */
    private String wkCtrDesc	= "";
    /** 일자 id */
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
