package dream.mgr.fiassetcd.form;

import dream.comm.form.MaFinderAcForm;
import dream.mgr.fiassetcd.dto.LovAssetListDTO;

/**
 * ÀÚ»êÆË¾÷ Form
 * @author  kim21017
 * @version $Id: LovAssetListForm.java,v 1.0 2016/01/18 09:12:46 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="lovAssetListForm"
 */
public class LovAssetListForm extends MaFinderAcForm
{
    /** ÀÚ»êÆË¾÷ DTO */
    private LovAssetListDTO lovAssetListDTO = new LovAssetListDTO();

	public LovAssetListDTO getLovAssetListDTO() {
		return lovAssetListDTO;
	}

	public void setLovAssetListDTO(LovAssetListDTO lovAssetListDTO) {
		this.lovAssetListDTO = lovAssetListDTO;
	}
}
