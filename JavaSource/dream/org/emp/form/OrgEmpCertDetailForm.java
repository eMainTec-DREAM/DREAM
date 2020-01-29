package dream.org.emp.form;

import common.struts.BaseForm;
import dream.org.emp.dto.OrgEmpCertDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpCertListDTO;

/**
 * 구매신청item
 * @author  kim2107
 * @version $Id: PartAdjStkTakeDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="orgEmpCertDetailForm"
 */
public class OrgEmpCertDetailForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 - 공통 DTO */
    private MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
	/** 구매신청item  DTO  */
    private OrgEmpCertListDTO orgEmpCertListDTO = new OrgEmpCertListDTO();
	/** 구매신청item  Detail DTO  */
    private OrgEmpCertDetailDTO orgEmpCertDetailDTO = new OrgEmpCertDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public OrgEmpCertListDTO getOrgEmpCertListDTO() {
		return orgEmpCertListDTO;
	}
	public void setOrgEmpCertListDTO(OrgEmpCertListDTO orgEmpCertListDTO) {
		this.orgEmpCertListDTO = orgEmpCertListDTO;
	}
	public OrgEmpCertDetailDTO getOrgEmpCertDetailDTO() {
		return orgEmpCertDetailDTO;
	}
	public void setOrgEmpCertDetailDTO(OrgEmpCertDetailDTO orgEmpCertDetailDTO) {
		this.orgEmpCertDetailDTO = orgEmpCertDetailDTO;
	}
	public MaEmpCommonDTO getMaEmpCommonDTO() {
		return maEmpCommonDTO;
	}
	public void setMaEmpCommonDTO(MaEmpCommonDTO maEmpCommonDTO) {
		this.maEmpCommonDTO = maEmpCommonDTO;
	}
	
}