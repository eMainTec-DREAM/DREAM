package dream.asset.list.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPmWorkListDTO;

/**
 * ������ �����۾� ���
 * @author  kim21017
 * @version $Id: MaEqMstrPmWorkListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maEqMstrPmWorkListForm"
 */
public class MaEqMstrPmWorkListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /**  ���� �����۾� ���  */
    private MaEqMstrPmWorkListDTO maEqMstrPmWorkListDTO = new MaEqMstrPmWorkListDTO();
    
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

}
