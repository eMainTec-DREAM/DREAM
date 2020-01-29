package dream.work.pm.std.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * ǥ���׸� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class MaStdPointHdrDetailDTO extends BaseDTO
{
	/** ǥ���׸�Id */
	private String stWrkId						= "";
	/** ǥ���׸�No */
	private String stWrkNo						= "";
	/** ǥ�ظ� */
	private String stWrkDesc					= "";
	/** ��������Id */
	private String eqCtgId						= "";
	/** ���������� */
	private String eqCtgDesc					= "";
	/** �ۼ���Id */
	private String regBy						= "";
	/** �ۼ��ڸ� */
	private String regName						= "";
	/** ��Ϻμ�Id */
	private String deptId						= "";
	/** ��Ϻμ��� */
	private String deptDesc						= "";
	/** ������� */
	private String regDate						= "";
	/** ��� */
	private String remark						= "";
	
	/** ����������Id */
	private String updBy						= "";
	/** ���������ڸ� */
	private String updName						= "";
	/** ���������ð� */
	private String lastUpdTime					= "";
	
	/** �����̷� id */
	private String revisionhistId 				= "";
	/** ���� ���� */
	private String revisionStatusId 			= "";
	/** ������ ���� ���� */
	private String isLastVersion 				= "";
	/** ���࿩�� id */
	private String isActive 					= "";
	/** ���࿩�� */
	private String isActiveDesc 				= "";
	/** ������� id */
	private String stwrkStatus 					= "";
	/** ������� */
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
