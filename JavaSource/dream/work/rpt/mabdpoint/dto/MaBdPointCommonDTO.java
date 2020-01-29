package dream.work.rpt.mabdpoint.dto;

import common.bean.BaseDTO;

/**
 * 이상점검조치 공통 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaBdPointCommonDTO extends BaseDTO
{
	/** ID */
	private String woNgPointId             = "";
	/** 필터-부서Id */
	private String filterDeptId            = "";
	/** 필터-부서명 */
	private String filterDeptDesc          = "";
	/** 필터-점검일자 From */
	private String filterStartDate         = "";
	/** 필터-점검일자 To */
	private String filterEndDate           = "";
	/** 필터-점검자Id */
	private String filterEmpId             = "";
	/** 필터-점검자명 */
	private String filterEmpName           = "";
	/** 필터-조치결과*/
	private String filterRepStatus         = "";
	/** 필터-조치결과명 */
	private String filterRepStatusDesc     = "";
	/** 필터-위치Id */
	private String filterEqLocId           = "";
	/** 필터-위치명 */
	private String filterEqLocDesc         = "";
	/** 필터-종류Id */
	private String filterEqCtgId           = "";
	/** 필터-종류명 */
	private String filterEqCtgDesc         = "";
	/** Filter-설비유형Id */
	private String filterEqCtgTypeId 	   = "";
	/** Filter-설비유형 */
	private String filterEqCtgTypeDesc 	   = "";
	/** Filter-워크그룹Id */
	private String filterWkCtrId	 	   = "";
	/** Filter-워크그룹 */
	private String filterWkCtrDesc 		   = "";
	
	/** Filter-작업형태Id */
	private String filterPmTypeId	 	   = "";
	/** Filter-작업형태 */
	private String filterPmTypeDesc 		   = "";
	
	/** pmType */
	private String pmType 		   = "";
	/** woType */
	private String woType 		   = "";
	/** pmiType */
	private String pmiType 		   = "";
	
	/** wkorId */
	private String wkorId 		   = "";
	
	/** filter-공장 Id */
	private String filterPlantId 	   = "";
	/** filter-공장명 */
	private String filterPlantDesc 	   = "";
	
	/** 필터 사용부서 */
    private String filterUsageDeptId        = "";
    /** 필터 사용부서명 */
    private String filterUsageDeptDesc      = "";  
	
	private String reloadRow 	   = "";
	/** filter - 설비번호 */
	private String filterItemNo 	= "";
	
	public String getFilterItemNo() {
		return filterItemNo;
	}
	public void setFilterItemNo(String filterItemNo) {
		this.filterItemNo = filterItemNo;
	}
	public String getFilterUsageDeptId()
    {
        return filterUsageDeptId;
    }
    public void setFilterUsageDeptId(String filterUsageDeptId)
    {
        this.filterUsageDeptId = filterUsageDeptId;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getReloadRow() {
		return reloadRow;
	}
	public void setReloadRow(String reloadRow) {
		this.reloadRow = reloadRow;
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
	public String getFilterPmTypeId()
    {
        return filterPmTypeId;
    }
    public void setFilterPmTypeId(String filterPmTypeId)
    {
        this.filterPmTypeId = filterPmTypeId;
    }
    public String getFilterPmTypeDesc()
    {
        return filterPmTypeDesc;
    }
    public void setFilterPmTypeDesc(String filterPmTypeDesc)
    {
        this.filterPmTypeDesc = filterPmTypeDesc;
    }
    public String getWkorId()
    {
        return wkorId;
    }
    public void setWkorId(String wkorId)
    {
        this.wkorId = wkorId;
    }
    public String getPmiType()
    {
        return pmiType;
    }
    public void setPmiType(String pmiType)
    {
        this.pmiType = pmiType;
    }
    public String getPmType()
    {
        return pmType;
    }
    public void setPmType(String pmType)
    {
        this.pmType = pmType;
    }
    public String getWoType()
    {
        return woType;
    }
    public void setWoType(String woType)
    {
        this.woType = woType;
    }
    public String getFilterWkCtrId() {
		return filterWkCtrId;
	}
	public void setFilterWkCtrId(String filterWkCtrId) {
		this.filterWkCtrId = filterWkCtrId;
	}
	public String getFilterWkCtrDesc() {
		return filterWkCtrDesc;
	}
	public void setFilterWkCtrDesc(String filterWkCtrDesc) {
		this.filterWkCtrDesc = filterWkCtrDesc;
	}
	public String getFilterEqCtgTypeId() {
		return filterEqCtgTypeId;
	}
	public void setFilterEqCtgTypeId(String filterEqCtgTypeId) {
		this.filterEqCtgTypeId = filterEqCtgTypeId;
	}
	public String getFilterEqCtgTypeDesc() {
		return filterEqCtgTypeDesc;
	}
	public void setFilterEqCtgTypeDesc(String filterEqCtgTypeDesc) {
		this.filterEqCtgTypeDesc = filterEqCtgTypeDesc;
	}
	public String getWoNgPointId() {
		return woNgPointId;
	}
	public void setWoNgPointId(String woNgPointId) {
		this.woNgPointId = woNgPointId;
		super.setAuditKey(woNgPointId);
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterStartDate() {
		return filterStartDate;
	}
	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
	}
	public String getFilterEmpId() {
		return filterEmpId;
	}
	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}
	public String getFilterEmpName() {
		return filterEmpName;
	}
	public void setFilterEmpName(String filterEmpName) {
		this.filterEmpName = filterEmpName;
	}
	public String getFilterRepStatus() {
		return filterRepStatus;
	}
	public void setFilterRepStatus(String filterRepStatus) {
		this.filterRepStatus = filterRepStatus;
	}
	public String getFilterRepStatusDesc() {
		return filterRepStatusDesc;
	}
	public void setFilterRepStatusDesc(String filterRepStatusDesc) {
		this.filterRepStatusDesc = filterRepStatusDesc;
	}
	public String getFilterEqLocId() {
		return filterEqLocId;
	}
	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}
	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}
	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
}
