package dream.asset.categ.list.form;

import dream.asset.categ.list.dto.LovEqCtgAsmbListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * ���������۾����� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqCtgAsmbListForm"
 */
public class LovEqCtgAsmbListForm extends MaFinderAcForm
{
    /** ���������۾����� DTO */
    private LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO = new LovEqCtgAsmbListDTO();

	public LovEqCtgAsmbListDTO getLovEqCtgAsmbListDTO() 
	{
		return lovEqCtgAsmbListDTO;
	}

	public void setLovEqCtgAsmbListDTO(LovEqCtgAsmbListDTO lovEqCtgAsmbListDTO) 
	{
		this.lovEqCtgAsmbListDTO = lovEqCtgAsmbListDTO;
	}
}
