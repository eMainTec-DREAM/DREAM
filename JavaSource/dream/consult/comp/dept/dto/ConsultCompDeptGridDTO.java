package dream.consult.comp.dept.dto;

import common.bean.BaseDTO;

/**
 * �����μ� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class ConsultCompDeptGridDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** �μ�Id */
	private String deptId 			= "";
	/** �������� */
	private String isDelCheck		= "";
	
	public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
	}
	public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getIsDelCheck() 
	{
		return isDelCheck;
	}
	public void setIsDelCheck(String isDelCheck) 
	{
		this.isDelCheck = isDelCheck;
	}
	
	
}
