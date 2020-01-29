package dream.rcm.system.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.system.dto.RcmSysEqAsmbDetailDTO;
import dream.rcm.system.dto.RcmSysEqAsmbListDTO;
import dream.rcm.system.dto.RcmSysCommonDTO;

/**
 * �������
 * @author  kim2107
 * @version $Id: RcmSysEqAsmbDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmSysEqAsmbDetailForm"
 */
public class RcmSysEqAsmbDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� - ���� DTO */
    private RcmSysCommonDTO rcmSysCommonDTO = new RcmSysCommonDTO();
	/** �亯  DTO  */
    private RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO = new RcmSysEqAsmbListDTO();
	/** �亯  Detail DTO  */
    private RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO = new RcmSysEqAsmbDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmSysEqAsmbListDTO getRcmSysEqAsmbListDTO() {
		return rcmSysEqAsmbListDTO;
	}
	public void setRcmSysEqAsmbListDTO(RcmSysEqAsmbListDTO rcmSysEqAsmbListDTO) {
		this.rcmSysEqAsmbListDTO = rcmSysEqAsmbListDTO;
	}
	public RcmSysEqAsmbDetailDTO getRcmSysEqAsmbDetailDTO() {
		return rcmSysEqAsmbDetailDTO;
	}
	public void setRcmSysEqAsmbDetailDTO(RcmSysEqAsmbDetailDTO rcmSysEqAsmbDetailDTO) {
		this.rcmSysEqAsmbDetailDTO = rcmSysEqAsmbDetailDTO;
	}
	public RcmSysCommonDTO getRcmSysCommonDTO() {
		return rcmSysCommonDTO;
	}
	public void setRcmSysCommonDTO(RcmSysCommonDTO rcmSysCommonDTO) {
		this.rcmSysCommonDTO = rcmSysCommonDTO;
	}
	
}
