package dream.budget.plan.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ������� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtBudgetCommonDTO extends BaseDTO
{
    
    /** ȸ���ڵ� */
    private String compNo                   = "";
    /** ���������ȹ�ڵ� */
    private String bgtPlanId				= "";
    /** �����ڵ� */
    private String accountNo				= "";
    /** ������ */
    private String accountDesc				= "";
    /** ��� */
    private String yyyymm   				= "";
    /** �����μ��������ȹ ID */
    private String bgtDeptPlanId			= "";
    /** �μ��ڵ� */
    private String deptId					= "";
    
    
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getBgtDeptPlanId() {
		return bgtDeptPlanId;
	}
	public void setBgtDeptPlanId(String bgtDeptPlanId) {
		this.bgtDeptPlanId = bgtDeptPlanId;
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


}
