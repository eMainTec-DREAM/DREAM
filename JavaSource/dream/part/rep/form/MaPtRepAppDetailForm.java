package dream.part.rep.form;

import common.struts.BaseForm;
import dream.part.rep.dto.MaPtRepAppDetailDTO;
import dream.part.rep.dto.MaPtRepCommonDTO;

/**
 * �������- �� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="maPtRepAppDetailForm"
 */
public class MaPtRepAppDetailForm extends BaseForm
{
    //========================================================================
    /** ������� ���� */ 
    private MaPtRepCommonDTO maPtRepCommonDTO = new MaPtRepCommonDTO();
    //========================================================================
    /** ������� �� */
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
