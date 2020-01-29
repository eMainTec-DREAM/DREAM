package dream.org.dept.dto;

import common.bean.BaseDTO;

/**
 * �����μ� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaDeptCommonDTO extends BaseDTO
{
	
	/** ȸ���ڵ� */
	private String compNo                   = "";
	/** �μ�Id */
	private String deptId                   = "";
	
	/** Filter-ȸ���ڵ� */
	private String filterCompNo 		    = "";
	/** ���� �μ��ڵ� */
	private String filterDeptNo            	= "";
	/** ���� �μ��� */
	private String filterDescription 		= "";
	/** ��뿩�� */
	private String filterIsUse              = "";
	private String filterIsUseDesc          = "";
	/** �����μ��ڵ� */
	private String filterPdeptId            = "";
	private String filterPdeptDesc          = "";
	/** ������ detail�� �ѱ� �μ�id */
	private String detailPdeptId            = "";
	/** ���� detail�� �ѱ� �μ��� */
	private String detailPdeptDesc          = "";
	/** �����μ����� */
	private String filterIsMaint            = "";
	private String filterIsMaintDesc        = "";

    /** ���� */
	private String filterPlantId 			= "";
	private String filterPlantDesc 			= "";
	
	/** �������ε�� set�� exceltab_no  */
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
