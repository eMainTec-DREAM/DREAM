package dream.invt.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �� DTO
 * @author  kim21017
 * @version $Id: RcmFmeaDetailDTO.java,v 1.0 2015/12/02 08:44:16 kim21017 Exp $
 * @since   1.0
 *
 */
public class InvtDetailDTO extends BaseDTO
{
	/** ���[���ǻ���] */
	private String remark = "";
	/** �����Ϸ����� */
	private String endDate = "";
	/** �Ϸ�ñ� */
	private String planEdate = "";
	/** ���ڽñ� */
	private String planSdate = "";
	/** ����ݾ� */
	private String planAmt = "";
	/** ���ڱݾ� */
	private String invtAmt	= "";
	/** ����� */
	private String empId = "";
	
	private String empDesc	= "";
	/** �μ� */
	private String deptId = "";
	
	private String deptDesc	= "";
	/** ����ID */
	private String equipId = "";
	
	private String equipDesc	= "";
	/** ���ںз����� */
	private String invtCateg = "";
	
	private String invtCategDesc	= "";
	/** ����ڵ�ID */
	private String eqctgId = "";
	
	private String eqctgDesc	= "";
	/** ��ġ�з�ID */
	private String eqlocId = "";
	
	private String eqlocDesc	= "";
	/** �������� ����ID */
	private String invtprctpId = "";
	/** �������� ���� */
	private String invtlistStatus = "";
	
	private String invtlistStatusDesc	= "";
	/** �������� �� */
	private String description = "";
	/** �������� ����Ʈ �ڵ� */
	private String invtlistNo = "";
	/** �������� ����Ʈ id */
	private String invtlistId = "";
	/** ȸ���ڵ� */
	private String compNo = "";
	/** ����ܰ� */
	private String invtphaseStatusDesc	= "";
	/** ���������� */
	private String invtDesc	= "";
	/** ���� */
	private String invtStatusDesc 	= "";

	private String eqLocDesc		= "";
	/** ���ںз�Id */
	private String invtTypeId 		= "";
	/** ���ںз��� */
	private String invtTypeDesc 	= "";
	
	/** ���� */
	private String plantId			= "";
	private String plantDesc		= "";
	
	/** OLD �������� ����Ʈ id */
	private String oldInvtlistId 	= "";
	
	/** �۾���û ID */
	private String woReqId			= "";
	private String woReqResId       = "";
	
	/** �������� */
	private String invtKindId		= "";
	private String invtKindDesc		= "";
	
	   /** ���� */
    private String spec            = "";
    /** ��û���� */
    private String recQty          = "";
    /** ��û�ܰ� */
    private String unitPrice       = "";
    /** ��ȭ */
    private String currency        = "";
    /** ��ȭ�� */
    private String currencyDesc    = "";
    /** ���ű׷� */
    private String dwbuygroup      = "";
    /** �������� */
    private String dwtaxgubun      = "";
    /** ���ű׷��  */
    private String dwbuygroupDesc  = "";
    /** �������и� */
    private String dwtaxgubunDesc  = "";
    
    private String dwptcontitemStatus      = "";
    
    private String dwptcontitemStatusDesc  = "";
    /** ���� */
    private String unit            = "";
    /** ǰ�� ID */
    private String partId          = "";
    /** ǰ�� �ڵ�  */
    private String partNo          = "";
    /** ǰ��� */
    private String itemDesc         = "";
    
    private String vendorId         = "";
    
    private String vendorDesc       = "";
    
    private String contAmt          = "";
    /** �԰������� */
    private String ivDate           = "";
    
    private String usageDept        = "";
    
    private String usageDeptDesc    = "";

