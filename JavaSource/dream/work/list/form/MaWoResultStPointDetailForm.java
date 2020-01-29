package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultStPointDetailDTO;
import dream.work.list.dto.MaWoResultStPointListDTO;

/**
 * �۾���� �۾��ʼ��˻��׸�
 * @author  kim2107
 * @version $Id: MaWoResultStPointDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultStPointDetailForm"
 */
public class MaWoResultStPointDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** �۾���� �۾��ʼ��˻��׸� ��� DTO  */
    private MaWoResultStPointListDTO maWoResultStPointListDTO = new MaWoResultStPointListDTO();
	/** �۾���� �۾��ʼ��˻��׸� �� DTO  */
    private MaWoResultStPointDetailDTO maWoResultStPointDetailDTO = new MaWoResultStPointDetailDTO();
    
	public MaWoResultStPointListDTO getMaWoResultStPointListDTO() {
		return maWoResultStPointListDTO;
	}
	public void setMaWoResultStPointListDTO(MaWoResultStPointListDTO maWoResultStPointListDTO) {
		this.maWoResultStPointListDTO = maWoResultStPointListDTO;
	}
	public MaWoResultStPointDetailDTO getMaWoResultStPointDetailDTO() {
		return maWoResultStPointDetailDTO;
	}
	public void setMaWoResultStPointDetailDTO(MaWoResultStPointDetailDTO maWoResultStPointDetailDTO) {
		this.maWoResultStPointDetailDTO = maWoResultStPointDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
