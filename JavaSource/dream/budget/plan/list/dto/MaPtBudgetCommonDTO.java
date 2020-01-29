package dream.budget.plan.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 예산관리 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaPtBudgetCommonDTO extends BaseDTO
{
    
    /** 회사코드 */
    private String compNo                   = "";
    /** 월별예산계획코드 */
    private String bgtPlanId				= "";
    /** 계정코드 */
    private String accountNo				= "";
    /** 계정명 */
    private String accountDesc				= "";
    /** 년월 */
    private String yyyymm   				= "";
    /** 월별부서별예산계획 ID */
    private String bgtDeptPlanId			= "";
    /** 부서코드 */
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
