package dream.doc.file.form;

import common.file.FileForm;
import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.dto.MaDocLibDetailDTO;

/**
 * 첨부문서- 상세 Form
 * @author  kim21017
 * @version $Id: MaDocLibDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maDocLibDetailForm"
 */
public class MaDocLibDetailForm extends FileForm
{
    //========================================================================
    /** 메뉴 공통 */ 
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    //========================================================================
    /** 메뉴 상세 */
    private MaDocLibDetailDTO maDocLibDetailDTO = new MaDocLibDetailDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

	public MaDocLibDetailDTO getMaDocLibDetailDTO() {
		return maDocLibDetailDTO;
	}

	public void setMaDocLibDetailDTO(MaDocLibDetailDTO maDocLibDetailDTO) {
		this.maDocLibDetailDTO = maDocLibDetailDTO;
	}

}
