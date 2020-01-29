package dream.budget.plan.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 부서별 예산금액 상세 dto
 * @author  ssong
 * @version 
 * @since   1.0
 */
public class MaPtDeptBgDetailDTO extends BaseDTO
{
    /** 회사코드 - PK */
    private String compNo           = "";
    /**  */
    private String planAmt			= "";
    /**  */
    private String deptDesc			= "";
    /**  */
    private String bgtDeptPlanId    = "";
    /**  */
    private String bgtPlanId        = "";
    /**  */
    private String deptId			= "";
    /**  */
    private String yyyymm			= "";
    
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	public String getPlanAmt() {
		return planAmt;
	}
	public void setPlanAmt(String planAmt) {
		this.planAmt =  CommonUtil.convertMoney(planAmt);
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getBgtDeptPlanId() {
		return bgtDeptPlanId;
	}
	public void setBgtDeptPlanId(String bgtDeptPlanId) {
		this.bgtDeptPlanId = bgtDeptPlanId;
		super.setAuditKey(bgtDeptPlanId);
	}
	public String getBgtPlanId() {
		return bgtPlanId;
	}
	public void setBgtPlanId(String bgtPlanId) {
		this.bgtPlanId = bgtPlanId;
	}
}