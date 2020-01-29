package dream.part.adj.stktake.dto;

import common.bean.BaseDTO;

/**
 * ��ǰ�ǻ� ���� DTO
 * @author  kim21017
 * @version $Id: PartAdjStkTakeHdrCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class PartAdjStkTakeCommonDTO extends BaseDTO
{
	/** �����û id */
	private String ptPrListId 				= "";
	/** ���� ��û�μ�id  */
	private String filterDeptId				= "";
	/** ���� ��û�μ��� */
	private String filterDeptDesc			= "";
	/** ���� ���� ��û���� */
	private String filterStartPlanDate		= "";
	/** ���� �� ��û���� */
	private String filterEndPlanDate			= "";
	/** ���� ���� */
	private String filterPtPrListDesc		= "";
	/** ���� ����id  */
	private String filterPartId				= "";
	/** ���� ����No */
	private String filterPartNo				= "";
	/** ���� ����� */
	private String filterPartDesc			= "";
	/** ���� �ۼ�����id  */
	private String filterPtPrListStatusId	= "";
	/** ���� �ۼ����¸� */
	private String filterPtPrListStatusDesc	= "";
	/** ���� ǰ��/�԰� */
	private String filterPtDescSize			= "";
	/** ���� ��û��ȣ */
	private String filterPtPrListNo			= "";
	/** ���� ��û��id  */
	private String filterUserId				= "";
	/** ���� ��û�ڸ� */
	private String filterUserDesc			= "";
	
	/** ���� â��� */
	private String filterWname				= "";
	/** ���� â�� */
	private String filterWcodeId			= "";
	
	/** ����-���� ID */
	private String filterPlantId         	= "";
	/** ����-���� DESC */
	private String filterPlantDesc        	= "";
	
	private String ptStkTakeListId			= "";
	private String ptStkTakeStatus			= "";

    
	public String getFilterPlantId() {
		return filterPlantId;
	}
	public void setFilterPlantId(String filterPlantId) {
		this.filterPlantId = filterPlantId;
	}
	public String getFilterPlantDesc() {
		return filterPlantDesc;
	}
	public void setFilterPlantDesc(String filterPlantDesc) {
		this.filterPlantDesc = filterPlantDesc;
	}
	public String getPtStkTakeStatus() {
		return ptStkTakeStatus;
	}
	public void setPtStkTakeStatus(String ptStkTakeStatus) {
		this.ptStkTakeStatus = ptStkTakeStatus;
	}
	public String getPtStkTakeListId() {
		return ptStkTakeListId;
	}
	public void setPtStkTakeListId(String ptStkTakeListId) {
		this.ptStkTakeListId = ptStkTakeListId;
		super.setAuditKey(ptStkTakeListId);
	}
	public String getFilterWname() {
		return filterWname;
	}
	public void setFilterWname(String filterWname) {
		this.filterWname = filterWname;
	}
	public String getFilterWcodeId() {
		return filterWcodeId;
	}
	public void setFilterWcodeId(String filterWcodeId) {
		this.filterWcodeId = filterWcodeId;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getPtPrListId() {
		return ptPrListId;
	}
	public void setPtPrListId(String ptPrListId) {
		this.ptPrListId = ptPrListId;
	}
	public String getFilterDeptId() {
		return filterDeptId;
	}
	public void setFilterDeptId(String filterDeptId) {
		this.filterDeptId = filterDeptId;
	}
	public String getFilterDeptDesc() {
		return filterDeptDesc;
	}
	public void setFilterDeptDesc(String filterDeptDesc) {
		this.filterDeptDesc = filterDeptDesc;
	}

	public String getFilterStartPlanDate() {
		return filterStartPlanDate;
	}
	public void setFilterStartPlanDate(String filterStartPlanDate) {
		this.filterStartPlanDate = filterStartPlanDate;
	}
	public String getFilterEndPlanDate() {
		return filterEndPlanDate;
	}
	public void setFilterEndPlanDate(String filterEndPlanDate) {
		this.filterEndPlanDate = filterEndPlanDate;
	}
	public String getFilterPtPrListDesc() {
		return filterPtPrListDesc;
	}
	public void setFilterPtPrListDesc(String filterPtPrListDesc) {
		this.filterPtPrListDesc = filterPtPrListDesc;
	}
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartNo() {
		return filterPartNo;
	}
	public void setFilterPartNo(String filterPartNo) {
		this.filterPartNo = filterPartNo;
	}
	public String getFilterPtPrListStatusId() {
		return filterPtPrListStatusId;
	}
	public void setFilterPtPrListStatusId(String filterPtPrListStatusId) {
		this.filterPtPrListStatusId = filterPtPrListStatusId;
	}
	public String getFilterPtPrListStatusDesc() {
		return filterPtPrListStatusDesc;
	}
	public void setFilterPtPrListStatusDesc(String filterPtPrListStatusDesc) {
		this.filterPtPrListStatusDesc = filterPtPrListStatusDesc;
	}
	public String getFilterPtDescSize() {
		return filterPtDescSize;
	}
	public void setFilterPtDescSize(String filterPtDescSize) {
		this.filterPtDescSize = filterPtDescSize;
	}
	public String getFilterPtPrListNo() {
		return filterPtPrListNo;
	}
	public void setFilterPtPrListNo(String filterPtPrListNo) {
		this.filterPtPrListNo = filterPtPrListNo;
	}
	public String getFilterUserId() {
		return filterUserId;
	}
	public void setFilterUserId(String filterUserId) {
		this.filterUserId = filterUserId;
	}
	public String getFilterUserDesc() {
		return filterUserDesc;
	}
	public void setFilterUserDesc(String filterUserDesc) {
		this.filterUserDesc = filterUserDesc;
	}
	
}
