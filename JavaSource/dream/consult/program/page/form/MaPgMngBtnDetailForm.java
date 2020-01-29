package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * 화면별 버튼 상세 
 * @author  kim2107
 * @version $Id: MaPgMngBtnDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngBtnDetailForm"
 */
public class MaPgMngBtnDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
	/** 화면별 버튼 목록 DTO  */
    private MaPgMngBtnListDTO maPgMngBtnListDTO = new MaPgMngBtnListDTO();
	/** 화면별 버튼 상세 DTO  */
    private MaPgMngBtnDetailDTO maPgMngBtnDetailDTO = new MaPgMngBtnDetailDTO();
    
	public MaPgMngBtnListDTO getMaPgMngBtnListDTO() {
		return maPgMngBtnListDTO;
	}
	public void setMaPgMngBtnListDTO(MaPgMngBtnListDTO maPgMngBtnListDTO) {
		this.maPgMngBtnListDTO = maPgMngBtnListDTO;
	}
	public MaPgMngBtnDetailDTO getMaPgMngBtnDetailDTO() {
		return maPgMngBtnDetailDTO;
	}
	public void setMaPgMngBtnDetailDTO(MaPgMngBtnDetailDTO maPgMngBtnDetailDTO) {
		this.maPgMngBtnDetailDTO = maPgMngBtnDetailDTO;
	}
	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}
	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}
}
