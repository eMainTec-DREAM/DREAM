package dream.org.emp.dto;

import common.bean.BaseDTO;

/**
 * ��� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaEmpCommonDTO extends BaseDTO
{
	
	/** ȸ���ڵ� */
	private String compNo                  = "";
	/** ���Id */
	private String empId                   = "";
	
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		   = "";
	/** Filter-�����ȣ */
	private String filterEmpNo 			   = "";
	/** Filter-����� */
	private String filterEmpName           = "";
	/** Filter-�μ�Id */
	private String filterDeptId 		   = "";
	/** Filter-�μ�Id */
	private String filterDeptDesc 		   = "";
	/** Filter-�ٹ����� */
	private String filterIsJoin            = "";
	private String filterIsJoinDesc        = "";
	/** Filter- �۾��׷� */
	private String filterWkCtrDesc         = "";
	private String filterWkCtrId           = "";

    /** ���� */
	private String filterPlantId 		   = "";
	private String filterPlantDesc 		   = "";

    /** �������� */
    private String filterIsDirectDesc       = "";
    
    
    public String getFilterIsDirectDesc() {
		return filterIsDirectDesc;
	}
	public void setFilterIsDirectDesc(String filterIsDirectDesc) {
		this.filterIsDirectDesc = filterIsDirectDesc;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
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
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterIsJoinDesc()
    {
        return filterIsJoinDesc;
    }
    public void setFilterIsJoinDesc(String filterIsJoinDesc)
    {
        this.filterIsJoinDesc = filterIsJoinDesc;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getCompNo()
    {
        return compNo;
    }
    public void setCompNo(String compNo)
    {
        this.compNo = compNo;
    }
    public String getEmpId()
    {
        return empId;
    }
    public void setEmpId(String empId)
    {
        this.empId = empId;
        super.setAuditKey(empId);
    }
    public String getFilterCompNo()
    {
        return filterCompNo;
    }
    public void setFilterCompNo(String filterCompNo)
    {
        this.filterCompNo = filterCompNo;
    }
    public String getFilterEmpNo()
    {
        return filterEmpNo;
    }
    public void setFilterEmpNo(String filterEmpNo)
    {
        this.filterEmpNo = filterEmpNo;
    }
    public String getFilterEmpName()
    {
        return filterEmpName;
    }
    public void setFilterEmpName(String filterEmpName)
    {
        this.filterEmpName = filterEmpName;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterIsJoin()
    {
        return filterIsJoin;
    }
    public void setFilterIsJoin(String filterIsJoin)
    {
        this.filterIsJoin = filterIsJoin;
    }
}
