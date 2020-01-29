package dream.work.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 작업계획목록 - 상세 DTO
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 */
public class WoPlanDetailDTO extends BaseDTO
{
	/** 작업결과Id */
	private String wkOrId 				= "";
	/** 작업No */
	private String woNo 				= "";
	/** 상태Id */
	private String woPlanStatusId 		= "";
	/** 상태명 */
	private String woPlanStatusDesc 	= "";
	/** 설비id */
	private String equipId 				= "";
	/** 설비명 */
	private String equipDesc 			= "";
	/** 위치 */
	private String eqLocDesc 			= "";
	/** 작업명 */
	private String wkOrDesc 			= "";
	/** 작업종류Id */
	private String woTypeId 			= "";
	/** 작업종류명 */
	private String woTypeDesc 			= "";
	/** 담당부서Id */
	private String deptId 				= "";
	/** 담당부서명 */
	private String deptDesc 			= "";
	/** 작업형태Id */
	private String pmTypeId 			= "";
	/** 작업형태명 */
	private String pmTypeDesc 			= "";
	/** 담당자Id */
	private String empId 				= "";
	/** 담당자명 */
	private String empDesc 				= "";
	/** 작업일자 시작일 */
	private String startDate 			= "";
	/** 작업시간 시작시간 */
	private String endDate 				= "";
	/** 작업일자 종료일 */
	private String startTime 			= "";
	/** 작업시간 종료시간 */
	private String endTime 				= "";
	/** 작업시간(분) */
	private String workTime 			= "";
	/** 작업상세내용 */
	private String perform 				= "";
	/** 자가/외주 구분 */
	private String selfVendorType   	= "";
	/** 자가/외주 구분명 */
	private String selfVendorTypeDesc	= "";
	/** 거래처 ID */
	private String vendorId				= "";
	/** 거래처명 */
	private String vendorDesc			= "";
	/** 거래처명(text value) */
    private String vendorName       	= "";
	/** 상위작업ID */
	private String pWkOrId          	= "";
	/** 작업일자 */
	private String wkorDate         	= "";
	/** 교대조ID */
	private String shiftTypeId      	= "";
	/** 교대조ID명 */	
	private String shiftTypeDesc    	= "";
	/** 금액(작업종류-투자인경우만) */
	private String totAmt           	= "";
	/** 부품출고확인 KEY */
	private String ptisslistId      	= "";
	/** 작업서발생구분 */
	private String woGenType        	= "";
	/** 위치id */
	private String eqLocId          	= "";
	/** 원인W/O id */
	private String parentWoId       	= "";
	/** 원인W/O 명 */
	private String parentWoDesc     	= "";
	/** 작업그룹Id */
	private String wkCtrId				= "";
	/** 작업그룹명 */
	private String wkCtrDesc			= "";
	/** 확정자명 */
	private String closeBy				= "";
	/** 확정일자 */
	private String closeDate			= "";
	/** 교정기준값 ID */
	private String pmCalibStdTpId		= "";
	/** 교육명 */
    private String trainDesc        	= "";
    /** 교육Id */
    private String courseListId     	= "";
	/** 부 담당자Id */
	private String subEmpId 			= "";
	/** 부 담당자명 */
	private String subEmpDesc 			= "";
	/** 처리사항  ID */
	private String woReqResId 			= "";
	/** 공장 ID */
	private String plant				= "";
	/** 공장 DESC */
	private String plantDesc 			= "";
	/** 부위 ID */
	private String eqAsmbId				= "";
	/** 부위 DESC */
	private String eqAsmbDesc 			= "";
	
	public String getEqAsmbId() {
		return eqAsmbId;
	}
	public void setEqAsmbId(String eqAsmbId) {
		this.eqAsmbId = eqAsmbId;
	}
	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}
	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
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
	public String getWoReqResId() {
		return woReqResId;
	}
	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
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
	public String getWoPlanStatusId() {
		return woPlanStatusId;
	}
	public void setWoPlanStatusId(String woPlanStatusId) {
		this.woPlanStatusId = woPlanStatusId;
	}
	public String getWoPlanStatusDesc() {
		return woPlanStatusDesc;
	}
	public void setWoPlanStatusDesc(String woPlanStatusDesc) {
		this.woPlanStatusDesc = woPlanStatusDesc;
	}
}
