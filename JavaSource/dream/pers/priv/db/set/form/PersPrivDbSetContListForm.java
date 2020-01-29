package dream.pers.priv.db.set.form;

import common.struts.BaseForm;
import dream.pers.priv.db.set.dto.PersPrivDbSetCommonDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContDetailDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetContListDTO;
import dream.pers.priv.db.set.dto.PersPrivDbSetDetailDTO;

/**
 * PersPrivDbSetCont Page - List Form
 * @author nhkim8548
 * @version $Id: PersPrivDbSetContListForm.java,v 1.0 2018/08/03 11:09:40 nhkim8548 Exp $
 * @since 1.0
 * @struts.form name="persPrivDbSetContListForm"
 * */
public class PersPrivDbSetContListForm extends BaseForm {
    
    private PersPrivDbSetCommonDTO persPrivDbSetCommonDTO = new PersPrivDbSetCommonDTO();
    private PersPrivDbSetDetailDTO persPrivDbSetDetailDTO = new PersPrivDbSetDetailDTO();
    private PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO = new PersPrivDbSetContDetailDTO();
    private PersPrivDbSetContListDTO persPrivDbSetContListDTO = new PersPrivDbSetContListDTO();
    
    public PersPrivDbSetContDetailDTO getPersPrivDbSetContDetailDTO()
    {
        return persPrivDbSetContDetailDTO;
    }
    public void setPersPrivDbSetContDetailDTO(
            PersPrivDbSetContDetailDTO persPrivDbSetContDetailDTO)
    {
        this.persPrivDbSetContDetailDTO = persPrivDbSetContDetailDTO;
    }
    public PersPrivDbSetDetailDTO getPersPrivDbSetDetailDTO()
    {
        return persPrivDbSetDetailDTO;
    }
    public void setPersPrivDbSetDetailDTO(
            PersPrivDbSetDetailDTO persPrivDbSetDetailDTO)
    {
        this.persPrivDbSetDetailDTO = persPrivDbSetDetailDTO;
    }
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
    
}