package dream.consult.program.menu.form;

import java.util.Collection;

import common.struts.BaseForm;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;
import dream.consult.program.menu.dto.MaMenuMngDetailDTO;

/**
 * 메뉴- 상세 Form
 * @author  kim21017
 * @version $Id: MaMenuMngDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maMenuMngDetailForm"
 */
public class MaMenuMngDetailForm extends BaseForm
{
    //========================================================================
    /** 메뉴 공통 */ 
    private MaMenuMngCommonDTO maMenuMngCommonDTO = new MaMenuMngCommonDTO();
    //========================================================================
    /** 메뉴 상세 */
    private MaMenuMngDetailDTO maMenuMngDetailDTO = new MaMenuMngDetailDTO();
    
	public MaMenuMngCommonDTO getMaMenuMngCommonDTO() {
		return maMenuMngCommonDTO;
	}

	public void setMaMenuMngCommonDTO(MaMenuMngCommonDTO maMenuMngCommonDTO) {
		this.maMenuMngCommonDTO = maMenuMngCommonDTO;
	}

	public MaMenuMngDetailDTO getMaMenuMngDetailDTO() {
		return maMenuMngDetailDTO;
	}

	public void setMaMenuMngDetailDTO(MaMenuMngDetailDTO maMenuMngDetailDTO) {
		this.maMenuMngDetailDTO = maMenuMngDetailDTO;
	}

}
