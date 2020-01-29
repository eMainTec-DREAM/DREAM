package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * 화면별 필드 기본값 목록
 * @author  kim21017
 * @version $Id: MaPgMngFieldValueListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngFieldValueListForm"
 */
public class MaPgMngFieldValueListForm extends BaseForm
{    
    //===============================================================
    /** 공통 */
    private MaPgMngCommonDTO maPgMngCommonDTO 					= new MaPgMngCommonDTO();
    /** page-field */
    private MaPgMngFieldListDTO maPgMngFieldListDTO 			= new MaPgMngFieldListDTO();
    /** page-field detail */
    private MaPgMngFieldDetailDTO maPgMngFieldDetailDTO 			= new MaPgMngFieldDetailDTO();
    /** page-field-Value */
    private MaPgMngFieldValueListDTO maPgMngFieldValueListDTO 	= new MaPgMngFieldValueListDTO();

    
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

	public MaPgMngFieldListDTO getMaPgMngFieldListDTO() {
		return maPgMngFieldListDTO;
	}

	public void setMaPgMngFieldListDTO(MaPgMngFieldListDTO maPgMngFieldListDTO) {
		this.maPgMngFieldListDTO = maPgMngFieldListDTO;
	}

	public MaPgMngFieldValueListDTO getMaPgMngFieldValueListDTO() {
		return maPgMngFieldValueListDTO;
	}

	public void setMaPgMngFieldValueListDTO(MaPgMngFieldValueListDTO maPgMngFieldValueListDTO) {
		this.maPgMngFieldValueListDTO = maPgMngFieldValueListDTO;
	}
	
}
