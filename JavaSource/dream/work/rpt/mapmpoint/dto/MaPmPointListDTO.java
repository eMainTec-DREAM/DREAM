package dream.work.rpt.mapmpoint.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예방점검내역DTO
 * @author  kim21017
 * @version $Id: MaPmPointListDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class MaPmPointListDTO extends BaseDTO
{
	/** 필터-부서아이디 */
	private String filterDeptDesc 		= "";
	/** 필터-부서명 */
	private String filterDeptId 		= "";
	/** 필터-시작일자 */
	private String filterStartDate 		= "";
	/** 필터-끝일자 */
	private String filterEndDate 		= "";
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
	/** 필터-내/외자 */
	private String filterPlfTypeId		= "";
	/** 필터-내/외자 명 */
	private String filterPlfTypeDesc	= "";
	
	/** 담당자id - 필터 */
    private String filterEmpId          = "";
    /** 담당자명 - 필터 */
    private String filterEmpDesc        = "";

    /** 필터-공장 ID */
    private String filterPlantId            = "";
    /** 필터-공장 DESC */
    private String filterPlantDesc          = "";
    
    
	public String getFilterEmpId()
    {
        return filterEmpId;
    }
    public void setFilterEmpId(String filterEmpId)
    {
        this.filterEmpId = filterEmpId;
    }
    public String getFilterEmpDesc()
    {
        return filterEmpDesc;
    }
    public void setFilterEmpDesc(String filterEmpDesc)
    {
        this.filterEmpDesc = filterEmpDesc;
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
	public String getFilterPlfTypeId() {
		return filterPlfTypeId;
	}
	public void setFilterPlfTypeId(String filterPlfTypeId) {
		this.filterPlfTypeId = filterPlfTypeId;
	}
	public String getFilterPlfTypeDesc() {
		return filterPlfTypeDesc;
	}
	public void setFilterPlfTypeDesc(String filterPlfTypeDesc) {
		this.filterPlfTypeDesc = filterPlfTypeDesc;
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
