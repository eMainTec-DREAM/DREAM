package dream.work.rpt.mapmpoint.form;

import common.struts.BaseForm;
import dream.work.rpt.mapmpoint.dto.MaPmPointListDTO;

/**
 * 예방점검내역
 * @author  kim21017
 * @version $Id: MaPmPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmPointListForm"
 */
public class MaPmPointListForm extends BaseForm
{    
    //===============================================================
    /** 예방점검내역 */
    private MaPmPointListDTO maPmPointListDTO = new MaPmPointListDTO();

	public MaPmPointListDTO getMaPmPointListDTO() {
		return maPmPointListDTO;
	}

	public void setMaPmPointListDTO(MaPmPointListDTO maPmPointListDTO) {
		this.maPmPointListDTO = maPmPointListDTO;
	}

}
