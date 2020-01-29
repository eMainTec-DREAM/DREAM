package intf.dream.bee.doc.form;

import common.struts.BaseForm;
import intf.dream.bee.doc.dto.BeeDocCommonDTO;

/**
 * form
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 *
 * @struts.form name="beeDocListForm"
 */
public class BeeDocListForm extends BaseForm {
	// ===============================================================
	/** °øÅë */
	private BeeDocCommonDTO beeDocCommonDTO = new BeeDocCommonDTO();

	public BeeDocCommonDTO getBeeDocCommonDTO() {
		return beeDocCommonDTO;
	}

	public void setBeeDocCommonDTO(BeeDocCommonDTO beeDocCommonDTO) {
		this.beeDocCommonDTO = beeDocCommonDTO;
	}

}
