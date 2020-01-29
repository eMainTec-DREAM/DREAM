package dream.consult.program.uploaddata.form;

import dream.comm.form.MaFinderAcForm;
import dream.consult.program.uploaddata.dto.LovExcelTabAcListDTO;

/**
 * Excel Tab Form
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovExcelTabAcListForm"
 */
public class LovExcelTabAcListForm extends MaFinderAcForm
{
    /** ¸Þ´º DTO */
    private LovExcelTabAcListDTO lovExcelTabAcListDTO = new LovExcelTabAcListDTO();

	public LovExcelTabAcListDTO getLovExcelTabAcListDTO() 
	{
		return lovExcelTabAcListDTO;
	}

	public void setLovExcelTabAcListDTO(LovExcelTabAcListDTO lovExcelTabAcListDTO) 
	{
		this.lovExcelTabAcListDTO = lovExcelTabAcListDTO;
	}
}
