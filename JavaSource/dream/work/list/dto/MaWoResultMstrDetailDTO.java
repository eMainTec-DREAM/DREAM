package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업결과 - 상세 DTO
 * @author  kim21017
 * @version $Id: MaWoResultMstrDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class MaWoResultMstrDetailDTO extends BaseDTO
{
	/** 작업결과Id */
	private String wkOrId 			= "";
	/** 작업No */
	private String woNo 			= "";
	/** 상태Id */
	private String woStatusId 		= "";
	/** 상태명 */
	private String woStatusDesc 	= "";
	/** 설비id */
	private String equipId 			= "";
	/** 설비명 */
	private String equipDesc 		= "";
	/** 위치 */
	private String eqLocDesc 		= "";
	/** 작업명 */
	private String wkOrDesc 		= "";
	/** 작업종류Id */
	private String woTypeId 		= "";
	/** 작업종류명 */
	private String woTypeDesc 		= "";
	/** 담당부서Id */
	private String deptId 			= "";
	/** 담당부서명 */
	private String deptDesc 		= "";
	/** 작업형태Id */
	private String pmTypeId 		= "";
	/** 작업형태명 */
	private String pmTypeDesc 		= "";
	/** 담당자Id */
	private String empId 			= "";
	/** 담당자명 */
	private String empDesc 			= "";
	/** 작업일자 시작일 */
	private String startDate 		= "";
	/** 작업시간 시작시간 */
	private String endDate 			= "";
	/** 작업일자 종료일 */
	private String startTime 		= "";
	/** 작업시간 종료시간 */
	private String endTime 			= "";
	/** 작업시간(분) */
	private String workTime 		= "";
	/** 작업상세내용 */
	private String perform 			= "";
	/** 자가/외주 구분 */
	private String selfVendorType   = "";
	/** 자가/외주 구분명 */
	private String selfVendorTypeDesc   = "";
	/** 거래처 ID */
	private String vendorId			= "";
	/** 거래처명 */
	private String vendorDesc		= "";
	/** 거래처명(text value) */
    private String vendorName       = "";
	/** 상위작업ID */
	private String pWkOrId          = "";
	/** 작업일자 */
	private String wkorDate         = "";
	/** 교대조ID */
	private String shiftTypeId      = "";
	/** 교대조ID명 */
	private String shiftTypeDesc    = "";
	/** 금액(작업종류-투자인경우만) */
	private String totAmt           = "";
	/** 부품출고확인 KEY */
	private String ptisslistId      = "";
	/** 작업서발생구분 */
	private String woGenType        = "";
	/** 위치id */
	private String eqLocId          = "";
	/** 원인W/O id */
	private String parentWoId       = "";
	/** 원인W/O 명 */
	private String parentWoDesc     = "";
	/** 향후개선내용 */
	private String pmAction			= "";
	/** 작업그룹Id */
	private String wkCtrId			= "";
	/** 작업그룹명 */
	private String wkCtrDesc		= "";
	/** 확정자명 */
	private String closeBy			= "";
	/** 확정일자 */
	private String closeDate		= "";
	/** 교정기준값 ID */
	private String pmCalibStdTpId	= "";
	/** 교육명 */
    private String trainDesc           = "";
    /** 교육Id */
    private String courseListId         = "";
	/** 부 담당자Id */
	private String subEmpId 			= "";
	/** 부 담당자명 */
	private String subEmpDesc 			= "";
	/** 작업요청 ID */
	private String woReqId 			= "";
	/** 설비부위ID */
    private String eqAsmbId         = "";
    /** 설비부위명 */
    private String eqAsmbDesc       = "";
    
    /** 청소여부 ID */
    private String isCleaningId     = "";
    /** 청소여부 */
    private String isCleaning       = "";
    /** 설비종류 */
    private String eqctgType       	= "";
    
    /** 생성일자 */
    private String creDate       	= "";
    /** 수정일자 */
    private String updDate       	= "";
    
    /** 품의여부 */
    private String isDraft			= "";
    
    /** 처리사항 ID */
    private String woReqResId       = "";
    
    /** 설비번호 */
    private String equipNo	        = "";
    /** 상위설비 Desc */
    private String pequipDesc       = "";
    /** 상위설비사용부서 Desc */
    private String pequipUsaDeptDesc= "";
    /** 상위설비ID */
    private String pequipId         = "";
    private String itemNo           = "";
    /** PM ID */
    private String pmId        		= "";
    /** PM param */
    private String pmParam        	= "";
	/** 교정주기 */
	private String calibCycle		= "";
	private String periodTypeId		= "";
	private String periodTypeDesc	= "";
	/** 구설비정보 */
	private String oldEqNo          = "";

	/** 작업비용 (인건비+경비) */
	private String laborAmt			= "";
	/** 점검이상값 ID */
	private String woNgPointId      = "";
	/** 이상점검응답 ID */
	private String woNgPointResId	= "";
	
	/** 설비이력발생구분 */
	private String eqHistGenType	= "";
	
	/** 공장 ID */
	private String plant				= "";
	/** 공장 Desc */
	private String plantDesc			= "";
	/** 현재상태 ID */
	private String currentEqStatusNo	= "";
	/** 현재상태 Desc */
	private String currentEqStatusDesc	= "";
	/** 변경상태 ID */
	private String changedEqStatusId	= "";
	/** 변경상태 NO */
	private String changedEqStatusNo	= "";
	/** 변경상태 Desc */
	private String changedEqStatusDesc	= "";
	/** 현재위치 NO */
	private String currentEqLocNo		= "";
	/** 현재위치 Desc */
	private String currentEqLocDesc		= "";
	/** 변경위치 ID */
	private String changedEqLocId		= "";
	/** 변경위치 NO */
	private String changedEqLocNo		= "";
	/** 변경위치 Desc */
	private String changedEqLocDesc		= "";
	
	public String getChangedEqStatusId() {
		return changedEqStatusId;
	}
	public void setChangedEqStatusId(String changedEqStatusId) {
		this.changedEqStatusId = changedEqStatusId;
	}
	public String getChangedEqLocId() {
		return changedEqLocId;
	}
	public void setChangedEqLocId(String changedEqLocId) {
		this.changedEqLocId = changedEqLocId;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getCurrentEqStatusNo() {
		return currentEqStatusNo;
	}
	public void setCurrentEqStatusNo(String currentEqStatusNo) {
		this.currentEqStatusNo = currentEqStatusNo;
	}
	public String getCurrentEqStatusDesc() {
		return currentEqStatusDesc;
	}
	public void setCurrentEqStatusDesc(String currentEqStatusDesc) {
		this.currentEqStatusDesc = currentEqStatusDesc;
	}
	public String getChangedEqStatusNo() {
		return changedEqStatusNo;
	}
	public void setChangedEqStatusNo(String changedEqStatusNo) {
		this.changedEqStatusNo = changedEqStatusNo;
	}
	public String getChangedEqStatusDesc() {
		return changedEqStatusDesc;
	}
	public void setChangedEqStatusDesc(String changedEqStatusDesc) {
		this.changedEqStatusDesc = changedEqStatusDesc;
	}
	public String getCurrentEqLocNo() {
		return currentEqLocNo;
	}
	public void setCurrentEqLocNo(String currentEqLocNo) {
		this.currentEqLocNo = currentEqLocNo;
	}
	public String getCurrentEqLocDesc() {
		return currentEqLocDesc;
	}
	public void setCurrentEqLocDesc(String currentEqLocDesc) {
		this.currentEqLocDesc = currentEqLocDesc;
	}
	public String getChangedEqLocNo() {
		return changedEqLocNo;
	}
	public void setChangedEqLocNo(String changedEqLocNo) {
		this.changedEqLocNo = changedEqLocNo;
	}
	public String getChangedEqLocDesc() {
		return changedEqLocDesc;
	}
	public void setChangedEqLocDesc(String changedEqLocDesc) {
		this.changedEqLocDesc = changedEqLocDesc;
	}
	public String getEqHistGenType() {
		return eqHistGenType;
	}
	public void setEqHistGenType(String eqHistGenType) {
		this.eqHistGenType = eqHistGenType;
	}
	public String getWoNgPointId() {
		return woNgPointId;
	}
	public void setWoNgPointId(String woNgPointId) {
		this.woNgPointId = woNgPointId;
	}
	public String getWoNgPointResId() {
		return woNgPointResId;
	}
	public void setWoNgPointResId(String woNgPointResId) {
		this.woNgPointResId = woNgPointResId;
	}
	public String getLaborAmt() {
		return laborAmt;
	}
	public void setLaborAmt(String laborAmt) {
		this.laborAmt = CommonUtil.convertMoney(laborAmt);
	}
	public String getOldEqNo()
    {
        return oldEqNo;
    }
    public void setOldEqNo(String oldEqNo)
    {
        this.oldEqNo = oldEqNo;
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
	public String getCalibCycle() {
		return calibCycle;
	}
	public void setCalibCycle(String calibCycle) {
		this.calibCycle = calibCycle;
	}
	public String getPmId() {
		return pmId;
	}
	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	public String getPmParam() {
		return pmParam;
	}
	public void setPmParam(String pmParam) {
		this.pmParam = pmParam;
	}
	public String getPequipId()
    {
        return pequipId;
    }
    public void setPequipId(String pequipId)
    {
        this.pequipId = pequipId;
    }
    public String getItemNo()
    {
        return itemNo;
    }
    public void setItemNo(String itemNo)
    {
        this.itemNo = itemNo;
    }
    public String getIsDraft() {
		return isDraft;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public String getPequipDesc() {
		return pequipDesc;
	}
	public void setPequipDesc(String pequipDesc) {
		this.pequipDesc = pequipDesc;
	}
	public String getPequipUsaDeptDesc() {
		return pequipUsaDeptDesc;
	}
	public void setPequipUsaDeptDesc(String pequipUsaDeptDesc) {
		this.pequipUsaDeptDesc = pequipUsaDeptDesc;
	}
	public String getWoReqResId() {
		return woReqResId;
	}
	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}
	public void setIsDraft(String isDraft) {
		this.isDraft = isDraft;
	}
	public String getCreDate() {
		return creDate;
	}
	public void setCreDate(String creDate) {
		this.creDate = creDate;
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getEqctgType() {
		return eqctgType;
	}
	public void setEqctgType(String eqctgType) {
		this.eqctgType = eqctgType;
	}
	public String getIsCleaningId() {
		return isCleaningId;
	}
	public void setIsCleaningId(String isCleaningId) {
		this.isCleaningId = isCleaningId;
	}
	public String getIsCleaning() {
		return isCleaning;
	}
	public void setIsCleaning(String isCleaning) {
		this.isCleaning = isCleaning;
	}
	public String getEqAsmbId()
    {
        return eqAsmbId;
    }
    public void setEqAsmbId(String eqAsmbId)
    {
        this.eqAsmbId = eqAsmbId;
    }
    public String getEqAsmbDesc()
    {
        return eqAsmbDesc;
    }
    public void setEqAsmbDesc(String eqAsmbDesc)
    {
        this.eqAsmbDesc = eqAsmbDesc;
    }
    public String getWoReqId() {
		return woReqId;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getSubEmpId() {
		return subEmpId;
	}
	public void setSubEmpId(String subEmpId) {
		this.subEmpId = subEmpId;
	}
	public String getSubEmpDesc() {
		return subEmpDesc;
	}
	public void setSubEmpDesc(String subEmpDesc) {
		this.subEmpDesc = subEmpDesc;
	}
	public String getVendorName()
    {
        return vendorName;
    }
    public void setVendorName(String vendorName)
    {
        this.vendorName = vendorName;
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
    public String getPmCalibStdTpId() {
		return pmCalibStdTpId;
	}
	public void setPmCalibStdTpId(String pmCalibStdTpId) {
		this.pmCalibStdTpId = pmCalibStdTpId;
	}
	public String getCloseBy() {
		return closeBy;
	}
	public void setCloseBy(String closeBy) {
		this.closeBy = closeBy;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = CommonUtil.convertDate(closeDate);
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
	public String getPmAction() {
		return pmAction;
	}
	public void setPmAction(String pmAction) {
		this.pmAction = pmAction;
	}
	public String getParentWoId() {
		return parentWoId;
	}
	public void setParentWoId(String parentWoId) {
		this.parentWoId = parentWoId;
	}
	
	public String getParentWoDesc() {
		return parentWoDesc;
	}
	public void setParentWoDesc(String parentWoDesc) {
		this.parentWoDesc = parentWoDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getWoGenType() {
		return woGenType;
	}
	public void setWoGenType(String woGenType) {
		this.woGenType = woGenType;
	}
	public String getPtisslistId()
    {
        return ptisslistId;
    }
    public void setPtisslistId(String ptisslistId)
    {
        this.ptisslistId = ptisslistId;
    }
    public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = CommonUtil.convertMoney(totAmt);
	}
	public String getWkorDate() {
		return wkorDate;
	}
	public void setWkorDate(String wkorDate) {
		this.wkorDate = CommonUtil.convertDate(wkorDate);
	}
	public String getShiftTypeId() {
		return shiftTypeId;
	}
	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}
	public String getShiftTypeDesc() {
		return shiftTypeDesc;
	}
	public void setShiftTypeDesc(String shiftTypeDesc) {
		this.shiftTypeDesc = shiftTypeDesc;
	}
	public String getpWkOrId()
    {
        return pWkOrId;
    }
    public void setpWkOrId(String pWkOrId)
    {
        this.pWkOrId = pWkOrId;
    }
    public String getSelfVendorTypeDesc() {
		return selfVendorTypeDesc;
	}
	public void setSelfVendorTypeDesc(String selfVendorTypeDesc) {
		this.selfVendorTypeDesc = selfVendorTypeDesc;
	}
	public String getSelfVendorType() {
		return selfVendorType;
	}
	public void setSelfVendorType(String selfVendorType) {
		this.selfVendorType = selfVendorType;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getWoStatusId() {
		return woStatusId;
	}
	public void setWoStatusId(String woStatusId) {
		this.woStatusId = woStatusId;
	}
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
		super.setAuditKey(wkOrId);
	}
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	public String getWoStatusDesc() {
		return woStatusDesc;
	}
	public void setWoStatusDesc(String woStatusDesc) {
		this.woStatusDesc = woStatusDesc;
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
	public String getWkOrDesc() {
		return wkOrDesc;
	}
	public void setWkOrDesc(String wkOrDesc) {
		this.wkOrDesc = wkOrDesc;
	}
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = CommonUtil.convertTime(endTime);
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime);
	}
	public String getPerform() {
		return perform;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
	
}
