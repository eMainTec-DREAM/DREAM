package dream.doc.file.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;

/**
 * ÷�ι��� - ��� form
 * @author  jung7126
 * @version $Id: MaDocLibListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maDocLibListForm"
 */
public class MaDocLibListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}

	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}

}
