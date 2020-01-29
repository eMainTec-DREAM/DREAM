package mobile.dream.mapm.mains.form;

import common.struts.BaseForm;
import mobile.dream.mapm.mains.dto.MaPmInsCommonDTO;

/**
 * �������� - ��� form
 * @author  jung7126
 * @version $Id: MaPmInsListForm.java,v 1.0 2015/12/01 09:13:09 jung7126 Exp $
 * @since   1.0
 *
 * @struts.form name="maPmInsListForm"
 */
public class MaPmInsListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaPmInsCommonDTO maPmInsCommonDTO = new MaPmInsCommonDTO();
    
	public MaPmInsCommonDTO getMaPmInsCommonDTO() {
		return maPmInsCommonDTO;
	}

	public void setMaPmInsCommonDTO(MaPmInsCommonDTO maPmInsCommonDTO) {
		this.maPmInsCommonDTO = maPmInsCommonDTO;
	}
}
