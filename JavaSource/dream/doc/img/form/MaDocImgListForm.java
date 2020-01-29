package dream.doc.img.form;

import common.struts.BaseForm;
import dream.doc.img.dto.MaDocImgCommonDTO;

/**
 * 사진파일 - 목록 form
 * @author  jung7126
 * @version $Id: MaDocImgListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maDocImgListForm"
 */
public class MaDocImgListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaDocImgCommonDTO maDocImgCommonDTO = new MaDocImgCommonDTO();

    
	public MaDocImgCommonDTO getMaDocImgCommonDTO() {
		return maDocImgCommonDTO;
	}

	public void setMaDocImgCommonDTO(MaDocImgCommonDTO maDocImgCommonDTO) {
		this.maDocImgCommonDTO = maDocImgCommonDTO;
	}

}
