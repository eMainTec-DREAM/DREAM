package dream.part.rec.form;

import common.struts.BaseForm;
import dream.part.rec.dto.MaPtRecSerialDetailDTO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;

/**
 * �����԰�item
 * @author  kim2107
 * @version $Id: MaPtRecSerialDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPtRecSerialDetailForm"
 */
public class MaPtRecSerialDetailForm extends BaseForm
{    
    //===============================================================
    /** �����԰� - ���� DTO */
    private MaPtRecCommonDTO maPtRecCommonDTO = new MaPtRecCommonDTO();
	/** �����԰�item  DTO  */
    private MaPtRecSerialListDTO maPtRecSerialListDTO = new MaPtRecSerialListDTO();
	/** �����԰�item  Detail DTO  */
    private MaPtRecSerialDetailDTO maPtRecSerialDetailDTO = new MaPtRecSerialDetailDTO();
    
	public MaPtRecSerialListDTO getMaPtRecSerialListDTO() {
		return maPtRecSerialListDTO;
	}
	public void setMaPtRecSerialListDTO(MaPtRecSerialListDTO maPtRecSerialListDTO) {
		this.maPtRecSerialListDTO = maPtRecSerialListDTO;
	}
	public MaPtRecSerialDetailDTO getMaPtRecSerialDetailDTO() {
		return maPtRecSerialDetailDTO;
	}
	public void setMaPtRecSerialDetailDTO(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO) {
		this.maPtRecSerialDetailDTO = maPtRecSerialDetailDTO;
	}
	public MaPtRecCommonDTO getMaPtRecCommonDTO() {
		return maPtRecCommonDTO;
	}
	public void setMaPtRecCommonDTO(MaPtRecCommonDTO maPtRecCommonDTO) {
		this.maPtRecCommonDTO = maPtRecCommonDTO;
	}
	
}