package dream.cert.list.dto;

import common.bean.BaseDTO;

/**
 * �ڰ����з� ���� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * 
 */
public class CertCommonDTO extends BaseDTO
{
	/** ID */
	private String certListId         = "";
	/** �ڰ�������*/
	private String filterCertTypeDesc         = "";
	/** �ڰ����� */
	private String filterCertName         = "";
	/** �ڰ������� Desc */
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
