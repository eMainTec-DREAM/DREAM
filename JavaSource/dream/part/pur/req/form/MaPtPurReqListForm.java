package dream.part.pur.req.form;

import common.struts.BaseForm;
import dream.part.pur.req.dto.MaPtReqCommonDTO;

/**
 * ��ǰ���� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtPurReqListForm"
 */
public class MaPtPurReqListForm extends BaseForm
{    
    //===============================================================
    /** ��ǰ���� ���� */
    private MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
    
	public MaPtReqCommonDTO getMaPtReqCommonDTO() {
		return maPtReqCommonDTO;
	}

	public void setMaPtReqCommonDTO(MaPtReqCommonDTO maPtReqCommonDTO) {
		this.maPtReqCommonDTO = maPtReqCommonDTO;
	}
}
