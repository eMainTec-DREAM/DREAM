package dream.work.pm.std.dto;

import common.bean.BaseDTO;

/**
 * ǥ���׸� ���� DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class MaStdPointCommonDTO extends BaseDTO
{
	/** ǥ���׸�Id */
	private String stWrkId				= "";
	/** ����- ǥ�ظ�*/
	private String filterStWrkDesc		= "";
	/** ����- ��������*/
	private String filterEqCtgDesc		= "";
	/** ����- ��������*/
	private String filterEqCtgId		= "";
	/** ����- ��������*/
	private String filterStWrkNo		= "";

	/** ����- last����*/
	private String isLastVersion     	= "";
	/** ����- Revision ����ID*/
	private String revisionStatus    	= "";
	/** ����- Revision ���¸�*/
	private String revisionStatusDesc   = "";
	/** ����- Revision ��ȣ*/
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
