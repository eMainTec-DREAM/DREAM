package dream.part.pur.buy.form;

import common.struts.BaseForm;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;

/**
 * 구매신청item- 목록
 * @author  kim21017
 * @version $Id: MaPtBuyReqListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPtBuyReqListForm"
 */
public class MaPtBuyReqListForm extends BaseForm
{    
    //===============================================================
    /** 구매신청 공통 */
    private MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
    /** 구매신청item  */
    private MaPtBuyReqListDTO maPtBuyReqListDTO = new MaPtBuyReqListDTO();
    /** 구매신청item  Detail DTO  */
    private MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = new MaPtBuyReqDetailDTO();
    
	public MaPtBuyReqDetailDTO getMaPtBuyReqDetailDTO()
    {
        return maPtBuyReqDetailDTO;
    }

    public void setMaPtBuyReqDetailDTO(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO)
    {
        this.maPtBuyReqDetailDTO = maPtBuyReqDetailDTO;
    }

    public MaPtBuyReqHdrCommonDTO getMaPtBuyReqHdrCommonDTO() {
		return maPtBuyReqHdrCommonDTO;
	}

	public void setMaPtBuyReqHdrCommonDTO(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO) {
		this.maPtBuyReqHdrCommonDTO = maPtBuyReqHdrCommonDTO;
	}

	public MaPtBuyReqListDTO getMaPtBuyReqListDTO() {
		return maPtBuyReqListDTO;
	}

	public void setMaPtBuyReqListDTO(MaPtBuyReqListDTO maPtBuyReqListDTO) {
		this.maPtBuyReqListDTO = maPtBuyReqListDTO;
	}
}
