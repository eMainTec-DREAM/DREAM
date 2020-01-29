package dream.consult.comp.dept.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 보전부서 - 상세 DTO
 * @author  hyosung
 * @version $Id: $
 * @since   1.0
 *
 */
public class ConsultCompDeptDetailDTO extends BaseDTO
{
    /** 부서ID */
    private String deptId 			= "";
	
    
    
    /** 회사코드 */
	private String compNo 			= "";
	/** 회사명  */
	private String compDesc         = "";
    
	/** 부서코드 */
	private String deptNo 			= "";
	/** 부서명 */
	private String description 		= "";
	
    /** 상위부서Id */
	private String pdeptId			= "";
	/** 상위부서명 */
	private String pdeptDesc		= "";
	/** 사용여부 */
    private String isUse            = "";
    private String isUseDesc        = "";
    /** 조회순서 */
    private String ordNo            = "";
    /** 보전부서여부 */
    private String isMaint    = "";
    private String isMaintDesc    = "";
	
    
	public String getCompDesc()
    {
        return compDesc;
    }
    public void setCompDesc(String compDesc)
    {
        this.compDesc = compDesc;
    }
  
    public String getIsUseDesc()
    {
        return isUseDesc;
    }
    public void setIsUseDesc(String isUseDesc)
    {
        this.isUseDesc = isUseDesc;
    }
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getDeptNo() 
	{
		return deptNo;
	}
	public void setDeptNo(String deptNo) 
	{
		this.deptNo = deptNo;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getDeptId()
    {
        return deptId;
    }
    public void setDeptId(String deptId)
    {
        this.deptId = deptId;
    }
    public String getPdeptId()
    {
        return pdeptId;
    }
    public void setPdeptId(String pdeptId)
    {
        this.pdeptId = pdeptId;
    }
    public String getPdeptDesc() 
	{
		return pdeptDesc;
	}
	public void setPdeptDesc(String pdeptDesc) 
	{
		this.pdeptDesc = pdeptDesc;
	}
	public String getIsUse() 
	{
		return isUse;
	}
	public void setIsUse(String isUse) 
	{
		this.isUse = isUse;
	}
	public String getOrdNo() 
	{
		return ordNo;
	}
	public void setOrdNo(String ordNo) 
	{
		this.ordNo = CommonUtil.convertMoney(ordNo);
	}
    public String getIsMaint()
    {
        return isMaint;
    }
    public void setIsMaint(String isMaint)
    {
        this.isMaint = isMaint;
    }
    public String getIsMaintDesc()
    {
        return isMaintDesc;
    }
    public void setIsMaintDesc(String isMaintDesc)
    {
        this.isMaintDesc = isMaintDesc;
    }
    
    

	
}
