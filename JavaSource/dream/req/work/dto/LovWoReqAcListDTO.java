package dream.req.work.dto;

import common.bean.BaseDTO;

/**
 * �۾���û �˾� DTO
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 */
public class LovWoReqAcListDTO extends BaseDTO
{
    /** �ڵ� */
    private String woReqNo 		= "";
    /** �� */
    private String woReqDesc 	= "";
    /** ���� */
    private String woReqStatus 	= "";
    /** extCode1 */
    private String extCode1 	= "";
    /** extCode2 */
    private String extCode2 	= "";
    /** code type */
    private String woReqType	= "";
    
    /** �۾���û�� - ���� */
	private String filterWoReqDesc			= "";
	/** �۾���û��ȣ - ���� */
	private String filterWoReqNo			= "";
	/** �۾���û ������ - ���� */
	private String filterReqStartDate		= "";
	/** �۾���û ������ - ���� */
	private String filterReqEndDate			= "";
	/** ����id - ���� */
	private String filterEquipId			= "";
	/** ����� - ���� */
	private String filterEquipDesc			= "";
	/** ��ġid - ���� */
	private String filterEqLocId			= "";
	/** ��ġ�� - ���� */
	private String filterEqLocDesc			= "";
	/** ��û�μ�id - ���� */
	private String filterReqDeptId			= "";
	/** ��û�μ��� - ���� */
	private String filterReqDeptDesc		= "";
	/** ��û��id - ���� */
	private String filterReqEmpId			= "";
	/** ��û�ڸ� - ���� */
	private String filterReqEmpDesc			= "";
	/** ����-��û���� */
	private String filterWoReqTypeId		= "";
	/** ����-��û���� �� */
	private String filterWoReqTypeDesc		= "";
	/** ���� - ������� */
	private String filterWoReqStatus		= "";
	/** ���� - ������� �� */
	private String filterWoReqStatusDesc	= "";

	/** Multy Select Y */
    private String multiSelect    			= "";

    
	public String getWoReqNo() {
		return woReqNo;
	}

	public void setWoReqNo(String woReqNo) {
		this.woReqNo = woReqNo;
	}

	public String getWoReqDesc() {
		return woReqDesc;
	}

	public void setWoReqDesc(String woReqDesc) {
		this.woReqDesc = woReqDesc;
	}

	public String getWoReqStatus() {
		return woReqStatus;
	}

	public void setWoReqStatus(String woReqStatus) {
		this.woReqStatus = woReqStatus;
	}

	public String getExtCode1() {
		return extCode1;
	}

	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}

	public String getExtCode2() {
		return extCode2;
	}

	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}

	public String getWoReqType() {
		return woReqType;
	}

	public void setWoReqType(String woReqType) {
		this.woReqType = woReqType;
	}

	public String getFilterWoReqDesc() {
		return filterWoReqDesc;
	}

	public void setFilterWoReqDesc(String filterWoReqDesc) {
		this.filterWoReqDesc = filterWoReqDesc;
	}

	public String getFilterWoReqNo() {
		return filterWoReqNo;
	}

	public void setFilterWoReqNo(String filterWoReqNo) {
		this.filterWoReqNo = filterWoReqNo;
	}

	public String getFilterReqStartDate() {
		return filterReqStartDate;
	}

	public void setFilterReqStartDate(String filterReqStartDate) {
		this.filterReqStartDate = filterReqStartDate;
	}

	public String getFilterReqEndDate() {
		return filterReqEndDate;
	}

	public void setFilterReqEndDate(String filterReqEndDate) {
		this.filterReqEndDate = filterReqEndDate;
	}

	public String getFilterEquipId() {
		return filterEquipId;
	}

	public void setFilterEquipId(String filterEquipId) {
		this.filterEquipId = filterEquipId;
	}

	public String getFilterEquipDesc() {
		return filterEquipDesc;
	}

	public void setFilterEquipDesc(String filterEquipDesc) {
		this.filterEquipDesc = filterEquipDesc;
	}

	public String getFilterEqLocId() {
		return filterEqLocId;
	}

	public void setFilterEqLocId(String filterEqLocId) {
		this.filterEqLocId = filterEqLocId;
	}

	public String getFilterEqLocDesc() {
		return filterEqLocDesc;
	}

	public void setFilterEqLocDesc(String filterEqLocDesc) {
		this.filterEqLocDesc = filterEqLocDesc;
	}

	public String getFilterReqDeptId() {
		return filterReqDeptId;
	}

	public void setFilterReqDeptId(String filterReqDeptId) {
		this.filterReqDeptId = filterReqDeptId;
	}

	public String getFilterReqDeptDesc() {
		return filterReqDeptDesc;
	}

	public void setFilterReqDeptDesc(String filterReqDeptDesc) {
		this.filterReqDeptDesc = filterReqDeptDesc;
	}

	public String getFilterReqEmpId() {
		return filterReqEmpId;
	}

	public void setFilterReqEmpId(String filterReqEmpId) {
		this.filterReqEmpId = filterReqEmpId;
	}

	public String getFilterReqEmpDesc() {
		return filterReqEmpDesc;
	}

	public void setFilterReqEmpDesc(String filterReqEmpDesc) {
		this.filterReqEmpDesc = filterReqEmpDesc;
	}

	public String getFilterWoReqTypeId() {
		return filterWoReqTypeId;
	}

	public void setFilterWoReqTypeId(String filterWoReqTypeId) {
		this.filterWoReqTypeId = filterWoReqTypeId;
	}

	public String getFilterWoReqTypeDesc() {
		return filterWoReqTypeDesc;
	}

	public void setFilterWoReqTypeDesc(String filterWoReqTypeDesc) {
		this.filterWoReqTypeDesc = filterWoReqTypeDesc;
	}

	public String getFilterWoReqStatus() {
		return filterWoReqStatus;
	}

	public void setFilterWoReqStatus(String filterWoReqStatus) {
		this.filterWoReqStatus = filterWoReqStatus;
	}

	public String getFilterWoReqStatusDesc() {
		return filterWoReqStatusDesc;
	}

	public void setFilterWoReqStatusDesc(String filterWoReqStatusDesc) {
		this.filterWoReqStatusDesc = filterWoReqStatusDesc;
	}

	public String getMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
    
}
