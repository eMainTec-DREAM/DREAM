package dream.asset.rpt.lcc.equip.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 고장TOP(설비) dto
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 */
public class AssetRptLccEquipCommonDTO extends BaseDTO
{
    /** 일자(시작) */
    private String filterStartDate    	= "";
    /** 일자(끝) */
    private String filterEndDate    	= "";
    /** 설비위치 */
    private String filterEqLocId    	= "";
    /** 설비위치 desc */
    private String filterEqLocDesc    	= "";
    /** 부서 */
    private String filterDeptId    		= "";
    /** 부서 desc */
    private String filterDeptDesc    	= "";
    /** 설비종류 */
    private String filterEqCtgId    	= "";
    /** 설비종류 desc */
    private String filterEqCtgDesc    	= "";
    /** 필터-공장 ID */
    private String filterPlantId        = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc      = "";
    /** 사용부서 */
    private String filterUsageDept      = "";
    /** 사용부서명 */
    private String filterUsageDeptDesc  = "";
    /** 필터-관리자(정) ID */
    private String filterMainMngId      = "";
    /** 필터-관리자(정) DESC */
    private String filterMainMngName    = "";
    /** 필터-관리자(부) ID */
    private String filterSubMngId       = "";
    /** 필터-관리자(부) DESC */
    private String filterSubMngName     = "";
    /** 필터-설비번호 */
    private String filterItemNo	        = "";
    /** 필터-설비 ID */
    private String filterEquipId        = "";
    /** 필터-설비 DESC */
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