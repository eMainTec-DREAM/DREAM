package dream.consult.program.guide.dto;

import common.bean.BaseDTO;
/**
 * Guide Page - 공통 DTO
 * @author kim21017
 * @version $Id: ConsultPgmGuideCommonDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class ConsultPgmGuideCommonDTO extends BaseDTO
{
	/**프로그램 가이드 ID*/
	private String pgGuideId 			= "";
	/**Filter 가이드명*/
	private String filterPgGuideDesc 	= "";
	/**Filter 설비위치 ID*/
	private String filterEqLocId 		= "";
	/**Filter 설비위치 */
	private String filterEqLocDesc 		= "";
	/**Filter 설비종류 ID*/
	private String filterEqCtgId 		= "";
	/**Filter 설비종류 */
	private String filterEqCtgDesc 		= "";
	/**Filter 설비 ID*/
	private String filterEquipId 		= "";
	/**Filter 설비 */
	private String filterEquipDesc 		= "";
	/**Filter 부서 ID*/
	private String filterDeptId 		= "";
	/**Filter 부서 */
	private String filterDeptDesc 		= "";
	/**Filter 사원 ID*/
	private String filterEmpId 			= "";
	/**Filter 사원 */
	private String filterEmpDesc 		= "";
	/**Filter 설비상태 ID*/
	private String filterEqStatusId 	= "";
	/**Filter 설비상태 */
	private String filterEqStatusDesc 	= "";
	/**Filter 공장 ID*/
	private String filterPlantId 		= "";
	/**Filter 공장 */
	private String filterPlantDesc	 	= "";
	/**Filter 자재 ID*/
	private String filterPartId 		= "";
	/**Filter 자재 */
	private String filterPartDesc	 	= "";
	/**Filter 비고 */
	private String filterRemark	 		= "";
	/**Filter 사용여부 ID*/
	private String filterIsUseId 		= "";
	/**Filter 사용여부 */
	private String filterIsUseDesc	 	= "";
	/**Filter 공장 */
	private String filterCompNo		 	= "";
	
	
	public String getFilterCompNo() {
		return filterCompNo;
	}
	public void setFilterCompNo(String filterCompNo) {
		this.filterCompNo = filterCompNo;
	}
	public String getPgGuideId() {
		return pgGuideId;
	}
	public void setPgGuideId(String pgGuideId) {
		this.pgGuideId = pgGuideId;
	}
	public String getFilterPgGuideDesc() {
		return filterPgGuideDesc;
	}
	public void setFilterPgGuideDesc(String filterPgGuideDesc) {
		this.filterPgGuideDesc = filterPgGuideDesc;
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
	public String getFilterEqStatusId() {
		return filterEqStatusId;
	}
	public void setFilterEqStatusId(String filterEqStatusId) {
		this.filterEqStatusId = filterEqStatusId;
	}
	public String getFilterEqStatusDesc() {
		return filterEqStatusDesc;
	}
	public void setFilterEqStatusDesc(String filterEqStatusDesc) {
		this.filterEqStatusDesc = filterEqStatusDesc;
	}
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
	public String getFilterPartId() {
		return filterPartId;
	}
	public void setFilterPartId(String filterPartId) {
		this.filterPartId = filterPartId;
	}
	public String getFilterPartDesc() {
		return filterPartDesc;
	}
	public void setFilterPartDesc(String filterPartDesc) {
		this.filterPartDesc = filterPartDesc;
	}
	public String getFilterRemark() {
		return filterRemark;
	}
	public void setFilterRemark(String filterRemark) {
		this.filterRemark = filterRemark;
	}
	public String getFilterIsUseId() {
		return filterIsUseId;
	}
	public void setFilterIsUseId(String filterIsUseId) {
		this.filterIsUseId = filterIsUseId;
	}
	public String getFilterIsUseDesc() {
		return filterIsUseDesc;
	}
	public void setFilterIsUseDesc(String filterIsUseDesc) {
		this.filterIsUseDesc = filterIsUseDesc;
	}
	
}
