package dream.tool.list.form;

import dream.comm.form.MaFinderAcForm;
import dream.tool.list.dto.LovToolsListDTO;

/**
 * °ø±â±¸ ÆË¾÷ Form
 * @author  kim21017
 * @version $Id: LovToolsListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovToolsListForm"
 */
public class LovToolsListForm extends MaFinderAcForm
{
    /** °ø±â±¸ ÆË¾÷ DTO */
    private LovToolsListDTO lovToolsListDTO = new LovToolsListDTO();

	public LovToolsListDTO getLovToolsListDTO() {
		return lovToolsListDTO;
	}
	public void setLovToolsListDTO(LovToolsListDTO lovToolsListDTO) {
		this.lovToolsListDTO = lovToolsListDTO;
	}
}
