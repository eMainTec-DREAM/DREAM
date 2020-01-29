package dream.asset.loc.list.form;

import dream.asset.loc.list.dto.LovEqLocListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * ��ġ�з��˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqLocListForm"
 */
public class LovEqLocListForm extends MaFinderAcForm
{
    /** ��ġ�з��˾� DTO */
    private LovEqLocListDTO lovEqLocListDTO = new LovEqLocListDTO();

	public LovEqLocListDTO getLovEqLocListDTO() 
	{
		return lovEqLocListDTO;
	}

	public void setLovEqLocListDTO(LovEqLocListDTO lovEqLocListDTO) 
	{
		this.lovEqLocListDTO = lovEqLocListDTO;
	}
}
