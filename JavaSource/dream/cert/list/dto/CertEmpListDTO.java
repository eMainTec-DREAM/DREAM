package dream.cert.list.dto;

import common.bean.BaseDTO;

/**
 * �ڰ��� ��� ��� DTO
 * @author  kim21017
 * @version $Id: CertEmpListDTO.java,v 1.1 2015/12/04 09:10:45 kim21017 Exp $
 * @since   1.0
 */
public class CertEmpListDTO extends BaseDTO
{
	
	/** �ڰ��� ��� �۾��� id */
	private String empCertListId 	= "";
	
	/** �ڰ��� ��� �۾��� id */
	private String certListId 	= "";
	
	

	public String getEmpCertListId() {
		return empCertListId;
	}

	public void setEmpCertListId(String empCertListId) {
		this.empCertListId = empCertListId;
		super.setAuditKey(empCertListId);
	}

	public String getCertListId() {
		return certListId;
	}

	public void setCertListId(String certListId) {
		this.certListId = certListId;
	}
	
	

	

}