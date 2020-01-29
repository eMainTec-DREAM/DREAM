package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * 표준항목 공통 DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaStdPointCommonDTO extends BaseDTO
{
	/** 표준항목Id */
	private String stWrkId				= "";
	/** 필터- 표준명*/
	private String filterStWrkDesc		= "";
	/** 필터- 설비종류*/
	private String filterEqCtgDesc		= "";
	/** 필터- 설비종류*/
	private String filterEqCtgId		= "";
	/** 필터- 설비종류*/
	private String filterStWrkNo		= "";

	/** 필터- last여부*/
	private String isLastVersion     	= "";
	/** 필터- Revision 상태ID*/
	private String revisionStatus    	= "";
	/** 필터- Revision 상태명*/
	private String revisionStatusDesc   = "";
	/** 필터- Revision 번호*/
	private String revNo     			= "";
	
	public String getIsLastVersion() {
		return isLastVersion;
	}
	public void setIsLastVersion(String isLastVersion) {
		this.isLastVersion = isLastVersion;
	}
	public String getRevisionStatus() {
		return revisionStatus;
	}
	public void setRevisionStatus(String revisionStatus) {
		this.revisionStatus = revisionStatus;
	}
	public String getRevisionStatusDesc() {
		return revisionStatusDesc;
	}
	public void setRevisionStatusDesc(String revisionStatusDesc) {
		this.revisionStatusDesc = revisionStatusDesc;
	}
	public String getRevNo() {
		return revNo;
	}
	public void setRevNo(String revNo) {
		this.revNo = revNo;
	}
	public String getFilterStWrkNo() {
		return filterStWrkNo;
	}
	public void setFilterStWrkNo(String filterStWrkNo) {
		this.filterStWrkNo = filterStWrkNo;
	}
	public String getFilterEqCtgDesc() {
		return filterEqCtgDesc;
	}
	public void setFilterEqCtgDesc(String filterEqCtgDesc) {
		this.filterEqCtgDesc = filterEqCtgDesc;
	}
	public String getFilterEqCtgId() {
		return filterEqCtgId;
	}
	public void setFilterEqCtgId(String filterEqCtgId) {
		this.filterEqCtgId = filterEqCtgId;
	}

	public String getStWrkId() {
		return stWrkId;
	}
	public void setStWrkId(String stWrkId) {
		this.stWrkId = stWrkId;
		super.setAuditKey(stWrkId);
	}

	public String getFilterStWrkDesc() {
		return filterStWrkDesc;
	}
	public void setFilterStWrkDesc(String filterStWrkDesc) {
		this.filterStWrkDesc = filterStWrkDesc;
	}
}
