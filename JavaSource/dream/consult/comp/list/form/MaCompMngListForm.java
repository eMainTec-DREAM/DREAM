package dream.consult.comp.list.form;

import common.struts.BaseForm;
import dream.consult.comp.list.dto.MaCompMngCommonDTO;

/**
 * 회사설정 - 목록 form
 * @author  kim21017
 * @version $Id: MaCompMngListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maCompMngListForm"
 */
public class MaCompMngListForm extends BaseForm
{    
    //===============================================================
    /** 회사설정 공통 */
    private MaCompMngCommonDTO maCompMngCommonDTO = new MaCompMngCommonDTO();

	public MaCompMngCommonDTO getMaCompMngCommonDTO() {
		return maCompMngCommonDTO;
	}

	public void setMaCompMngCommonDTO(MaCompMngCommonDTO maCompMngCommonDTO) {
		this.maCompMngCommonDTO = maCompMngCommonDTO;
	}

}
