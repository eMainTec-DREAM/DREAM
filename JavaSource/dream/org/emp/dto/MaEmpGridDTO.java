package dream.org.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ��� - Grid DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaEmpGridDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 			= "";
	/** �����ȣ */
	private String empId 			= "";
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
	public String getEmpId() 
	{
		return empId;
	}
	public void setEmpId(String empId) 
	{
		this.empId = empId;
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
