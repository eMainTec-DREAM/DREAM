package dream.cert.list.form;

import common.struts.BaseForm;
import dream.cert.list.dto.CertCommonDTO;

/**
 * �ڰ����з� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="certListForm"
 */
public class CertListForm extends BaseForm
{    
    //===============================================================
    /** �ڰ����з� ���� */
    private CertCommonDTO certCommonDTO = new CertCommonDTO();
    
	public CertCommonDTO getCertCommonDTO() {
		return certCommonDTO;
	}

	public void setCertCommonDTO(CertCommonDTO certCommonDTO) {
		this.certCommonDTO = certCommonDTO;
	}
}
