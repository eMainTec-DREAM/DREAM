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
    /**Key �۾��Ϸ� �����׸� ���� ID */ 
    private String stwrkId    		= "";    
    private String stwrkWorkId    	= "";    
    
    /** ����# */ 
    private String stwrkNo    		= "";    
    /** ���� ���� */ 
    private String stwrkDesc   		= "";    
    /** ����ID */ 
    private String stwrkStatus    	= "";    
    /** ����DESC */ 
    private String stwrkStatusDesc	= "";    
    /** �۾�����ID */ 
    private String woType	    	= "";    
    /** �۾�����DESC */ 
    private String woTypeDesc		= "";    
    /** �۾�����ID */ 
    private String pmType	    	= "";    
    /** �۾�����DESC */ 
    private String pmTypeDesc		= "";    
    /** ����ID */ 
    private String plant	    	= "";    
    /** ����DESC */ 
    private String plantDesc		= "";    
    /** ���࿩��ID */ 
    private String isActive	    	= "";    
    /** ���࿩��DESC */ 
    private String isActiveDesc		= "";    
    /** ��Ϻμ�ID */ 
    private String regDept			= "";    
    /** ��Ϻμ�DESC */ 
    private String regDeptDesc		= "";    
    /** �����DESC */ 
    private String regBy			= "";    
    /** �����DESC */ 
    private String regByDesc		= "";    
    /** ������� */ 
    private String regDate			= "";    
    /** ��� */ 
    private String remark			= "";
    
    /** ������ */ 
    private String updBy			= "";    
    /** �������� */ 
    private String updDate			= "";    
    
	/** �����̷� id */
	private String revisionhistId 	= "";
	/** ���� ���� */
	private String revisionStatusId = "";
	/** ������ ���� ���� */
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
