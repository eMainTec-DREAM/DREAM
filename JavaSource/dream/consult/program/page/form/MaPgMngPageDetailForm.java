package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngPageDetailDTO;
import dream.consult.program.page.dto.MaPgMngPageListDTO;

/**
 * ȭ�麰 �������� �� 
 * @author  kim2107
 * @version $Id: MaPgMngPageDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngPageDetailForm"
 */
public class MaPgMngPageDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO = new MaPgMngCommonDTO();
	/** ȭ�麰 �������� ��� DTO  */
    private MaPgMngPageListDTO maPgMngPageListDTO = new MaPgMngPageListDTO();
	/** ȭ�麰 �������� �� DTO  */
    private MaPgMngPageDetailDTO maPgMngPageDetailDTO = new MaPgMngPageDetailDTO();
    
	public MaPgMngPageListDTO getMaPgMngPageListDTO() {
		return maPgMngPageListDTO;
	}
	public void setMaPgMngPageListDTO(MaPgMngPageListDTO maPgMngPageListDTO) {
		this.maPgMngPageListDTO = maPgMngPageListDTO;
	}
	public MaPgMngPageDetailDTO getMaPgMngPageDetailDTO() {
		return maPgMngPageDetailDTO;
	}
	public void setMaPgMngPageDetailDTO(MaPgMngPageDetailDTO maPgMngPageDetailDTO) {
		this.maPgMngPageDetailDTO = maPgMngPageDetailDTO;
	}
	public MaPgMngCommonDTO getMaPgMngCommonDTO() {
		return maPgMngCommonDTO;
	}
	public void setMaPgMngCommonDTO(MaPgMngCommonDTO maPgMngCommonDTO) {
		this.maPgMngCommonDTO = maPgMngCommonDTO;
	}
}
