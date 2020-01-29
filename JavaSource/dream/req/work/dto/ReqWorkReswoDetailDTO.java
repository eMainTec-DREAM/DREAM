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
public class ReqWorkReswoDetailDTO extends BaseDTO
{
    /** ó������ ID */
    private String woReqResId       = "";
	/** ó������ */
	private String resDate			= "";
	/** ����ID*/
	private String resStatusId		= "";
	/** ���и� */
	private String resStatusDesc	= "";
	/** �۾����� */
	private String response			= "";
	
	/** �۾����Id */
	private String wkOrId 			= "";
	/** �۾�No */
	private String woNo 			= "";
	/** ����Id */
	private String woStatusId 		= "";
	/** ���¸� */
	private String woStatusDesc 	= "";
	/** ����id */
	private String equipId 			= "";
	/** ����� */
	private String equipDesc 		= "";
	/** ��ġ */
	private String eqLocDesc 		= "";
	/** �۾��� */
	private String wkOrDesc 		= "";
	/** �۾�����Id */
	private String woTypeId 		= "";
	/** �۾������� */
	private String woTypeDesc 		= "";
	/** ���μ�Id */
	private String deptId 			= "";
	/** ���μ��� */
	private String deptDesc 		= "";
	/** �۾�����Id */
	private String pmTypeId 		= "";
	/** �۾����¸� */
	private String pmTypeDesc 		= "";
	/** �����Id */
	private String empId 			= "";
	/** ����ڸ� */
	private String empDesc 			= "";
	/** �۾����� ������ */
	private String startDate 		= "";
	/** �۾��ð� ���۽ð� */
	private String endDate 			= "";
	/** �۾����� ������ */
	private String startTime 		= "";
	/** �۾��ð� ����ð� */
	private String endTime 			= "";
	/** �۾��ð�(��) */
	private String workTime 		= "";
	/** �۾��󼼳��� */
	private String perform 			= "";
	/** �ڰ�/���� ���� */
	private String selfVendorType   = "";
	/** �ڰ�/���� ���и� */
	private String selfVendorTypeDesc   = "";
	/** �ŷ�ó ID */
	private String vendorId			= "";
	/** �ŷ�ó�� */
	private String vendorDesc		= "";
	/** �����۾�ID */
	private String pWkOrId          = "";
	/** �۾����� */
	private String wkorDate         = "";
	/** ������ID */
	private String shiftTypeId      = "";
	/** ������ID�� */
	private String shiftTypeDesc    = "";
	/** �ݾ�(�۾�����-�����ΰ�츸) */
	private String totAmt           = "";
	/** ��ǰ���Ȯ�� KEY */
	private String ptisslistId      = "";
	/** �۾����߻����� */
	private String woGenType        = "";
	/** ��ġid */
	private String eqLocId          = "";
	/** ����W/O id */
	private String parentWoId       = "";
	/** ����W/O �� */
	private String parentWoDesc     = "";
	/** ���İ������� */
	private String pmAction			= "";
	/** �۾��׷�Id */
	private String wkCtrId			= "";
	/** �۾��׷�� */
	private String wkCtrDesc		= "";
	/** Ȯ���ڸ� */
	private String closeBy			= "";
	/** Ȯ������ */
	private String closeDate		= "";
	
	
	
	public String getWkOrId() {
		return wkOrId;
	}
	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}
	public String getWoNo() {
		return woNo;
	}
	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	public String getWoStatusId() {
		return woStatusId;
	}
	public void setWoStatusId(String woStatusId) {
		this.woStatusId = woStatusId;
	}
	public String getWoStatusDesc() {
		return woStatusDesc;
	}
	public void setWoStatusDesc(String woStatusDesc) {
		this.woStatusDesc = woStatusDesc;
	}
	public String getEquipId() {
		return equipId;
	}
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	public String getEquipDesc() {
		return equipDesc;
	}
	public void setEquipDesc(String equipDesc) {
		this.equipDesc = equipDesc;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getWkOrDesc() {
		return wkOrDesc;
	}
	public void setWkOrDesc(String wkOrDesc) {
		this.wkOrDesc = wkOrDesc;
	}
	public String getWoTypeId() {
		return woTypeId;
	}
	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}
	public String getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(String pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getPerform() {
		return perform;
	}
	public void setPerform(String perform) {
		this.perform = perform;
	}
	public String getSelfVendorType() {
		return selfVendorType;
	}
	public void setSelfVendorType(String selfVendorType) {
		this.selfVendorType = selfVendorType;
	}
	public String getSelfVendorTypeDesc() {
		return selfVendorTypeDesc;
	}
	public void setSelfVendorTypeDesc(String selfVendorTypeDesc) {
		this.selfVendorTypeDesc = selfVendorTypeDesc;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorDesc() {
		return vendorDesc;
	}
	public void setVendorDesc(String vendorDesc) {
		this.vendorDesc = vendorDesc;
	}
	public String getpWkOrId() {
		return pWkOrId;
	}
	public void setpWkOrId(String pWkOrId) {
		this.pWkOrId = pWkOrId;
	}
	public String getWkorDate() {
		return wkorDate;
	}
	public void setWkorDate(String wkorDate) {
		this.wkorDate = CommonUtil.convertDate(wkorDate);
	}
	public String getShiftTypeId() {
		return shiftTypeId;
	}
	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}
	public String getShiftTypeDesc() {
		return shiftTypeDesc;
	}
	public void setShiftTypeDesc(String shiftTypeDesc) {
		this.shiftTypeDesc = shiftTypeDesc;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getPtisslistId() {
		return ptisslistId;
	}
	public void setPtisslistId(String ptisslistId) {
		this.ptisslistId = ptisslistId;
	}
	public String getWoGenType() {
		return woGenType;
	}
	public void setWoGenType(String woGenType) {
		this.woGenType = woGenType;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getParentWoId() {
		return parentWoId;
	}
	public void setParentWoId(String parentWoId) {
		this.parentWoId = parentWoId;
	}
	public String getParentWoDesc() {
		return parentWoDesc;
	}
	public void setParentWoDesc(String parentWoDesc) {
		this.parentWoDesc = parentWoDesc;
	}
	public String getPmAction() {
		return pmAction;
	}
	public void setPmAction(String pmAction) {
		this.pmAction = pmAction;
	}
	public String getWkCtrId() {
		return wkCtrId;
	}
	public void setWkCtrId(String wkCtrId) {
		this.wkCtrId = wkCtrId;
	}
	public String getWkCtrDesc() {
		return wkCtrDesc;
	}
	public void setWkCtrDesc(String wkCtrDesc) {
		this.wkCtrDesc = wkCtrDesc;
	}
	public String getCloseBy() {
		return closeBy;
	}
	public void setCloseBy(String closeBy) {
		this.closeBy = closeBy;
	}
	public String getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
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
	
}
