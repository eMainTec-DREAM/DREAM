package dream.cert.rpt.emplist.form;

import common.struts.BaseForm;

import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;

/**
 * �ڰ����з� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="certRptEmpListForm"
 */
public class CertRptEmpListForm extends BaseForm
{
    //===============================================================
    /** �ڰ����з� ���� */
    private CertRptEmpCommonDTO certRptEmpCommonDTO = new CertRptEmpCommonDTO();

	public CertRptEmpCommonDTO getCertRptEmpCommonDTO() {
		return certRptEmpCommonDTO;
	}

	public void setCertRptEmpCommonDTO(CertRptEmpCommonDTO certRptEmpCommonDTO) {
		this.certRptEmpCommonDTO = certRptEmpCommonDTO;
	}
    
    

}
