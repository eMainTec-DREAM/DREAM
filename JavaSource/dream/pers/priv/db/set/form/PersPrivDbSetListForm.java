package dream.pers.priv.db.set.form;

import common.struts.BaseForm;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
/**
 * Guide Page - List Form
 * @author nhkim8548
 * @version $Id: PersPrivDbSetListForm.java,v 1.0 2018/08/06 09:12:40 kim21017 Exp $
 * @since 1.0
 * @struts.form name="persPrivDbSetListForm"
 * */

public class PersPrivDbSetListForm extends BaseForm{
	
	private PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = new PersPrivDbSetCommonDTO();

	public PersPrivDbSetCommonDTO getPersPrivDbSetCommonDTO() {
		return persPrivDbSetCommonDTO;
	}

	public void setPersPrivDbSetCommonDTO(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO) {
		this.persPrivDbSetCommonDTO = persPrivDbSetCommonDTO;
	}
	
}
