package dream.cert.list.dto;

import common.bean.BaseDTO;

/**
 * ÀÚ°ÝÁõ LOV DTO
 * @author  hyosung
 * @version $Id: LovCertListDTO.java,v 1.1 2016/01/18 09:12:12 hyosung Exp $
 * @since   1.0
 */
public class LovCertListDTO extends BaseDTO
{
    /** Search Code */
    private String certNo 		= "";
    /** Search CertName */
    private String certName 	= "";
    /** Search Code */
    private String isUse 		= "";
    
	
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
    public String getCertName()
    {
        return certName;
    }
    public void setCertName(String certName)
    {
        this.certName = certName;
    }
	
	
    
}
