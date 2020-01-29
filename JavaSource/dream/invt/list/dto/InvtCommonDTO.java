package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * DTO
 * 
 * @author jung7126
 * @version $Id: RcmFmeaCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since 1.0
 * 
 */
public class InvtCommonDTO extends BaseDTO {
	
	/** 비고[주의사항] */
	private String remark 				= "";
	/** 실제완료일자 */
	private String endDate 				= "";
	/** 완료시기 */
	private String planEdate 			= "";
	/** 투자시기 */
	private String planSdate 			= "";
	/** 예상금액 */
	private String planAmt 				= "";
	/** 담당자 */
	private String empId 				= "";

	private String empDesc 				= "";
	/** 부서 */
	private String deptId 				= "";

	private String deptDesc 			= "";
	/** 설비ID */
	private String equipId 				= "";

	private String equipDesc 			= "";
	/** 투자분류정보 */
	private String invtCateg 			= "";

	private String invtCategDesc 		= "";
	/** 기능코드ID */
	private String eqctgId 				= "";

	private String eqctgDesc 			= "";
	/** 위치분류ID */
	private String eqlocId 				= "";

	private String eqlocDesc 			= "";
	/** 구매절차 종류ID */
	private String invtprctpId 			= "";

	private String invtprctpDesc 		= "";
	/** 설비투자 상태 */
	private String invtlistStatus 		= "";

	private String invtlistStatusDesc 	= "";
	/** 설비투자 명 */
	private String description 			= "";
	/** 설비투자 리스트 코드 */
	private String invtlistNo 			= "";
	/** 설비투자 리스트 id */
	private String invtlistId 			= "";
	/** 회사코드 */
	private String compNo 				= "";
	/** 진행 ID */
	private String invtphaseId 			= "";
	/** 분류 ID */
	private String invtTypeId 			= "";
	/** 분류 명 */
	private String invtTypeDesc 		= "";
	/** 투자시기 From */
	private String filterStartDate 		= "";
	/** 투자시기 To */
	private String filterEndDate 		= "";

	/** 공장 */
	private String filterPlantId 		= "";
	private String filterPlantDesc 		= "";

	/** 투자설비 ID */
	private String invtEquipId 			= "";
	/** 투자항목 ID */
	private String invtItemsId 			= "";

	/** 구매절차 단계ID */
	private String invtprcphId 			= "";
	
	/** 투자종류 */
	private String filterInvtKindId 	= "";
	private String filterInvtKindDesc 	= "";
	
	/** 투자완료일자 From */
	private String filterInvtComFromDate 	= "";
	/** 투자완료일자 To */
	private String filterInvtComToDate 		= "";
	/** 사용부서 ID */
	private String filterUsageDeptId 		= "";
	/** 사용부서 DESC */
	private String filterUsageDeptDesc 		= "";
	   /** 대분류 */
    private String filterLType					= "";
    /** 대분류 */
    private String filterLTypeId				= "";
    /** 소분류 */
    private String filterSType					= "";
    /** 소분류 */
    private String filterSTypeId				= "";
    /** 투자결과 ID */
    private String woReqId                      = "";
    private String woReqResId                   = "";
    
    public String getWoReqResId()
    {
        return woReqResId;
    }
    public void setWoReqResId(String woReqResId)
    {
        this.woReqResId = woReqResId;
    }
    public String getWoReqId()
    {
        return woReqId;
    }
    public void setWoReqId(String woReqId)
    {
        this.woReqId = woReqId;
    }
    public String getFilterLType() {
		return filterLType;
	}
	public void setFilterLType(String filterLType) {
		this.filterLType = filterLType;
	}
	public String getFilterLTypeId() {
		return filterLTypeId;
	}
	public void setFilterLTypeId(String filterLTypeId) {
		this.filterLTypeId = filterLTypeId;
	}
	public String getFilterSType() {
		return filterSType;
	}
	public void setFilterSType(String filterSType) {
		this.filterSType = filterSType;
	}
	public String getFilterSTypeId() {
		return filterSTypeId;
	}
	public void setFilterSTypeId(String filterSTypeId) {
		this.filterSTypeId = filterSTypeId;
	}
	public String getInvtItemsId() {
		return invtItemsId;
	}

	public String getFilterUsageDeptId() {
		return filterUsageDeptId;
	}

	public void setFilterUsageDeptId(String filterUsageDeptId) {
		this.filterUsageDeptId = filterUsageDeptId;
	}

	public String getFilterUsageDeptDesc() {
		return filterUsageDeptDesc;
	}

	public void setFilterUsageDeptDesc(String filterUsageDeptDesc) {
		this.filterUsageDeptDesc = filterUsageDeptDesc;
	}

	public String getFilterInvtComFromDate() {
		return filterInvtComFromDate;
	}

