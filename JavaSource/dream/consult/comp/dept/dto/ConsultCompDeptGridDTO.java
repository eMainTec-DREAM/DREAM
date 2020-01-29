package dream.consult.comp.dept.dto;

import common.bean.BaseDTO;

/**
 * 보전부서 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class ConsultCompDeptGridDTO extends BaseDTO
{
	/** 회사코드 */
	private String compNo 			= "";
	/** 부서Id */
	private String deptId 			= "";
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
