package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 목록
 * @author  kim21017
 * @version $Id: MaPgMngBtnListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngBtnListForm"
 */
public class MaPgMngBtnListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
    /**  page-btn  */
    private MaPgMngBtnListDTO maPgMngBtnListDTO = new MaPgMngBtnListDTO();
	

	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}

	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}

	public MaPgMngBtnListDTO getMaPgMngBtnListDTO() {
		return maPgMngBtnListDTO;
	}

	public void setMaPgMngBtnListDTO(MaPgMngBtnListDTO maPgMngBtnListDTO) {
		this.maPgMngBtnListDTO = maPgMngBtnListDTO;
	}
}
