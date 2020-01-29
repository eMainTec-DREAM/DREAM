package dream.consult.program.menu.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;

/**
 * 메뉴 - 목록 form
 * @author  kim21017
 * @version $Id: MaMenuMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMenuMngListForm"
 */
public class MaMenuMngListForm extends BaseForm
{    
    //===============================================================
    /** 메뉴 공통 */
    private MaMenuMngCommonDTO maMenuMngCommonDTO = new MaMenuMngCommonDTO();
    
    /** 상위메뉴 Options */
    private Collection pMenuOptions = null;

    
	public Collection getpMenuOptions() {
		return pMenuOptions;
	}

	public void setpMenuOptions(Collection pMenuOptions) {
		this.pMenuOptions = pMenuOptions;
	}

	public MaMenuMngCommonDTO getMaMenuMngCommonDTO() {
		return maMenuMngCommonDTO;
	}

	public void setMaMenuMngCommonDTO(MaMenuMngCommonDTO maMenuMngCommonDTO) {
		this.maMenuMngCommonDTO = maMenuMngCommonDTO;
	}

}
