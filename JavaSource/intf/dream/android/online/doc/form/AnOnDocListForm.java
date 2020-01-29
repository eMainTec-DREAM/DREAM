package intf.dream.android.online.doc.form;

import common.struts.BaseForm;
import intf.dream.android.online.doc.dto.AnOnDocCommonDTO;

/**
 * form
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 *
 * @struts.form name="anOnDocListForm"
 */
public class AnOnDocListForm extends BaseForm {
	// ===============================================================
	/** °øÅë */
	private AnOnDocCommonDTO anOnDocCommonDTO = new AnOnDocCommonDTO();

	public AnOnDocCommonDTO getAnOnDocCommonDTO() {
		return anOnDocCommonDTO;
	}

	public void setAnOnDocCommonDTO(AnOnDocCommonDTO anOnDocCommonDTO) {
		this.anOnDocCommonDTO = anOnDocCommonDTO;
	}

}
