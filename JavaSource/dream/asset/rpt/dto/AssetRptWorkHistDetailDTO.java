package dream.asset.rpt.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����̷�(����) - Detail DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptWorkHistDetailDTO extends BaseDTO
{
	/**���α׷� ���̵� ID*/
	private String eqHistoryId 		= "";
	
	/** ���� ID*/
	private String equipId			= "";
	/** ���� DESC*/
	private String equipName		= "";
	/** ���� NO */
	private String itemNo			= "";
	/** ���� */
	private String eqAsmbDesc		= "";
	/** ���� ��ġ */
	private String eqlocDesc		= "";
	/** ���� ���� */
	private String eqType			= "";
	/** ���� */
	private String wkorDate			= "";
	/** �۾����� */
	private String woType			= "";
	/** �۾����� id */
	private String woTypeId			= "";
	/** �۾���(����) */
	private String description		= "";
	/** ���μ� */
	private String deptDesc			= "";
	/** ���μ� id */
	private String deptId			= "";
	/** ����� */
	private String empName			= "";
	/** ����� id */
	private String empId			= "";
	/** �ŷ�ó */
	private String vendorName		= "";
	/** �ݾ� */
	private String totAmt			= "";
	/** �۾����� */
	private String perform			= "";
	/** ������� */
	private String caDesc			= "";
	/** ������ġ */
	private String reDesc			= "";
	/** �۾����� */
	private String startDate		= "";
	private String startTime		= "";
	/** �۾����� */
	private String endDate			= "";
	private String endTime			= "";
	/** �۾��ð�(��) */
	private String workTime			= "";
	/** WKORID */
	private String wkOrId			= "";
	
	/** WO PARAM */
	private String woParam			= "";
	
	private String eqHistGenType	= "";
	
	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getEqHistGenType() {
		return eqHistGenType;
	}

	public void setEqHistGenType(String eqHistGenType) {
		this.eqHistGenType = eqHistGenType;
	}

	public String getWoParam() {
		return woParam;
	}

	public void setWoParam(String woParam) {
		this.woParam = woParam;
	}

	public String getWkOrId() {
		return wkOrId;
	}

	public void setWkOrId(String wkOrId) {
		this.wkOrId = wkOrId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getEqAsmbDesc() {
		return eqAsmbDesc;
	}

	public void setEqAsmbDesc(String eqAsmbDesc) {
		this.eqAsmbDesc = eqAsmbDesc;
	}

	public String getEqlocDesc() {
		return eqlocDesc;
	}

	public void setEqlocDesc(String eqlocDesc) {
		this.eqlocDesc = eqlocDesc;
	}

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	public String getWkorDate() {
		return wkorDate;
	}

	public void setWkorDate(String wkorDate) {
		this.wkorDate = CommonUtil.convertDate(wkorDate);
	}

	public String getWoType() {
		return woType;
	}

	public void setWoType(String woType) {
		this.woType = woType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getTotAmt() {
		return totAmt;
	}

	public void setTotAmt(String totAmt) {
		this.totAmt = CommonUtil.convertMoney(totAmt);
	}

	public String getPerform() {
		return perform;
	}

	public void setPerform(String perform) {
		this.perform = perform;
	}

	public String getCaDesc() {
		return caDesc;
	}

	public void setCaDesc(String caDesc) {
		this.caDesc = caDesc;
	}

	public String getReDesc() {
		return reDesc;
	}

	public void setReDesc(String reDesc) {
		this.reDesc = reDesc;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = CommonUtil.convertTime(startTime);
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = CommonUtil.convertTime(endTime);
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = CommonUtil.convertMoney(workTime);
	}

	public String getEqHistoryId() {
		return eqHistoryId;
	}

	public void setEqHistoryId(String eqHistoryId) {
		this.eqHistoryId = eqHistoryId;
		super.setAuditKey(eqHistoryId);
	}
	
	public String getWoTypeId() {
		return woTypeId;
	}

	public void setWoTypeId(String woTypeId) {
		this.woTypeId = woTypeId;
	}

}
