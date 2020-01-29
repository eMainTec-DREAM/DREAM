package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.MaPtRepAppListDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안 - 목록 form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepAppListForm"
 */
public class MaPtRepAppListForm extends BaseForm
{    
    //===============================================================
    /** 부품수리 공통 */
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    /** 수리기안 목록  */
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
