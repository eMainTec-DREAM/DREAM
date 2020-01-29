package gaia.gapg.form;

import common.struts.BaseForm;
import gaia.gapg.dto.GaPgMngCommonDTO;

/**
 * 화면 - 목록 form
 * @author  kim21017
 * @version $Id: GaPgMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="gaPgMngListForm"
 */
public class GaPgMngListForm extends BaseForm
{    
    //===============================================================
    /** 화면 공통 */
    private GaPgMngCommonDTO gaPgMngCommonDTO = new GaPgMngCommonDTO();
    
	public GaPgMngCommonDTO getGaPgMngCommonDTO() {
		return gaPgMngCommonDTO;
	}

	public void setGaPgMngCommonDTO(GaPgMngCommonDTO gaPgMngCommonDTO) {
		this.gaPgMngCommonDTO = gaPgMngCommonDTO;
	}
}
