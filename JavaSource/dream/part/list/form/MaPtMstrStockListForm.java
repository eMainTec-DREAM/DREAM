package dream.part.list.form;

import common.struts.BaseForm;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.list.dto.MaPtMstrStockListDTO;
import dream.part.stk.dto.MaPtStckCommonDTO;

/**
 * ������� - ��� form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtMstrStockListForm"
 */
public class MaPtMstrStockListForm extends BaseForm
{    
    //===============================================================
    /** ��ǰ������ ���� */
    private MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    /** ������� ���� */
    private MaPtMstrStockListDTO maPtMstrStockListDTO = new MaPtMstrStockListDTO();
    /** ����� �� ������ ��ȸ�� */
    private MaPtStckCommonDTO maPtStckCommonDTO = new MaPtStckCommonDTO();
    
    /** ���縶���� �� */
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
