package dream.ass.base.form;

import common.struts.BaseForm;
import dream.ass.base.dto.AssBaseCommonDTO;
import dream.ass.base.dto.AssBaseDetailDTO;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * 설비등급 평가기준 - Detail Form
 * @author kim21017
 * @version $Id: AssBaseDetailForm.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="assBaseDetailForm"
 */
public class AssBaseDetailForm extends BaseForm
{
	private AssBaseCommonDTO assBaseCommonDTO = new AssBaseCommonDTO();
	private AssBaseDetailDTO assBaseDetailDTO = new AssBaseDetailDTO();
    /** 첨부문서 DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public AssBaseCommonDTO getAssBaseCommonDTO() {
		return assBaseCommonDTO;
	}
	public void setAssBaseCommonDTO(AssBaseCommonDTO assBaseCommonDTO) {
		this.assBaseCommonDTO = assBaseCommonDTO;
	}
	public AssBaseDetailDTO getAssBaseDetailDTO() {
		return assBaseDetailDTO;
	}
	public void setAssBaseDetailDTO(AssBaseDetailDTO assBaseDetailDTO) {
		this.assBaseDetailDTO = assBaseDetailDTO;
	}
}
