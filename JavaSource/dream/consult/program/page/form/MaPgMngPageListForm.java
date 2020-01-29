package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;

/**
 * 화면별 탭페이지 목록
 * @author  kim21017
 * @version $Id: MaPgMngPageListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngPageListForm"
 */
public class MaPgMngPageListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    /**  탭페이지 목록 */
    private MaPgMngPageListDTO maPgMngPageListDTO = new MaPgMngPageListDTO();
	

	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}

	public MaPgMngPageListDTO getMaPgMngPageListDTO() {
		return maPgMngPageListDTO;
	}

	public void setMaPgMngPageListDTO(MaPgMngPageListDTO maPgMngPageListDTO) {
		this.maPgMngPageListDTO = maPgMngPageListDTO;
	}
}
