package dream.part.pur.buy.form;

import common.struts.BaseForm;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;

/**
 * ���Ž�û - ��� form
 * @author  kim21017
 * @version $Id: MaPtBuyReqHdrListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPtBuyReqHdrListForm"
 */
public class MaPtBuyReqHdrListForm extends BaseForm
{    
    //===============================================================
    /** ���Ž�û ���� */
    private MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
    
	public MaPtBuyReqHdrCommonDTO getMaPtBuyReqHdrCommonDTO() {
		return maPtBuyReqHdrCommonDTO;
	}

	public void setMaPtBuyReqHdrCommonDTO(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO) {
		this.maPtBuyReqHdrCommonDTO = maPtBuyReqHdrCommonDTO;
	}
}
