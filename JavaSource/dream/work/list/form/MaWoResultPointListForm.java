package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;

/**
 * �۾���� �˻��׸� ���
 * @author  kim21017
 * @version $Id: MaWoResultPointListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoResultPointListForm"
 */
public class MaWoResultPointListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /**  �۾���� �˻��׸� ���  */
    private MaWoResultPointListDTO maWoResultPointListDTO = new MaWoResultPointListDTO();
	

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

	public MaWoResultPointListDTO getMaWoResultPointListDTO() {
		return maWoResultPointListDTO;
	}

	public void setMaWoResultPointListDTO(MaWoResultPointListDTO maWoResultPointListDTO) {
		this.maWoResultPointListDTO = maWoResultPointListDTO;
	}
}
