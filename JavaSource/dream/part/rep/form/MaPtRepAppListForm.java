package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * ������� - ��� form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepAppListForm"
 */
public class MaPtRepAppListForm extends BaseForm
{    
    //===============================================================
    /** ��ǰ���� ���� */
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    /** ������� ���  */
    private MaPtRepAppListDTO maPtRepAppListDTO = new MaPtRepAppListDTO();
    
	public MaPtRepCommonDTO getMaPtRepCommonDTO() 
	{
		return maPtRepCommonDTO;
	}

	public void setMaPtRepCommonDTO(MaPtRepCommonDTO maPtRepCommonDTO) 
	{
		this.maPtRepCommonDTO = maPtRepCommonDTO;
	}

    public MaPtRepAppListDTO getMaPtRepAppListDTO()
    {
        return maPtRepAppListDTO;
    }

    public void setMaPtRepAppListDTO(MaPtRepAppListDTO maPtRepAppListDTO)
    {
        this.maPtRepAppListDTO = maPtRepAppListDTO;
    }
}
