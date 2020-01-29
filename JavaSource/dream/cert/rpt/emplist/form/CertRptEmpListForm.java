package dream.cert.rpt.emplist.form;

import common.struts.BaseForm;

import dream.cert.rpt.emplist.dto.CertRptEmpCommonDTO;

/**
 * 자격증분류 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="certRptEmpListForm"
 */
public class CertRptEmpListForm extends BaseForm
{
    //===============================================================
    /** 자격증분류 공통 */
    private CertRptEmpCommonDTO certRptEmpCommonDTO = new CertRptEmpCommonDTO();

	public CertRptEmpCommonDTO getCertRptEmpCommonDTO() {
		return certRptEmpCommonDTO;
	}

	public void setCertRptEmpCommonDTO(CertRptEmpCommonDTO certRptEmpCommonDTO) {
		this.certRptEmpCommonDTO = certRptEmpCommonDTO;
	}
    
    

}
