package dream.work.list.form;

import common.struts.BaseForm;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �۾���� ������ ���
 * @author  kim21017
 * @version $Id: WorkListGnlCavalListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="workListGnlCavalListForm"
 */
public class WorkListGnlCavalListForm extends BaseForm
{    
    //===============================================================
    /** ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();


	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
}
