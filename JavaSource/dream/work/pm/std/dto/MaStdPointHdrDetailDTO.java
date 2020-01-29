package dream.work.pm.std.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * 표준항목 - 상세 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaStdPointHdrDetailDTO extends BaseDTO
{
	/** 표준항목Id */
	private String stWrkId						= "";
	/** 표준항목No */
	private String stWrkNo						= "";
	/** 표준명 */
	private String stWrkDesc					= "";
	/** 설비종류Id */
	private String eqCtgId						= "";
	/** 설비종류명 */
	private String eqCtgDesc					= "";
	/** 작성자Id */
	private String regBy						= "";
	/** 작성자명 */
	private String regName						= "";
	/** 등록부서Id */
	private String deptId						= "";
	/** 등록부서명 */
	private String deptDesc						= "";
	/** 등록일자 */
	private String regDate						= "";
	/** 비고 */
	private String remark						= "";
	
	/** 최종수정자Id */
	private String updBy						= "";
	/** 최종수정자명 */
	private String updName						= "";
	/** 최종수정시간 */
	private String lastUpdTime					= "";
	
	/** 개정이력 id */
	private String revisionhistId 				= "";
	/** 개정 상태 */
	private String revisionStatusId 			= "";
	/** 마지막 버전 여부 */
	private String isLastVersion 				= "";
	/** 시행여부 id */
	private String isActive 					= "";
	/** 시행여부 */
	private String isActiveDesc 				= "";
	/** 진행상태 id */
	private String stwrkStatus 					= "";
	/** 진행상태 */
	private String stwrkStatusDesc 				= "";
	
	
	public String getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(String lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getStwrkStatus() {
		return stwrkStatus;
	}
	public void setStwrkStatus(String stwrkStatus) {
		this.stwrkStatus = stwrkStatus;
	}
	public String getStwrkStatusDesc() {
		return stwrkStatusDesc;
	}
	public void setStwrkStatusDesc(String stwrkStatusDesc) {
		this.stwrkStatusDesc = stwrkStatusDesc;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsActiveDesc() {
		return isActiveDesc;
	}
	public void setIsActiveDesc(String isActiveDesc) {
		this.isActiveDesc = isActiveDesc;
	}
	public String getRevisionhistId() {
		return revisionhistId;
	}
	public void setRevisionhistId(String revisionhistId) {
		this.revisionhistId = revisionhistId;
	}
	public String getRevisionStatusId() {
		return revisionStatusId;
	}
	public void setRevisionStatusId(String revisionStatusId) {
		this.revisionStatusId = revisionStatusId;
	}
	public String getIsLastVersion() {
		return isLastVersion;
	}
	public void setIsLastVersion(String isLastVersion) {
		this.isLastVersion = isLastVersion;
	}
	public String getUpdBy() {
		return updBy;
	}
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	public String getUpdName() {
		return updName;
	}
	public void setUpdName(String updName) {
		this.updName = updName;
	}
	public String getStWrkId() {
		return stWrkId;
	}
	public void setStWrkId(String stWrkId) {
		this.stWrkId = stWrkId;
		super.setAuditKey(stWrkId);
	}
	public String getStWrkNo() {
		return stWrkNo;
	}
	public void setStWrkNo(String stWrkNo) {
		this.stWrkNo = stWrkNo;
	}
	public String getStWrkDesc() {
		return stWrkDesc;
	}
	public void setStWrkDesc(String stWrkDesc) {
		this.stWrkDesc = stWrkDesc;
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
	public String getRegBy() {
		return regBy;
	}
	public void setRegBy(String regBy) {
		this.regBy = regBy;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