	public void setFilterInvtComFromDate(String filterInvtComFromDate) {
		this.filterInvtComFromDate = CommonUtil.convertDate(filterInvtComFromDate);
	}

	public String getFilterInvtComToDate() {
		return filterInvtComToDate;
	}

	public void setFilterInvtComToDate(String filterInvtComToDate) {
		this.filterInvtComToDate = CommonUtil.convertDate(filterInvtComToDate);
	}

	public String getFilterInvtKindId() {
		return filterInvtKindId;
	}

	public void setFilterInvtKindId(String filterInvtKindId) {
		this.filterInvtKindId = filterInvtKindId;
	}

	public String getFilterInvtKindDesc() {
		return filterInvtKindDesc;
	}

	public void setFilterInvtKindDesc(String filterInvtKindDesc) {
		this.filterInvtKindDesc = filterInvtKindDesc;
	}

	public String getInvtprcphId() {
		return invtprcphId;
	}

	public void setInvtprcphId(String invtprcphId) {
		this.invtprcphId = invtprcphId;
	}

	public void setInvtItemsId(String invtItemsId) {
		this.invtItemsId = invtItemsId;
	}

	public String getInvtEquipId() {
		return invtEquipId;
	}

	public void setInvtEquipId(String invtEquipId) {
		this.invtEquipId = invtEquipId;
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

	public String getInvtTypeId() {
		return invtTypeId;
	}

	public void setInvtTypeId(String invtTypeId) {
		this.invtTypeId = invtTypeId;
	}

	public String getInvtTypeDesc() {
		return invtTypeDesc;
	}

	public void setInvtTypeDesc(String invtTypeDesc) {
		this.invtTypeDesc = invtTypeDesc;
	}

	public String getInvtphaseId() {
		return invtphaseId;
	}

	public void setInvtphaseId(String invtphaseId) {
		this.invtphaseId = invtphaseId;
		super.setAuditKey(invtphaseId);
	}

	public String getInvtprctpDesc() {
		return invtprctpDesc;
	}

	public void setInvtprctpDesc(String invtprctpDesc) {
		this.invtprctpDesc = invtprctpDesc;
	}

	public String getEmpDesc() {
		return empDesc;
	}

	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getEquipDesc() {
		return equipDesc;
	}

	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}

	public String getInvtCategDesc() {
		return invtCategDesc;
	}

	public void setInvtCategDesc(String invtCategDesc) {
		this.invtCategDesc = invtCategDesc;
	}

	public String getEqctgDesc() {
		return eqctgDesc;
	}

	public void setEqctgDesc(String eqctgDesc) {
		this.eqctgDesc = eqctgDesc;
	}

	public String getEqlocDesc() {
		return eqlocDesc;
	}

	public void setEqlocDesc(String eqlocDesc) {
		this.eqlocDesc = eqlocDesc;
	}

	public String getInvtlistStatusDesc() {
		return invtlistStatusDesc;
	}

	public void setInvtlistStatusDesc(String invtlistStatusDesc) {
		this.invtlistStatusDesc = invtlistStatusDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPlanEdate() {
		return planEdate;
	}

	public void setPlanEdate(String planEdate) {
		this.planEdate = CommonUtil.convertDate(planEdate);
	}

	public String getPlanSdate() {
		return planSdate;
	}

	public void setPlanSdate(String planSdate) {
		this.planSdate = CommonUtil.convertDate(planSdate);
	}

	public String getPlanAmt() {
		return planAmt;
	}

	public void setPlanAmt(String planAmt) {
		this.planAmt = planAmt;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
		super.setAuditKey(equipId);
	}

	public String getInvtCateg() {
		return invtCateg;
	}

	public void setInvtCateg(String invtCateg) {
		this.invtCateg = invtCateg;
	}

	public String getEqctgId() {
		return eqctgId;
	}

	public void setEqctgId(String eqctgId) {
		this.eqctgId = eqctgId;
	}

	public String getEqlocId() {
		return eqlocId;
	}

	public void setEqlocId(String eqlocId) {
		this.eqlocId = eqlocId;
	}

	public String getInvtprctpId() {
		return invtprctpId;
	}

	public void setInvtprctpId(String invtprctpId) {
		this.invtprctpId = invtprctpId;
	}

	public String getInvtlistStatus() {
		return invtlistStatus;
	}

	public void setInvtlistStatus(String invtlistStatus) {
		this.invtlistStatus = invtlistStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvtlistNo() {
		return invtlistNo;
	}

	public void setInvtlistNo(String invtlistNo) {
		this.invtlistNo = invtlistNo;
	}

	public String getInvtlistId() {
		return invtlistId;
	}

	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
		super.setAuditKey(invtlistId);
	}

	public String getCompNo() {
		return compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
}
