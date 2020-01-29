package dream.org.emp.form;

import common.struts.BaseForm;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;

/**
 * 구매신청item- 목록
 * @author  kim21017
 * @version $Id: PartAdjStkTakeListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="orgEmpCertListForm"
 */
public class OrgEmpCertListForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 공통 */
    private MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
    /** 구매신청item  */
    private OrgEmpCertListDTO orgEmpCertListDTO = new OrgEmpCertListDTO();
    
	public MaEmpCommonDTO getMaEmpCommonDTO() {
		return maEmpCommonDTO;
	}

	public void setMaEmpCommonDTO(MaEmpCommonDTO maEmpCommonDTO) {
		this.maEmpCommonDTO = maEmpCommonDTO;
	}

	public OrgEmpCertListDTO getOrgEmpCertListDTO() {
		return orgEmpCertListDTO;
	}

	public void setOrgEmpCertListDTO(OrgEmpCertListDTO orgEmpCertListDTO) {
		this.orgEmpCertListDTO = orgEmpCertListDTO;
	}
}
