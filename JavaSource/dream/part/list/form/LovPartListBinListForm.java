package dream.part.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.part.list.dto.LovPartListBinListDTO;

/**
 * 점검항목 Form
 * @author  euna0207
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovPartListBinListForm"
 */
public class LovPartListBinListForm extends MaFinderAcForm
{
    /** 설비종류별부품 DTO */
    private LovPartListBinListDTO lovPartListBinListDTO = new LovPartListBinListDTO();

    public LovPartListBinListDTO getLovPartListBinListDTO()
    {
        return lovPartListBinListDTO;
    }

    public void setLovPartListBinListDTO(LovPartListBinListDTO lovPartListBinListDTO)
    {
        this.lovPartListBinListDTO = lovPartListBinListDTO;
    }

}
