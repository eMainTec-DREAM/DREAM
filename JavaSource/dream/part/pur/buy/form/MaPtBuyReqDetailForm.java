package dream.part.pur.buy.form;

import common.struts.BaseForm;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * ���Ž�ûitem
 * @author  kim2107
 * @version $Id: MaPtBuyReqDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPtBuyReqDetailForm"
 */
public class MaPtBuyReqDetailForm extends BaseForm
{    
    //===============================================================
    /** ���Ž�û - ���� DTO */
    private MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
	/** ���Ž�ûitem  DTO  */
    private MaPtBuyReqListDTO maPtBuyReqListDTO = new MaPtBuyReqListDTO();
	/** ���Ž�ûitem  Detail DTO  */
    private MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = new MaPtBuyReqDetailDTO();
    
	public MaPtBuyReqListDTO getMaPtBuyReqListDTO() {
		return maPtBuyReqListDTO;
	}
	public void setMaPtBuyReqListDTO(MaPtBuyReqListDTO maPtBuyReqListDTO) {
		this.maPtBuyReqListDTO = maPtBuyReqListDTO;
	}
	public MaPtBuyReqDetailDTO getMaPtBuyReqDetailDTO() {
		return maPtBuyReqDetailDTO;
	}
	public void setMaPtBuyReqDetailDTO(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO) {
		this.maPtBuyReqDetailDTO = maPtBuyReqDetailDTO;
	}
	public MaPtBuyReqHdrCommonDTO getMaPtBuyReqHdrCommonDTO() {
		return maPtBuyReqHdrCommonDTO;
	}
	public void setMaPtBuyReqHdrCommonDTO(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO) {
		this.maPtBuyReqHdrCommonDTO = maPtBuyReqHdrCommonDTO;
	}
	
}