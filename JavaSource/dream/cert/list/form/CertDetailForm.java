package dream.cert.list.form;

import common.struts.BaseForm;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.dto.CertDetailDTO;

/**
 * �ڰ����з�- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="certDetailForm"
 */
public class CertDetailForm extends BaseForm
{
    //========================================================================
    /** �ڰ����з� ���� */ 
    private CertCommonDTO certCommonDTO = new CertCommonDTO();
    //========================================================================
    /** �ڰ����з� �� */
    private CertDetailDTO certDetailDTO = new CertDetailDTO();

    public CertCommonDTO getCertCommonDTO() {
		return certCommonDTO;
	}

    public void setCertCommonDTO(CertCommonDTO certCommonDTO) {
		this.certCommonDTO = certCommonDTO;
	}

	public CertDetailDTO getCertDetailDTO() {
		return certDetailDTO;
	}

	public void setCertDetailDTO(CertDetailDTO certDetailDTO) {
		this.certDetailDTO = certDetailDTO;
	}
}
