package dream.rcm.taskmap.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemDetailDTO;
import dream.rcm.taskmap.dto.RcmTaskMapItemListDTO;
import dream.rcm.taskmap.dto.RcmTaskMapCommonDTO;

/**
 * �亯
 * @author  kim2107
 * @version $Id: RcmTaskMapItemDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="rcmTaskMapItemDetailForm"
 */
public class RcmTaskMapItemDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� - ���� DTO */
    private RcmTaskMapCommonDTO rcmTaskMapCommonDTO = new RcmTaskMapCommonDTO();
	/** �亯  DTO  */
    private RcmTaskMapItemListDTO rcmTaskMapItemListDTO = new RcmTaskMapItemListDTO();
	/** �亯  Detail DTO  */
    private RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO = new RcmTaskMapItemDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();
    
	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public RcmTaskMapItemListDTO getRcmTaskMapItemListDTO() {
		return rcmTaskMapItemListDTO;
	}
	public void setRcmTaskMapItemListDTO(RcmTaskMapItemListDTO rcmTaskMapItemListDTO) {
		this.rcmTaskMapItemListDTO = rcmTaskMapItemListDTO;
	}
	public RcmTaskMapItemDetailDTO getRcmTaskMapItemDetailDTO() {
		return rcmTaskMapItemDetailDTO;
	}
	public void setRcmTaskMapItemDetailDTO(RcmTaskMapItemDetailDTO rcmTaskMapItemDetailDTO) {
		this.rcmTaskMapItemDetailDTO = rcmTaskMapItemDetailDTO;
	}
	public RcmTaskMapCommonDTO getRcmTaskMapCommonDTO() {
		return rcmTaskMapCommonDTO;
	}
	public void setRcmTaskMapCommonDTO(RcmTaskMapCommonDTO rcmTaskMapCommonDTO) {
		this.rcmTaskMapCommonDTO = rcmTaskMapCommonDTO;
	}
	
}
