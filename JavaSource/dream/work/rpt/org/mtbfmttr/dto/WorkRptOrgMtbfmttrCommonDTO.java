package dream.work.rpt.org.mtbfmttr.dto;

import common.bean.BaseDTO;

/**
 * 조직별MTBF,MTTR dto
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 */
public class WorkRptOrgMtbfmttrCommonDTO extends BaseDTO
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
    /** 필터-설비 ID */
    private String filterEquipId     	= "";
    /** 필터-설비 NO */
    private String filterEquipNo      	= "";
    /** 필터-설비 DESC */
    private String filterEquipDesc      = "";
    
    /** 필터-조직구분 ID */
    private String filterDeptCategId    = "";
    /** 필터-조직구분 DESC */
    private String filterDeptCategDesc	= "";
    
    
    
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
	public String getFilterEquipId()
    {
        return filterEquipId;
    }
    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }
    public String getFilterEquipNo()
    {
        return filterEquipNo;
    }
    public void setFilterEquipNo(String filterEquipNo)
    {
        this.filterEquipNo = filterEquipNo;
    }
    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }
    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
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
        this.filterStartDate = filterStartDate;
    }
    public String getFilterEndDate()
    {
        return filterEndDate;
    }
    public void setFilterEndDate(String filterEndDate)
    {
        this.filterEndDate = filterEndDate;
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