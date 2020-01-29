package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkDetailDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * ���� �����۾� ��
 * @author  kim2107
 * @version $Id: MaEqMstrPmWorkDetailForm.java,v 1.0 2015/12/04 09:09:54 kim2107 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmWorkDetailForm"
 */
public class MaEqMstrPmWorkDetailForm extends BaseForm
{    
    //===============================================================
    /** ���� DTO */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
	/** ���� �����۾� ��� DTO  */
    private MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO = new MaEqMstrPmWorkListDTO();
	/** ���� �����۾� �� DTO  */
    private MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO = new MaEqMstrPmWorkDetailDTO();
    
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmWorkListDTO getMaEqMstrPmWorkListDTO() {
		return maEqMstrPmWorkListDTO;
	}
	public void setMaEqMstrPmWorkListDTO(MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO) {
		this.maEqMstrPmWorkListDTO = maEqMstrPmWorkListDTO;
	}
	public MaEqMstrPmWorkDetailDTO getMaEqMstrPmWorkDetailDTO() {
		return maEqMstrPmWorkDetailDTO;
	}
	public void setMaEqMstrPmWorkDetailDTO(MaEqMstrPmWorkDetailDTO maEqMstrPmWorkDetailDTO) {
		this.maEqMstrPmWorkDetailDTO = maEqMstrPmWorkDetailDTO;
	}
}
