package dream.asset.categ.list.form;

import common.struts.BaseForm;

import dream.asset.categ.list.dto.LovEqCtgListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * 설비종류팝업 Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovEqCtgListForm"
 */
public class LovEqCtgListForm extends MaFinderAcForm
{
    /** 설비종류팝업 DTO */
    private LovEqCtgListDTO lovEqCtgListDTO = new LovEqCtgListDTO();

	public LovEqCtgListDTO getLovEqCtgListDTO() 
	{
		return lovEqCtgListDTO;
	}

	public void setLovEqCtgListDTO(LovEqCtgListDTO lovEqCtgListDTO) 
	{
		this.lovEqCtgListDTO = lovEqCtgListDTO;
	}
}
