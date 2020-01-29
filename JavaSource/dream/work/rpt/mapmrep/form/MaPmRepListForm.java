package dream.work.rpt.mapmrep.form;

import common.struts.BaseForm;
import dream.work.rpt.mapmrep.dto.MaPmRepListDTO;

/**
 * 抗规荐府郴开
 * @author  kim21017
 * @version $Id: MaPmRepListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmRepListForm"
 */
public class MaPmRepListForm extends BaseForm
{    
    //===============================================================
    /** 抗规荐府郴开 */
    private MaPmRepListDTO maPmRepListDTO = new MaPmRepListDTO();

	public MaPmRepListDTO getMaPmRepListDTO() {
		return maPmRepListDTO;
	}

	public void setMaPmRepListDTO(MaPmRepListDTO maPmRepListDTO) {
		this.maPmRepListDTO = maPmRepListDTO;
	}

}
