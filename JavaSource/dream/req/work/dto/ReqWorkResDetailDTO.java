package dream.req.work.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 처리사항 상세 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 */
public class ReqWorkResDetailDTO extends BaseDTO
{
    /** 처리사항 ID */
    private String woReqResId       = "";
	/** 처리일자 */
	private String resDate			= "";
	/** 구분ID*/
	private String resStatusId		= "";
	/** 구분명 */
	private String resStatusDesc	= "";
	/** 작업자ID */
	private String empId			= "";
	/** 작업자 명 */
	private String empDesc			= "";
	/** 부서ID */
	private String deptId			= "";
	/** 부서 명 */
	private String deptDesc			= "";
	/** 작업내용 */
	private String response			= "";

	private String wkorId			= "";

	private String woreqresMethod	= "";
	
	/** 투자목록 ID */
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
