package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmInsListDTO;

/**
 * ������ �������� ���
 * @author  kim21017
 * @version $Id: MaEqMstrPmInsListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmInsListForm"
 */
public class MaEqMstrPmInsListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  ���� �����۾� ���  */
    private MaEqMstrPmInsListDTO maEqMstrPmInsListDTO = new MaEqMstrPmInsListDTO();
    
	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}
	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}
	public MaEqMstrPmInsListDTO getMaEqMstrPmInsListDTO() {
		return maEqMstrPmInsListDTO;
	}
	public void setMaEqMstrPmInsListDTO(MaEqMstrPmInsListDTO maEqMstrPmInsListDTO) {
		this.maEqMstrPmInsListDTO = maEqMstrPmInsListDTO;
	}

}
