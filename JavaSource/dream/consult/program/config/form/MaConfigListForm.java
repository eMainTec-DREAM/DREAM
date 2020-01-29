package dream.consult.program.config.form;

import common.struts.BaseForm;
import dream.consult.program.config.dto.MaConfigCommonDTO;

/**
 * 시스템환경변수 - 목록 form
 * @author  kim21017
 * @version $Id: MaConfigListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maConfigListForm"
 */
public class MaConfigListForm extends BaseForm
{    
    //===============================================================
    /** 시스템환경변수 공통 */
    private MaConfigCommonDTO maConfigCommonDTO = new MaConfigCommonDTO();
    
	public MaConfigCommonDTO getMaConfigCommonDTO() {
		return maConfigCommonDTO;
	}

	public void setMaConfigCommonDTO(MaConfigCommonDTO maConfigCommonDTO) {
		this.maConfigCommonDTO = maConfigCommonDTO;
	}
	
}
