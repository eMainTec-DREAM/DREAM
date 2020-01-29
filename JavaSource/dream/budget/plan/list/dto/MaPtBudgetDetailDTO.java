package dream.budget.plan.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaPtBudgetDetailDTO extends BaseDTO
{
	/** ȸ���ڵ� */
	private String compNo 					= "";
	 /** ���������ȹ�ڵ� */
    private String bgtPlanId				= "";
    /** �����ڵ� */
    private String accountNo				= "";
    /** ������ */
    private String accountDesc				= "";
    /** �� */
    private String yyyymm					= "";
    /** ��ȹ�ݾ� */
    private String planAmt					= "";
    /** ������ �������� */
    private String updDate					= "";
    /** ������ ������ */
    private String updBy					= "";
    /** �����ݾ� */
    private String assignAmt				= "";
    /** ��� */
    private String remark					= "";
    /**  */
    private String updById					= "";
    
	public String getUpdById() {
		return updById;
	}
	public void setUpdById(String updById) {
		this.updById = updById;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAssignAmt() {
		return assignAmt;
	}
	public void setAssignAmt(String assignAmt) {
		this.assignAmt = CommonUtil.convertMoney(assignAmt);
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getBgtPlanId() {
		return bgtPlanId;
	}
	public void setBgtPlanId(String bgtPlanId) {
		this.bgtPlanId = bgtPlanId;
		super.setAuditKey(bgtPlanId);
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountDesc() {
		return accountDesc;
	}
	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = CommonUtil.convertDate(yyyymm);
	}
	public String getPlanAmt() {	
		//if(planAmt=="")planAmt=null;
		return planAmt;
	}
	public void setPlanAmt(String planAmt) {
		this.planAmt = CommonUtil.convertMoney(planAmt);
	}
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = CommonUtil.convertDate(updDate);
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
    
}
