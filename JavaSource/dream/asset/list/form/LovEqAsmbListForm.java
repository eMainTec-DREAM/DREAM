package dream.asset.list.form;


import dream.asset.list.dto.LovEqAsmbListDTO;
import dream.comm.form.MaFinderAcForm;

/**
 * ��������˾� Form
 * @author  hyosun
 * @version $Id: LovEqAsmbListForm.java,v 1.0 2016/01/18 09:12:46 hyosun Exp $
 * @since   1.0
 *
 * @struts.form name="lovEqAsmbListForm"
 */
public class LovEqAsmbListForm extends MaFinderAcForm
{
    /** ��������˾� DTO */
    private LovEqAsmbListDTO lovEqAsmbListDTO = new LovEqAsmbListDTO();

	public LovEqAsmbListDTO getLovEqAsmbListDTO() {
		return lovEqAsmbListDTO;
	}

	public void setLovEqAsmbListDTO(LovEqAsmbListDTO lovEqAsmbListDTO) {
		this.lovEqAsmbListDTO = lovEqAsmbListDTO;
	}
}
