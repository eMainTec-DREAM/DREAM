package mobile.dream.org.emp.dto;

import common.bean.BaseDTO;

/**
 * 사원검색 팝업 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 */
public class OrgEmpLovListDTO extends BaseDTO
{
    /** Search Code */
    private String empNo      = "";
    /** Search Description */
    private String empName    = "";
    /** 부서id */
    private String deptId      = "";
    /** 부서명 */
    private String deptDesc   = "";
    
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
