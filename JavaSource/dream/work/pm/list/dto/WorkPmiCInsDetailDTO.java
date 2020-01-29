package dream.work.pm.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * WorkPmiCIns Page - Detail DTO
 * @author youngjoo38
 * @version $Id: WorkPmiCInsDetailDTO.java,v 1.0 2017/08/22 15:19:40 youngjoo38 Exp $
 * @since 1.0
 *
 */
public class WorkPmiCInsDetailDTO extends BaseDTO
{
    /** 결과 ID */
    private String pmInsDListId            = "";
    /** SCHED ID */
    private String pmInsDSchedId            = "";
    /** 결과 NO */
    private String pmInsDListNo            = "";
    
    /** 작업결과ID */
    private String wkOrId               = "";
    /** 작업명 */
    private String description          = "";
    /** 작업번호 */
    private String woNo                 = "";
    /** 작업일자 시작일 */
    private String startDate            = "";
    /** 작업일자 시작시간 */
    private String startTime            = "";
    /** 작업일자 종료일 */
    private String endDate              = "";
    /** 작업일자 종료시간 */
    private String endTime              = "";
    /** 부서id */
    private String deptId               = "";
    /** 부서명 */
    private String deptDesc             = "";
    /** 설비id */
    private String equipId              = "";
    /** 설비id */
    private String equipNo              = "";
    /** 설비명 */
    private String equipDesc            = "";
    /** 담당자id */
    private String empId                = "";
    /** 담당자명 */
    private String empDesc              = "";
    /** 위치id */
    private String eqLocId              = "";
    /** 위치명 */
    private String eqLocDesc            = "";
    /** 종류id */
    private String eqCtgId              = "";
    /** 종류명 */
    private String eqCtgDesc            = "";
    /** 작업종류 */
    private String woTypeId             = "";
    /** 작업종류 명 */
    private String woTypeDesc           = "";
    /** 작업형태 */
    private String pmTypeId             = "";
    /** 작업형태 명 */
    private String pmTypeDesc           = "";
    /** 법정설비여부 ID*/
    private String isLawEqId            = "";
    /** 법정설비여부 */
    private String isLawEq              = "";
    /** 관리자(정)id */
    private String mainMngId            = "";
    /** 관리자(정)명 */
    private String mainMngName          = "";
    /** 관리자(부)id */
    private String subMngId             = "";
    /** 관리자(부)명 */
    private String subMngName           = "";
    /** 상태코드 */
    private String schedStatusId        = "";
    /** 상태코드명 */
    private String schedStatusDesc      = "";
    /** 교대조ID */
    private String shiftTypeId          = "";
    /** 교대조ID명 */
    private String shiftTypeDesc        = "";
    /** 작업그룹Id */
    private String wkCtrId              = "";
    /** 작업그룹명 */
    private String wkCtrDesc            = "";
    /** 예방작업ID */
    private String pmId                 = "";
    /** 예방작업# */
    private String pmNo                 = "";
    /** 선택된 wkorId */
    private String selectedWkorId       = "";
    /** 선택된 작업형태 */
    private String selectedPmType       = "";
    /** 선택된 작업종류 */
    private String selectedWoType       = "";
    /** 설비유형Id */
    private String eqCtgTypeId          = "";
    /** 설비유형 */
    private String eqCtgTypeDesc        = "";
    /** 비고 */
    private String remark               = "";
    
    /** 작업시간(분) */
    private String workTime             = "";
    /** 확정자명 */
    private String closeBy              = "";
    /** 확정자 ID */
    private String closeById            = "";
    /** 확정일자 */
    private String closeDate            = "";
    /** 운전시간 */
    private String operatingTime        = "";
    /** 측정시간 */
    private String measureTime          = "";
    /** 사용량 */
    private String usage                = "";
    /** 작업일자 */
    private String wkorDate             = "";
    /** 회차 */
    private String workNumber           = "";
    /** 생산제품 ID */
    private String productId            = "";
    /** 생산제품 DESC */
    private String productDesc          = "";

