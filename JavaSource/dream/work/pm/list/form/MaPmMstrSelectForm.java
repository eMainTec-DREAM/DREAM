package dream.work.pm.list.form;

import common.struts.BaseForm;
import dream.work.pm.list.dto.MaPmMstrSelectDTO;

/**
 * �۾����� �˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
11 *
 * @struts.form name="maPmMstrSelectForm"
 */
public class MaPmMstrSelectForm extends BaseForm
{
	MaPmMstrSelectDTO maPmMstrSelectDTO  = new MaPmMstrSelectDTO();

	public MaPmMstrSelectDTO getMaPmMstrSelectDTO() {
		return maPmMstrSelectDTO;
	}

	public void setMaPmMstrSelectDTO(MaPmMstrSelectDTO maPmMstrSelectDTO) {
		this.maPmMstrSelectDTO = maPmMstrSelectDTO;
	}

	
	
	
}
