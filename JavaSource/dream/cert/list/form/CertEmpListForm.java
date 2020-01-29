package dream.cert.list.form;

import common.struts.BaseForm;

import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertEmpListDTO;

/**
 * �ڰ��� ��� ���
 * @author  kim21017
 * @version $Id: CertEmpListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="certEmpListForm"
 */
public class CertEmpListForm extends BaseForm
{
    //===============================================================
    /** ���� */
    private CertCommonDTO certCommonDTO = new CertCommonDTO();
    /**  �ڰ��� ��� ���  */
    private CertEmpListDTO certEmpListDTO = new CertEmpListDTO();
    
	public CertCommonDTO getCertCommonDTO() {
		return certCommonDTO;
	}
	public void setCertCommonDTO(CertCommonDTO certCommonDTO) {
		this.certCommonDTO = certCommonDTO;
	}
	public CertEmpListDTO getCertEmpListDTO() {
		return certEmpListDTO;
	}
	public void setCertEmpListDTO(CertEmpListDTO certEmpListDTO) {
		this.certEmpListDTO = certEmpListDTO;
	}


}
