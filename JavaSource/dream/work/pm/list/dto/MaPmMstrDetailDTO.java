package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 상세 DTO
 * @author  jung7126
 * @version $Id: MaPmMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class MaPmMstrDetailDTO extends BaseDTO
{
    /** 비고 */
    private String remark 				= "";
    /** 시행여부 */
    private String isActive 			= "";
    private String isActiveDesc 		= "";
    
    private String oldInitWrkDate 		= ""; //기존에 사용자가 입력한 일자
    private String initWrkDate 			= ""; //사용자가 입력한 일자
    private String lastSchDate 			= ""; //마지막 일정생성 일자-주말보정전, 스베튤 시작은 이 날짜로 시작해야 함.
    private String nextSchDate 			= ""; //마지막 일정생성 일자-주말보정값..
    
    public String getNextSchDate() {
		return nextSchDate;
	}
	public void setNextSchDate(String nextSchDate) {
		this.nextSchDate = nextSchDate;
	}
	/** 몇일전/몇시간전 자동생성 */
    private String woResBef 			= "";
    /** 사용량 */
    private String usage 				= "";
    /** 주기-년,월,주, 일 */
    private String periodType 			= "";
    
    private String periodTypeDesc   	= "";
    /** 주기-기간 */
    private String cycle 				= "";
    /** 사용량/시간 구분(T:시간, U:사용량) */
    private String scheduleType 		= "";
    
    private String scheduleTypeDesc 	= "";
    /** 작업형태 */
    private String pmType 				= "";
    
    private String pmTypeDesc   		= "";
    /** 부서 */
    private String deptId 				= "";
    
    private String deptDesc 			= "";
    /** 설비코드 */
    private String equipId 				= "";
    
    private String equipDesc    		= "";
    /** PM Equip Id */
    private String pmEquipId			= "";
    /** PM 명 */
    private String description 			= "";
    /** PM 번호 */
    private String pmNo 				= "";
    /** PM ID */
    private String pmId 				= "";
    /** 회사코드 */
    private String compNo 				= "";
    
    /** 작업유형  */
    private String pmCateg  			= "";
    /** 작업유형명 */
    private String pmCategDesc  		= "";
    /** 사원ID */
    private String empId        		= "";
    /** 사원명 */
    private String empName      		= "";
    /** 작업종류 */
    private String woType       		= "";
    /** 작업종류명 */
    private String woTypeDesc   		= "";
    /** 위치ID */
	private String eqLocId 				= "";
	/** 위치명 */
	private String eqLocDesc 			= "";
	/** 위치명 */
	private String eqLocType 			= "";
	/** 작업그룹Id */
	private String wkCtrId	 			= "";
	/** 작업그룹명 */
	private String wkCtrDesc 			= "";
	/** 근무달력ID */
	private String wrkcalListId			= "";
	/** 근무달력명 */
	private String wrkcalListDesc		= "";
	/** 교육명 */
	private String trainDesc            = "";
	/** 교육Id */
    private String courseListId         = "";
	/** Revision Id */
	private String revisionhistId		= "";
	/** Revision 상태ID */
	private String revisionStatusId		= "";
	/** Revision 상태명 */
	private String revisionStatusDesc	= "";
	/** 최신 Version 여부 */
	private String isLastVersion		= "";
	/** 1일 작업 횟수 */
	private String workNumber           = "";
	
	
    private String oldPeriodType 		= "";
    private String oldCycle 			= "";
    private String oldScheduleType 		= "";
    private String oldPmNo				= "";
    private String oldEquipId			= "";
    
    /** 예상작업시간(뿐) */
    private String predWoTimeMin 		= "";
    
    /** 생성일자 */
    private String creDate       		= "";
    /** 수정일자 */
    private String updDate       		= "";
    
    /** PM param */
    private String pmParam 				= "";
    /** 가동달력Id */
    private String lnWrkListId 			= "";
    /** 가동달력명 */
    private String lnWrkListDesc 		= "";
    /** 공장 Id */
    private String plantId              = "";
    /** 공장명 */
    private String plantDesc            = "";
    /** 지연허용(기간/시간/횟수)값 */
    private String tolerance            = "";
    
    
    public String getTolerance() {
		return tolerance;
	}
	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}
	public String getOldEquipId() {
		return oldEquipId;
	}
	public void setOldEquipId(String oldEquipId) {
		this.oldEquipId = oldEquipId;
	}
	public String getOldInitWrkDate() {
		return oldInitWrkDate;
	}
	public void setOldInitWrkDate(String oldInitWrkDate) {
		this.oldInitWrkDate = oldInitWrkDate;
	}
	public String getOldPmNo() {
		return oldPmNo;
	}
	public void setOldPmNo(String oldPmNo) {
		this.oldPmNo = oldPmNo;
	}
	public String getPmEquipId() {
		return pmEquipId;
	}
	public void setPmEquipId(String pmEquipId) {
		this.pmEquipId = pmEquipId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getLnWrkListId()
    {
        return lnWrkListId;
    }
    public void setLnWrkListId(String lnWrkListId)
    {
        this.lnWrkListId = lnWrkListId;
    }
    public String getLnWrkListDesc()
    {
        return lnWrkListDesc;
    }
    public void setLnWrkListDesc(String lnWrkListDesc)
    {
        this.lnWrkListDesc = lnWrkListDesc;
    }
    public String getPmParam() {
		return pmParam;
	}
	public void setPmParam(String pmParam) {
		this.pmParam = pmParam;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = CommonUtil.convertDateTime(creDate);
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDateTime(updDate);
	}
	public String getPredWoTimeMin() {
		return predWoTimeMin;
	}
	public void setPredWoTimeMin(String predWoTimeMin) {
		this.predWoTimeMin = CommonUtil.convertMoney(predWoTimeMin);
	}
	public String getIsActiveDesc() {
		return isActiveDesc;
	}
	public void setIsActiveDesc(String isActiveDesc) {
		this.isActiveDesc = isActiveDesc;
	}
	public String getOldPeriodType() {
		return oldPeriodType;
	}
	public void setOldPeriodType(String oldPeriodType) {
		this.oldPeriodType = oldPeriodType;
	}
	public String getOldCycle() {
		return oldCycle;
	}
	public void setOldCycle(String oldCycle) {
		this.oldCycle = oldCycle;
	}
	public String getOldScheduleType() {
		return oldScheduleType;
	}
	public void setOldScheduleType(String oldScheduleType) {
		this.oldScheduleType = oldScheduleType;
	}
	public String getWorkNumber()
    {
        return workNumber;
    }
    public void setWorkNumber(String workNumber)
    {
        this.workNumber = CommonUtil.convertMoney(workNumber);
    }
    public String getTrainDesc()
    {
        return trainDesc;
    }
    public void setTrainDesc(String trainDesc)
    {
        this.trainDesc = trainDesc;
    }
    public String getCourseListId()
    {
        return courseListId;
    }
    public void setCourseListId(String courseListId)
    {
        this.courseListId = courseListId;
    }
    public String getRevisionStatusId() {
		return revisionStatusId;
	}
	public void setRevisionStatusId(String revisionStatusId) {
		this.revisionStatusId = revisionStatusId;
	}
	public String getRevisionStatusDesc() {
		return revisionStatusDesc;
	}
	public void setRevisionStatusDesc(String revisionStatusDesc) {
		this.revisionStatusDesc = revisionStatusDesc;
	}
	public String getRevisionhistId() {
		return revisionhistId;
	}
	public void setRevisionhistId(String revisionhistId) {
		this.revisionhistId = revisionhistId;
	}
	public String getIsLastVersion() {
		return isLastVersion;
	}
	public void setIsLastVersion(String isLastVersion) {
		this.isLastVersion = isLastVersion;
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
	public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getWoTypeDesc()
    {
        return woTypeDesc;
    }
    public void setWoTypeDesc(String woTypeDesc)
    {
        this.woTypeDesc = woTypeDesc;
    }
    public String getPmCateg()
    {
        return pmCateg;
    }
    public void setPmCateg(String pmCateg)
    {
        this.pmCateg = pmCateg;
    }
    public String getPmCategDesc()
    {
        return pmCategDesc;
    }
    public void setPmCategDesc(String pmCategDesc)
    {
        this.pmCategDesc = pmCategDesc;
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
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public String getIsActive()
    {
        return isActive;
    }
    public void setIsActive(String isActive)
    {
        this.isActive = isActive;
    }
    public String getLastSchDate()
    {
        return lastSchDate;
    }
    public void setLastSchDate(String lastSchDate)
    {
        this.lastSchDate = CommonUtil.convertDate(lastSchDate);
    }
    public String getInitWrkDate()
    {
        return initWrkDate;
    }
    public void setInitWrkDate(String initWrkDate)
    {
        this.initWrkDate =  CommonUtil.convertDate(initWrkDate);
    }
    public String getWoResBef()
    {
        return woResBef;
    }
    public void setWoResBef(String woResBef)
    {
        this.woResBef = CommonUtil.convertMoney(woResBef);
    }
    public String getUsage()
    {
        return usage;
    }
    public void setUsage(String usage)
    {
        this.usage = CommonUtil.convertMoney(usage);
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
    public String getCycle()
    {
        return cycle;
    }
    public void setCycle(String cycle)
    {
        this.cycle = CommonUtil.convertMoney(cycle);
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
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getPmNo()
    {
        return pmNo;
    }
    public void setPmNo(String pmNo)
    {
        this.pmNo = pmNo;
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
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
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
	public String getEqLocType() {
		return eqLocType;
	}
	public void setEqLocType(String eqLocType) {
		this.eqLocType = eqLocType;
	}

	/** 생산제품 ID */
    private String prodGoodsId         = "";
    /** 생산제품 */
    private String prodGoods         = "";
    
    
    public String getProdGoodsId()
    {
        return prodGoodsId;
    }
    public void setProdGoodsId(String prodGoodsId)
    {
        this.prodGoodsId = prodGoodsId;
    }
    public String getProdGoods()
    {
        return prodGoods;
    }
    public void setProdGoods(String prodGoods)
    {
        this.prodGoods = prodGoods;
    }
}
