package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;

/**
 * 화면별 필드 상세 
 * @author  kim2107
 * @version $Id: MaPgMngFieldDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngFieldDetailForm"
 */
public class MaPgMngFieldDetailForm extends BaseForm
{    
    //===============================================================
    /** 공통 DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
	/** 화면별 필드 목록 DTO  */
    private MaPgMngFieldListDTO maPgMngFieldListDTO = new MaPgMngFieldListDTO();
	/** 화면별 필드 상세 DTO  */
    private MaPgMngFieldDetailDTO maPgMngFieldDetailDTO = new MaPgMngFieldDetailDTO();
    
	public MaPgMngFieldListDTO getMaPgMngFieldListDTO() {
		return maPgMngFieldListDTO;
	}
	public void setMaPgMngFieldListDTO(MaPgMngFieldListDTO maPgMngFieldListDTO) {
		this.maPgMngFieldListDTO = maPgMngFieldListDTO;
	}
	public MaPgMngFieldDetailDTO getMaPgMngFieldDetailDTO() {
		return maPgMngFieldDetailDTO;
	}
	public void setMaPgMngFieldDetailDTO(MaPgMngFieldDetailDTO maPgMngFieldDetailDTO) {
		this.maPgMngFieldDetailDTO = maPgMngFieldDetailDTO;
	}
	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}
	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}
}
