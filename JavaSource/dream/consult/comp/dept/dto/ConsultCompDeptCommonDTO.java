package dream.consult.comp.dept.dto;

import common.bean.BaseDTO;

/**
 * 보전부서 공통 DTO
 * @author  hyosung
 * @version $Id: $
 * @since   1.0
 * 
 */
public class ConsultCompDeptCommonDTO extends BaseDTO
{
	
	/** 부서Id */
	private String deptId                   = "";

	/** Filter 회사명 */
	private String filterCompDesc           = "";
	
	/** Filter-회사코드 */
	private String filterCompNo 		    = "";
	/** 필터 부서코드 */
	private String filterDeptNo            	= "";
	/** 필터 부서명 */
	private String filterDescription 		= "";
	/** 사용여부 */
	private String filterIsUse              = "";
	private String filterIsUseDesc          = "";
	/** 보전부서여부 */
	private String filterIsMaint    = "";
	private String filterIsMaintDesc    = "";
	

	public String getFilterCompDesc()
    {
        return filterCompDesc;
    }
    public void setFilterCompDesc(String filterCompDesc)
    {
        this.filterCompDesc = filterCompDesc;
    }
	
	
	public String getFilterIsUseDesc()
    {
        return filterIsUseDesc;
    }
    public void setFilterIsUseDesc(String filterIsUseDesc)
    {
        this.filterIsUseDesc = filterIsUseDesc;
    }
    public String getFilterDeptNo()
    {
        return filterDeptNo;
    }
    public void setFilterDeptNo(String filterDeptNo)
    {
        this.filterDeptNo = filterDeptNo;
    }
  
	public String getFilterCompNo() 
	{
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) 
	{
		this.filterCompNo = filterCompNo;
	}
	public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getFilterDescription() 
	{
		return filterDescription;
	}
	public void setFilterDescription(String filterDescription) 
	{
		this.filterDescription = filterDescription;
	}
	public String getFilterIsUse() 
	{
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) 
	{
		this.filterIsUse = filterIsUse;
	}
    public String getFilterIsMaint()
    {
        return filterIsMaint;
    }
    public void setFilterIsMaint(String filterIsMaint)
    {
        this.filterIsMaint = filterIsMaint;
    }
    public String getFilterIsMaintDesc()
    {
        return filterIsMaintDesc;
    }
    public void setFilterIsMaintDesc(String filterIsMaintDesc)
    {
        this.filterIsMaintDesc = filterIsMaintDesc;
    }
    
	
	/*public String getFilterPdeptId()
    {
        return filterPdeptId;
    }
    public void setFilterPdeptId(String filterPdeptId)
    {
        this.filterPdeptId = filterPdeptId;
    }
	public String getFilterPdeptDesc() 
	{
		return filterPdeptDesc;
	}
	public void setFilterPdeptDesc(String filterPdeptDesc) 
	{
		this.filterPdeptDesc = filterPdeptDesc;
	}*/

}
