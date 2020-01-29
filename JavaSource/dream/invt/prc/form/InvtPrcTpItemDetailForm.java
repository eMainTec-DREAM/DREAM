package dream.invt.prc.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.invt.prc.dto.InvtPrcTpItemDetailDTO;
import dream.invt.prc.dto.InvtPrcTpItemListDTO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;

/**
 * �������� Item
 * @author  hyosung
 * @version $Id: InvtPrcTpItemDetailForm.java,v 1.0 2015/12/04 09:09:54 hyosung Exp $
 * @since   1.0
 *
 * @struts.form name="invtPrcTpItemDetailForm"
 */
public class InvtPrcTpItemDetailForm extends BaseForm
{    
    //===============================================================
    /** �������� - ���� DTO */
    private InvtPrcTpCommonDTO invtPrcTpCommonDTO = new InvtPrcTpCommonDTO();
	/** ��������  DTO  */
    private InvtPrcTpItemListDTO invtPrcTpItemListDTO = new InvtPrcTpItemListDTO();
	/** ��������  Detail DTO  */
    private InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO = new InvtPrcTpItemDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public InvtPrcTpItemListDTO getInvtPrcTpItemListDTO() {
		return invtPrcTpItemListDTO;
	}
	public void setInvtPrcTpItemListDTO(InvtPrcTpItemListDTO invtPrcTpItemListDTO) {
		this.invtPrcTpItemListDTO = invtPrcTpItemListDTO;
	}
	public InvtPrcTpItemDetailDTO getInvtPrcTpItemDetailDTO() {
		return invtPrcTpItemDetailDTO;
	}
	public void setInvtPrcTpItemDetailDTO(InvtPrcTpItemDetailDTO invtPrcTpItemDetailDTO) {
		this.invtPrcTpItemDetailDTO = invtPrcTpItemDetailDTO;
	}
	public InvtPrcTpCommonDTO getInvtPrcTpCommonDTO() {
		return invtPrcTpCommonDTO;
	}
	public void setInvtPrcTpCommonDTO(InvtPrcTpCommonDTO invtPrcTpCommonDTO) {
		this.invtPrcTpCommonDTO = invtPrcTpCommonDTO;
	}
	
}
