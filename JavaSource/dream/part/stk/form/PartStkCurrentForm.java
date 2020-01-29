package dream.part.stk.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.part.stk.dto.PartStkCurrentDTO;

/**
 * Form
 * @author  euna0207
 * @version $Id$
 * @since   1.0
 *
 * @struts.form name="partStkCurrentForm"
 */
public class PartStkCurrentForm extends BaseForm
{    
    //===============================================================
    /** °øÅë DTO */
    private PartStkCurrentDTO partStkCurrentDTO = new PartStkCurrentDTO();
    private MaPtMstrDetailDTO maPtMstrDetailDTO = new MaPtMstrDetailDTO();
    private MaPtStckDetailDTO maPtStckDetailDTO = new MaPtStckDetailDTO();
    
	public MaPtMstrDetailDTO getMaPtMstrDetailDTO() {
		return maPtMstrDetailDTO;
	}

	public void setMaPtMstrDetailDTO(MaPtMstrDetailDTO maPtMstrDetailDTO) {
		this.maPtMstrDetailDTO = maPtMstrDetailDTO;
	}

	public MaPtStckDetailDTO getMaPtStckDetailDTO() {
		return maPtStckDetailDTO;
	}

	public void setMaPtStckDetailDTO(MaPtStckDetailDTO maPtStckDetailDTO) {
		this.maPtStckDetailDTO = maPtStckDetailDTO;
	}

	public PartStkCurrentDTO getPartStkCurrentDTO() {
		return partStkCurrentDTO;
	}

	public void setPartStkCurrentDTO(PartStkCurrentDTO partStkCurrentDTO) {
		this.partStkCurrentDTO = partStkCurrentDTO;
	}
	
}
