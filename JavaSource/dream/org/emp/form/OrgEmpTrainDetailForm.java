package dream.org.emp.form;

import common.struts.BaseForm;
import dream.org.emp.dto.OrgEmpTrainDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.dto.OrgEmpTrainListDTO;

/**
 * 구매신청item
 * @author  kim2107
 * @version $Id: PartAdjStkTakeDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="orgEmpTrainDetailForm"
 */
public class OrgEmpTrainDetailForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 - 공통 DTO */
    private MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
	/** 구매신청item  DTO  */
    private OrgEmpTrainListDTO orgEmpTrainListDTO = new OrgEmpTrainListDTO();
	/** 구매신청item  Detail DTO  */
    private OrgEmpTrainDetailDTO orgEmpTrainDetailDTO = new OrgEmpTrainDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public OrgEmpTrainListDTO getOrgEmpTrainListDTO() {
		return orgEmpTrainListDTO;
	}
	public void setOrgEmpTrainListDTO(OrgEmpTrainListDTO orgEmpTrainListDTO) {
		this.orgEmpTrainListDTO = orgEmpTrainListDTO;
	}
	public OrgEmpTrainDetailDTO getOrgEmpTrainDetailDTO() {
		return orgEmpTrainDetailDTO;
	}
	public void setOrgEmpTrainDetailDTO(OrgEmpTrainDetailDTO orgEmpTrainDetailDTO) {
		this.orgEmpTrainDetailDTO = orgEmpTrainDetailDTO;
	}
	public MaEmpCommonDTO getMaEmpCommonDTO() {
		return maEmpCommonDTO;
	}
	public void setMaEmpCommonDTO(MaEmpCommonDTO maEmpCommonDTO) {
		this.maEmpCommonDTO = maEmpCommonDTO;
	}
	
}