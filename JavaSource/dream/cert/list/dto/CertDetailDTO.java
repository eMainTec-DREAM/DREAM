package dream.cert.list.dto;

import common.bean.BaseDTO;
import common.util.CommonUtil;

/**
 * �ڰ����з� - �� DTO
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 */
public class CertDetailDTO extends BaseDTO
{
	/** Cert ID */
	private String certListId     = "";
	/** Cert ���� */
	private String certType     = "";
	/** Cert ��ȣ */
	private String certNo     = "";
	/** Cert ���� */
	private String certTypeDesc     = "";
	/** Cert �� */
	private String certName     = "";
	/** ������ */
	private String certAgency     = "";
	/** ��뿩�� */
	private String isUse     = "";
	/** �⺻���� */
	private String certDesc     = "";
	/** ����� */
	private String howToGet     = "";
	public String getCertListId() {
		return certListId;
	}
	public void setCertListId(String certListId) {
		this.certListId = certListId;
		super.setAuditKey(certListId);
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCertTypeDesc() {
		return certTypeDesc;
	}
	public void setCertTypeDesc(String certTypeDesc) {
		this.certTypeDesc = certTypeDesc;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getCertAgency() {
		return certAgency;
	}
	public void setCertAgency(String certAgency) {
		this.certAgency = certAgency;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCertDesc() {
		return certDesc;
	}
	public void setCertDesc(String certDesc) {
		this.certDesc = certDesc;
	}
	public String getHowToGet() {
		return howToGet;
	}
	public void setHowToGet(String howToGet) {
		this.howToGet = howToGet;
	}
	
	
}
