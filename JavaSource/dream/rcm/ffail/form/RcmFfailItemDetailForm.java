package dream.rcm.ffail.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.ffail.dto.RcmFfailItemDetailDTO;
import dream.rcm.ffail.dto.RcmFfailItemListDTO;
import dream.rcm.ffail.dto.RcmFfailCommonDTO;

/**
 * �亯
 * @author  kim2107
 * @version $Id: RcmFfailItemDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmFfailItemDetailForm"
 */
public class RcmFfailItemDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� - ���� DTO */
    private RcmFfailCommonDTO rcmFfailCommonDTO = new RcmFfailCommonDTO();
	/** �亯  DTO  */
    private RcmFfailItemListDTO rcmFfailItemListDTO = new RcmFfailItemListDTO();
	/** �亯  Detail DTO  */
    private RcmFfailItemDetailDTO rcmFfailItemDetailDTO = new RcmFfailItemDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmFfailItemListDTO getRcmFfailItemListDTO() {
		return rcmFfailItemListDTO;
	}
	public void setRcmFfailItemListDTO(RcmFfailItemListDTO rcmFfailItemListDTO) {
		this.rcmFfailItemListDTO = rcmFfailItemListDTO;
	}
	public RcmFfailItemDetailDTO getRcmFfailItemDetailDTO() {
		return rcmFfailItemDetailDTO;
	}
	public void setRcmFfailItemDetailDTO(RcmFfailItemDetailDTO rcmFfailItemDetailDTO) {
		this.rcmFfailItemDetailDTO = rcmFfailItemDetailDTO;
	}
	public RcmFfailCommonDTO getRcmFfailCommonDTO() {
		return rcmFfailCommonDTO;
	}
	public void setRcmFfailCommonDTO(RcmFfailCommonDTO rcmFfailCommonDTO) {
		this.rcmFfailCommonDTO = rcmFfailCommonDTO;
	}
	
}
