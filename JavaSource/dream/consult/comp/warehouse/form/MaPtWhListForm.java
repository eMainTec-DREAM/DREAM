package dream.consult.comp.warehouse.form;

import common.struts.BaseForm;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;

/**
 * ������� - ��� form
 * @author  ssong
 * @version $Id: MaPtWhListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="maPtWhListForm"
 */
public class MaPtWhListForm extends BaseForm
{    
    //===============================================================
    /** ������� ���� */
    private MaPtWhCommonDTO maPtWhCommonDTO = new MaPtWhCommonDTO();
    
	public MaPtWhCommonDTO getMaPtWhCommonDTO() {
		return maPtWhCommonDTO;
	}

	public void setMaPtWhCommonDTO(MaPtWhCommonDTO maPtWhCommonDTO) {
		this.maPtWhCommonDTO = maPtWhCommonDTO;
	}
}
