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
    /** ��� ID */
    private String pmInsDListId            = "";
    /** SCHED ID */
    private String pmInsDSchedId            = "";
    /** ��� NO */
    private String pmInsDListNo            = "";
    
    /** �۾����ID */
    private String wkOrId               = "";
    /** �۾��� */
    private String description          = "";
    /** �۾���ȣ */
    private String woNo                 = "";
    /** �۾����� ������ */
    private String startDate            = "";
    /** �۾����� ���۽ð� */
    private String startTime            = "";
    /** �۾����� ������ */
    private String endDate              = "";
    /** �۾����� ����ð� */
    private String endTime              = "";
    /** �μ�id */
    private String deptId               = "";
    /** �μ��� */
    private String deptDesc             = "";
    /** ����id */
    private String equipId              = "";
    /** ����id */
    private String equipNo              = "";
    /** ����� */
    private String equipDesc            = "";
    /** �����id */
    private String empId                = "";
    /** ����ڸ� */
    private String empDesc              = "";
    /** ��ġid */
    private String eqLocId              = "";
    /** ��ġ�� */
    private String eqLocDesc            = "";
    /** ����id */
    private String eqCtgId              = "";
    /** ������ */
    private String eqCtgDesc            = "";
    /** �۾����� */
    private String woTypeId             = "";
    /** �۾����� �� */
    private String woTypeDesc           = "";
    /** �۾����� */
    private String pmTypeId             = "";
    /** �۾����� �� */
    private String pmTypeDesc           = "";
    /** �������񿩺� ID*/
    private String isLawEqId            = "";
    /** �������񿩺� */
    private String isLawEq              = "";
    /** ������(��)id */
    private String mainMngId            = "";
    /** ������(��)�� */
    private String mainMngName          = "";
    /** ������(��)id */
    private String subMngId             = "";
    /** ������(��)�� */
    private String subMngName           = "";
    /** �����ڵ� */
    private String schedStatusId        = "";
    /** �����ڵ�� */
    private String schedStatusDesc      = "";
    /** ������ID */
    private String shiftTypeId          = "";
    /** ������ID�� */
    private String shiftTypeDesc        = "";
    /** �۾��׷�Id */
    private String wkCtrId              = "";
    /** �۾��׷�� */
    private String wkCtrDesc            = "";
    /** �����۾�ID */
    private String pmId                 = "";
    /** �����۾�# */
    private String pmNo                 = "";
    /** ���õ� wkorId */
    private String selectedWkorId       = "";
    /** ���õ� �۾����� */
    private String selectedPmType       = "";
    /** ���õ� �۾����� */
    private String selectedWoType       = "";
    /** ��������Id */
    private String eqCtgTypeId          = "";
    /** �������� */
    private String eqCtgTypeDesc        = "";
    /** ��� */
    private String remark               = "";
    
    /** �۾��ð�(��) */
    private String workTime             = "";
    /** Ȯ���ڸ� */
    private String closeBy              = "";
    /** Ȯ���� ID */
    private String closeById            = "";
    /** Ȯ������ */
    private String closeDate            = "";
    /** �����ð� */
    private String operatingTime        = "";
    /** �����ð� */
    private String measureTime          = "";
    /** ��뷮 */
    private String usage                = "";
    /** �۾����� */
    private String wkorDate             = "";
    /** ȸ�� */
    private String workNumber           = "";
    /** ������ǰ ID */
    private String productId            = "";
    /** ������ǰ DESC */
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
