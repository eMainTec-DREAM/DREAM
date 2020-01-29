package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkPartListDTO;

/**
 * ���� �����۾�-��ǰ ��
 * @author  kim2107
 * @version $Id: MaEqMstrPmWorkPartDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmWorkPartDetailForm"
 */
public class MaEqMstrPmWorkPartDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** ���� �����۾� �� DTO  */
    private MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = new MaEqMstrPmWorkDetailDTO();
	/** ���� �����۾� ��ǰ �׸� ��� DTO  */
    private MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO = new MaEqMstrPmWorkPartListDTO();
	/** ���� �����۾� ��ǰ �׸� �� DTO  */
    private MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO = new MaEqMstrPmWorkPartDetailDTO();
    
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmWorkDetailDTO getMaEqMstrPmWorkDetailDTO() {
		return maEqMstrPmWorkDetailDTO;
	}
	public void setMaEqMstrPmWorkDetailDTO(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO) {
		this.maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailDTO;
	}
	public MaEqMstrPmWorkPartListDTO getMaEqMstrPmWorkPartListDTO() {
		return maEqMstrPmWorkPartListDTO;
	}
	public void setMaEqMstrPmWorkPartListDTO(MaEqMstrPmWorkPartListDTO maEqMstrPmWorkPartListDTO) {
		this.maEqMstrPmWorkPartListDTO = maEqMstrPmWorkPartListDTO;
	}
	public MaEqMstrPmWorkPartDetailDTO getMaEqMstrPmWorkPartDetailDTO() {
		return maEqMstrPmWorkPartDetailDTO;
	}
	public void setMaEqMstrPmWorkPartDetailDTO(MaEqMstrPmWorkPartDetailDTO maEqMstrPmWorkPartDetailDTO) {
		this.maEqMstrPmWorkPartDetailDTO = maEqMstrPmWorkPartDetailDTO;
	}
}
