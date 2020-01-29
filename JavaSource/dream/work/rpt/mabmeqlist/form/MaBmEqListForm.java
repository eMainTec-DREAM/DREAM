package dream.work.rpt.mabmeqlist.form;

import common.struts.BaseForm;
import dream.work.rpt.mabmeqlist.dto.MaBmEqListDTO;

/**
 * 설비별고장분석
 * @author  kim21017
 * @version $Id: MaBmEqListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maBmEqListForm"
 */
public class MaBmEqListForm extends BaseForm
{    
    //===============================================================
    /** 설비별고장분석 */
    private MaBmEqListDTO maBmEqListDTO = new MaBmEqListDTO();

	public MaBmEqListDTO getMaBmEqListDTO() {
		return maBmEqListDTO;
	}

	public void setMaBmEqListDTO(MaBmEqListDTO maBmEqListDTO) {
		this.maBmEqListDTO = maBmEqListDTO;
	}

}
