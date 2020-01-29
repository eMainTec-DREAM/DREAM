package dream.work.list.form;

import common.struts.BaseForm;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointDetailDTO;
import dream.work.list.dto.MaWoResultPointListDTO;

/**
 * �۾���� �˻��׸�
 * @author  kim2107
 * @version $Id: MaWoResultPointDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultPointDetailForm"
 */
public class MaWoResultPointDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	/** �۾���� �˻��׸� ��� DTO  */
    private MaWoResultPointListDTO maWoResultPointListDTO = new MaWoResultPointListDTO();
	/** �۾���� �˻��׸� �� DTO  */
    private MaWoResultPointDetailDTO maWoResultPointDetailDTO = new MaWoResultPointDetailDTO();
    /** ÷�ι��� DTO */
    private MaDocLibCommonDTO maDocLibCommonDTO = new MaDocLibCommonDTO();

	public MaDocLibCommonDTO getMaDocLibCommonDTO() {
		return maDocLibCommonDTO;
	}
	public void setMaDocLibCommonDTO(MaDocLibCommonDTO maDocLibCommonDTO) {
		this.maDocLibCommonDTO = maDocLibCommonDTO;
	}
	public MaWoResultPointListDTO getMaWoResultPointListDTO() {
		return maWoResultPointListDTO;
	}
	public void setMaWoResultPointListDTO(MaWoResultPointListDTO maWoResultPointListDTO) {
		this.maWoResultPointListDTO = maWoResultPointListDTO;
	}
	public MaWoResultPointDetailDTO getMaWoResultPointDetailDTO() {
		return maWoResultPointDetailDTO;
	}
	public void setMaWoResultPointDetailDTO(MaWoResultPointDetailDTO maWoResultPointDetailDTO) {
		this.maWoResultPointDetailDTO = maWoResultPointDetailDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
