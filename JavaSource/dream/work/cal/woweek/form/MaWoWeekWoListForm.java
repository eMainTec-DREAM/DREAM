package dream.work.cal.woweek.form;

import common.struts.BaseForm;
import dream.work.cal.woweek.dto.MaWoWeekWoCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * �ְ��۾����� - ��� form
 * @author  kim21017
 * @version $Id: MaWoWeekWoListForm.java,v 1.0 2015/12/01 09:13:09 kim21017 Exp $
 * @since   1.0
 *
 * @struts.form name="maWoWeekWoListForm"
 */
public class MaWoWeekWoListForm extends BaseForm
{    
    //===============================================================
    /** �ְ��۾����� ���� */
    private MaWoWeekWoCommonDTO maWoWeekWoCommonDTO = new MaWoWeekWoCommonDTO();
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    
	public MaWoWeekWoCommonDTO getMaWoWeekWoCommonDTO() {
		return maWoWeekWoCommonDTO;
	}

	public void setMaWoWeekWoCommonDTO(MaWoWeekWoCommonDTO maWoWeekWoCommonDTO) {
		this.maWoWeekWoCommonDTO = maWoWeekWoCommonDTO;
	}

	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}

	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	

}
