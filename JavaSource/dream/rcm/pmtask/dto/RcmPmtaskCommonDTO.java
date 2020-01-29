package dream.rcm.pmtask.dto;

import common.bean.BaseDTO;

/**
 * °øÅë DTO
 * @author  kim21017
 * @version $Id: RcmPmtaskCommonDTO.java,v 1.0 2015/12/02 09:13:08 kim21017 Exp $
 * @since   1.0
 * 
 */
public class RcmPmtaskCommonDTO extends BaseDTO
{
	/**  */
	private String remark = "";
	/**  */
	private String effailDesc = "";
	/**  */
	private String effcd = "";
	/**  */
	private String effailId = "";
	/**  */
	private String cafailDesc = "";
	/**  */
	private String cafcd = "";
	/**  */
	private String cafailId = "";
	/**  */
	private String mofailDesc = "";
	/**  */
	private String mofcd = "";
	/**  */
	private String mofailId = "";
	/**  */
	private String rcmeqasmbId = "";
	/**  */
	private String rcmeqId = "";
	/**  */
	private String rcmffailId = "";
	/**  */
	private String rcmfuncId = "";
	/**  */
	private String rcmlistId = "";
	
	private String rcmlistDesc = "";
	/**  */
	private String rcmfmeaId = "";
	/**  */
	private String compNo = "";
	
	private String rcmpmtaskId	= "";
	
	private String filterRcmlistId	= "";
	
	private String filterRcmlistDesc	= "";
	
	/** Task Map ID */
	private String pmtaskmapId	= "";
	
	private String pmtaskmapVal = "";
	
	private String pmtaskmapRsltVal = "";
	
	
	public String getPmtaskmapRsltVal() {
		return pmtaskmapRsltVal;
	}
	public void setPmtaskmapRsltVal(String pmtaskmapRsltVal) {
		this.pmtaskmapRsltVal = pmtaskmapRsltVal;
	}
	public String getPmtaskmapVal() {
		return pmtaskmapVal;
	}
	public void setPmtaskmapVal(String pmtaskmapVal) {
		this.pmtaskmapVal = pmtaskmapVal;
	}
	public String getPmtaskmapId() {
		return pmtaskmapId;
	}
	public void setPmtaskmapId(String pmtaskmapId) {
		this.pmtaskmapId = pmtaskmapId;
	}
	public String getRcmpmtaskId() {
		return rcmpmtaskId;
	}
	public void setRcmpmtaskId(String rcmpmtaskId) {
		this.rcmpmtaskId = rcmpmtaskId;
		super.setAuditKey(rcmpmtaskId);
	}
	public String getFilterRcmlistId() {
		return filterRcmlistId;
	}
	public void setFilterRcmlistId(String filterRcmlistId) {
		this.filterRcmlistId = filterRcmlistId;
	}
	public String getFilterRcmlistDesc() {
		return filterRcmlistDesc;
	}
	public void setFilterRcmlistDesc(String filterRcmlistDesc) {
		this.filterRcmlistDesc = filterRcmlistDesc;
	}
	public String getRcmlistDesc() {
		return rcmlistDesc;
	}
	public void setRcmlistDesc(String rcmlistDesc) {
		this.rcmlistDesc = rcmlistDesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEffailDesc() {
		return effailDesc;
	}
	public void setEffailDesc(String effailDesc) {
		this.effailDesc = effailDesc;
	}
	public String getEffcd() {
		return effcd;
	}
	public void setEffcd(String effcd) {
		this.effcd = effcd;
	}
	public String getEffailId() {
		return effailId;
	}
	public void setEffailId(String effailId) {
		this.effailId = effailId;
	}
	public String getCafailDesc() {
		return cafailDesc;
	}
	public void setCafailDesc(String cafailDesc) {
		this.cafailDesc = cafailDesc;
	}
	public String getCafcd() {
		return cafcd;
	}
	public void setCafcd(String cafcd) {
		this.cafcd = cafcd;
	}
	public String getCafailId() {
		return cafailId;
	}
	public void setCafailId(String cafailId) {
		this.cafailId = cafailId;
	}
	public String getMofailDesc() {
		return mofailDesc;
	}
	public void setMofailDesc(String mofailDesc) {
		this.mofailDesc = mofailDesc;
	}
	public String getMofcd() {
		return mofcd;
	}
	public void setMofcd(String mofcd) {
		this.mofcd = mofcd;
	}
	public String getMofailId() {
		return mofailId;
	}
	public void setMofailId(String mofailId) {
		this.mofailId = mofailId;
	}
	public String getRcmeqasmbId() {
		return rcmeqasmbId;
	}
	public void setRcmeqasmbId(String rcmeqasmbId) {
		this.rcmeqasmbId = rcmeqasmbId;
	}
	public String getRcmeqId() {
		return rcmeqId;
	}
	public void setRcmeqId(String rcmeqId) {
		this.rcmeqId = rcmeqId;
	}
	public String getRcmffailId() {
		return rcmffailId;
	}
	public void setRcmffailId(String rcmffailId) {
		this.rcmffailId = rcmffailId;
	}
	public String getRcmfuncId() {
		return rcmfuncId;
	}
	public void setRcmfuncId(String rcmfuncId) {
		this.rcmfuncId = rcmfuncId;
	}
	public String getRcmlistId() {
		return rcmlistId;
	}
	public void setRcmlistId(String rcmlistId) {
		this.rcmlistId = rcmlistId;
	}
	public String getRcmfmeaId() {
		return rcmfmeaId;
	}
	public void setRcmfmeaId(String rcmfmeaId) {
		this.rcmfmeaId = rcmfmeaId;
	}
	public String getCompNo() {
		return compNo;
	}
	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}
	
	
}
