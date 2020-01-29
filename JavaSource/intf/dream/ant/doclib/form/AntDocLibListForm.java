package intf.dream.ant.doclib.form;

import common.file.FileForm;
import intf.dream.ant.doclib.dto.AntDocLibCommonDTO;

/**
 * 첨부문서 - 목록 form
 * @author  kim21017
 * @version $Id: AntDocLibListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="antDocLibListForm"
 */
public class AntDocLibListForm extends FileForm
{    
    //===============================================================
    /** 공통 */
    private AntDocLibCommonDTO antDocLibCommonDTO = new AntDocLibCommonDTO();

    
	public AntDocLibCommonDTO getAntDocLibCommonDTO() {
		return antDocLibCommonDTO;
	}

	public void setAntDocLibCommonDTO(AntDocLibCommonDTO antDocLibCommonDTO) {
		this.antDocLibCommonDTO = antDocLibCommonDTO;
	}

}
