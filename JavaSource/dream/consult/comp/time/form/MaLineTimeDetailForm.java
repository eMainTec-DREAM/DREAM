package dream.consult.comp.time.form;

import common.struts.BaseForm;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDetailDTO;

/**
 * 가동시간설정- 상세 Form
 * @author  kim21017
 * @version $Id: MaLineTimeDetailForm.java,v 1.0 2015/12/02 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLineTimeDetailForm"
 */
public class MaLineTimeDetailForm extends BaseForm
{
    //========================================================================
    /** 가동시간설정 공통 */ 
    private MaLineTimeCommonDTO maLineTimeCommonDTO = new MaLineTimeCommonDTO();
    //========================================================================
    /** 가동시간설정 상세 */
    private MaLineTimeDetailDTO maLineTimeDetailDTO = new MaLineTimeDetailDTO();
    

	public MaLineTimeCommonDTO getMaLineTimeCommonDTO() {
		return maLineTimeCommonDTO;
	}

	public void setMaLineTimeCommonDTO(MaLineTimeCommonDTO maLineTimeCommonDTO) {
		this.maLineTimeCommonDTO = maLineTimeCommonDTO;
	}

	public MaLineTimeDetailDTO getMaLineTimeDetailDTO() {
		return maLineTimeDetailDTO;
	}

	public void setMaLineTimeDetailDTO(MaLineTimeDetailDTO maLineTimeDetailDTO) {
		this.maLineTimeDetailDTO = maLineTimeDetailDTO;
	}

}
