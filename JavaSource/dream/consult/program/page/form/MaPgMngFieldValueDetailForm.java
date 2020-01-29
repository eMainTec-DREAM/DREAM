package dream.consult.program.page.form;

import common.struts.BaseForm;
import dream.consult.program.page.dto.MaPgMngCommonDTO;
import dream.consult.program.page.dto.MaPgMngFieldDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldListDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueDetailDTO;
import dream.consult.program.page.dto.MaPgMngFieldValueListDTO;

/**
 * ȭ�麰 �ʵ� �⺻�� �� 
 * @author  kim2107
 * @version $Id: MaPgMngFieldValueDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maPgMngFieldValueDetailForm"
 */
public class MaPgMngFieldValueDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaPgMngCommonDTO maPgMngCommonDTO 						= new MaPgMngCommonDTO();
    /** ȭ�麰 �ʵ� */
    private MaPgMngFieldListDTO maPgMngFieldListDTO 				= new MaPgMngFieldListDTO();
    /** ȭ�麰 �ʵ� �� */
    private MaPgMngFieldDetailDTO maPgMngFieldDetailDTO 			= new MaPgMngFieldDetailDTO();
	/** ȭ�麰 �ʵ� �⺻�� ��� DTO  */
    private MaPgMngFieldValueListDTO maPgMngFieldValueListDTO 		= new MaPgMngFieldValueListDTO();
	/** ȭ�麰 �ʵ� �⺻�� �� DTO  */
    private MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO 	= new MaPgMngFieldValueDetailDTO();
    
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
	public MaPgMngFieldValueDetailDTO getMaPgMngFieldValueDetailDTO() {
		return maPgMngFieldValueDetailDTO;
	}
	public void setMaPgMngFieldValueDetailDTO(MaPgMngFieldValueDetailDTO maPgMngFieldValueDetailDTO) {
		this.maPgMngFieldValueDetailDTO = maPgMngFieldValueDetailDTO;
	}
}
