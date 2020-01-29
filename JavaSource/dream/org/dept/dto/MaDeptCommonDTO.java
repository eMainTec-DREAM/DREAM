package dream.org.dept.dto;

import common.bean.BaseDTO;

/**
 * 보전부서 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaDeptCommonDTO extends BaseDTO
{
	
	/** 회사코드 */
	private String compNo                   = "";
	/** 부서Id */
	private String deptId                   = "";
	
	/** Filter-회사코드 */
	private String filterCompNo 		    = "";
	/** 필터 부서코드 */
	private String filterDeptNo            	= "";
	/** 필터 부서명 */
	private String filterDescription 		= "";
	/** 사용여부 */
	private String filterIsUse              = "";
	private String filterIsUseDesc          = "";
	/** 상위부서코드 */
	private String filterPdeptId            = "";
	private String filterPdeptDesc          = "";
	/** 생성시 detail에 넘길 부서id */
	private String detailPdeptId            = "";
	/** 성시 detail에 넘길 부서명 */
	private String detailPdeptDesc          = "";
	/** 보전부서여부 */
	private String filterIsMaint            = "";
	private String filterIsMaintDesc        = "";

    /** 공장 */
	private String filterPlantId 			= "";
	private String filterPlantDesc 			= "";
	
	/** 엑셀업로드시 set할 exceltab_no  */
	private String exceltabNo				= "";
	
	public String getExceltabNo() {
		return exceltabNo;
	}
	public void setExceltabNo(String exceltabNo) {
		this.exceltabNo = exceltabNo;
	}
	public String getDetailPdeptId() {
		return detailPdeptId;
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
	public void setDetailPdeptId(String detailPdeptId) {
		this.detailPdeptId = detailPdeptId;
	}
	public String getDetailPdeptDesc() {
		return detailPdeptDesc;
	}
	public void setDetailPdeptDesc(String detailPdeptDesc) {
		this.detailPdeptDesc = detailPdeptDesc;
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
    public String getCompNo() 
	{
		return compNo;
	}
	public void setCompNo(String compNo) 
	{
		this.compNo = compNo;
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
        super.setAuditKey(deptId);
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
	public String getFilterPdeptId()
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

}
