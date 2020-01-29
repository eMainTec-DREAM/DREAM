package intf.dream.android.offline.madoclib.form;

import common.file.FileForm;
import intf.dream.android.offline.madoclib.dto.AnIfDocLibCommonDTO;

/**
 * 첨부문서 - 목록 form
 * @author  kim21017
 * @version $Id: AnIfDocLibListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="anIfDocLibListForm"
 */
public class AnIfDocLibListForm extends FileForm
{    
    //===============================================================
    /** 공통 */
    private AnIfDocLibCommonDTO anIfDocLibCommonDTO = new AnIfDocLibCommonDTO();

    
	public AnIfDocLibCommonDTO getAnIfDocLibCommonDTO() {
		return anIfDocLibCommonDTO;
	}

	public void setAnIfDocLibCommonDTO(AnIfDocLibCommonDTO anIfDocLibCommonDTO) {
		this.anIfDocLibCommonDTO = anIfDocLibCommonDTO;
	}

}
