package dream.pers.priv.db.set.form;

import dream.comm.form.MaFinderAcForm;
import dream.pers.priv.db.set.dto.LovDbConAcListDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;

/**
 * Contents Lov Form
 * @author  nhkim8548
 * @version $Id: LovDbConAcListForm.java,v 1.0 2018/08/06 09:26:40 nhkim8548 Exp $
 * @since   1.0
 *
 * @struts.form name="lovDbConAcListForm"
 */
public class LovDbConAcListForm extends MaFinderAcForm
{
	private LovDbConAcListDTO lovDbConAcListDTO  = new LovDbConAcListDTO();
	private PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = new PersPrivDbSetCommonDTO();
	private PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO = new PersPrivDbSetContDetailDTO();
	
	public PersPrivDbSetContDetailDTO getPersPrivDbSetContDetailDTO()
    {
        return persPrivDbSetContDetailDTO;
    }

    public void setPersPrivDbSetContDetailDTO(PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO)
    {
        this.persPrivDbSetContDetailDTO = persPrivDbSetContDetailDTO;
    }

    public PersPrivDbSetCommonDTO getPersPrivDbSetCommonDTO()
    {
        return persPrivDbSetCommonDTO;
    }

    public void setPersPrivDbSetCommonDTO(PersPrivDbSetCommonDTO persPrivDbSetCommonDTO)
    {
        this.persPrivDbSetCommonDTO = persPrivDbSetCommonDTO;
    }

    public LovDbConAcListDTO getLovDbConAcListDTO() {
		return lovDbConAcListDTO;
	}

	public void setLovDbConAcListDTO(LovDbConAcListDTO lovDbConAcListDTO) {
		this.lovDbConAcListDTO = lovDbConAcListDTO;
	}
}
