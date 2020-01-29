package dream.consult.comp.cdsys.form;

import common.struts.BaseForm;
import dream.consult.comp.cdsys.dto.MaCdSysCodeDetailDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCodeListDTO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;

/**
 * �ý����ڵ� - �з� 
 * @author  kim2107
 * @version $Id: MaCdSysCodeDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maCdSysCodeDetailForm"
 */
public class MaCdSysCodeDetailForm extends BaseForm
{    
    //===============================================================
    /** �ý��� �ڵ� - ���� DTO */
    private MaCdSysCommonDTO maCdSysCommonDTO = new MaCdSysCommonDTO();
	/** �ý��� �ڵ� - �з� DTO  */
    private MaCdSysCodeListDTO maCdSysCodeListDTO = new MaCdSysCodeListDTO();
	/** �ý��� �ڵ� - �з�Detail DTO  */
    private MaCdSysCodeDetailDTO maCdSysCodeDetailDTO = new MaCdSysCodeDetailDTO();
    
	public MaCdSysCodeListDTO getMaCdSysCodeListDTO() {
		return maCdSysCodeListDTO;
	}
	public void setMaCdSysCodeListDTO(MaCdSysCodeListDTO maCdSysCodeListDTO) {
		this.maCdSysCodeListDTO = maCdSysCodeListDTO;
	}
	public MaCdSysCodeDetailDTO getMaCdSysCodeDetailDTO() {
		return maCdSysCodeDetailDTO;
	}
	public void setMaCdSysCodeDetailDTO(MaCdSysCodeDetailDTO maCdSysCodeDetailDTO) {
		this.maCdSysCodeDetailDTO = maCdSysCodeDetailDTO;
	}
	public MaCdSysCommonDTO getMaCdSysCommonDTO() {
		return maCdSysCommonDTO;
	}
	public void setMaCdSysCommonDTO(MaCdSysCommonDTO maCdSysCommonDTO) {
		this.maCdSysCommonDTO = maCdSysCommonDTO;
	}
	
}
