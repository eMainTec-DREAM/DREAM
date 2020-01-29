package dream.invt.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;

/**
 * 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="invtEquipListForm"
 */
public class InvtEquipListForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
}
