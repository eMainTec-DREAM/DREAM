package dream.asset.rpt.dto;

import common.bean.BaseDTO;
/**
 * 설비이력(과거) - 공통 DTO
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 *
 */
public class AssetRptWorkHistCommonDTO extends BaseDTO
{
	/**프로그램 가이드 ID*/
	private String eqHistoryId 			= "";
	
	/** Filter 작업일자 */
	private String filterStartDate		= "";
	private String filterEndDate		= "";
	
	/** Filter 설비 id */
	private String filterEquipId		= "";
	
	/** Filter 설비명 */
	private String filterEquipDesc		= "";
	
	/** Filter 설비위치id */
	private String filterEqLocId		= "";
	
	/** Filter 설비위치명 */
	private String filterEqLocDesc		= "";
	
	/** Filter 설비종류id */
	private String filterEqCtgId		= "";
	
	/** Filter 설비종류명 */
	private String filterEqCtgDesc		= "";
	
	/** Filter 담당부서id */
	private String filterDeptId		= "";
	
	/** Filter 담당부서명 */
	private String filterDeptDesc		= "";
	
	/** Filter 담당자id */
	private String filterEmpId		= "";
	
	/** Filter 담당자명 */
	private String filterEmpDesc		= "";
	
	/** Filter 고장원인 */
	private String filterCaCdDesc		= "";
	
	/** Filter 고장조치 */
	private String filterReCdDesc		= "";
	
	/** Filter 작업명	 */
	private String filterWkOrDesc		= "";
	
	/** Filter W/O#	 */
	private String filterWoNo		= "";
	/** Filter wo type ID	 */
	private String filterWoTypeId		= "";
	/** Filter wo type DESC	 */
	private String filterWoTypeDesc		= "";
	
	/** Filter EQHIST GEN TYPE ID	 */
	private String filterEqHistGenTypeId		= "";
	/** Filter EQHIST GEN TYPE DESC	 */
	private String filterEqHistGenTypeDesc		= "";
	
	public String getFilterEqHistGenTypeId() {
		return filterEqHistGenTypeId;
	}

	public void setFilterEqHistGenTypeId(String filterEqHistGenTypeId) {
		this.filterEqHistGenTypeId = filterEqHistGenTypeId;
	}

	public String getFilterEqHistGenTypeDesc() {
		return filterEqHistGenTypeDesc;
	}

	public void setFilterEqHistGenTypeDesc(String filterEqHistGenTypeDesc) {
		this.filterEqHistGenTypeDesc = filterEqHistGenTypeDesc;
	}

	public String getFilterWoTypeId() {
		return filterWoTypeId;
	}

	public void setFilterWoTypeId(String filterWoTypeId) {
		this.filterWoTypeId = filterWoTypeId;
	}

	public String getFilterWoTypeDesc() {
		return filterWoTypeDesc;
	}

	public void setFilterWoTypeDesc(String filterWoTypeDesc) {
		this.filterWoTypeDesc = filterWoTypeDesc;
	}

	public String getEqHistoryId() {
		return eqHistoryId;
	}

	public void setEqHistoryId(String eqHistoryId) {
		this.eqHistoryId = eqHistoryId;
		super.setAuditKey(eqHistoryId);
	}

	public String getFilterStartDate() {
		return filterStartDate;
	}

	public void setFilterStartDate(String filterStartDate) {
		this.filterStartDate = filterStartDate;
	}

	public String getFilterEndDate() {
		return filterEndDate;
	}

	public void setFilterEndDate(String filterEndDate) {
		this.filterEndDate = filterEndDate;
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

	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}

	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}

	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}

	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
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

	public String getFilterEmpId() {
		return filterEmpId;
	}

	public void setFilterEmpId(String filterEmpId) {
		this.filterEmpId = filterEmpId;
	}

	public String getFilterEmpDesc() {
		return filterEmpDesc;
	}

	public void setFilterEmpDesc(String filterEmpDesc) {
		this.filterEmpDesc = filterEmpDesc;
	}

	public String getFilterCaCdDesc() {
		return filterCaCdDesc;
	}

	public void setFilterCaCdDesc(String filterCaCdDesc) {
		this.filterCaCdDesc = filterCaCdDesc;
	}

	public String getFilterReCdDesc() {
		return filterReCdDesc;
	}

	public void setFilterReCdDesc(String filterReCdDesc) {
		this.filterReCdDesc = filterReCdDesc;
	}

	public String getFilterWkOrDesc() {
		return filterWkOrDesc;
	}

	public void setFilterWkOrDesc(String filterWkOrDesc) {
		this.filterWkOrDesc = filterWkOrDesc;
	}

	public String getFilterWoNo() {
		return filterWoNo;
	}

	public void setFilterWoNo(String filterWoNo) {
		this.filterWoNo = filterWoNo;
	}
	
	
}
