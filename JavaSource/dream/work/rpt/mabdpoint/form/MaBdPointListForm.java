package dream.work.rpt.mabdpoint.form;

import common.struts.BaseForm;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;

/**
 * �̻�������ġ - ��� form
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maBdPointListForm"
 */
public class MaBdPointListForm extends BaseForm
{    
    //===============================================================
    /** �̻�������ġ ���� */
    private MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
    
	public MaBdPointCommonDTO getMaBdPointCommonDTO() {
		return maBdPointCommonDTO;
	}

	public void setMaBdPointCommonDTO(MaBdPointCommonDTO maBdPointCommonDTO) {
		this.maBdPointCommonDTO = maBdPointCommonDTO;
	}
}
