package dream.consult.comp.time.form;

import common.struts.BaseForm;
import dream.consult.comp.time.dto.MaLineTimeCommonDTO;
import dream.consult.comp.time.dto.MaLineTimeDowListDTO;

/**
 * 요일별 설정 목록
 * @author  kim21017
 * @version $Id: MaLineTimeDowListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maLineTimeDowListForm"
 */
public class MaLineTimeDowListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaLineTimeCommonDTO maLineTimeCommonDTO = new MaLineTimeCommonDTO();
    /** 요일별 설정 목록  */
    private MaLineTimeDowListDTO maLineTimeDowListDTO = new MaLineTimeDowListDTO();
	

	public MaLineTimeCommonDTO getMaLineTimeCommonDTO() {
		return maLineTimeCommonDTO;
	}

	public void setMaLineTimeCommonDTO(MaLineTimeCommonDTO maLineTimeCommonDTO) {
		this.maLineTimeCommonDTO = maLineTimeCommonDTO;
	}

	public MaLineTimeDowListDTO getMaLineTimeDowListDTO() {
		return maLineTimeDowListDTO;
	}

	public void setMaLineTimeDowListDTO(MaLineTimeDowListDTO maLineTimeDowListDTO) {
		this.maLineTimeDowListDTO = maLineTimeDowListDTO;
	}
}
