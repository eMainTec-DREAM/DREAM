package dream.part.rec.form;

import common.struts.BaseForm;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;

/**
 * 구매입고item- 목록
 * @author  kim21017
 * @version $Id: MaPtRecSerialListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPtRecSerialListForm"
 */
public class MaPtRecSerialListForm extends BaseForm
{    
    //===============================================================
    /** 구매입고 공통 */
    private MaPtRecCommonDTO maPtRecCommonDTO = new MaPtRecCommonDTO();
    /** 구매입고item  */
    private MaPtRecSerialListDTO maPtRecSerialListDTO = new MaPtRecSerialListDTO();
    
	public MaPtRecCommonDTO getMaPtRecCommonDTO() {
		return maPtRecCommonDTO;
	}

	public void setMaPtRecCommonDTO(MaPtRecCommonDTO maPtRecCommonDTO) {
		this.maPtRecCommonDTO = maPtRecCommonDTO;
	}

	public MaPtRecSerialListDTO getMaPtRecSerialListDTO() {
		return maPtRecSerialListDTO;
	}

	public void setMaPtRecSerialListDTO(MaPtRecSerialListDTO maPtRecSerialListDTO) {
		this.maPtRecSerialListDTO = maPtRecSerialListDTO;
	}
}
