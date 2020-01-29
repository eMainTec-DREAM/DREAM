package dream.work.rpt.org.bd.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������ ����м� dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptOrgBdCommonDTO extends BaseDTO
{
	/** KEY �μ� */
	private String deptId    		    = "";
	                                    
    /** ����-����(����) */              
    private String filterStartDate      = "";
    /** ����-����(��) */                
    private String filterEndDate        = "";
    /** ����-������ġ */                
    private String filterEqLocId        = "";
    /** ����-������ġ desc */           
    private String filterEqLocDesc      = "";
    /** ����-�μ� */                    
    private String filterDeptId    	    = "";
    /** ����-�μ� desc */               
    private String filterDeptDesc       = "";
    /** ����-�������� */                
    private String filterEqCtgId        = "";
    /** ����-�������� desc */           
    private String filterEqCtgDesc      = "";
    /** ����-���� ID */                 
    private String filterPlantId        = "";
    /** ����-���� DESC */               
    private String filterPlantDesc      = "";
    
    /** ����-�������� ID */
    private String filterDeptCategId	= "";
    /** ����-�������� DESC */
    private String filterDeptCategDesc  = "";
    
    public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getFilterDeptCategId() {
		return filterDeptCategId;
	}
	public void setFilterDeptCategId(String filterDeptCategId) {
		this.filterDeptCategId = filterDeptCategId;
	}
	public String getFilterDeptCategDesc() {
		return filterDeptCategDesc;
	}
	public void setFilterDeptCategDesc(String filterDeptCategDesc) {
		this.filterDeptCategDesc = filterDeptCategDesc;
	}
	public String getFilterPlantId()
    {
        return filterPlantId;
    }
    public void setFilterPlantId(String filterPlantId)
    {
        this.filterPlantId = filterPlantId;
    }
    public String getFilterPlantDesc()
    {
        return filterPlantDesc;
    }
    public void setFilterPlantDesc(String filterPlantDesc)
    {
        this.filterPlantDesc = filterPlantDesc;
    }
    public String getFilterStartDate()
    {
        return filterStartDate;
    }
    public void setFilterStartDate(String filterStartDate)
    {
        this.filterStartDate = CommonUtil.convertDate(filterStartDate);
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = CommonUtil.convertDate(filterEndDate);
    }
    public String getFilterEqLocId()
    {
        return filterEqLocId;
    }
    public void setFilterEqLocId(String filterEqLocId)
    {
        this.filterEqLocId = filterEqLocId;
    }
    public String getFilterEqLocDesc()
    {
        return filterEqLocDesc;
    }
    public void setFilterEqLocDesc(String filterEqLocDesc)
    {
        this.filterEqLocDesc = filterEqLocDesc;
    }
    public String getFilterDeptId()
    {
        return filterDeptId;
    }
    public void setFilterDeptId(String filterDeptId)
    {
        this.filterDeptId = filterDeptId;
    }
    public String getFilterDeptDesc()
    {
        return filterDeptDesc;
    }
    public void setFilterDeptDesc(String filterDeptDesc)
    {
        this.filterDeptDesc = filterDeptDesc;
    }
    public String getFilterEqCtgId()
    {
        return filterEqCtgId;
    }
    public void setFilterEqCtgId(String filterEqCtgId)
    {
        this.filterEqCtgId = filterEqCtgId;
    }
    public String getFilterEqCtgDesc()
    {
        return filterEqCtgDesc;
    }
    public void setFilterEqCtgDesc(String filterEqCtgDesc)
    {
        this.filterEqCtgDesc = filterEqCtgDesc;
    }
	
}