package dream.pers.priv.db.set.form;

import common.struts.BaseForm;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;

/**
 * 대시보드(Contents) - Detail Form
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContDetailForm.java,v 1.0 2018/08/03 11:11:40 nhkim8548 Exp $
 * @since 1.0
 * @struts.form name="persPrivDbSetContDetailForm"
 */
public class PersPrivDbSetContDetailForm extends BaseForm
{
	private PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = new PersPrivDbSetCommonDTO();
	private PersPrivDbSetContListDTO persPrivDbSetContListDTO = new PersPrivDbSetContListDTO();
	private PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO = new PersPrivDbSetContDetailDTO();
    
	public PersPrivDbSetCommonDTO getPersPrivDbSetCommonDTO()
    {
        return persPrivDbSetCommonDTO;
    }
    public void setPersPrivDbSetCommonDTO(
            PersPrivDbSetCommonDTO persPrivDbSetCommonDTO)
    {
        this.persPrivDbSetCommonDTO = persPrivDbSetCommonDTO;
    }
    public PersPrivDbSetContListDTO getPersPrivDbSetContListDTO()
    {
        return persPrivDbSetContListDTO;
    }
    public void setPersPrivDbSetContListDTO(
            PersPrivDbSetContListDTO persPrivDbSetContListDTO)
    {
        this.persPrivDbSetContListDTO = persPrivDbSetContListDTO;
    }
    public PersPrivDbSetContDetailDTO getPersPrivDbSetContDetailDTO()
    {
        return persPrivDbSetContDetailDTO;
    }
    public void setPersPrivDbSetContDetailDTO(
            PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO)
    {
        this.persPrivDbSetContDetailDTO = persPrivDbSetContDetailDTO;
    }
}
