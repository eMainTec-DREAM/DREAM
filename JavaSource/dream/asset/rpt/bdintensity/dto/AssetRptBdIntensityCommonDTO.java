package dream.asset.rpt.bdintensity.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 설비별 고장강도율 목록 - 공통 DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptBdIntensityCommonDTO extends BaseDTO
{
	/** 시작일자 */ 
    private String filterStartDate           = "";
    /** 종료일자 */ 
    private String filterEndDate             = "";
    /** 공장 ID */ 
    private String filterPlantId             = "";
    /** 공장명 */ 
    private String filterPlantDesc           = "";
    /** 부서 ID */
    private String filterDeptId				 = "";
    /** 부서명 */
    private String filterDeptDesc			 = "";
    /** 설비종류 ID */
    private String filterEqCtgTypeId		 = "";
    /** 설비종류명 */
    private String filterEqCtgTypeDesc		 = "";
    /** 구분 ID */
    private String filterSeparation		 	 = "";
    /** 구분명 */
    private String filterSeparationDesc		 = "";
    /** Daewoong 구분 ID */
    private String filterDwSeparation		 = "";
    /** Daewoong 구분명 */
    private String filterDwSeparationDesc	 = "";
    
    private String filterEqGrade             = "";
    
    private String filterEqGradeDesc         = "";

    private String filterEquipNo			 = "";
    
    
	public String getFilterEquipNo() {
		return filterEquipNo;
	}
	public void setFilterEquipNo(String filterEquipNo) {
		this.filterEquipNo = filterEquipNo;
	}
	public String getFilterEqGrade()
    {
        return filterEqGrade;
    }
    public void setFilterEqGrade(String filterEqGrade)
    {
        this.filterEqGrade = filterEqGrade;
    }
    public String getFilterEqGradeDesc()
    {
        return filterEqGradeDesc;
    }
    public void setFilterEqGradeDesc(String filterEqGradeDesc)
    {
        this.filterEqGradeDesc = filterEqGradeDesc;
    }
    public String getFilterDeptId() {
		return filterDeptId;
	}
	public String getFilterDwSeparation() {
		return filterDwSeparation;
	}
	public void setFilterDwSeparation(String filterDwSeparation) {
		this.filterDwSeparation = filterDwSeparation;
	}
	public String getFilterDwSeparationDesc() {
		return filterDwSeparationDesc;
	}
	public void setFilterDwSeparationDesc(String filterDwSeparationDesc) {
		this.filterDwSeparationDesc = filterDwSeparationDesc;
	}
	public String getFilterSeparation() {
		return filterSeparation;
	}
	public void setFilterSeparation(String filterSeparation) {
		this.filterSeparation = filterSeparation;
	}
	public String getFilterSeparationDesc() {
		return filterSeparationDesc;
	}
	public void setFilterSeparationDesc(String filterSeparationDesc) {
		this.filterSeparationDesc = filterSeparationDesc;
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
		this.filterStartDate = CommonUtil.convertDate(filterStartDate);
	}
	public String getFilterEndDate() {
		return filterEndDate;
	}
	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = CommonUtil.convertDate(filterEndDate);
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
    
}
