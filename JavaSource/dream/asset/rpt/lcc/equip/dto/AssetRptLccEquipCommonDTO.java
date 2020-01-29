package dream.asset.rpt.lcc.equip.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ����TOP(����) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccEquipCommonDTO extends BaseDTO
{
    /** ����(����) */
    private String filterStartDate    	= "";
    /** ����(��) */
    private String filterEndDate    	= "";
    /** ������ġ */
    private String filterEqLocId    	= "";
    /** ������ġ desc */
    private String filterEqLocDesc    	= "";
    /** �μ� */
    private String filterDeptId    		= "";
    /** �μ� desc */
    private String filterDeptDesc    	= "";
    /** �������� */
    private String filterEqCtgId    	= "";
    /** �������� desc */
    private String filterEqCtgDesc    	= "";
    /** ����-���� ID */
    private String filterPlantId        = "";
    /** ����-���� DESC */
    private String filterPlantDesc      = "";
    /** ���μ� */
    private String filterUsageDept      = "";
    /** ���μ��� */
    private String filterUsageDeptDesc  = "";
    /** ����-������(��) ID */
    private String filterMainMngId      = "";
    /** ����-������(��) DESC */
    private String filterMainMngName    = "";
    /** ����-������(��) ID */
    private String filterSubMngId       = "";
    /** ����-������(��) DESC */
    private String filterSubMngName     = "";
    /** ����-�����ȣ */
    private String filterItemNo	        = "";
    /** ����-���� ID */
    private String filterEquipId        = "";
    /** ����-���� DESC */
    private String filterEquipDesc      = "";
    
    
    public String getFilterMainMngId() {
		return filterMainMngId;
	}
	public String getFilterItemNo() {
		return filterItemNo;
	}
	public void setFilterItemNo(String filterItemNo) {
		this.filterItemNo = filterItemNo;
	}
	public String getFilterEquipId() {
		return filterEquipId;
	}
	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}
	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}
	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}
	public void setFilterMainMngId(String filterMainMngId) {
		this.filterMainMngId = filterMainMngId;
	}
	public String getFilterMainMngName() {
		return filterMainMngName;
	}
	public void setFilterMainMngName(String filterMainMngName) {
		this.filterMainMngName = filterMainMngName;
	}
	public String getFilterSubMngId() {
		return filterSubMngId;
	}
	public void setFilterSubMngId(String filterSubMngId) {
		this.filterSubMngId = filterSubMngId;
	}
	public String getFilterSubMngName() {
		return filterSubMngName;
	}
	public void setFilterSubMngName(String filterSubMngName) {
		this.filterSubMngName = filterSubMngName;
	}
	public String getFilterUsageDept()
    {
        return filterUsageDept;
    }
    public void setFilterUsageDept(String filterUsageDept)
    {
        this.filterUsageDept = filterUsageDept;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
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