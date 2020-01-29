package dream.mgr.mail.form;

import common.struts.BaseForm;
import dream.mgr.mail.dto.MaMailCommonDTO;
import dream.mgr.mail.dto.MaMailUsrDetailDTO;
import dream.mgr.mail.dto.MaMailUsrListDTO;

/**
 * ���ϼ����ڼ��� - ������ 
 * @author  kim2107
 * @version $Id: MaMailUsrDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maMailUsrDetailForm"
 */
public class MaMailUsrDetailForm extends BaseForm
{    
    //===============================================================
    /** ���ϼ����ڼ��� - ���� DTO */
    private MaMailCommonDTO maMailCommonDTO = new MaMailCommonDTO();
	/** ���ϼ����ڼ��� - ������  DTO  */
    private MaMailUsrListDTO maMailUsrListDTO = new MaMailUsrListDTO();
	/** ���ϼ����ڼ��� - ������  Detail DTO  */
    private MaMailUsrDetailDTO maMailUsrDetailDTO = new MaMailUsrDetailDTO();
    
	public MaMailUsrListDTO getMaMailUsrListDTO() {
		return maMailUsrListDTO;
	}
	public void setMaMailUsrListDTO(MaMailUsrListDTO maMailUsrListDTO) {
		this.maMailUsrListDTO = maMailUsrListDTO;
	}
	public MaMailUsrDetailDTO getMaMailUsrDetailDTO() {
		return maMailUsrDetailDTO;
	}
	public void setMaMailUsrDetailDTO(MaMailUsrDetailDTO maMailUsrDetailDTO) {
		this.maMailUsrDetailDTO = maMailUsrDetailDTO;
	}
	public MaMailCommonDTO getMaMailCommonDTO() {
		return maMailCommonDTO;
	}
	public void setMaMailCommonDTO(MaMailCommonDTO maMailCommonDTO) {
		this.maMailCommonDTO = maMailCommonDTO;
	}
	
}
