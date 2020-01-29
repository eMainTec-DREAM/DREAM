package dream.part.list.dto;

import common.bean.BaseDTO;

/**
 * 자재검색 팝업 DTO
 * @author  kim21017
 * @version $Id: LovPartsListDTO.java,v 1.1 2016/01/18 09:12:12 kim21017 Exp $
 * @since   1.0
 */
public class LovPartsListDTO extends BaseDTO
{
    /** Search Code */
    private String partNo 				= "";
    /** Search Description */
    private String partDesc 			= "";
    /** 부서ID */
    private String deptId	 			= "";
    /** 부서명 */
    private String deptDesc	 			= "";
    /** extCode1 */
    private String extCode1 			= "";
    /** extCode2 */
    private String extCode2 			= "";
    /** code type */
    private String codeType 			= "";
    /** 제작사 */
    private String filterMaker  		= "";
    /** 모델 */
    private String filterModel  		= "";
    /** 자재그룹 */
    private String filterPartGroup      = "";
    /** 자재그룹명 */
    private String filterPartGroupDesc  = "";
    /** Remark */
    private String filterRemark 		= "";
    private String filterVendorPtCode 	= "";
    
    /** 설비 ID */
    private String filterEquipId 		= "";
    /** 설비 DESC */
    private String filterEquipDesc 		= "";
    /** 창고 ID */
    private String filterWId 	    	= "";
    /** 창고 DESC */
    private String filterWDesc 			= "";
    /** 입고일자 start */
    private String filterStartRecDate 	= "";
    /** 입고일자 end */
    private String filterEndRecDate 	= "";
    /** 출고일자 start */
    private String filterStartIssDate 	= "";
    /** 출고일자 end */
    private String filterEndIssDate 	= "";
    /** 사용일자 start */
    private String filterStartUseDate 	= "";
    /** 사용일자 end */
    private String filterEndUseDate 	= "";
    /** ERP 코드 */
    private String filterErpPartNo 		= "";
    /** 부위Id */
    private String filterEqAsmbId       = "";
    /** 부위명 */
    private String filterEqAsmbDesc     = "";
    /** 사용여부 */
    private String filterIsUse			= "";
    /** 사용여부 Desc */
    private String filterIsUseDesc		= "";
    /** 자재유형 ID */ 
    private String filterPartCategCode		= "";
    
	public String getFilterPartCategCode() {
		return filterPartCategCode;
	}
	public void setFilterPartCategCode(String filterPartCategCode) {
		this.filterPartCategCode = filterPartCategCode;
	}
	public String getFilterIsUseDesc() {
		return filterIsUseDesc;
	}
	public void setFilterIsUseDesc(String filterIsUseDesc) {
		this.filterIsUseDesc = filterIsUseDesc;
	}
	public String getFilterIsUse() {
		return filterIsUse;
	}
	public void setFilterIsUse(String filterIsUse) {
		this.filterIsUse = filterIsUse;
	}
	public String getFilterErpPartNo()
    {
        return filterErpPartNo;
    }
    public String getFilterEqAsmbId() {
		return filterEqAsmbId;
	}
	public void setFilterEqAsmbId(String filterEqAsmbId) {
		this.filterEqAsmbId = filterEqAsmbId;
	}
	public String getFilterEqAsmbDesc() {
		return filterEqAsmbDesc;
	}
	public void setFilterEqAsmbDesc(String filterEqAsmbDesc) {
		this.filterEqAsmbDesc = filterEqAsmbDesc;
	}
	public void setFilterErpPartNo(String filterErpPartNo)
    {
        this.filterErpPartNo = filterErpPartNo;
    }
    public String getFilterStartRecDate()
    {
        return filterStartRecDate;
    }
    public void setFilterStartRecDate(String filterStartRecDate)
    {
        this.filterStartRecDate = filterStartRecDate;
    }
    public String getFilterEndRecDate()
    {
        return filterEndRecDate;
    }
    public void setFilterEndRecDate(String filterEndRecDate)
    {
        this.filterEndRecDate = filterEndRecDate;
    }
    public String getFilterStartIssDate()
    {
        return filterStartIssDate;
    }
    public void setFilterStartIssDate(String filterStartIssDate)
    {
        this.filterStartIssDate = filterStartIssDate;
    }
    public String getFilterEndIssDate()
    {
        return filterEndIssDate;
    }
    public void setFilterEndIssDate(String filterEndIssDate)
    {
        this.filterEndIssDate = filterEndIssDate;
    }
    public String getFilterStartUseDate()
    {
        return filterStartUseDate;
    }
    public void setFilterStartUseDate(String filterStartUseDate)
    {
        this.filterStartUseDate = filterStartUseDate;
    }
    public String getFilterEndUseDate()
    {
        return filterEndUseDate;
    }
    public void setFilterEndUseDate(String filterEndUseDate)
    {
        this.filterEndUseDate = filterEndUseDate;
    }
    public String getFilterEquipId()
    {
        return filterEquipId;
    }
    public void setFilterEquipId(String filterEquipId)
    {
        this.filterEquipId = filterEquipId;
    }
    public String getFilterEquipDesc()
    {
        return filterEquipDesc;
    }
    public void setFilterEquipDesc(String filterEquipDesc)
    {
        this.filterEquipDesc = filterEquipDesc;
    }
    public String getFilterWId()
    {
        return filterWId;
    }
    public void setFilterWId(String filterWId)
    {
        this.filterWId = filterWId;
    }
    public String getFilterWDesc()
    {
        return filterWDesc;
    }
    public void setFilterWDesc(String filterWDesc)
    {
        this.filterWDesc = filterWDesc;
    }
    public String getFilterVendorPtCode() {
		return filterVendorPtCode;
	}
	public void setFilterVendorPtCode(String filterVendorPtCode) {
		this.filterVendorPtCode = filterVendorPtCode;
	}
	public String getFilterMaker() {
		return filterMaker;
	}
	public void setFilterMaker(String filterMaker) {
		this.filterMaker = filterMaker;
	}
	public String getFilterModel() {
		return filterModel;
	}
	public void setFilterModel(String filterModel) {
		this.filterModel = filterModel;
	}
	public String getFilterPartGroup() {
		return filterPartGroup;
	}
	public void setFilterPartGroup(String filterPartGroup) {
		this.filterPartGroup = filterPartGroup;
	}
	public String getFilterPartGroupDesc() {
		return filterPartGroupDesc;
	}
	public void setFilterPartGroupDesc(String filterPartGroupDesc) {
		this.filterPartGroupDesc = filterPartGroupDesc;
	}
	
	public String getFilterRemark() {
		return filterRemark;
	}
	public void setFilterRemark(String filterRemark) {
		this.filterRemark = filterRemark;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getExtCode1() {
		return extCode1;
	}
	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}
	public String getExtCode2() {
		return extCode2;
	}
	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
}
