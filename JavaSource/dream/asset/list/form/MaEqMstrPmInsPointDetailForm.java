package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointDetailDTO;
import dream.asset.list.dto.MaEqMstrPmInsPointListDTO;

/**
 * ���� ��������-�����׸� ��
 * @author  kim2107
 * @version $Id: MaEqMstrPmInsPointDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmInsPointDetailForm"
 */
public class MaEqMstrPmInsPointDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** ���� �������� �� DTO  */
    private MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO = new MaEqMstrPmInsDetailDTO();
	/** ���� �������� �׸� ��� DTO  */
    private MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO = new MaEqMstrPmInsPointListDTO();
	/** ���� �������� �׸� �� DTO  */
    private MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO = new MaEqMstrPmInsPointDetailDTO();
    
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmInsDetailDTO getMaEqMstrPmInsDetailDTO() {
		return maEqMstrPmInsDetailDTO;
	}
	public void setMaEqMstrPmInsDetailDTO(MaEqMstrPmInsDetailDTO maEqMstrPmInsDetailDTO) {
		this.maEqMstrPmInsDetailDTO = maEqMstrPmInsDetailDTO;
	}
	public MaEqMstrPmInsPointListDTO getMaEqMstrPmInsPointListDTO() {
		return maEqMstrPmInsPointListDTO;
	}
	public void setMaEqMstrPmInsPointListDTO(MaEqMstrPmInsPointListDTO maEqMstrPmInsPointListDTO) {
		this.maEqMstrPmInsPointListDTO = maEqMstrPmInsPointListDTO;
	}
	public MaEqMstrPmInsPointDetailDTO getMaEqMstrPmInsPointDetailDTO() {
		return maEqMstrPmInsPointDetailDTO;
	}
	public void setMaEqMstrPmInsPointDetailDTO(MaEqMstrPmInsPointDetailDTO maEqMstrPmInsPointDetailDTO) {
		this.maEqMstrPmInsPointDetailDTO = maEqMstrPmInsPointDetailDTO;
	}
}
