package dream.org.emp.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 사원 - Grid DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaEmpGridDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 사원번호 */
	private String empId 			= "";
	/** 삭제여부 */
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
