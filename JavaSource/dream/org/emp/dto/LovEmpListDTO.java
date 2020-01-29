package dream.org.emp.dto;

import common.bean.BaseDTO;

/**
 * ����˻� �˾� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class LovEmpListDTO extends BaseDTO
{
    /** Search Code */
    private String empNo      = "";
    /** Search Description */
    private String empName    = "";
    /** �μ�id */
    private String deptId      = "";
    /** �μ��� */
    private String deptDesc   = "";
    /** Multy Select Y */
    private String multiSelect    = "";
    
    private String extCode1 = "";
    /** ���� ID */
    private String plantId            = "";
    /** ���� DESC */
    private String plantDesc          = "";
    /** �������� ID */
    private String isJoinId            = "";
    /** �������� DESC */
    private String isJoinDesc          = "";
    
	public String getIsJoinId() {
		return isJoinId;
	}
	public void setIsJoinId(String isJoinId) {
		this.isJoinId = isJoinId;
	}
	public String getIsJoinDesc() {
		return isJoinDesc;
	}
	public void setIsJoinDesc(String isJoinDesc) {
		this.isJoinDesc = isJoinDesc;
	}
	public String getPlantId()
    {
        return plantId;
    }
    public void setPlantId(String plantId)
    {
        this.plantId = plantId;
    }
    public String getPlantDesc()
    {
        return plantDesc;
    }
    public void setPlantDesc(String plantDesc)
    {
        this.plantDesc = plantDesc;
    }
    public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
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
	public String getEmpNo()
    {
        return empNo;
    }
    public void setEmpNo(String empNo)
    {
        this.empNo = empNo;
    }
    public String getEmpName()
    {
        return empName;
    }
    public void setEmpName(String empName)
    {
        this.empName = empName;
    }
    
   
}
