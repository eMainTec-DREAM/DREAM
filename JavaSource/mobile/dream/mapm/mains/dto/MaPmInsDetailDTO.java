package mobile.dream.mapm.mains.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  jung7126
 * @version $Id: MaPmInsDetailDTO.java,v 1.0 2015/12/02 08:44:16 jung7126 Exp $
 * @since   1.0
 *
 */
public class MaPmInsDetailDTO extends BaseDTO
{
    /** ��� */
    private String remark = "";
    /** ���࿩�� */
    private String isActive = "";
    
    private String initWrkDate = ""; //����ڰ� �Է��� ����
    private String lastSchDate = ""; //������ �������� ����-�ָ�������, ����ƫ ������ �� ��¥�� �����ؾ� ��.
    private String nextSchDate = ""; //������ �������� ����-�ָ�������..
    
    public String getNextSchDate() {
		return nextSchDate;
	}
	public void setNextSchDate(String nextSchDate) {
		this.nextSchDate = nextSchDate;
	}
	/** ������/��ð��� �ڵ����� */
    private String woResBef = "";
    /** ��뷮 */
    private String usage = "";
    /** �ֱ�-��,��,��, �� */
    private String periodType = "";
    
    private String periodTypeDesc   = "";
    /** �ֱ�-�Ⱓ */
    private String cycle = "";
    /** ��뷮/�ð� ����(T:�ð�, U:��뷮) */
    private String scheduleType = "";
    
    private String scheduleTypeDesc = "";
    /** �۾����� */
    private String pmType = "";
    
    private String pmTypeDesc   = "";
    /** �μ� */
    private String deptId = "";
    
    private String deptDesc = "";
    /** �����ڵ� */
    private String equipId = "";
    
    private String equipDesc    = "";
    /** PM �� */
    private String description = "";
    /** PM ��ȣ */
    private String pmNo = "";
    /** PM ID */
    private String pmId = "";
    /** ȸ���ڵ� */
    private String compNo = "";
    
    /** �۾�����  */
    private String pmCateg  = "";
    /** �۾������� */
    private String pmCategDesc  = "";
    /** ���ID */
    private String empId        = "";
    /** ����� */
    private String empName      = "";
    /** �۾����� */
    private String woType       = "";
    /** �۾������� */
    private String woTypeDesc   = "";
    /** ��ġID */
	private String eqLocId 				= "";
	/** ��ġ�� */
	private String eqLocDesc 			= "";
	/** ��ġ�� */
	private String eqLocType 			= "";
	/** �۾��׷�Id */
	private String wkCtrId	 			= "";
	/** �۾��׷�� */
	private String wkCtrDesc 			= "";
    
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
        this.woResBef = woResBef;
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

}
