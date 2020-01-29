package dream.pers.priv.db.set.form;

import common.struts.BaseForm;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;

/**
 * Guide Page - Detail Form
 * @author nhkim8548
 * @version $Id: PersPrivDbSetDetailForm.java,v 1.0 2018/07/31 13:25:40 nhkim8548 Exp $
 * @since 1.0
 * @struts.form name="persPrivDbSetDetailForm"
 */
public class PersPrivDbSetDetailForm extends BaseForm
{
	private PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = new PersPrivDbSetCommonDTO();
	private PersPrivDbSetDetailDTO persPrivDbSetDetailDTO = new PersPrivDbSetDetailDTO();
    
	public PersPrivDbSetCommonDTO getPersPrivDbSetCommonDTO() {
		return persPrivDbSetCommonDTO;
	}
	public void setPersPrivDbSetCommonDTO(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO) {
		this.persPrivDbSetCommonDTO = persPrivDbSetCommonDTO;
	}
	public PersPrivDbSetDetailDTO getPersPrivDbSetDetailDTO() {
		return persPrivDbSetDetailDTO;
	}
	public void setPersPrivDbSetDetailDTO(PersPrivDbSetDetailDTO persPrivDbSetDetailDTO) {
		this.persPrivDbSetDetailDTO = persPrivDbSetDetailDTO;
	}
}
