package dream.mgr.cccd.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.cccd.dto.LovCtCtrListDTO;

/**
 * CP�˾� Form
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 *
 * @struts.form name="lovCtCtrListForm"
 */
public class LovCtCtrListForm extends MaFinderAcForm
{
    /** CP�˾� DTO */
    private LovCtCtrListDTO lovCtCtrListDTO = new LovCtCtrListDTO();

	public LovCtCtrListDTO getLovCtCtrListDTO() 
	{
		return lovCtCtrListDTO;
	}

	public void setLovCtCtrListDTO(LovCtCtrListDTO lovCtCtrListDTO) 
	{
		this.lovCtCtrListDTO = lovCtCtrListDTO;
	}
}
