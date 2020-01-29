package dream.doc.img.form;

import common.file.FileForm;
import common.struts.BaseForm;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;

/**
 * 첨부문서- 상세 Form
 * @author  kim21017
 * @version $Id: MaDocImgDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maDocImgDetailForm"
 */
public class MaDocImgDetailForm extends FileForm
{
    //========================================================================
    /** 메뉴 공통 */ 
    private MaDocImgCommonDTO maDocImgCommonDTO = new MaDocImgCommonDTO();
    //========================================================================
    /** 메뉴 상세 */
    private MaDocImgDetailDTO maDocImgDetailDTO = new MaDocImgDetailDTO();
    
	public MaDocImgCommonDTO getMaDocImgCommonDTO() {
		return maDocImgCommonDTO;
	}

	public void setMaDocImgCommonDTO(MaDocImgCommonDTO maDocImgCommonDTO) {
		this.maDocImgCommonDTO = maDocImgCommonDTO;
	}

    public MaDocImgDetailDTO getMaDocImgDetailDTO()
    {
        return maDocImgDetailDTO;
    }

    public void setMaDocImgDetailDTO(MaDocImgDetailDTO maDocImgDetailDTO)
    {
        this.maDocImgDetailDTO = maDocImgDetailDTO;
    }

}
