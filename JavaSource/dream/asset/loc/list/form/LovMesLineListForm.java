package dream.asset.loc.list.form;

import dream.asset.loc.list.dto.LovMesLineListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * MESLINE ÆË¾÷ Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovMesLineListForm"
 */
public class LovMesLineListForm extends MaFinderAcForm
{
    /** MESLINEÆË¾÷ DTO */
    private LovMesLineListDTO lovMesLineListDTO = new LovMesLineListDTO();

	public LovMesLineListDTO getLovMesLineListDTO() 
	{
		return lovMesLineListDTO;
	}

	public void setLovMesLineListDTO(LovMesLineListDTO lovMesLineListDTO) 
	{
		this.lovMesLineListDTO = lovMesLineListDTO;
	}
}