    private String pmParam            	= "";
    
    
    public String getPmParam() {
		return pmParam;
	}
	public void setPmParam(String pmParam) {
		this.pmParam = pmParam;
	}
	public String getProductId()
    {
        return productId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    public String getProductDesc()
    {
        return productDesc;
    }
    public void setProductDesc(String productDesc)
    {
        this.productDesc = productDesc;
    }
    public String getPmInsDSchedId()
    {
        return pmInsDSchedId;
    }
    public void setPmInsDSchedId(String pmInsDSchedId)
    {
        this.pmInsDSchedId = pmInsDSchedId;
    }
    public String getWorkNumber()
    {
        return workNumber;
    }
    public void setWorkNumber(String workNumber)
    {
        this.workNumber = workNumber;
    }
    public String getOperatingTime()
    {
        return operatingTime;
    }
    public void setOperatingTime(String operatingTime)
    {
        this.operatingTime = CommonUtil.convertTime(operatingTime);
    }
    public String getPmId()
    {
        return pmId;
    }
    public void setPmId(String pmId)
    {
        this.pmId = pmId;
    }
    public String getCloseById()
    {
        return closeById;
    }
    public void setCloseById(String closeById)
    {
        this.closeById = closeById;
    }
    public String getShiftTypeId()
    {
        return shiftTypeId;
    }
    public void setShiftTypeId(String shiftTypeId)
    {
        this.shiftTypeId = shiftTypeId;
    }
    public String getShiftTypeDesc()
    {
        return shiftTypeDesc;
    }
    public void setShiftTypeDesc(String shiftTypeDesc)
    {
        this.shiftTypeDesc = shiftTypeDesc;
    }
    public String getWorkTime()
    {
        return workTime;
    }
    public void setWorkTime(String workTime)
    {
        this.workTime = CommonUtil.convertMoney(workTime);
    }
    public String getCloseBy()
    {
        return closeBy;
    }
    public void setCloseBy(String closeBy)
    {
        this.closeBy = closeBy;
    }
    public String getCloseDate()
    {
        return closeDate;
    }
    public void setCloseDate(String closeDate)
    {
        this.closeDate = CommonUtil.convertDate(closeDate);
    }
    public String getPmInsDListId()
    {
        return pmInsDListId;
    }
    public void setPmInsDListId(String pmInsDListId)
    {
        this.pmInsDListId = pmInsDListId;
    }
    public String getPmInsDListNo()
    {
        return pmInsDListNo;
    }
    public void setPmInsDListNo(String pmInsDListNo)
    {
        this.pmInsDListNo = pmInsDListNo;
    }
    public String getMeasureTime()
    {
        return measureTime;
    }
    public void setMeasureTime(String measureTime)
    {
        this.measureTime = CommonUtil.convertTime(measureTime);
    }
    public String getUsage()
    {
        return usage;
    }
    public void setUsage(String usage)
    {
        this.usage = usage;
    }
    public String getWkorDate() {
        return wkorDate;
    }
    public void setWkorDate(String wkorDate) {
        this.wkorDate = CommonUtil.convertDate(wkorDate);
    }
    public String getWkOrId()
    {
        return wkOrId;
    }
    public void setWkOrId(String wkOrId)
    {
        this.wkOrId = wkOrId;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getWoNo()
    {
        return woNo;
    }
    public void setWoNo(String woNo)
    {
        this.woNo = woNo;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = CommonUtil.convertDate(startDate);
    }
    public String getStartTime()
    {
        return startTime;
    }
    public void setStartTime(String startTime)
    {
        this.startTime = CommonUtil.convertTime(startTime);
    }
    public String getEndDate()
    {
        return endDate;
    }
    public void setEndDate(String endDate)
    {
        this.endDate = CommonUtil.convertDate(endDate);
    }
    public String getEndTime()
    {
        return endTime;
    }
    public void setEndTime(String endTime)
    {
        this.endTime = CommonUtil.convertTime(endTime);
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
    public String getEquipNo()
    {
        return equipNo;
    }
    public void setEquipNo(String equipNo)
    {
        this.equipNo = equipNo;
    }
    public String getEquipDesc()
    {
        return equipDesc;
    }
    public void setEquipDesc(String equipDesc)
    {
        this.equipDesc = equipDesc;
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
    public String getEqLocId()
    {
        return eqLocId;
    }
    public void setEqLocId(String eqLocId)
    {
        this.eqLocId = eqLocId;
    }
    public String getEqLocDesc()
    {
        return eqLocDesc;
    }
    public void setEqLocDesc(String eqLocDesc)
    {
        this.eqLocDesc = eqLocDesc;
    }
    public String getEqCtgId()
    {
        return eqCtgId;
    }
    public void setEqCtgId(String eqCtgId)
    {
        this.eqCtgId = eqCtgId;
    }
    public String getEqCtgDesc()
    {
        return eqCtgDesc;
    }
    public void setEqCtgDesc(String eqCtgDesc)
    {
        this.eqCtgDesc = eqCtgDesc;
    }
    public String getWoTypeId()
    {
        return woTypeId;
    }
    public void setWoTypeId(String woTypeId)
    {
        this.woTypeId = woTypeId;
    }
    public String getWoTypeDesc()
    {
        return woTypeDesc;
    }
    public void setWoTypeDesc(String woTypeDesc)
    {
        this.woTypeDesc = woTypeDesc;
    }
    public String getPmTypeId()
    {
        return pmTypeId;
    }
    public void setPmTypeId(String pmTypeId)
    {
        this.pmTypeId = pmTypeId;
    }
    public String getPmTypeDesc()
    {
        return pmTypeDesc;
    }
    public void setPmTypeDesc(String pmTypeDesc)
    {
        this.pmTypeDesc = pmTypeDesc;
    }
    public String getIsLawEqId()
    {
        return isLawEqId;
    }
    public void setIsLawEqId(String isLawEqId)
    {
        this.isLawEqId = isLawEqId;
    }
    public String getIsLawEq()
    {
        return isLawEq;
    }
    public void setIsLawEq(String isLawEq)
    {
        this.isLawEq = isLawEq;
    }
    public String getMainMngId()
    {
        return mainMngId;
    }
    public void setMainMngId(String mainMngId)
    {
        this.mainMngId = mainMngId;
    }
    public String getMainMngName()
    {
        return mainMngName;
    }
    public void setMainMngName(String mainMngName)
    {
        this.mainMngName = mainMngName;
    }
    public String getSubMngId()
    {
        return subMngId;
    }
    public void setSubMngId(String subMngId)
    {
        this.subMngId = subMngId;
    }
    public String getSubMngName()
    {
        return subMngName;
    }
    public void setSubMngName(String subMngName)
    {
        this.subMngName = subMngName;
    }
    public String getSchedStatusId()
    {
        return schedStatusId;
    }
    public void setSchedStatusId(String schedStatusId)
    {
        this.schedStatusId = schedStatusId;
    }
    public String getSchedStatusDesc()
    {
        return schedStatusDesc;
    }
    public void setSchedStatusDesc(String schedStatusDesc)
    {
        this.schedStatusDesc = schedStatusDesc;
    }
    public String getWkCtrId()
    {
        return wkCtrId;
    }
    public void setWkCtrId(String wkCtrId)
    {
        this.wkCtrId = wkCtrId;
    }
    public String getWkCtrDesc()
    {
        return wkCtrDesc;
    }
    public void setWkCtrDesc(String wkCtrDesc)
    {
        this.wkCtrDesc = wkCtrDesc;
    }
    public String getPmNo()
    {
        return pmNo;
    }
    public void setPmNo(String pmNo)
    {
        this.pmNo = pmNo;
    }
    public String getSelectedWkorId()
    {
        return selectedWkorId;
    }
    public void setSelectedWkorId(String selectedWkorId)
    {
        this.selectedWkorId = selectedWkorId;
    }
    public String getSelectedPmType()
    {
        return selectedPmType;
    }
    public void setSelectedPmType(String selectedPmType)
    {
        this.selectedPmType = selectedPmType;
    }
    public String getSelectedWoType()
    {
        return selectedWoType;
    }
    public void setSelectedWoType(String selectedWoType)
    {
        this.selectedWoType = selectedWoType;
    }
    public String getEqCtgTypeId()
    {
        return eqCtgTypeId;
    }
    public void setEqCtgTypeId(String eqCtgTypeId)
    {
        this.eqCtgTypeId = eqCtgTypeId;
    }
    public String getEqCtgTypeDesc()
    {
        return eqCtgTypeDesc;
    }
    public void setEqCtgTypeDesc(String eqCtgTypeDesc)
    {
        this.eqCtgTypeDesc = eqCtgTypeDesc;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
}
