package dream.asset.rpt.bdintensity.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ���� ���尭���� ��� - ���� DTO
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptBdIntensityCommonDTO extends BaseDTO
{
	/** �������� */ 
    private String filterStartDate           = "";
    /** �������� */ 
    private String filterEndDate             = "";
    /** ���� ID */ 
    private String filterPlantId             = "";
    /** ����� */ 
    private String filterPlantDesc           = "";
    /** �μ� ID */
    private String filterDeptId				 = "";
    /** �μ��� */
    private String filterDeptDesc			 = "";
    /** �������� ID */
    private String filterEqCtgTypeId		 = "";
    /** ���������� */
    private String filterEqCtgTypeDesc		 = "";
    /** ���� ID */
    private String filterSeparation		 	 = "";
    /** ���и� */
    private String filterSeparationDesc		 = "";
    /** Daewoong ���� ID */
    private String filterDwSeparation		 = "";
    /** Daewoong ���и� */
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
