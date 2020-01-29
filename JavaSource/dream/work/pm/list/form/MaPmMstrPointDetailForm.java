package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;

/**
 * �˻��׸�
 * @author  jung7126
 * @version $Id: MaPmMstrPointDetailForm.java,v 1.0 2015/12/04 09:09:54 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmMstrPointDetailForm"
 */
public class MaPmMstrPointDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
	/** �˻��׸� �� DTO  */
    private MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = new MaPmMstrPointDetailDTO();

	public MaPmMstrPointDetailDTO getMaPmMstrPointDetailDTO() {
		return maPmMstrPointDetailDTO;
	}
	public void setMaPmMstrPointDetailDTO(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO) {
		this.maPmMstrPointDetailDTO = maPmMstrPointDetailDTO;
	}
    public MaPmMstrCommonDTO getMaPmMstrCommonDTO()
    {
        return maPmMstrCommonDTO;
    }
    public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO)
    {
        this.maPmMstrCommonDTO = maPmMstrCommonDTO;
    }

	
}
