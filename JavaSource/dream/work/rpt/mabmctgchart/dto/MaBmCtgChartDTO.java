package dream.work.rpt.mabmctgchart.dto;

import common.bean.BaseDTO;

/**
 * 설비종류별고장분석 DTO
 * @author  kim21017
 * @version $Id: MaBmCtgChartDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaBmCtgChartDTO extends BaseDTO
{
	/** 필터-부서아이디 */
	private String filterDeptDesc 		= "";
	/** 필터-부서명 */
	private String filterDeptId 		= "";
	/** 필터-설비사용부서아이디 */
	private String filterUsageDeptDesc 	= "";
	/** 필터-설비사용부서명 */
	private String filterUsageDeptId 	= "";
	/** 필터-년 */
	private String filterYyyy 			= "";
	/** 위치id - 필터 */
	private String filterEqLocId		= "";
	/** 위치명 - 필터 */
	private String filterEqLocDesc		= "";
	/** 종류id - 필터 */
	private String filterEqCtgId		= "";
	/** 종류명 - 필터 */
	private String filterEqCtgDesc		= "";
	/** 필터-관리자(정)id */
	private String filterMainMngId		= "";
	/** 필터-관리자(정)명 */
	private String filterMainMngName	= "";
	/** 필터-관리자(부)id */
	private String filterSubMngId		= "";
	/** 필터-관리자(부)명 */
	private String filterSubMngName		= "";
	/** 필터-법정설비여부 */
	private String filterIsLawEq		= "";
    /** 설비Id */
    private String filterEquipId        = "";
    /** 설비명 */
    private String filterEquipDesc      = "";
    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
	/** 클릭 시 년*/
	private String yyyy 				= "";
	/** 클릭 시 라인*/
	private String eqlocId 				= "";
	/** 클릭 시 라인desc*/
    private String eqlocDesc              = "";
	/** 클릭 시 종류*/
	private String eqctgId 				= "";
	/** 클릭 시 종류desc*/
    private String eqctgDesc              = "";
	/** 클릭 시 부서*/
	private String deptId 				= "";
	/** 클릭 시 설비사용부서*/
	private String usageDeptId 			= "";
	/** 클릭 시 설비사용부서desc*/
	private String usageDeptDesc        = "";
	
	
	public String getUsageDeptId()
    {
        return usageDeptId;
    }
    public void setUsageDeptId(String usageDeptId)
    {
        this.usageDeptId = usageDeptId;
    }
    public String getUsageDeptDesc()
    {
        return usageDeptDesc;
    }
    public void setUsageDeptDesc(String usageDeptDesc)
    {
        this.usageDeptDesc = usageDeptDesc;
    }
    public String getFilterUsageDeptDesc()
    {
        return filterUsageDeptDesc;
    }
    public void setFilterUsageDeptDesc(String filterUsageDeptDesc)
    {
        this.filterUsageDeptDesc = filterUsageDeptDesc;
    }
    public String getFilterUsageDeptId()
    {
        return filterUsageDeptId;
    }
    public void setFilterUsageDeptId(String filterUsageDeptId)
    {
        this.filterUsageDeptId = filterUsageDeptId;
    }
    public String getEqlocDesc()
    {
        return eqlocDesc;
    }
    public void setEqlocDesc(String eqlocDesc)
    {
        this.eqlocDesc = eqlocDesc;
    }
    public String getEqctgDesc()
    {
        return eqctgDesc;
    }
    public void setEqctgDesc(String eqctgDesc)
    {
        this.eqctgDesc = eqctgDesc;
    }
    public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
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
	public String getFilterMainMngId() {
		return filterMainMngId;
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
	public String getFilterIsLawEq() {
		return filterIsLawEq;
	}
	public void setFilterIsLawEq(String filterIsLawEq) {
		this.filterIsLawEq = filterIsLawEq;
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
	public String getEqlocId() {
		return eqlocId;
	}
	public void setEqlocId(String eqlocId) {
		this.eqlocId = eqlocId;
	}
	public String getEqctgId() {
		return eqctgId;
	}
	public void setEqctgId(String eqctgId) {
		this.eqctgId = eqctgId;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterYyyy() {
		return filterYyyy;
	}
	public void setFilterYyyy(String filterYyyy) {
		this.filterYyyy = filterYyyy;
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
