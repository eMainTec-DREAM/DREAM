package dream.rcm.funceq.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * �亯
 * @author  kim2107
 * @version $Id: RcmFuncEqItemDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFuncEqItemDetailForm"
 */
public class RcmFuncEqItemDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� - ���� DTO */
    private RcmFuncEqCommonDTO rcmFuncEqCommonDTO = new RcmFuncEqCommonDTO();
	/** �亯  DTO  */
    private RcmFuncEqItemListDTO rcmFuncEqItemListDTO = new RcmFuncEqItemListDTO();
	/** �亯  Detail DTO  */
    private RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO = new RcmFuncEqItemDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmFuncEqItemListDTO getRcmFuncEqItemListDTO() {
		return rcmFuncEqItemListDTO;
	}
	public void setRcmFuncEqItemListDTO(RcmFuncEqItemListDTO rcmFuncEqItemListDTO) {
		this.rcmFuncEqItemListDTO = rcmFuncEqItemListDTO;
	}
	public RcmFuncEqItemDetailDTO getRcmFuncEqItemDetailDTO() {
		return rcmFuncEqItemDetailDTO;
	}
	public void setRcmFuncEqItemDetailDTO(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO) {
		this.rcmFuncEqItemDetailDTO = rcmFuncEqItemDetailDTO;
	}
	public RcmFuncEqCommonDTO getRcmFuncEqCommonDTO() {
		return rcmFuncEqCommonDTO;
	}
	public void setRcmFuncEqCommonDTO(RcmFuncEqCommonDTO rcmFuncEqCommonDTO) {
		this.rcmFuncEqCommonDTO = rcmFuncEqCommonDTO;
	}
	
}
