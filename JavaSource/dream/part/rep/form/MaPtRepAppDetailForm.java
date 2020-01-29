package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * 수리기안- 상세 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepAppDetailForm"
 */
public class MaPtRepAppDetailForm extends BaseForm
{
    //========================================================================
    /** 수리기안 공통 */ 
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    //========================================================================
    /** 수리기안 상세 */
    private MaPtRepAppDetailDTO maPtRepAppDetailDTO = new MaPtRepAppDetailDTO();

    public MaPtRepCommonDTO getMaPtRepCommonDTO() 
    {
		return maPtRepCommonDTO;
	}

    public void setMaPtRepCommonDTO(MaPtRepCommonDTO maPtRepCommonDTO) 
    {
		this.maPtRepCommonDTO = maPtRepCommonDTO;
	}

	public MaPtRepAppDetailDTO getMaPtRepAppDetailDTO() 
	{
		return maPtRepAppDetailDTO;
	}

	public void setMaPtRepAppDetailDTO(MaPtRepAppDetailDTO maPtRepAppDetailDTO) 
	{
		this.maPtRepAppDetailDTO = maPtRepAppDetailDTO;
	}
}
