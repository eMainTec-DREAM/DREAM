package dream.work.cal.pmperiod.form;

import common.struts.BaseForm;
import dream.work.cal.pmperiod.dto.MaWoSchedCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �����۾�����(�Ⱓ) - ��� form
 * @author  kim21017
 * @version $Id: MaWoSchedListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoSchedListForm"
 */
public class MaWoSchedListForm extends BaseForm
{    
    //===============================================================
    /** �����۾�����(�Ⱓ) ���� */
    private MaWoSchedCommonDTO maWoSchedCommonDTO = new MaWoSchedCommonDTO();

    /** �۾� ���� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

	public MaWoSchedCommonDTO getMaWoSchedCommonDTO() {
		return maWoSchedCommonDTO;
	}

	public void setMaWoSchedCommonDTO(MaWoSchedCommonDTO maWoSchedCommonDTO) {
		this.maWoSchedCommonDTO = maWoSchedCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}

}
