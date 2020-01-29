package dream.rcm.system.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * System�м� - �� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 */
public class RcmSysDetailDTO extends BaseDTO
{
	/** System �м�Id */
	private String rcmListId			= "";
	/** System �м�No */
	private String rcmListNo 			= "";
	/** �������Id*/
	private String rcmListStatus	    = "";
	/** ������¸� */
	private String rcmListStatusDesc    = "";
	/** System �м���  */
	private String description			= "";
	/** ��ġ Id */
	private String eqLocId 				= "";
	/** ��ġ�� */
	private String eqLocDesc         	= "";
	/** System �з�Id */
	private String rcmCateg           	= "";
	/** System �з�aud */
	private String rcmCategDesc         = "";
	/** ������ */
	private String startDate            = "";
	/** ������ */
	private String endDate	        	= "";
	/** ����� */
	private String regDate				= "";
	/** Crytycalist Id */
	private String crityListId 			= "";
	/** Critycality �� */
	private String crityListDesc 		= "";
	/** TaskMap Id */
	private String pmTaskMapListId 		= "";
	/** TaskMap�� */
	private String pmTaskMapListDesc	= "";
	/** ��� */
	private String remark 				= "";
	public String getRcmListId() {
		return rcmListId;
	}
	public void setRcmListId(String rcmListId) {
		this.rcmListId = rcmListId;
		super.setAuditKey(rcmListId);
	}
	public String getRcmListNo() {
		return rcmListNo;
	}
	public void setRcmListNo(String rcmListNo) {
		this.rcmListNo = rcmListNo;
	}
	public String getRcmListStatus() {
		return rcmListStatus;
	}
	public void setRcmListStatus(String rcmListStatus) {
		this.rcmListStatus = rcmListStatus;
	}
	public String getRcmListStatusDesc() {
		return rcmListStatusDesc;
	}
	public void setRcmListStatusDesc(String rcmListStatusDesc) {
		this.rcmListStatusDesc = rcmListStatusDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getRcmCateg() {
		return rcmCateg;
	}
	public void setRcmCateg(String rcmCateg) {
		this.rcmCateg = rcmCateg;
	}
	public String getRcmCategDesc() {
		return rcmCategDesc;
	}
	public void setRcmCategDesc(String rcmCategDesc) {
		this.rcmCategDesc = rcmCategDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonUtil.convertDate(startDate);
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonUtil.convertDate(endDate);
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = CommonUtil.convertDate(regDate);
	}
	public String getCrityListId() {
		return crityListId;
	}
	public void setCrityListId(String crityListId) {
		this.crityListId = crityListId;
	}
	public String getCrityListDesc() {
		return crityListDesc;
	}
	public void setCrityListDesc(String crityListDesc) {
		this.crityListDesc = crityListDesc;
	}
	public String getPmTaskMapListId() {
		return pmTaskMapListId;
	}
	public void setPmTaskMapListId(String pmTaskMapListId) {
		this.pmTaskMapListId = pmTaskMapListId;
	}
	public String getPmTaskMapListDesc() {
		return pmTaskMapListDesc;
	}
	public void setPmTaskMapListDesc(String pmTaskMapListDesc) {
		this.pmTaskMapListDesc = pmTaskMapListDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
   
}