    public String getWoReqResId()
    {
        return woReqResId;
    }
    public void setWoReqResId(String woReqResId)
    {
        this.woReqResId = woReqResId;
    }
    public String getUsageDept()
    {
        return usageDept;
    }
    public void setUsageDept(String usageDept)
    {
        this.usageDept = usageDept;
    }
    public String getUsageDeptDesc()
    {
        return usageDeptDesc;
    }
    public void setUsageDeptDesc(String usageDeptDesc)
    {
        this.usageDeptDesc = usageDeptDesc;
    }
    public String getIvDate()
    {
        return ivDate;
    }
    public void setIvDate(String ivDate)
    {
        this.ivDate = CommonUtil.convertDate(ivDate);
    }
    public String getVendorId()
    {
        return vendorId;
    }
    public void setVendorId(String vendorId)
    {
        this.vendorId = vendorId;
    }
    public String getVendorDesc()
    {
        return vendorDesc;
    }
    public void setVendorDesc(String vendorDesc)
    {
        this.vendorDesc = vendorDesc;
    }
    public String getContAmt()
    {
        return contAmt;
    }
    public void setContAmt(String contAmt)
    {
        this.contAmt =CommonUtil.convertMoney(contAmt);
    }
    public String getItemDesc()
    {
        return itemDesc;
    }
    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }
    public String getSpec()
    {
        return spec;
    }
    public void setSpec(String spec)
    {
        this.spec = spec;
    }
    public String getRecQty()
    {
        return recQty;
    }
    public void setRecQty(String recQty)
    {
        this.recQty = CommonUtil.convertMoney(recQty);
    }
    public String getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(String unitPrice)
    {
        this.unitPrice = CommonUtil.convertMoney(unitPrice);
    }
    public String getCurrency()
    {
        return currency;
    }
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
    public String getCurrencyDesc()
    {
        return currencyDesc;
    }
    public void setCurrencyDesc(String currencyDesc)
    {
        this.currencyDesc = currencyDesc;
    }
    public String getDwbuygroup()
    {
        return dwbuygroup;
    }
    public void setDwbuygroup(String dwbuygroup)
    {
        this.dwbuygroup = dwbuygroup;
    }
    public String getDwtaxgubun()
    {
        return dwtaxgubun;
    }
    public void setDwtaxgubun(String dwtaxgubun)
    {
        this.dwtaxgubun = dwtaxgubun;
    }
    public String getDwbuygroupDesc()
    {
        return dwbuygroupDesc;
    }
    public void setDwbuygroupDesc(String dwbuygroupDesc)
    {
        this.dwbuygroupDesc = dwbuygroupDesc;
    }
    public String getDwtaxgubunDesc()
    {
        return dwtaxgubunDesc;
    }
    public void setDwtaxgubunDesc(String dwtaxgubunDesc)
    {
        this.dwtaxgubunDesc = dwtaxgubunDesc;
    }
    public String getDwptcontitemStatus()
    {
        return dwptcontitemStatus;
    }
    public void setDwptcontitemStatus(String dwptcontitemStatus)
    {
        this.dwptcontitemStatus = dwptcontitemStatus;
    }
    public String getDwptcontitemStatusDesc()
    {
        return dwptcontitemStatusDesc;
    }
    public void setDwptcontitemStatusDesc(String dwptcontitemStatusDesc)
    {
        this.dwptcontitemStatusDesc = dwptcontitemStatusDesc;
    }
    public String getUnit()
    {
        return unit;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    public String getPartId()
    {
        return partId;
    }
    public void setPartId(String partId)
    {
        this.partId = partId;
    }
    public String getPartNo()
    {
        return partNo;
    }
    public void setPartNo(String partNo)
    {
        this.partNo = partNo;
    }
    public String getWoReqId() {
		return woReqId;
	}
	public String getInvtKindId() {
		return invtKindId;
	}
	public void setInvtKindId(String invtKindId) {
		this.invtKindId = invtKindId;
	}
	public String getInvtKindDesc() {
		return invtKindDesc;
	}
	public void setInvtKindDesc(String invtKindDesc) {
		this.invtKindDesc = invtKindDesc;
	}
	public void setWoReqId(String woReqId) {
		this.woReqId = woReqId;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getOldInvtlistId() {
		return oldInvtlistId;
	}
	public void setOldInvtlistId(String oldInvtlistId) {
		this.oldInvtlistId = oldInvtlistId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
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
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getInvtStatusDesc() {
		return invtStatusDesc;
	}
	public void setInvtStatusDesc(String invtStatusDesc) {
		this.invtStatusDesc = invtStatusDesc;
	}
	public String getInvtDesc() {
		return invtDesc;
	}
	public void setInvtDesc(String invtDesc) {
		this.invtDesc = invtDesc;
	}
	public String getInvtphaseStatusDesc() {
		return invtphaseStatusDesc;
	}
	public void setInvtphaseStatusDesc(String invtphaseStatusDesc) {
		this.invtphaseStatusDesc = invtphaseStatusDesc;
	}
	public String getInvtAmt() {
		return invtAmt;
	}
	public void setInvtAmt(String invtAmt) {
		this.invtAmt = CommonUtil.convertMoney(invtAmt);
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
		this.endDate = CommonUtil.convertDate(endDate);
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
		this.planAmt = CommonUtil.convertMoney(planAmt);
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
