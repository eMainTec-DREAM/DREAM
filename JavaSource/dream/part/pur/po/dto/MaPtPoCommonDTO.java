package dream.part.pur.po.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �����̷� ���� DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtPoCommonDTO extends BaseDTO
{
	/** ID */
	private String poListId						= "";
	/** ����-�μ�Id */
	private String filterDeptId					= "";
	/** ����-�μ��� */
	private String filterDeptDesc				= "";
	/** ����-�ŷ�óId */
	private String filterVendorId				= "";
	/** ����-�ŷ�ó�� */
	private String filterVendorDesc				= "";
	/** ����-û����ȣ */
	private String filterRequestNo				= "";
	/** ����-���ֹ�ȣ */
	private String filterPoNo					= "";
	/** ����-ǰ��԰� */
	private String filterPtNameSize				= "";
	/** ����-���ֻ���Id */
	private String filterPoStatusId				= "";
	/** ����-���ֻ��¸� */
	private String filterPoStatusDesc			= "";
	/** ����-û������ ���� */
	private String filterStartRequestDate		= "";
	/** ����-û������ �� */
	private String filterEndRequestDate			= "";
	/** ����-�������� ���� */
	private String filterStartPoDate			= "";
	/** ����-�������� �� */
	private String filterEndPoDate				= "";
	/** ����-�����԰�ݿ����� */
	private String filterIsTransfer				= "";

	public String getFilterIsTransfer() {
		return filterIsTransfer;
	}
	public void setFilterIsTransfer(String filterIsTransfer) {
		this.filterIsTransfer = filterIsTransfer;
	}
	public String getPoListId() {
		return poListId;
	}
	public void setPoListId(String poListId) {
		this.poListId = poListId;
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
	public String getFilterVendorId() {
		return filterVendorId;
	}
	public void setFilterVendorId(String filterVendorId) {
		this.filterVendorId = filterVendorId;
	}
	public String getFilterVendorDesc() {
		return filterVendorDesc;
	}
	public void setFilterVendorDesc(String filterVendorDesc) {
		this.filterVendorDesc = filterVendorDesc;
	}
	public String getFilterRequestNo() {
		return filterRequestNo;
	}
	public void setFilterRequestNo(String filterRequestNo) {
		this.filterRequestNo = filterRequestNo;
	}
	public String getFilterPoNo() {
		return filterPoNo;
	}
	public void setFilterPoNo(String filterPoNo) {
		this.filterPoNo = filterPoNo;
	}
	public String getFilterPtNameSize() {
		return filterPtNameSize;
	}
	public void setFilterPtNameSize(String filterPtNameSize) {
		this.filterPtNameSize = filterPtNameSize;
	}
	public String getFilterPoStatusId() {
		return filterPoStatusId;
	}
	public void setFilterPoStatusId(String filterPoStatusId) {
		this.filterPoStatusId = filterPoStatusId;
	}
	public String getFilterPoStatusDesc() {
		return filterPoStatusDesc;
	}
	public void setFilterPoStatusDesc(String filterPoStatusDesc) {
		this.filterPoStatusDesc = filterPoStatusDesc;
	}
	public String getFilterStartRequestDate() {
		return filterStartRequestDate;
	}
	public void setFilterStartRequestDate(String filterStartRequestDate) {
		this.filterStartRequestDate = CommonUtil.convertDate(filterStartRequestDate);
	}
	public String getFilterEndRequestDate() {
		return filterEndRequestDate;
	}
	public void setFilterEndRequestDate(String filterEndRequestDate) {
		this.filterEndRequestDate = CommonUtil.convertDate(filterEndRequestDate);
	}
	public String getFilterStartPoDate() {
		return filterStartPoDate;
	}
	public void setFilterStartPoDate(String filterStartPoDate) {
		this.filterStartPoDate = CommonUtil.convertDate(filterStartPoDate);
	}
	public String getFilterEndPoDate() {
		return filterEndPoDate;
	}
	public void setFilterEndPoDate(String filterEndPoDate) {
		this.filterEndPoDate = CommonUtil.convertDate(filterEndPoDate);
	}
}
