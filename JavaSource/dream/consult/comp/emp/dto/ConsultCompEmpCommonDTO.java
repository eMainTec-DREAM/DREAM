package dream.consult.comp.emp.dto;

import common.bean.BaseDTO;

/**
 * ��� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class ConsultCompEmpCommonDTO extends BaseDTO
{
	
	/** ȸ���ڵ� */
	private String compNo                  = "";
	/** ���Id */
	private String empId                   = "";
	
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		   = "";
	/** Filter-ȸ��� */
    private String filterCompDesc           = "";
	/** Filter-�����ȣ */
	private String filterEmpNo 			   = "";
	/** Filter-����� */
	private String filterEmpName           = "";
	/** Filter-�μ�Id */
	private String filterDeptId 		   = "";
	/** Filter-�μ��� */
	private String filterDeptDesc 		   = "";
	/** Filter-�ٹ����� */
	private String filterIsJoin            = "";
	private String filterIsJoinDesc        = "";

    public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
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
