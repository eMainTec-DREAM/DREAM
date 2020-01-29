package dream.work.close.check.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * MgrWorkCloseCheck Page - Detail DTO
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 *
 */
public class MgrWorkCloseCheckDetailDTO extends BaseDTO
{
    /**Key 작업완료 점검항목 설정 ID */ 
    private String stwrkId    		= "";    
    private String stwrkWorkId    	= "";    
    
    /** 점검# */ 
    private String stwrkNo    		= "";    
    /** 점검 제목 */ 
    private String stwrkDesc   		= "";    
    /** 상태ID */ 
    private String stwrkStatus    	= "";    
    /** 상태DESC */ 
    private String stwrkStatusDesc	= "";    
    /** 작업종류ID */ 
    private String woType	    	= "";    
    /** 작업종류DESC */ 
    private String woTypeDesc		= "";    
    /** 작업형태ID */ 
    private String pmType	    	= "";    
    /** 작업형태DESC */ 
    private String pmTypeDesc		= "";    
    /** 공장ID */ 
    private String plant	    	= "";    
    /** 공장DESC */ 
    private String plantDesc		= "";    
    /** 시행여부ID */ 
    private String isActive	    	= "";    
    /** 시행여부DESC */ 
    private String isActiveDesc		= "";    
    /** 등록부서ID */ 
    private String regDept			= "";    
    /** 등록부서DESC */ 
    private String regDeptDesc		= "";    
    /** 등록자DESC */ 
    private String regBy			= "";    
    /** 등록자DESC */ 
    private String regByDesc		= "";    
    /** 등록일자 */ 
    private String regDate			= "";    
    /** 비고 */ 
    private String remark			= "";
    
    /** 수정자 */ 
    private String updBy			= "";    
    /** 수정일자 */ 
    private String updDate			= "";    
    
	/** 개정이력 id */
	private String revisionhistId 	= "";
	/** 개정 상태 */
	private String revisionStatusId = "";
	/** 마지막 버전 여부 */
	private String isLastVersion 	= "";
	
	public String getStwrkId() {
		return stwrkId;
	}
	public String getStwrkWorkId() {
		return stwrkWorkId;
	}
	public void setStwrkWorkId(String stwrkWorkId) {
		this.stwrkWorkId = stwrkWorkId;
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
	public String getUpdDate() {
		return updDate;
	}
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	public String getRegDept() {
		return regDept;
	}
	public void setRegDept(String regDept) {
		this.regDept = regDept;
	}
	public String getRegByDesc() {
		return regByDesc;
	}
	public void setRegByDesc(String regByDesc) {
		this.regByDesc = regByDesc;
	}
	public void setStwrkId(String stwrkId) {
		this.stwrkId = stwrkId;
	}
	public String getStwrkNo() {
		return stwrkNo;
	}
	public void setStwrkNo(String stwrkNo) {
		this.stwrkNo = stwrkNo;
	}
	public String getStwrkDesc() {
		return stwrkDesc;
	}
	public void setStwrkDesc(String stwrkDesc) {
		this.stwrkDesc = stwrkDesc;
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
	public String getWoType() {
		return woType;
	}
	public void setWoType(String woType) {
		this.woType = woType;
	}
	public String getWoTypeDesc() {
		return woTypeDesc;
	}
	public void setWoTypeDesc(String woTypeDesc) {
		this.woTypeDesc = woTypeDesc;
	}
	public String getPmType() {
		return pmType;
	}
	public void setPmType(String pmType) {
		this.pmType = pmType;
	}
	public String getPmTypeDesc() {
		return pmTypeDesc;
	}
	public void setPmTypeDesc(String pmTypeDesc) {
		this.pmTypeDesc = pmTypeDesc;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getPlantDesc() {
		return plantDesc;
	}
	public void setPlantDesc(String plantDesc) {
		this.plantDesc = plantDesc;
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
	public String getRegDeptDesc() {
		return regDeptDesc;
	}
	public void setRegDeptDesc(String regDeptDesc) {
		this.regDeptDesc = regDeptDesc;
	}
	public String getRegBy() {
		return regBy;
	}
	public void setRegBy(String regBy) {
		this.regBy = regBy;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}    
}
