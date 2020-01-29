package dream.cert.list.dto;

import common.bean.BaseDTO;

/**
 * 자격증분류 공통 DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class CertCommonDTO extends BaseDTO
{
	/** ID */
	private String certListId         = "";
	/** 자격증구분*/
	private String filterCertTypeDesc         = "";
	/** 자격증명 */
	private String filterCertName         = "";
	/** 자격증구분 Desc */
	private String filterCertType         = "";
	
	public String getCertListId() {
		return certListId;
	}
	public void setCertListId(String certListId) {
		this.certListId = certListId;
		super.setAuditKey(certListId);
	}
	public String getFilterCertTypeDesc() {
		return filterCertTypeDesc;
	}
	public void setFilterCertTypeDesc(String filterCertTypeDesc) {
		this.filterCertTypeDesc = filterCertTypeDesc;
	}
	public String getFilterCertName() {
		return filterCertName;
	}
	public void setFilterCertName(String filterCertName) {
		this.filterCertName = filterCertName;
	}
	public String getFilterCertType() {
		return filterCertType;
	}
	public void setFilterCertType(String filterCertType) {
		this.filterCertType = filterCertType;
	}
	


}
