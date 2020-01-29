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
	
	/** ���[���ǻ���] */
	private String remark 				= "";
	/** �����Ϸ����� */
	private String endDate 				= "";
	/** �Ϸ�ñ� */
	private String planEdate 			= "";
	/** ���ڽñ� */
	private String planSdate 			= "";
	/** ����ݾ� */
	private String planAmt 				= "";
	/** ����� */
	private String empId 				= "";

	private String empDesc 				= "";
	/** �μ� */
	private String deptId 				= "";

	private String deptDesc 			= "";
	/** ����ID */
	private String equipId 				= "";

	private String equipDesc 			= "";
	/** ���ںз����� */
	private String invtCateg 			= "";

	private String invtCategDesc 		= "";
	/** ����ڵ�ID */
	private String eqctgId 				= "";

	private String eqctgDesc 			= "";
	/** ��ġ�з�ID */
	private String eqlocId 				= "";

	private String eqlocDesc 			= "";
	/** �������� ����ID */
	private String invtprctpId 			= "";

	private String invtprctpDesc 		= "";
	/** �������� ���� */
	private String invtlistStatus 		= "";

	private String invtlistStatusDesc 	= "";
	/** �������� �� */
	private String description 			= "";
	/** �������� ����Ʈ �ڵ� */
	private String invtlistNo 			= "";
	/** �������� ����Ʈ id */
	private String invtlistId 			= "";
	/** ȸ���ڵ� */
	private String compNo 				= "";
	/** ���� ID */
	private String invtphaseId 			= "";
	/** �з� ID */
	private String invtTypeId 			= "";
	/** �з� �� */
	private String invtTypeDesc 		= "";
	/** ���ڽñ� From */
	private String filterStartDate 		= "";
	/** ���ڽñ� To */
	private String filterEndDate 		= "";

	/** ���� */
	private String filterPlantId 		= "";
	private String filterPlantDesc 		= "";

	/** ���ڼ��� ID */
	private String invtEquipId 			= "";
	/** �����׸� ID */
	private String invtItemsId 			= "";

	/** �������� �ܰ�ID */
	private String invtprcphId 			= "";
	
	/** �������� */
	private String filterInvtKindId 	= "";
	private String filterInvtKindDesc 	= "";
	
	/** ���ڿϷ����� From */
	private String filterInvtComFromDate 	= "";
	/** ���ڿϷ����� To */
	private String filterInvtComToDate 		= "";
	/** ���μ� ID */
	private String filterUsageDeptId 		= "";
	/** ���μ� DESC */
	private String filterUsageDeptDesc 		= "";
	   /** ��з� */
    private String filterLType					= "";
    /** ��з� */
    private String filterLTypeId				= "";
    /** �Һз� */
    private String filterSType					= "";
    /** �Һз� */
    private String filterSTypeId				= "";
    /** ���ڰ�� ID */
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
