package dream.consult.program.guide.dto;

import common.bean.BaseDTO;

/**
 * Guide Page - Detail DTO
 * @author kim21017
 * @version $Id: ConsultPgmGuideDetailDTO.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 *
 */
public class ConsultPgmGuideDetailDTO extends BaseDTO
{
	/**프로그램 가이드 ID*/
	private String pgGuideId 		= "";
	/**프로그램 가이드 No*/
	private String pgGuideNo 		= "";
	/**가이드명*/
	private String pgGuideDesc 		= "";
	/**설비위치 ID*/
	private String eqLocId 			= "";
	/**설비위치 */
	private String eqLocDesc 		= "";
	
	private String eqLocDesc2 		= "";
	/**설비종류 ID*/
	private String eqCtgId 			= "";
	/**설비종류 */
	private String eqCtgDesc 		= "";
	/**설비 ID*/
	private String equipId 			= "";
	/**설비 */
	private String equipDesc 		= "";
	/**부서 ID*/
	private String deptId 			= "";
	/**부서 */
	private String deptDesc 		= "";
	/**사원 ID*/
	private String empId 			= "";
	/**사원 */
	private String empDesc 			= "";
	/**설비상태 ID*/
	private String eqStatusId 		= "";
	/**설비상태 */
	private String eqStatusDesc 	= "";
	/**공장 ID*/
	private String plantId 			= "";
	/**공장 */
	private String plantDesc	 	= "";
	/**자재 ID*/
	private String partId 			= "";
	/**자재 */
	private String partDesc	 		= "";
	/**비고 */
	private String remark	 		= "";
	/**사용여부 ID*/
	private String isUseId 			= "";
	/**사용여부 */
	private String isUseDesc	 	= "";
	
	/**설비 */
	private String equipDesc1 		= "";
	/**설비 */
	private String equipDesc2		= "";
	/**설비 */
	private String equipDesc3 		= "";
	
	
	public String getEquipDesc1() {
		return equipDesc1;
	}
	public void setEquipDesc1(String equipDesc1) {
		this.equipDesc1 = equipDesc1;
	}
	public String getEquipDesc2() {
		return equipDesc2;
	}
	public void setEquipDesc2(String equipDesc2) {
		this.equipDesc2 = equipDesc2;
	}
	public String getEquipDesc3() {
		return equipDesc3;
	}
	public void setEquipDesc3(String equipDesc3) {
		this.equipDesc3 = equipDesc3;
	}
	public String getEqLocDesc2() {
		return eqLocDesc2;
	}
	public void setEqLocDesc2(String eqLocDesc2) {
		this.eqLocDesc2 = eqLocDesc2;
	}
	public String getPgGuideId() {
		return pgGuideId;
	}
	public void setPgGuideId(String pgGuideId) {
		this.pgGuideId = pgGuideId;
	}
	public String getPgGuideNo() {
		return pgGuideNo;
	}
	public void setPgGuideNo(String pgGuideNo) {
		this.pgGuideNo = pgGuideNo;
	}
	public String getPgGuideDesc() {
		return pgGuideDesc;
	}
	public void setPgGuideDesc(String pgGuideDesc) {
		this.pgGuideDesc = pgGuideDesc;
	}
	public String getEqLocId() {
		return eqLocId;
	}
	public void setEqLocId(String eqLocId) {
		this.eqLocId = eqLocId;
	}
	public String getEqLocDesc() {
		return eqLocDesc;
	}
	public void setEqLocDesc(String eqLocDesc) {
		this.eqLocDesc = eqLocDesc;
	}
	public String getEqCtgId() {
		return eqCtgId;
	}
	public void setEqCtgId(String eqCtgId) {
		this.eqCtgId = eqCtgId;
	}
	public String getEqCtgDesc() {
		return eqCtgDesc;
	}
	public void setEqCtgDesc(String eqCtgDesc) {
		this.eqCtgDesc = eqCtgDesc;
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
	public String getEqStatusId() {
		return eqStatusId;
	}
	public void setEqStatusId(String eqStatusId) {
		this.eqStatusId = eqStatusId;
	}
	public String getEqStatusDesc() {
		return eqStatusDesc;
	}
	public void setEqStatusDesc(String eqStatusDesc) {
		this.eqStatusDesc = eqStatusDesc;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
	public String getPartDesc() {
		return partDesc;
	}
	public void setPartDesc(String partDesc) {
		this.partDesc = partDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsUseId() {
		return isUseId;
	}
	public void setIsUseId(String isUseId) {
		this.isUseId = isUseId;
	}
	public String getIsUseDesc() {
		return isUseDesc;
	}
	public void setIsUseDesc(String isUseDesc) {
		this.isUseDesc = isUseDesc;
	}

}
