package dream.part.pur.buy.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.pur.buy.dto.LovPtprAcListDTO;

/**
 * 구매신청item- 목록
 * @author  kim21017
 * @version $Id: MaPtBuyReqListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovPtprAcListForm"
 */
public class LovPtprAcListForm extends MaFinderAcForm
{    
    LovPtprAcListDTO lovPtprAcListDTO = new LovPtprAcListDTO();

    public LovPtprAcListDTO getLovPtprAcListDTO()
    {
        return lovPtprAcListDTO;
    }

    public void setLovPtprAcListDTO(LovPtprAcListDTO lovPtprAcListDTO)
    {
        this.lovPtprAcListDTO = lovPtprAcListDTO;
    }
}
