package dream.part.stk.form;

import common.struts.BaseForm;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.part.stk.dto.PartStkSerialListDTO;


/**
 * 자재재고 - 목록 form
 * @author  hyosung
 * @version $Id: PartStkSerialListForm.java,v 1.0 2015/12/01 09:13:09 ssong Exp $
 * @since   1.0
 *
 * @struts.form name="partStkSerialListForm"
 */
public class PartStkSerialListForm extends BaseForm
{    
    //===============================================================
    /** 자재재고 공통 */
    private MaPtStckCommonDTO maPtStckCommonDTO=new MaPtStckCommonDTO();
    private MaPtStckDetailDTO maPtStckDetailDTO=new MaPtStckDetailDTO();
    private PartStkSerialListDTO partStkSerialListDTO = new PartStkSerialListDTO();
    public MaPtStckCommonDTO getMaPtStckCommonDTO()
    {
        return maPtStckCommonDTO;
    }
    public void setMaPtStckCommonDTO(MaPtStckCommonDTO maPtStckCommonDTO)
    {
        this.maPtStckCommonDTO = maPtStckCommonDTO;
    }
    public MaPtStckDetailDTO getMaPtStckDetailDTO()
    {
        return maPtStckDetailDTO;
    }
    public void setMaPtStckDetailDTO(MaPtStckDetailDTO maPtStckDetailDTO)
    {
        this.maPtStckDetailDTO = maPtStckDetailDTO;
    }
    public PartStkSerialListDTO getPartStkSerialListDTO()
    {
        return partStkSerialListDTO;
    }
    public void setPartStkSerialListDTO(PartStkSerialListDTO partStkSerialListDTO)
    {
        this.partStkSerialListDTO = partStkSerialListDTO;
    }
    

    
    

}
