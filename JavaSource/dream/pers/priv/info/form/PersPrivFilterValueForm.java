package dream.pers.priv.info.form;

import common.struts.BaseForm;
import dream.pers.priv.info.dto.MaMyInfoCommonDTO;
import dream.pers.priv.info.dto.PersPrivFilterValueDTO;

/**
 * Form
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="persPrivFilterValueForm"
 */
public class PersPrivFilterValueForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private MaMyInfoCommonDTO maMyInfoCommonDTO = new MaMyInfoCommonDTO();
    private PersPrivFilterValueDTO persPrivFilterValueDTO = new PersPrivFilterValueDTO();
    
	public MaMyInfoCommonDTO getMaMyInfoCommonDTO() {
		return maMyInfoCommonDTO;
	}

	public void setMaMyInfoCommonDTO(MaMyInfoCommonDTO maMyInfoCommonDTO) {
		this.maMyInfoCommonDTO = maMyInfoCommonDTO;
	}

	public PersPrivFilterValueDTO getPersPrivFilterValueDTO() {
		return persPrivFilterValueDTO;
	}

	public void setPersPrivFilterValueDTO(PersPrivFilterValueDTO persPrivFilterValueDTO) {
		this.persPrivFilterValueDTO = persPrivFilterValueDTO;
	}
	
}
