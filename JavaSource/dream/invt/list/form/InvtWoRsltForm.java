package dream.invt.list.form;

import common.struts.BaseForm;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtWoRsltDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 
 * @author  ghlee
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="invtWoRsltForm"
 */
public class InvtWoRsltForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    
    private InvtWoRsltDTO invtWoRsltDTO = new InvtWoRsltDTO();
    
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    
	public InvtWoRsltDTO getInvtWoRsltDTO()
    {
        return invtWoRsltDTO;
    }
    public void setInvtWoRsltDTO(InvtWoRsltDTO invtWoRsltDTO)
    {
        this.invtWoRsltDTO = invtWoRsltDTO;
    }
    public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO()
    {
        return maWoResultMstrCommonDTO;
    }
    public void setMaWoResultMstrCommonDTO(
            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
        this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
    }
    public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
}
