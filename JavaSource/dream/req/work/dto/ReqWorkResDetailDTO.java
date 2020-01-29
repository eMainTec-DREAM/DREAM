package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ó������ �� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class ReqWorkResDetailDTO extends BaseDTO
{
    /** ó������ ID */
    private String woReqResId       = "";
	/** ó������ */
	private String resDate			= "";
	/** ����ID*/
	private String resStatusId		= "";
	/** ���и� */
	private String resStatusDesc	= "";
	/** �۾���ID */
	private String empId			= "";
	/** �۾��� �� */
	private String empDesc			= "";
	/** �μ�ID */
	private String deptId			= "";
	/** �μ� �� */
	private String deptDesc			= "";
	/** �۾����� */
	private String response			= "";

	private String wkorId			= "";

	private String woreqresMethod	= "";
	
	/** ���ڸ�� ID */
	private String invtlistId		= "";
	
	public String getInvtlistId() {
		return invtlistId;
	}

	public void setInvtlistId(String invtlistId) {
		this.invtlistId = invtlistId;
	}

	public String getWoReqResId() {
		return woReqResId;
	}

	public void setWoReqResId(String woReqResId) {
		this.woReqResId = woReqResId;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = CommonUtil.convertDate(resDate);
	}

	public String getResStatusId() {
		return resStatusId;
	}

	public void setResStatusId(String resStatusId) {
		this.resStatusId = resStatusId;
	}

	public String getResStatusDesc() {
		return resStatusDesc;
	}

	public void setResStatusDesc(String resStatusDesc) {
		this.resStatusDesc = resStatusDesc;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpDesc() {
		return empDesc;
	}

	public void setEmpDesc(String empDesc) {
		this.empDesc = empDesc;
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

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getWkorId() {
		return wkorId;
	}

	public void setWkorId(String wkorId) {
		this.wkorId = wkorId;
	}

	public String getWoreqresMethod() {
		return woreqresMethod;
	}

	public void setWoreqresMethod(String woreqresMethod) {
		this.woreqresMethod = woreqresMethod;
	}


	
	
	

}
