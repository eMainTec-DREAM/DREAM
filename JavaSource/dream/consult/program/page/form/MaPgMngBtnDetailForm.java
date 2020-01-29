package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngBtnDetailDTO;
import dream.consult.program.page.dto.MaPgMngBtnListDTO;
import dream.consult.program.page.dto.MaPgMngCommonDTO;

/**
 * ȭ�麰 ��ư �� 
 * @author  kim2107
 * @version $Id: MaPgMngBtnDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngBtnDetailForm"
 */
public class MaPgMngBtnDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
	/** ȭ�麰 ��ư ��� DTO  */
    private MaPgMngBtnListDTO maPgMngBtnListDTO = new MaPgMngBtnListDTO();
	/** ȭ�麰 ��ư �� DTO  */
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
