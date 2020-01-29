package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.list.dto.MaPtMstrStockListDTO;
import dream.part.stk.dto.MaPtStckCommonDTO;

/**
 * 자재재고 - 목록 form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrStockListForm"
 */
public class MaPtMstrStockListForm extends BaseForm
{    
    //===============================================================
    /** 부품마스터 공통 */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    /** 자재재고 공통 */
    private MaPtMstrStockListDTO maPtMstrStockListDTO = new MaPtMstrStockListDTO();
    /** 현재고 상세 페이지 조회용 */
    private MaPtStckCommonDTO maPtStckCommonDTO = new MaPtStckCommonDTO();
    
    /** 자재마스터 상세 */
    private MaPtMstrDetailDTO maPtMstrDetailDTO = new MaPtMstrDetailDTO();
    
    public MaPtMstrDetailDTO getMaPtMstrDetailDTO() {
		return maPtMstrDetailDTO;
	}

	public void setMaPtMstrDetailDTO(MaPtMstrDetailDTO maPtMstrDetailDTO) {
		this.maPtMstrDetailDTO = maPtMstrDetailDTO;
	}

	public MaPtStckCommonDTO getMaPtStckCommonDTO()
    {
        return maPtStckCommonDTO;
    }

    public void setMaPtStckCommonDTO(MaPtStckCommonDTO maPtStckCommonDTO)
    {
        this.maPtStckCommonDTO = maPtStckCommonDTO;
    }

    public MaPtMstrCommonDTO getMaPtMstrCommonDTO()
    {
        return maPtMstrCommonDTO;
    }

    public void setMaPtMstrCommonDTO(MaPtMstrCommonDTO maPtMstrCommonDTO)
    {
        this.maPtMstrCommonDTO = maPtMstrCommonDTO;
    }

    public MaPtMstrStockListDTO getMaPtMstrStockListDTO()
    {
        return maPtMstrStockListDTO;
    }

    public void setMaPtMstrStockListDTO(MaPtMstrStockListDTO maPtMstrStockListDTO)
    {
        this.maPtMstrStockListDTO = maPtMstrStockListDTO;
    }
    
}
