package dream.part.pur.po.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 발주이력 공통 DTO
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * 
 */
public class MaPtPoCommonDTO extends BaseDTO
{
	/** ID */
	private String poListId						= "";
	/** 필터-부서Id */
	private String filterDeptId					= "";
	/** 필터-부서명 */
	private String filterDeptDesc				= "";
	/** 필터-거래처Id */
	private String filterVendorId				= "";
	/** 필터-거래처명 */
	private String filterVendorDesc				= "";
	/** 필터-청구번호 */
	private String filterRequestNo				= "";
	/** 필터-발주번호 */
	private String filterPoNo					= "";
	/** 필터-품명규격 */
	private String filterPtNameSize				= "";
	/** 필터-발주상태Id */
	private String filterPoStatusId				= "";
	/** 필터-발주상태명 */
	private String filterPoStatusDesc			= "";
	/** 필터-청구일자 시작 */
	private String filterStartRequestDate		= "";
	/** 필터-청구일자 끝 */
	private String filterEndRequestDate			= "";
	/** 필터-발주일자 시작 */
	private String filterStartPoDate			= "";
	/** 필터-발주일자 끝 */
	private String filterEndPoDate				= "";
	/** 필터-무상입고반영여부 */
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
